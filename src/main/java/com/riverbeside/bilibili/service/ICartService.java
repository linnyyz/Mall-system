package com.riverbeside.bilibili.service;


import com.riverbeside.bilibili.VO.CartVO;
import com.riverbeside.bilibili.entity.Cart;
import com.riverbeside.bilibili.entity.Product;

import java.util.List;

public interface ICartService {

    /**
     * 添加购物车信息，如果购物车中本来存在该商品，那么直接增加原信息的num
     * @param num 添加的数量
     * @param uid 用户id
     * @param username 用户名
     * @param product 添加的商品
     */
    void addProductInCart(Integer num,Integer uid, String username, Product product);


    /**
     * 获取购物车的展示信息
     * @param uid 用户的id
     * @return 购物车的展示信息
     */
    List<CartVO> getCartVO(Integer uid);

    /**
     * 添加购物车的数量
     * @param uid 用户id
     * @param cid 购物车id
     * @param num 添加的数量
     */
    void addCartNum(Integer uid,Integer cid,Integer num);


    /**
     * 展示勾选的购物车的数据
     * @param cid 购物车id
     * @return 勾选的购物车的数据
     */
    List<CartVO> findByCid(Integer uid,Integer[] cid);

}
