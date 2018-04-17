package com.amoxu.service;

import com.amoxu.entity.Friends;
import com.amoxu.entity.PageResult;

public interface FriendsService {


    String removeRelation(Integer suid, Integer duid);

    PageResult<Friends> getFriends(Integer id, PageResult<Friends> pageResult);
}
