package com.amoxu.service.impl;

import com.amoxu.entity.Comments;
import com.amoxu.service.CommentService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"
})

public class CommentServiceImplTest {
    @Resource(name = "commentServiceImpl")
    private CommentService commentService;

    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getIndexTopComment() {
        Comments indexTopComment = commentService.getIndexTopComment();
        logger.info(indexTopComment);

    }

    @Test
    public void getIndexLikeComment() {
    }
}