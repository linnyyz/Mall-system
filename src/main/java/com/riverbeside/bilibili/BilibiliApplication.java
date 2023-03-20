package com.riverbeside.bilibili;

import jakarta.servlet.MultipartConfigElement;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

@SpringBootApplication
//mapperscan注解 指定当前项目中的mapper接口的路径位置，在项目启动的时候会自动加载所有接口文件
@MapperScan("com/riverbeside/bilibili/mapper")
@Configuration //表示配置类
public class BilibiliApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilibiliApplication.class, args);
    }


    /**
     * 用java代码来限制文件上传的大小
     */
    @Bean
    public MultipartConfigElement getMultipartConfigElement(){
        //创建一个配置的工厂对象
        MultipartConfigFactory factory = new MultipartConfigFactory();

        //设置需要创建的对象的相关信息
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(15, DataUnit.MEGABYTES));

        //通过工厂类来创建MultipartConfigElement对象
        return factory.createMultipartConfig();
    }

}
