package com.yinyang.sellerpay.web.util;

import org.apache.commons.lang.StringUtils;

/**
 * 简单的验证规则
 * @author yangqin@dhgate.com
 * 
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
	/**
	  * 判断给定值是否在给定的区间内。
	  * 注：一键修改项目新增。
	  * @param errorMsg
	  * @param value
	  * @param min
	  * @param max
	  * @return
	  */
	 public static boolean valueInRange(StringBuilder errorMsg, String paramName, int value, int min,int max){
		 boolean result = true;
		 if(value < min || value > max){
			result = false;
			if(errorMsg != null){
				errorMsg.append(paramName+"("+value+") not in permitted range; ");
			}
		 }
		 return result;
	 }
	 /**
	  * 判断给定值是否在给定的区间内。
	  * 注：一键修改项目新增。
	  * @param errorMsg
	  * @param value
	  * @param min
	  * @param max
	  * @return
	  */
	 public static boolean valueInRange(StringBuilder errorMsg, String paramName, double value, double min,double max){
		 boolean result = true;
		 if(value < min || value > max){
			result = false;
			if(errorMsg != null){
				errorMsg.append(paramName+"("+value+") not in permitted range; ");
			}
		 }
		 return result;
	 }
}
