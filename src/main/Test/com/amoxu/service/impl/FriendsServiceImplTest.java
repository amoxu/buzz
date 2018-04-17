package com.amoxu.service.impl;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.PageResult;
import com.amoxu.service.FriendsService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        ,"classpath:spring/applicationContext-dao.xml"
        ,"classpath:spring/applicationContext-trsaction.xml"
        ,"classpath:spring/applicationContext-mail.xml"
})



public class FriendsServiceImplTest {
    private Logger logger = Logger.getLogger(getClass());
    @Resource(name = "friendsServiceImpl")
    private FriendsService friendsService;

    @Test
    public void getFrineds() {
        PageResult pageResult = new PageResult().setCount(10).setLimit(10).setOffset(0);

        logger.info(
                JSON.toJSONString(friendsService.getFriends(1, pageResult))
        );

    }

    @Test
    public void removeRelation() {
        logger.info(friendsService.removeRelation(23, 1));

    }

    @Test
    public void getFriends() {
    }
}