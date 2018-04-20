package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LikeController {

    @RequestMapping(
            value = "/event/like/{cid}"
            , method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String eventsLike(@PathVariable("cid") String cid) {

        AjaxResult<String> ajaxResult = new AjaxResult<>();

        return ajaxResult.toString();
    }
}
