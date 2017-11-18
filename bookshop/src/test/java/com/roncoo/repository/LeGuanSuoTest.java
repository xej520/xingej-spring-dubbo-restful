package com.roncoo.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.roncoo.BaseTest;
import com.roncoo.domain.Book;

/**
 * 乐观锁 测试
 * 
 * --------<问题描述？
 * 
 * 有两个人同时操作一个账户，同时读取到余额为100元
 * 
 * 第一个人，买东西花了10元，然后将余额改为90元 ;第二个人，存了100元，然后，将余额改为200元，
 * 
 * 结果，就会覆盖第一个人的操作，也就是说，第一个人没有花10元。
 * 
 * ---------<如何避免这个问题呢？>
 * 
 * 就需要使用乐观锁了，每次更新时，都要更新version，这样第一个人更新成功后，第二个人，就会更新失败
 * 
 * ---------<如何使用呢？ 乐观锁呢>
 * 
 * 在实体类Book里，添加一个version字段，每次更新操作时，就会更新这个字段；
 * 
 * 在JPA里，只要添加一个注解@Version，就可以了
 * 
 * @author erjun 2017年11月18日 下午4:55:36
 */
public class LeGuanSuoTest extends BaseTest {

    @Autowired
    private BookRepository5 bookRepository5;

    @Test
    public void test() {
        Book book = bookRepository5.findOne(8L);

        book.setName("和平与战争");

        // 立即更新
        bookRepository5.saveAndFlush(book);
    }

}
