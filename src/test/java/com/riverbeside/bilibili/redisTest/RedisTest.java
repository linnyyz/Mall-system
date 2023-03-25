package com.riverbeside.bilibili.redisTest;


import com.riverbeside.bilibili.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {


    @Autowired
    RedisUtils redisUtils;

    @Test
    public void hashTest() {
        redisUtils.setHash("rePut", "test", "success",30);
        System.out.println(redisUtils.deleteValue("rePut"));
    }
}
