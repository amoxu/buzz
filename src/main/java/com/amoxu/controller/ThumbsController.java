package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.service.ThumbsService;
import com.amoxu.util.StaticEnum;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ThumbsController {

    @Resource(name = "thumbsServiceImpl")
    private ThumbsService thumbsService;

    @RequiresAuthentication()
    @RequestMapping(
            value = "/like/{type}/{cid}"
            , method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String eventsLike(@PathVariable("cid") Integer cid, @PathVariable("type") String type) {
        AjaxResult<String> ajaxResult = new AjaxResult<>();
        boolean liked;
        switch (type) {
            case "event":
                liked = thumbsService.likeEvents(cid);
                break;
            case "comment":
                liked = thumbsService.likeComment(cid);
                break;
            case "share":
                liked = thumbsService.likeShare(cid);
                break;
            case "topic":
                liked = thumbsService.likeTopic(cid);
                break;
            default:
                ajaxResult.failed();
                ajaxResult.setMsg(StaticEnum.OPT_UNLOGIN);
                return ajaxResult.toString();
        }
        ajaxResult.ok();
        if (liked) {
            ajaxResult.setCount(1);

        } else {
            ajaxResult.setCount(0);
        }
        return ajaxResult.toString();
    }

    @RequiresAuthentication
    @RequestMapping(
            value = "/likes/{type}/{cid}"
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String testLogin(@PathVariable("cid") Integer cid, @PathVariable("type") String type) {
        AjaxResult<String> ajaxResult = new AjaxResult<>();
        return ajaxResult.toString();
    }

    @RequestMapping(
            value = "/likess/{type}/{cid}"
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String testUnLogin(@PathVariable("cid") Integer cid, @PathVariable("type") String type) {
        AjaxResult<String> ajaxResult = new AjaxResult<>();
        return ajaxResult.toString();
    }
}
