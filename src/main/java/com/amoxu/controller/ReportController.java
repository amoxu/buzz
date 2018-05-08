package com.amoxu.controller;

import com.amoxu.service.ReportService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/report")
@Controller
public class ReportController {
    @Resource(name = "reportServiceImpl")
    private ReportService reportService;

    /**
     * type ：
     * 1001 动态
     * 1002 话题
     * 1003 热评
     * 1004 音乐分享
     *
     * */
    @RequestMapping(value = "/{type}/{cid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String report(HttpServletRequest request,
                         @RequestParam("data") String reason,
                         @PathVariable("type") Integer type,
                         @PathVariable("cid") Integer cid) {

        return reportService.report(reason, type, cid, request).toString();
    }
}
