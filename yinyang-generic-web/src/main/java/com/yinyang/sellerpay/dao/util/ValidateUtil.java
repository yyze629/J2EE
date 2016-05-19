package com.yinyang.sellerpay.dao.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.dhgate.common.util.StringUtil;

/**
 * 简单的验证规则
 * @author 
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
	public static boolean required(StringBuilder errorMsg, String paramName, String value){
		boolean result = true;
		if(value==null  || StringUtils.isBlank(String.valueOf(value))){
			result = false;
			if(errorMsg != null){
				errorMsg.append(paramName + " is blank;");
			}
		}
		return result;
	}
	
	/***
	 * 验证数据是否为空
	 * @param errorMsg
	 * @param paramName
	 * @param value
	 * @return
	 */
	public static boolean required(StringBuilder errorMsg, String paramName, Object value){
		boolean result = true;
		if(value==null){
			result = false;
			if(errorMsg != null){
				errorMsg.append(paramName + " is null;");
			}
		}
		return result;
	}
	
	/***
	 * 验证数据是否为空
	 * @param errorMsg
	 * @param paramName
	 * @param value
	 * @return
	 */
	public static boolean required(StringBuilder errorMsg, String paramName, List<Object> value){
		boolean result = true;
		if(value==null){
			result = false;
			if(errorMsg != null){
				errorMsg.append(paramName + " is null;");
			}
		}else if(value.size() <= 0){
			result = false;
			if(errorMsg != null){
				errorMsg.append(paramName + " list size is zero;");
			}
		}
		return result;
	}
	
	/***
	 * 验证数据是否为空
	 * @param errorMsg
	 * @param paramName
	 * @param value
	 * @return
	 */
	public static boolean required(StringBuilder errorMsg, String paramName, Object[] value){
		boolean result = true;
		if(value==null  || value.length<=0){
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
	 * 是否是正整数验证
	 * @param bot
	 * @return
	 */
	 public static boolean isMatches(String bot){
	  boolean flag=false;
	  try{
		   String regex="^[0-9]+[0-9]*$";
		   //^[1-9]+\\d*$
		   Pattern p=Pattern.compile(regex);
		   Matcher m=p.matcher(bot);
		   if(m.find()){
			   return true;
		   }
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  return flag;
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
	 /***
	  * 判断List是否为null,size是否为0；为null和为0时都返回0
	  * */
	 public static int ListSize(List param){
		 if(param == null){
			 return 0;
		 }
		 return param.size();
	 }
	 /***
	  * 判断List是否为null,size是否为0；为null和为0时都返回0
	  * */
	 public static int MapSize(Map param){
		 if(param == null){
			 return 0;
		 }
		 return param.size();
	 }
	 /***
	  * 判断字符串的长度
	  * */
	 public static int StrSize(String param){
		 if(StringUtil.isBlank(param)){
			 return 0;
		 }
		 return param.trim().length();
	 }
}
