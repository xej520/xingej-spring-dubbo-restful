package com.roncoo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 测试数据库表之间的关系
 * 
 * 书与类别之间是 多对一的关系，也就是说，多本书 对应一个类别
 * 
 * 使用@ManyToOne注解
 * 
 * @author erjun 2017年11月11日 上午7:06:46
 */
@Entity
public class book {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 书与门类 是 多对一的关系(单向，也就是说只能从书 访问 门类，不能反过来，通过门类访问自己有多少本书的)
    // 在book表中，会产生一个外键，指向category表的主键
    @ManyToOne
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
