package com.yinyang.sellerpay.dao.api;

import java.util.List;

import com.dhgate.common.metadata.ResultDto;
import com.dhgate.common.util.Page;
import com.yinyang.sellerpay.dao.api.dto.TdLoginDTO;

/**
 * 通用接口
 * 
 * @Title: GenericService.java
 * @Package com.yinyang.sellerpay.dao.api
 * @author yinyang@dhgate.com
 * @date 2016年1月19日 上午10:36:30
 * @version V1.0
 */
public interface TdLoginDao {
	
	/**
	 * 根据用户名密码查询
	 * @param loginName
	 * @param loginPassword
	 * @return
	 */
	public TdLoginDTO getLogin(String loginName,String loginPassword);
	
}
