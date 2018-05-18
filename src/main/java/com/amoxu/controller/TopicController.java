package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.TopicComment;
import com.amoxu.entity.TopicMap;
import com.amoxu.exception.UnLoginException;
import com.amoxu.service.TopicService;
import com.amoxu.util.StaticEnum;
import com.amoxu.util.ToolKit;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping(value = "/topic")
@Controller
public class TopicController {
    private Logger logger = Logger.getLogger(getClass());

    @Resource(name = "topicServiceImpl")
    private TopicService topicService;




    @RequestMapping(value = "/head", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String headTopic() {
        AjaxResult<List<TopicMap>> ajaxResult = new AjaxResult<>();
        List<TopicMap> list = topicService.getHeadTopic();
        ajaxResult.setData(list);

        return ajaxResult.toString();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String createTopic(@RequestParam(value = "topic") String topic) {

        AjaxResult<String> ajaxResult = new AjaxResult<>();

        String ret = topicService.addTopic(topic);
        if (StaticEnum.OPT_SUCCESS.equals(ret)) {
            ajaxResult.ok();
            ajaxResult.setMsg(StaticEnum.OPT_SUCCESS);
        } else {
            ajaxResult.failed();
            ajaxResult.setMsg(ret);
        }
        return ajaxResult.toString();
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String publishComment(@RequestParam("data") String data) throws UnLoginException {
        logger.info(data);
        logger.info(ToolKit.aesDecrypt(data));
        TopicComment comment = topicService.publishComment(data);
        AjaxResult<TopicComment> ajaxResult = new AjaxResult<>();

        if (comment == null) {
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.WORD_TOPIC_LENGTH);
            return ajaxResult.toString();
        }
        ajaxResult.ok();
        ajaxResult.setData(comment);
        return ajaxResult.toString();
    }
    @RequestMapping(value = "/comment/reply/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String commentReply(PageResult<TopicComment> pageResult, @PathVariable("id") Integer cid) {
        return topicService.commentDetail(cid, pageResult).toString();
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String comment(@PathVariable("id") Integer cid) {
        return topicService.getDetailMain(cid).toString();
    }

    @RequestMapping(value = "/{bcid}/{rcid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String replyComment(@PathVariable("rcid") Integer rcid,
                               @PathVariable("bcid") Integer bcid,
                               @RequestParam("data") String data) {
        return topicService.replyComment(rcid, bcid, data).toString();
    }



    /**
     * type:  hot new rand focus
     *
     * */
    @RequestMapping(value = "/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String hot(PageResult<TopicComment> pageResult, @PathVariable("type") String type) throws UnLoginException {
        AjaxResult<List<TopicComment>> ajaxResult = new AjaxResult<>();
        logger.info(pageResult);
        pageResult = topicService.getMain(type,pageResult);
        ajaxResult.setData(pageResult.getList());
        ajaxResult.setCount(pageResult.getCount());
        ajaxResult.ok();
        return ajaxResult.toString();
    }



}
