package com.amoxu.service.impl;

import com.amoxu.entity.*;
import com.amoxu.exception.UnLoginException;
import com.amoxu.mapper.UserLogMapper;
import com.amoxu.service.RecentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class RecentServiceImpl implements RecentService {
    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public AjaxResult getUserLog(Integer uid, PageResult<UserLog> pageResult) throws UnLoginException {
        AjaxResult ajaxResult = new AjaxResult();
        UserLogExample example = new UserLogExample();
        if (uid == 0) {
            Subject subject = SecurityUtils.getSubject();
            try {
                uid = ((User) subject.getPrincipal()).getUid();
            } catch (Exception e) {
                throw new UnLoginException();
            }
        }

        example.or().andUidEqualTo(uid);

        int count = userLogMapper.countByExample(example);
        ajaxResult.setCount(count);

        example.setLimit(pageResult.getLimit());
        example.setOffset(pageResult.getOffset());

        ajaxResult.ok();
        List<UserLog> userLogs = userLogMapper.selectByExample(example);
        ajaxResult.setData(userLogs);

        return ajaxResult;
    }
}
