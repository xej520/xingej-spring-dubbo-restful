package com.lesson.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lesson.dto.BookCondition;
import com.lesson.dto.BookInfo;

public interface BookService {

    void getKongzhiUrlByZhujie(Long id);

    // 由all in one 架构 到 MVC架构演进时，添加的
    // 由于获取书的信息的服务，无论是在book-admin后台管理系统，还是在
    // web(用户访问) 都需要
    // 因此，可以将此实现类放在
    // bookshop里，作为公共的服务
    BookInfo getInfo(Long id);

    Page<BookInfo> query(BookCondition condition, Pageable pageable);

    BookInfo create(BookInfo bookInfo);

    BookInfo update(BookInfo bookInfo);

    void delete(Long id);

    // 定时任务处理
    // 定时任务，必须是无返回值的，无输入参数的
    void task() throws Exception;

}
