package com.amoxu.cache;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        , "classpath:spring/applicationContext-dao.xml"
        , "classpath:spring/applicationContext-mail.xml"
        , "classpath:spring/applicationContext-trsaction.xml"
})
public class RecommendCacheDaoTest {
    @Autowired
    RecommendCacheDao cacheDao;

    @Test
    public void getCommend() {
        List commend = cacheDao.getCommend(55);
        System.out.println(JSON.toJSONString(commend));

    }
}