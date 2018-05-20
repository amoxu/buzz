package com.amoxu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.amoxu.service.RecommendService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RequestMapping(value = "/recommend")
@Controller
public class RecommendController {

    @Resource(name = "recommendServiceImpl")
    private RecommendService recommendService;

    @RequestMapping(
             method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String getRecommend() {
        return JSON.toJSONString(recommendService.getRecommend(), SerializerFeature.WriteNullListAsEmpty);
    }

}
