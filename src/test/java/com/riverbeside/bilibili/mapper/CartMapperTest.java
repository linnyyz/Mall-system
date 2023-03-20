package com.riverbeside.bilibili.mapper;


import com.riverbeside.bilibili.entity.Cart;
import com.riverbeside.bilibili.entity.Product;
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
public class CartMapperTest {

    @Autowired
    CartMapper cartMapper;

    @Test
    public void insertProductInCartTest() {

        Cart cart = new Cart();
        cart.setCreatedTime(new Date());
        cart.setUid(6);
        cart.setPrice(19999);
        cart.setNum(6);
        cart.setPid(1001);
        cart.setModifiedTime(new Date());
        cart.setModifiedUser("管理员");
        cart.setCreatedUser("管理员");
        System.out.println(cartMapper.insertProductInCart(cart));
    }


    @Test
    public void addNum(){
        cartMapper.addNum(1,3);
    }

    @Test
    public void findCart(){
        System.out.println(cartMapper.findByUidAndPid(6,1001));
    }


    @Test
    public void findByUid(){
        System.out.println(cartMapper.findByUid(6));
    }

    @Test
    public void addCartNum(){
        System.out.println(cartMapper.findByCid(1));
    }

    @Test
    public void findCartsByCids(){
        Integer[] cids = new Integer[]{1};
        System.out.println(cartMapper.findVOsByCid(cids));
    }
}
