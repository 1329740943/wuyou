package com.demo.wuyou.handler;

import java.lang.annotation.*;

/**
 * 自定义拦截去注解
 * 在需要验证controller上面加此注解
 */
@Target({ElementType.METHOD,ElementType.TYPE})//可用在方法名上
@Retention(RetentionPolicy.RUNTIME)//运行时有效
@Inherited//自动继承
@Documented//注解应该被 javadoc工具记录
public @interface LoginRequired {
    boolean loginSuccess() default true;
}
