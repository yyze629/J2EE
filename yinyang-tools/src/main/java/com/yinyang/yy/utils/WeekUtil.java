package com.yinyang.yy.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 周的工具类
 * 
 * @Title: WeekUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午2:58:06
 * @version V1.0
 */
public class WeekUtil {

	/**
	 * 
	 * @Description: 得到某一年周的总数
	 *
	 * @param year
	 *            某年
	 * @return int 某年的周总数
	 * @create time 2013-6-6 下午1:43:07
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(c.getTime());
	}

	/**
	 * 
	 * @Description: 取得当前日期是多少周
	 *
	 * @param date
	 *            日期
	 * @return int 第几周
	 * @create time 2013-6-6 下午1:43:22
	 */
	public static int getWeekOfYear(Date date) {
		Date result = createDate(date);
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);// 星期日为一周的第一天
		c.setMinimalDaysInFirstWeek(4);
		c.setTime(result);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 
	 * @Description: 得到某年某周的第一天
	 *
	 * @param year
	 *            某年
	 * @param week
	 *            某周
	 * @return Date 某年某周的第一天
	 * @create time 2013-6-6 下午1:41:55
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * 
	 * @Description: 得到某年某周的最后一天
	 *
	 * @param year
	 *            某年
	 * @param week
	 *            某周
	 * @return Date 某年某周的最后一天
	 * @create time 2013-6-6 下午1:42:20
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getLastDayOfWeek(cal.getTime());
	}

	/**
	 * 
	 * @Description: 取得指定日期所在周的第一天
	 *
	 * @param date
	 *            日期
	 * @return Date 指定日期所在周的第一天
	 * @create time 2013-6-6 下午1:36:59
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Date result = createDate(date);
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(result);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 
	 * @Description: 取得指定日期所在周的最后一天
	 *
	 * @param date
	 *            日期
	 * @return Date 取得指定日期所在周的最后一天
	 * @create time 2013-6-6 下午1:37:33
	 */
	public static Date getLastDayOfWeek(Date date) {
		Date result = createDate(date);
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(result);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 
	 * @Description: 格式化日期
	 *
	 * @param date
	 *            日期
	 * @param formatStr
	 *            格式
	 * @return String
	 * @create time 2013-6-6 下午1:49:40
	 */
	public static String getDateStrByFormat(Date date, String formatStr) {
		String result = null;
		DateFormat format = new SimpleDateFormat(formatStr);
		if (date != null) {
			result = format.format(date);
		}
		return result;
	}

	/**
	 * 
	 * @Description: 创建日期
	 *
	 * @param date
	 * @return Date
	 * @create time 2013-6-6 下午4:05:17
	 */
	@SuppressWarnings("deprecation")
	public static Date createDate(Date date) {
		Date result = new Date();
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDate();

		result.setYear(year);
		result.setMonth(month);
		result.setDate(day);

		return result;
	}

}
