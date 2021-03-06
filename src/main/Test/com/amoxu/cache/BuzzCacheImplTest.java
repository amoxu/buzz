package com.amoxu.cache;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        , "classpath:spring/applicationContext-dao.xml"
        , "classpath:spring/applicationContext-mail.xml"
        , "classpath:spring/applicationContext-trsaction.xml"
})
public class BuzzCacheImplTest {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    BuzzCacheDao buzzCache;
    @Test
    public void getBuzzCount() {
        int buzzCount = buzzCache.getBuzzCount();
        logger.info(buzzCount);
    }

    @Test
    public void setBuzzCount() {
    }
}