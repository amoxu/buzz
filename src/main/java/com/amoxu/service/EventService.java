package com.amoxu.service;

import com.amoxu.entity.*;
import com.amoxu.exception.UnLoginException;

import java.util.List;

public interface EventService {

    AjaxResult commentDetail(Integer cid, PageResult<Event> pageResult);


    AjaxResult replyComment(Integer rcid, Integer bcid, String data);

    Event publishComment(String data) throws UnLoginException;

    AjaxResult<List<Event>> getDetailMain(Integer... cid);

    PageResult<Event> getMain(String type, PageResult<Event> pageResult) throws UnLoginException;


}
