package com.riverbeside.bilibili.service.impl;

import com.riverbeside.bilibili.VO.CartVO;
import com.riverbeside.bilibili.entity.Address;
import com.riverbeside.bilibili.entity.Order;
import com.riverbeside.bilibili.entity.OrderItem;
import com.riverbeside.bilibili.mapper.OrderMapper;
import com.riverbeside.bilibili.service.IAddressService;
import com.riverbeside.bilibili.service.ICartService;
import com.riverbeside.bilibili.service.IOrderService;
import com.riverbeside.bilibili.service.IProductService;
import com.riverbeside.bilibili.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {


    @Autowired
    OrderMapper orderMapper;

    @Autowired
    IAddressService addressService;

    @Autowired
    ICartService cartService;

    @Autowired
    IProductService productService;

    @Override
    public Order createOrder(Integer uid, Integer aid, String username, Integer[] cids) {

        List<CartVO> carts = cartService.findByCid(uid, cids);

        long totalPrice = 0;
        //创建订单详细项,计算商品总价
        for (CartVO cartVO : carts) {
            totalPrice += (cartVO.getRealPrice() * cartVO.getNum());
            productService.reduceProduct(cartVO.getPid(), cartVO.getNum());
        }


        Address address = addressService.findByAid(uid, aid);

        Order order = new Order();
        order.setUid(uid);
        //收货地址数据
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setRecvPhone(address.getPhone());
        order.setRecvName(username);
        //支付，总价,下单时间
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());
        //日志
        order.setCreatedTime(new Date());
        order.setCreatedUser(username);
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());
        Integer rows = orderMapper.createOrder(order);
        if (rows != 1) {
            throw new InsertException("插入数据异常");
        }
        for (CartVO cartVO : carts) {
            //创建一个订单项数据
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(cartVO.getPid());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setNum(cartVO.getNum());
            //日志
            orderItem.setCreatedTime(new Date());
            orderItem.setCreatedUser(username);
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());
            rows = orderMapper.insertOrderItem(orderItem);
            if (rows != 1) {
                throw new InsertException("插入数据异常");
            }
        }
        return order;
    }


}
