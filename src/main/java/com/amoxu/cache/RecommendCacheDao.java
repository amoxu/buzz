package com.amoxu.cache;

import com.amoxu.entity.BuzzNetease;
import com.amoxu.entity.BuzzNeteaseExample;
import com.amoxu.entity.UserFeature;
import com.amoxu.entity.UserFeatureExample;
import com.amoxu.mapper.BuzzNeteaseMapper;
import com.amoxu.mapper.UserFeatureMapper;
import com.amoxu.util.JedisPoolUtil;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Component
public class RecommendCacheDao {
    @Autowired
    UserFeatureMapper userFeatureMapper;
    @Autowired
    private BuzzNeteaseMapper buzzNeteaseMapper;
    @Autowired
    BuzzCacheDao buzzCache;

    private RuntimeSchema<BuzzNetease> schema = (RuntimeSchema<BuzzNetease>) RuntimeSchema.getSchema(BuzzNetease.class);
    private String key;
    public List<BuzzNetease> getCommend(Integer uid) {
        Jedis jedis = JedisPoolUtil.getJedis();
        key = "recommend:" + uid;
        try {
            List<BuzzNetease> buzzNeteases;
            byte[] bytes = jedis.get(key.getBytes());

            if (bytes == null || bytes.length == 0) {
                /*缓存不存在，在数据库取*/
                UserFeatureExample example = new UserFeatureExample();
                example.setLimit(5);
                example.setOrderByClause("counts desc");
                UserFeatureExample.Criteria criteria = example.createCriteria();
                criteria.andUidEqualTo(uid);

                List<UserFeature> userFeatures = userFeatureMapper.selectByExample(example);
                List<String> keyword = new ArrayList<>();

                for (UserFeature feature : userFeatures) {
                    keyword.add(feature.getName());
                }
                BuzzNeteaseExample buzzExample = new BuzzNeteaseExample();
                buzzExample.setLimit(5);
                buzzExample.setOffset(0);
                buzzExample.setOrderByClause("rand()");

                if (userFeatures.size() == 0) {
                    BuzzNeteaseExample.Criteria buzzExampleCriteria = buzzExample.createCriteria();
                    int allCount = buzzCache.getBuzzCount();

                    Random random = new Random();
                    ArrayList<Integer> ids = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        if (allCount < 2) {
                            allCount = 3;
                        }
                        ids.add(random.nextInt(allCount - 2) + 1);
                    }
                    buzzExampleCriteria.andIdIn(ids);
                    buzzNeteases = buzzNeteaseMapper.selectMain(uid, buzzExample);

                } else {
                    for (int i = 0; i < keyword.size(); i++) {
                        keyword.set(i, "%" + keyword.get(i) + "%");
                    }
                    buzzNeteases = buzzNeteaseMapper.selectUserRecommend(uid, keyword, buzzExample);
                }

                System.out.println("the count get from db is :" + buzzNeteases);

                setBuzzCount(uid, buzzNeteases);
                return buzzNeteases;
            }
            buzzNeteases = ProtostuffIOUtil.parseListFrom(new ByteArrayInputStream(bytes), schema);

            System.out.println("the count get from redis is :" + buzzNeteases);
            return buzzNeteases;
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            JedisPoolUtil.closeJedis(jedis);
        }
        return null;
    }

    private void setBuzzCount(Integer uid, List<BuzzNetease> buzzNeteases) {
        key = "recommend:" + uid;

        if (buzzNeteases == null) {
            return;
        }
        Jedis jedis = JedisPoolUtil.getJedis();

        try {
            /*设置0点过期*/
            int secondsLeftToday = (int) ((86400000 -
                                DateUtils.getFragmentInMilliseconds(Calendar.getInstance(), Calendar.DATE))/1000);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ProtostuffIOUtil.writeListTo(out, buzzNeteases, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            byte[] byteArray = out.toByteArray();
            jedis.setex(key.getBytes(),secondsLeftToday, byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            JedisPoolUtil.closeJedis(jedis);
        }
    }

}
