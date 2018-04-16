package com.amoxu.service.impl;

import com.amoxu.service.PermissionService;
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
public class PermissionServiceImplTest {
    @Resource(name = "permissionServiceImpl")
    private PermissionService permissionService;
    @Test
    public void getUserPermission() {
        permissionService.getUserPermission();
    }
}