package com.roncoo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.roncoo.domain.Book;

/**
 * Repository<Book, Long> 参数介绍
 * 
 * Book 是这个Repository要操作的对象，也就是说，要操作的数据表
 * 
 * Long 是主键类型
 * 
 * CrudRepository： 是Repository的子类，但是 它不会产生代理对象，
 * 
 * 因为它有这个注解@NoRepositoryBean，会告诉spring 不需要给这个类产生代理对象的
 * 
 * CrudRepository 提供了基本的增删改查的方法
 * 
 * @author erjun 2017年11月12日 上午10:46:22
 */
public interface BookRepository extends CrudRepository<Book, Long> {
    // 根据name属性来查询book列表
    public List<Book> findByName(String name);

}
