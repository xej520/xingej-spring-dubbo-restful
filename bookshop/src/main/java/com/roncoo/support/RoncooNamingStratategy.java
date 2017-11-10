package com.roncoo.support;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

/**
 * 批量更新类对应的表名，字段名；如将所有的表，都加上前缀
 * 
 * 就是将category类名等，批量更新成roncoo_category等，
 * 
 * @author erjun 2017年11月10日 下午11:23:01
 */
// 如何启动这个策略呢？
// 在application.properties里添加配置项
//
public class RoncooNamingStratategy extends ImplicitNamingStrategyComponentPathImpl {

    private static final long serialVersionUID = 1L;

    // 如果使用这个策略的话，
    // 生成的表名，类名，都加上了
    // rc这个前缀了
    @Override
    protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
        return super.toIdentifier("rc_" + stringForm, buildingContext);
    }

}
