package com.roncoo.repository;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.roncoo.BaseTest;
import com.roncoo.domain.Book;

/**
 * fetch 抓取策略 测试
 * 
 * 就是关联查询
 * 
 * @author erjun 2017年11月15日 上午5:52:20
 */
public class FetchTest extends BaseTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FetchRepository fetchRepository;

    @Autowired
    private FetchRepository2 fetchRepository2;

    @Test
    public void testByFindOne() {
        // 使用findOne 查询的话，执行了关联查询
        // 在Book对象里 对Category字段使用了注解 @ManyToOne
        // fetch的默认值就是EAGER，就是立即查询的意思
        Book book = bookRepository.findOne(1L);

        System.out.println(book.getCategory().getName());
    }

    @Test
    public void testByFindByName() {
        // 自定义的findByName方法，会 执行两条SQL语句，才可以将关联的另一个表内容查询出来
        // 效率降低了。
        List<Book> books = bookRepository.findByName("战争");

        for (Book book : books) {
            // ------ 如果没有使用关联查询的话-------，
            // 上面的语句，只会查询出book的内容
            // 等到程序执行到这里，需要获取Category的内容时，还会进行查询数据库的
            // 因此，效率是低的
            System.out.println("--1-->:\t" + book.getCategory().getName());
            System.out.println("--2-->:\t" + book.getCategory().getName());
            System.out.println("--3-->:\t" + book.getCategory().getAlias());
            // 上面的3条打印输出，查询时，只会查询一次，应该是利用了
            // 持久化上下文一级缓存的知识
        }

    }

    // 测试目的，使用 @EntityGraph(attributePaths = "category") 注解
    // 只要调用findByName 方法，都会执行一次关联查询，查询category表的内容
    @Test
    public void test2() {
        // 测试抓取策略
        fetchRepository.findByName("战争");
    }

    @Test
    public void test3() {
        // 测试，自定义 抓取策略
        // 抓取的目的，就是，通过一条SQL语句，来查询出所有关联表的内容
        fetchRepository2.findByName("战争");
    }
}
