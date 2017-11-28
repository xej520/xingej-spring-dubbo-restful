package com.lession.spring.service.impl;

import org.springframework.stereotype.Service;

import com.lession.spring.service.BookService;

/**
 * 在想拦截的方法里，添加注解，就可以了
 * 
 * @author erjun 2017年11月29日 上午6:25:36
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Override
    public void getKongzhiUrlByZhujie(Long id) {
        System.out.println("------测试使用注解来控制URL权限------");
    }

}
