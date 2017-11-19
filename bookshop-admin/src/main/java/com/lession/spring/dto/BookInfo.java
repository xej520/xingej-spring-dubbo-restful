package com.lession.spring.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * 
 * 为什么不直接使用book? 而是 bookinfo
 * 
 * 因为，不要将数据库里(domain)层，直接暴露给controller层，
 * 
 * 而是，做一个封装，
 * 
 * 如果以后，某一个表的字段增加或者减少了的话，并不会影响到其他层
 * 
 * 并不会将整个book的字段全部返还给controller层，或者页面，只是将部分返还
 * 
 * 因此，dto里的BookInfo 与 domain里的book 作用是不一样的
 * 
 * BookInfo是封装的是请求的输入输出的数据格式
 * 
 * Book是对数据库的映射
 * 
 * 比方说，这里仅仅将name字段，返还给页面
 * 
 * @author erjun 2017年11月18日 下午10:48:09
 */
public class BookInfo {

    // 视图，是这样的，
    // 有些查询，某个字段可以显示，
    // 而有些查询，某个字段就不能显示
    // 因此，定义了下面的视图；如context字段，返回结果是列表时，就没有必要显示
    // 只是在单个查询详情里，显示此字段

    // 定义了两个视图接口
    // 针对的controller层里不同的查询业务
    // 列表查询业务
    public interface BookListView {
    };

    // 详情查询业务
    public interface BookDetailView extends BookListView {
    };

    private Long id;

    private String name;

    // 注意，单独使用这个注解，是不会生效的，
    // 在使用到这个类的地方，必须加上
    // @Valid注解，才可以的
    @NotBlank
    private String context;

    private Date publishDate;

    // id字段，在BookListView 视图/查询时，显示
    @JsonView(BookListView.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // name字段，在BookListView 视图/查询时，显示
    @JsonView(BookListView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // context字段，在BookDetailView 视图/查询时，显示
    @JsonView(BookDetailView.class)
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "BookInfo [id=" + id + ", name=" + name + ", context=" + context + ", publishDate=" + publishDate + "]";
    }

    @JsonView(BookDetailView.class)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

}
