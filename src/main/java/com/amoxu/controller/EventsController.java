package com.amoxu.controller;

import com.amoxu.entity.*;
import com.amoxu.service.EventService;
import com.amoxu.service.MessageService;
import com.amoxu.util.StaticEnum;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class EventsController {
    @Resource(name = "eventServiceImpl")
    private EventService eventService;

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;


    private Logger logger = Logger.getLogger(getClass());


    @RequestMapping(
            method = RequestMethod.POST
            , value = {"/event/{rcid}", "/event"}
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String replyOrPublish(@PathVariable(required = false, value = "rcid") Integer rcid, @RequestParam(name = "data") String msg) {

        AjaxResult<Events> ajaxResult = new AjaxResult<>();
        int uid;
        try {
            User thisUser = (User) SecurityUtils.getSubject().getPrincipal();
            uid = thisUser.getUid();
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.OPT_UNLOGIN);
            return ajaxResult.toString();
        }
        Events events = eventService.replyOrPublish(uid, rcid, msg);
        if (events != null) {
            ajaxResult.ok();
            ajaxResult.setData(events);
        } else {
            ajaxResult.failed();
        }
        return ajaxResult.toString();
    }

    @RequestMapping(
            method = RequestMethod.DELETE
            , value = "/event/{rid}"
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String delete(@PathVariable("rid") Integer rid) {
        AjaxResult<String> ajaxResult = new AjaxResult<>();
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.OPT_UNLOGIN);
            return ajaxResult.toString();
        }
        if (messageService.deleteMsg(subject, rid)) {
            ajaxResult.ok();
        } else {
            ajaxResult.failed();
        }

        return ajaxResult.toString();
    }

    @RequestMapping(
            method = RequestMethod.GET
            , value = "/event"
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String getEvents(PageResult<Events> pageResult) {
        AjaxResult<List<Events>> ajaxResult = new AjaxResult<>();
        /*int uid = 1;*/
        logger.info(pageResult);
        pageResult = eventService.getEvents(pageResult);
        ajaxResult.setData(pageResult.getList());
        ajaxResult.setCount(pageResult.getCount());
        ajaxResult.ok();
        return ajaxResult.toString();
    }
    @RequestMapping(
            method = RequestMethod.GET
            , value = "/event/{commentId}"
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String loadReply(PageResult<Events> pageResult, @PathVariable(value = "commentId") Integer commentId) {
        AjaxResult<List<Events>> ajaxResult = new AjaxResult<>();
        /*int uid = 1;*/
        logger.info(pageResult);

        pageResult = eventService.getEvents(pageResult, commentId);

        ajaxResult.setData(pageResult.getList());
        ajaxResult.setCount(pageResult.getCount());
        ajaxResult.ok();
        return ajaxResult.toString();
    }

}
