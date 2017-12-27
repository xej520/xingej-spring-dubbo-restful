package com.lession.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lesson.dto.BookCondition;
import com.lesson.dto.BookInfo;
import com.lesson.service.BookService;

/**
 * 这个是针对分布式服务dubbo，创建的，
 * 
 * 就不在以前的基础上，修改了
 * 
 * @author erjun 2017年12月27日 下午9:34:58
 */

// 注意，controller里，不做任何的业务逻辑，
@RestController
@RequestMapping(value = "/book_dubbo")
public class BookControllerForDubbo {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Page<BookInfo> query(BookCondition condition, Pageable pageable) {

        return bookService.query(condition, pageable);
    }

    @PostMapping
    public BookInfo create(@RequestBody BookInfo bookInfo, BindingResult result) {
        return bookService.create(bookInfo);
    }

    @PutMapping("/{id}")
    public BookInfo update(@RequestBody BookInfo bookInfo, BindingResult result) {
        return bookService.update(bookInfo);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

}
