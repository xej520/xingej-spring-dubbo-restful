package com.roncoo.aspect;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
@Aspect
public class ServiceLogAspect {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ServiceLogAspect.class);

    // 切入点的声明，方式一
    // 参数说明：
    // 第一个*， 表示，任何的方法
    // service..* :表示，service包的任何包，包括子包，
    // 如果是这种形式，service.* 就不包括子包了
    // .* 表示，任何类
    // (..)表示，传入的任何参数

    // 是将所有的方法都打印出来，批量处理
    @Around("execution(* com.roncoo.service..*.*(..))")
    public Object logServiceInvoke(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("方法被调用啊");
        return doLog(pjp);
    }

    // 切入点的声明，方式二
    // 打印个别日志，非全部日志
    // 如果那个方法被调用时，想打印日志的话，就可以将@ServiceLog注解，添加到那个方法上就可以了
    // 如bookshop工程的BookServiceImpl类里，getInfo方法，进行设置； @ServiceLog // 调用这个方法时，打印日志
    @Around("@annotation(com.roncoo.aspect.ServiceLog)")
    public Object logServiceInvoke2(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("方法被调用啊2");
        return doLog(pjp);
    }

    protected Object doLog(ProceedingJoinPoint pjp) throws Throwable {
        // 调用的是isInfoEnabled(),说明，只有是info级别时，才打印这个信息
        // LOGGER.isDebugEnable()说明，只有是debug级别的时候，才打印信息
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("*****调用服务:" + pjp.getSignature().toLongString() + "*****");

            // getArgs表示，调用时，传入的参数，是一个数组类型
            for (Object arg : pjp.getArgs()) {
                printObj(arg, "服务参数:");
            }

            try {
                // pjp.proceed() 是说，实际真正的去调用被切入的方法，执行完成后，会有一个返回结果
                Object retVal = pjp.proceed();

                printObj(retVal, "返回结果:");

                return retVal;
            } catch (Throwable e) {
                LOGGER.info("抛出异常", e);
                throw e;
            } finally {
                LOGGER.info("*****调用服务结束*****");
            }
        }
        return pjp.proceed();
    }

    /**
     * 记录参数
     * 
     * @param arg
     * @param prefix
     * @author zhailiang
     * @since 2016年12月19日
     */
    @SuppressWarnings("rawtypes")
    void printObj(Object arg, String prefix) {
        if (arg != null) {
            // 如果传过来的是数组，就走这个分支
            if (arg.getClass().isArray()) {
                if (ArrayUtils.isNotEmpty((Object[]) arg)) {
                    Object[] args = (Object[]) arg;
                    for (Object object : args) {
                        printObj(object, prefix);
                    }
                }
                // 如果是集合的话 ，就走这个分支
            } else if (arg instanceof Collection) {
                if (CollectionUtils.isNotEmpty((Collection) arg)) {
                    Collection collection = (Collection) arg;
                    for (Object object : collection) {
                        printObj(object, prefix);
                    }
                }
            }

            // 如果是基本类型，或者包装类，直接打印出来，就可以了
            if (ClassUtils.isPrimitiveOrWrapper(arg.getClass())) {
                LOGGER.info(prefix + arg.toString());
            } else if (arg instanceof String) {
                LOGGER.info(prefix + (String) arg);
            } else {
                // 通过反射，打印出来
                LOGGER.info(prefix + ReflectionToStringBuilder.toString(arg));
            }
        } else {
            LOGGER.info(prefix + " null");
        }
    }

}
