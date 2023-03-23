package com.riverbeside.bilibili.controller;


import com.riverbeside.bilibili.controller.ex.*;
import com.riverbeside.bilibili.entity.User;
import com.riverbeside.bilibili.service.IUserService;
import com.riverbeside.bilibili.service.ex.InsertException;
import com.riverbeside.bilibili.service.ex.UsernameDuplicatedException;
import com.riverbeside.bilibili.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//@Controller
@RestController //@Controller+@ResponseBody
@RequestMapping("users")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    /**
     * 约定大于配置的开发思想省略掉了大量的数字
     * 1.接收数据方式：请求处理方法的参数列表设置为pojo类型来接受前端的数据
     * Springboot会将前端的url的参数名和pojo中的属性名和pojo类中的属性名进行比较，如果这两个项目名称
     * 相同，则将值注入到pojo类中对应的属性上
     */
    @RequestMapping("reg")
    //@ResponseBody //表示此方法的响应结果以json的格式进行数据的响应给到前端
    public JsonResult<Void> reg(User user){
//        //创建响应结果对象
//        JsonResult<Void> result = new JsonResult<>();
//        result.setState(200);
//        result.setMessage("用户注册成功");
//        try {
//            userService.reg(user);
//        } catch (UsernameDuplicatedException e) {
//            result.setState(4000);
//            result.setMessage("用户名被占用");
//        }catch (InsertException e) {
//            result.setState(5000);
//            result.setMessage("在注册时产生未知的异常");
//        }
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    /**
     * 2.接收数据方式：请求处理方法的参数列表设置为非pojo类型
     * springboot直接将请求的参数名和方法的参数名进行比较，如果名称相同则自动完成值的依赖注入
     *
     *
     */

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){


        User user = userService.login(username,password);
        //向session对象中完成数据的绑定
        session.setAttribute("username",user.getUsername());
        session.setAttribute("uid",user.getUid());

        //获取session中绑定的数据
        getUidFromSession(session);
        getUsernameFromSession(session);

        return new JsonResult<User>(OK,user);
    }

    @RequestMapping("changePassword")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){

        Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
        String username = session.getAttribute("username").toString();
        userService.updatePasswordByUid(uid,oldPassword,newPassword,username,new Date());
        return new JsonResult<>(OK);
    }

    @RequestMapping(value = "getByUid")
    public JsonResult<User> getByUid(HttpSession session){

        User user = userService.getByUid(getUidFromSession(session));
        return new JsonResult<>(OK,user);
    }

    @RequestMapping("changeInfo")
    public JsonResult<Void> updateInfo(HttpSession session,User user){

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.updateInform(uid,username,user);
        return new JsonResult<>(OK);
    }

    public static final Integer AVATAR_MAX_SIZE=10*1024*1024;
    /**限制文件上传的类型*/
    public static final List<String> AVATAR_TYPE=new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
        AVATAR_TYPE.add("image/png");
    }
    /**
     * MultipartFile接口是springmvc提供的接口，这个接口为我们包装了
     * 获取文件类型的数据（任何类型的file都可以接受）,springboot又整合了springmvc
     * 只需要在处理请求方法的参数列表上声明一个参数类型为MultipartFile这样一个参数，
     * springboot会自动将文件中的数据赋值给这个参数
     * @param session
     * @param file
     * @return
     */
    @RequestMapping("uploadAvatar")
    public JsonResult<String> uploadAvatar(HttpSession session, MultipartFile file){
        //判断是否为空
        if (file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        //判断文件是否过大
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }

        //判断文件类型是否是我们规定的类型
        String fileType = file.getContentType();
        //如果集合包含某个元素则返回true 反之则返回false
        if (!AVATAR_TYPE.contains(fileType)){
            throw new FileTypeException("文件类型错误");
        }
        //文件夹上传的文件目录结构  ../upload/文件.png

        String parent = session.getServletContext().getRealPath("upload");

        //File对象指向这个路径，File是否存在
        File dir = new File(parent);
        if(!dir.exists()){//判断当前目录是否存在
            dir.mkdirs();//创建目录
        }
        //获取文件名称，用UUID工具来生成一个新的字符串作为文件名
        String originalFilename = file.getOriginalFilename();//例如avatar01.png
        System.out.println(originalFilename);
        int index = originalFilename.lastIndexOf(".");//获取文件后缀名
        String suffix = originalFilename.substring(index);//将文件后缀名保存起来
        String filename =
                    UUID.randomUUID().toString().toUpperCase()+suffix;//生成随机字符串再拼接文件后缀

        File dest = new File(dir,filename);//是一个空文件
        //将上传的文件写入上面创建的空文件中
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        }
        System.out.println(parent);
        //返回头像的路径 /upload/filename
        String avatar = "/upload/"+filename;
        userService.updateAvatar(getUidFromSession(session),avatar,getUsernameFromSession(session));
        //返回用户头像的路径给前端页面将来用于头像的展示使用
        return new JsonResult<>(OK,avatar);
    }


}
