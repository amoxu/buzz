package com.amoxu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.amoxu.entity.BuzzNetease;
import com.amoxu.entity.BuzzNeteaseExample;
import com.amoxu.entity.PageResult;
import com.amoxu.mapper.BuzzNeteaseMapper;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"
})
public class BuzzServiceImplTest {
    @Autowired
    BuzzNeteaseMapper buzzNeteaseMapper;
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getMain() {


        BuzzNeteaseExample buzzExample = new BuzzNeteaseExample();
        BuzzNeteaseExample.Criteria buzzExampleCriteria = buzzExample.createCriteria();


        int count = buzzNeteaseMapper.countByExample(buzzExample);


        Random random = new Random();
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ids.add(random.nextInt(count - 1) + 1);
        }
        buzzExampleCriteria.andIdIn(ids);


        PageResult pageResult = new PageResult();

        buzzExample.setOffset(pageResult.getOffset());
        buzzExample.setLimit(pageResult.getLimit());

        List<BuzzNetease> buzzNeteaseList;

        pageResult.setCount(count);

        buzzNeteaseList = buzzNeteaseMapper.selectMain(null, buzzExample);

        logger.info(JSON.toJSONString(buzzNeteaseList, SerializerFeature.WriteDateUseDateFormat));


    }

    @Test
    public void replyComment() {

    }

    @Test
    public void getDetailMain() {
    }

    @Test
    public void commentDetail() {
    }
}