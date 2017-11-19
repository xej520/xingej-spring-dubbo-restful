package com.lession.spring.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.lession.BookShopAdminApplication;

/**
 * 测试驱动开发
 * 
 * -------> 传统开发顺序：先弄需求，开发，测试，上线
 * 
 * -------> 测试驱动开发/测试先行：先写测试用例，然后再写代码；
 * 
 * 测试驱动开发的好处？
 * 
 * 拿到需求之后，可以以使用者的角度去思考你的代码; 如果直接开发，会用开发者的角度去思考你的代码，
 * 
 * 因此，最好先写测试用例，这样会使得你的代码，更好用的代码
 * 
 * @author erjun 2017年11月18日 下午10:22:00
 */

// 使用SpringRunner.class 来运行我的测试用例
@RunWith(SpringRunner.class)
// @SpringBootTest 是说，测试的类型 是 springboot的，入口是BookShopAdminApplication.class
@SpringBootTest(classes = BookShopAdminApplication.class)
public class BookControllerTest {

    @Autowired
    private WebApplicationContext wac;

    // 用例模拟mvc的，也就是模拟tomcat的
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(get("/book").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    // 请求时，添加 参数
    @Test
    public void whenQuerySuccessAndParams() throws Exception {
        // name 请求参数，controller层里，接收时，也必须是name
        // 请求 与 接收 必须保持一致
        mockMvc.perform(get("/book/params").param("name", "hello, restfull").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3));
    }

    // 请求时，添加 参数, 接收方，使用@RequestMapping注解
    @Test
    public void whenQuerySuccessAndParams2() throws Exception {
        // name 请求参数，controller层里，接收时，也必须是name
        // 请求 与 接收 必须保持一致
        // name是请求参数名称，你用这个发送请求，那么，接收方，也必须，以这个参数来接收啊，
        mockMvc.perform(get("/book/params2").param("name", "hello, restfull").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3));
    }

    // 请求时，添加 参数, 接收方，使用@RequestMapping注解
    @Test
    public void whenQuerySuccessAndParams3() throws Exception {
        mockMvc.perform(get("/book/params3").param("name", "hello, restfull").param("categoryId", "2")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    // 分页 测试
    // 因为Pageable 有默认值，因此，pageable可以不添加参数也可以的
    @Test
    public void whenQuerySuccessAndParams4() throws Exception {
        mockMvc.perform(get("/book/params4").param("name", "hello, restfull").param("categoryId", "2")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    // 分页 测试
    // 使用了注解@PageableDefault,来修改默认的参数，如每页有多少条记录
    @Test
    public void whenQuerySuccessAndParams5() throws Exception {
        mockMvc.perform(get("/book/params5").param("name", "hello, restfull").param("categoryId", "2")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    // 分页 测试
    // 使用了注解@PageableDefault,来修改默认的参数，如每页有多少条记录

    // -----*****注意啦*****-----------
    // param("sort", "name, desc") 这是错误的写法，name 后面的逗号，不能有空格，正确的写法是
    // param("sort", "name,desc") 这是正确的写法
    // -----*****注意啦*****-----------

    @Test
    public void whenQuerySuccessAndParams5a() throws Exception {
        String result = mockMvc
                .perform(get("/book/params5").param("name", "hello, restfull").param("page", "1") // 查询2页的内容
                        .param("size", "5") // 每页显示5条记录
                        .param("sort", "name,desc", "createTime,asc") // 根据name字段，进行降序排序;可以对多个字段，进行排序
                        .param("categoryId", "2").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3)).andReturn().getResponse()
                .getContentAsString();

        System.out.println("-----result-----:\t" + result);
    }

    @Test
    public void whenQueryInfo() throws Exception {
        // 请求ID为1的 book内容
        // $.name 是说，返回的json 格式里，有一个name属性，
        String result = mockMvc.perform(get("/book/1").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("战争与和平")).andReturn().getResponse()
                .getContentAsString();

        System.out.println("---->:\t" + result);
    }

    // 测试错误情况

    @Test
    public void whenQueryFailed() throws Exception {
        mockMvc.perform(get("/book/ids/10").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());

    }

    // 创建测试
    @Test
    public void whenCreateSuccess() throws Exception {

        String context = "{\"id\":1,\"name\":\"战争与和平\",\"context\":null}";

        mockMvc.perform(post("/book").content(context).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1"));
    }

    // 创建测试
    // 时间问题，指定的时间是
    // 2017-11-19 ,转换后，就称为了Sun Nov 19 08:00:00 CST 2017

    // 变成8点了，应该是0点
    // 因为跟标准时间，有8个小时的时差
    // 如何解决呢？
    // 需要在application.properties配置文件里，添加一个参数
    // spring.jackson.time-zone = GMT+8
    @Test
    public void whenCreateSuccess2() throws Exception {

        String context = "{\"id\":1,\"name\":\"战争与和平\",\"context\":null,\"publishDate\":\"2017-11-19\"}";

        mockMvc.perform(post("/book").content(context).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1"));
    }

    // 测试，校验参数
    // @NotBlank 与 @Valid
    // 在组合成BookInfo之前，先进行校验，校验失败后，就直接返回了，程序不会往下进行了
    @Test
    public void whenCreateSuccess3() throws Exception {

        String context = "{\"id\":1,\"name\":\"战争与和平\",\"context\":null,\"publishDate\":\"2017-11-19\"}";

        mockMvc.perform(post("/book/c3").content(context).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1"));
    }

    // 测试，校验参数
    // @NotBlank 与 @Valid
    // 在组合成BookInfo之前，先进行校验，校验失败后，就直接返回了，程序不会往下进行了
    @Test
    public void whenCreateSuccess4() throws Exception {

        String context = "{\"id\":1,\"name\":\"战争与和平\",\"context\":null,\"publishDate\":\"2017-11-19\"}";

        mockMvc.perform(post("/book/c4").content(context).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1"));
    }

}
