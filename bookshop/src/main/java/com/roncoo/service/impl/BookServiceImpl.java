package com.roncoo.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lesson.dto.BookInfo;
import com.lesson.service.BookService;
import com.roncoo.domain.Book;
import com.roncoo.repository.BookRepository;

/***
 * 在想拦截的方法里，添加注解，就可以了**服务层是在controller层和Repository层之间的，做连接的，在dao和domain层做转换的**
 * 
 * @author erjun 2017年11月29日 上午6:25:36
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookInfo getInfo(Long id) {
        Book book = bookRepository.findOne(id);

        BookInfo bookInfo = new BookInfo();

        BeanUtils.copyProperties(book, bookInfo);

        return bookInfo;
    }

    @Override
    public void getKongzhiUrlByZhujie(Long id) {
        System.out.println("------测试使用注解来控制URL权限------");

    }

}
