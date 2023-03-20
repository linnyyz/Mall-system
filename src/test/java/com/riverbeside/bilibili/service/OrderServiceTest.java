package com.riverbeside.bilibili.service;

import com.riverbeside.bilibili.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//@SpringBootTest:表示标注当前的类是一个测试类,不会随同项目一块打包发送
@SpringBootTest
//RunWith:表示启动这个单元测试类（单元测试类是不能够运行的),需要传递一个参数，参数必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Autowired
    IOrderService orderService;

    @Test
    public void createOrder(){
        Integer[] cids = new Integer[]{1,3};
        System.out.println(orderService.createOrder(6,5,"儿子1",cids));
    }
}

