package com.amoxu.controller;

import com.amoxu.entity.PageResult;
import com.amoxu.entity.UserLog;
import com.amoxu.exception.UnLoginException;
import com.amoxu.service.RecentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/recent")
public class RecentController {

    @Resource(name = "recentServiceImpl")
    private RecentService recentService;

    @RequestMapping(value = "/{uid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf8")
    @ResponseBody
    public String getRecentLog(@PathVariable("uid") Integer uid, PageResult<UserLog> pageResult) throws UnLoginException {

        return recentService.getUserLog(uid, pageResult).toString();
    }

}
