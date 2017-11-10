package com.roncoo.domain.One2Many;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * 在数据库已经生成好的表，以及字段，都不会改变的， 因此，如果想要改变这个字段的话，只能删除，重新创建
 * 
 * 演示：单向 的一对多的关系; 单向关系，只能由一个方向执行
 * 
 * 这种关系，要建立的门类这个类里
 * 
 * @author erjun 2017年11月11日 上午07:20:30
 */

// @Entity 这里注释掉了，因为单向的 一对多关系 最好不要使用，
// 因为，会多产生一个表，来进行维护
// 最佳推荐，是，使用双向的多对一关系
public class Category2 {

    // 表明是主键
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Transient
    private String alias;

    // 一对多的关系，门类与书之间的关系
    @OneToMany
    private List<Book2> books;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<Book2> getBooks() {
        return books;
    }

    public void setBooks(List<Book2> books) {
        this.books = books;
    }

}
