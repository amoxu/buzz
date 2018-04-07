package com.amoxu.service.impl;

import com.amoxu.entity.User;
import com.amoxu.entity.UserExample;
import com.amoxu.mapper.UserMapper;
import com.amoxu.service.UserService;
import com.amoxu.util.StaticEnum;
import com.amoxu.util.ToolKit;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserMapper mapper;

    @Override
    public int insetUser(User user) {
        user.setCity("北京-西城");
        user.setSex("男");
        user.setPassword(ToolKit.shaDecode(user.getPassword().trim()));
        user.setState(StaticEnum.STATE_ACTIVATED);
        user.setBirth(new Date());
        int code = 0;
        try {
            code = mapper.insert(user);
        } catch (DuplicateKeyException e) {
            logger.error(e.getMessage());
            logger.error(e.getCause());
            Pattern pattern = Pattern.compile("Duplicate entry '(.+)' for key 'user_nickname'");
            if (pattern.matcher(e.getMessage()).find()) {
                logger.warn("用户已存在");
                return StaticEnum.REG_ACCOUNT_ERROR;/*用户已存在*/
            }
            pattern = Pattern.compile("Duplicate entry '(.+)' for key 'user_email'");
            if (pattern.matcher(e.getMessage()).find()) {
                logger.warn("邮箱已存在");
                return StaticEnum.REG_MAIL_ERROR;/*用户已存在*/
            }
            e.printStackTrace();
        }
        if (1 == code) {
            return StaticEnum.REG_SUCCESS;
        } else {
            return StaticEnum.REG_ERROR;
        }

    }

    @Override
    public int updateUser(User user) {
        return mapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> selectUserByName(String name) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameLike(name);
        return mapper.selectByExample(example);
    }

    @Override
    public User selectUserById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public User getLoginUser(String user, String pwd) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameEqualTo(user);
        criteria.andPasswordEqualTo(pwd);
        List<User> users = mapper.selectByExample(example);
        return users.size() > 0 ? users.get(0) : null;
    }
}
