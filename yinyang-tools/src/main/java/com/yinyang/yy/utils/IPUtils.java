package com.yinyang.yy.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
  * 获取客户端真实IP地址工具类
  * <br>http://developer.51cto.com/art/201111/305181.htm</br>
  * @author  作者:尹洋  E-mail:yinyang@shujutang.com
  * @date 创建时间：2015年8月13日 下午2:09:07 
  * @version 1.0 
  */
public class IPUtils {
	
	private static Log logger = LogFactory.getLog(IPUtils.class);
	
	/**
	 * 简单获取IP地址
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) { 
			return request.getRemoteAddr(); 
		} 
		return request.getHeader("x-forwarded-for"); 
	} 
	
	/**
	 * 获取IP地址[全面]
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		
	    String ip= null; 
	    try {
			ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-Forwarded-For");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip != null &&isIpAddress(ip)) {
	            return ip;
	        }
			
			
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			} 
		} catch (Exception e) {
			logger.error("获取客户端真实IP地址工具类异常,", e);
		}
		return ip; 
	}
	
	/**
     * ip校验
     * @param s
     * @return Boolean
     */
    public static Boolean isIpAddress(String s){
            String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(s);
            return m.matches();
    }

    /**
     * 获取客户端ip
     * @param request
     * @return String
     */
    public static String getClientAddress(HttpServletRequest request) {
        String address = request.getHeader("X-Forwarded-For");
        if (address != null &&isIpAddress(address)) {
            return address;
        }
        return request.getRemoteAddr();
    }
}
