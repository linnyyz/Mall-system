package com.riverbeside.bilibili.service;

import com.riverbeside.bilibili.entity.Address;

import java.util.Date;
import java.util.List;

/**收货地址的业务层*/
public interface IAddressService {

    /**
     * 添加收货地址
     * @param address 收货地址信息
     */
    void addAddress(Integer uid,String username,Address address);

    /**
     * 获得用户的所有收货地址
     * @param uid
     * @return 用户所有收货地址
     */
    List<Address> getByUid(Integer uid);

    /**
     * 设置收货地址为默认收货地址
     * @param aid 地址id
     * @param modifiedName 修改者
     */
    void setDefault(Integer aid, String modifiedName,Integer uid);


    /**
     * 删除收货地址
     * @param uid 用户id
     * @param aid 收货地址id
     */
    void deleteAddress(Integer uid,Integer aid);

    /**
     * 通过aid获取收货地址
     * @param aid 收货地址id
     * @return 收货地址
     */
    Address findByAid(Integer uid,Integer aid);


}
