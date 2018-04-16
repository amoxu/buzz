package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Message;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.User;
import com.amoxu.service.MessageService;
import com.amoxu.util.StaticEnum;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class MessageController {

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;


    private Logger logger = Logger.getLogger(getClass());


    @RequestMapping(
            method = RequestMethod.POST
            , value = "/msg/{id}"
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String sendMsg(@PathVariable("id") Integer ruid, @RequestParam(name = "data") String msg) {
        AjaxResult<String> ajaxResult = new AjaxResult<>();
        int suid;
        try {
            User thisUser = (User) SecurityUtils.getSubject().getPrincipal();
            suid = thisUser.getUid();
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.OPT_UNLOGIN);
            return ajaxResult.toString();
        }

        String s = messageService.sendMsg(suid, ruid, msg);

        if (!s.equals(StaticEnum.OPT_SUCCESS)) {
            ajaxResult.failed();
            ajaxResult.setMsg(s);
        } else {
            ajaxResult.ok();
        }
        return ajaxResult.toString();
    }

    @RequestMapping(
            method = RequestMethod.DELETE
            , value = "/msg/{mid}"
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String deleteMsg(@PathVariable("mid") Integer mid) {
        AjaxResult<String> ajaxResult = new AjaxResult<>();
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.OPT_UNLOGIN);
            return ajaxResult.toString();
        }
        if (messageService.deleteMsg(subject, mid)) {
            ajaxResult.ok();
        } else {
            ajaxResult.failed();
        }

        return ajaxResult.toString();
    }

    @RequestMapping(
            method = RequestMethod.GET
            , value = {"/msg", "/msg/{suid}"}
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String getMsg(PageResult<Message> pageResult, @PathVariable(value = "suid", required = false) Integer suid) {

        AjaxResult<PageResult<Message>> ajaxResult = new AjaxResult<>();
        int uid;
        if (suid != null) {
            uid = suid;
        } else {
            Subject subject = SecurityUtils.getSubject();

            if (!subject.isAuthenticated()) {
                ajaxResult.failed();
                ajaxResult.setMsg(StaticEnum.OPT_UNLOGIN);
                return ajaxResult.toString();
            }

            uid = ((User) subject.getPrincipal()).getUid();
        }



        /*int uid = 1;*/
        logger.info(pageResult);

        ajaxResult.ok();
        ajaxResult.setData(messageService.getMessage(pageResult, uid));
        ajaxResult.setCount(ajaxResult.getData().getCount());
        return ajaxResult.toString();
    }
}
