package com.riverbeside.bilibili.mapper;

import com.riverbeside.bilibili.entity.Order;
import com.riverbeside.bilibili.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTest {

    @Autowired
    OrderMapper orderMapper;



    @Test
    public void createOrderTest(){
        Order order = new Order();
        order.setUid(6);
        order.setRecvName("儿子");
        order.setRecvPhone("19982712654");
        order.setRecvProvince("四川省");
        order.setRecvCity("绵阳市");
        order.setRecvArea("涪城区");
        order.setRecvAddress("青义");
        order.setTotalPrice(100000);
        order.setStatus(0);
        order.setOrderTime(new Date());
        order.setPayTime(new Date());
        order.setModifiedTime(new Date());
        order.setModifiedUser("儿子");
        System.out.println(orderMapper.createOrder(order));
    }

    @Test
    public void insertOrderItemTest(){
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(1);
        orderItem.setTitle("测试");
        System.out.println(orderMapper.insertOrderItem(orderItem));
    }
}
