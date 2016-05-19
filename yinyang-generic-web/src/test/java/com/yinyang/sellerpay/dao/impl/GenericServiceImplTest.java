package com.yinyang.sellerpay.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.dhgate.common.metadata.ResultDto;
import com.dhgate.common.util.Page;
import com.dhgate.merchant.api.MerchantService;
import com.dhgate.merchant.dto.SellerPageLoginDTO;
import com.yinyang.sellerpay.dao.api.GenericService;
import com.yinyang.sellerpay.dao.api.dto.TdLoginDTO;

/**
 * 通用接口测试
 * 
 * @Title: GenericService.java
 * @Package com.yinyang.sellerpay.dao.api
 * @author yinyang@dhgate.com
 * @date 2016年1月19日 上午10:36:30
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class GenericServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Resource(name = "GenericService")
	GenericService genericService;
	
	@Test
	public void getLogin(){
		
		String loginName = "";
		String loginPassword = "";
		TdLoginDTO dto = genericService.getLogin(loginName, loginPassword);
		if(dto != null)
			System.out.println(dto.toString());
	}
}
