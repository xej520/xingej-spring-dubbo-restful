package com.roncoo.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * 
 * Author 与 AuthorInfo 是一对一的关系
 * 
 * 这个表存储的是，作者信息里，不常用的信息属性，如作者的学校，并不常用
 * 
 * @author erjun 2017年11月12日 上午9:08:41
 */

@Entity
public class AuthorInfo extends DomainImpl {

    private String school;

    // 一对一的关系映射
    // 交给Author属性里的info属性来管理维护
    // 放弃了管理关系的权
    // 在表里，不会添加author字段的
    @OneToOne(mappedBy = "info")
    private Author author;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

}
