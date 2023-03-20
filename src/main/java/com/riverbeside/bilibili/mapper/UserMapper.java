package com.riverbeside.bilibili.mapper;

import com.riverbeside.bilibili.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**用户模块的持久层接口*/

public interface UserMapper {


    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数（增删改都有受影响的行数返回值，可以根据返回值来判断是否执行成功)
     */
    Integer insert(User user);


    /**
     * 根据用户来查询用户的数据
     * @param username 用户名
     * @return 如果找到对应的用户，则返回这个用户的数据，如果没找到则返回null
     */
    User findUserName(String username);


    /**
     * 修改密码
     * @param uid 用户id
     * @param password 用户修改的新密码
     * @param modifiedUser 修改的用户
     * @param modifiedTime 表示修改的时间
     * @return 返回为受影响的行数
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户的uid查询用户的数据
     * @param uid 用户的uid
     * @return 如果找到相对应的用户数据则返回user，没找到则返回null
     */
    User findByUid(Integer uid);

    /**
     * 修改用户的信息
     * @param user 存着用户信息
     * @return 受影响的行数
     */
    Integer updateInform(User user);

    /**
     * @Param("SQL映射文件中#{}占位符的变量名"):主要用于解决sql语句的占位符和映射的接口方法参数名不一致时，强行将参数注入占位符中
     *
     *根据用户id修改用户头像
     * @param uid 用户id
     * @param avatar 头像路径
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return
     */

    Integer updateAvatarByUid
            (@Param("uid") Integer uid,
             @Param("avatar") String avatar,
             @Param("modifiedUser") String modifiedUser,
             @Param("modifiedTime") Date modifiedTime);
}
