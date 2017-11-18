package com.lession.spring.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
