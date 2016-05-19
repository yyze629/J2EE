package com.yinyang.sellerpay.web.service;

import com.dhgate.apsaras.access.ServiceProxyFactory;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.dhgate.merchant.api.MerchantService;
import com.dhgate.sellerauth.api.SellerAuthService;
import com.yinyang.sellerpay.dao.api.SellerPayService;

public class ApsarasServiceFactory {
	
	private static final Log log = LogFactory.getLogger(ApsarasServiceFactory.class);
	
	/*public static SellerAuthService getSellerAuthService() {
		Object obj  = null;
		try {
        	obj = ServiceProxyFactory.getServiceProxy(SellerAuthService.class, "1.0.1");
        	
        } catch (Exception e) {
        	log.error(e);
            
        }
        SellerAuthService service = (SellerAuthService) obj;
		if (service == null) {
			throw new RuntimeException("cann't get service SellerAuthService ");
		}
		return service;
	}
	
	
	public static MerchantService getMerchantService() {
		Object obj  = null;
		try {
        	obj = ServiceProxyFactory.getServiceProxy(MerchantService.class, "2.0.0");
        	
        } catch (Exception e) {
        	log.error(e);
            
        }
		MerchantService service = (MerchantService) obj;
		if (service == null) {
			throw new RuntimeException("cann't get service MerchantService ");
		}
		return service;
	}
	
	public static SellerPayService getSellerPayService() {
		Object obj  = null;
		try {
        	obj = ServiceProxyFactory.getServiceProxy(SellerPayService.class, "1.0.0");
        	
        } catch (Exception e) {
        	log.error(e);
            
        }
		SellerPayService service = (SellerPayService) obj;
		if (service == null) {
			throw new RuntimeException("cann't get service SellerPayService ");
		}
		return service;
	}*/
	
	
}
