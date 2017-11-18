package com.roncoo.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.roncoo.BaseTest;
import com.roncoo.domain.Address;
import com.roncoo.domain.Author;

/**
 * 参数校验
 * 
 * @author erjun 2017年11月18日 下午5:29:42
 */
public class AuthorRepositoryTest extends BaseTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void test() {
        Author author = new Author();

        Address address = new Address();

        address.setAddress("北京市望京东辛店");

        author.setAddress(address);

        author.setAge(19);

        // 这里设定的email地址，是不符合要求的
        // 因此，使用了@Email注解，存储时，会自动进行校验的
        // 存储失败的

        // 会抛出这个异常信息
        // ConstraintViolationImpl{interpolatedMessage='不是一个合法的电子邮件地址',
        // propertyPath=email,
        author.setEmail("uuuu#163.com");

        authorRepository.saveAndFlush(author);
    }

}
