package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Friends;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.User;
import com.amoxu.service.FriendsService;
import com.amoxu.util.StaticEnum;
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
import java.util.List;

/**
 * TODO 做一个拦截器，在需要登录的任务的时候检测用户是否登录，统一返回
 */

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

        AjaxResult<List<Friends>> ajaxResult = new AjaxResult<>();



        if (id == null){
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                User thisUser = (User) subject.getPrincipal();
                id = thisUser.getUid();
            } else {
                ajaxResult.failed();
                ajaxResult.setMsg(StaticEnum.OPT_UNLOGIN);
                return ajaxResult.toString();
            }
        }

        pageResult = friendsService.getFriends(id, pageResult);

        ajaxResult.ok();
        ajaxResult.setData(pageResult.getList());
        ajaxResult.setCount(pageResult.getCount());
        return ajaxResult.toString();
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
