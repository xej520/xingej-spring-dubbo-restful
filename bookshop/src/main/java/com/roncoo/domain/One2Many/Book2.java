package com.roncoo.domain.One2Many;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 测试数据库表之间的关系
 * 
 * 
 * 
 * 使用@OneToMany注解
 * 
 * @author erjun 2017年11月11日 上午7:06:46
 */
// @Entity
public class Book2 {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
