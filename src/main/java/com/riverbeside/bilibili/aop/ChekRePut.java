package com.riverbeside.bilibili.aop;


import com.riverbeside.bilibili.annotation.NoRepeatSubmit;
import com.riverbeside.bilibili.service.ex.InsertException;
import com.riverbeside.bilibili.util.JsonResult;
import com.riverbeside.bilibili.util.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class ChekRePut {

    @Autowired
    RedisUtils redisUtils;

    /**
     * 切面类中定义增强
     */
    @Around("@annotation(noRepeatSubmit)")
    public Object reSubmit(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) throws Throwable {
        //获取注解
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        //获取类，方法
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();

        //组装key：用户唯一标识+操作类和方法
        String key = "用户token#" + className + "#" + methodName;
        int keyHashCode = Math.abs(key.hashCode());


        //获取超时时间
        int timeOut = noRepeatSubmit.timeout();
        long count = redisUtils.getRePut("rePut", Integer.toString(keyHashCode));
        redisUtils.setHash("rePut", Integer.toString(keyHashCode), "1", timeOut);

        if (count > 1) {
            throw new InsertException("请勿重复提交");
        }
        return pjp.proceed();
    }
}
