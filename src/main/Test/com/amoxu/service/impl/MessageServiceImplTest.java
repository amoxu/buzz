package com.amoxu.service.impl;

import com.amoxu.service.MessageService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        ,"classpath:spring/applicationContext-dao.xml"
        ,"classpath:spring/applicationContext-trsaction.xml"
        ,"classpath:spring/applicationContext-mail.xml"
})
public class MessageServiceImplTest {
    private Logger logger = Logger.getLogger(getClass());
    @Resource(name = "messageServiceImpl")
    private MessageService messageService;

    @Test
    public void sendMsg() {
        logger.info(messageService.sendMsg(1,23,"你好啊，欢迎来到乐热评。"));
    }



}