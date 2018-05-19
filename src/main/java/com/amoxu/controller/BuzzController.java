package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Comments;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.BuzzNetease;
import com.amoxu.exception.UnLoginException;
import com.amoxu.service.BuzzService;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping(value = "/buzz")
@Controller
public class BuzzController {
    private Logger logger = Logger.getLogger(getClass());

    @Resource(name = "buzzServiceImpl")
    private BuzzService buzzService;

    /*喜欢热评*/
    @RequestMapping(value = "/like", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String likeBuzz(@PathVariable("id") Integer bid) {
        return buzzService.likeBuzz(bid).toString();
    }

    /*不喜欢喜欢热评*/
    @RequestMapping(value = "/dislike", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String dislikeBuzz(@PathVariable("id") Integer bid) {
        return buzzService.dislikeBuzz(bid).toString();
    }

    /*获取热评回复列表*/
    @RequestMapping(value = "/comment/reply/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String commentReply(PageResult<Comments> pageResult, @PathVariable("id") Integer cid) {
        return buzzService.commentDetail(cid, pageResult).toString();
    }

    /*获取热评*/
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String comment(@PathVariable("id") Integer cid) {
        return buzzService.getDetailMain(cid).toString();
    }


    /*回复一条评论或者热评*/
    @RequestMapping(value = "/{bcid}/{rcid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String replyComment(@PathVariable("rcid") Integer rcid,
                               @PathVariable("bcid") Integer bcid,
                               @RequestParam("data") String data) {
        return buzzService.replyComment(rcid, bcid, data).toString();
    }


    /*
     * type:  hot new rand focus
     *
     *
     */
    @RequestMapping(value = "/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String buzz(PageResult<BuzzNetease> pageResult, @PathVariable("type") String type) throws UnLoginException {

        AjaxResult<List<BuzzNetease>> ajaxResult = new AjaxResult<>();
        logger.info(pageResult);
        pageResult = buzzService.getMain(type, pageResult);
        ajaxResult.setData(pageResult.getList());
        ajaxResult.setCount(pageResult.getCount());
        ajaxResult.ok();
        return ajaxResult.toString();
    }


}
