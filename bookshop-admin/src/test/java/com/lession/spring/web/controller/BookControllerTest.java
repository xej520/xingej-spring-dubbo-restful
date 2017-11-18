package com.lession.spring.web.controller;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void test() {
        fail("Not yet implemented");
    }

}
