package com.lession.spring.dto;

/**
 * 参数映射，也就是，将多个请求参数，映射成一个类
 * 
 * 测试，如果请求参数有很多的情况下，如何简洁的表示，或者使用？
 * 
 * 不然的话，api的参数，你要写很多，就很麻烦，因此，
 * 
 * 将这些参数，进行封装一个类里
 * 
 * @author erjun 2017年11月19日 上午8:07:10
 */
public class BookCondition {

    // 这里的参数名称，必须跟请求方是一致的
    private String name;

    private Long categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

}
