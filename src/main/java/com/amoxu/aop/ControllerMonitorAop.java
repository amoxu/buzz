package com.amoxu.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by wangH on 2017/12/12.
 */
@Aspect
@Component
public class ControllerMonitorAop {

    private Logger logger = Logger.getLogger(getClass());
/*
    @Pointcut("!execution(public * com.amoxu.mapper..insert*())")
    public void excudeService() {
    }

    @AfterReturning(value = "excudeService()", returning = "result")
    public void AfterExec(JoinPoint pjp, Object result) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        logger.info(result);
        String ipAddr = NetworkUtil.getIpAddr(request);
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String params = "";
        if ("POST".equals(method)) {
            Object[] paramsArray = pjp.getArgs();
            params = NetworkUtil.argsArrayToString(paramsArray);
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            params = paramsMap.toString();
        }
        logger.info("request begin=>ipAddr: " + ipAddr +
                ", uri: " + uri +
                ", method: " + method +
                ", url: " + url +
                ", params: " + params);

        //Object result = pjp.proceed();
        String serviceMthodDescription = getServiceMthodDescription(pjp);

        //logger.info(result.toString());
        // return result;

    }

    private String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(UserLog.class).content();
                    break;
                }
            }
        }
        return description;
    }*/
}
