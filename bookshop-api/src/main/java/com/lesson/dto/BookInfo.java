package com.lesson.dto;

import java.util.Date;

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

    private String context;

    private Date publishDate;

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

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

}
