package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.MusicShare;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.TopicComment;
import com.amoxu.exception.UnLoginException;
import com.amoxu.service.MusicService;
import com.amoxu.util.StaticEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping(value = "/music")
@Controller
public class MusicController {
    private Logger logger = Logger.getLogger(getClass());

    @Resource(name = "musicServiceImpl")
    private MusicService musicService;


    @RequestMapping(value = "/share", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String share(@RequestParam("data") String data) throws UnLoginException {
        if (StringUtils.isBlank(data)) {
            AjaxResult ajaxResult = new AjaxResult<String>();
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.EMPTY_WORD);
            return ajaxResult.toString();
        }

        return musicService.shareMusic(data).toString();

    }
    @RequestMapping(value = "/comment/reply/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String commentReply(PageResult<TopicComment> pageResult, @PathVariable("id") Integer cid) {
        return musicService.commentDetail(cid, pageResult).toString();
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String comment(@PathVariable("id") Integer cid) {
        return musicService.getDetailMain(cid).toString();
    }


        @RequestMapping(value = "/{bcid}/{rcid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
        @ResponseBody
        public String replyComment(@PathVariable("rcid") Integer rcid,
                                   @PathVariable("bcid") Integer bcid,
                                   @RequestParam("data") String data) {
            return musicService.replyComment(rcid, bcid, data).toString();
        }




    /*
     * type:  hot new rand focus
     *
     *
     */
    @RequestMapping(value = "/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String hot(PageResult<MusicShare> pageResult, @PathVariable("type") String type) throws UnLoginException {

        AjaxResult<List<MusicShare>> ajaxResult = new AjaxResult<>();
        logger.info(pageResult);
        pageResult = musicService.getMain(type, pageResult);
        ajaxResult.setData(pageResult.getList());
        ajaxResult.setCount(pageResult.getCount());
        ajaxResult.ok();
        return ajaxResult.toString();
    }



}
