package com.riverbeside.bilibili.mapper;


import com.riverbeside.bilibili.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

//@SpringBootTest:表示标注当前的类是一个测试类,不会随同项目一块打包发送
@SpringBootTest
//RunWith:表示启动这个单元测试类（单元测试类是不能够运行的),需要传递一个参数，参数必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class ProductMapperTest {

    @Autowired
    ProductMapper productMapper;

    @Test
    public void findHotTest() {
        System.out.println(productMapper.findHotProducts());;
    }
    @Test
    public void reduceProductTest() {
        System.out.println(productMapper.reduceProduct(10000001,2));;
    }



}
