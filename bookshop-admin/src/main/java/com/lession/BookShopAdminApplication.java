/**
 * 
 */
package com.lession;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author erjun 2017年11月18日 下午10:01:51
 */
@SpringBootApplication
@EnableSwagger2 // 此注解，就可以自动根据controller里的方法，生成文档，就跟marathon的一样一样的
// ui访问地址是:http://localhost:8060/admin/swagger-ui.html
// @EnableJdbcHttpSession // 测试 集群session的管理
// 进行由ORM框架到MVC框架，时，
// bookshop的包路径与bookshop-admin的路径不一致，导致
// bookService 自动化注入@Autowired失败，
// 因此，修改自动扫描的路径，
// @ComponentScan 自动扫描的默认路径是 同包，以及子包中声明的组件
@ComponentScan(basePackages = { "com" })

@ImportResource("classpath:consumer.xml")
@EnableCaching // 启动缓存
@EnableScheduling // 启动定时任务
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

    @Bean
    public CacheManagerCustomizer<RedisCacheManager> customizer() {
        return new CacheManagerCustomizer<RedisCacheManager>() {

            @Override
            public void customize(RedisCacheManager cacheManager) {

                // 方式一：个性化指定每一类，过期的时间，
                Map<String, Long> map = new HashMap<String, Long>();

                map.put("books", 1000L);// books类的缓存过期时间是1000秒
                map.put("users", 100L);// users类的缓存过期时间是100秒
                map.put("shopInfo", 200L);// shopInfo类的缓存过期时间是200秒
                // -----请注意，你是不能单独指定某一个id的缓存过期时间的，除非你自己去实现XXXCacheManger的
                cacheManager.setExpires(map);

                // 方式二：设置所有的缓存，时间一直
                // 往redis存储的缓存，过期时间都是10秒钟
                // cacheManager.setDefaultExpiration(10);
            }
        };
    }

}
