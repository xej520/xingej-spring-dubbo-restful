package com.roncoo.service;

import com.roncoo.dto.BookInfo;

public interface BookService {

    // 由all in one 架构 到 MVC架构演进时，添加的
    // 由于获取书的信息的服务，无论是在book-admin后台管理系统，还是在
    // web(用户访问) 都需要
    // 因此，可以将此实现类放在
    // bookshop里，作为公共的服务
    BookInfo getInfo(Long id);

}
