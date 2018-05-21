package com.amoxu.service;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Friends;
import com.amoxu.entity.PageResult;

import java.util.List;

public interface FriendsService {


    String removeRelation(Integer suid, Integer duid);

    AjaxResult<List<Friends>> getFriends(Integer id, PageResult<Friends> pageResult);
}
