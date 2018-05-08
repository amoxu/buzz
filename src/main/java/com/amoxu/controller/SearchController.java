package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.PageResult;
import com.amoxu.service.SearchService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/search")
public class SearchController {
    @Resource(name = "searchServiceImpl")
    private SearchService searchService;

    @RequestMapping(value = "/{type}/{key}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String search(@PathVariable("type") Integer type, @PathVariable("key") String key, PageResult pageResult) {
        AjaxResult<List> ajaxResult = new AjaxResult<>();
        if (type != 1001) {
            /*当搜索内容为话题时，不需要对offset进行处理*/
            pageResult.setOffset((pageResult.getOffset() - 1) * 10);
        }
        pageResult = searchService.search(type, key, pageResult);
        ajaxResult.setCount(pageResult.getCount());
        ajaxResult.setData(pageResult.getList());
        ajaxResult.ok();
        return ajaxResult.toString();
    }

}
