package com.lession.spring.web.config;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
            return new User("zhangsan", "123456", new ArrayList<GrantedAuthority>());
        }

        return null;
    }

}
