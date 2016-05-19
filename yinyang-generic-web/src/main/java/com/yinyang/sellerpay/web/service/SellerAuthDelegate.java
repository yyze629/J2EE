package com.yinyang.sellerpay.web.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.dhgate.sellerauth.api.SellerAuthService;
import com.dhgate.sellerauth.dto.MyDhgatePageDto;
import com.dhgate.sellerauth.dto.ServiceFunctionDto;
import com.dhgate.sellerauth.exception.SellerAuthServiceException;
import com.yinyang.sellerpay.web.util.ProductSys;

//@Service("sellerAuthDelegate")
public class SellerAuthDelegate {

	private static final Log log = LogFactory.getLogger(SellerAuthDelegate.class);
	
	//@ApsarasClient(ifName = "com.dhgate.sellerauth.api.SellerAuthService", version = "1.0.0", serviceName = "sellerAuthService", stackEnum = StackEnum.JBOSS_REMOTING)
    /*private SellerAuthService  sellerAuthService = ApsarasServiceFactory.getSellerAuthService();
	
	public MyDhgatePageDto getMyDhgatePageDto(String sessionid,String supplierid ,String suppliersubId,String dhpath,List<String> menuCloseList)throws SellerAuthServiceException{
		MyDhgatePageDto result = null;
		result = sellerAuthService.getMyDhgatePageDto( sessionid, supplierid , suppliersubId, dhpath, menuCloseList);
		return result;
	}
	public MyDhgatePageDto getMyDhgatePageDtoProd(String sessionid,String supplierid ,String suppliersubId,String dhpath,List<String> menuCloseList)throws SellerAuthServiceException{
		MyDhgatePageDto result = null;
		result = sellerAuthService.getMyDhgatePageDtoProd( sessionid, supplierid , suppliersubId, dhpath, menuCloseList);
		return result;
	}
	
	public boolean isUrlpathLimit(String supplierid,String urlPath){
		boolean isUrlpathLimit = false;
		isUrlpathLimit = sellerAuthService.isUrlpathLimit(supplierid, urlPath, null);
		return isUrlpathLimit;
	}
	
	public boolean hasAuthorityModel(String supplierid,String modelid)throws SellerAuthServiceException{
		boolean result = false;
		result = sellerAuthService.hasAuthorityModel(supplierid,modelid);
		return result;
	}
	
	*//**
	 * 是否具有快速上架权限。
	 * @param supplierid
	 * @param appId
	 *//*
	public boolean hasQuickUpAuthority(String supplierid){
		boolean hasQuickUpAuthority = false;
		ServiceFunctionDto  functionDto = sellerAuthService.getSupplierService(supplierid, ProductSys.ServiceFunctionAPP_QUICKUP);
		String allPickupNumStr = functionDto.getFunctionValue(ProductSys.ServiceFunctionAPP_QUICKUP);
		if(StringUtils.isNotBlank(allPickupNumStr)){
			if("DEFAULT".equals(allPickupNumStr.toUpperCase().trim())){
				allPickupNumStr = "200";
			}
			try {
				Long allPickupNum = Long.valueOf(allPickupNumStr);
				if(allPickupNum > 0){
					hasQuickUpAuthority = true;
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				log.error(e);
			}
		}

		return hasQuickUpAuthority;
	}
	
	*//**
	* 判断是否是学生卖家
	* @param supplierid
	* @return
	*//*
	public boolean isStudentSupplier(String supplierid){
		boolean isStudentSupplier = false;
		if(StringUtils.isBlank(supplierid)){
			return isStudentSupplier;
		}
		isStudentSupplier = this.sellerAuthService.isStudentSupplier(supplierid);
		return isStudentSupplier;
	}*/

	
}
