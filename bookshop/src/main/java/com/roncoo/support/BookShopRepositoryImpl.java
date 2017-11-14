package com.roncoo.support;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * 使用自定义的Repostiory的方法？
 * 
 * 1、继承SimpleJpaRepository
 * 
 * 2、在启动类里，添加注解，@EnableJpaRepositories(repositoryBaseClass =
 * BookShopRepositoryImpl.class)
 * 
 * @author erjun 2017年11月14日 下午9:36:48
 * @param <T>
 */
// Respository的代理类，其实都是基于SimpleJpaRepository类为基准的
// 定义一个泛型
public class BookShopRepositoryImpl<T> extends SimpleJpaRepository<T, Long> {

    public BookShopRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    // 每次调用前，都会打印一条信息
    @Override
    public <S extends T> S save(S entity) {
        System.out.println("---保存了---->:\t" + entity.getClass().getSimpleName());
        return super.save(entity);
    }

}
