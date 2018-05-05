package com.amoxu.service.impl;

import com.amoxu.service.TopicService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"
})
public class TopicServiceImplTest {
    private Logger logger = Logger.getLogger(getClass());

    @Resource(name = "topicServiceImpl")
    private TopicService topicService;

    @Test
    public void getMain() {
    }

    @Test
    public void getHotTopic() {
    }

    @Test
    public void addTopic() {
        String s = topicService.addTopic("乐热评");
        logger.info(s);

    }
}