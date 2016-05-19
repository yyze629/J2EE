package com.yinyang.sellerpay.web.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class PayEaseConstant {
	
	/**
	 * 首信易支付渠道ID。
	 */
	public static final String ChannelId_PayEase = "59a9757db7504a12b946b2c8dc002c41";
	
	public static Map<String,String> SPTipsMap = new HashMap<String,String>();
	
	public static final String SPTips_00 = "SPTips_00";
	public static final String SPTips_01 = "SPTips_01";
	public static final String SPTips_02 = "SPTips_02";
	public static final String SPTips_03 = "SPTips_03";
	public static final String SPTips_04 = "SPTips_04";
	public static final String SPTips_05 = "SPTips_05";
	public static final String SPTips_06 = "SPTips_06";
	public static final String SPTips_07 = "SPTips_07";
	public static final String SPTips_08 = "SPTips_08";
	public static final String SPTips_09 = "SPTips_09";
	public static final String SPTips_10 = "SPTips_10";
	
	public static final String SPTips_00_Desc = "支付请求参数不正确，请核对信息后，重新提交 !";
	public static final String SPTips_01_Desc = "存在相同编号的订单，正在处理中，请稍后查看支付结果 !";
	public static final String SPTips_02_Desc = "存在相同编号的订单，已经支付成功 !";
	public static final String SPTips_03_Desc = "支付订单数字信息验证失败，请核对支付信息后，重新提交 !";
	public static final String SPTips_04_Desc = "存在相同编号的订单，并且订单数字信息验证失败，请核对信息后，重新提交 !";
	public static final String SPTips_05_Desc = "存在相同编号的订单，未知订单状态 !";
	public static final String SPTips_06_Desc = "创建支付订单信息失败 !";
	public static final String SPTips_07_Desc = "支付失败 !";
	public static final String SPTips_08_Desc = "支付成功 !";
	public static final String SPTips_09_Desc = "已提交 !";
	public static final String SPTips_10_Desc = "处理中 !";
	
	static{
		SPTipsMap.put(SPTips_00, SPTips_00_Desc);
		SPTipsMap.put(SPTips_01, SPTips_01_Desc);
		SPTipsMap.put(SPTips_02, SPTips_02_Desc);
		SPTipsMap.put(SPTips_03, SPTips_03_Desc);
		SPTipsMap.put(SPTips_04, SPTips_04_Desc);
		SPTipsMap.put(SPTips_05, SPTips_05_Desc);
		SPTipsMap.put(SPTips_06, SPTips_06_Desc);
		SPTipsMap.put(SPTips_07, SPTips_07_Desc);
		SPTipsMap.put(SPTips_08, SPTips_08_Desc);
		SPTipsMap.put(SPTips_09, SPTips_09_Desc);
		SPTipsMap.put(SPTips_10, SPTips_10_Desc);
	}
	
	/**
	 * 支付状态-已创建
	 */
	public static final String Status_Create_Failed = "-1";
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
	
	public static final String Status_Failed_Desc_Params = SPTips_00;
	
	public static final String Status_Failed_Desc = SPTips_07;
	
	public static final String Status_Commit_Desc_Repeat = SPTips_01;
	
	public static final String Status_Success_Desc_Repeat = SPTips_02;
	
	public static final String PayMd5Info_Error = SPTips_03;
	public static final String PayMd5Info_Error_Repeat = SPTips_04;
	public static final String Status_Unknown_Desc = SPTips_05;
	
	public static final String Status_Create_Failed_Desc = SPTips_06;
	
	public static final String Status_Success_Desc = SPTips_08;
	public static final String Status_Commit_Desc = SPTips_09;
	public static final String Status_Unkown_Desc = SPTips_10;
	
	
	public static final String PaymentType_Online = "01";
	
	/**
	 * 支付流水状态信息-已创建。
	 */
	public static final String VPstatus_Create = "00";
	/**
	 * 支付流水状态信息-已提交。
	 */
	public static final String VPstatus_Commit = "1";
	/**
	 * 支付流水状态信息-支付成功。
	 */
	public static final String VPstatus_Success = "20";
	/**
	 * 支付流水状态信息-支付失败。
	 */
	public static final String VPstatus_Failed = "30";
	
	/**
	 * 首信易支付币种-人民币。
	 */
	public static final String VMoneytype_RMB = "0";
	
	public static String getSPTipsContent(String sptipsId){
		String tips = "";
		if(StringUtils.isBlank(sptipsId)){
			return tips;
		}
		tips = SPTipsMap.get(sptipsId);
		return tips;
	}
	
	public static void main(String[] agrs){
		String ss = "你好";
		try {
			byte[] ssab = ss.getBytes("utf-8");
			System.out.println(new String(ssab,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
