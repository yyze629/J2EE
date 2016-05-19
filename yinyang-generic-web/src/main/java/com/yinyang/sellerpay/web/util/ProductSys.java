package com.yinyang.sellerpay.web.util;


public class ProductSys {
    /** 上传图片相关常量 */
    public static final int UPIMGSIZE = 1024;
    /** 产品相关常量 */
    public static final int PAGESIZE = 10; // 每页的行数

    // /** 序列的类型: 产品编码 "productitemcode" */
    public static final String SEQUENCETYPE_PRODUCTITEMCODE = "productitemcode";

    public static final String SHIPOPTION_COPYPAGEFLAG = "copyProductflag";
    
    public static final String BASEMEASUREID = "00000000000000000000000000000001"; //默认产品单位件
    
    public static final String  ROOT_CATEGORY = "00000000000000000000000000000000"; //根类目父Id
    
  //  public static final String PAYPAL_COUNTERFEIT_PCATALOGID="in ('15','14','28','30','17','19','20')";
    public static final String PRODUCT_CACHE_KEY = "productformvo";
    public static final String PRODUCT_CACHEPRICE_KEY = "productpricevo";
    
    public static final String PRODUCT_OPEMESSAGE = "product_opemessage";
    
    public static final String SKU_CACHE_KEY = "skuvo";

    /**
     * 待审
     */
    public static final String PAYPAL_COUNTERFEIT_TYPE_100000="100000";

    public static final String PAYPAL_COUNTERFEIT_TYPE_100002="100002";
    public static final String PAYPAL_COUNTERFEIT_TYPE_100031="100031";
    public static final String PAYPAL_COUNTERFEIT_TYPE_100025="100025";
    /**
     * 关注产品
     */
    public static final String PAYPAL_COUNTERFEIT_TYPE_100023="100023";

    public static final String PAYPAL_COUNTERFEIT_TYPE_100008="100008";
    public static final String PAYPAL_COUNTERFEIT_TYPE_100012="100012";
    //直通车产品
    public static final String PAYPAL_COUNTERFEIT_TYPE_100014="100014"; //直通车产品
    public static final String PAYPAL_COUNTERFEIT_TYPE_100001="100001";
    public static final String PAYPAL_COUNTERFEIT_TYPE_NULL=null;
    
    //不允许修改的产品
    public static final String PAYPAL_COUNTERFEIT_TYPE_100015="100015"; //品牌商投诉产品
    public static final String PAYPAL_COUNTERFEIT_TYPE_100006="100006"; //禁销产品
    
    public static final String PAYPAL_COUNTERFEIT_TYPE_100016="100016";
    
    /** 待推送审  */
    public static final String PAYPAL_COUNTERFEIT_TYPE_200000="200000";
    
    //服务承诺产品
    public static final String PAYPAL_COUNTERFEIT_TYPE_ACCURATELY_DESCRIBE="accuratelydescribe"; //直通车产品

    //TDCOUNTERFEITNEWPRO表中的STATUS  状态     
    public static final String PAYPAL_TDCOUNTERFEITNEWPRO_STATUS_0="0";     //一般产品
    public static final String PAYPAL_TDCOUNTERFEITNEWPRO_STATUS_D="D";     //专区产品(移入店铺产品)
    public static final String PAYPAL_TDCOUNTERFEITNEWPRO_STATUS_C="0";     //移出专区(移出店铺产品)
    
    public static final String SPECIAL_ZONE_SAVE="D";                        //是专区的产品
    public static final String SPECIAL_ZONE_DEL="C";                         //不是专区的产品
   /** 工厂相关常量  */
    public static final String SUPPLIER_MANUFACTORY_LIST = "supplierManufactorylist";
    
    /*** VIP 相关 ***/
    /** VIP 方案是否可用 1 可用，0 不可用*/
    public static final String VIPZONE_SCHEMASTATUS_Y = "1";
    public static final String VIPZONE_SCHEMASTATUS_N = "0";
    /**VIP 方案内的产品是否可用 0 删除 ，1 可用*/
    public static final String VIPZONE_SCHEMAPRODUCT_VALID_Y = "1";
    public static final String VIPZONE_SCHEMAPRODUCT_VALID_N = "0";
    
    /**产品操作权限验证相关*/
    public static final String PRODUCT_LIMIT_ACTION_ADD     ="add";
    public static final String PRODUCT_LIMIT_ACTION_UPDATE  ="update";
    
    /**SKU权限验证相关*/
    public static final String SKU_LIMIT_ACTION_ADD     ="add";
    public static final String SKU_LIMIT_ACTION_UPDATE  ="update";
    /** 审核通过的SKU **/
    public static final String SKU_PUBLISH     ="40";
    public static final String SKU_OLD_PUBLISH ="60";
    /** 美元汇率 **/
    public static final Double DOLLARS_EXCHANGE_RATE = 6.85;
    //SKU 图片水印
    public static final String SKU_IMG_WATERTEXT = "Certified Items";
    
