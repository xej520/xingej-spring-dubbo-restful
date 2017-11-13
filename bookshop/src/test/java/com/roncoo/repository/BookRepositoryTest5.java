package com.roncoo.repository;

import static org.junit.Assert.fail;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.roncoo.BaseTest;
import com.roncoo.domain.Book;

public class BookRepositoryTest5 extends BaseTest {

    @Autowired
    private BookRepository5 bookRep;

    @Test
    public void test() {
        fail("Not yet implemented");
    }

    // 动态查询
    @Test
    public void testByEqual() {
        Specification<Book> specification = new Specification<Book>() {

            // Root ： 可以认为是对book的封装
            // query： 查询条件
            // cb： 用来构建 Predicate对象的
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("name"), "战争");
            }
        };

        Book book = bookRep.findOne(specification);

        System.out.println("---->:\t" + book.getName());
    }

}
