/**
 * 
 */
package com.lession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author erjun 2017年11月18日 下午10:01:51
 */
@SpringBootApplication
@EnableSwagger2 // 此注解，就可以自动根据controller里的方法，生成文档，就跟marathon的一样一样的
// ui访问地址是:http://localhost:8060/admin/swagger-ui.html
public class BookShopAdminApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(BookShopAdminApplication.class, args);
    }

}