    public static final String PRODUCT_UPDATE_LIMIT_SYI_ADD_5      ="1000001";//基础数据的主键
    public static final String PRODUCT_UPDATE_LIMIT_SYI_ADD_DAY    ="1000002";//基础数据的主键
    public static final String PRODUCT_UPDATE_LIMIT_SYI_UPDATE_5   ="1000003";//基础数据的主键
    
    /**search web service url*/
    public static final String CONFIG_SEARCH_WEBSERVICE="product.search.webservice";
    
    /**是不是工厂用户的工厂产品*/
    public static final String IS_FACTORY_PRODUCT_YES="1";  //是工厂用户的工厂产品。
    public static final String IS_FACTORY_PRODUCT_NO="0";   //不是工厂用户
    
    /**图片上传路径  windows */
    public static final String WEB_SERVER_PATH="/usr/local/jboss/server/default/deploy/seller.ear/sellerWeb.war/";
    
    /**虚拟产品目录**/
    public static final String PRODUCT_VIRTUAL_CATALOGID="262011";  
    
//    /**每个用户的快速上架产品的最大产品数 */
    /** 快速上架产品的 session key**/
    public static final String PRO_QUICKUP_KEY="pro_quickup_key";  
    
    
    public static final String[] STATUS3={"100000","100031","100002",PAYPAL_COUNTERFEIT_TYPE_100023,PAYPAL_COUNTERFEIT_TYPE_100025};
    
    /** 审核通过 （包括纯绿区"100031") */
    public static final String[] STATUS4={"100000","100002","100031",PAYPAL_COUNTERFEIT_TYPE_100023,PAYPAL_COUNTERFEIT_TYPE_100025};
    
    
    /** 服务承诺可操作产品**/
    public static final String[] STATUS6={"100000","100002","100014","100031",PAYPAL_COUNTERFEIT_TYPE_100023,PAYPAL_COUNTERFEIT_TYPE_100025};
    
    /**产品目录默认最低价格*/
    public static final Double CATALOG_PRICE_LIMIT=0.01D;
    /**莆田联盟站*/ 
    public static final String APP_PT_06="06" ;
    
    /**SKU 相关 */
    public static final String IS_SKU="sku";
    
    /** 有效的**/
    public static final String VALID = "1";
    /** 无效的 **/
    public static final String INVALID = "0";
    
    /** 有效的**/
    public static final Long VALIDL = 1L;
    /** 无效的 **/
    public static final Long INVALIDL = 0L;
    
    /** 搜索加载状态为**/
    public static final String SRHSTATE = "7";
    public static final String UN_SRHSTATE ="6";
    
    /** 适用于 store的产品*/
    public static final String [] PROSTATE_STORE = {"100000","100014","100002","100031",PAYPAL_COUNTERFEIT_TYPE_100023,PAYPAL_COUNTERFEIT_TYPE_100025};
    
    /** 保存DH产品**/
    public static final String ADDDHPRODUCT = "save_DH";
    /** 保存海外直发产品**/
    public static final String ADDDDHPORT = "save_DHPORT";
    
    /** 保存联盟UNION产品**/
    public static final String ADDUNIONPRODUCT = "save_NOT_DH";
    /** 空字符 **/
    public static final String EMPTY_SPACE="";
    /** 产品类别 普通产品 **/
    public static final String PRODUCT_NORMAL="0";
    
    /** 产品类别 海外直发产品 **/
    public static final String PRODUCT_DHPORT="1";
    
    /** 产品类别 SKU产品 **/
    public static final String PRODUCT_SKU="2";
    /** 类别 SKU模版 **/
    public static final String SKU_TEMP="1";
    
    public static final Long pro_upload_num = 100L ;
    public static final Long pro_all_num = 50L ;
    
    /** 手机、邮箱认证没全部通过的用户的最大的加载搜索的产品总数 */
    public static final Long PRO_SEARCHED_MAX = 0L;
    
    
    /** 手机、邮箱认证没全部通过的用户的最大的黄金展位的产品总数 */
    public static final Long PRO_GOLDSTALL_MAX = 0L;
    
    //产品上下架状态
    public static final String PRODUCT_ISTATE_OFFLINE="0";	//下架
    public static final String PRODUCT_ISTATE_ONLINE="1";	//上架
    public static final String PRODUCT_ISTATE_DELETE="2";	//删除
    public static final Integer PRODUCT_ISTATE_DELETE_I=2;	//删除
    public static final String PRODUCT_ISTATE_STAR = "*"; //未通过邮件或电话认证的用户PHP审核状态
    
    
    /** 验证Email地址的正则表达式*/
    public static final String REG_EMAIL = "[\\w\\-]+(\\.[\\w\\-]*)*@[\\w\\-]+([\\.][\\w\\-]+)+";
    /** 产品最终页显示密钥*/
    public static final String SECRET_KEY = "62636465666768696a6b6c6d6e2a302a7071727374757677";
    /**
     * 产品SYI页面万能验证码(测试用)
     */
    public static final String SYI_VALIDKEY = "dhsellerQaZ";
    /** 审核通过的SKU **/
    //public static final String VALIDSKU="40";
    /** 1:SYI新增产品   2:SYI更新产品    3:快登手新增产品   4:快登手更新产品 9:seller平台修改产品价格 **/
    
