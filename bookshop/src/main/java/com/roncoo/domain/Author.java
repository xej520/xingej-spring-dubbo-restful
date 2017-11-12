package com.roncoo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author erjun 2017年11月11日 上午5:49:08
 */
@Entity
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 年龄，你最多活到100多岁，占用3位
    // 默认是11位，资源浪费，因此，这里进行了限定
    @Column(columnDefinition = "INT(3)")
    private int age;

    // 默认存储到数据库，如20171111 0552
    // 一般情况下，生日，不会说几点几分的，只需要年月日即可了。
    // TemporalType.DATE 转换到数据库里，类型就不是DATETIME了，而是DATE
    @Temporal(TemporalType.DATE)
    private Date birthday;

    // 将枚举类型，转换成 string类型，存储到数据库里
    @Enumerated(EnumType.STRING)
    private Sex sex;

    // --------------------内嵌映射的介绍------------------
    // 内嵌对象的映射
    // 会一次性映射Address类里的所有字段
    // --------------------------------------
    // 地址与作者的生命周期是一样的，也就是说，一个作者对应一个地址，作者存在，地址就存在，
    // 此种情况下，就可以使用内嵌映射
    // 但是，地址，与书就不能用，因为生命周期不一样，作者挂了，书还是存在的
    @Embedded
    private Address address;

    // ---------------下面介绍，如何映射----集合-------------------
    // 针对集合，映射到数据库里，会单独映射成一个hobbies表
    @ElementCollection
    private List<String> hobbies;

    // 不光可以映射成普通类型List<String>， 成普通String类型
    // 也可以映射成 自定义的对象，如
    @ElementCollection
    private List<Address> addresses; // 一个作者，可以有多个地址，家里的地址，公司的地址

    // 作者与书 是多对多的映射
    // 是说，
    // 一个Author作者可以些多本书，
    // 是由中间对象里的author属性来维护的
    @OneToMany(mappedBy = "author")
    // 当你调用getBooks时，获取到的列表里排序是按照
    // book里name的属性 升序排序的
    // 默认是按照主键ID进行升序排序的
    @OrderBy("book.name ASC")
    private List<BookAuthor> books;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<BookAuthor> getBooks() {
        return books;
    }

    public void setBooks(List<BookAuthor> books) {
        this.books = books;
    }

}
