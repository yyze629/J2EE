package com.yinyang.sellerpay.web.util;

import org.apache.commons.lang.StringUtils;


public class DatabaseStateUtil {
	public final static String KEY_OF_MYSQLDB = "dbmysql.db.product";
	public final static String GROUP = "com.dhgate.relay.config";
	public final static String DATA_ID = "apsarasrelay.properties";
	public final static int Database_available = 3;
	/***
	 * diamond设置mysql数据库是否可用，0完全不可用，1只读，2只写，其它为完全可用
	 * */
	public int diamondMysqlDb(){
		int result = Database_available;
		String dbValue = DHDiamondManager.getDiamondPropertiesValue(GROUP,DATA_ID,KEY_OF_MYSQLDB);
		if(StringUtils.isNotBlank(dbValue)){
			if("1".equals(dbValue.trim())){
				result = 1;
			}else if("2".equals(dbValue.trim())){
				result = 2;
			}else if("0".equals(dbValue.trim())){
				result = 0;
			}
		}
		return result;
	}

}
