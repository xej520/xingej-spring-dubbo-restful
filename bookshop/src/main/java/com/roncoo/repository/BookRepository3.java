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

    // like 关键字 练习
    // 根据bookName来查询，模糊查询
    List<Book> findByNameLike(String bookName);

    // OrderBy 关键字 练习
    // 这里，先对name属性，使用like关键字，也就是，名字 可以支持 模糊查询
    // 然后，对查询结果，根据name进行 降序排序
    List<Book> findByNameLikeOrderByNameDesc(String bookName);

    // OrderBy 关键字 练习
    // 升序排序，默认不写的话，就是升序排序
    // --------> 总结： 属性 + 关键字 , 如 NameLike；或者 关键字 + 属性操作, 如 OrderByName；
    // <--------
    // 如 NameLike
    // NameDesc， 降序
    List<Book> findByNameLikeOrderByName(String bookName);
}
