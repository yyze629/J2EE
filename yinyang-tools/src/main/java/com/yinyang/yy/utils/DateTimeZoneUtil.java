package com.yinyang.yy.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * 时区日期时间工具类
 * 
 * @Title: DateTimeZoneUtil.java
 * @Package com.dhgate.shopify.web.util.searchlist
 * @author yinyang@dhgate.com
 * @date 2016年4月29日 下午7:24:36
 * @version V1.0
 */
public final class DateTimeZoneUtil {

	private static final Logger log = Logger.getLogger(DateTimeZoneUtil.class);

	/** 日期格式，yyyyMMddhhmmss: "yyyy-MM-dd HH:mm:ss" */
	public static final String yyyyMMddhhmmssStr = "yyyy-MM-dd HH:mm:ss";

	/** 日期格式，yyyyMMddhhmmss: "yyyyMMddhhmmss" */
	public static final String yyyyMMddhhmmss = "yyyyMMddHHmmss";
	/** HH:mm:ss */
	public static final String hhmmssStr = "HH:mm:ss";

	/** 日期格式，yyyyMMddhhmmsss: "yyyy-MM-dd HH:mm:ss:s" */
	public static final String yyyyMMddhhmmsssStr = "yyyy-MM-dd HH:mm:ss:s";

	/** 日期格式，MMddhhmm: "MM-dd HH:mm" */
	public static final String MMddhhmmStr = "MM-dd HH:mm";

	/** 日期格式，yyyymmddhhmm: "yyyy-MM-dd HH:mm" */
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	
	public static final String YYYYMMDDHHMMSS_GLOBAL = "yyyy-MM-dd'T'HH:mm:ssXXX";
	
	/** Apr 21, 2016 2:16:55 AM */
	public static final String MMMDYYYYHHMM = "MMM d, yyyy K:m:s a";// "hh:mm:ss a";
	
