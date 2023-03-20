package com.riverbeside.bilibili.service;

import com.riverbeside.bilibili.entity.Address;
import com.riverbeside.bilibili.entity.Order;

/**订单业务层*/
public interface IOrderService {

    /**
     * 创建订单
     * @param uid 用户id
     * @param aid 收货地址id
     * @param username 用户名
     * @param cids 购物车id
     */
    Order createOrder(Integer uid, Integer aid, String username, Integer[] cids);

}
