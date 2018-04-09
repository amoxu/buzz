package com.amoxu.service.impl;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.User;
import com.amoxu.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class UserServiceImplTest {
    final Logger logger = Logger.getLogger(getClass());
    @Resource(name = "userServiceImpl")
    UserService userService;

    @Test
    public void insetUser() {
        User user = new User();
        user.setNickname("amoxuk");
        user.setEmail("amoxu@aliyun.com");
        user.setPassword("123");
        Date date = new Date(new Date().getTime());

        user.setBirth(date);
        int ret = userService.insetUser(user);
        Assert.assertEquals(ret, 1);
    }

    @Test
    public void selectUserByName() {
        List<User> users = userService.selectUserLikeName("amoxuk");
        for (User user : users) {
            logger.info("user:" + user.getNickname()
                    + " id：" + user.getUid()
                    + " birth: " + user.getBirth()
                    + " role:" + user.getRoles().getName()
            );
        }
        Assert.assertEquals(users.size(), 1);
    }

    @Test
    public void selectUserById() {
        User u = userService.selectUserById(1);
        logger.info(u);
        Assert.assertEquals(u.getNickname(), "amoxuk");
    }

    @Test
    public void updateUser() {
        User u = userService.selectUserById(1);
        u.setSex("男");
        u.setCity("安徽-宣城");
        u.setEmail("amoxuk@aliyun.com");
        Assert.assertEquals(userService.updateUser(u), 1);
        u = userService.selectUserById(1);
        logger.info(u);
    }

    @Test
    public void getUserInfo() {
        User userInfo = userService.getUserInfo(1);
        int uid = userInfo.getUid();
        AjaxResult<List<User>> result = new AjaxResult<>();
        List<User> lu = new ArrayList<>();
        lu.add(userInfo);
        result.setData(lu);

        logger.info(result);
        Assert.assertEquals(uid, 1);
    }
}