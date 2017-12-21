/**
 * 
 */
package com.lession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author erjun 2017年11月18日 下午10:01:51
 */
@SpringBootApplication
@EnableSwagger2 // 此注解，就可以自动根据controller里的方法，生成文档，就跟marathon的一样一样的
// ui访问地址是:http://localhost:8060/admin/swagger-ui.html
@EnableJdbcHttpSession // 测试 集群session的管理
// 进行由ORM框架到MVC框架，时，
// bookshop的包路径与bookshop-admin的路径不一致，导致
// bookService 自动化注入@Autowired失败，
// 因此，修改自动扫描的路径，
// @ComponentScan 自动扫描的默认路径是 同包，以及子包中声明的组件
@ComponentScan(basePackages = { "com" })
public class BookShopAdminApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(BookShopAdminApplication.class, args);

        String[] allBeanNames = context.getBeanDefinitionNames();

        for (String key : allBeanNames) {
            System.out.println("-------->:\t" + key);
        }
    }

}
