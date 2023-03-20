package com.riverbeside.bilibili.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTest {

    @Autowired
    DistrictMapper districtMapper;


    @Test
    public void findByParent(){
        System.out.println(districtMapper.findByParent("210100"));
    }
}
