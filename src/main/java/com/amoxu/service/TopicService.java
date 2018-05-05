package com.amoxu.service;

import com.amoxu.entity.PageResult;
import com.amoxu.entity.TopicComment;
import com.amoxu.entity.TopicMap;
import com.amoxu.exception.UnLoginException;

import java.util.List;

public interface TopicService {
    PageResult<TopicComment> getMain(String type, PageResult<TopicComment> pageResult) throws UnLoginException;

    List<TopicMap> getHotTopic();


    String addTopic(String topic);
}
