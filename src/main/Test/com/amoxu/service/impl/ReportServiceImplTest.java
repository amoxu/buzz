package com.amoxu.service.impl;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.AjaxResult;
import com.amoxu.service.ReportService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class ReportServiceImplTest {

    Logger logger = Logger.getLogger(getClass());
    @Resource(name = "reportServiceImpl")
    private ReportService reportService;


    @Test
    public void report() {
        AjaxResult report = reportService.report("111111111111111", 1001, 1, new MockHttpServletRequest());
        logger.info(JSON.toJSONString(report));

    }
}