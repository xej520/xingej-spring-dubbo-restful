package com.lession.spring.utils;

import org.springframework.context.ApplicationContext;

public class SpringUtils {

    public static ApplicationContext applicationContext;

    // 通过name获取 Bean.
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
