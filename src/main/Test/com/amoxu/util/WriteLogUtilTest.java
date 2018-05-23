package com.amoxu.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext-*.xml"
})
public class WriteLogUtilTest {

    @Autowired
    private WriteLogUtil writeLogUtil;

    @Test
    public void writeLog() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0;i<64;i++) {
            stringBuffer.append(i);
        }
        writeLogUtil.writeLog(1, "123456", stringBuffer.toString(), new Date());
    }
}