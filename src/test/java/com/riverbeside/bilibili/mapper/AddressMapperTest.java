package com.riverbeside.bilibili.mapper;


import com.riverbeside.bilibili.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

//@SpringBootTest:表示标注当前的类是一个测试类,不会随同项目一块打包发送
@SpringBootTest
//RunWith:表示启动这个单元测试类（单元测试类是不能够运行的),需要传递一个参数，参数必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class AddressMapperTest {


    @Autowired
    AddressMapper addressMapper;

    @Test
    public void insertAddress() {
        Address address = new Address();
        address.setAddress("test");
        address.setAreaCode("test");
        address.setAreaName("test");
        address.setUid(6);
        System.out.println(addressMapper.addAddress(address));
    }

    @Test
    public void countByUid() {
        System.out.println(addressMapper.countAddressByUid(6));
    }

    @Test
    public void findByUid() {
        List<Address> list = addressMapper.findByUid(6);
        for (Address address : list) {
            System.out.println(address);
        }
    }

    @Test
    public void findByAid() {
        System.out.println(addressMapper.findByAid(2));
        ;
    }

    @Test
    public void setAllNoDefault() {
        System.out.println(addressMapper.setAllNoDefault(6));
    }

    @Test
    public void setDefault() {
        System.out.println(addressMapper.setDefault(1, "管理员", new Date()));

    }

    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(6));
    }

    @Test
    public void deleteAddress(){
        System.out.println(addressMapper.deleteAddress(1));
    }

}