    public static final Long DHNEWPRODUCT = 1L;
    public static final Long DHMODIFYPRODUCT = 2L;
    public static final Long DHANEWPRODUCT = 3L;
    public static final Long DHAMODIFYPRODUCT = 4L;

    public static final Long DHOUTSPECIALZONE = 5L;

    /**
     * 上架
     */
    public static final Long DH_UP_SHELF = 6L;

    public static final Long PRODUCTAPPEAL = 8L;
    public static final Long DHMODIFYPRODUCTPRICE = 9L;
    
    /** 产品货币单位 tc_currency  **/
    public static final String CURRENCR_USD = "10002"; //美金
	public static final String funcName = "ppc for pro";
	public static final String PRPDUCTXUNJIAN = "product xunjian";
	//HTML 模版类型
	public static final String SELLER_HTMLMODEL_SYIDESHTML = "1";
	//用户每月可提交的产品申诉次数
	public static final int PRODUCT_APPEALCOUNT_MONTH=10;
	
	//dhport海外直发产品库存状态1 普通、2 预订、3 预售、4 仓库 0 平台产品
	public static final String DHPORTNORMAL = "1"; //DHPort普通产品
	public static final String DHPORTBOOKING = "2"; //DHPort预定产品
	public static final String DHPORTPRESALE = "3"; //DHPort预售产品
	public static final String DHPORTSTOCK = "4"; //DHPort库存产品	
	
	public static final Long DHPORSEATBOOKINGLEADTIME = 50L; //DHPort预定产品海运备货期
	public static final Long DHPORTSEAPRESALELEADTIME = 35L; //DHPort预售产品海运备货期
	public static final Long DHPORAIRTBOOKINGLEADTIME = 15L; //DHPort预定产品海运备货期
	public static final Long DHPORTAIRPRESALELEADTIME = 10L; //DHPort预售产品海运备货期
	public static final Long DHPORTSTOCKLEADTIME = 2L; //DHPort库存产品备货期
	
	
	
	//认领的临时数据，保存在session中
	public static final String TempMoveSessionId = "tempmovesessionid";
	//每个产品，可以认领的最大数量
	public static final Long PNUMBER = 2L;
	//每人可认领的最大产品数
	public static final int MAXPNUMBER = 10000;
	//每次的最大认领的产品数
	public static final int PERMAXPNUMBER = 100;
	
	public static final String DEFAULTMEASUREID = "00000000000000000000000000000003"; //件
	public static final String DEFAULTMEASURENAME = "件"; //件
	
	//0  平台抓取上传  1 API上传  2  手功导入 Excel
	public static final String [] SOURCETYPE0 = {"0","4"};
	public static final String [] SOURCETYPE1 = {"1"};
	public static final String [] SOURCETYPE2 = {"2"};
	/** 上架产品的认领标志*/
	public static final String [] SOURCETYPE_SHELF = {"1","2","5"};
	/** 搬家产品的认领标志*/
	public static final String [] SOURCETYPE_SPIDER = {"0","4"};
	
	/******************* 上传图片ftp列表  ****************************/
	//图片后缀
	public static final String IMGORI = ".ori.jpg";		//原图
	public static final String IMGMIN = ".thumb.gif";	//小图
	public static final String IMGMID = ".200x200.jpg";	//中图
	public static final String IMGNOR = ".jpg";			//水印图
	//图片FTP服务器配置
	public static final String IMGHOST = "ftpserver";
	public static final String IMGUSERNAME = "test";
	public static final String IMGPASSWORD = "test";
	public static final String IMGPORT = "21";
	//原图FTP服务器
	public static final String IMGORIHOST = "ftporiimgserver";
	public static final String IMGORIUSERNAME = "ftptupian-ori";
	public static final String IMGORIPASSWORD = "ftptupian-ori";
	public static final String IMGORIPORT = "21";
	//中图FTP服务器
	public static final String IMGMIDHOST = "ftpmidimgserver";
	public static final String IMGMIDUSERNAME = "ftptupian-zhong";
	public static final String IMGMIDPASSWORD = "ftptupian-zhong";
	public static final String IMGMIDPORT = "21";
	/******************* 上传图片ftp列表结束  ****************************/
	
	/******************* 上传HTMLftp ***********************************/
	//中图FTP服务器
	public static final String HTMLFTPSERVER="htmlftpserver";//SellerConfig.getString("htmlftpserver.ftp.host");
	public static final String HTMLUSER="htmlupload";//SellerConfig.getString("htmlftpserver.ftp.user");
	public static final String HTMLPASS="htmlupload2008";//SellerConfig.getString("htmlftpserver.ftp.pass");
	public static final String HTMLPORT="21";//SellerConfig.getString("htmlftpserver.ftp.port");
	
	
	/******************* 下载服务器host列表  ****************************/
	public static final String BANIMGSRV  = "image.dhgate.com"; 	//"banimgsrv";    	//抓取产品图片服务器
	public static final String BANHTMLSRV  = "html.dhgate.com"; 	//"banhtmlsrv";  	//抓取产品详细描述服务器
	
