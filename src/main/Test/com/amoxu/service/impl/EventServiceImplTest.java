package com.amoxu.service.impl;

import com.amoxu.entity.Event;
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
    public void getEvents() {
        PageResult<Event> pageResult = new PageResult<>();
        pageResult.setOffset(0);
        pageResult.setLimit(20);
        long bg = System.currentTimeMillis();
/*
        long ed = System.currentTimeMillis();
        logger.info(ed - bg);
        events = eventService.getEvents(pageResult, 11);
        logger.info(JSON.toJSONString(events));
*/

    }



}