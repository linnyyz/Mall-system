package com.riverbeside.bilibili.service;


import com.riverbeside.bilibili.entity.District;

import java.util.List;

public interface IDistrictService {


    /**
     * 根据父代号查询区域信息(省/市/区)
     * @param parent 父代号
     * @return 返回父级子区域的所有信息
     */
    List<District> getByParent(String parent);

}
