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

    User getUserInfo(Integer uid);

    int updateUserPassword(String oldPasswor, String newPasswor);

    int sendMail2NewNail(String mail);

    String activeUserMail(String id, String key);

    String findPassword(String email);

    String findPassword(String email, String verNote, String password);

}
