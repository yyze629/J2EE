package com.yinyang.yy.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 日期工具类
 * 自己扩展总结
 * @Title: DateUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午2:31:38
 * @version V1.0
 */
public final class DateUtil {

	public static final String DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";
	public static final String DATE_FORMAT_COMPACT = "yyyyMMdd";
	public static final String DATE_FORMAT_COMPACTFULL = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_FULL_MSEL = "yyyyMMddHHmmssSSSS";
	public static final String DATE_YEAR_MONTH = "yyyyMM";
	
	/** from b2b2c web */
	public static SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdfYMDHMS2 = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
	public static SimpleDateFormat sdfYMD000 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
	public static SimpleDateFormat sdfYM = new SimpleDateFormat("yyyy-MM");
	public static SimpleDateFormat sdfYM2 = new SimpleDateFormat("yyyyMM");
	public static SimpleDateFormat sdfW = new SimpleDateFormat("w");
	public static SimpleDateFormat sdfD = new SimpleDateFormat("dd");
	public static SimpleDateFormat sdfE = new SimpleDateFormat("E"); // 星期四
	public static SimpleDateFormat sdfMD = new SimpleDateFormat("MMdd");
	public static SimpleDateFormat sdfMDHM = new SimpleDateFormat("MM-dd HH:mm");
	public static SimpleDateFormat sdfHM = new SimpleDateFormat("HH:mm");
	public static SimpleDateFormat sdfYMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static SimpleDateFormat sdfS = new SimpleDateFormat("ss");
	public static SimpleDateFormat sdfYMD2 = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 一天的毫秒数
	 */
	public final static long DAYTIMES = 24 * 60 * 60 * 1000;
	/**
	 * 一小时的毫秒数
	 */
	public final static long HOURTIMES = 60 * 60 * 1000;
	/**
	 * 一分钟的毫秒数
	 */
	public final static long MINUTETIMES = 60 * 1000;

	/**
	 * 获取现在的时间字符串形式，格式如："yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return 时间字符串
	 */
	public static String getDateNowYMDHMS() {
		SimpleDateFormat sdfYMDHMSNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdfYMDHMSNew.format(new Date());
	}

	/**
	 * 获取指定时间的字符串形式，格式如："yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param date
	 *            指定时间
	 * @return 时间字符串
	 */
	public static String getDateYMDHMS(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdfYMDHMSNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdfYMDHMSNew.format(date);
	}

	/**
	 * 获取指定时间的字符串形式，格式如："yyyyMM"
	 * 
	 * @param date
	 *            指定时间
	 * @return 时间字符串
	 */
	public static String getDateYM2(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdfYM2New = new SimpleDateFormat("yyyyMM");
		return sdfYM2New.format(date);
	}

	/**
	 * 获取指定时间的字符串形式，格式如："yyyy-MM-dd"
	 * 
	 * @param date
	 *            指定时间
	 * @return 时间字符串
	 */
	public static String getDateYMD2(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdfYMDHMSNew = new SimpleDateFormat("yyyy-MM-dd");
		return sdfYMDHMSNew.format(date);
	}

	/**
	 * 获取指定时间的字符串形式，格式如："yyyyMMdd"
	 * 
	 * @param date
	 *            指定时间
	 * @return 时间字符串
	 */
	public static String getDateYMD(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdfYMDHMSNew = new SimpleDateFormat("yyyyMMdd");
		return sdfYMDHMSNew.format(date);
	}

	/**
	 * 获取指定时间的字符串形式，格式如："HH"
	 * 
	 * @param date
	 *            指定时间
	 * @return 小时数（24）
	 */
	public static String getDateHH(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdfYMDHMSNew = new SimpleDateFormat("HH");
		return sdfYMDHMSNew.format(date);
	}

