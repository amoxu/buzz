package com.amoxu.service.impl;

import com.amoxu.entity.User;
import com.amoxu.entity.UserFeature;
import com.amoxu.mapper.UserFeatureMapper;
import com.amoxu.service.UserFeatureService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFeatureServiceImpl implements UserFeatureService {
    @Autowired

    private UserFeatureMapper userFeatureMapper;

    @Override
    public int createUserFeature(List<Integer> features) {
        Subject subject = SecurityUtils.getSubject();
        Integer uid = ((User) subject.getPrincipal()).getUid();
        List<UserFeature> list = new ArrayList<>();
        UserFeature userFeature;
        for (Integer fid : features) {
            userFeature = new UserFeature();
            userFeature.setFid(fid);
            userFeature.setUid(uid);
            userFeature.setCounts(1.0);
            list.add(userFeature);
        }
        return userFeatureMapper.insertBatch(list);
    }
}
