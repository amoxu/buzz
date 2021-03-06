package com.amoxu.mapper;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void selectByKeyAndUser() {
        User user = userMapper.selectByKeyAndUser(1, 2);
        logger.info(JSON.toJSONString(user));

    }
}