	/**
	 * 获得昨天 日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getYesterdayDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 000);

		String yesterday = new SimpleDateFormat(DATA_FORMAT).format(cal.getTime());

		return yesterday;
	}

	/**
	 * 获得昨天 00:00:00
	 * 
	 * @return
	 */
	public static Date getYesterdayMorning() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 000);

		return cal.getTime();
	}

	/**
	 * 获得昨天 23:59:59
	 * 
	 * @return
	 */
	public static Date getYesterdayEvening() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return cal.getTime();
	}

	public static Date StrToDateYYYYMMDDHHMMSS(String sDate) {
		java.util.Date date = null;
		String pattern = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			date = format.parse(sDate);
		} catch (ParseException e) {
		}
		return date == null ? null : date;
	}

	/**
	 * 获取上个月今天的开始时间
	 * 
	 * @return
	 */
	public static Date getPreMonthStartTime() {
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.MONTH, -1);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		return cl.getTime();
	}

	public static Date getDateByFormat(String dateStr, String formatStr) {
		Date result = null;
		DateFormat format = new SimpleDateFormat(formatStr);
		if (dateStr != null) {
			try {
				result = format.parse(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;

	}

	public static Date getDate(String datetimeStr) {
		Date result = null;
		if (StringUtils.isBlank(datetimeStr)) {
			return result;
		}
		datetimeStr = datetimeStr.trim();

		if (datetimeStr != null && datetimeStr.trim().length() == 10) {
			String reg1 = "/^(\\d{4})(-)(\\d{1,2})\\2(\\d{1,2})$/";
			boolean b = Pattern.matches(reg1, datetimeStr);
			if (b) {
				result = DateUtil.getDateByFormat(datetimeStr, "yyyy-MM-dd");
			} else {
				result = DateUtil.getDateByFormat(datetimeStr, "yyyy.MM.dd");
			}

		} else if (datetimeStr != null && datetimeStr.trim().length() == 13) {
			String reg = "/^(\\d{4})(-)(\\d{1,2})\\2(\\d{1,2}) (\\d{1,2})$/";
			boolean b = Pattern.matches(reg, datetimeStr);

			if (b) {
				result = DateUtil.getDateByFormat(datetimeStr, "yyyy-MM-dd HH");
			} else {
				result = DateUtil.getDateByFormat(datetimeStr, "yyyy.MM.dd HH");
			}

		} else if (datetimeStr != null && datetimeStr.trim().length() == 16) {
			String reg = "/^(\\d{4})(-)(\\d{1,2})\\2(\\d{1,2}) (\\d{1,2}):(\\d{1,2})$/";
			boolean b = Pattern.matches(reg, datetimeStr);

			if (b) {
				result = DateUtil.getDateByFormat(datetimeStr, "yyyy-MM-dd HH:mm");
			} else {
				result = DateUtil.getDateByFormat(datetimeStr, "yyyy.MM.dd HH:mm");
			}

		} else if (datetimeStr != null && datetimeStr.trim().length() == 19) {
			String reg = "/^(\\d{4})(-)(\\d{1,2})\\2(\\d{1,2}) (\\d{1,2}):(\\d{1,2}):(\\d{1,2})$/";
			boolean b = Pattern.matches(reg, datetimeStr);
			if (b) {
				result = DateUtil.getDateByFormat(datetimeStr, "yyyy-MM-dd HH:mm:ss");
			} else {
				result = DateUtil.getDateByFormat(datetimeStr, "yyyy.MM.dd HH:mm:ss");
			}

		}
		return result;
	}

	public static String getDateStrByFormat(Date date, String formatStr) {
		String result = null;
		DateFormat format = new SimpleDateFormat(formatStr);
		if (date != null) {
			result = format.format(date);
		}
		return result;
	}

	public static boolean isSameDay(Date a, Date b) {
		if (a == null || b == null) {
			return false;
		}

		boolean result = false;
		a = DateUtils.truncate(a, Calendar.DAY_OF_MONTH);
		b = DateUtils.truncate(b, Calendar.DAY_OF_MONTH);

		if (a.getTime() == b.getTime()) {
			result = true;
		}

		return result;

	}

	public static Date addDay(Date date, int addValue) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, addValue);
		return calendar.getTime();
	}

	public static Date subDay(Date date, int subValue) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, subValue * -1);
		return calendar.getTime();
	}

	public static Date addTime(Date date, int addValue) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, addValue);
		return calendar.getTime();
	}

	public static Date truncateTime(Date date) {
		return DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
	}

	public static int getTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static Date getEndDate(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);
			return cal.getTime();
		} else {
			return date;
		}
	}

	// 闰年2月有问题
	/*
	 * public static Date getMonthEndDate(Date date) { if (date != null) {
	 * Calendar cal = Calendar.getInstance(); cal.setTime(date);
	 * cal.set(Calendar.DAY_OF_MONTH,
	 * cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	 * cal.set(Calendar.HOUR_OF_DAY, 23); cal.set(Calendar.MINUTE, 59);
	 * cal.set(Calendar.SECOND, 59); cal.set(Calendar.MILLISECOND, 999); return
	 * cal.getTime(); } else { return date; } } public static Date
	 * getStartDate(Date date) { if (date != null) { Calendar cal =
	 * Calendar.getInstance(); cal.setTime(date); cal.set(Calendar.HOUR_OF_DAY,
	 * 00); cal.set(Calendar.MINUTE, 00); cal.set(Calendar.SECOND, 00);
	 * cal.set(Calendar.MILLISECOND, 000); return cal.getTime(); } else { return
	 * date; } }
	 */
	// 闰年2月有问题
	/*
	 * public static Date getMonthStartDate(Date date) { if (date != null) {
	 * Calendar cal = Calendar.getInstance(); cal.setTime(date);
	 * cal.set(Calendar.DAY_OF_MONTH, 1); cal.set(Calendar.HOUR_OF_DAY, 00);
	 * cal.set(Calendar.MINUTE, 00); cal.set(Calendar.SECOND, 00);
	 * cal.set(Calendar.MILLISECOND, 000); return cal.getTime(); } else { return
	 * date; } }
	 */

	/**
	 * 求某年某月第一天 00:00:00
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMonthStartDateNew(int year, int month) {
		Calendar cal = Calendar.getInstance();
		if (year == 0) {
			year = cal.get(Calendar.YEAR);
		}
		if (month == 0) {
			month = cal.get(Calendar.MONTH);
		}
		cal.set(year, month - 1, 2);// 2月第1天
		cal.roll(Calendar.DATE, -1);

		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 000);
		return cal.getTime();

	}

	/**
	 * 求某年某月最后一天 23:59:59
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMonthEndDateNew(int year, int month) {
		Calendar cal = Calendar.getInstance();
		if (year == 0) {
			year = cal.get(Calendar.YEAR);
		}
		if (month == 0) {
			month = cal.get(Calendar.MONTH);
		}
		cal.set(year, month - 1, 1);// 2月第1天
		cal.roll(Calendar.DATE, -1);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static List<Date> findDates(Date dBegin, Date dEnd) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(dBegin);

		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(dBegin);

		boolean bContinue = true;

		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (dEnd.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}

		lDate.add(dEnd);

		return lDate;
	}

	public static List<String> findDateStringList(Date dBegin, Date dEnd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> lDate = new ArrayList<String>();
		lDate.add(sdf.format(dBegin));

		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(dBegin);

		boolean bContinue = true;

		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (dEnd.after(cal.getTime())) {
				lDate.add(sdf.format(cal.getTime()));
			} else {
				break;
			}
		}

		lDate.add(sdf.format(dEnd));

		return lDate;
	}
	
	/** TODO from msg **/
	/**
     * 获取当前日期
     * @return
     */
    public static  Date  getCurrentDate()
    {
    	return Calendar.getInstance().getTime();
    }
    
    /**
     * 返回日期格式(yyyy-MM-dd HH:mm:ss)
     * @param date
     * @return
     */
    public static  String  getDateTime(java.util.Date  date)
    {
        return   DateFormatUtils.format(date.getTime(), "yyyy-MM-dd HH:mm:ss");
    }
    
   /**
    * 返回日期格式(yyyy年MM月dd日)
    * @param date
    * @return
    */
    public  static  String   getDateTime_CN(java.util.Date  date)
    {
    	return  DateFormatUtils.format(date.getTime(), "yyyy年MM月dd");
    }

    /**
     * 返回日期格式(yyyyMMddHHmmss)
     * @param date
     * @return
     */
     public  static  String   getDateTime3(java.util.Date  date)
     {
     	return  DateFormatUtils.format(date.getTime(), "yyyyMMddHHmmss");
     }

    /**
     * 返回日期格式(yyyyMMdd)
     * @param date
     * @return
     */
    public static  String  getDateTime2(java.util.Date  date)
    {
        return   DateFormatUtils.format(date.getTime(), "yyyyMMdd");
    }
    
    /**
     * 返回日期格式(yyyy-MM-dd)
     * @param date
     * @return
     */
    public static  String  getDateTime_I(java.util.Date  date)
    {
        return   DateFormatUtils.ISO_DATE_FORMAT.format(date);
    }
    
    

    /**
     * 返回日期格式(HH:mm:ss)
     * @param date
     * @return
     */
    public static  String  getDateTime_IV(java.util.Date  date)
    {
        return   DateFormatUtils.ISO_TIME_NO_T_FORMAT.format(date); 
    }

    
    
     

    /**
     * <p> 获取当前系统时间的小时数 </p>
     * @return
     */
    public static int  getCurrentHour()
    {
         Calendar calendar = new GregorianCalendar();
         return calendar.get(Calendar.HOUR_OF_DAY);
    }

    
    
    /**
     * 获取当前日期的年/月/日
     * @param filed
     * @return
     */
    public   static   int   getCurrentDateFiled(int   filed)
    {
    	 Calendar calendar = new GregorianCalendar();
    	 return  calendar.get(filed);  
    }
    
    
    
    /**
     * 获取某日期的年/月/日
     * @param date
     * @param filed
     * @return
     */
    public   static   int    getDateFiled(Date  date,int   filed)
    {
    	 Calendar calendar = new GregorianCalendar();
    	 calendar.setTime(date);
    	 return  calendar.get(filed);  
    	  
    }

    /**
     * <p> 获取本月第一天日期（格式如YYYYMMDD）,如果当前日为当月1日,则返回上月第一日</p>
     * @return
     */
    public static String getMonthFirstDay()
    {
        Calendar calendar = new GregorianCalendar();
        int  day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = 0;
        if (day == 1)//当月第一日
        {
           calendar.add(Calendar.MONTH,-1);
        }
        month = calendar.get(Calendar.MONTH);
        if (month < 10)
        {
           return ""+calendar.get(Calendar.YEAR)+"0"+(month+1)+"01";
        }
        else
        {
          return ""+calendar.get(Calendar.YEAR)+month+"01";
        }
    }

    /**
     * <p> 获取当前时间前几天或后几天的日期 </p>
     * @return
     */
    public static Date getAddDays(int  days) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return  calendar.getTime();
    }
    
    /**
     * <p> 获取某个月后的日期格式（yyyyMMdd）</p>
     * @return
     */
    public static String getAfterMonth(int monthNum)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, monthNum);
        return  getDateTime2(calendar.getTime());
    }
    
    

    /**
     * <p> 返回日期（格式yyyyMMdd） </p>
     * @param timeMillis
     * @return
     */
    public static  String  getFormatDate(long  timeMillis)
    {
       return getDateTime2(new Date(timeMillis));
    }

	 /**
	  * 将某日期增加日期值
	  * @param data
	  * @param field
	  * @param value
	  * @return
	  */
	 public static  Date   add(Date   date,int   field,int  value)
	 {
		 Calendar calendar1= Calendar.getInstance();
		 calendar1.setTime(date);
		 calendar1.add(field, value);
		 return  calendar1.getTime();
	 }
     
	 /**
	  * 设置时间为23:59:59 第二天凌晨前，为查询提供
	  * @param date
	  * @return
	  */
	 public static  Date  setTomorrowZeroBeforSecond(Date  date) {
		 Calendar calendar1= Calendar.getInstance();
		 calendar1.setTime(date);
//		 calendar1.add(Calendar.PM, 1);
		 calendar1.set(Calendar.HOUR_OF_DAY, 23);
		 calendar1.set(Calendar.MINUTE, 59);
		 calendar1.set(Calendar.SECOND, 59);
		 return  calendar1.getTime();
	 }
	 
	 
	 /**
	  * 设置时间为23:59:59 第二天凌晨前，为查询提供
	  * @param date
	  * @return
	  */
	 public static  Date  setDateStartSecond(Date  date) {
		 Calendar calendar1= Calendar.getInstance();
		 calendar1.setTime(date);
		 calendar1.set(Calendar.HOUR_OF_DAY, 0);
		 calendar1.set(Calendar.MINUTE, 0);
		 calendar1.set(Calendar.SECOND, 0);
		
		 return  calendar1.getTime();
	 }
	 
	   /**
	     * 字符串转日期
	     *
	     * @return Date
	     * @author kevin
	     */
	    public static Date parseStringToDate(String thedate, String format) {
	        DateFormat sdf = new SimpleDateFormat(format);
	        Date dd1 = null;
	        try {
	            dd1 = sdf.parse(thedate);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return dd1;
	    }
	    
	    
	    /**
	     * 字符串转日期
	     *
	     * @return Date
	     * @author kevin
	     */
	    public static Date parseStringToDate(String thedate) {
	    	String  format = "";
	    	if (null == thedate || "".equals(thedate)){
	    		return null;
	    	} else {
	    		if (thedate.length() == 6) {
	    			format = DATE_YEAR_MONTH;
	    		} else if (thedate.length() == 8) {
	    			format = DATE_FORMAT_COMPACT;
	    		} else if (thedate.length() == 10) {
	    			format = DATE_FORMAT_SHORT;
	    		} else if (thedate.length() == 14) {
	    			format = DATE_FORMAT_COMPACTFULL;
	    		} else if (thedate.length() == 18) {
	    			format = DATE_FORMAT_FULL_MSEL;
	    		} else if (thedate.length() == 19) {
	    			format = DATE_FORMAT_FULL;
	    		} 
	    		
	    	}
	        DateFormat sdf = new SimpleDateFormat(format);
	        Date dd1 = null;
	        try {
	            dd1 = sdf.parse(thedate);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return dd1;
	    }
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		/*
		 * cal.set(Calendar.YEAR, 2015); cal.set(Calendar.MONTH, 3);
		 */
		cal.add(Calendar.DAY_OF_MONTH, 0);
		Date date = cal.getTime();
		System.out.println(date.toLocaleString());
	}
}
