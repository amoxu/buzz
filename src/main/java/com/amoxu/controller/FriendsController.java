package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Friends;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.User;
import com.amoxu.service.FriendsService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class FriendsController {
    @Resource(name = "friendsServiceImpl")
    private FriendsService friendsService;


    private Logger logger = Logger.getLogger(getClass());


    @RequestMapping(
            value = {"/friend", "/friend/{id}"}
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String friendsList(
            PageResult<Friends> pageResult
            , @PathVariable(value = "id", required = false) Integer id) {
        return friendsService.getFriends(id, pageResult).toString();
    }

    @RequestMapping(
            value = "/friend/{id}"
            , method = RequestMethod.DELETE
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String cancelRelation(@PathVariable("id") Integer id) {

        AjaxResult<String> ajaxResult = new AjaxResult<>();

        int suid;

        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()) {
            User thisUser = (User) subject.getPrincipal();
            suid = thisUser.getUid();
        } else {
            ajaxResult.failed();
            ajaxResult.setMsg("用户未登录");
            return ajaxResult.toString();
        }

        ajaxResult.ok();
        ajaxResult.setData(friendsService.removeRelation(suid, id));

        return ajaxResult.toString();
    }


}
