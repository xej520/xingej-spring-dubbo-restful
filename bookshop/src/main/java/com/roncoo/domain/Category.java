package com.roncoo.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * 在数据库已经生成好的表，以及字段，都不会改变的， 因此，如果想要改变这个字段的话，只能删除，重新创建
 * 
 * @author erjun 2017年11月10日 下午11:13:30
 */

// table注解，可以修改Category类在数据库的表名
// 这里修改了表名，会覆盖application.properties里 命名规则的全局配置的
// @Table(name = "roncoo_category")

// 此注解，表明，告诉jpa，是要在数据库里生成表的
@Entity
public class Category {

    // 表明是主键
    @Id
    // 下面注解：生成主键ID的策略，有4个，默认的是auto，也就是说，根据底层数据库的类型来选择，选择的类型是剩下的3种 的一个
    @GeneratedValue
    private Long id;

    // 不加注解，默认也会在数据库里，添加属性的
    // 其实，会有默认的注解，@Basic属性的
    // nullable不能为空，unique必须唯一
    // @Column(name = "roncoo_name", length = 10, nullable = false, unique =
    // true)
    private String name;

    // 这个注解，告诉jpa，下面这个属性，不需要在数据库里生成字段的
    // 也就是说，不会同步到数据库的
    @Transient
    private String alias;

    // 一对多的关系
    // mappedBy="category" 这个属性的意思，是什么呢？
    // 目前是双向的多对一的关系，多的一方式book，一的一方是Category
    // 如果不加这个属性的话，双向都在维护这个关系，也就是说，在一对多的一方Category还会创建一个额外的表
    // 来维护这个关系，因此，我们希望不要维护了，交给多的一方去维护这个关系，这样的话，就不会产生额外的表了
    // "category" 表示的是在多一个一方，由那个属性来维护这个关系
    // cascade 表示级联操作，比方说，你删了目录时，同样会删除书的记录
    @OneToMany(mappedBy = "category")
    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
