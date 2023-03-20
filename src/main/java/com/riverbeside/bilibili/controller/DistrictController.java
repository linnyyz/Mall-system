package com.riverbeside.bilibili.controller;


import com.riverbeside.bilibili.entity.District;
import com.riverbeside.bilibili.service.IDistrictService;
import com.riverbeside.bilibili.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{

    @Autowired
    IDistrictService districtService;

    //districts开头的请求全部被拦截到getByParent()方法
    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){

        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(OK, data);
    }



}
