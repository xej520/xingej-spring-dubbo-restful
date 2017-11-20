package com.lession.spring.web.support;

/**
 * 测试：模拟 自定义的 捕获异常时 定义的类
 * 
 * @author erjun 2017年11月20日 下午11:54:54
 */
public class MyExecption extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MyExecption(String errorMsg) {
        super(errorMsg);
    }

}
