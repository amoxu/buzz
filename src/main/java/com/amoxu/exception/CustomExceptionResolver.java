package com.amoxu.exception;

import com.amoxu.util.StaticEnum;
import org.apache.log4j.Logger;
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

    private final Logger logger = Logger.getLogger(getClass());

    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {

        ModelAndView modelAndView = new ModelAndView();
        CustomException customException;
        logger.error("Custom Exception: ",e);
        if (e instanceof CustomException) {
            customException = (CustomException) e;
        } else if (e instanceof UnknownAccountException) {
            //用户名错误异常
            customException = new CustomException("用户不存在");

            /*modelAndView.addObject("message", "{\"status\":1,\"msg\":\"用户不存在\"}");
            modelAndView.setViewName("error");
            return modelAndView;*/
        } else if (e instanceof IncorrectCredentialsException) {
            //用户名密码异常
            customException = new CustomException("密码错误");
            logger.error("Exception: ",e);
            /*modelAndView.addObject("message", "{\"status\":1,\"msg\":\"密码错误\"}");
            modelAndView.setViewName("error");
            return modelAndView;*/
        } else if (e instanceof NullPointerException) {
            customException = new CustomException("必填选项不能为空！");
            logger.error("Exception: ",e);
        } else if (e instanceof UnauthenticatedException) {
            /*用户未登录*/
            customException = new CustomException(StaticEnum.OPT_UNLOGIN);
            logger.error("Exception: ",e);
        } else if (e instanceof UnLoginException) {
            customException = new CustomException(e.getMessage());
        } else {
            customException = new CustomException("未知错误");
            logger.error("Exception: ",e);
        }

        //错误信息
        String message = customException.getMessage();


        //错误信息传递和错误页面跳转
        modelAndView.addObject("message", message);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