	public static final String SYIHTMLSRV  = "htmlftpserver";  	//产品详细描述服务器
	public static final String SYIIMGSRV  = "image.dhgate.com"; //产品图片服务器
	
	/******************* 下载服务器host列表结束  *************************/
	
	//
	public static final String NORMAL_Catch = "0";
	public static final String QUICK_Catch = "4";
	public static final String SPIDER_CATCHERROR_INVALIDDATA = "0001";  //无效的数据
	public static final String SPIDER_CATCHERROR_SYSDATA = "0002";		//系统错误
	public static final String SPIDER_CATCHERROR_USERDATA = "0003";		//用户数据错误
	public static final String SPIDER_CATCHERROR_COPYIMG = "0004";		//图片拷贝错误
	
	
	public static final Long PRODUCT_LEADINGTIMEMIN = 1L;
	public static final Long PRODUCT_LEADINGTIMEMAX = 60L;
	public static int threadupnum  = 5;
	public static int threadtasknum  = 1;
	public static String []  Random = {"1","2"};
	

	/***paypal过来巡查产品*/
    public static final String FUNCNAME_PAYPALCHECK = "paypal check";

    
    //产品模板类型
    public static final String PRODUCT_MODEL_DESCRIPTION = "1";			//详描模版
    public static final String PRODUCT_MODEL_PROMISE = "2";				//服务服务承诺模版
	

    
    /** 页面操作 */
    public static final String PRODUCT_OPERATE = "productoperate";



	//卖家图片上传类型
	public static final String PRODUCT_SYI_IMGUPLOAD = "1";
	public static final String ALBUMS_IMGUPLOAD = "2";
	
	/** 我的产品的页面类型参数: 待审核  "0"*/
	public static final String BIZTYPE_0 = "0";
	/** 我的产品的页面类型参数: 未通过审核产品  "2"*/
	public static final String BIZTYPE_2 = "2";
	/** 我的产品的页面类型参数: 上架的产品  "3"*/
	public static final String BIZTYPE_3 = "3";
	/** 我的产品的页面类型参数: 下架的产品  "4"*/
	public static final String BIZTYPE_4 = "4";
	/** 我的产品的页面类型参数: 搜索产品  "5"*/
	public static final String BIZTYPE_5 = "5";
	/** 我的产品的页面类型参数: 黄金展位产品  "6"*/
	public static final String BIZTYPE_6 = "6";
	/** 我的产品的页面类型参数: 会员专区产品  "7"*/
	public static final String BIZTYPE_7 = "7";
	/** 我的产品的页面类型参数: 搬家上架产品  "8"*/
	public static final String BIZTYPE_8 = "8";
	/** 我的产品的页面类型参数: 品牌商投诉产品  "9"*/
	public static final String BIZTYPE_9 = "9";

    /**疑似侵权产品*/
	public static final String BIZTYPE_10 = "10";

	/** 所有产品页面类型参数 */
	public static final String[] BIZTYPE_ALL = { BIZTYPE_0, BIZTYPE_2, BIZTYPE_3, 
												 BIZTYPE_4, BIZTYPE_6, 
												 BIZTYPE_7, BIZTYPE_8, BIZTYPE_9, BIZTYPE_10};

	/** 3天将过期的产品 */
	public static final String SUMMARYTYPE1 = "1";
	/** 最近30天因问题被下架的产品 */
	public static final String SUMMARYTYPE2 = "2";
	/** 最近30天已过期的产品 */
	public static final String SUMMARYTYPE3 = "3";
	/** 被品牌商投诉并下加的产品 */
	public static final String SUMMARYTYPE4 = "4";

	
	/** 添加、修改 */
	public static final String CHECKTYPE1 = "1";
	/** 上架 */
	public static final String CHECKTYPE2 = "2";
	/** 修改价格 */
	public static final String CHECKTYPE3 = "3";
	/** 调整产品组 */
	public static final String CHECKTYPE4 = "4";
	/** 更新有效期 */
	public static final String CHECKTYPE5 = "5";
	/** 调整产品组 */
	public static final String CHECKTYPE6 = "6";
	/** 修改运费 */
	public static final String CHECKTYPE7 = "7";
	/** 服务保障**/
	public static final String CHECKTYPE8 = "8";
	/** 删除**/
	public static final String CHECKTYPE9 = "9";
	
	/***限制类目经营和冻结 ***/
	public static final String[] PROD_MANAGE_LIMIT_TYPES = {CHECKTYPE1,CHECKTYPE2,CHECKTYPE3, 
		   CHECKTYPE4,CHECKTYPE5,CHECKTYPE6,CHECKTYPE7};
	/***限制上传产品的权限 ***/
	public static final String[] PROD_UPLOAD_LIMIT_TYPES = {CHECKTYPE1,CHECKTYPE2 };
	
	/** 1:修改	2:上架,	3:修改价格,4:调整产品组 5:更新有效期, 6:调整产品组, 7:修改运费 */
	public static final String[] CHECKTYPES = {CHECKTYPE1,CHECKTYPE2,CHECKTYPE3, 
											   CHECKTYPE4,CHECKTYPE5,CHECKTYPE6,
											   CHECKTYPE7,CHECKTYPE8,CHECKTYPE9};
	
