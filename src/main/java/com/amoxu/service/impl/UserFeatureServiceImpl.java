package com.amoxu.service.impl;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.Feature;
import com.amoxu.entity.FeatureExample;
import com.amoxu.entity.User;
import com.amoxu.entity.UserFeature;
import com.amoxu.exception.UnLoginException;
import com.amoxu.mapper.FeatureMapper;
import com.amoxu.mapper.UserFeatureMapper;
import com.amoxu.service.UserFeatureService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserFeatureServiceImpl implements UserFeatureService {
    @Autowired
    private UserFeatureMapper userFeatureMapper;
    @Autowired
    private FeatureMapper featureMapper;


    private Logger logger = Logger.getLogger(UserFeatureServiceImpl.class);


    @Override
    public int createUserFeature(List<Integer> features) throws UnLoginException {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new UnLoginException();
        }
        Integer uid = ((User) subject.getPrincipal()).getUid();
        /*通过list直接转换成数组，调用setUserFeature(Integer uid, Integer... fids)*/
        return setUserFeature(uid, (Integer[]) features.toArray());
    }
    @Override
    public int setUserFeature(Integer uid, Double count, String... features) {
        FeatureExample featureExample = new FeatureExample();
        FeatureExample.Criteria criteria = featureExample.createCriteria();
        List<String> nameList = Arrays.asList(features);
        logger.info(JSON.toJSONString(nameList));
        criteria.andNameIn(nameList);
        /*不重复插入关键词到兴趣关键词表*/
        featureMapper.insertBatch(nameList);
        List<Feature> featureList = featureMapper.selectByExample(featureExample);
        List<Integer> list = new ArrayList<>();
        for (Feature aFeatureList : featureList) {
            list.add(aFeatureList.getFid());
        }
        logger.info(JSON.toJSONString(list));
        userFeatureMapper.insertBatchByReplace(uid, list);
        return userFeatureMapper.updateCountWithFids(uid, count, list);
    }

    @Override
    public int setUserFeature(Integer uid, Integer... fids) {
        List<UserFeature> list = new ArrayList<>();
        UserFeature feature;
        for (int fid : fids) {
            feature = new UserFeature();
            feature.setUid(uid);
            feature.setFid(fid);
            list.add(feature);
        }

        return userFeatureMapper.insertBatch(list);

    }
}
