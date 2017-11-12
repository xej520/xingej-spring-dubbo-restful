package com.roncoo.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.roncoo.domain.Book;

/**
 * Repository<Book, Long> 参数介绍
 * 
 * Book 是这个Repository要操作的对象，也就是说，要操作的数据表
 * 
 * Long 是主键类型
 * 
 * @author erjun 2017年11月12日 上午10:46:22
 */
public interface BookRespository extends Repository<Book, Long> {
    // 根据name属性来查询book列表
    public List<Book> findByName(String name);

}
