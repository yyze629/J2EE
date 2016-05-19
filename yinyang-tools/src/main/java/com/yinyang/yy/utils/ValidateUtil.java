package com.yinyang.yy.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 验证工具类
 * 
 * @Title: ValidateUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午2:43:44
 * @version V1.0
 */
public class ValidateUtil {

	/***
	 * 验证数据是否为空
	 * @param errorMsg
	 * @param paramName
	 * @param value
	 * @return
	 */
	public static boolean required(StringBuilder errorMsg, String paramName, Object value){
		boolean result = true;
		if(value==null  || StringUtils.isBlank(String.valueOf(value))){
			result = false;
			if(errorMsg != null){
				errorMsg.append(paramName + " is blank;");
			}
		}
		return result;
	}
	
	/**
	 * 验证数据为非负数  is zero 不做验证
	 * @param errorMsg
	 * @param paramName
	 * @param value
	 * @return
	 */
	public static boolean  notZero(StringBuilder errorMsg, String paramName, Double value){
		boolean result = true;
		if(value !=null && value==0){
			result = false;
			if(errorMsg != null){
				errorMsg.append(paramName + "[" + value + "] is notZero;");
			}
		}
		return result;
	}
	
	public static boolean  notZero(StringBuilder errorMsg, String paramName, Long value){
		boolean result = true;
		if(value !=null && value==0){
			result = false;
			if(errorMsg != null){
				errorMsg.append(paramName + "[" + value + "] is notZero;");
			}
		}
		return result;
	}
	
	
	/**
	 * 验证数据为非负数  is null 不做验证
	 * @param errorMsg
	 * @param paramName
	 * @param value
	 * @return
	 */
	public static boolean  notNegative(StringBuilder errorMsg, String paramName, Double value){
		boolean result = true;
		if(value !=null && value<0){
			result = false;
			if(errorMsg != null){
				errorMsg.append(paramName + "[" + value + "] is negative;");
			}
		}
		return result;
	}
	
	public static boolean  notNegative(StringBuilder errorMsg, String paramName, Long value){
		boolean result = true;
		if(value !=null && value<0){
			result = false;
			if(errorMsg != null){
				errorMsg.append(paramName + "[" + value + "] is negative;");
			}
		}
		return result;
	}
}
