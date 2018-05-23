package com.amoxu.aop;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserLog {
    /**
     * 数据内容
     */
    String content()  default "";

    /**
     * 操作表类型
     */
    String url() default "";

}  