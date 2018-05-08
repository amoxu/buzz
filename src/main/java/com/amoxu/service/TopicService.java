package com.amoxu.service;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.TopicComment;
import com.amoxu.entity.TopicMap;
import com.amoxu.exception.UnLoginException;

import java.util.List;

public interface TopicService {
    PageResult<TopicComment> getMain(String type, PageResult<TopicComment> pageResult) throws UnLoginException;

    List<TopicMap> getHotTopic();


    String addTopic(String topic);

    TopicComment publishComment(String data) throws UnLoginException;

    AjaxResult<TopicComment> replyComment(Integer rcid, Integer bcid, String data);

    AjaxResult commentDetail(Integer cid, PageResult<TopicComment> pageResult);
}
