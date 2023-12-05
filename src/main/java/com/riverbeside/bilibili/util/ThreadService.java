package com.riverbeside.bilibili.util;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {

    @Async("Executor")
    public void minCount(Integer uid,Integer aid,String username,Integer[] cids){
        try {
            Thread.sleep(5000);
            System.out.println("完成了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
