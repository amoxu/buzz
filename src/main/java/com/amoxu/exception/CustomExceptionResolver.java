package com.amoxu.exception;

import com.amoxu.util.StaticEnum;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * springmvc提供一个HandlerExceptionResolver接口
 * 只要实现该接口，并配置到spring 容器里，该类就能
 * 成为默认全局异常处理类
 * <p>
 * 全局异常处理器只有一个，配置多个也没用。
 */
@Controller
public class CustomExceptionResolver implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {

        ModelAndView modelAndView = new ModelAndView();
        CustomException customException;
        e.printStackTrace();

        if (e instanceof CustomException) {
            customException = (CustomException) e;
        } else if (e instanceof UnknownAccountException) {
            //用户名错误异常
            customException = new CustomException("用户不存在");
            e.printStackTrace();

            /*modelAndView.addObject("message", "{\"status\":1,\"msg\":\"用户不存在\"}");
            modelAndView.setViewName("error");
            return modelAndView;*/
        } else if (e instanceof IncorrectCredentialsException) {
            //用户名密码异常
            customException = new CustomException("密码错误");
            e.printStackTrace();
            /*modelAndView.addObject("message", "{\"status\":1,\"msg\":\"密码错误\"}");
            modelAndView.setViewName("error");
            return modelAndView;*/
        } else if (e instanceof NullPointerException) {
            customException = new CustomException("必填选项不能为空！");
            e.printStackTrace();
        } else if (e instanceof UnauthenticatedException) {
            /*用户未登录*/
            customException = new CustomException(StaticEnum.OPT_UNLOGIN);
            e.printStackTrace();
        } else {
            customException = new CustomException("未知错误");
            e.printStackTrace();
        }

        //错误信息
        String message = customException.getMessage();


        //错误信息传递和错误页面跳转
        modelAndView.addObject("message", message);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
