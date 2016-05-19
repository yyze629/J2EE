package com.yinyang.sellerpay.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhgate.apsaras.access.spring.annotation.ApsarasService;
import com.dhgate.apsaras.metadata.StackEnum;
import com.dhgate.common.metadata.ResultDto;
import com.dhgate.common.util.Page;
import com.dhgate.framework.service.dao.GenericDao;
import com.dhgate.framework.service.service.impl.AbstractGenericServiceImpl;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.yinyang.sellerpay.dao.api.GenericService;
import com.yinyang.sellerpay.dao.api.SellerPayService;
import com.yinyang.sellerpay.dao.api.dto.PayAccountDTO;
import com.yinyang.sellerpay.dao.api.dto.PayBusinessDTO;
import com.yinyang.sellerpay.dao.api.dto.PayChannelDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowQueryDTO;
import com.yinyang.sellerpay.dao.api.dto.PayOrderDTO;
import com.yinyang.sellerpay.dao.api.dto.PayReconciliationDTO;
import com.yinyang.sellerpay.dao.api.dto.TdLoginDTO;
import com.yinyang.sellerpay.dao.impl.GenericServiceImplTest;
import com.yinyang.sellerpay.dao.impl.bean.SellerPayServiceBean;
import com.yinyang.sellerpay.web.util.MD5Utils;


/**
 * 
 * 
 * @Title: GenericServiceImpl.java
 * @Package com.yinyang.sellerpay.dao.impl
 * @author yinyang@dhgate.com
 * @date 2016年1月19日 上午11:10:57
 * @version V1.0
 */
@Service("genericService")
public class GenericServiceImpl extends AbstractGenericServiceImpl implements GenericService {
	
	private static final Log log = LogFactory.getLogger(GenericServiceImpl.class);
	
	@Autowired
	TdLoginDaoImpl tdLoginDao;
	
	/**
	 * 根据用户名密码查询
	 * @param loginName
	 * @param loginPassword
	 * @return
	 */
	public TdLoginDTO getLogin(String loginName,String loginPassword){
		TdLoginDTO dto = null;
		if(StringUtils.isBlank(loginName) || StringUtils.isBlank(loginPassword)){
			return dto;
		}
		loginPassword = MD5Utils.MD5(loginPassword);
		dto = this.tdLoginDao.getLoginByIdAndName(loginName, loginPassword);
		return dto;
	}
}
