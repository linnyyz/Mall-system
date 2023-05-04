package com.riverbeside.bilibili.controller;


import com.riverbeside.bilibili.entity.Product;
import com.riverbeside.bilibili.service.IProductService;
import com.riverbeside.bilibili.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController{

    @Autowired
    IProductService productService;


    @RequestMapping("hotProducts")
    public JsonResult<List<Product>> getHotProducts(){
        List<Product> hotProducts = productService.findHotList();
        return new JsonResult<>(OK,hotProducts);

    }

}
