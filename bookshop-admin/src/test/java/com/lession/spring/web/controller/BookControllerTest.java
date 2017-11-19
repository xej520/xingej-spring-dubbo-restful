package com.lession.spring.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
        mockMvc.perform(MockMvcRequestBuilders.get("/book").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    // 请求时，添加 参数
    @Test
    public void whenQuerySuccessAndParams() throws Exception {
        // name 请求参数，controller层里，接收时，也必须是name
        // 请求 与 接收 必须保持一致
        mockMvc.perform(MockMvcRequestBuilders.get("/book/params").param("name", "hello, restfull")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    // 请求时，添加 参数, 接收方，使用@RequestMapping注解
    @Test
    public void whenQuerySuccessAndParams2() throws Exception {
        // name 请求参数，controller层里，接收时，也必须是name
        // 请求 与 接收 必须保持一致
        // name是请求参数名称，你用这个发送请求，那么，接收方，也必须，以这个参数来接收啊，
        mockMvc.perform(MockMvcRequestBuilders.get("/book/params2").param("name", "hello, restfull")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    // 请求时，添加 参数, 接收方，使用@RequestMapping注解
    @Test
    public void whenQuerySuccessAndParams3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/params3").param("name", "hello, restfull")
                .param("categoryId", "2").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

}
