package com.amoxu.mapper;

import com.amoxu.entity.Feature;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        ,"classpath:spring/applicationContext-dao.xml"
        ,"classpath:spring/applicationContext-trsaction.xml"
})
public class FeatureMapperTest {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    FeatureMapper featureMapper;

    @Test
    public void selectByRandom() {
        List<Feature> features = featureMapper.selectByRandom(5);
        logger.info(features.toString());
        Assert.assertNotEquals(features.size(), 0);

    }
}