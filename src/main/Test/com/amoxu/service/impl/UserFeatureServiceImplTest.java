package com.amoxu.service.impl;

import com.amoxu.service.UserFeatureService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class UserFeatureServiceImplTest {
    final Logger logger = Logger.getLogger(getClass());
    @Resource(name = "userFeatureServiceImpl")
    UserFeatureService userFeatureService;

    @Test
    public void createUserFeature() {
        List<Integer> ls = new ArrayList<>();
        ls.add(1);
        ls.add(1001);

    }
    @Test
    public void setUserFeature() {
        String[] strings = "阿杜/成就/当时/不然的话/火".split("/");

        int i = userFeatureService.setUserFeature(1, 1.0, strings);
        logger.info(i);
    }
}