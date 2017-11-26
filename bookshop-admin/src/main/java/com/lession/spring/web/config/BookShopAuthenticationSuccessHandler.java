package com.lession.spring.web.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("bookShopAuthenticationSuccessHandler")
public class BookShopAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    // 认证成功之后，要做的事情
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // 这里面，可以做一些 用户 登录 日志 处理，比方说，看看都有哪些用户 登录过

        System.out.println("---认证成功后---打印登录用户的信息-----:\t" + (UserDetails) authentication.getPrincipal());

        // 就是为了仍然保存 父类的行为
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
