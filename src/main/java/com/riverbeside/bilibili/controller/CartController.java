package com.riverbeside.bilibili.controller;


import com.riverbeside.bilibili.VO.CartVO;
import com.riverbeside.bilibili.entity.Product;
import com.riverbeside.bilibili.service.ICartService;
import com.riverbeside.bilibili.service.IProductService;
import com.riverbeside.bilibili.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController extends BaseController {

    @Autowired
    ICartService cartService;


    @RequestMapping("addCart")
    public JsonResult<Void> addCart(HttpSession session, Product product, Integer num) {


        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        cartService.addProductInCart(num, uid, username, product);

        return new JsonResult<>(OK);
    }

    @RequestMapping("getCartMessage")
    public JsonResult<List<CartVO>> getCartMessage(HttpSession session) {
        Integer uid = getUidFromSession(session);

        return new JsonResult<>(OK, cartService.getCartVO(uid));
    }

    @RequestMapping("addCartNum")
    public JsonResult<Void> addCartNum(HttpSession session, Integer cid, Integer num) {
        Integer uid = getUidFromSession(session);
        cartService.addCartNum(uid, cid, num);
        return new JsonResult<>(OK);
    }

    @RequestMapping("list")
    public JsonResult<List<CartVO>> selectedCart(HttpSession session,Integer[] cid){
        Integer uid = getUidFromSession(session);
        List<CartVO> list = cartService.findByCid(uid ,cid);

        return new JsonResult<>(OK,list);
    }

}
