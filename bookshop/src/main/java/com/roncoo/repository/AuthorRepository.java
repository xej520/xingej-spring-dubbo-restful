package com.roncoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.roncoo.domain.Author;

/**
 * 参数校验
 * 
 * 就是测试，某个字段是否符合要求的
 * 
 * @author erjun 2017年11月18日 下午5:27:42
 */
public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

}
