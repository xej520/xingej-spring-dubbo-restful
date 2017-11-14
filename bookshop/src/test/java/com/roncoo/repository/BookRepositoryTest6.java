package com.roncoo.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.roncoo.BaseTest;
import com.roncoo.domain.Book;

/**
 * 
 * 
 * @author erjun 2017年11月14日 下午10:34:51
 */
public class BookRepositoryTest6 extends BaseTest {

    @Autowired
    private BookRepository bookRepostiory;

    // 测试
    // 持久化上下文，自动检测脏数据
    @Autowired
    private PlatformTransactionManager transactionManager;

    // 注意，测试这个用例的话，
    // 需要将事务机制关闭
    // BaseTest 里注释掉 @Transactional
    @Test
    public void test() {
        // 开启事务
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        // 其实，同findOne查询，就是将结果存储到了
        // 持久化上下文里
        Book book = bookRepostiory.findOne(1L);
        // 上下文中，更新了book对象的内容，但是数据库里并没有更新，
        book.setName("美女与野兽");

        bookRepostiory.save(book);//

        System.out.println("---success---");

        // 提交事务，才是真正更新到数据库的
        transactionManager.commit(status);

    }

}
