package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Event;
import com.amoxu.entity.PageResult;
import com.amoxu.exception.UnLoginException;
import com.amoxu.service.EventService;
import com.amoxu.util.StaticEnum;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping(value = "/event")
@Controller
public class EventController {
    @Resource(name = "eventServiceImpl")
    private EventService eventService;

    private Logger logger = Logger.getLogger(getClass());



    @RequestMapping( method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String publishComment(@RequestParam("data") String data) throws UnLoginException {
        Event comment = eventService.publishComment(data);
        AjaxResult<Event> ajaxResult = new AjaxResult<>();

        if (comment == null) {
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.WORD_CONTENT_LENGTH);
            return ajaxResult.toString();
        }
        ajaxResult.ok();
        ajaxResult.setData(comment);
        return ajaxResult.toString();
    }
    @RequestMapping(value = "/comment/reply/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String commentReply(PageResult<Event> pageResult, @PathVariable("id") Integer cid) {
        return eventService.commentDetail(cid, pageResult).toString();
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String comment(@PathVariable("id") Integer cid) {
        return eventService.getDetailMain(cid).toString();
    }

    @RequestMapping(value = "/{bcid}/{rcid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String replyComment(@PathVariable("rcid") Integer rcid,
                               @PathVariable("bcid") Integer bcid,
                               @RequestParam("data") String data) {
        return eventService.replyComment(rcid, bcid, data).toString();
    }



    /**
     * type:  hot new rand focus
     *
     * */
    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String hot(PageResult<Event> pageResult) throws UnLoginException {
        AjaxResult<List<Event>> ajaxResult = new AjaxResult<>();
        logger.info(pageResult);
        String type = "new";
        pageResult = eventService.getMain(type,pageResult);
        ajaxResult.setData(pageResult.getList());
        ajaxResult.setCount(pageResult.getCount());
        ajaxResult.ok();
        return ajaxResult.toString();
    }



}
