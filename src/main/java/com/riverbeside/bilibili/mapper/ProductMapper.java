package com.riverbeside.bilibili.mapper;

import com.riverbeside.bilibili.entity.Product;

import java.util.List;

public interface ProductMapper {

    List<Product> findHotProducts();


    Integer reduceProduct(Integer pid,Integer num);

}
