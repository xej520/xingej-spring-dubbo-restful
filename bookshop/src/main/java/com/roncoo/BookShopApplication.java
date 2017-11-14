package com.roncoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.roncoo.support.BookShopRepositoryImpl;

@SpringBootApplication // 就是声明本项目是SpringBoot项目
// 告诉JPA，生成代理类时，要基于我的BookShopRepositoryImpl为基类
@EnableJpaRepositories(repositoryBaseClass = BookShopRepositoryImpl.class)
public class BookShopApplication {

    public static void main(String[] args) {
        // 声明项目入口
        // 就是告诉springboot, 将此类BookShopApplication作为项目的入口。
        SpringApplication.run(BookShopApplication.class, args);
    }

}