	/** 发布类目准入、受限状态 **/
	public static final String CATEGORYPUBLISHADMITTANCE0="0";	//行业限制
	public static final String CATEGORYPUBLISHADMITTANCE1="1";	//一类行业准入（A、B类，Factory卖家不受限，其他卖家准入后才可进入）
	public static final String CATEGORYPUBLISHADMITTANCE2="2";	//二类行业准入（所有卖家准入后才可进入）
	
	/** 类目路径的连接字符串: >> */
	public static final String CATEGORY_PATH_JOIN_STR = " >> ";
	
	/** 类目属性类型: 下拉框 */
	public static final String CATEGORY_ATTR_TYPE_SINGLE = "2";
	
	/** 类目属性类型: 复选框 */
	public static final String CATEGORY_ATTR_TYPE_MULTI = "1";
	
	/** 类目属性类型: 字符型输入框 */
	public static final String CATEGORY_ATTR_TYPE_STR = "4";
	
	/** 类目属性类型: 数值型输入框 */
	public static final String CATEGORY_ATTR_TYPE_NUMRIC = "5";
	
	/** 类目属性样式: 文字 */
	public static final String CATEGORY_ATTR_STYLE_TEXT = "1";
	
	
	/** 类目属性样式: 图片*/
	public static final String CATEGORY_ATTR_STYLE_PIC = "2";
	
	/** 类目属性样式: 图文*/
	public static final String CATEGORY_ATTR_STYLE_PICTEXT = "3";
	
	/** 文本属性的最大的属性值个数: 1*/
	public static final int MAXVALCOUNT_OF_STRATTR = 1;
	
	/** 品牌属性的属性值的最大个数:1*/
	public static final int MAXVALCOUNT_OF_BRANDATTR = 1;
	
	/** 数字属性值的最大个数:1*/
	public static final int MAXVALCOUNT_OF_NUMRICATTR = 1;
	
	
	/** 单选属性值的最大个数:1*/
	public static final int MAXVALCOUNT_OF_SINGLEATTR = 1;
	
	/** 自增加属性, 属性值的最大个数:5*/
	public static final int MAXVALCOUNT_OF_SELFADDATTR = 5;
	
	/** 校验数据长度: 1*/
	public static final int VALIDATE_LENGTH = 40;
	
	/** Other属性值id 0L*/
	public static final Long OTHER_ATTRVAL_ID = 0L;
	
	/** 自增加属性属性ID:11L */
	public static final Long SELF_ADD_ATTRID = 11L;
	
	/** 产品自增加属性的type:"9" */
	public static final String PRODUCT_ATTR_TYPE_SELFADD = "9";
	
	/** 无品牌属性ID:99 */
	public static final Long NO_BRAND_ID = 99L;
	
	/** 品牌属性ID:99 */
	public static final Long ATTR_BRAND_ID = 99L;
	
	
	/* --  增值服务降级处理相关  开始   ---*/
	
	/** 产品类型标识,上传产品(不包括快速上架的产品,和搬家产品):"1" */
	public static final String PROD_FLAG_1 = "1";
	
	/** 产品类型标识,上架产品(店铺产品):"2" */
	public static final String PROD_FLAG_2 = "2";
	
	/** 产品类型标识,加载搜索产品:"3" */
	public static final String PROD_FLAG_3 = "3";
	
	/** 将数据按处理顺序加载到中间表的状态 ：待加载 */
	public static final String ISADD_0 = "0";
	
	/** 将数据按处理顺序加载到中间表的状态 ：已加载 */
	public static final String ISADD_1 = "1";
	
	/** 将数据按处理顺序加载到中间表的状态 ：降级处理结束 */
	public static final String ISADD_2 = "2";
	
	/* --  增值服务降级处理相关 结束   ---*/
	
	/** 列表页面取的默认最近***天内操作过的产品:30 */
	public static int DEFAULT_DAYS = 30;
	
	/** AutoProcessIsSearchedGoldStall job每次每个分区默认处理1000条数据:1000 */
	public static int MAX_RESULTS = 1000;
	
	/** 大数据量处理 ，每次获取100个产品*/
	public static int PCOU = 100;
	
	
	/***交叉营销开关***/
	public static final String FUNCTION_CROSSPROMOTION = "crosspromotion";
	

	/**最终页分流开关**/
	public static final String FUNCTION_ABVERSIONSWITCH = "finalpageABVersionSwitch";
	

	/** 金枪鱼产品的页面类型参数 :下架的产品*/
	public static final String GOLD_BIZTYPE_0 = "0";
	/** 金枪鱼产品的页面类型参数 :上架的产品 */
	public static final String GOLD_BIZTYPE_1 = "1";
    /** 金枪鱼产品的页面类型参数 :黄金展位产品 */
	public static final String GOLD_BIZTYPE_6 = "6";
	
	public static final String[] GOLD_BIZTYPE_ALL = {GOLD_BIZTYPE_0, GOLD_BIZTYPE_1 ,GOLD_BIZTYPE_6};
	
