package com.amoxu.service;

import com.amoxu.entity.AjaxResult;
import com.amoxu.exception.UnLoginException;

import javax.servlet.http.HttpServletRequest;

public interface NearbyService {

    /**
     * @param request 用户请求
     * @return 构造完成的用户列表 包括用户id和头像
     * @throws UnLoginException 未登录异常
     */
    AjaxResult nearbyUser(HttpServletRequest request);

}
