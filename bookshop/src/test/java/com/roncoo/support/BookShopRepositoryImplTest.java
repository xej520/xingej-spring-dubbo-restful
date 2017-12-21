package com.roncoo.support;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.roncoo.BaseTest;
import com.roncoo.domain.Book;
import com.roncoo.domain.Category;
import com.roncoo.repository.BookRepository5;

public class BookShopRepositoryImplTest extends BaseTest {

    @Autowired
    private BookRepository5 bookRep;

    @Test
    public void test() {
        System.out.println("-----就是测试呢-----");
        // 默认是failed() 这个在进行打包时，会抛异常的
        // 因此，注释掉了，改成 System.out.println("-----就是测试呢-----");
    }

    // 测试使用自己的定义的Repository
    @Test
    public void testByMysel() {
        Book book = new Book();

        Category category = new Category();
        category.setAlias("文艺");
        category.setName("文艺发展史");

        book.setCategory(category);
        book.setName("战争与和平");

        // 这里，其实，会使用我们自己定义的
        // BookShopRepositoryImpl.java
        // 每次调用save前，都会打印一条日志
        bookRep.save(book);
    }

}
