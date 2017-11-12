package com.roncoo.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.roncoo.BaseTest;
import com.roncoo.domain.Book;

public class BookRepositoryTest extends BaseTest {

    // 当spring启动的时候，发现BookRespository 继承了Repository 接口，
    // 会自动为实现Repository接口的子类，创建一个代理对象，
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindByName() {

        // ---代理对象----: class com.sun.proxy.$Proxy96， 通过代理类，来操作数据库
        System.out.println("---代理对象----:\t" + bookRepository.getClass());

        bookRepository.findByName("战争与和平");
    }

    @Test
    public void testSave() {
        Book book = new Book();

        book.setName("spark");

        // 保存与更新 都是一个接口，那么什么时候是保存，什么时候是更新呢？
        // 是根据book属性里的ID号是否为空？
        // 空的话，就保存
        // 非空的话，就更新
        bookRepository.save(book);
    }

}
