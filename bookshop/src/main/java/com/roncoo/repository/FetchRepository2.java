package com.roncoo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.roncoo.domain.Book;

/**
 * 抓取策略, 定义一个抓取策略，只要使用到了这个策略的地方，都会执行；属于批量执行
 * 
 * @author erjun 2017年11月15日 上午6:21:48
 */
public interface FetchRepository2 extends CrudRepository<Book, Long> {

    @EntityGraph(value = "Book.fetch.category.and.author")
    public Book findByName(String name);

    // ------例如，下面被注释掉的内容，当你有很多这样的方法时，都进行了相同的关联查询时，
    // -------可以定义一个 抓取策略，在原对象Book上使用注解@NamedEntityGraph 来实现

    // @EntityGraph(attributePaths = {"category","authors"})
    // public Book findByName(String name);
    //
    // @EntityGraph(attributePaths = {"category","authors"})
    // public Book findByName(String name);
    //
    // @EntityGraph(attributePaths = {"category","authors"})
    // public Book findByName(String name);

}
