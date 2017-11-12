package com.roncoo.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void testBySort2() {
        // 先按照name降序排序
        // 再按照id号 升序排序，默认是升序排序的
        bookRespository2.findAll(new Sort(new Order(Direction.DESC, "name"), new Order("id")));
    }

}
