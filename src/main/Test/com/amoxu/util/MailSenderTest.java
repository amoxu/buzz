package com.amoxu.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext-mail.xml"
})
public class MailSenderTest {
    @Autowired
    private MailSender mailSender;


    @Test
    public void send() {
        mailSender.send("amoxu", "激活 ","http://localhost", "amoxu@qq.com");
        mailSender.send("amoxu", "激活 ","http://localhost", "misty-rain@foxmail.com");
        mailSender.send("amoxu", "激活 ","http://localhost", "852989844@qq.com");
        mailSender.send("amoxu", "激活 ","http://localhost", "bymzgkx@163.com");
        mailSender.send("amoxu", "激活 ", "http://localhost", "amoxuk@163.com");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}