	/** 产品上下架状态:下架的产品*/
	public static final String ISTATE_0 = "0";
	/** 产品上下架状态:上架的产品 */
	public static final String ISTATE_1 = "1";
	
	/** 产品类型:金枪鱼产品*/
	public static final String PRODTYPE_1 = "1";
	
	/** 服务到期降级处理来源(TD_SUPPLIER_FRESHPRO.SOURCE)：MIC */
	public static final String SUPPLIER_FREPRO_SOURCE_MIC = "1";
	
	/** 服务到期降级处理产品默认处理产品的天数(XX天之前的降级) */
	public static final int DEFAULT_FRESH_DAY = 7;
	
	/** 产品MIC标：无效 */
	public static final String  MIC_TAG_INVALID = "0";
	/** 产品MIC标：有效 */
	public static final String  MIC_TAG_VALID = "1";
	/** 产品MIC标：过期*/
	public static final String  MIC_TAG_OVERDUE = "2";
	
	/** 最终页transactions数据缓存*/
	public static final String PROD_DETAIL_TRANSACTION = "prod_detail_transation";
	
	/**最终页metaVo数据缓存*/
	public static final String METAVO_PRODID = "metavo_prodId_new";
	
	/** 最终页productreviews数据缓存 -getReviewSummaryInfo*/
	public static final String PROD_DETAIL_REVIEWS = "prod_detail_reviews";
	
	/**  最终页productreviews数据---loadReviewsByProductId*/
	public static final String PROD_DETAIL_REVIEWS_IS = "prod_detail_reviews_is";
    
	/**根据类名，取得代码信息的列表信息*/
	public static final String GET_CODE_PARAM_LIST = "class_to_code";
	
	/**buyer是否是seller会员--isBuyerMemberOfSeller*/
	public static final String BUYER_MEMBER_OF_SELLER_FLAG = "is_member";
	
	/**PaymentRelease订单数缓存-getRfxPaymentReleaseNum*/
	public static final String PAYMENT_RELEASE_NUM = "pr_num";
	
	/**通过buyerID取得buyer的相关信息-getBuyerInfo caoke*/
	public static final String GET_BUYER_INFO = "get_buyer_info_new2";
	
	/**buyer是否与该seller交易过-isOldBuyertoSeller*/
	public static final String ISOLD_BUYER_TO_SELLER = "is_old_buyer_to_seller";
	
	/**获得卖家的所有的买够送的coupon活动信息-getSellerCouponCampaignListforBuyer*/
	public static final String SELLER_COUPON_FOR_BUYER = "seller_coupon_for_buyer";
	
	/** 黄区**/
	public static final String [] COUNTERFEIT_TYPE_HUANG = {
            PAYPAL_COUNTERFEIT_TYPE_100008,
            PAYPAL_COUNTERFEIT_TYPE_100012,
            PAYPAL_COUNTERFEIT_TYPE_100001
    };

	/** 绿区**/
	public static final String [] COUNTERFEIT_TYPE_LV = {"100002","100031",PAYPAL_COUNTERFEIT_TYPE_100023,PAYPAL_COUNTERFEIT_TYPE_100025};
	/** 未通过核**/
	public static final String [] COUNTERFEIT_TYPE_NOT = {"100006","100003","100013","100017","100022","100024"};
	/** 未通过核,不可修改产品 黄4， 禁销品**/
	public static final String [] COUNTERFEIT_TYPE_NOT_NOM = {"100015","100006"};
	/** 未通过核,可修改产品 违规品，图重, 名重 ,侵权产品**/
	public static final String [] COUNTERFEIT_TYPE_NOT_M = {"100003","100013","100017","100022"};
	
	/** 待审核: 待审核、待推送审核 **/
	public static final String [] COUNTERFEIT_TYPE_AUDIT = {PAYPAL_COUNTERFEIT_TYPE_100000,PAYPAL_COUNTERFEIT_TYPE_200000};
	
	/** 能够参加MIC促销活动的产品状态 **/
	public static final String [] COUNTERFEIT_TYPE_MIC = {"100002","100031"};
	/** 重复产品：名重、图重、描述重*/
	public static final String [] COUNTERFEIT_TYPE_REPEAT = {"100013","100017","100024"};
	
	/** 重复产品处理原因：平台下架 */
	public static final String REPEAT_PROD_DEALWITH_REASON_DOWNSHELF = "2";
	
	/** 重复产品处理原因：删除产品*/
	public static final String REPEAT_PROD_DEALWITH_REASON_DELETE = "3";
	
    /**新曾特殊类目缓存key值，固定*/
    public static final String SPECILCATELOGREDIRET = "product_specilcatelog_redirect_list";
    
    /**seo使用，产品最终页面包屑链接转换为dcp链接，缓存key值前缀 */
    public static final String SEOCATELOGURLDCPMAP = "new_seo_catelog_url_dcp_map";
    
    /**seo使用，产品最终页面包屑中类目是否有dcp页面，缓存map*/
    public static final String SEOCATELOGDCPMAP = "seo_catelog_dcp_map";
    
