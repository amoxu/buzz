package com.amoxu.service.impl;

import com.amoxu.entity.Permission;
import com.amoxu.entity.User;
import com.amoxu.mapper.PermissionMapper;
import com.amoxu.service.PermissionService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermissionServiceImpl implements PermissionService {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    PermissionMapper mapper;



    @Override
    public Permission getUserPermission() {
         Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            User user = (User) subject.getPrincipal();
            return mapper.selectByPrimaryKey(user.getUid());
        } else return null;
    }

    @Override
    public int updateUserPermission(Permission permission) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        permission.setUid(user.getUid());
        logger.info(permission);
        return mapper.updateByPrimaryKey(permission);
    }
}
