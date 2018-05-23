package com.amoxu.service;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.UserLog;
import com.amoxu.exception.UnLoginException;

public interface RecentService {

    AjaxResult getUserLog(Integer uid, PageResult<UserLog> pageResult) throws UnLoginException;

}
