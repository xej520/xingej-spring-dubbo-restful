package com.lession.spring.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
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

    // 注册一个filter过滤器
    // 先执行过滤器，再执行 拦截器
    // 先将所有的过滤器都执行完了，再执行拦截器的
    // 还有其他类型的FilterRegistrationBean，如SevlevtXXXX
    // 基本的编码形式，都是下面的样子，只是，返回的类型bean不一样
    @Bean
    public FilterRegistrationBean characterEncodingFilterRegister() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        // 创建一个关于 编码的filter的
        CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8");
        filter.setForceEncoding(true);
        registrationBean.setFilter(filter);

        List<String> urls = new ArrayList<>();
        // 指定 过滤的url路径，
        // 只有在下面的路径下，使用filter
        urls.add("/admin/book/*");
        // urls.add("/*");
        registrationBean.setUrlPatterns(urls);

        return registrationBean;

    }

}
