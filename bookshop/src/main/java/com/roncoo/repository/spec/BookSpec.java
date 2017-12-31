/**
 * 
 */
package com.roncoo.repository.spec;

import com.lesson.dto.BookCondition;
import com.roncoo.domain.Book;
import com.roncoo.repository.spec.support.QueryWraper;
import com.roncoo.repository.spec.support.ShopSimpleSpecification;

/**
 * @author zhailiang
 *
 */
public class BookSpec extends ShopSimpleSpecification<Book, BookCondition> {

    public BookSpec(BookCondition condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWraper<Book> queryWraper) {
        addLikeCondition(queryWraper, "name");
    }

}
