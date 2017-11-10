package com.roncoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 就是声明本项目是SpringBoot项目
public class BookShopApplication {

    public static void main(String[] args) {
        // 声明项目入口
        // 就是告诉springboot, 将此类BookShopApplication作为项目的入口。
        SpringApplication.run(BookShopApplication.class, args);
    }

}
