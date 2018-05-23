package com.amoxu.aop;

import com.amoxu.entity.Event;
import com.amoxu.entity.User;
import com.amoxu.mapper.EventMapper;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by wangH on 2017/12/12.
 */
@Aspect
@Component
public class EventSelectChildAop {

    @Autowired
    private EventMapper eventMapper;

    private Logger logger = Logger.getLogger(getClass());

    @Pointcut("execution(public * com.amoxu.mapper.EventMapper.selectChild(..))")
    public void excudeService() {
    }
    @SuppressWarnings("unchecked")
    @Around(value = "excudeService()")
    public Object AfterExec(ProceedingJoinPoint pjp) {
        Object result = null;
        String methodName = pjp.getSignature().getName();

        try {
            //前置通知
           //System.err.println("The method " + methodName + " begins with " + Arrays.asList(pjp.getArgs()));
            //执行目标方法
            result = pjp.proceed();
            //返回通知
           //System.err.println("The method " + methodName + " ends with " + result);
            List<Event> events = (List<Event>) result;
            if (events != null) {
                for (Event event : events) {
                    User receiveUser = event.getReceiveUser();
                    if (receiveUser == null) {
                        User user = eventMapper.selectBaseUser(event.getBid());
                        event.setReceiveUser(user);
                    }
                }

            }
        } catch (Throwable e) {
            //异常通知
           //System.err.println("The method " + methodName + " occurs exception:" + e);
            throw new RuntimeException(e);
        }
        //后置通知
       //System.err.println("The method " + methodName + " ends");

        return result;

        //Object result = pjp.proceed();

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
    }
}