	/** Tuesday, 6:13am 格式 */
	public static final String WHHMM = "E, K:m a";// "E, K:m:s a">>>Thu, 2:16:55 AM
	
	
	/**
	 * 日期处理 
	 * @param dataString 2016-04-19T05:27:24-04:00
	 * @return
	 * @throws ParseException
	 */
	public static Date getShopifyDate(String dataString){
		Date date = null;
		try {
			if(StringUtils.isNotBlank(dataString)){
				DateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSS_GLOBAL);
				date = df.parse(dataString);
			}
		} catch (Exception e) {
			log.error("日期解析失败,入参["+dataString+"]...");
		}
		return date;
	}
	
    /**
     * 取北京时间
     * @return
     */
    public static String getBeijingTime(){
        return getFormatedDateString(8);
    }
    
    /**
     * 取班加罗尔时间
     * @return
     */
    public static String getBangaloreTime(){
        return getFormatedDateString(5.5f);
    }
    
    /**
     * 取纽约时间
     * @return
     */
    public static String getNewyorkTime(){
        return getFormatedDateString(-5);
    }
    
	
    /**
     * 获取当前时区时间
	 * timeZoneOffset原为int类型，为班加罗尔调整成float类型
     * timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8
     * @param timeZoneOffset
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getFormatedDateString(float timeZoneOffset){
        if (timeZoneOffset > 13 || timeZoneOffset < -12) {
            timeZoneOffset = 0;
        }
        
        int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
        TimeZone timeZone;
        String[] ids = TimeZone.getAvailableIDs(newTime);
        if (ids.length == 0) {
            timeZone = TimeZone.getDefault();
        } else {
            timeZone = new SimpleTimeZone(newTime, ids[0]);
        }
    
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
        sdf.setTimeZone(timeZone);
        return sdf.format(new Date());
    }
    
    /**
     * 转换为某时区时间
	 * timeZoneOffset原为int类型，为班加罗尔调整成float类型
     * timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8
     * @param date 待转换时间
     * @param timeZoneOffset
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String getFormatedDateString(Date date ,float timeZoneOffset){
    	if(date == null){
    		return null;
    	}
        if (timeZoneOffset > 13 || timeZoneOffset < -12) {
            timeZoneOffset = 0;
        }
        
        int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
        TimeZone timeZone;
        String[] ids = TimeZone.getAvailableIDs(newTime);
        if (ids.length == 0) {
            timeZone = TimeZone.getDefault();
        } else {
            timeZone = new SimpleTimeZone(newTime, ids[0]);
        }
    
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }
    
    /**
     * 获取当前时区时间
     * timeZoneOffset原为int类型，为班加罗尔调整成float类型
     * timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8
     * @param dateString 待转换时间
     * @param timeZoneOffset
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static Date getFormatedDateString(String dateString ,float timeZoneOffset){
    	if(StringUtils.isBlank(dateString)){
    		return null;
    	}
    	if (timeZoneOffset > 13 || timeZoneOffset < -12) {
    		timeZoneOffset = 0;
    	}
    	
    	Date date = null;
		try {
			int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
			TimeZone timeZone;
			String[] ids = TimeZone.getAvailableIDs(newTime);
			if (ids.length == 0) {
				timeZone = TimeZone.getDefault();
			} else {
				timeZone = new SimpleTimeZone(newTime, ids[0]);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
			sdf.setTimeZone(timeZone);
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			log.error("获取当前时区时间异常,",e);
		}
    	return date;
    }
    
    /**
     * 获取当前时区时间
     * timeZoneOffset原为int类型，为班加罗尔调整成float类型
     * timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8
     * @param dateString 待转换时间
     * @param format 格式 yyyy-MM-dd HH:mm:ss
     * @param timeZoneOffset
     * @return 默认yyyy-MM-dd HH:mm:ss
     */
    public static Date getFormatedDateString(String dateString ,String format, float timeZoneOffset){
    	if(StringUtils.isBlank(dateString)){
    		return null;
    	}
    	if(StringUtils.isBlank(format)){
    		format = YYYYMMDDHHMMSS;
    	}
    	if (timeZoneOffset > 13 || timeZoneOffset < -12) {
    		timeZoneOffset = 0;
    	}
    	
    	Date date = null;
    	try {
    		int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
    		TimeZone timeZone;
    		String[] ids = TimeZone.getAvailableIDs(newTime);
    		if (ids.length == 0) {
    			timeZone = TimeZone.getDefault();
    		} else {
    			timeZone = new SimpleTimeZone(newTime, ids[0]);
    		}
    		
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		sdf.setTimeZone(timeZone);
    		date = sdf.parse(dateString);
    	} catch (ParseException e) {
    		log.error("获取当前时区时间异常,",e);
    	}
    	return date;
    }
    
    /**
     * 获取当前时区时间
     * timeZoneOffset原为int类型，为班加罗尔调整成float类型
     * timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8
     * @param date 待转换时间
     * @param format 格式 yyyy-MM-dd HH:mm:ss
     * @param timeZoneOffset
     * @return 默认yyyy-MM-dd HH:mm:ss
     */
    public static String getFormatedDateString(Date date ,String format, float timeZoneOffset){
    	if(date == null){
    		return null;
    	}
    	if(StringUtils.isBlank(format)){
    		format = YYYYMMDDHHMMSS;
    	}
    	if (timeZoneOffset > 13 || timeZoneOffset < -12) {
    		timeZoneOffset = 0;
    	}
    	
    	String dateTarget = null;
    	try {
    		int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
    		TimeZone timeZone;
    		String[] ids = TimeZone.getAvailableIDs(newTime);
    		if (ids.length == 0) {
    			timeZone = TimeZone.getDefault();
    		} else {
    			timeZone = new SimpleTimeZone(newTime, ids[0]);
    		}
    		
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		sdf.setTimeZone(timeZone);
    		dateTarget = sdf.format(date);
    	} catch (Exception e) {
    		log.error("获取当前时区时间异常,",e);
    	}
    	return dateTarget;
    }
    
    /**
     * 获取当前时区时间
     * timeZoneOffset原为int类型，为班加罗尔调整成float类型
     * timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8
     * @param date 待转换时间
     * @param format 格式 yyyy-MM-dd HH:mm:ss
     * @param local English or CN
     * @param timeZoneOffset
     * @return 默认yyyy-MM-dd HH:mm:ss
     */
    public static String getFormatedDateString(Date date ,String format, Locale local, float timeZoneOffset){
    	if(date == null){
    		return null;
    	}
    	if(StringUtils.isBlank(format)){
    		format = YYYYMMDDHHMMSS;
    	}
    	if (timeZoneOffset > 13 || timeZoneOffset < -12) {
    		timeZoneOffset = 0;
    	}
    	
    	String dateTarget = null;
    	try {
    		int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
    		TimeZone timeZone;
    		String[] ids = TimeZone.getAvailableIDs(newTime);
    		if (ids.length == 0) {
    			timeZone = TimeZone.getDefault();
    		} else {
    			timeZone = new SimpleTimeZone(newTime, ids[0]);
    		}
    		
    		SimpleDateFormat sdf = new SimpleDateFormat(format,local);
    		sdf.setTimeZone(timeZone);
    		dateTarget = sdf.format(date);
    	} catch (Exception e) {
    		log.error("获取当前时区时间异常,",e);
    	}
    	return dateTarget;
    }
    
    public String getAMOrPM(Date date){
    	
    	GregorianCalendar ca = new GregorianCalendar();  
    	System.out.println(ca.get(GregorianCalendar.AM_PM));  
    	
    	return "";
    }
    
    
    public static void main(String[] args) {
    	String source = "2016-04-21T03:16:55-04:00";
		Date shopifyDate = getShopifyDate(source);
		
		System.out.println(">>"+source);
		System.out.println(">>"+shopifyDate);
		String beijing = getFormatedDateString(shopifyDate,8);
		System.out.println("beijing:"+beijing);
		System.out.println("EDT:"+getFormatedDateString(beijing,-5));
		System.out.println("EDT target:"+getFormatedDateString(shopifyDate,WHHMM,Locale.ENGLISH, -5));
		
		String formatedDateString = getFormatedDateString(-4);
		System.out.println("-4>>"+formatedDateString);
		
		Date formatedDateString2 = getFormatedDateString(source,YYYYMMDDHHMMSS_GLOBAL, -5);
		System.out.println("-5>>"+formatedDateString2);
		
	}
}
