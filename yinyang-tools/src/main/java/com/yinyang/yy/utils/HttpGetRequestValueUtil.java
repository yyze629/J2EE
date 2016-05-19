package com.yinyang.yy.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Http获取参数值工具类
 * 
 * @Title: CommUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午4:11:34
 * @version V1.0
 */
public class HttpGetRequestValueUtil {
	private static final Log logger = LogFactory.getLog(HttpGetRequestValueUtil.class);
 
    /**
	 * 获取参数
	 * 
	 * @param request
	 * @param attrName
	 */
    public static String getParamVal(HttpServletRequest request, String attrName) {
		String retVal = (String) request.getParameter(attrName);
		if (retVal == null) {
			return "";
		} else {
			return retVal;
		}
	}
    
    /**
	 * 获取参数
	 * 
	 * @param request
	 * @param attrName
	 * @param defaultValue 
	 */
    public static String getParamVal(HttpServletRequest request, String attrName, String defaultValue) {
		String retVal = (String) request.getParameter(attrName);
		if (retVal == null) {
			return defaultValue;
		} else {
			return retVal;
		}
	}
    
    /**
     * 获取参数,以map形式
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String,String> getMapParams(HttpServletRequest request){
		Map<String,String> params = new HashMap<String,String>();
		for(Map.Entry<String, String[]> entry: (Set<Map.Entry<String, String[]>>)request.getParameterMap().entrySet()){
			String key = entry.getKey();
			String value = entry.getValue()[0];
			if(value == null){
				value = "";
			}
			params.put(key,value);
		}
		return params;
    }
    
	/**
	 * 返回字符串
	 * @param response
	 * @param ret
	 */
    public static void responseRet(HttpServletResponse response, String ret) {
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(ret);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("CommUtil.responseRet(+ret+)", e);
		}
	}
    
    /**
     * 返回JSON字符串
     * @param response
     * @param ret
     */
    public static void responseJsonRet(HttpServletResponse response, String ret) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(ret);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("CommUtil.responseJsonRet(+ret+)", e);
		}
    }
    
    /**
     * 生成MD5散列串
     * @param source
     * @return
     */
    public static String genMD5(String source){
    	return DigestUtils.md5Hex(source);
    }
    
    /**
     * 使用JAVA UUID生成唯一串
     * @return
     */
    public static String genUUID(){
		UUID uuid = UUID.randomUUID();
		return StringUtils.remove(uuid.toString(), '-');
    }
    
    /**
     * 返回当前时间（格式yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static String curTime(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
    }
    /**
     * 返回以当前时间为基准的新时间 （格式yyyy-MM-dd HH:mm:ss）
     *  eg：CommUtil.getTime(Calendar.HOUR_OF_DAY,1); 返回当前时间一个小时候时间
     * @param type    类型参考 Calendar
     * @param amount  改变的度量值
     * @return
     */
    public static String getTime(int type,int amount){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(type, amount);
    	return format.format(calendar.getTime());
    }
    
    /**
     * test
     * @param args
     */
    public static void main(String[] args){
    	/*String source = "sunny123"+System.currentTimeMillis();
    	System.out.println(genMD5(source));
    	System.out.println(genUUID());*/
    	System.out.println(getTime(Calendar.HOUR_OF_DAY,1));
    }
    
    
}
