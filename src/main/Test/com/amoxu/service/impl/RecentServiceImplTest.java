package com.amoxu.service.impl;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.UserLog;
import com.amoxu.exception.UnLoginException;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class RecentServiceImplTest {
    Logger logger = Logger.getLogger(getClass());
    @Autowired
    private RecentServiceImpl recentService;
    @Test
    public void getUserLog() throws UnLoginException {
        PageResult<UserLog> userLogPageResult = new PageResult<>();
        userLogPageResult.setOffset(0).setLimit(10);
        AjaxResult userLog = recentService.getUserLog(1, userLogPageResult);

        logger.info(userLog);

    }
}