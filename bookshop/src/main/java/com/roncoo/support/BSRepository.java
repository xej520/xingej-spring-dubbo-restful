package com.roncoo.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 对repository包，进行重构
 * 
 * 你有没有发现，这个包里的接口repository，好多都实现了相同的接口么
 * 
 * 这种情况下，你可以进行重构，
 * 
 * 编写一个父类，然后其他子类继承就可以了
 * 
 * 当然，我这里仅仅是对这个包里的部分接口，进行重构，并不是所有的接口
 * 
 * 主要是想，体现出，学习的一个过程，并不是一开始，所有的接口bookRepository，都实现了相同 的接口JPA接口
 * 
 * @author erjun 2017年11月18日 下午5:47:52
 */
// 这个注解是说，告诉spring，就不要为这个接口，实现了动态代理了
// 其实，也就是说，告诉spring不要把这个当成Repository了
@NoRepositoryBean
public interface BSRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
