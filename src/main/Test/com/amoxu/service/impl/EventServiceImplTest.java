package com.amoxu.service.impl;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.Events;
import com.amoxu.entity.PageResult;
import com.amoxu.service.EventService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"
})

public class EventServiceImplTest {
    @Resource(name = "eventServiceImpl")
    private EventService eventService;
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void replyOrPublish() {
        eventService.replyOrPublish(1,null,"这是一条测试数据");
    }

    @Test
    public void getEvents() {
        PageResult<Events> pageResult = new PageResult<>();
        pageResult.setOffset(0);
        pageResult.setLimit(20);
        long bg = System.currentTimeMillis();
        PageResult<Events> events = eventService.getEvents(pageResult);

        logger.info(JSON.toJSONString(events));
/*
        long ed = System.currentTimeMillis();
        logger.info(ed - bg);
        events = eventService.getEvents(pageResult, 11);
        logger.info(JSON.toJSONString(events));
*/

    }
}