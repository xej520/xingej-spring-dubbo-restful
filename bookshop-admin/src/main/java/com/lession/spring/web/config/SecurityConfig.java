package com.lession.spring.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 
 * @author erjun 2017年11月23日 下午10:20:47
 */
@Configuration
// 添加注解
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 使用注解来控制 URL 时，用到的
@EnableOAuth2Sso // 支持授权认证流程，使用OAuth2进行第三方登录
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticationSuccessHandler bookShopAuthenticationSuccessHandler;

    @Autowired
    private BookShopAuthenticationFailureHandler bookShopAuthenticationFailureHandler;

    // @Autowired
    // private DataSource dataSource;

    // @Bean
    // public PersistentTokenRepository persistentTokenRepository() {
    // JdbcTokenRepositoryImpl tokenRepositoryImpl = new
    // JdbcTokenRepositoryImpl();
    // // 启动项目时，创建一个 表，来存储token
    // // 第一次启动时，需要设置为True
    // // 第2次启动时，就不需要这个了，因此注释掉哦
    // // tokenRepositoryImpl.setCreateTableOnStartup(true);
    //
    // // setDataSource() 与 setJdbcTemplate(jdbcTemplate);
    // // 使用其中的一个就可以了
    // // tokenRepositoryImpl.setDataSource(dataSource);
    //
    // // 基于数据库的 存储 token的对象
    // return tokenRepositoryImpl;
    //
    // }

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
                .failureHandler(bookShopAuthenticationFailureHandler)
                // .and()
                // .rememberMe()
                // 将token持久化到数据库里，用于下次对比操作
                // .tokenRepository(persistentTokenRepository())
                // 控制 token的时间，这里是60秒
                // .tokenValiditySeconds(60)
                .and()
                // session的并发管理
                // 当前系统中，session的最大数量是1
                // .sessionManagement()
                // // 每次请求成功后，就要改变sessionID，"防止固定攻击"
                // .sessionFixation().changeSessionId()
                // // session失效时，会跳到登录页面
                // .invalidSessionUrl("/session.html")
                //
                // // 测试时，用两个浏览器测试，看看浏览器的报错，第二次登陆时，前面的session会过期掉
                // .maximumSessions(1)
                // // 第一次登录了后，就不允许再用其他浏览器登录了
                // // 也就是说，当前用户只能有一个session
                // .maxSessionsPreventsLogin(true)
                // .and()
                // .and()
                .csrf().disable()
                // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                // // 授权
                // .and() // 配置
                .authorizeRequests()

                // 只要controller里URL 是GET请求的 都可以访问
                // .antMatchers(HttpMethod.GET).permitAll().and().authorizeRequests()

                // 只要是book开头的URL请求，也可以都访问的，不需要输入用户名和密码
                .antMatchers("/book/*", "/login.html", "/auth", "/session.html").permitAll()
                // 自定义授权策略 测试，方式一： 访问shopURL的权限是a角色或b角色
                .antMatchers("/shop/*").access("hasRole('a') or hasRole('b')")
                // 这种方式的缺点：不够灵活，扩展性不好，比方说，现在shopURL的权限发生变化，或者，新增角色，都需要修改源码的
                // 而且，需要重启 系统，重启上面
                .antMatchers("/user/*").access("hasRole('c') or hasRole('d')")
                // 但是，其他请求，必须经过身份认证才可以的
                // .anyRequest().authenticated();
                // 通过java 的配置，来控制URL的权限
                // .anyRequest().access("hasAuthority('admin')");

                // 自定义授权策略 测试，方式二：
                .anyRequest().access("@bookSecurity.check(authentication, request)");

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
