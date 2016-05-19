package com.yinyang.yy.utils;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * JDBC工具类
 * 
 * @author 作者：yinyang E-mail:
 * @date 创建时间：2015年4月15日 下午4:41:55
 * @version 1.0
 */
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class JdbcUtil {

	private static Log logger = LogFactory.getLog(JdbcUtil.class);
	private static QueryRunner qr = new QueryRunner();

	/**
	 * 查询
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> search(Connection conn, String sql, Object[] params) {
		List<Map<String, Object>> mapList = null;
		try {
			if (params == null) {
				mapList = qr.query(conn, sql, new MapListHandler());
			} else {
				mapList = qr.query(conn, sql, params, new MapListHandler());
			}
		} catch (Exception e) {
			logger.error("查询出现异常!原因:" + e);
		}
		return mapList;
	}

	/**
	 * 查询,将结果返回到bean中,多个bean通过List包装返回
	 * 
	 * @param <T>
	 * @param conn
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return List<T>
	 */
	public static <T> List<T> search(Connection conn, Class<T> entityClass, String sql, Object[] params) {
		List<T> list = null;
		try {
			if (params == null) {
				list = (List<T>) qr.query(conn, sql, new BeanListHandler(entityClass));
			} else {
				list = (List<T>) qr.query(conn, sql, new BeanListHandler(entityClass), params);
			}
		} catch (Exception e) {
			logger.error("查询出现异常!原因:" + e);
		}
		return list;
	}

	/**
	 * 通过主键查找记录
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> getById(Connection conn, String sql, Object[] params) {
		Map<String, Object> map = null;
		try {
			if (params == null) {
				map = qr.query(conn, sql, new MapHandler());
			} else {
				map = qr.query(conn, sql, new MapHandler(), params);
			}
		} catch (Exception e) {
			logger.error("查询出现异常!原因:" + e);
		}
		return map;
	}

	/**
	 * eg: select count(1) from user
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int count(Connection conn, String sql, Object[] params) {

		Object o = getAnAttr(conn, sql, params);
		if (o instanceof Integer) {
			return (Integer) o;
		}
		if (o instanceof Long) {
			Long l = (Long) o;
			return l.intValue();
		}

		String s = (String) o;
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 获得第一个查询第一行第一列
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Object getAnAttr(Connection conn, String sql, Object[] params) {

		Object s = null;
		try {
			s = qr.query(conn, sql, new ScalarHandler(1), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 查询返回单个对象
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "finally" })
	public static <T> T queryForObject(Connection conn, String sql, Object param[], Class<T> clazz) {
		T obj = null;
		try {
			obj = (T) qr.query(conn, sql, new BeanHandler(clazz), param);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			return obj;
		}

	}

	/**
	 * 查询表的记录总数
	 * 
	 * @param conn
	 * @param table
	 * @return int
	 */
	public static int getTotal(Connection conn, String table) {
		int total = 0;
		String field = "total";
		if (conn != null) {
			String sql = "SELECT COUNT(*) AS " + field + " FROM " + table;
			try {
				Map<String, Object> map = qr.query(conn, sql, new MapHandler());
				String n = map.get(field).toString();

				total = Integer.parseInt(n);
			} catch (Exception e) {
				logger.error("查询[" + table + "]表记录总数出现异常!原因:" + e);
			}
		} else {
			logger.error("查询[" + table + "]表记录总数出现异常!原因:连接为空!");
		}
		return total;
	}

	/**
	 * insert or update delete语句执行
	 * 
	 * @param conn
	 * @param sql
	 * @return
	 */
	public static int update(Connection conn, String sql) {
		int total = 0;

		if (conn != null) {

			try {
				total = qr.update(conn, sql);
			} catch (Exception e) {
				logger.error("查询sql=[" + sql + "]表记录总数出现异常!原因:" + e);
				return -1;
			}
		} else {
			logger.error("查询sql=[" + sql + "]表记录总数出现异常!原因:连接为空!");
		}
		return total;
	}

	/*** ----------------------------- add page ------------------------ ***/

	/**
	 * <T>分页查询
	 * 
	 * @param beanClass
	 * @param sql
	 * @param page
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public <T> List<T> findPage(Class<T> beanClass, String sql, int page, int pageSize, Object... params) {
		if (page <= 1) {
			page = 0;
		}
		return query(beanClass, sql + " LIMIT ?,?", ArrayUtils.addAll(params, new Integer[] { page, pageSize }));
	}

	public <T> List<T> query(Class<T> beanClass, String sql, Object... params) {
		try {
			qr = new QueryRunner();
			return (List<T>) qr.query(sql, isPrimitive(beanClass) ? columnListHandler : new BeanListHandler(beanClass), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("serial")
	private ArrayList<Class<?>> PrimitiveClasses = new ArrayList<Class<?>>() {
		{
			add(Long.class);
			add(Integer.class);
			add(String.class);
			add(java.util.Date.class);
			add(java.sql.Date.class);
			add(java.sql.Timestamp.class);
		}
	};

	/**
	 * 返回单一列时用到的handler
	 */
	private final static ColumnListHandler columnListHandler = new ColumnListHandler() {
		@Override
		protected Object handleRow(ResultSet rs) throws SQLException {
			Object obj = super.handleRow(rs);
			if (obj instanceof BigInteger)
				return ((BigInteger) obj).longValue();
			return obj;
		}

	};

	/**
	 * 判断是否为原始类型
	 * 
	 * @param cls
	 * @return
	 */
	private boolean isPrimitive(Class<?> cls) {
		return cls.isPrimitive() || PrimitiveClasses.contains(cls);
	}
}
