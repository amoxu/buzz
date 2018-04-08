package com.amoxu.service.impl;

import com.amoxu.entity.Feature;
import com.amoxu.mapper.FeatureMapper;
import com.amoxu.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeatureServiceImpl implements FeatureService {
    @Autowired
    private FeatureMapper mapper;

    @Override
    public List<Feature> getRegFeatures() {
        return mapper.selectByRandom(5);
    }
}
