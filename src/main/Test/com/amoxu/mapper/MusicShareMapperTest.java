package com.amoxu.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.amoxu.entity.MusicShare;
import com.amoxu.entity.MusicShareComment;
import com.amoxu.entity.MusicShareCommentExample;
import com.amoxu.entity.MusicShareExample;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        ,"classpath:spring/applicationContext-dao.xml"
        ,"classpath:spring/applicationContext-mail.xml"
        ,"classpath:spring/applicationContext-trsaction.xml"
})

public class MusicShareMapperTest {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    MusicShareMapper mapper;

    @Autowired
    MusicShareCommentMapper commentMapper;


    @Test
    public void selectPK() {
        MusicShare musicShare = mapper.selectByPrimaryKey(10);
        logger.info(JSON.toJSONString(musicShare));
    }
    @Test
    public void selectConmentPK() {
        MusicShareComment musicShare = commentMapper.selectByPrimaryKey(10);
        logger.info(JSON.toJSONString(musicShare));
    }
    @Test
    public void selectMain() {
        MusicShareExample musicShareExample = new MusicShareExample();
        musicShareExample.setOffset(0);

        musicShareExample.setLimit(10);
        musicShareExample.setOrderByClause("ctime desc");

        List<MusicShare> musicShares = mapper.selectMain(1, musicShareExample);


        logger.info(JSON.toJSONString(musicShares, SerializerFeature.DisableCircularReferenceDetect));

        /*
        List<MusicShareComment> musicShareComments = musicShareCommentMapper.selectMainChild(null, 1);

        logger.info(JSON.toJSONString(musicShareComments));*/

    }

    @Test
    public void selectMainChild() {
    }

    @Test
    public void selectChild() {
        MusicShareCommentExample musicShareExample = new MusicShareCommentExample();
        musicShareExample.setOffset(0);

        musicShareExample.setLimit(10);
        musicShareExample.setOrderByClause("ctime desc");
        List<MusicShareComment> musicShareComments = commentMapper.selectChild(1, 1, musicShareExample);
        logger.info(JSON.toJSONString(musicShareComments));


    }
}