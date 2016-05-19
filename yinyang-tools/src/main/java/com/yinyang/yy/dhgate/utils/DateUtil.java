package com.yinyang.yy.dhgate.utils;

import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class DateUtil
{
  public static final int SECOND = 1000;
  public static final int MINUTE = 60000;
  public static final int HOUR = 3600000;
  public static final int DAY = 86400000;
  public static final int WEEK = 604800000;
  public static final int YEAR = 1471228928;
  public static final long GMT_VIETNAM_TIME_OFFSET = 25200000L;
  private static long SERVER_TIME_OFFSET = 0L;
  public static final String ddMMyyyyStr = "dd-MM-yyyy";
  public static final String yyyyMMddStr = "yyyy-MM-dd";
  public static final String yyyyMMddStr1 = "yyyy/MM/dd";
  public static final String yyyyMdStr = "yyyy-M-d";
  public static final String yyyyMMddhhmmssStr = "yyyy-MM-dd HH:mm:ss";
  public static final String hhmmssStr = "HH:mm:ss";
  public static final String yyyyMMddhhmmsssStr = "yyyy-MM-dd HH:mm:ss:s";
  public static final String MMddhhmmStr = "MM-dd HH:mm";
  public static final String yyyymmddhhmmStr = "yyyy-MM-dd HH:mm";
  public static final String yyStr = "yy";
  public static final String yyyyMM = "yyyyMM";
  public static final String YYYYMM = "yyyy-MM";
  public static final String yyyyMMdd = "yyyyMMdd";
  private static DateFormat hhmmssStrFormat = new SimpleDateFormat("HH:mm:ss");
  private static DateFormat ddMMyyyyFormat = new SimpleDateFormat("dd-MM-yyyy");
  private static DateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");
  private static DateFormat yyyyMdFormat = new SimpleDateFormat("yyyy-M-d");
  private static DateFormat yyyymmddhhmmssFormat = new SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss");
  private static DateFormat yyyymmddhhmmsssFormat = new SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss:s");
  private static DateFormat mmddhhmmFormat = new SimpleDateFormat(
    "MM-dd HH:mm");
  private static DateFormat yyyymmddhhmmFormat = new SimpleDateFormat(
    "yyyy-MM-dd HH:mm");
  private static DateFormat yyFormat = new SimpleDateFormat(
    "yy");
  private static DateFormat dateFormat = SimpleDateFormat.getDateInstance(
    2);
  private static DateFormat datetimeFormat = SimpleDateFormat.getDateTimeInstance(2, 2);
  private static DateFormat yyyyMMFormat = new SimpleDateFormat(
    "yyyyMM");
  private static DateFormat YYYYMMFormat = new SimpleDateFormat("yyyy-MM");
  private static DateFormat yyyyMMdd_Format = new SimpleDateFormat("yyyyMMdd");

  public static synchronized String getDateHHMMSS(Date date)
  {
    if (date != null)
      return hhmmssStrFormat.format(date);

    return "";
  }

  public static synchronized String getDateDDMMYYYY(Date date)
  {
    if (date != null)
      return ddMMyyyyFormat.format(date);

    return "";
  }

  public static synchronized Date Add(Date date, int days, int hours, int minutes, int seconds)
  {
    Date dt = date;
    if (dt != null)
    {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(dt);

      if (days != 0)
      {
        calendar.add(5, days);
      }
      if (hours != 0)
      {
        calendar.add(10, hours);
      }
      if (minutes != 0)
      {
        calendar.add(12, minutes);
      }
      if (seconds != 0)
      {
        calendar.add(13, seconds);
      }
      dt = calendar.getTime();
    }
    return dt;
  }

  public static synchronized Date getNextDay(Date date)
  {
    Date dt = date;
    if (dt != null)
    {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(dt);
      calendar.set(10, 0);
      calendar.set(12, 0);
      calendar.set(13, 0);
      calendar.add(5, 1);
      dt = calendar.getTime();
    }
    return dt;
  }

  public static synchronized Date getShortDate(Date date)
  {
    if (date != null) {
      Date dt = (Date)date.clone();

      dt.setHours(0);
      dt.setMinutes(0);
      dt.setSeconds(0);

      return dt;
    }
    return null;
  }

  public static synchronized String getDateYYYYMMDD(Date date)
  {
    if (date != null)
      return yyyyMMddFormat.format(date);

    return "";
  }

  public static synchronized String getDateYYYYMD(Date date)
  {
    if (date != null)
      return yyyyMdFormat.format(date);

    return "";
  }

  public static synchronized String getDateYYYYMMDDHHMMSSS(Date date)
  {
    if (date != null)
      return yyyymmddhhmmsssFormat.format(date);

    return "";
  }

  public static synchronized String getDateYYYYMMDDHHMMSS(Date date)
  {
    if (date != null)
      return yyyymmddhhmmssFormat.format(date);

    return "";
  }

  public static synchronized Date getDateFormat(Date date) {
    if (date != null)
      try {
        return yyyymmddhhmmssFormat.parse(yyyymmddhhmmssFormat.format(date));
      }
      catch (ParseException e) {
        e.printStackTrace();

        return null;
      }
    return null;
  }

  public static synchronized Date getDateTimeYYYYMMDDHHMMSS(String date)
  {
    if (date != null)
      try {
        return yyyymmddhhmmssFormat.parse(date);
      } catch (Exception e) {
        return null;
      }

    return null;
  }

  public static void main(String[] args) {
    System.out.println(getDateFormat(new Date())); }

  public static synchronized String getDateYY(Date date) {
    if (date != null)
      return yyFormat.format(date);

    return "";
  }

  public static synchronized String getDateindex(Date date)
  {
    if (date != null)
      return yyyymmddhhmmssFormat.format(date);

    return "";
  }

  public static synchronized String getDateSearch(Date date)
  {
    if (date != null)
      return mmddhhmmFormat.format(date);

    return "";
  }

  public static synchronized String getDateNoSencond(Date date)
  {
    if (date != null)
      return yyyymmddhhmmFormat.format(date);

    return "";
  }

  public static synchronized String getYearAndMonth(Date date)
  {
    if (date != null)
      return yyyyMMFormat.format(date);

    return "";
  }

  public static synchronized String getDateyyyyMMddStr(Date date)
  {
    if (date != null)
      return yyyyMMdd_Format.format(date);

    return "";
  }

  public static synchronized String getDateStrByFormat(Date date, String formatStr)
  {
    DateFormat tFormat = new SimpleDateFormat(formatStr);
    if (date != null)
      return tFormat.format(date);

    return "";
  }

  public static synchronized String formatDate(Date date)
  {
    if (date != null)
      return dateFormat.format(date);

    return "";
  }

  public static synchronized String formatDateTime(Date date)
  {
    if (date != null)
      return datetimeFormat.format(date);

    return "";
  }

  public static Timestamp getCurrentGMTTimestamp()
  {
    return new Timestamp(System.currentTimeMillis() - SERVER_TIME_OFFSET);
  }

  public static void updateCurrentGMTTimestamp(Timestamp timeToUpdate) {
    timeToUpdate.setTime(System.currentTimeMillis() - SERVER_TIME_OFFSET);
  }

  public static Date getVietnamDateFromGMTDate(Date date) {
    return new Date(date.getTime() + 25200000L);
  }

  public static Date convertGMTDate(Date gmtDate, int hourOffset) {
    return new Date(gmtDate.getTime() + hourOffset * 3600000);
  }

  public static Timestamp convertGMTTimestamp(Timestamp gmtTimestamp, int hourOffset)
  {
    return new Timestamp(gmtTimestamp.getTime() + hourOffset * 3600000);
  }

  public static Timestamp getCurrentGMTTimestampExpiredYear(int offsetYear)
  {
    Calendar now = Calendar.getInstance();
    now.add(1, offsetYear);
    return new Timestamp(now.getTime().getTime());
  }

  public static Timestamp getCurrentGMTTimestampExpiredMonth(int offsetMonth) {
    Calendar now = Calendar.getInstance();
    now.add(2, offsetMonth);
    return new Timestamp(now.getTime().getTime());
  }

  public static String CalendarToStr(Calendar cal)
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    if (cal != null) {
      Date date = cal.getTime();
      return format.format(date);
    }
    return "";
  }

  public static Calendar starCalOfWeek(Calendar day)
  {
    int temp = day.get(7);
    switch (temp)
    {
    case 1:
      return day;
    case 2:
      day.add(5, -1);
      return day;
    case 3:
      day.add(5, -2);
      return day;
    case 4:
      day.add(5, -3);
      return day;
    case 5:
      day.add(5, -4);
      return day;
    case 6:
      day.add(5, -5);
      return day;
    case 7:
      day.add(5, -6);
      return day;
    }
    return day;
  }

  public static Calendar endCalOfWeek(Calendar day)
  {
    int temp = day.get(7);
    switch (temp)
    {
    case 1:
      day.add(5, 6);
      return day;
    case 2:
      day.add(5, 5);
      return day;
    case 3:
      day.add(5, 4);
      return day;
    case 4:
      day.add(5, 3);
      return day;
    case 5:
      day.add(5, 2);
      return day;
    case 6:
      day.add(5, 1);
      return day;
    case 7:
      return day;
    }
    return day;
  }

  public static Date StrToDate(String str)
  {
    if (str.length() == 0)
      return null;

    int start = str.indexOf(45);
    String year = str.substring(0, start);
    ++start;
    int start1 = str.indexOf(45, start);
    String month = str.substring(start, start1);
    String day = str.substring(start1 + 1);
    Calendar cal = Calendar.getInstance();

    cal.set(1, Integer.parseInt(year));
    cal.set(2, Integer.parseInt(month) - 1);
    cal.set(5, Integer.parseInt(day));

    return new Timestamp(cal.getTime().getTime());
  }

  public static Date StrToDateTime(String str) {
    if (str.length() == 0)
      return null;

    int start = str.indexOf(45);
    String year = str.substring(0, start);
    ++start;
    int start1 = str.indexOf(45, start);
    String month = str.substring(start, start1);
    String day = str.substring(start1 + 1);
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(1, Integer.parseInt(year));
    cal.set(2, Integer.parseInt(month) - 1);
    cal.set(5, Integer.parseInt(day));

    return new Timestamp(cal.getTime().getTime());
  }

  public static Calendar StrToCalendar(String str) {
    if (str.length() == 0)
      return null;

    int start = str.indexOf(47);
    String year = str.substring(0, start);
    ++start;
    int start1 = str.indexOf(47, start);
    String month = str.substring(start, start1);
    String day = str.substring(start1 + 1);
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(2, Integer.parseInt(month) - 1);
    cal.set(5, Integer.parseInt(day));
    return cal;
  }

  public static String dayOfWeek(Calendar day)
  {
    int temp = day.get(7);
    switch (temp)
    {
    case 1:
      return "Sun";
    case 2:
      return "Mon";
    case 3:
      return "Tue";
    case 4:
      return "Wed";
    case 5:
      return "Thu";
    case 6:
      return "Fri";
    case 7:
      return "Sat";
    }
    return "";
  }

  public static String dayOfWeek(String inDay)
  {
    Calendar cal = Calendar.getInstance();

    cal.setTime(StrToDate(inDay));
    int temp = cal.get(7);
    switch (temp)
    {
    case 1:
      return "Sun";
    case 2:
      return "Mon";
    case 3:
      return "Tue";
    case 4:
      return "Wed";
    case 5:
      return "Thu";
    case 6:
      return "Fri";
    case 7:
      return "Sat";
    }
    return "";
  }

  public static Timestamp StrToTimestamp(String timestampStr, String pattern)
    throws ParseException
  {
    Date date = null;
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    try {
      date = format.parse(timestampStr);
    } catch (ParseException e) {
      throw e;
    }
    return new Timestamp(date.getTime());
  }

  public static Date StrToDate(String sDate, String pattern)
  {
    Date date = null;
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    try {
      date = format.parse(sDate);
    } catch (ParseException localParseException) {
    }
    return ((date == null) ? null : date);
  }

  public static boolean IsDate(String sDate, String vPattern)
  {
    String pattern;
    boolean bDate = true;

    if ((vPattern == null) || (vPattern.length() == 0))
      pattern = "yyyy-MM-dd";
    else {
      pattern = vPattern;
    }

    Date date = null;
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    format.setLenient(false);
    try {
      date = format.parse(sDate);
      bDate = true;
    } catch (ParseException e) {
      bDate = false;
    }

    return bDate;
  }

  public static Date getNowDateTime()
  {
    Date dnow = new Date();

    return dnow;
  }

  public static Date getDateTimeForSortDate()
  {
    Date dnow = new Date();
    dnow = StrToDate("2005-08-19");

    return dnow;
  }

  public static Calendar getNowCalendar()
  {
    Calendar calendar = Calendar.getInstance();

    return calendar;
  }

  public static Date getNowDate()
  {
    Date dnow = new Date();
    return dnow;
  }

  public static long getaLongTime()
  {
    return System.currentTimeMillis();
  }

  public static long getDateSpace(Date dFirst, Date dSecond)
  {
    long lspace = 0L;

    if ((dFirst != null) && (dSecond != null)) {
      lspace = dFirst.getTime() - dSecond.getTime();
    }

    return lspace;
  }

  public static long getDateSpaceSecond(Date dFirst, Date dSecond)
  {
    long lspace = 0L;

    if ((dFirst != null) && (dSecond != null)) {
      lspace = (dFirst.getTime() - dSecond.getTime()) / 1000L;
    }

    return lspace;
  }

  public static boolean between(Date curDate, Date vStartDate, Date vEndDate)
  {
    boolean bRet = false;

    if (curDate != null) {
      if ((vStartDate == null) && (vEndDate == null))
        bRet = true;
      else if (vStartDate == null)
        if (!(curDate.after(vEndDate)))
          bRet = true;

      else if (vEndDate == null)
        if (!(curDate.before(vStartDate)))
          bRet = true;

      else if ((!(curDate.before(vStartDate))) && (!(curDate.after(vEndDate))))
        bRet = true;

    }

    return bRet;
  }

  public static Date getDate(Date date, int time, String status)
  {
    Calendar tempdate = Calendar.getInstance();
    tempdate.setTime(date);
    if (status.equals("YEAR"))
      tempdate.add(1, time);
    else if (status.equals("MONTH"))
      tempdate.add(2, time);
    else
      tempdate.add(5, time);

    Date yesterday = tempdate.getTime();
    return yesterday;
  }

  public static String getDateTimeNormal(Date date)
  {
    return formatDateByFormat(date, "yyyy-MM-dd HH:mm:ss");
  }

  public static String formatDateByFormat(Date date, String format)
  {
    String result = "";
    if (date != null)
      try {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        result = sdf.format(date);
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }

    return result;
  }

  public static double DNVL(Object obj)
  {
    if (obj == null)
      return new Double(0D).doubleValue();

    return ((Double)obj).doubleValue();
  }

  public static long dateSub(Date date1, Date date2)
  {
    if (date2 == null) date2 = new Date();
    long day = (date1.getTime() - date2.getTime()) / 86400000L;

    return day;
  }

  public static long dateSub(Date date1)
  {
    return dateSub(date1, null);
  }

  public static synchronized Date AddMonth(Date date, int month)
  {
    Date dt = date;
    if (dt != null)
    {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(dt);

      if (month != 0)
      {
        calendar.add(2, month);
      }
      dt = calendar.getTime();
    }
    return dt;
  }

  public static String getLastMonthDay(Long state)
  {
    Calendar cal = Calendar.getInstance();
    Date date = new Date();
    cal.setTime(date);
    int year = 0;
    int month = cal.get(2);
    int day = 0;
    String endDay = "";
    if (state.longValue() == 0L) {
      day = cal.getActualMinimum(5);
      if (month == 0) {
        year = cal.get(1) - 1;
        month = 12;
      } else {
        year = cal.get(1);
      }
      endDay = year + "-" + month + "-" + day;
    } else {
      Calendar calendar = Calendar.getInstance();

      calendar.add(2, 1);

      calendar.set(5, 1);

      calendar.add(5, -1);
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      String day_end = df.format(calendar.getTime());
      Calendar c = Calendar.getInstance();
      c.set(5, 1);
      endDay = df.format(c.getTime());
    }

    return endDay;
  }

  public static Date getDateBefore(Date inDate, int day)
  {
    Calendar now = Calendar.getInstance();
    now.setTime(inDate);
    now.set(5, now.get(5) - day);
    return now.getTime();
  }

  public static Date getDateAfter(Date inDate, int day)
  {
    Calendar now = Calendar.getInstance();
    now.setTime(inDate);
    now.set(5, now.get(5) + day);
    return now.getTime();
  }

  public static String getPreDateByDate(Date d, int n)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(d);
    calendar.add(5, -n);
    Date yesterDay = calendar.getTime();
    return getDateYYYYMMDD(yesterDay);
  }

  public static String getYesterDayByDate(Date d)
  {
    return getPreDateByDate(d, 1);
  }

  public static Date getDateByStrYYYYMMDD(String strDate)
  {
    if (strDate != null)
      try {
        return yyyyMMddFormat.parse(strDate);
      }
      catch (ParseException localParseException)
      {
      }
    return null;
  }

  public static Date getDateByStrYMD(String strDate)
  {
    if (strDate != null)
      try {
        return yyyyMMdd_Format.parse(strDate);
      }
      catch (ParseException localParseException) {
      }
    return null;
  }

  public static List<String> getBefMoths(Date d, int count)
  {
    List moths = new ArrayList();
    for (int i = 0; i < count; ++i) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(d);
      calendar.add(2, -(i + 1));
      moths.add(YYYYMMFormat.format(calendar.getTime()));
    }
    return moths;
  }
}