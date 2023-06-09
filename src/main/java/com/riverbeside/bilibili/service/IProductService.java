package com.riverbeside.bilibili.service;


import com.riverbeside.bilibili.entity.District;
import com.riverbeside.bilibili.entity.Product;

import java.util.List;

public interface IProductService {


    /**
     * 获取热卖前十商品
     *
     * @return 热卖商品列表
     */
    List<Product> findHotList();


    /**
     * 减少库存
     *
     * @param pid 商品id
     * @param num 减少数量
     */
    void reduceProduct(Integer pid, Integer num);
}
