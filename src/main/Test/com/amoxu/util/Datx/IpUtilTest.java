package com.amoxu.util.Datx;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.Coordinate;
import com.amoxu.util.JedisPoolUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/*.xml"
})
public class IpUtilTest {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Test
    public void getLocation() {
        IpUtil ipUtil = new IpUtil();
        Coordinate location = ipUtil.getLocation("61.190.213.226", sqlSessionFactory);
        Jedis jedis = JedisPoolUtil.getJedis();
        Random random = new Random();
        jedis.geoadd("location", location.getLng(), location.getLat(), "zs1");
        jedis.geoadd("location", location.getLng()+random.nextDouble(), location.getLat()+random.nextDouble(), "zs2");
        jedis.geoadd("location", location.getLng()+random.nextDouble(), location.getLat()+random.nextDouble(), "zs3");
        jedis.geoadd("location", location.getLng()+random.nextDouble(), location.getLat()+random.nextDouble(), "zs4");
        jedis.geoadd("location", location.getLng()+random.nextDouble(), location.getLat()+random.nextDouble(), "zs5");
        jedis.geoadd("location", location.getLng()+random.nextDouble(), location.getLat()+random.nextDouble(), "zs6");
        jedis.geoadd("location", location.getLng()+random.nextDouble(), location.getLat()+random.nextDouble(), "zs7");
        jedis.close();
        List<GeoRadiusResponse> location1 = jedis.georadius("location", location.getLng(), location.getLat(), 100, GeoUnit.KM);

        for (GeoRadiusResponse geo : location1) {
            System.err.println(JSON.toJSONString(geo));

        }

    }
}