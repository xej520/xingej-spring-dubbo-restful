package com.roncoo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.roncoo.domain.Book;

/**
 * 抓取策略
 * 
 * @author erjun 2017年11月15日 上午6:21:48
 */
public interface FetchRepository extends CrudRepository<Book, Long> {

    // 测试 抓取策略，使得自定义的findByName2方法，也可以实现关联查询
    // 是说，在抓取book表的时候，也把category表查询一次
    @EntityGraph(attributePaths = "category")
    public Book findByName(String name);
}
