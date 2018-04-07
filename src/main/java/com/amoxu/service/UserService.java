package com.amoxu.service;

import com.amoxu.entity.User;

import java.util.List;

public interface UserService {
    int insetUser(User user);

    int updateUser(User user);

    List<User> selectUserLikeName(String name);

    User selectUserByName(String name);
    User selectUserById(Integer id);

    User getLoginUser(String user, String pwd);
}
