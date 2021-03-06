package com.amoxu.service;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Message;
import com.amoxu.entity.PageResult;
import org.apache.shiro.subject.Subject;

import java.util.List;

public interface MessageService {
    String sendMsg(int suid, int ruid, String content);


    boolean deleteMsg(Subject subject, Integer mid);


    /*获取对应ID用户收到的消息*/
    AjaxResult<List<Message>> getMessage(PageResult<Message> pageResult, Integer uid);
}
