package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.service.LikeService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LikeController {

    @Resource
    private LikeService likeService;


    @RequestMapping(
            value = "/like/event/{cid}"
            , method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String eventsLike(@PathVariable("cid") Integer cid) {
        AjaxResult<String> ajaxResult = new AjaxResult<>();

        likeService.likeEvents(cid);

        return ajaxResult.toString();
    }
}
