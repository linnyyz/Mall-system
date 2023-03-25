package com.riverbeside.bilibili.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    /**
     * 默认过期时间3秒
     */
    int timeout() default 3000;
}
