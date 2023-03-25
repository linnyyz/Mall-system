package com.riverbeside.bilibili.controller;


import com.riverbeside.bilibili.annotation.NoRepeatSubmit;
import com.riverbeside.bilibili.entity.Address;
import com.riverbeside.bilibili.service.IAddressService;
import com.riverbeside.bilibili.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController extends BaseController {

    @Autowired
    private IAddressService addressService;


    @RequestMapping("addNewAddress")
    @NoRepeatSubmit(timeout = 10)
    public JsonResult<Void> addNewAddress(HttpSession session, Address address) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addAddress(uid, username, address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {

        Integer uid = getUidFromSession(session);
        List<Address> res = addressService.getByUid(uid);

        return new JsonResult<>(OK, res);
    }

    @RequestMapping("setAddressDefault")
    public JsonResult<Void> setAddressDefault(HttpSession session,Integer aid){

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        addressService.setDefault(aid,username,uid);

        return new JsonResult<>(OK);
    }


    @RequestMapping("deleteAddress")
    public JsonResult<Void> delete(HttpSession session,Integer aid){

        Integer uid = getUidFromSession(session);
        addressService.deleteAddress(uid,aid);

        return new JsonResult<>(OK);
    }

}
