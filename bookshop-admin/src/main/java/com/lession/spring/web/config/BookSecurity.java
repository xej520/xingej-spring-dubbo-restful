package com.lession.spring.web.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 自定义授权策略 测试，方式二：
 * 
 * @author erjun 2017年11月29日 下午10:47:19
 */
@Component("bookSecurity")
public class BookSecurity {
    public boolean check(Authentication authentication, HttpServletRequest request) {

        System.out.println(request.getRequestURI());

        Object principal = authentication.getPrincipal();

        if (null != principal && principal instanceof UserDetails) {
            // 打印出权限
            System.out.println(((UserDetails) principal).getAuthorities());
        }

        // 其实，控制的是就是Vote,就是投票机制那个
        // 如果为true的话，就投票通过
        return true; // true 表示，可以访问，false：就是不能访问
    }
}
