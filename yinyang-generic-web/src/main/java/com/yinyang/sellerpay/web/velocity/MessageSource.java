package com.yinyang.sellerpay.web.velocity;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageSource
{
  public static String getText(String key)
  {
    return InternationalizationProperties.getProperty(key);
  }

  public static String getText(String str, Object[] obj)
  {
    String strOne = getText(str);

    if (obj == null) {
      return strOne;
    }

    return fillStringByArgs(strOne, obj);
  }

  private static String fillStringByArgs(String str, Object[] obj)
  {
    Matcher m = Pattern.compile("\\{(\\d)\\}").matcher(str);
    int i = 0;
    while (m.find()) {
      if (i == obj.length)
        return str;

      str = str.replace(m.group(), getText(String.valueOf(obj[i])));
      ++i;
    }
    return str; }

  public static void main(String[] args) {
    Object[] obj = new Object[4];
    obj[0] = "中国人";
    obj[1] = "北京";
    obj[2] = "22";
    obj[3] = "vv";

    String str = "我123是{0},我来自{1},今年{2}岁{3}";

    System.out.println(getText(str, obj));
  }
}