package com.amoxu.entity;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class UserTest {
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void cloneTest() {
        User user = new User();
        user.setEmail("1234");
        user.setNickname("nick");
        user.setUid(1);
        User u = user.clone();
        User u1 = new User();
        logger.info(System.identityHashCode(u));
        logger.info(System.identityHashCode(u1));
        logger.info(System.identityHashCode(user));
        Assert.assertEquals(user == u, false);

    }
}