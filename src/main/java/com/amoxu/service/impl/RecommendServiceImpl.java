package com.amoxu.service.impl;

import com.amoxu.cache.RecommendCacheDao;
import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.BuzzNetease;
import com.amoxu.entity.User;
import com.amoxu.service.RecommendService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    RecommendCacheDao cacheDao;
    @Override

    public AjaxResult getRecommend() {
        AjaxResult<List<BuzzNetease>> ajaxResult = new AjaxResult<>();
        Subject subject = SecurityUtils.getSubject();
        Integer uid;
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
            User principal = (User) subject.getPrincipal();
            uid = principal.getUid();
        } else
            uid = 0;
        List<BuzzNetease> commend = cacheDao.getCommend(uid);
        ajaxResult.ok().setData(commend);

        return ajaxResult;
    }

}
