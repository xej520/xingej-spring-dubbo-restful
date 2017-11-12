package com.roncoo.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.roncoo.BaseTest;

public class BookRespositoryTest2 extends BaseTest {

    @Autowired
    private BookRespository2 bookRespository2;

    // 排序查询
    @Test
    public void testBySort() {

        // 先按照 name属性，进行升序，再按照id进行升序
        bookRespository2.findAll(new Sort(Direction.ASC, "name", "id"));
    }

    // 排序查询
    @Test
    public void testBySort2() {
        // 先按照name降序排序
        // 再按照id号 升序排序，默认是升序排序的
        bookRespository2.findAll(new Sort(new Order(Direction.DESC, "name"), new Order("id")));
    }

    // 分页 查询 练习
    @Test
    public void testByPage() {

        // 根据name属性的， 降序排序
        // 取出第一页的10条信息
        Pageable paramPageable = new PageRequest(0, 10, new Sort(Direction.DESC, "name"));
        bookRespository2.findAll(paramPageable);
    }

}
