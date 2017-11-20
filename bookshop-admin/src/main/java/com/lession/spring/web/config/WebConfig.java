package com.lession.spring.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lession.spring.web.interceptor.TimeInterceptor;

/**
 * 配置类，专门用于TimeInterceptor.java 拦截器的，
 * 
 * 使得拦截器 起作用的
 * 
 * @author erjun 2017年11月21日 上午6:39:22
 */
// 告诉spring Boot 这是配置 使用到的一个类哦
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    // 此注解是 spring的aop
    @Autowired
    private TimeInterceptor timeInterceptor;

    // 通过下面的方法，将拦截器 添加到系统里
    // 使其，生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(timeInterceptor);
    }

}
