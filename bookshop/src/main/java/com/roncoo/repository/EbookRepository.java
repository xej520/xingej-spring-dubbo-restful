package com.roncoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roncoo.domain.Ebook;

/**
 * 继承策略
 * 
 * 只操作,如查询；关于Ebook的记录，PrintBook的记录不关心
 * 
 * @author erjun 2017年11月16日 上午6:43:08
 */
public interface EbookRepository extends JpaRepository<Ebook, Long> {

}
