package com.riverbeside.bilibili.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 读取String数据类型
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    /**
     * 添加String类型的数据进redis中
     *
     * @param key   键
     * @param value 值
     * @return 是否设置成功
     */
    public boolean setValue(final String key, String value) {
        boolean result = false;

        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更改redis中的值
     *
     * @param key   键
     * @param value 更改值
     * @return 是否更改成功
     */
    public boolean getAndSetValue(final String key, String value) {
        boolean result = false;

        try {
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            System.out.println("oldValue:" + oldValue);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除redis中的键值对
     *
     * @param key 要删除的键
     * @return 是否删除成功
     */
    public boolean deleteValue(final String key) {
        boolean result = false;

        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 在redis中创建一个list并从左端插入数据
     *
     * @param key   list的名字
     * @param value 插入的值
     * @return
     */
    public boolean lPush(final String key, String value) {
        boolean result = false;

        try {
            redisTemplate.opsForList().leftPush(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查找redis中的一个list中的一个值
     *
     * @param key   list的名字
     * @param index 要查找的数据的下标索引
     * @return 查找到的数据
     */
    public String indexList(final String key, long index) {
        String res = redisTemplate.opsForList().index(key, index);
        System.out.println(res);
        return res;
    }


    /**
     * 更新list中的数据
     *
     * @param key   list的名字
     * @param index 更新数据的下标索引
     * @param value 要更新的数据
     * @return 是否更新成功
     */
    public boolean getAndSetList(final String key, long index, String value) {
        boolean result = false;

        try {
            redisTemplate.opsForList().set(key, index, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据 count 的值，移除列表中与参数 value 相等的元素。
     *
     * @param key   list名字
     * @param count 需要移除的数量
     * @param value 需要移除的值
     * @return 移除的数量
     */
    public Long delListValue(final String key, long count, String value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 读取hash数据
     *
     * @param key     hash表名
     * @param hashKey hash表中的键
     * @return 查找的hash值
     */
    public Object getHash(final String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }


    /**
     * 插入或者修改hash值
     *
     * @param key     hash表的名字
     * @param hashKey 键
     * @param value   值
     */
    public void setHash(final String key, Object hashKey, String value,int outTime) {

        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
            redisTemplate.boundHashOps("rePut").expire(outTime,TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看reids中有没有重复的表单数据
     * @param key
     * @param hashKey
     * @return
     */
    public Long getRePut(final String key, Object hashKey) {

        return redisTemplate.boundHashOps(key).increment(hashKey, 1);
    }


    /**
     * 读取数据
     */
    public Set<String> getSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 插入数据
     */
    public Long setSet(final String key, String[] values) {
        Long flag = 0L;
        try {
            flag = redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 读取数据
     */
    public Set<String> getZset(final String key) {
        return redisTemplate.opsForZSet().range(key, 0, 2);
    }

    /**
     * 插入数据
     */
    public Boolean setZset(final String key, String value, long index) {
        Boolean flag = true;
        try {
            flag = redisTemplate.opsForZSet().add(key, value, index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
