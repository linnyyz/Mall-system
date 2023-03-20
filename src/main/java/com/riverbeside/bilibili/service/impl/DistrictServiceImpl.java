package com.riverbeside.bilibili.service.impl;

import com.riverbeside.bilibili.entity.District;
import com.riverbeside.bilibili.mapper.DistrictMapper;
import com.riverbeside.bilibili.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    DistrictMapper districtMapper;


    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        //在进行网络数据传输时，为了尽量避免无效数据的传递，可以将无效数据设置为null，这样可以节省流量,提升了效率
        List<District> res = new ArrayList<>();
        for(District d:list){
            d.setId(null);
            d.setParent(null);
            res.add(d);
        }
        return res;
    }
}
