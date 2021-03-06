package com.lession.spring.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.annotation.JsonView;
import com.lession.spring.web.support.MyExecption;
import com.lesson.dto.BookCondition;
import com.lesson.dto.BookInfo;
import com.lesson.dto.BookInfo.BookDetailView;
import com.lesson.dto.BookInfo.BookListView;
import com.lesson.service.BookService;

/**
 * 构建BookController
 * 
 * @author erjun 2017年11月18日 下午10:44:43
 */
@RestController
@RequestMapping(value = "/book")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 下面这个引用，是测试 “使用注解，来控制URL权限” 知识点的
    @Autowired
    private BookService bookService;

    private ConcurrentMap<Long, DeferredResult<BookInfo>> map = new ConcurrentHashMap<Long, DeferredResult<BookInfo>>();

    // 这就是一个rest服务，接收一个book请求，然后调用query()方法
    // 返还一个空的集合
    // rest风格，是通过Method来表示 动作的，就是表示，你要干啥
    // RequestMethod.GET 只接收get请求
    @RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
    public List<BookInfo> query() {
        List<BookInfo> books = new ArrayList<>();
        BookInfo bookInfo = new BookInfo();
        bookInfo.setName("天龙八部");

        BookInfo bookInfo2 = new BookInfo();
        bookInfo2.setName("神雕侠侣");

        BookInfo bookInfo3 = new BookInfo();
        bookInfo3.setName("金庸");

        books.add(bookInfo);
        books.add(bookInfo2);
        books.add(bookInfo3);

        return books;
    }

    // 请求时，接收参数
    @RequestMapping(value = "/params", method = RequestMethod.GET)
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
    @RequestMapping(value = "/params2", method = RequestMethod.GET)
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
    @RequestMapping(value = "/params3", method = RequestMethod.GET)
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
    @RequestMapping(value = "/params4", method = RequestMethod.GET)
    // 定义视图
    @JsonView(BookListView.class)
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

    // 分页测试 之 @PageableDefault,修每页有多少条记录
    @RequestMapping(value = "/params5", method = RequestMethod.GET)
    @JsonView(BookListView.class)
    public List<BookInfo> queryByCondition5(BookCondition bookCondition,
            // 将默认值20改成10，每页显示10条记录
            @PageableDefault(size = 10) Pageable pageable) {
        List<BookInfo> books = new ArrayList<>();

        System.out.println("----pageable--第几页--:\t" + pageable.getPageNumber());
        System.out.println("----pageable--每页多少条记录--:\t" + pageable.getPageSize());
        System.out.println("----pageable--排序：:\t" + pageable.getSort());

        books.add(new BookInfo());
        books.add(new BookInfo());
        books.add(new BookInfo());

        return books;
    }

    // 注意，这里 只能用value，不能name="/book/{id}"
    // @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    // 可以使用GetMapping来代替RequestMapping
    @GetMapping(value = "/{id}")
    @JsonView(BookDetailView.class)
    public BookInfo getInfo(@PathVariable Long id) {
        System.out.println("---id----:\t" + id);

        BookInfo bookInfo = new BookInfo();

        bookInfo.setId(id);
        bookInfo.setName("战争与和平");

        return bookInfo;
    }

    // 使用正则表达式，来对URL进行校验
    // 例子的需求是，现在id 的位数为1位，超过1位就报错，如11,12,13 就会报错的
    @GetMapping(value = "/ids/{id:\\d}")
    public BookInfo getInfo2(@PathVariable Long id) {
        System.out.println("---id----:\t" + id);

        BookInfo bookInfo = new BookInfo();

        bookInfo.setId(id);
        bookInfo.setName("战争与和平");

        return bookInfo;
    }

    @PostMapping
    // @RequestBody 查询操作时，不需要添加这个注解
    // 更新操作，插入操作时，需要添加这个注解
    // 不然，无法解析，
    // 你记住就可以了
    public BookInfo create(@RequestBody BookInfo bookInfo) {
        System.out.println("--->:\t" + bookInfo);

        bookInfo.setId(1L);

        return bookInfo;
    }

    // 我是为了测试 ，体现出不同的学习进程，并没有在原始的方法上，操作，才加的
    // "/c2"
    @PostMapping("/c3")
    public BookInfo create3(@Valid @RequestBody BookInfo bookInfo) {
        System.out.println("--->:\t" + bookInfo);

        bookInfo.setId(1L);

        return bookInfo;
    }

    // 我是为了测试 ，体现出不同的学习进程，并没有在原始的方法上，操作，才加的
    // "/c2"
    // BindingResult 用来存储，
    // @Valid校验的结果的，如果失败后，程序不会立即结束
    // 而是，交给程序员去处理，
    // 立即结束还是继续执行，由程序来进行判断
    // 下面的测试用例的目的是，是可以继续执行的
    @PostMapping("/c4")
    public BookInfo create4(@Valid @RequestBody BookInfo bookInfo, BindingResult checkResult) {
        if (checkResult.hasErrors()) {
            checkResult.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println("--->:\t" + bookInfo);

        bookInfo.setId(1L);

        return bookInfo;
    }

    // ---------------------下面是 -----更新操作--------------------------------------

    @PutMapping("/{id}")
    public BookInfo update(@Valid @RequestBody BookInfo bookInfo, BindingResult checkResult) {
        if (checkResult.hasErrors()) {
            checkResult.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        // 接收到请求，打印出
        System.out.println("--->:\t" + bookInfo);

        return bookInfo;
    }
    // ---------------------下面是 -----删除操作--------------------------------------

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        // 接收到请求，打印出
        System.out.println("--->:\t" + id);

    }

    // -------------下面是 -----cookie 和 header操作--------------
    @GetMapping(value = "/cookie/{id}")
    @JsonView(BookDetailView.class)
    public BookInfo getInfo2(@PathVariable Long id, @CookieValue String token) {
        System.out.println("---id----:\t" + id);

        System.out.println("---token----:\t" + token);

        BookInfo bookInfo = new BookInfo();

        bookInfo.setId(id);
        bookInfo.setName("战争与和平");

        return bookInfo;
    }

    // 主要测试 header
    @GetMapping(value = "/cookieAndHeader/{id}")
    @JsonView(BookDetailView.class)
    public BookInfo getInfo3(@PathVariable Long id, @CookieValue String token, @RequestHeader String auth) {
        System.out.println("---id----:\t" + id);

        System.out.println("---token----:\t" + token);
        System.out.println("---auth----:\t" + auth);

        BookInfo bookInfo = new BookInfo();

        bookInfo.setId(id);
        bookInfo.setName("战争与和平");

        return bookInfo;
    }

    // -------------下面是 -----分析---spring boot异常处理逻辑--------------
    @GetMapping(value = "/exception/{id}")
    @JsonView(BookDetailView.class)
    public BookInfo getInfoException(@PathVariable Long id) {

        throw new RuntimeException("service error in server!");

    }

    // 捕获自定义异常，测试
    @GetMapping(value = "/exception2/{id}")
    @JsonView(BookDetailView.class)
    public BookInfo getInfoExceptionNot(@PathVariable Long id) {

        throw new MyExecption("service error in server!");

    }

    // -------------下面是 -----异步处理---http请求--------------

    // 你要明白，哪些语句是 tomcat线程执行的，哪些是springMVC线程的
    // Callable，的请求，与 响应 都是在同一个线程里

    @GetMapping("/async")
    public Callable<BookInfo> getInfo() {
        long startTime = new Date().getTime();

        System.out.println("这是tomcat主线程:\t" + Thread.currentThread().getName() + "开始");
        Callable<BookInfo> result = () -> {

            System.out.println("这是springMVC主线程:\t" + Thread.currentThread().getName() + "线程开始");
            // 模拟服务，执行了1秒钟
            Thread.sleep(1000);
            BookInfo bookInfo = new BookInfo();
            bookInfo.setName("spark");

            System.out.println("这是springMVC主线程:\t" + Thread.currentThread().getName() + "线程结束:\t"
                    + (new Date().getTime() - startTime));

            return bookInfo;
        };

        long endTime = new Date().getTime();
        System.out.println("这是tomcat主线程:\t" + Thread.currentThread().getName() + "结束:\t" + (endTime - startTime));

        return result;

    }

    // 若返回值是DeferredResult 类型的话
    // 请求 与响应 分别在不同的线程里，
    // 有点类似于Netty，或者JDK 多线程里的Future
    // 先给你一个返回值的引用，但是并没有具体的值
    // 不阻塞tomcat主线程，主线程可以做其他事件
    // 跟Callable的最大区别是？

    // 结果的获取，Callable必须等待，而DeferredResult 有了事件通知机制，等有了结果，才真正的返回真正的值
    // 就好像，你烧水时，一直在看着水壶有没有开，这种情况属于 Callable
    // 若，你烧水时，去做其他时间，如看电视了，等水壶开了，会 发起 声音 通知你，我已经做了，你可以用了。DeferredResult就属于这种形式吧
    @GetMapping("/async2/{id}")
    public DeferredResult<BookInfo> getInfoDefferred(@PathVariable Long id) {
        long startTime = new Date().getTime();

        System.out.println("这是tomcat主线程:\t" + Thread.currentThread().getName() + "开始");

        DeferredResult<BookInfo> result = new DeferredResult<>();

        map.put(id, result);

        long endTime = new Date().getTime();
        System.out.println("这是tomcat主线程:\t" + Thread.currentThread().getName() + "结束:\t" + (endTime - startTime));

        return result;
    }

    @SuppressWarnings("unused")
    private void listenMessage(BookInfo bookInfo) {
        map.get(bookInfo.getId()).setResult(bookInfo);
    }

    // -------------下面是 -----第5章-----Spring security--------------

    // -------------下面是 ---展示自己的代码(我们自己)是如何使用第一个机制的(保存和获取用户身份信息的)--------------
    @RequestMapping(value = { "/security/getInfo" }, method = RequestMethod.GET)
    public List<BookInfo> querySpringSecurity() {
        // 获取SecurityContext对象
        // 注意，不会直接跟 SecurityContextHolderStrategy 这个类 打交道的
        // 这个类SecurityContextHolderStrategy被 SecurityContextHolder 包装起来了
        // 其实，内部，还是通过SecurityContextHolderStrategy 类获取的

        SecurityContext securityContext = SecurityContextHolder.getContext();
        // 获取Authentication对象
        Authentication authentication = securityContext.getAuthentication();

        List<BookInfo> books = new ArrayList<>();

        books.add(new BookInfo());
        books.add(new BookInfo());
        books.add(new BookInfo());

        return books;
    }

    // -------------下面是 ----测试---使用注解来控制URL权限--------------
    // 但是 注意
    // ------一般不使用 这种方式 进行 URL权限控制
    // 因为，不建议在方法上，进行控制，
    // 因为，这个方法很有可能被其他模块调用
    // 还有如果使用dubbo的话，也是不能通过的，不在同一个线程里
    // 主要测试 header
    @GetMapping(value = "/zhujie/{id}")
    @JsonView(BookDetailView.class)
    public BookInfo getKongzhiUrlByZhujie(@PathVariable Long id) {
        bookService.getKongzhiUrlByZhujie(id);

        System.out.println("---id----:\t" + id);

        BookInfo bookInfo = new BookInfo();

        bookInfo.setId(id);
        bookInfo.setName("战争与和平");

        return bookInfo;
    }

    // ----------------------测试---由ORM框架到MVC框架的演进-------
    @GetMapping(value = "mvc/{id}")
    public BookInfo getMVC(@PathVariable Long id) {
        BookInfo info = bookService.getInfo(id);

        System.out.println("------ORM框架到----MVC框架----演进成功！---");

        return info;
    }

}
