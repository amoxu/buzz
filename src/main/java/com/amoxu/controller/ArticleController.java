package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Article;
import com.amoxu.service.ArticleService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
}
