package com.amoxu.service;

import com.amoxu.entity.Permission;

public interface PermissionService {
    Permission getUserPermission();

    int updateUserPermission(Permission permission);
}
