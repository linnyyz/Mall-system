package com.riverbeside.bilibili.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE})  //注解作用范围
@Retention(RetentionPolicy.RUNTIME)  //注解生命周期
public @interface NoRepeatSubmit {

    /**
     * 默认过期时间10秒
     */
    int timeout() default 10;
}
