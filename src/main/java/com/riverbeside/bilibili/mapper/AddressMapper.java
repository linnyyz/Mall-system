package com.riverbeside.bilibili.mapper;


import com.riverbeside.bilibili.entity.Address;

import java.util.Date;
import java.util.List;

/**
 * 收货地址模块
 */

public interface AddressMapper {

    /**
     * 插入用户的收货地址数据
     *
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer addAddress(Address address);

    /**
     * 根据用户id统计收获地址的条数
     *
     * @param uid 用户id
     * @return 返回当前用户拥有的收货地址条数
     */
    Integer countAddressByUid(Integer uid);

    /**
     * 通过用户id查找用户所有的收货地址
     *
     * @param Uid 用户id
     * @return 返回的收获地址信息
     */
    List<Address> findByUid(Integer Uid);


    /**
     * 查找要修改的收货地址信息是否存在
     *
     * @param aid 地址信息的id
     * @return 查找的地址信息
     */
    Address findByAid(Integer aid);


    /**
     * 将该uid下的所有地址默认都改为非默认
     *
     * @param uid 用户id
     * @return 影响行数
     */
    Integer setAllNoDefault(Integer uid);

    /**
     * 将该收货地址改为默认收货地址
     *
     * @param aid 地址信息的id
     * @return 受影响的行数
     */
    Integer setDefault(Integer aid, String username, Date modifiedTime);

    /**
     * 删除收货地址
     *
     * @param aid 地址id
     * @return 影响行数
     */
    Integer deleteAddress(Integer aid);

    /**
     * 获取最近添加的收货地址id
     *
     * @param uid 用户信息
     * @return 最近添加的收货地址id
     */
    Address findLastModified(Integer uid);
}