    /** 有备货的产品：1*/
	public static final String INVENTORY_TYPE = "1";
	/** 暂无备货的产品：0*/
	public static final String INVENTORY_TYPE_UN = "0";
	/** 需要补充备货的产品：2*/
	public static final String INVENTORY_TYPE_NEED = "2";
    
	/**
	 * 注册来源--平台域名
	 */
	public static final String WWW_DHGATE_COM = "www.dhgate.com";
	
	/**
	 * 最终页image域名
	 */
	public static final String WWW_DHRESOURCE_COM = "www.dhresource.com";
	
	/**
	 * 店铺最终页是否查询左侧类目信息 1查询，非1不查询
	 */
	public static final String DHGATE_STORE_PRODUCT_ISCATEGORY = "1";
	
	/**
	 * 店铺最终页是否查询产品相关三项信息 
	 * topSelling
	 * have orders
	 * Viewed
	 * tselling-10|reviews-10|rlist-10
	 * -之后数字为信息条数
	 * 
	 */
	public static final String DHGATE_STORE_PRODUCT_PRODUCTS = "tselling-10|reviews-10|rlist-10";
	
	/**
	 * 店铺最终页请求连接
	 */
	public static final String DHGATE_STORE_PRODUCT_URL = "http://www.dhgate.com/wholesale/storeproductaip.do?act=storeProuctInfo";
	
	/**
	 * 低佣金类目，diamond配置无法取到时默认值
	 */
	public static final String PRODUCT_LOW_COMMISITION_CATELOG = "105001,018002,018030,002,018001,018008,018009,018017,018121";
	
	
	/**
	 * 店铺最终页diamond：
	 */
	public static final String PRODUCT_STORE_DIAMOND_GROUPID = "com.dhgate.buyer.store";					//groudid
	public static final String PRODUCT_STORE_DIAMOND_DATAID = "dhconfig_buyer_store.properties";			//dataid
	public static final String PRODUCT_STORE_DIAMOND_CATELOGKEY = "Product.productStore.isCatelog";	//keyIsCatelog
	public static final String PRODUCT_STORE_DIAMOND_PRODUCTKEY = "Product.productStore.products";	//keyProducts
	public static final String PRODUCT_STORE_DAIMOND_URLKEY = "Product.productStore.url";						//keyurl
	public static final String PRODUCT_STORE_DIAMOND_LOW_COMMISSIONKEY = "Product.filter.low.commission.switch";//低佣金类目
	public static final String PRODUCT_STORE_DIAMOND_SEO_INDUSTRY_CATELOG_WORDS = "product.productStore.industryCatelogWord"; //seo行业特征词key前缀 key + catelogId
	
	
	
	
	/**
	 * 店铺开张状态1，已开张
	 */
	public static final String PRODUCT_STORE_IS_CREATED = "1";
	
	
	/**
	 * 是否是访问店铺最终页
	 */
	public static final String PRODUCT_STORE_IS_FROM = "store";
	
	
	/**
	 * 店铺最终页用户行为名称
	 */
	public static final String PRODUCT_STORE_USER_BEHAVIOUR = "store_behaviour";
	
	/**
	 * 店铺最终页左侧显示状态值 1，显示，非 1不显示 
	 */
	public static final String PRODUCT_STORE_USER_BEHAVIOUR_SHOW = "1";
	
	/**
	 * 定价核算系统自定义规格属性id
	 */
	public static final int PRODUCT_SKU_ATTR_SPEC_TYPE = 9999;
	
	/**
	 * 定价核算系统 属性名称map用常量前缀
	 */
	public static final String PRODUCT_SKU_MAP_ATTRNAME = "attr";
	public static final String PRODUCT_SKU_MAP_ATTRVALNAME = "attrval";
	public static final String PRODUCT_SKU_MAP_ATTRVALNAME_CN = "attrvalcn";
	public static final String PRODUCT_SKU_MAP_ATTRPIC = "pic";
	public static final String PRODUCT_SKU_MAP_CUSTOMSIZE = "custom";
	
	
	/**
	 * 最终页服务化开关groudid
	 */
	public static final String PRODUCT_DETAIL_SERVICE_GROUPID = "com.dhgate.relay.config";
	
	/**
	 * 最终页服务化开关dataid
	 */
	public static final String PRODUCT_DETAIL_SERVICE_DATAID = "apsarasrelay.properties";
	
	/**
	 * 最终页服务化开关key： sellerAuthService
	 */
	public static final String PRODUCT_DETAIL_SERVICE_SELLERAUTHSERVICE = "relay.detail.sellerauth";
	
	/**
	 * 最终页服务化开关key ：dmrsService
	 */
	public static final String PRODUCT_DETAIL_SERVICE_DMRSSERVICE = "relay.detail.dmrs";
	
	/**
	 * 最终页服务化开关key ：prodbizService
	 */
	public static final String PRODUCT_DETAIL_SERVICE_PRODBIZSERVICE = "relay.detail.prodbiz";
	
