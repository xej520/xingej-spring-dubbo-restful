package com.roncoo.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * 研究多对多的关系
 * 
 * 中间对象
 * 
 * @author erjun 2017年11月12日 上午8:44:20
 */

@Entity
public class BookAuthor extends DomainImpl {

    @ManyToOne
    private Author author;

    @ManyToOne
    private Book book;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
