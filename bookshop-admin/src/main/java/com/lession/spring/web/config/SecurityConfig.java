package com.lession.spring.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * 
 * @author erjun 2017年11月23日 下午10:20:47
 */
// 添加注解
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticationSuccessHandler bookShopAuthenticationSuccessHandler;

    @Autowired
    private BookShopAuthenticationFailureHandler bookShopAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        // 启动项目时，创建一个 表，来存储token
        // 第一次启动时，需要设置为True
        // 第2次启动时，就不需要这个了，因此注释掉哦
        // tokenRepositoryImpl.setCreateTableOnStartup(true);

        // setDataSource() 与 setJdbcTemplate(jdbcTemplate);
        // 使用其中的一个就可以了
        tokenRepositoryImpl.setDataSource(dataSource);

        // 基于数据库的 存储 token的对象
        return tokenRepositoryImpl;

    }

    // 配置自定义的 身份认证 逻辑
    // 也就是说，当用户在输入用户名和密码时，不再用框架自带的 校验
    // 而是，我自己定义的 身份认证逻辑
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }

    // 认证方式是
    // formLogin
    // 这种方式，必须是代码的方式
    // basic方式，不需要写代码，直接用就可以了，就是页面会弹出一个对话框，用户要写上用户名和密码
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 实现表单认证，并且，添加 页面
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/auth")
                // 添加认证成功后，处理器
                .successHandler(bookShopAuthenticationSuccessHandler)
                // 添加 认证失败后，处理器
                .failureHandler(bookShopAuthenticationFailureHandler).and().rememberMe()
                .tokenRepository(persistentTokenRepository()).and().csrf().disable().authorizeRequests()

                // 只要controller里URL 是GET请求的 都可以访问
                // .antMatchers(HttpMethod.GET).permitAll().and().authorizeRequests()

                // 只要是book开头的URL请求，也可以都访问的，不需要输入用户名和密码
                .antMatchers("/book/*", "/login.html", "/auth").permitAll()

                // 但是，其他请求，必须经过身份认证才可以的
                .anyRequest().authenticated();

        // http.httpBasic().and().authorizeRequests()
        //
        // // 只要controller里URL 是GET请求的 都可以访问
        // // .antMatchers(HttpMethod.GET).permitAll().and().authorizeRequests()
        //
        // // 只要是book开头的URL请求，也可以都访问的，不需要输入用户名和密码
        // .antMatchers("/book/*").permitAll()
        //
        // // 但是，其他请求，必须经过身份认证才可以的
        // .anyRequest().authenticated();

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
