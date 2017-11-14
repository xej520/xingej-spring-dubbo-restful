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
            System.out.println("---->:\t" + book.getCategory().getName());
        }

    }

}
