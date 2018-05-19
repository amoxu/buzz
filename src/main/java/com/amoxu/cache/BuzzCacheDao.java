package com.amoxu.cache;

import com.amoxu.mapper.BuzzNeteaseMapper;
import com.amoxu.util.JedisPoolUtil;
import com.amoxu.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
@Component
public class BuzzCacheDao {
    @Autowired
    private BuzzNeteaseMapper buzzNeteaseMapper;


    private final static String CountKey = "buzz_count";
    public int getBuzzCount() {
        Jedis jedis = JedisPoolUtil.getJedis();
        try {
            int count;
            byte[] bytes = jedis.get(CountKey.getBytes());
            if (bytes == null || bytes.length == 0) {
                count = buzzNeteaseMapper.countByExample(null);

                System.out.println("the count get from db is :" + count);

                setBuzzCount(count);
                return count;
            }
            count = JedisUtil.deSeri(bytes, Integer.class);
            System.out.println("the count get from redis is :" + count);
            return count;
        }finally {
            JedisPoolUtil.closeJedis(jedis);
        }
    }

    private void setBuzzCount(Integer count) {
        if (count == null) {
            return;
        }
        Jedis jedis = JedisPoolUtil.getJedis();
        try {
            byte[] bytes = JedisUtil.enSeri(count);
            jedis.set(CountKey.getBytes(), bytes);
        } finally {
            JedisPoolUtil.closeJedis(jedis);
        }
    }
}
