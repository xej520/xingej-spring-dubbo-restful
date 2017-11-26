package com.lession.spring.web.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author erjun 2017年11月27日 上午6:45:18
 */

// 失败处理器
@Component("bookShopAuthenticationFailureHandler")
public class BookShopAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        response.getWriter().println("----认证失败后-----" + exception.getMessage());
    }

}
