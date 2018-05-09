package com.amoxu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Permission;
import com.amoxu.entity.User;
import com.amoxu.exception.UnLoginException;
import com.amoxu.service.PermissionService;
import com.amoxu.service.UserFeatureService;
import com.amoxu.service.UserService;
import com.amoxu.util.StaticEnum;
import com.amoxu.util.ToolKit;
import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    @RequestMapping(value = "/logout",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String logout() {
        AjaxResult<String> ajaxResult = new AjaxResult<>();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        ajaxResult.ok();
        return ajaxResult.toString();
    }

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

        if (Captcha.checkCaptchaEqu(user.getNote())) {
            user.setNote("");
            user.setPassword(ToolKit.shaEncode(ToolKit.aesDecrypt(user.getPassword())));
            int code = userService.insetUser(user);
            if (StaticEnum.REG_SUCCESS == code) {

                AuthenticationToken token = new UsernamePasswordToken(user.getNickname(), user.getPassword());
                subject.login(token);

                ajaxResult.ok();
                ajaxResult.setMsg("注册成功");
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
    public String setUserFeature(@RequestParam("data") String data) {
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
    public String getUserInfo(@PathVariable("id") Integer id) throws UnLoginException {
        AjaxResult<User> result = new AjaxResult<>();
        User info = userService.getUserInfo(id);
        if (info == null) {
            result.failed();
            result.setMsg(StaticEnum.OPT_UNLOGIN);
            return result.toString();
        }

        result.ok();
        /**
         * 设置返回数据隐私内容不显示
         *
         * ======================
         */
        info.setPassword(null);
        info.setUserState(null);
        info.setCtime(null);
        info.setNote(null);
        info.setRoles(null);
        info.setRid(null);
        info.setState(null);
        /**
         * =====================
         * */
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
        Permission userPermission = permissionService.getUserPermission();
        if (null == userPermission) {
            result.failed();
            return result.toString();
        }

        result.ok();
        result.setData(userPermission);
        return result.toString();
    }

    @RequestMapping(value = "/user/permission"
            , method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    public String setUserPermission(Permission permission) {
        AjaxResult<Permission> result = new AjaxResult<>();
        if (0 < permissionService.updateUserPermission(permission)) {
            result.ok();
        } else {
            result.failed();
        }
        return result.toString();
    }


    @RequestMapping(value = "/user/mail"
            , method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    /**
     * @descript 修改邮箱
     *
     * @param data 邮箱地址
     *
     *
     * */
    public String alterMail(@RequestParam("data") String mail) {
        AjaxResult<String> result = new AjaxResult<>();
        logger.info(mail);
        int code = userService.sendMail2NewNail(mail);
        if (code != 0) {
            result.failed();
        } else {
            result.ok();
        }
        return result.toString();
    }

    /**
     * @param id  用户id
     * @RequestParam key note 信息
     * @descript 修改邮箱
     */
    @RequestMapping(value = "/user/active"
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )

    public void activeMail(@RequestParam("id") String id,
                           @RequestParam("key") String key,
                           HttpServletRequest request,
                           HttpServletResponse response
    ) throws IOException {
        logger.info(id+" ,key: " + key);
        id = id.replace(" ", "+");
        String code = userService.activeUserMail(id, key);
        String html = "<br><h3><a href = '" + request.getScheme() + "://"
                + request.getServerName()
                + ":" + request.getServerPort()
                + "'>点击返回主页</a></h3>";
        /**
         * 直接用response的writer无法输出中文字符
         * Exception java.io.CharConversionException: Not an ISO 8859-1 character
         * 用PrintWriter同样无法输出html
         * */
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(code + html);
    }


    @RequestMapping(value = "/user/icon"
            , method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    /**
     * @descript 修改用户头像
     *
     * @RequestParam url 头像的文件名
     *
     *
     * */
    public String alterIcon(@RequestParam("data") String url) {
        AjaxResult<String> result = new AjaxResult<>();
        logger.info(url);
        if (StringUtils.isEmpty(url)) {
            result.setMsg("操作失败");
            result.failed();
            return result.toString();
        }
        int code = userService.updataIcon(url);
        if (code == 0) {
            result.setMsg("操作失败");
            result.failed();
        } else {
            result.ok();
        }
        return result.toString();
    }

    @RequestMapping(value = "/user/icon/{id}"
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
    )
    @ResponseBody
    /**
     * @descript 获取用户头像
     *
     * @RequestParam id 用户ID
     *
     *
     * */
    public String getUserIcon(@PathVariable("id") Integer id) {
        AjaxResult<String> result = new AjaxResult<>();
        logger.info(id);
        String url = userService.findUserIcon(id);

        result.ok();
        result.setData(url);

        return result.toString();
    }
    @Controller
    public class PasswordController {
        @RequestMapping(value = "/user/password"
                , method = RequestMethod.POST
                , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
        )
        @ResponseBody
        /**
         * @descript 修改密码
         *
         * @param data 经过两层aes加密的新密码和旧密码
         *
         *
         * */
        public String alterPassword(@RequestParam("data") String data) {
            AjaxResult<Permission> result = new AjaxResult<>();

            logger.info(data);
            data = ToolKit.aesDecrypt(data);
            JSONObject obj = JSON.parseObject(data);
            logger.info("obj: " + obj);
            int code = userService.updateUserPassword(obj.getString("oldPassword"), obj.getString("newPassword"));
            if (code == 0) {
                result.setMsg("密码错误");
                result.failed();
            } else {
                result.ok();
            }
            return result.toString();
        }

        @RequestMapping(value = "/user/password/{step}"
                , method = RequestMethod.POST
                , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
        )
        @ResponseBody
        /**
         * @descript 修改密码
         *
         * @RequestParam data 经过两层aes加密的新密码和旧密码
         *
         *
         * */
        public String findPassword(@RequestParam("data") String data, @PathVariable("step") Integer step) {
            AjaxResult<String> result = new AjaxResult<>();
            logger.info(data);
            data = ToolKit.aesDecrypt(data);
            JSONObject obj = JSON.parseObject(data);
            logger.info("obj: " + obj);

            if (Captcha.checkCaptchaEqu(obj.getString("note"))) {
                result.failed();
                result.setMsg("验证码错误");
                return result.toString();
            }

            if (step == 1) {
                String msg = userService.findPassword(obj.getString("email"));
                if (StaticEnum.OPT_SUCCESS.equals(msg)) {
                    result.ok();
                    result.setMsg("请前往邮箱获取验证编码");
                } else {
                    result.failed();
                    result.setMsg(msg);
                }
            } else if (step == 2) {
                /**
                 * {"note":"xjrt","password":"lRZFV1Xw+l+ZcvTzTyWTxQ==","nickname":"12345","email":"amoxu@qq.com"}
                 * */
                String msg = userService.findPassword(obj.getString("email"), obj.getString("nickname"), obj.getString("password"));
                if (StaticEnum.OPT_SUCCESS.equals(msg)) {
                    result.ok();
                } else {
                    result.failed();
                    result.setMsg(msg);
                }
            } else {
                result.setMsg("请重新输入验证码");
                result.failed();
            }


            return result.toString();
        }

    }

}


