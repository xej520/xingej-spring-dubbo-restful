package com.roncoo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.roncoo.domain.Book;

/**
 * 使用Repository存在的问题？
 * 
 * 只能声明find查询之类的，update,delete是不可以的
 * 
 * 复杂查询的话，名字会很长
 * 
 * 如何解决呢?这些问题呢？
 * 
 * 使用@Query注解（使用JPQ语句，类似于SQL语句）
 * 
 * @author erjun 2017年11月13日 下午9:29:50
 */
public interface BookRepository4 extends JpaRepository<Book, String> {

    // JPQ语法，返回全部的内容，
    @Query("from Book b where b.name like ?1 and b.category.name = ?2 order by b.name desc")
    Page<Book> findBooks(String bookName, String categoryName, Pageable sort);

    // JPQ语法，返回指定的内容，
    @Query("select b from Book b where b.name like ?1 and b.category.name = ?2 order by b.name desc")
    Page<Book> findBooks2(String bookName, String categoryName, Pageable sort);

}
