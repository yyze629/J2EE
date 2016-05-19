package com.yinyang.yy.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 结果集对象
 * 
 * @Title: ResultSet.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午3:03:52
 * @version V1.0
 */
@SuppressWarnings({ "serial", "rawtypes","unchecked" })
public class ResultSet implements Serializable, Cloneable {
	private List infolist = new ArrayList();

	/**
	 * 构造器
	 */
	public ResultSet() {

	}

	/**
	 * 添加一个对象
	 * 
	 * @param obj
	 */
	public void add(Object obj) {
		infolist.add(obj);
	}

	/**
	 * 返回一个string类型的值
	 * 
	 * @param index
	 * @return
	 */
	public String getString(int index) {
		return (String) infolist.get(index);
	}

	/**
	 * 返回一个int类型的值
	 * 
	 * @param index
	 * @return
	 */
	public int getInt(int index) {
		String str = getString(index);
		if (str != null) {
			try {
				return Integer.parseInt(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * 返回一个long类型的值
	 * 
	 * @param index
	 * @return
	 */
	public long getLong(int index) {
		String str = getString(index);
		if (str != null) {
			try {
				return Long.parseLong(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * 返回一个double类型的值
	 * 
	 * @param index
	 * @return
	 */
	public double getDouble(int index) {
		String str = getString(index);
		if (str != null) {
			try {
				return Double.parseDouble(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * 返回一个date类型的值
	 * 
	 * @param index
	 * @return
	 */
	public Date getDate(int index) {
		String str = getString(index);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
		}

		return date;
	}

	/**
	 * 返回一个date类型的值
	 * 
	 * @param index
	 *            序列号
	 * @param format
	 *            要格式化的方式
	 * @return
	 */
	public Date getDate(int index, String format) {
		String str = getString(index);

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
		}

		return date;
	}

	/**
	 * 返回一个boolean类型的值
	 * 
	 * @param index
	 * @return
	 */
	public boolean getBoolean(int index) {
		String str = getString(index);

		if (str != null) {
			if ("true".equalsIgnoreCase(str)) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	/**
	 * 返回一个对象
	 * 
	 * @param index
	 * @return
	 */
	public Object getObject(int index) {
		return infolist.get(index);
	}

	/**
	 * 返回数据量
	 * 
	 * @return
	 */
	public int size() {
		return infolist.size();
	}

	/**
	 * 返回数据集合
	 * 
	 * @return
	 */
	public List getList() {
		return infolist;
	}

	/**
	 * 克隆自身
	 * 
	 * @return
	 */
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

}