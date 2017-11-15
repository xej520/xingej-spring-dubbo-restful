package com.roncoo.domain;

import javax.persistence.Entity;

/**
 * 继承策略
 * 
 * 电子书：word，pdf类型
 * 
 * @author erjun 2017年11月16日 上午5:53:45
 */
@Entity
public class Ebook extends Book {

    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
