package com.roncoo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 所有的测试用例，都会用一些公用的配置（其实，都是注解），都写到这个类里
 * 
 * @author erjun 2017年11月10日 下午10:18:02
 */

@RunWith(SpringRunner.class) // 使用SpringRunner.class 来运行我的测试用例
@SpringBootTest(classes = BookShopApplication.class) // 告诉测试用例，程序的应用入口在哪里
@Transactional // 数据库操作都是事务的，并且，测试完成后，会进行回滚，比方说，删除记录，并不是真正的删除，会进行回滚的
public class BaseTest {

}
