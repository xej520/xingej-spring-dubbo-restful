package com.roncoo.repository;

import com.roncoo.domain.Book;
import com.roncoo.support.BSRepository;

/**
 * 动态查询
 * 
 * 就是会根据用户的输入，动态生成不懂的SQL语句查询；
 * 
 * 或者说，有的时候输入1个参数，有的时候输入3个参数
 * 
 * 如何实现呢？
 * 
 * 必须继承JpaSpecificationExecutor 接口，当然也要继承JpaRepository接口
 * 
 * @author erjun 2017年11月14日 上午6:28:05
 */
public interface BookRepository5 extends BSRepository<Book> {

}
