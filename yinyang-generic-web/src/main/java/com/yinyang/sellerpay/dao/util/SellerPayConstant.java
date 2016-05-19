package com.yinyang.sellerpay.dao.util;

import com.dhgate.common.util.Md5;



/**
 * 通用静态变量
 */
public class SellerPayConstant {
	
	/**
	 * 支付状态-已创建
	 */
	public static final String Status_Create = "00";
	/**
	 * 支付状态-已提交
	 */
	public static final String Status_Commit = "10";
	/**
	 * 首信易支付状态-已提交.
	 */
	public static final String Status_Commit_PayEase = "1";
	
	/**
	 * 支付状态-支付成功
	 */
	public static final String Status_Success = "20";
	/**
	 * 支付状态-支付失败
	 */
	public static final String Status_Failed = "30";
	
	/**
	 * 首信易对账状态-未提交
	 */
	public static final String Status_Reconciliation_UnCommit = "0";
	/**
	 * 首信易对账状态-支付成功
	 */
	public static final String Status_Reconciliation_Success = "1";
	/**
	 * 首信易对账状态-未支付（支付结果未确定）
	 */
	public static final String Status_Reconciliation_Processing = "2";
	/**
	 * 首信易对账状态-支付失败
	 */
	public static final String Status_Reconciliation_Failed = "3";
	/**
	 * 首信易对账状态-商户退款中
	 */
	public static final String Status_Reconciliation_OnRefund = "4";
	/**
	 * 首信易对账状态-退款已成功
	 */
	public static final String Status_Reconciliation_Refund = "5";
	/**
	 * 首信易对账状态-退款被拒绝
	 */
	public static final String Status_Reconciliation_Refund_Refuse = "6";
	/**
	 * 首信易对账状态-持卡人拒付
	 */
	public static final String Status_Reconciliation_Refuse = "7";
	/**
	 * 首信易对账状态-异常退款
	 */
	public static final String Status_Reconciliation_ErrorRefund = "8";
	
	/**
	 * Seller支付系统的敏感信息，全部用此密码进行对称加密后，存储在数据库中。
	 */
	public static String SensitiveInfoSignKey = "SP_Sensitive_2015";
	
	/** 
     *   --------------------------- 首信易支付时的编号---------------------------------- 
     * */
    public static final String DHGATE_MID_IN_PAYEASE_DHB = "1708";
    
    /**
     * -------------------------------加密串  1708账号------------------------------------
     * */
    public static final String MD5PASS_1708="dhgate20080916_1502";
    
    public static final String MD5PASS_2254 = "dhgate20101028_2254";
    
    
    /** 卖家支付戴梦得配置-配置组*/
	public static String SellerPayDiamond_Group = "com.dhgate.sellerpay";
	/** 卖家支付戴梦得配置-配置ID*/
	public static String SellerPayDiamond_DataId = "sellerpay.properties";
    
	/** 首信易对账任务的机器IP*/
	public static String PayEaseAutoJobIP_key = "PayEase_AutoJob_IP";
	
	/** 任务队列大小*/
	public static String PayEaseJobQueue_MaxSize_Key = "PayEaseJobQueue_MaxSize";
	/** 任务队列默认大小，暂时为200*/
	public static int PayEaseJobQueue_MaxSize_Default = 200;
	/** 生产对账任务每批大小。*/
	public static String PayEaseJobQueue_Producer_PageSize_Key = "PayEaseJobQueue_Producer_PageSize";
	/** 生产每批对账任务的时间间隔。单位：毫秒*/
	public static String PayEaseJobQueue_Producer_Interval_Key = "PayEaseJobQueue_Producer_Interval";
	/** 指定支付流水对账期限。单位：天*/
	public static String PayEaseJob_PayFlow_Days_Key = "PayEaseJob_PayFlow_Days";
	/** 是否暂停生产对账任务。0：不暂停，1：暂停。*/
	public static String PayEaseJobQueue_Producer_Pause_Key = "PayEaseJobQueue_Producer_Pause";
	
	
	public static int PayEaseJobQueue_Producer_NotPause = 0;
	public static int PayEaseJobQueue_Producer_Pause = 1;
	
	/** 处理对账任务线程最大线程数。最大不能超过300个。*/
	public static String PayEaseJobQueue_ConsumerThreadSize_Max_Key = "PayEaseJobQueue_ConsumerThreadSize_Max";
	
	/** 处理每个对账任务的时间间隔。单位：毫秒*/
	public static String PayEaseJobQueue_Consumer_Interval_Key = "PayEaseJobQueue_Consumer_Interval";
	
	/** 处理对账任务的上限数。*/
	public static String PayEaseJobQueue_Consumer_MaxTasks_Key = "PayEaseJobQueue_Consumer_MaxTasks";
	
	/** 是否暂停处理对账任务。0：不暂停，1：暂停。*/
	public static String PayEaseJobQueue_Consumer_Pause_Key = "PayEaseJobQueue_Consumer_Pause";
	
	public static int PayEaseJobQueue_Consumer_NotPause = 0;
	public static int PayEaseJobQueue_Consumer_Pause = 1;
	
	public static String PayEaseStart_VOid = "20140101-0000-SP10000-1000000000";
	
	/**首信易对账任务的默认操作人。***/
	public static String PayEaseJob_Operator = "Job_PayEase_00001";
	
	public static void main(String[] args){
		try {
			String businessSignKey = "sp_multi_10000";
			String supplierId = "402880f100f59ccd0100f59cd37d0004";
			String businessNumber = "SP_10000";  
			String orderNumber = "100";
			String payAmount= "0.1";
			String msg = supplierId + businessNumber + orderNumber + payAmount;
			//String encrypt = AesUtil.aesEncrypt(msg, businessSignKey); 
			//String md5Info = Md5.getMd5(encrypt);
			//System.out.println("md5Info:"+md5Info);
			//System.out.println("encrypt:"+encrypt);
			//System.out.println("Decrypt:"+AesUtil.aesDecrypt("y4lBa00t/CnrkoD+DZQjlQ==", SensitiveInfoSignKey));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
