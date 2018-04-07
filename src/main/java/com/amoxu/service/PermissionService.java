package com.amoxu.service;

import com.amoxu.entity.Permission;

import java.util.List;

public interface PermissionService {
    Permission getUserPermission(Integer uid);

    List<Permission> getRoleList(Integer rid);

    List<Permission> getRoleList(String role);

}
