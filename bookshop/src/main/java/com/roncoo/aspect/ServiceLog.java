/**
 * 
 */
package com.roncoo.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhailiang
 *
 */
// 这个注解的使用范围是，类，方法
@Target({ ElementType.TYPE, ElementType.METHOD })
// 这个类的作用时间是，运行期间
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {

}
