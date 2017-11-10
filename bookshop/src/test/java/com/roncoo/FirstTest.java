package com.roncoo;

import static org.junit.Assert.fail;

import org.junit.Test;

public class FirstTest extends BaseTest {
    // 注意，所有的测试用例，都是public的，void 无返回值类的
    @Test
    public void test() {
        fail("Not yet implemented");
    }

    @Test
    public void hello() {
        System.out.println("hello, spring dubbo rest");
    }

}
