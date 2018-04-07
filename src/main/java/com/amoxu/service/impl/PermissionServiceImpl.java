package com.amoxu.service.impl;

import com.amoxu.entity.Permission;
import com.amoxu.mapper.PermissionMapper;
import com.amoxu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public Permission getUserPermission(Integer uid) {
        return permissionMapper.selectByPrimaryKey(uid);
    }

    @Override
    public List<Permission> getRoleList(Integer rid) {
        return null;
    }

    @Override
    public List<Permission> getRoleList(String role) {
        return null;
    }
}
