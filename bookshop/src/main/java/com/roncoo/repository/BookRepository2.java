package com.roncoo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.roncoo.domain.Book;

/**
 * 测试 分页 和 排序 Repository
 * 
 * @author erjun 2017年11月12日 下午6:48:19
 */
public interface BookRepository2 extends PagingAndSortingRepository<Book, Long> {

}
