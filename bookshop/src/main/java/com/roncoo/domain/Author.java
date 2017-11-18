package com.roncoo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author erjun 2017年11月11日 上午5:49:08
 */
@Entity
public class Author extends DomainImpl {

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

    // Author 与 AuthorInfo 是一对一的关系映射
    @OneToOne
    private AuthorInfo info;

    // 参数校验吧，就是
    // 在存储到数据库的时候，自动会校验一次，是否符合你指定的规则
    // 这里，每次都会校验是否符合email规则的
    // 不符合要求的话，存储失败的
    @Email
    @NotBlank // 这个注解不会向@Column的注解一样，
    // Valodator的注解不会影响 数据库表的定义，也就是说，虽然，你这里定义了，此email字段不能
    // 为空，但是，数据库表的属性里，并没有进行设定。
    // 这个注解，只是在程序运行时，将要往数据库里存的时候，才真正的校验是不是为空
    // @Column 注解，会真正的影响 你的数据库表的定义，你可以从数据表的属性里，看出来是不是定义了，
    private String email;

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

    public AuthorInfo getInfo() {
        return info;
    }

    public void setInfo(AuthorInfo info) {
        this.info = info;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
