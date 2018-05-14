package com.amoxu.service;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Comments;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.BuzzNetease;
import com.amoxu.exception.UnLoginException;

public interface BuzzService {
    AjaxResult getDetailMain(Integer cid);

    AjaxResult replyComment(Integer rcid, Integer bcid, String data);

    AjaxResult commentDetail(Integer cid, PageResult<Comments> pageResult);

    PageResult<BuzzNetease> getMain(String type, PageResult<BuzzNetease> pageResult) throws UnLoginException;

}
