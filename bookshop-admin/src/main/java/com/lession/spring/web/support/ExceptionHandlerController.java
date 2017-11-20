package com.lession.spring.web.support;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理
 * 
 * @author erjun 2017年11月20日 下午11:15:29
 */
@RestControllerAdvice
public class ExceptionHandlerController {

    // 这个注解的意思是
    // 这个方法 是用来处理中的系统中的某个异常的
    @ExceptionHandler(RuntimeException.class)
    // 当系统中，某一处发生了运行时异常，异常就会作为参数，传进来
    public Map<String, Object> handleException(RuntimeException exception) {

        Map<String, Object> result = new HashMap<>();

        // json里，会有两个属性，一个result，一个errorMsg
        result.put("result", "failed");
        result.put("errorMsg", exception.getMessage());

        // 由于@RestControllerAdvice 这个注解的作用
        // 直接会将result对象，转换成json格式，返回给页面
        return result;
    }

}
