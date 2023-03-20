package com.riverbeside.bilibili.service;

import com.riverbeside.bilibili.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//@SpringBootTest:表示标注当前的类是一个测试类,不会随同项目一块打包发送
@SpringBootTest
//RunWith:表示启动这个单元测试类（单元测试类是不能够运行的),需要传递一个参数，参数必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class AddressServiceTest {

    @Autowired
    IAddressService addressService;


    @Test
    public void addNewAddressTest(){
        Address address = new Address();
        address.setCityCode("110000");
        address.setName("西华大学");
        addressService.addAddress(6,"博儿",address);
    }

    @Test
    public void findByUid(){
        System.out.println(addressService.getByUid(6));
    }

    @Test
    public void setAddressDefault(){
        addressService.setDefault(3,"管理员",6);
    }

    @Test
    public void deleteAddressTest(){
        addressService.deleteAddress(6,3);
    }

    @Test
    public void findByAid(){
        System.out.println(addressService.findByAid(6,5));
    }
}
