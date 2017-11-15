package com.roncoo.repository;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.roncoo.BaseTest;
import com.roncoo.domain.Book;
import com.roncoo.domain.Ebook;
import com.roncoo.domain.PrintBook;

/**
 * 继承策略 测试
 * 
 * 目的是：在java里，有面向对象的概念，面向对象，有继承的属性，
 * 
 * 那么，PrintBook,Ebook分别继承了Book,
 * 
 * 对应到数据库里，会不会产生新的表？
 * 
 * 测试后，发现不会；PrintBook,Ebook 对应到数据库里 会在Book表里，产生字段。
 * 
 * @author erjun 2017年11月16日 上午6:02:34
 */
public class JiChengCelueTest extends BaseTest {

    @Autowired
    private BookRepository3 bookRepository3;

    @Test
    public void test() {
        // 创建一个PrintBook
        PrintBook printBook = new PrintBook();
        printBook.setPrintDate(new Date());
        printBook.setName("纸质书");

        bookRepository3.save(printBook);

        Ebook ebook = new Ebook();
        ebook.setName("电子书");
        bookRepository3.save(ebook);

        // findAll是把所有的数据都查询出来的，然后转换成对应的子类如PrintBook, Ebook
        List<Book> bookList = bookRepository3.findAll();

        System.out.println("---->" + bookList.size());

        // 下面提供了 3种不同的打印方式
        for (Book book : bookList) {
            System.out.println(book.getClass().getSimpleName());
        }

        // bookList.stream().forEach(book ->
        // System.out.println(book.getClass().getSimpleName()));

        // bookList.forEach(book ->
        // System.out.println(book.getClass().getSimpleName()));

    }

}
