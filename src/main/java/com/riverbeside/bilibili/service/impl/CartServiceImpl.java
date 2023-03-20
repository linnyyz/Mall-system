package com.riverbeside.bilibili.service.impl;

import com.riverbeside.bilibili.VO.CartVO;
import com.riverbeside.bilibili.entity.Cart;
import com.riverbeside.bilibili.entity.Product;
import com.riverbeside.bilibili.mapper.CartMapper;
import com.riverbeside.bilibili.mapper.ProductMapper;
import com.riverbeside.bilibili.service.ICartService;
import com.riverbeside.bilibili.service.IProductService;
import com.riverbeside.bilibili.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 用户模块业务层的实现类
 */
@Service //@Service注解将当前类的对象交给Spring来管理，能自动创建对象一级对象的维护
public class CartServiceImpl implements ICartService {

    @Autowired
    CartMapper cartMapper;

    @Override
    public void addProductInCart(Integer num, Integer uid, String username, Product product) {
        Cart cart = cartMapper.findByUidAndPid(uid, product.getId());
        if (!(cart == null)) {
            cartMapper.addNum(cart.getCid(), num);
            return;
        }

        Cart add = new Cart();
        add.setCreatedUser(username);
        add.setCreatedTime(new Date());
        add.setModifiedUser(username);
        add.setModifiedTime(new Date());
        add.setUid(uid);
        add.setNum(num);
        add.setPrice(product.getPrice());
        add.setPid(product.getId());

        Integer rows = cartMapper.insertProductInCart(add);
        if (rows < 1) {
            throw new InsertException("购物车添加失败!");
        }

    }

    @Override
    public List<CartVO> getCartVO(Integer uid) {
        return cartMapper.findByUid(uid);
    }

    @Override
    public void addCartNum(Integer uid, Integer cid, Integer num) {
        Cart cart = cartMapper.findByCid(cid);

        if (cart == null) {
            throw new CartNotFoundException("购物车信息未找到");
        }

        if (cart.getUid() != uid) {
            throw new AccessDeniedException("数据异常访问");
        }

        Integer row = cartMapper.addNum(cid, num);
        if (row < 1) {
            throw new UpdateException("商品添加失败");
        }
    }

    @Override
    public List<CartVO> findByCid(Integer uid, Integer[] cid) {
        List<CartVO> list = cartMapper.findVOsByCid(cid);

        //表示当前的数据不属于当前的用户
        list.removeIf(cartVO -> !cartVO.getUid().equals(uid));

        return list;
    }
}
