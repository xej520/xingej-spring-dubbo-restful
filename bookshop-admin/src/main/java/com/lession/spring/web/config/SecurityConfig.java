package com.lession.spring.web.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * @author erjun 2017年11月23日 下午10:20:47
 */
// 添加注解
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 认证方式是
    // formLogin
    // 这种方式，必须是代码的方式
    // basic方式，不需要写代码，直接用就可以了，就是页面会弹出一个对话框，用户要写上用户名和密码
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().and().authorizeRequests()

                // 只要controller里URL 是GET请求的 都可以访问
                // .antMatchers(HttpMethod.GET).permitAll().and().authorizeRequests()

                // 只要是book开头的URL请求，也可以都访问的，不需要输入用户名和密码
                .antMatchers("/book/*").permitAll()

                // 但是，其他请求，必须经过身份认证才可以的
                .anyRequest().authenticated();

    }
}
