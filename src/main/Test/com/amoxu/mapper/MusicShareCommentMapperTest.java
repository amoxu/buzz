package com.amoxu.mapper;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.MusicShareComment;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        , "classpath:spring/applicationContext-dao.xml"
        , "classpath:spring/applicationContext-mail.xml"
        , "classpath:spring/applicationContext-trsaction.xml"
})


public class MusicShareCommentMapperTest {

    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    MusicShareMapper mapper;

    @Autowired
    MusicShareCommentMapper commentMapper;


    @Test
    public void selectByPrimaryKey() {
        MusicShareComment shareComment = commentMapper.selectByPrimaryKey(7);

        logger.info(shareComment);

    }

    @Test
    public void selectMainChild() {
        List<MusicShareComment> shareComment = commentMapper.selectMainChild(1, 2);

        logger.info(JSON.toJSONString(shareComment));

    }

}