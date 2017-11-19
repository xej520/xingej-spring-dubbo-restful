package com.lession.spring.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lession.spring.dto.BookCondition;
import com.lession.spring.dto.BookInfo;

/**
 * 构建BookController
 * 
 * @author erjun 2017年11月18日 下午10:44:43
 */
@RestController
public class BookController {

    // 这就是一个rest服务，接收一个book请求，然后调用query()方法
    // 返还一个空的集合
    // rest风格，是通过Method来表示 动作的，就是表示，你要干啥
    // RequestMethod.GET 只接收get请求
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public List<BookInfo> query() {
        List<BookInfo> books = new ArrayList<>();

        books.add(new BookInfo());
        books.add(new BookInfo());
        books.add(new BookInfo());

        return books;
    }

    // 请求时，接收参数
    @RequestMapping(value = "/book/params", method = RequestMethod.GET)
    // 参数名称，规定死了，
    // URL里的请求参数名称 与 接收参数里的 参数名称 的关系
    // 如果没有使用@RequestParam 注解的话，那么必须一致；
    // name，请求是name, 这里进行接收时也必须是name， 不过不是name的话，就接收不到了
    // 如果使用注解的话，可以不一样的
    public List<BookInfo> queryAndParams(String name) {// 参数 直接 接收的
        List<BookInfo> books = new ArrayList<>();

        System.out.println("----name----:\t" + name);

        books.add(new BookInfo());
        books.add(new BookInfo());
        books.add(new BookInfo());

        return books;
    }

    // 请求时，接收参数
    @RequestMapping(value = "/book/params2", method = RequestMethod.GET)
    // 如果此时，你不想叫name了，也可以使用
    // @RequestParam
    // 在这个注解里，你用name 或者 value 都是一样的，他俩等价
    // defaultValue 是说，如果，请求时，没有传入name参数的话，这里会自动给默认值的，默认值会自动复制给bookName的
    // request 默认是true, 默认是必须添加参数的
    public List<BookInfo> queryAndParams2(
            @RequestParam(name = "name", defaultValue = "hello, beijing") String bookName) {
        List<BookInfo> books = new ArrayList<>();

        System.out.println("----name----:\t" + bookName);

        books.add(new BookInfo());
        books.add(new BookInfo());
        books.add(new BookInfo());

        return books;
    }

    // 测试的是
    // controller层里，
    // 参数很多的 的情况下，将这些参数，进行封装成一个类
    // 请求方/调用方/postman/页面等请求时，还是不变，一个参数一个参数的传递。
    @RequestMapping(value = "/book/params3", method = RequestMethod.GET)
    public List<BookInfo> queryByCondition(BookCondition bookCondition) {
        List<BookInfo> books = new ArrayList<>();

        System.out.println("----name----:\t" + bookCondition.getName());
        System.out.println("----CategoryId----:\t" + bookCondition.getCategoryId());

        books.add(new BookInfo());
        books.add(new BookInfo());
        books.add(new BookInfo());

        return books;
    }

    // 测试的是
    // controller层里，
    // 参数很多的 的情况下，将这些参数，进行封装成一个类
    // 请求方/调用方/postman/页面等请求时，还是不变，一个参数一个参数的传递。
    @RequestMapping(value = "/book/params4", method = RequestMethod.GET)
    public List<BookInfo> queryByCondition(BookCondition bookCondition, Pageable pageable) {
        List<BookInfo> books = new ArrayList<>();

        System.out.println("----pageable--第几页--:\t" + pageable.getPageNumber());
        System.out.println("----pageable--每页多少条记录--:\t" + pageable.getPageSize());
        System.out.println("----pageable--排序：:\t" + pageable.getSort());

        books.add(new BookInfo());
        books.add(new BookInfo());
        books.add(new BookInfo());

        return books;
    }

}