	/**
	 * 最终页服务化开关key： promoService
	 */
	public static final String PRODUCT_DETAIL_SERVICE_PROMOSERVICE = "relay.detail.promo";
    
    	
	/**
	 * 大客户buyer列表缓存key
	 */
	public static final String LARGE_CUSTOMER_BUYER_LIST = "dhgate_large_customer_buyers";
	
	/**
	 * 大客户seller列表缓存key
	 */
	public static final String LARGE_CUSTOMER_SELLER_LIST = "cooperation_provide_product_seller_key_05";
	
	/**
	 * 大客户卖家标志位 5
	 */
	public static final String LARGE_CUSTOMER_SELLER_TYPE = "5";
	//消费者服务保障计划服务
    public static final String CATCHPRO_2401 = "2401";
    
    /**
     *上架的产品
     */
    public static final String SearchActivity_Shelf = "SearchActivity_Shelf";
    /**
     *批量修改
     */   
    public static final String SearchActivity_BatchUpdate = "SearchActivity_BatchUpdate";
    /**
     *黄金展位产品
     */
    public static final String SearchActivity_GoldStall = "SearchActivity_GoldStall";
    /**
     *添加黄金展位
     */  
    public static final String SearchActivity_AddGoldStall = "SearchActivity_AddGoldStall";
    /**
     *会员专区
     */      
    public static final String SearchActivity_SpecialZone = "SearchActivity_SpecialZone";
    /**
     *搬家产品
     */      
    public static final String SearchActivity_Spider = "SearchActivity_Spider";
    /**
     *下架的产品
     */    
    public static final String SearchActivity_DownShelf = "SearchActivity_DownShelf";
    /**
     *待审核产品
     */    
    public static final String SearchActivity_Audit = "SearchActivity_Audit";
    /**
     *疑似侵权产品
     */  
    public static final String SearchActivity_Violate = "SearchActivity_Violate";
    /**
     *审核未通过产品
     */
    public static final String SearchActivity_AuditFail = "SearchActivity_AuditFail";
    /**
     *品牌商投诉产品
     */
    public static final String SearchActivity_BrandComplaint = "SearchActivity_BrandComplaint";
    /**
     *快速上架产品
     */  
    public static final String SearchActivity_QuickShelf = "SearchActivity_QuickShelf";
    /**
     *诚信诚信服务
     */  
    public static final String SearchActivity_Pledge = "SearchActivity_Pledge";
    /**
     *海外退货
     */      
    public static final String SearchActivity_LocalReturn = "SearchActivity_LocalReturn";
    
    /**
     * 品牌商投诉原因的特定原因ID。
     */
    public static final String ComplaintMatterCause_Brand = "DCBD9723E6AAABFBE040007F010029C9";
    
    public static final String ServiceFunctionAPP_QUICKUP = "TF_QUICKUP";
    
    
    
    
    
    /**
     *构建绿区产品状态
     * @return
     */
    public static String buildMicStatus() {
        StringBuilder stringBuffer = new StringBuilder();
        for (String type : COUNTERFEIT_TYPE_MIC) {
            stringBuffer.append('\'');
            stringBuffer.append(type);
            stringBuffer.append("',");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }


    /**
     *构建绿区产品状态
     * @return
     */
    public static String buildGreenStatus() {
        StringBuilder stringBuffer = new StringBuilder();
        for (String type : COUNTERFEIT_TYPE_LV) {
            stringBuffer.append('\'');
            stringBuffer.append(type);
            stringBuffer.append("',");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    /**
     * 构建未审核产品状态
     *
     * @return
     */
    public static String buildUnauditedStatus() {
        StringBuilder stringBuffer = new StringBuilder();
        for (String type : COUNTERFEIT_TYPE_NOT) {
            stringBuffer.append('\'');
            stringBuffer.append(type);
            stringBuffer.append("',");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }
    
    /**
     * 构建待审核产品状态
     *
     * @return
     */
    public static String buildWaitAuditStatus() {
        StringBuilder stringBuffer = new StringBuilder();
        for (String type : COUNTERFEIT_TYPE_AUDIT) {
            stringBuffer.append('\'');
            stringBuffer.append(type);
            stringBuffer.append("',");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }
    /**
     * 构建重复产品状态
     *
     * @return
     */
    public static String buildRepeatStatus() {
        StringBuilder stringBuffer = new StringBuilder();
        for (String type : COUNTERFEIT_TYPE_REPEAT) {
            stringBuffer.append('\'');
            stringBuffer.append(type);
            stringBuffer.append("',");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    /**
     * 是否绿区状态
     * @param counterfeittypeid
     * @return
     */
    public static boolean isGreen(String counterfeittypeid) {
        for (String type : COUNTERFEIT_TYPE_LV) {
            if(type.equals(counterfeittypeid)) return true;
        }
        return false;
    }
    
    /**
     *构建绿区产品状态，以逗号分隔。
     * @return
     */
    public static String buildGreenStatusComma() {
        StringBuilder stringBuffer = new StringBuilder();
        int i = 0;
        for (String type : COUNTERFEIT_TYPE_LV) {
        	if(i != 0){
        		stringBuffer.append(",");
        	}
        	stringBuffer.append(type);
        	i++;
        }
        return stringBuffer.toString();
    }
   
}
