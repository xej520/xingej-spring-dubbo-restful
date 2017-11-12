package com.roncoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roncoo.domain.Book;

/**
 * JpaRepository 这个Repository 是最常用的Repository
 * 
 * @author erjun 2017年11月12日 下午7:46:17
 */

public interface BookRespository3 extends JpaRepository<Book, Long> {

}
