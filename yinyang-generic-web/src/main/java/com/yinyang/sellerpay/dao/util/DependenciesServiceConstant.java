package com.yinyang.sellerpay.dao.util;



/**
 * 
* @Description: 依赖的服务的常量
*
* @author 
* @version 1.0
* @create time 2013-7-30 下午4:41:17
 */
public class DependenciesServiceConstant {

	public final static String DATA_ID = "apsarasrelay.properties";
	
	public final static String GROUP = "com.dhgate.relay.config";
	
	
	public final static String KEY_OF_CATEGORY = "relay.prodbiz.category";
	
	public final static String KEY_OF_PROD = "relay.prodbiz.prod";
	
	public final static String KEY_OF_GOLDPROD = "relay.prodbiz.goldProd";
	
	public final static String KEY_OF_SELLERAUTH = "relay.prodbiz.sellerauth";
	
	public final static String KEY_OF_FRT = "relay.prodbiz.frt";
	
	public final static String KEY_OF_FREIGHT = "relay.prodbiz.freight";
	
	public final static String KEY_OF_SYSDICT = "relay.prodbiz.sysdict";
	
	public final static String KEY_OF_BRANDS = "relay.prodbiz.brands";
	
	public final static String KEY_OF_FOOTBALL = "relay.prodbiz.football";
	
	public final static String KEY_OF_PRODREPEAT = "relay.prodbiz.prodrepeat";
	
	public final static String KEY_OF_MERCHANT = "relay.prodbiz.merchant";
	
	public final static String KEY_OF_ALUBM = "relay.prodbiz.album";
	
	public final static String KEY_OF_SEARCH = "relay.prodbiz.search";
	
	public final static String KEY_OF_STORE = "relay.prodbiz.store";
	
	public final static String KEY_OF_PROMO = "relay.prodbiz.promo";
	
	public final static String KEY_OF_REVIEW = "relay.prodbiz.reView";
	
	public final static String KEY_OF_ORDER = "relay.prodbiz.order";
	
	public final static String KEY_OF_MYSQLDB = "dbmysql.db.product";
	
	/** 是否开启价格体系二期表路由功能。0：未开启，1：开启。*/
	public static String TableRouteStatus_Key = "TableRoute_Status";
	
	/** 开启价格体系二期表路由功能。0：未开启，1：开启。*/
	public static int TableRouteStatus_On = 1;
	/** 关闭价格体系二期表路由功能。0：未开启，1：开启。*/
	public static int TableRouteStatus_Off = 0;
	
	public static final String tableRoute_on = "on";
	public static final String tableRoute_off = "off";
	
	//设置google shopping是否是必填项
	public final static String KEY_OF_GOOGLESHOPPING = "relay.product.googleshopping.required";
	//设置不需要校验首图的卖家supplierId
	public final static String KEY_SUPPLIERID_NOFIRSTIMG = "relay.product.nofirstimg.supplierid";
}
