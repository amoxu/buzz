package com.amoxu.realm;


import com.amoxu.entity.Permission;
import com.amoxu.entity.User;
import com.amoxu.service.PermissionService;
import com.amoxu.service.UserService;
import com.amoxu.util.ToolKit;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jacey on 2017/6/30.
 */

@Component
public class loginRealm extends AuthorizingRealm {
    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "permissionServiceImpl")
    private PermissionService permissionService;
    /**
     * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     * 当调用权限验证时，就会调用此方法
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Integer uid = (Integer) getAvailablePrincipal(principalCollection);

        Permission permission = permissionService.getUserPermission(uid);
        //通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> r = new HashSet<String>();

        if (permission.getRoles().getName() != null) {
            r.add(permission.getRoles().getName());
            info.setRoles(r);
        }

        return info;

    }

    /**
     * 在这个方法中，进行身份验证
     * login时调用
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //用户名
        String username = (String) token.getPrincipal();
        //密码
        String password = new String((char[]) token.getCredentials());

        password = ToolKit.aesDecrypt(password);
        password = ToolKit.shaDecode(password);


        User userlogin = userService.getLoginUser(username, password);

        if (userlogin == null) {
            //没有该用户名
            throw new UnknownAccountException();
        } else if (!password.equals(userlogin.getPassword())) {
            //密码错误
            throw new IncorrectCredentialsException();
        }
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(userlogin.getUid(), password, getName());
        //身份验证通过,返回一个身份信息

        return aInfo;
    }
}
