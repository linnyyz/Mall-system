package com.riverbeside.bilibili.service;


import com.riverbeside.bilibili.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTest {


    @Autowired
    IDistrictService districtService;



    @Test
    public void getByParent(){

        List<District> list =  districtService.getByParent("86");
        for (District d : list){
            System.out.println(d);
        }
    }

}
