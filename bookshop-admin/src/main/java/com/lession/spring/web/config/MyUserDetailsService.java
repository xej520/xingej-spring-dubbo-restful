package com.lession.spring.web.config;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 测试httpBasic认证时 用到的
 * 
 * 自定义UserDetailsService
 * 
 * @author erjun 2017年11月26日 下午6:23:31
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.equals(username, "zhangsan")) {
            System.out.println("------使用----自定义---身份认证方式------");

            // 生产情况下，这里"123456" 应该是通过Respority 向数据库里查询出来的，
            // 并且数据库的密码是经过加密过的，
            // 目前，我这里，仅仅是为了做测试
            // 不然，会跟页面密码不匹配
            // 页面输入的是明文密码，没有加密过，
            // 这里是加密过的密码，应该对应不上，所以，使用new
            // BCryptPasswordEncoder().encode("123456")
            // 进行加密
            String password = new BCryptPasswordEncoder().encode("123456");
            // 加密后，spring 框架 会自动 在对password进行解密的，这样就跟页面上，输入的密码一样了
            return new User("zhangsan", password, new ArrayList<GrantedAuthority>());
        }

        return null;
    }

}
