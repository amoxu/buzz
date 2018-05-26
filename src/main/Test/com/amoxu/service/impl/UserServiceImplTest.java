package com.amoxu.service.impl;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.User;
import com.amoxu.entity.UserExample;
import com.amoxu.exception.UnLoginException;
import com.amoxu.mapper.UserMapper;
import com.amoxu.service.UserService;
import com.amoxu.util.ToolKit;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void updateUser() throws UnLoginException {
        User u = userService.selectUserById(1);
        u.setSex("男");
        u.setCity("安徽-宣城");
        u.setEmail("amoxuk@aliyun.com");
        Assert.assertEquals(userService.updateUser(u), 1);
        u = userService.selectUserById(1);
        logger.info(u);
    }

    @Test
    public void getUserInfo() throws UnLoginException {
        User userInfo = userService.getUserInfo(1);
        int uid = userInfo.getUid();
        AjaxResult<List<User>> result = new AjaxResult<>();
        List<User> lu = new ArrayList<>();
        lu.add(userInfo);
        result.setData(lu);

        logger.info(result);
        Assert.assertEquals(uid, 1);
    }

    @Test
    public void sendMail2NewNail() {
        userService.sendMail2NewNail("");
    }

    @Autowired
    private UserMapper mapper;

    @Test
    public void findPassword() {
        User user = new User();
        user.setNote("");

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo("amoxu@qq.com");
        criteria.andNoteLike("%768710");

        mapper.updateByExampleSelective(user, example);
    }

    @Test
    public void activeUserMail() {
        User user = new User();
        user.setIcons("f85d233849aa09ee8a44c1d48b7441ba?d=https%3A%2F%2Ficon.amoxuk.com%2Ficon%2Ff85d233849aa09ee8a44c1d48b7441ba.jpg");
        String[] notes = user.getIcons().split("\\?d=");
        for (String s : notes) {
            logger.info(s);
        }
        String[] split = user.getIcons().split("\\?d=");
        user.setEmail("amoxuk@163.com");
        String newIcon = ToolKit.md5Hex(user.getEmail());
        newIcon += "?d=";
        newIcon += split[1];
        user.setIcons(newIcon);
        logger.info(user);
    }
}