package com.riverbeside.bilibili.mapper;

import com.riverbeside.bilibili.entity.Order;
import com.riverbeside.bilibili.entity.OrderItem;

/**订单持久层接口*/
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 影响行数
     */
    Integer createOrder(Order order);


    /**
     * 插入订单项数据
     * @param orderItem 订单项数据
     * @return 影响行数
     */
    Integer insertOrderItem(OrderItem orderItem);
}
