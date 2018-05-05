package com.amoxu.service.impl;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.PageResult;
import com.amoxu.service.SearchService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class SearchServiceImplTest {
    Logger logger = Logger.getLogger(getClass());
    @Resource(name = "searchServiceImpl")
    private SearchService searchService;

    @Test
    public void search() {
        PageResult pageResult = new PageResult();
        pageResult.setOffset(0);
        pageResult.setLimit(10);

        PageResult pageResult1 = searchService.search(1001, "乐热评", pageResult);
        logger.info(JSON.toJSONString(pageResult1));

    }
}