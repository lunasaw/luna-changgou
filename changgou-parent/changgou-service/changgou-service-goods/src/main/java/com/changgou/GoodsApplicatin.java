package com.changgou;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/*****
 * @Author: luna
 * @Description: com.changgou
 * 启动类
 ****/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.changgou.goods.dao"})  //Dao接口包扫描->@MapperScan:tk下的包
public class GoodsApplicatin {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplicatin.class,args);
    }
}
