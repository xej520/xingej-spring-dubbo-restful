package com.roncoo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lesson.dto.BookCondition;
import com.lesson.dto.BookInfo;
import com.lesson.service.BookService;
import com.roncoo.aspect.ServiceLog;
import com.roncoo.domain.Book;
import com.roncoo.lock.GlobalLock;
import com.roncoo.repository.BookRepository;
import com.roncoo.repository.BookRepository5;
import com.roncoo.repository.spec.BookSpec;
import com.roncoo.support.QueryResultConverter;

/***
 * 在想拦截的方法里，添加注解，就可以了**服务层是在controller层和Repository层之间的，做连接的，在dao和domain层做转换的**
 * 
 * @author erjun 2017年11月29日 上午6:25:36
 */
@Service("bookService")
@Transactional // 添加事务，这样的话，这个类BookServiceImpl里，所有的public操作，都添加上了事务
// 针对的是数据库操作
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRepository5 bookRepository5;

    @Autowired
    private CacheManager cacheManager;

    // 引入一个job的运行器
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Cacheable("books")
    @Override
    @ServiceLog // 调用这个方法时，打印日志
    public BookInfo getInfo(Long id) {
        Book book = bookRepository.findOne(id);

        BookInfo bookInfo = new BookInfo();

        BeanUtils.copyProperties(book, bookInfo);

        return bookInfo;
    }

    // 通过代码的方式，进行缓存
    public BookInfo getInfo2(Long id) {

        ValueWrapper valueWrapper = cacheManager.getCache("books").get(id);

        if (null == valueWrapper) {
            Book book = bookRepository.findOne(id);

            BookInfo bookInfo = new BookInfo();

            BeanUtils.copyProperties(book, bookInfo);

            cacheManager.getCache("books").put(id, bookInfo);
        }

        return (BookInfo) valueWrapper.get();
    }

    // 表示，从缓存里，清除
    @CacheEvict(cacheNames = "books", allEntries = true)
    @Override
    public void getKongzhiUrlByZhujie(Long id) {
        System.out.println("------测试使用注解来控制URL权限------");

    }

    @Override
    // 只是将某些关键之，作为key，而不是整体组合作为key
    // 这里，只是将condition里的name作为关键之，只有这个关键之变化了，才去重新查询
    // 否则一直在缓存redis里获取
    // #condition.size > 10 每一页 取的条数 大于 10的时候，才缓存，小于10 就不缓存到redis里
    @Cacheable(cacheNames = "books", key = "#condition.name", condition = "#condition.size > 10")
    public Page<BookInfo> query(BookCondition condition, Pageable pageable) {
        Page<Book> pageData = bookRepository5.findAll(new BookSpec(condition), pageable);

        return QueryResultConverter.convert(pageData, BookInfo.class, pageable);
    }

    @Override
    public BookInfo create(BookInfo bookInfo) {
        Book book = new Book();
        book.setName(bookInfo.getName());

        bookRepository.save(book);

        bookInfo.setId(book.getId());

        return bookInfo;
    }

    @Override
    public BookInfo update(BookInfo bookInfo) {

        Book book = bookRepository.findOne(bookInfo.getId());

        book.setName(bookInfo.getName());

        bookRepository.save(book);

        return bookInfo;
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    @Override
    @Scheduled(cron = "0/3 * * * * *") // 每隔3秒，执行一次当前的task方法
    @GlobalLock(path = "/book/task")
    public void task() throws Exception {
        Map<String, JobParameter> param = new HashMap<>();

        param.put("startTime", new JobParameter(new Date()));

        jobLauncher.run(job, new JobParameters());

        // jobExplorer.
    }

    // 可以写到controller层里，给页面进行展示
    // @Autowired
    // private JobExplorer jobExplorer;

}
