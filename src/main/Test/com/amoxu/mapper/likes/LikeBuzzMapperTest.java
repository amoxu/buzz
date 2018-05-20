package com.amoxu.mapper.likes;

import com.amoxu.entity.likes.LikeBuzzKey;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        ,"classpath:spring/applicationContext-dao.xml"
        ,"classpath:spring/applicationContext-mail.xml"
        ,"classpath:spring/applicationContext-trsaction.xml"
})
public class LikeBuzzMapperTest {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    LikeBuzzMapper likeBuzzMapper;
    @Test
    public void ignoreIntoByPk() {
        int i = likeBuzzMapper.ignoreIntoByPk(new LikeBuzzKey().setBuzzId(100).setUid(1));
        logger.info("\n=================================\n"+i+"\n=================================");

    }
}