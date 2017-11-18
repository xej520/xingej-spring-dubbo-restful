package com.lession.spring.dto;

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
