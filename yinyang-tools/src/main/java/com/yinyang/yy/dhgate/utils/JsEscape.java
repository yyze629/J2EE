package com.yinyang.yy.dhgate.utils;

public class JsEscape
{
  public static String escape(String src)
  {
    if (src == null) {
      return null;
    }

    StringBuffer tmp = new StringBuffer();
    tmp.ensureCapacity(src.length() * 6);
    for (int i = 0; i < src.length(); ++i) {
      char j = src.charAt(i);
      if ((Character.isDigit(j)) || (Character.isLowerCase(j)) || (Character.isUpperCase(j))) {
        tmp.append(j);
      } else if (j < 256) {
        tmp.append("%");
        if (j < '\16')
          tmp.append("0");
        tmp.append(Integer.toString(j, 16));
      } else {
        tmp.append("%u");
        tmp.append(Integer.toString(j, 16));
      }
    }
    return tmp.toString();
  }

  public static String unescape(String src) {
    if (src == null)
      return null;
    StringBuffer tmp = new StringBuffer();
    tmp.ensureCapacity(src.length());
    int lastPos = 0; int pos = 0;

    while (lastPos < src.length()) {
      pos = src.indexOf("%", lastPos);
      if (pos == lastPos) {
        char ch;
        if (src.charAt(pos + 1) == 'u') {
          ch = (char)Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
          tmp.append(ch);
          lastPos = pos + 6;
        } else {
          ch = (char)Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
          tmp.append(ch);
          lastPos = pos + 3;
        }
      }
      else if (pos == -1) {
        tmp.append(src.substring(lastPos));
        lastPos = src.length();
      } else {
        tmp.append(src.substring(lastPos, pos));
        lastPos = pos;
      }
    }

    return tmp.toString();
  }
}