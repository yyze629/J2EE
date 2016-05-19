package com.yinyang.sellerpay.web.util;

/**
 * Cookie名称常量类
 * @author leidengyan
 *
 */
public class CookieConstants {
	/**敦煌网根域*/
    public static final String DHGATE_ROOT = ".dhgate.com";
    /**seller 私域**/
    public static final String DHGATE_SELLER_DOMAIN = "seller.dhgate.com";
    /**buyer 私域**/
    public static final String DHGATE_BUYER_DOMAIN = "www.dhgate.com";
    
    public static final String DHTALK_TOKEN = "dhtalk_token";
    
    
    public static final String BUYER_TOKEN = "b2b_b_t_v2";
    public static final String SELLER_TOKEN = "dh_s_t";
    
    /** Session Name: DHgateUserSession*/
    public static final String DHgateSession="DHgateSession";
    /** Session Name: SELECTPRODUCTLIST */
    public static final String SELECTPRODUCTLIST = "selectproductlist";
    /** Session Name: shareinfo 与search平台共享的信息*/
    public static final String SHAREINFO_KEY = "_shareinfo";
    /**为paypal counterfeit添加的cookie名称*/
    //public static final String COOKIE_DHGATEBL = "DHgateBL";
    /**是否新buyer*/
	//public static final String COOKIE_NEWBUYER = "newbuyer";
	public static final String BUYER_LEVEL = "b2b_buyer_lv";
	public static final String COOKIE_BUYERID = "b2b_buyerid";
	public static final String COOKIE_COUNTRY = "b2b_country";
    public static final String COOKIE_AFF_ADB = "aff_adb";
    public static final String B2B_NICK = "b2b_nick_n";
    /***cookie名称*/
    public static String cookieName = "B2BCookie";
    
    /** 用户是否可以看到服务的图标*/
    public static final String COOKIE_S4PIC = "s4pic";

    /**** wuyixiao 20090327 new buyer cookie constants **/
    /** New buyer cookie name **/
    public static final String NewBuyerCookieName = "registertime";
    /** New buyer cookie age(seconds) **/
    public static final int NewBuyerCookieAge = 5*24*60*60; 
    /**** end **/

    /** 拥有一单的Buyer的Cookie值*/
    public static final String COOKIE_B_STA_VAL = "0";
    
    public static final String LAST_LOGON_LOG_ID = "last_logon_log_id";
    
    public static final String COOKIE_B_STA = "_b_o0l";
    public static final String COOKIE_S_STA = "_s_o0l";
    
    public static final String COOKIE_B_STA_02 = "_b_o02";
    public static final String COOKIE_B_STA_03 = "_b_o03";
    
    public static final String COOKIE_B_STA_old = "_b_sta";
    public static final String COOKIE_S_STA_old = "_s_sta";
    public static final String USERID = "userid";
    

    /***add by leidengyan 推荐标识cookie*/
    public static String REFERTOFRIEND_COOKIE ="refertofriend" ;
    public static String REFERTOFRIEND_USERID = "rtf_userid" ;
    
    /***是否显示Live Help cookie*/
    public static String COOKIE_ISLIVEHELPVALID = "livehelpvalue" ;
    
    public static String COOKIE_SIGNINID = "b2b_b_signinid";
    
    /***seller 身份信息 cookie*/
    public static String COOKIE_SUPPLERLEVEL = "_s_i" ;
    /***记录promotion的信息的cookie Fer要求加的 2010-3-15 leidengyan*/
    public static String COOKIE_PROMOINFO = "ref_p" ;
    /***ip所在国家id，有效期30天 2010-3-26 leidengyan*/
    public static final String COOKIE_IPCOUNTRY = "b2b_ip_country";
	
	/**---------------seller-------------------**/
	//控制seller 左菜单
	public static final String DH_SELLER_NAV = "dh_seller_nav";
	//保存产品cookie，7天 SKU
	public static final String LASTUPSKU = "LASTUPSKU";
	//保存产品cookie
	public static final String LASTUPP = "LASTUPP";
    //session id
	public static final String DHC_S = "dhc_s";
	/*** 卖家IDCookie*/
	public static final String COOKIE_SUPPLIERID = "supplierid";
	
	/**
	 * 记录是登录还是刷新,以及记录温馨提示记录的ID号
	 */
	public static final String COOKIE_NOTICEID = "cookie_noticeid";
	
	//记录buyer confirmed的订单的数量，如果没有就不记，NHP跳转规则使用
	public static final String COOKIE_CONFIRMRFXNUM = "b2b_c_n";
	//记录是否检测登陆过ali或ebay
	public static final String COOKIE_ISCHECKVISITALI = "ischeckvisitali";
	public static final String COOKIE_SYI_LAST_VERSION = "dh_syi_l_v";
	/** syi页备货期提示 */
	public static final String COOKIE_SYI_INVENTORY_PERIOD_HIP = "inventory_time_hip";
}
