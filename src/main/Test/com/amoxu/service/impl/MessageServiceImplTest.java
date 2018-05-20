package com.amoxu.service.impl;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Message;
import com.amoxu.entity.PageResult;
import com.amoxu.service.MessageService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

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


    @Test
    public void getMessage() {
        PageResult<Message> pageResult = new PageResult<>();
        pageResult.setLimit(10);
        pageResult.setOffset(0);
        AjaxResult<List<Message>> message = messageService.getMessage(pageResult, 1);
        logger.info(JSON.toJSONString(message));
    }
}