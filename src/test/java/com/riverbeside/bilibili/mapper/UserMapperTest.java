package com.riverbeside.bilibili.mapper;


import com.riverbeside.bilibili.entity.User;
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
public class UserMapperTest {
    //idea有检测的功能，接口是不能够直接创建bean的，(动态代理技术来解决)
    @Autowired
    private UserMapper userMapper;

    /**
     * 单元测试：就可以单独的独立运行，不用启动整哥项目，可以做单元测试，提升代码的测试效率
     * 1.必须被@Test所修饰
     * 2.返回值必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须是public
     */
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("linny");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void select() {
        User user = userMapper.findUserName("linny");
        System.out.println(user.getUsername());
    }

    @Test
    public void upDatePasswordByUid(){
        System.out.println(userMapper.updatePasswordByUid(5,"123456123","管理员",new Date()));
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(6));
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
        System.out.println(userMapper.updateInform(user));
    }

    @Test
    public void updateAvatarById(){

        System.out.println(userMapper.updateAvatarByUid(6,"update/load/avatar.png","管理员",new Date()));
    }
}
