package com.riverbeside.bilibili.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * 切面方法
 * 1.修饰符必须是public
 * 2.切面方法的返回值必须是void或者是Object。如果这个方法被@Around注解修饰此方法必须声明为Object类型，反之随意。
 * 3.切面方法名称可以自定义
 * 4.切面方法可以接受参数，参数是ProccedingJoinPoint接口类型的参数。但是@Around所修饰方法必须要传递这个参数，其他随意.
 */
@Component //将当前类的对象创建使用维护交由Spring容器维护
@Aspect
public class TimerAspect {

    @Around("execution(* com.riverbeside.bilibili.service.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //先记录当前时间
        long start = System.currentTimeMillis();
        Object result = pjp.proceed(); //调用目标方法: login
        System.out.println(result);
        //后调用当前时间
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start));
        return result;
    }


}
