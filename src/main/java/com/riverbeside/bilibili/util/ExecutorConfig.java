package com.riverbeside.bilibili.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
public class ExecutorConfig {


    @Bean("Executor")
    public Executor asynServiceExecutor(){
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        //设置核心线程数
//        executor.setCorePoolSize(5);
//        //设置最大线程数
//        executor.setMaxPoolSize(20);
//        //配置队列大小
//        executor.setQueueCapacity(Integer.MAX_VALUE);
//        //设置线程活跃时间(秒)
//        executor.setKeepAliveSeconds(60);
//        //等待所有任务结束关闭线程池
//        executor.setWaitForTasksToCompleteOnShutdown(true);
//        //执行初始化
//        executor.initialize();
//
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                5,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10)
        );

        return executor;
    }
}
