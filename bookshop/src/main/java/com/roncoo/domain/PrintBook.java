package com.roncoo.domain;

import java.util.Date;

import javax.persistence.Entity;

/**
 * 
 * 继承策略
 * 
 * 纸质书继承Book
 * 
 * @author erjun 2017年11月16日 上午5:51:51
 */
@Entity
public class PrintBook extends Book {
    private Date printDate;

    public Date getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }

}
