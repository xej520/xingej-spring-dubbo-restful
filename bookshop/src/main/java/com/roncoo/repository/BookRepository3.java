package com.roncoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roncoo.domain.Book;

/**
 * JpaRepository 这个Repository 是最常用的Repository
 * 
 * @author erjun 2017年11月12日 下午7:46:17
 */

public interface BookRepository3 extends JpaRepository<Book, Long> {

    // 静态查询
    //
    // 根据book表里的Name属性，以及Category对象里的name属性进行查询
    // 注意
    // CategoryName 要连着写
    List<Book> findByNameAndCategoryName(String bookName, String CategoryName);

}
