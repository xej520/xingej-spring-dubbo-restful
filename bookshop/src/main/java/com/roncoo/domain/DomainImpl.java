package com.roncoo.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 测试继承关系，映射到数据库里，
 * 
 * 数据库里并没有继承的概念，这里是通过java的继承来实现的
 * 
 * @author erjun 2017年11月12日 上午9:41:24
 */

// 在被其他来继承时，这个类里的属性，都会被映射到子类里，
@MappedSuperclass
public class DomainImpl {

    // 表明是主键
    @Id
    // 下面注解：生成主键ID的策略，有4个，默认的是auto，也就是说，根据底层数据库的类型来选择，选择的类型是剩下的3种 的一个
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    // 初始化值，就是创建这个实例对象的时间
    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
