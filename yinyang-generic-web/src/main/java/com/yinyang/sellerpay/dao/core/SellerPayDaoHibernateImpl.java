package com.yinyang.sellerpay.dao.core;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dhgate.framework.service.dao.impl.AbsractGenericDaoHibernateImpl;

@Repository("dhSellerPayDao")
public class SellerPayDaoHibernateImpl extends AbsractGenericDaoHibernateImpl {

	@Resource(name = "defaultSF")
	private SessionFactory sf;

	@PostConstruct
	protected void injectSessionFactory() {
		super.setSessionFactory(sf);
	}
}
