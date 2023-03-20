package com.riverbeside.bilibili.service;


import com.riverbeside.bilibili.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 用户模块业务层接口
 */
public interface IUserService {
    /**
     * 用户注册方法
     *
     * @param user 用户的数据对象
     */
    void reg(User user);

    /**
     * 用户登录方法
     *
     * @param username,password 用户的用户名和密码
     * @return 当前匹配的用户数据，如果没有则返回null
     */
    User login(String username, String password);

    /**
     * 用户修改密码
     *
     * @param uid          用户id
     * @param oldPassword  旧密码
     * @param newPassword  新密码
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 影响行数
     */
    Integer updatePasswordByUid(Integer uid, String oldPassword, String newPassword, String modifiedUser, Date modifiedTime);

    /**
     * 根据有用户的id查询用户数据
     *
     * @param uid 用户id
     * @return 用户的数据
     */
    User getByUid(Integer uid);


    /**
     * 更新用户数据操作
     *
     * @param uid      用户id
     * @param username 用户的名称
     * @param user     用户对象的数据
     * @return 影响的行数
     */
    Integer updateInform(Integer uid, String username, User user);

    /**
     * 修改用户的头像
     * @param uid 用户的id
     * @param avatar 用户头像的路径
     * @param username 用户名称
     * @return
     */
    void updateAvatar(Integer uid, String avatar, String username);

}
