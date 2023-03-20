package com.riverbeside.bilibili.service.impl;

import com.riverbeside.bilibili.entity.Product;
import com.riverbeside.bilibili.entity.User;
import com.riverbeside.bilibili.mapper.ProductMapper;
import com.riverbeside.bilibili.mapper.UserMapper;
import com.riverbeside.bilibili.service.IProductService;
import com.riverbeside.bilibili.service.IUserService;
import com.riverbeside.bilibili.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户模块业务层的实现类
 */
@Service //@Service注解将当前类的对象交给Spring来管理，能自动创建对象一级对象的维护
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {

        List<Product> hotList = productMapper.findHotProducts();

        if (hotList==null){
            throw new ProductNotFoundException("商品获取失败!");
        }

        for(Product p:hotList){
            p.setId(null);
            p.setCategoryId(null);
            p.setCreatedTime(null);
            p.setCreatedUser(null);
            p.setModifiedTime(null);
            p.setModifiedUser(null);
        }

        return hotList;
    }



}
