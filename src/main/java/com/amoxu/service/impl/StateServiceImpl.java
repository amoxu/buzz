package com.amoxu.service.impl;

import com.amoxu.entity.State;
import com.amoxu.mapper.StateMapper;
import com.amoxu.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl implements StateService {
    @Autowired
    private StateMapper stateMapper;

    @Override
    public int inserUserState(State state) {
        return stateMapper.insert(state);
    }
}
