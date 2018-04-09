package com.amoxu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Permission;
import com.amoxu.entity.User;
import com.amoxu.service.PermissionService;
import com.amoxu.service.UserFeatureService;
import com.amoxu.service.UserService;
import com.amoxu.util.StaticEnum;
import com.amoxu.util.ToolKit;
import com.sun.istack.internal.NotNull;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userService;
    @Resource(name = "userFeatureServiceImpl")
    private UserFeatureService userFeatureService;
    @Resource(name = "permissionServiceImpl")
    private PermissionService permissionService;


    private Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = "/user/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String login(@RequestParam("data") String param) {
        logger.info("通过浏览器获取的param：" + param.length());


        logger.info("解密过过后的param：" + param);


        logger.info(ToolKit.aesDecrypt(param));
        String s = ToolKit.aesDecrypt(param);
        logger.info(s);

        JSONObject json = JSON.parseObject(s);
        String username = json.getString("username");
        String password = json.getString("password");
        String captcha = json.getString("captcha");
        /*logger.info(param + " " + username + " " + password + " " + captcha);*/
        AjaxResult ajaxResult = new AjaxResult<String>();

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        @NotNull
        String tempCaptcha = (String) session.getAttribute("captcha");
        try {
            if (tempCaptcha.equals(captcha)) {
                password = ToolKit.aesDecrypt(password);
                logger.info("the password is : " + password);
                password = ToolKit.shaEncode(password);
                logger.info("the sha password is : " + password);

                AuthenticationToken token = new UsernamePasswordToken(username, password);
                subject.login(token);
                ajaxResult.ok();
                ajaxResult.setMsg("登录成功");
            } else {
                ajaxResult.failed();
                ajaxResult.setMsg("验证码错误");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            ajaxResult.failed();
            ajaxResult.setMsg("账号或密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.failed();
            ajaxResult.setMsg("请尝试重新登录");
        }

        return ajaxResult.toString();
    }

    @RequestMapping(value = "/user/reg",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String register(@RequestParam("data") String param) {
        logger.info("通过浏览器获取的param：" + param.length());
        String s = ToolKit.aesDecrypt(param);//
        logger.info(s);

        User user = JSON.parseObject(s, User.class);

        /*logger.info(param + " " + username + " " + password + " " + captcha);*/
        AjaxResult ajaxResult = new AjaxResult<String>();

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        @NotNull
        String tempCaptcha = (String) session.getAttribute("captcha");
        if (tempCaptcha.equals(user.getNote())) {
            user.setNote("");
            user.setPassword(ToolKit.shaEncode(ToolKit.aesDecrypt(user.getPassword())));
            int code = userService.insetUser(user);
            if (StaticEnum.REG_SUCCESS == code) {
                ajaxResult.ok();
                ajaxResult.setMsg("注册成功");
                AuthenticationToken token = new UsernamePasswordToken(user.getNickname(), user.getPassword());
                subject.login(token);
            } else if (StaticEnum.REG_MAIL_ERROR == code) {
                ajaxResult.failed();
                ajaxResult.setMsg("邮箱已存在");
            } else if (StaticEnum.REG_ACCOUNT_ERROR == code) {
                ajaxResult.failed();
                ajaxResult.setMsg("账户已存在");
            } else {
                ajaxResult.failed();
                ajaxResult.setMsg("注册失败");
            }
        } else {
            ajaxResult.failed();
            ajaxResult.setMsg("验证码错误");
        }

        return ajaxResult.toString();
    }

    /*
     * 注册初始化用户兴趣
     *
     * @data 收用户选择的爱好id列表
     *   对id进行封装为List交于service处理
     * @return 操作结果
     *
     *
     * */
    @RequestMapping(value = "/user/reg/feature"
            , method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String setUserFeature(@Param("data") String data) {
        logger.info(data);
        AjaxResult<String> result = new AjaxResult<>();
        JSONObject js = JSON.parseObject(data);
        Iterator<String> iterator = js.keySet().iterator();
        List<Integer> fids = new ArrayList<>();
        while (iterator.hasNext()) {
            fids.add(Integer.valueOf(iterator.next()));
        }
        if (fids.size() == 0 || userFeatureService.createUserFeature(fids) > 0) {
            result.ok();
        } else {
            result.failed();
        }
        return result.toString();
    }

    @RequestMapping(value = "/user/info/{id}"
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String getUserInfo(@PathVariable("id") Integer id) {
        AjaxResult<User> result = new AjaxResult<>();
        User info = userService.getUserInfo(id);
        result.ok();
        result.setData(info);
        return result.toString();
    }

    @RequestMapping(value = "/user/info"
            , method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String setUserInfo(User user) {
        AjaxResult<User> result = new AjaxResult<>();
        int code = userService.updateUser(user);
        result.ok();
        return result.toString();
    }

    @RequestMapping(value = "/user/permission"
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String getUserPermission() {
        AjaxResult<Permission> result = new AjaxResult<>();

        result.setData(permissionService.getUserPermission());
        result.ok();
        return result.toString();
    }


}

