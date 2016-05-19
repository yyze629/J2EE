package com.yinyang.yy.utils;

import java.util.List;

/**
 * 
 * @Description: 集合工具类
 * @Title: ListUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午2:39:41
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
public class ListUtil {

	/**
	 * 
	* @Description: 将列表转换成数组
	*
	* @param list
	* @return String[]
	* @create time 2013-5-29 上午10:56:16
	 */
	public static String[] toArray(List list) {
		String[] array = null;
		
		if (list == null) {
			return null;
		}

		int size = list.size();
		if (size == 0) {
			return null;
		}
		array = new String[size];

		Object[] objs = list.toArray();

		for (int i = 0; i < objs.length; i++) {
			array[i] = objs[i].toString();
		}

		return array;
	}
}
