package com.riverbeside.bilibili.mapper;


import com.riverbeside.bilibili.VO.CartVO;
import com.riverbeside.bilibili.entity.Cart;

import java.util.List;

public interface CartMapper {

    /**
     * 添加商品到购物车
     * @param cart 添加信息
     * @return 影响行数
     */
    Integer insertProductInCart(Cart cart);


    /**
     * 通过cid查找购物车信息
     * @param uid 用户id
     * @param pid 商品id
     * @return 相对应购物车信息
     */
    Cart findByUidAndPid(Integer uid,Integer pid);


    /**
     * 如果有添加相同商品到购物车直接添加原有的购物车信息的数量
     * @param cid 购物车id
     * @param num 添加数量
     * @return 影响行数
     */
    Integer addNum(Integer cid,Integer num);


    /**
     * VO对象:Value Object值对象，当金星select查询的时候，查询的结果为多张数据表中的内容，此时发现结果集不能直接使用某个pojo实体类
     * 来接受，POJO实体类不能包含多表查询出来的结果。解决方式是：重新去构建一个新的对象用户存储所查询出来的结果集对应的映射，所以八这个
     * 对象称之为值对象。
     *
     * 购物车展示数据
     *
     * @param uid 用户的id
     * @return 返回购物车和商品的VO类
     */
    List<CartVO> findByUid(Integer uid);


    /**
     * 查找购物车
     * @param cid 购物车id
     * @return 购物车信息
     */
    Cart findByCid(Integer cid);

    /**
     *  显示勾选购物车的信息
     * @param cids 购物车的id
     * @return  被选中购物车的数据
     */
    List<CartVO> findVOsByCid(Integer[] cids);

}
