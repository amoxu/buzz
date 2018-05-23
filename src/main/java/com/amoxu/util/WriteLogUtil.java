package com.amoxu.util;

import com.amoxu.entity.UserLog;
import com.amoxu.mapper.UserLogMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WriteLogUtil {
    @Autowired
    private UserLogMapper userLogMapper;

    public boolean writeLog(Integer uid, String url, String content) {

        if (StringUtils.isBlank(content)) {
            return false;
        }

        if (content.length() > 64) {
            content = content.substring(0, 63);
        }
        UserLog userLog = new UserLog();
        userLog.setUid(uid);
        userLog.setUrl(url);
        userLog.setContent(content);
        writerLog(userLog);
        return true;
    }

    public boolean writeLog(Integer uid, String url, String content, Date date) {
        if (StringUtils.isBlank(content)) {
            return false;
        }

        if (content.length() > 64) {
            content = content.substring(0, 64);
        }
        UserLog userLog = new UserLog();
        userLog.setUid(uid);
        userLog.setUrl(url);
        userLog.setContent(content);
        userLog.setCtime(date);
        writerLog(userLog);
        return true;
    }


    private void writerLog(UserLog userLog) {
        userLogMapper.insert(userLog);
    }
}

