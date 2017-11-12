package com.roncoo.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.roncoo.BaseTest;

public class BookRespositoryTest extends BaseTest {

    @Autowired
    private BookRespository bookRepository;

    @Test
    public void testFindByName() {
        bookRepository.findByName("战争与和平");
    }

}
