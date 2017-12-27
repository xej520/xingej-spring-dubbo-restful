package com.roncoo.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lesson.dto.BookInfo;
import com.lesson.service.BookService;
import com.roncoo.BaseTest;
import com.roncoo.repository.BookRepository;

public class BookServiceImplTest extends BaseTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    // 测试创建
    @Test
    public void whenCreateSuccess() {
        long count = bookRepository.count();

        BookInfo bookInfo = new BookInfo();
        bookInfo.setName("神话");

        bookService.create(bookInfo);

        Assert.assertEquals(count + 1, bookRepository.count());
    }

    // 测试更新
    @Test
    public void whenUpdateSuccess() {
        BookInfo bookInfo = new BookInfo();

        bookInfo.setName("神话");

        bookInfo.setId(8L);

        bookInfo = bookService.update(bookInfo);

        Assert.assertEquals("神话", bookRepository.findOne(bookInfo.getId()).getName());
    }

    // 测试删除
    @Test
    public void whenDeleteSuccess() {
        Long id = 8L;

        bookService.delete(id);

        Assert.assertEquals(null, bookRepository.findOne(id));
    }

}
