package com.roncoo.repository;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import com.roncoo.BaseTest;
import com.roncoo.domain.Book;

/**
 * 
 * 
 * @author erjun 2017年11月13日 下午9:36:02
 */
public class BookRepositoryTest4 extends BaseTest {

    @Autowired
    private BookRepository4 bookRep;

    @Test
    public void testByQuery() {
        Page<Book> books = bookRep.findBooks("战争", "计算机", new PageRequest(0, 10, Direction.DESC, "name"));
        List<Book> bookContent = books.getContent();

        for (Book book : bookContent) {
            System.out.println("--->:\t" + book.getName());
        }

    }

}
