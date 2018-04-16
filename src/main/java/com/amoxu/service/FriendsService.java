package com.amoxu.service;

import com.amoxu.entity.User;

import java.util.List;

public interface FriendsService {
    List<User> getFriends(Integer id, Integer limit, Integer offset);


    String removeRelation(Integer suid, Integer duid);
}
