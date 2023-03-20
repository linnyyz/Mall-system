package com.riverbeside.bilibili.mapper;


import com.riverbeside.bilibili.entity.District;

import java.util.List;

public interface DistrictMapper {


    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 某个父区域下的所有区域列表
     */
    List<District> findByParent(String parent);


}
