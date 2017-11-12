package com.roncoo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 研究多对多的关系
 * 
 * 中间对象
 * 
 * @author erjun 2017年11月12日 上午8:44:20
 */

@Entity
public class BookAuthor {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Book book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
