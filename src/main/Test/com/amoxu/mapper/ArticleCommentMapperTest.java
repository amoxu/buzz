package com.amoxu.mapper;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.ArticleComment;
import com.amoxu.entity.ArticleCommentExample;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class ArticleCommentMapperTest {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    ArticleCommentMapper mapper;

    @Test
    public void selectChild() {
        ArticleCommentExample example = new ArticleCommentExample();
        example.setOffset(0);
        example.setLimit(5);

        List<ArticleComment> comments = mapper.selectChild(1, 1, example);
        logger.info(JSON.toJSONString(comments));
    }

    @Test
    public void getReceive() {
    }
}