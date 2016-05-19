package com.yinyang.yy.utils;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * map打印工具类
 * 
 * @Title: MapUtils.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午2:18:13
 * @version V1.0
 */
public class MapUtils {
	
	private static Logger logger  = Logger.getLogger(MapUtils.class);
	
	public static void print(Map<String, ?> map){
		if(map == null){
			return ;
		}
		Iterator<String> keys = map.keySet().iterator();
		boolean bool = true;
		while(keys.hasNext()){
			bool = false;
			String key = keys.next();
			logger.debug(key + " : " + map.get(key));
		}
		if(bool){
			logger.debug("MAP是空的");
		}
	}
	
	public static void printSystem(Map<String, ?> map){
		if(map == null){
			return ;
		}
		
		Iterator<String> keys = map.keySet().iterator();
		boolean bool = true;
		while(keys.hasNext()){
			bool = false;
			String key = keys.next();
			logger.debug(key + " : " + map.get(key));
		}
		if(bool){
			logger.debug("MAP是空的");
		}
	}
}
