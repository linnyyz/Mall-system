package com.riverbeside.bilibili.service.impl;

import com.riverbeside.bilibili.entity.User;
import com.riverbeside.bilibili.mapper.UserMapper;
import com.riverbeside.bilibili.service.IUserService;
import com.riverbeside.bilibili.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 用户模块业务层的实现类
 */
@Service //@Service注解将当前类的对象交给Spring来管理，能自动创建对象一级对象的维护
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        // 调用findUsername() 判断用户是否被注册过

        User result = userMapper.findUserName(user.getUsername());
        //如果结果result不为null则抛出用户名被占用的异常
        if (result != null) {
            //抛出异常
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //密码加密处理的实现 ：MD5算法加密
        //串 + password + 串 ---MD5算法进行加密,连续加载3次
        //(串-->一般成为盐值) 一个随机的字符串
        String oldPassword = user.getPassword();
        //获取盐值（随机生成一个盐值）
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将密码和盐值作为一个整体进行加密处理
        String md5Password = getMD5Password(oldPassword, salt);
        //将加密密码替换原密码
        user.setPassword(md5Password);


        //补全盐值
        user.setSalt(salt);
        //补全数据: is_delete设置为0
        user.setIsDelete(0);
        //补全数据: 4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行注册业务功能的实现 rows==1
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        //查找是否存在该用户
        User res = userMapper.findUserName(username);

        //用户不存在
        if (res == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        //密码错误
        //1.先获取数据库保存的加密密码
        //2.将传递过来的用户密码通过和存储密码加密时的方式加密
        //3.再比较两个密码是否相同
        String afterPwd = getMD5Password(password, res.getSalt());
        if (!afterPwd.equals(res.getPassword())) {
            throw new PasswordNotMatchException("用户密码错误");
        }

        //判断is_delete字段是否为1
        if (res.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户数据不存在");
        }

        //减少数据量与层与层和前后端的体量，提高了性能
        User user = new User();
        user.setUid(res.getUid());
        user.setUsername(res.getUsername());
        user.setAvatar(res.getAvatar());
        return user;
    }

    @Override
    public Integer updatePasswordByUid(Integer uid, String oldPassword, String newPassword, String modifiedUser, Date modifiedTime) {
        User user = userMapper.findByUid(uid);
        Integer rows;
        //判断用户是否存在,或者是否已被删除
        if (user == null || user.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户不存在或已被删除");
        }

        //输入的旧密码与原始密码对比
        if (!getMD5Password(oldPassword, user.getSalt()).equals(user.getPassword())) {
            throw new PasswordNotMatchException("旧密码与原密码不一致");
        }

        //将新密码加密
        String password = getMD5Password(newPassword, user.getSalt());
        //调用userMapper更新密码
        rows = userMapper.updatePasswordByUid(uid, password, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("密码更新异常未成功");
        }
        return rows;
    }

    @Override
    public User getByUid(Integer uid) {
        User res = userMapper.findByUid(uid);
        if(res==null||res.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUsername(res.getUsername());
        user.setEmail(res.getEmail());
        user.setGender(res.getGender());
        user.setPhone(res.getPhone());

        return user;
    }


    @Override
    public void updateAvatar(Integer uid, String avatar, String username) {
        //查询当前的用户数据是否存在
        User user = userMapper.findByUid(uid);
        if(user==null||user.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }

        Integer rows = userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if (rows!=1){
            throw new UpdateException("密码更新异常未成功");
        }
    }

    /**
 * User对象中的数据phone，email，gender 需要手动将uid和username封装到user对象
 */
    @Override
    public Integer updateInform(Integer uid, String username, User user) {
        User res = userMapper.findByUid(uid);
        if(res==null||res.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInform(user);
        if (rows!=1){
            throw new UpdateException("更新数据出现未知异常");
        }

        return rows;
    }


    /**
     * 定义一个MD5算法加密处理
     */
    private String getMD5Password(String password, String salt) {
        //MD5加密算法的调用(进行三次加密)
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
