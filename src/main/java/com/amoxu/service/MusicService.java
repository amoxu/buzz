package com.amoxu.service;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.MusicShare;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.TopicComment;
import com.amoxu.exception.UnLoginException;

public interface MusicService {
    AjaxResult<MusicShare> shareMusic(String data) throws UnLoginException;

    PageResult<MusicShare> getMain(String type, PageResult<MusicShare> pageResult) throws UnLoginException;

    AjaxResult replyComment(Integer rcid, Integer bcid, String data);

    AjaxResult getDetailMain(Integer cid);

    AjaxResult commentDetail(Integer cid, PageResult<TopicComment> pageResult);
}
