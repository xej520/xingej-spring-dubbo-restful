package com.lession.spring.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器的作用？
 * 
 * 对所有的Rest API 进行调用前，成功的返回之后，rest Api 调用后，进行处理
 * 
 * @author erjun 2017年11月21日 上午6:10:17
 */
// 将拦截器 声明成组件，
// 但，仅仅声明成spring的bean是不起作用的，因为，默认情况下，没有添加到系统里，必须
// 写一些配置才可以的
// 如：web/config/WebConfig 进行配置

// 此拦截器的作用，就是在调用rest API 之前，之后，打印出一些时间信息
@Component
public class TimeInterceptor implements HandlerInterceptor {

    // afterCompletion:
    // rest API 处理完成的时候，做一些处理
    // 这里的处理完成，包括两种情况：
    // 1、 正确的返回，没有出现任何问题，被调用
    // 2、出现异常，也会被调用的

    // afterCompletion 与 postHandle的区别？
    // 如果API 出现异常后， postHandle，不会被调用的
    // 不管成功，还是失败，都会调用afterCompletion

    // exception:当rest API 处理过程中，抛出异常时，会有值；会将抛出的异常作为参数，传进来
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception exception) throws Exception {

        System.out.println("-----进入afterCompletion---------");

        // 结束时间
        long endTime = new Date().getTime();
        long startTime = (long) request.getAttribute("startTime");

        System.out.println("---服务处理耗时(异常情况)----:\t" + (endTime - startTime) + " 毫秒");
        System.out.println("---异常信息---：\t" + exception);

    }

    // rest API 成功的返回之后，做一些处理
    // modeAndView 保存了，你要返回的视图，以及视图上一些数据信息
    // 前后端 不分离的时候，也就是 服务器端需要渲染页面的时候，才有用；
    // 由于，目前我们做的都是前后端分离的rest API, 因此，modeAndView一般为空
    // 响应一般都会存储到response里

    // 成功调用API后，会调用此方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modeAndView) throws Exception {
        System.out.println("-----进入postHandle---------");

        // 结束时间
        long endTime = new Date().getTime();
        long startTime = (long) request.getAttribute("startTime");

        System.out.println("---服务正确处理耗时----:\t" + (endTime - startTime) + " 毫秒");
    }

    // rest API 在调用之前做一些处理

    // handler : 真正处理http请求的API方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("-----进入preHandle---------");
        // 打印出 处理当前请求的 类的名称
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());

        // 打印出 处理当前请求的 方法的名称
        System.out.println(((HandlerMethod) handler).getMethod().getName());

        // 将当前时间，设置到startTime里
        request.setAttribute("startTime", new Date().getTime());

        // 返回false的话，真正处理的API 方法是不会被调用的
        // 返回true的话，会调用
        return true;
    }

}
