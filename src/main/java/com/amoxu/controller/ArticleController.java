package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Article;
import com.amoxu.entity.ArticleComment;
import com.amoxu.entity.PageResult;
import com.amoxu.exception.UnLoginException;
import com.amoxu.service.ArticleService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
    @Resource(name = "articleServiceImpl")
    private ArticleService articleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getArticle(@PathVariable("id") Integer id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("article");
        modelAndView.addObject("article", articleService.getArticle(id));

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf8")
    @ResponseBody
    public String getNotice() {
        AjaxResult<List<Article>> ajaxResult = articleService.getNotice();
        return ajaxResult.toString();
    }

    @RequestMapping(value = "/comment/reply/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf8")
    @ResponseBody
    public String getReplyList(@PathVariable("id") Integer id, PageResult<ArticleComment> pageResult) {
        AjaxResult<List<ArticleComment>> ajaxResult = articleService.getReplyList(id, pageResult);
        return ajaxResult.toString();
    }

    /*回复一条评论或者热评*/
    @RequestMapping(value = "/{bcid}/{rcid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String reply(@PathVariable("rcid") Integer rcid,
                        @PathVariable("bcid") Integer bcid,
                        @RequestParam("data") String data) throws UnLoginException {
        return articleService.reply(rcid, bcid, data).toString();
    }
}
