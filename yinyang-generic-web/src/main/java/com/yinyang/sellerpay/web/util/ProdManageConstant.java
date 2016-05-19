package com.yinyang.sellerpay.web.util;

public class ProdManageConstant {
	
	/** 价格体系二期戴梦得配置-配置组*/
	public static String PriceV2Diamond_Group = "com.dhgate.product.table.route";
	/** 价格体系二期戴梦得配置-配置ID*/
	public static String PriceV2Diamond_DataId = "PriceVII.properties";
	
	/** 是否开启价格体系二期的接口。0：未开启，1：开启。*/
	public static String InterfacePriceV2Status_Key = "InterfacePriceV2_Status";
	
	/** 产品列表页中，是否显示价格体系二期正式版本（版本分为：过渡期版本(20日版本)、正式版本(27日版本)）。0：过渡期版本，1：正式版本。*/
	public static String PriceV2PageVersion_Normal_Key = "PriceV2PageVersion_Normal";
	
	/** 是否开启价格体系二期的接口。0：未开启，1：开启。*/
	public static int InterfacePriceV2Status_on = 1;
	/** 是否开启价格体系二期的接口。0：未开启，1：开启。*/
	public static int InterfacePriceV2Status_off = 0;
	
	/** 产品列表页中，价格体系二期正式版本（版本分为：过渡期版本(20日版本)、正式版本(27日版本)）。0：过渡期版本，1：正式版本。*/
	public static String PriceV2PageVersion_Normal = "1";
	
	public static Double MinSellerPrice = 0.01;
	
	
	
	public static final String siteid_EN = "EN";
	public static final String siteid_RU = "RU";
	public static final String siteid_ES = "ES";
	public static final String siteid_PT = "PT";
	public static final String siteid_FR = "FR"; 
	public static final String siteid_DE = "DE";
	public static final String siteid_IT = "IT";
	
	public static final String[] Siteid_Multi = new String[]{siteid_RU,siteid_ES,siteid_PT,siteid_FR,siteid_DE,siteid_IT};
	
	public static final String siteid_RU_Desc = "俄语";
	public static final String siteid_ES_Desc = "西班牙语";
	public static final String siteid_PT_Desc = "葡萄牙语";
	public static final String siteid_FR_Desc = "法语"; 
	public static final String siteid_DE_Desc = "德语";
	public static final String siteid_IT_Desc = "意大利语";
	
	/**
	 * 1 待翻译 ， 2：翻译成功 ， 3：翻译失败 , 22:卖家修改后重新翻译;
	 */
	public static final int Translate_Status_Prepare = 1;
	public static final int Translate_Status_Success = 2;
	public static final int Translate_Status_Failed = 3;
	public static final int Translate_Status_Alone = 22;
	
	
	
	/**
	 * 黄金展位状态类型-失效-服务降级失效。
	 */
	public static final int GoldStallType_INVALID_ServiceDown = 1000;
	
	/**
	 * 黄金展位状态类型-失效-下架失效。
	 */
	public static final int GoldStallType_INVALID_DownShelf = 1001;
	
	/**
	 * 黄金展位状态类型-失效-产品信息变动。
	 */
	public static final int GoldStallType_INVALID_InforChange = 1002;
	/**
	 * 是否为诚保卖家。0：否；1：是；
	 */
	public static final int IsHonestSeller_True = 1;
	/**
	 * 是否为诚保卖家。0：否；1：是；
	 */
	public static final int IsHonestSeller_False = 0;
	//css时间戳设置
	public final static String CSS_DATA_ID = "seller.cssjs.properties";
	public final static String CSS_GROUP = "com.dhgate.seller.cssjs.cache";
	public final static String KEY_OF_JSCSSVERSION="version";
	public final static String KEY_OF_JSCSSEFFECT="effect_type";
	
	public final static String AttrDefect_All = "0";
	public final static String AttrDefect_Required = "1";

	
}
