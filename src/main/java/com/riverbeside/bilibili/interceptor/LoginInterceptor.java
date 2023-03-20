package com.riverbeside.bilibili.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 定义一个拦截器
 */


public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否有uid数据，如果有则放行，如果没有则重定向到登录页面
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  处理器（url+Controller :映射）
     * @return 如果返回值为true则表示放行当前的请求，如果返回值为false则表示拦截当前的请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //HttpServletRequest对象来获取session对象,如果uid中的值为null则表示用户未登录，然后重定向到登录页面
        if (request.getSession().getAttribute("uid") == null) {
            response.sendRedirect("/web/login.html");
            //结束后续调用
            return false;
        }
        //请求放行
        return true;
    }
}
