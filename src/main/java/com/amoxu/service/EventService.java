package com.amoxu.service;

import com.amoxu.entity.Events;
import com.amoxu.entity.PageResult;

public interface EventService {
    Events replyOrPublish(int uid, Integer rcid, String msg);

    PageResult<Events> getEvents(PageResult<Events> pageResult, Integer commentId);

    PageResult<Events> getEvents(PageResult<Events> pageResult);
}
