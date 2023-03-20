package com.riverbeside.bilibili.service.impl;

import com.riverbeside.bilibili.entity.Address;
import com.riverbeside.bilibili.mapper.AddressMapper;
import com.riverbeside.bilibili.service.IAddressService;
import com.riverbeside.bilibili.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Value("${user.address.max-count}")
    private Integer maxCount;
    @Autowired
    AddressMapper addressMapper;

    @Override
    public void addAddress(Integer uid, String username, Address address) {
        //调用收货地址的统计数量
        Integer counts = addressMapper.countAddressByUid(address.getUid());

        if (counts >= 20) {
            throw new AddressCountLimitException("收货地址数量达到上限");
        }

        //uid,isDelete
        address.setUid(uid);
        Integer isDefault = counts == 0 ? 1 : 0;
        address.setIsDefault(isDefault);


        //补全四项日志
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        address.setModifiedUser(username);
        address.setCreatedUser(username);

        Integer rows = addressMapper.addAddress(address);
        if (rows != 1) {
            throw new InsertException("插入用户收货地址时出现未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> res = addressMapper.findByUid(uid);

        //省略数据
        for (Address address : res) {
            address.setUid(null);
            address.setZip(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setProvinceCode(null);
            address.setZip(null);
            address.setTel(null);
        }
        return res;
    }

    @Override
    public void deleteAddress(Integer uid, Integer aid) {

        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (address.getUid() != uid) {
            throw new AccessDeniedException("收货地址非法访问");
        }
        //如果用户的收货地址不止一条并且删除的这一条收货地址是默认收货地址，则自动将最近修改过的收货地址设置为收货地址
        if (address.getIsDefault() == 1&&addressMapper.countAddressByUid(uid)!=1) {
            addressMapper.deleteAddress(aid);
            if (addressMapper.findLastModified(uid).getAid() != null) {
                addressMapper.setDefault(addressMapper.findLastModified(uid).getAid(), "系统自动设置", new Date());
            }
            return;
        }
        Integer rows =  addressMapper.deleteAddress(aid);
        if(rows<1){
            throw new DeleteException("删除数据出现异常");
        }
    }

    @Override
    public Address findByAid(Integer uid,Integer aid) {
        Address address = addressMapper.findByAid(aid);

        if (address==null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }

        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        address.setCreatedUser(null);
        address.setCreatedUser(null);

        return address;
    }

    @Override
    public void setDefault(Integer aid, String modifiedName, Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取到的收货地址数据归属
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问数据！");
        }

        Integer rows = addressMapper.setAllNoDefault(uid);
        if (rows < 1) {
            throw new UpdateException("更新数据产生未知异常");
        }
        Integer rows1 = addressMapper.setDefault(aid, modifiedName, new Date());
        if (rows1 < 1) {
            throw new UpdateException("更新数据产生未知异常");
        }
    }
}
