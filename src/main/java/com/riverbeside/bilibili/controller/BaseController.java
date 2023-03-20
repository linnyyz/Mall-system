package com.riverbeside.bilibili.controller;

import com.riverbeside.bilibili.controller.ex.*;
import com.riverbeside.bilibili.service.ex.*;
import com.riverbeside.bilibili.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**控制层类的基类*/
public class BaseController {
    /**表示操作成功的状态码*/
    public static final int OK = 200;

    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    //当项目中产生了异常，被统一拦截到该方法中，这个方法此时充当的是请求处理方法，方法的返回值直接给到前端
    @ExceptionHandler({ServiceException.class, FileUploadException.class}) //用于统一处理抛出异常
    public JsonResult<Void> handlerException(Throwable e){
        JsonResult<Void> jsonResult = new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            jsonResult.setState(4000);
            jsonResult.setMessage("用户名被占用");
        }else if(e instanceof UsernameNotFoundException){
            jsonResult.setState(4001);
            jsonResult.setMessage("用户不存在");
        }else if(e instanceof PasswordNotMatchException){
            jsonResult.setState(4002);
            jsonResult.setMessage("用户密码错误");
        }else if(e instanceof AddressCountLimitException){
            jsonResult.setState(4003);
            jsonResult.setMessage("用户的收货地址超出上限的异常");
        }else if(e instanceof AddressNotFoundException){
            jsonResult.setState(4004);
            jsonResult.setMessage("收货地址不存在");
        }else if(e instanceof AccessDeniedException){
            jsonResult.setState(4005);
            jsonResult.setMessage("收货地址非法访问");
        }else if(e instanceof ProductNotFoundException){
            jsonResult.setState(4006);
            jsonResult.setMessage("商品获取失败");
        }else if(e instanceof CartNotFoundException){
            jsonResult.setState(4007);
            jsonResult.setMessage("购物车获取失败");
        }else if(e instanceof InsertException){
            jsonResult.setState(5000);
            jsonResult.setMessage("注册时产生未知的异常");
        }else if(e instanceof FileEmptyException){
            jsonResult.setState(6000);
            jsonResult.setMessage("文件是空文件");
        }else if(e instanceof FileSizeException){
            jsonResult.setState(6001);
            jsonResult.setMessage("文件过大");
        }else if(e instanceof FileStateException){
            jsonResult.setState(6002);
            jsonResult.setMessage("文件状态异常");
        }else if(e instanceof FileUploadIOException){
            jsonResult.setState(6003);
            jsonResult.setMessage("文件上传读写异常");
        }
        return jsonResult;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录用户uid的值
     */
    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的用户名username
     * @param session session对象
     * @return 当前登录用户的用户名
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
