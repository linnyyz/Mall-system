package com.riverbeside.bilibili.controller;


import com.riverbeside.bilibili.entity.Order;
import com.riverbeside.bilibili.service.IOrderService;
import com.riverbeside.bilibili.util.JsonResult;
import com.riverbeside.bilibili.util.ThreadService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController{

    @Autowired
    IOrderService orderService;

    @Autowired
    ThreadService threadService;
    @RequestMapping("createOrder")
    public JsonResult<Order> createOrder(Integer aid, HttpSession session, Integer[] cids){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Order order = orderService.createOrder(uid,aid,username,cids);
        threadService.minCount(uid,aid,username,cids);
        return new JsonResult<>(OK,order);
    }

}
