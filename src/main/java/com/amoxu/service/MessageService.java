package com.amoxu.service;

import com.amoxu.entity.Message;
import com.amoxu.entity.PageResult;
import org.apache.shiro.subject.Subject;

public interface MessageService {
    String sendMsg(int suid, int ruid, String content);


    boolean deleteMsg(Subject subject, Integer mid);

    PageResult<Message> getMessage(PageResult<Message> pageResult, int uid);

}
