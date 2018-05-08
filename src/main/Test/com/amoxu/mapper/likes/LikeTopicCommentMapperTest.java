package com.amoxu.mapper.likes;

import com.amoxu.entity.ProcCallResult;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        ,"classpath:spring/applicationContext-dao.xml"
        ,"classpath:spring/applicationContext-mail.xml"
        ,"classpath:spring/applicationContext-trsaction.xml"
})
public class LikeTopicCommentMapperTest {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    LikeTopicCommentMapper likeEventsMapper;

    @Test
    public void callLikeProc() {
        int cid = 1;
        int uid = 1;

        ProcCallResult callResult = new ProcCallResult();
        callResult.setCid(cid);
        callResult.setUid(uid);

        likeEventsMapper.callLikeProc(callResult);

        logger.info(callResult.getUid());

    }
}