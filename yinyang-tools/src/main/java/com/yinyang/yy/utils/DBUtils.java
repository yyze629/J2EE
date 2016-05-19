package com.yinyang.yy.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 
 * @Title: DBUtils.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午4:47:16
 * @version V1.0
 */
public class DBUtils {
	private static final String FROM = "from";
	private static final String WHERE = "where";
	private static final String PREFIX = "_";
	/**
	 * 登录用户名与ID映射分表sql处理
	 * @param userName
	 * @return
	 */
	public static  String processMapTableSql(String sql,String userName) {
		String mtable = null;
		if (StringUtils.isNotBlank(userName)) {
			mtable = userName.substring(userName.length() - 1, userName.length());
			mtable = processTableName(sql, mtable);
		}
		return mtable;
	}

	/**
	 * 处理用户分表sql，
	 *  @param sql
	 * @param userId
	 * @return
	 */
	public static  String processUserTableSql(String sql,String userId) {
		if (userId.length() <= 2) {
			return processTableName(sql, "00");
		} else {
			return processTableName(sql, userId.substring(userId.length() - 2, userId.length()));
		}
	}

	/**
	 * 替换表名
	 * @param sql
	 * @param sname
	 * @return
	 */
	private static  String processTableName(String sql, String sname) {
		String tempSql = sql.toLowerCase();
		String tempName = tempSql.substring(tempSql.indexOf(FROM)+FROM.length(), tempSql.indexOf(WHERE)).trim();
		return tempSql.replace(tempName, tempName + PREFIX + sname);
	}
}
