package com.lession.spring.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.lession.BookShopAdminApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookShopAdminApplication.class)
public class FileControllerTest {

    @Autowired
    private WebApplicationContext wac;

    // 用例模拟mvc的，也就是模拟tomcat的
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    // -------------下面是 -----文件上传---------
    @Test
    public void whenUploadSuccess() throws Exception {

        // MockMultipartFile(A,B,C,D)
        // A：不是文件的名字，而是我们发起这样的一个请求时候，文件在请求里第一个参数的名称
        // B：text 才是文件名称
        String result = mockMvc
                .perform(fileUpload("/file/upload").file(new MockMultipartFile("file", "textFile.txt",
                        "multipart/form-data", "hello file up load".getBytes())))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println("----文件上传测试----:\t" + result);
    }

}
