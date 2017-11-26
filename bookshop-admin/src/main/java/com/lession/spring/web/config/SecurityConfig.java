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
        // http.formLogin().and().authorizeRequests()
        //
        // // 只要controller里URL 是GET请求的 都可以访问
        // // .antMatchers(HttpMethod.GET).permitAll().and().authorizeRequests()
        //
        // // 只要是book开头的URL请求，也可以都访问的，不需要输入用户名和密码
        // .antMatchers("/book/*").permitAll()
        //
        // // 但是，其他请求，必须经过身份认证才可以的
        // .anyRequest().authenticated();

        http.httpBasic().and().authorizeRequests()

                // 只要controller里URL 是GET请求的 都可以访问
                // .antMatchers(HttpMethod.GET).permitAll().and().authorizeRequests()

                // 只要是book开头的URL请求，也可以都访问的，不需要输入用户名和密码
                .antMatchers("/book/*").permitAll()

                // 但是，其他请求，必须经过身份认证才可以的
                .anyRequest().authenticated();

    }
    /**
     * httpBasic认证时什么？
     * 
     * 客户端以“ :
     * ”连接用户名和密码后，再经BASE64加密通过Authorization请求头发送该密文至服务端进行验证，每次请求都需要重复发送该密文。
     * 可见Basic认证过程简单，安全性也低，存在泄露个人账号信息以及其他诸多安全问题。
     * 
     * 
     */

}
