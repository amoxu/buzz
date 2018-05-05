package com.amoxu.mapper;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.TopicComment;
import com.amoxu.entity.TopicCommentExample;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        ,"classpath:spring/applicationContext-dao.xml"
        ,"classpath:spring/applicationContext-mail.xml"
        ,"classpath:spring/applicationContext-trsaction.xml"
})
public class TopicCommentMapperTest {

    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    TopicCommentMapper mapper;
    @Test
    public void selectMain() {

        TopicCommentExample example = new TopicCommentExample();
        TopicCommentExample.Criteria criteria = example.createCriteria();
        criteria.andTtidIn(Arrays.asList(0, 1, 2));
        example.setLimit(10);
        example.setOffset(0);
        example.setOrderByClause("topic_comment.ctime desc");

        List<TopicComment> topicComments = mapper.selectMain(null, example);

        logger.info(topicComments);
        logger.info(JSON.toJSONString(topicComments));
    }

    @Test
    public void selectChild() {
        TopicCommentExample example = new TopicCommentExample();
        TopicCommentExample.Criteria criteria = example.createCriteria();
        criteria.andBaseCidEqualTo(2);
        example.setLimit(10);
        example.setOffset(0);
        example.setOrderByClause("topic_comment.ctime desc");

        List<TopicComment> topicComments = mapper.selectChild(null, example);

        logger.info(topicComments);
        logger.info(JSON.toJSONString(topicComments));
    }

    @Test
    public void selectMainChild() {


        List<TopicComment> topicComments = mapper.selectMainChild(null, 2);
        logger.info(JSON.toJSONString(topicComments));

    }
}