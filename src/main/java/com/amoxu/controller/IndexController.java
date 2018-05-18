package com.amoxu.controller;

import com.amoxu.service.BuzzService;
import com.amoxu.service.MusicService;
import com.amoxu.service.TopicService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RequestMapping("/index")
@Controller
public class IndexController {

    @Resource(name = "buzzServiceImpl")
    private BuzzService buzzService;
    @Resource(name = "topicServiceImpl")
    private TopicService topicService;


    @Resource(name = "musicServiceImpl")
    private MusicService musicService;

    @RequestMapping(value = "/buzz"
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String getBuzz() {

        return buzzService.index().toString();
    }

    @RequestMapping(value = "/topic"
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String getTopic() {
        return topicService.index().toString();
    }

    @RequestMapping(value = "/music"
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String getMusic() {

        return musicService.index().toString();
    }

    @RequestMapping(value = "/column"
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String getColumn() {

        return topicService.indexColumn().toString();
    }
}
