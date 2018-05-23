package com.amoxu.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext-mail.xml"
})
public class MailSenderTest {
    @Autowired
    private MailSender mailSender;


    @Test
    public void send() {
        /*
        mailSender.send("amoxu", "激活 ","http://localhost", "amoxu@qq.com");
        mailSender.send("amoxu", "激活 ","http://localhost", "misty-rain@foxmail.com");
        mailSender.send("amoxu", "激活 ","http://localhost", "852989844@qq.com");
        mailSender.send("amoxu", "激活 ","http://localhost", "bymzgkx@163.com");
        mailSender.send("amoxu", "激活 ", "http://localhost", "amoxuk@163.com");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            logger.error("Exception: ",e);
        }
        */



        String content = "在我心中难得一首情人节的甜歌，终于还是阿杜唱了出来。不知为什么，那天电台放了这首歌就突然觉得心情好好。心里幻想和你的那一天。也许我们能到那一天，也许以后每天都能和你过情人节。我想许个愿望，不管它能不能实现。珍惜和你在一起的每一天，即使是最平凡普通的一天。";

        List<Term> segment = StandardTokenizer.segment(content);
        System.out.println(segment);

        List<String> keywordList = HanLP.extractKeyword(content, 5);

        System.err.println(keywordList);
    }
}