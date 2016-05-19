package com.yinyang.sellerpay.dao.core;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;

public class MysqlDailetExtend extends MySQL5InnoDBDialect {

	public MysqlDailetExtend() {
		super();
		registerFunction("date_add", new SQLFunctionTemplate(Hibernate.DATE,
				"date_add(?1, INTERVAL ?2 ?3)"));
	}
}
