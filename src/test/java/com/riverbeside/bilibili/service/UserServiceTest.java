package com.riverbeside.bilibili.service;


import com.riverbeside.bilibili.entity.User;
import com.riverbeside.bilibili.mapper.UserMapper;
import com.riverbeside.bilibili.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//@SpringBootTest:表示标注当前的类是一个测试类,不会随同项目一块打包发送
@SpringBootTest
//RunWith:表示启动这个单元测试类（单元测试类是不能够运行的),需要传递一个参数，参数必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserServiceTest {
    //idea有检测的功能，接口是不能够直接创建bean的，(动态代理技术来解决)
    @Autowired
    private IUserService userService;

    /**
     * 单元测试：就可以单独的独立运行，不用启动整哥项目，可以做单元测试，提升代码的测试效率
     * 1.必须被@Test所修饰
     * 2.返回值必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须是public
     */
    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("鸿莲");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            //获取类对象随后获取类名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常的具体描述信息
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        try {
            System.out.println(userService.login("儿子","123456123"));
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void updatePassword(){
        System.out.println(userService.updatePasswordByUid(6,"654321","123456","管理员",new Date()));
    }


    @Test
    public void findUserById(){
        System.out.println(userService.getByUid(5));
    }

    @Test
    public void updateInform(){
        User user = new User();
        user.setUid(6);
        user.setEmail("1260866126@qq.com");
        user.setModifiedUser("管理员");
        user.setModifiedTime(new Date());
        user.setPassword("12984516546");
        user.setGender(1);
        System.out.println(userService.updateInform(6,"儿子1",user));
    }

    @Test
    public void updateAvatar(){
        userService.updateAvatar(6,"/upload/tab/image.png","博儿");
    }


}
