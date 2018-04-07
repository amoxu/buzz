package com.amoxu.service.impl;

import com.amoxu.entity.State;
import com.amoxu.service.StateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        ,"classpath:spring/applicationContext-dao.xml"
        ,"classpath:spring/applicationContext-trsaction.xml"
})
public class StateServiceImplTest {
    @Resource(name = "stateServiceImpl")
    private StateService stateService;

    @Test
    public void inserUserState() {
        State state = new State();
        state.setName("已激活");
        int ret = stateService.inserUserState(state);
        Assert.assertEquals(ret, 1);
    }
}