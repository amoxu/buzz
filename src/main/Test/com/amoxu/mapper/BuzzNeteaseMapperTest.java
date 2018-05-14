package com.amoxu.mapper;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.BuzzNetease;
import com.amoxu.entity.BuzzNeteaseExample;
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
public class BuzzNeteaseMapperTest {

    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    BuzzNeteaseMapper mapper;


    @Test
    public void selectMain() {
        BuzzNeteaseExample buzzNeteaseExample = new BuzzNeteaseExample();
        buzzNeteaseExample.setLimit(10);
        buzzNeteaseExample.setOffset(0);

        List<BuzzNetease> buzzNeteases = mapper.selectTopReply(1, buzzNeteaseExample);
        logger.info(JSON.toJSONString(buzzNeteases));

    }
}