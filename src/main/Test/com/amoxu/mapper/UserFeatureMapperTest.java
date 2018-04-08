package com.amoxu.mapper;

import com.amoxu.entity.UserFeature;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        , "classpath:spring/applicationContext-dao.xml"
        , "classpath:spring/applicationContext-trsaction.xml"
})
public class UserFeatureMapperTest {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private UserFeatureMapper mapper;

    @Test
    public void insertBatch() {
        List<UserFeature> list = new ArrayList<>();
        UserFeature uf = new UserFeature();
        uf.setFid(100);
        uf.setUid(1);
        uf.setCounts(1.0);
        list.add(uf);
        uf = new UserFeature();
        uf.setFid(101);
        uf.setUid(1);
        uf.setCounts(1.0);
        list.add(uf);
        uf = new UserFeature();
        uf.setFid(1);
        uf.setUid(1);
        uf.setCounts(1.0);
        list.add(uf);
        logger.info(mapper.insertBatch(list));

    }
}