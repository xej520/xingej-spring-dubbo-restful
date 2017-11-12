package com.roncoo.repository;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.roncoo.BaseTest;
import com.roncoo.domain.Book;

public class BookRepositoryTest3 extends BaseTest {
    @Autowired
    private BookRepository3 bookRespository3;

    // 查询 练习
    // 查询书名是 分布式系统架构与设计
    // 并
    // 进行 分页排序
    @Test
    public void testByExample() {

        Book book = new Book();
        book.setName("分布式系统架构与设计");

        Example<Book> example = Example.of(book);

        Pageable paramPageable = new PageRequest(0, 5, Direction.DESC, "name");
        bookRespository3.findAll(example, paramPageable);

    }

    // 分页查询，支持 模糊查询
    @Test
    public void testByExample2() {

        Book book = new Book();
        book.setName("分布式");

        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
        // 模糊查询
        // 只要包含 "分布式" 即可
        // 但是，
        // 复杂的 模糊查询不支持，
        // 比如groupBy之类的
        // 目前，仅仅支持 字符串形式的 模糊查询
        Example<Book> example = Example.of(book, matcher);

        Pageable paramPageable = new PageRequest(0, 5, Direction.DESC, "name");
        bookRespository3.findAll(example, paramPageable);

    }

    // 静态查询 练习
    // << and >> 关键字 练习
    @Test
    public void testByStaticFind() {
        // 查询 计算机 门类里的 spark设计 这本书
        bookRespository3.findByNameAndCategoryName("spark设计", "计算机");
    }

    // 静态查询 练习
    // << like >> 关键字 练习
    @Test
    public void testByLike() {
        List<Book> books = bookRespository3.findByNameLike("战争");

        for (Book book : books) {
            System.out.println("---book.name----:\t" + book.getName());
        }
    }

    // 静态查询 练习
    // << like >> 关键字 练习
    // 模糊查询 ---> 开头是"战争"的，全部查询出来，如战争与和平，。。
    @Test
    public void testByLike2() {
        List<Book> books = bookRespository3.findByNameLike("战争%");

        for (Book book : books) {
            System.out.println("---book.name----:\t" + book.getName());
        }
    }

    // 静态查询 练习
    // << like >> 关键字 练习
    // 模糊查询 ---> 结尾是"战争"的，全部查询出来，如 spark与战争，。。。。
    @Test
    public void testByLike3() {
        List<Book> books = bookRespository3.findByNameLike("%战争");

        for (Book book : books) {
            System.out.println("---book.name----:\t" + book.getName());
        }
    }

    // 静态查询 练习
    // << like >> 关键字 练习
    // 模糊查询 ---> 只要含有"战争"的，全部查询出来，如 spark与战争，。
    @Test
    public void testByLike4() {
        List<Book> books = bookRespository3.findByNameLike("%战争%");

        for (Book book : books) {
            System.out.println("---book.name----:\t" + book.getName());
        }
    }
}
