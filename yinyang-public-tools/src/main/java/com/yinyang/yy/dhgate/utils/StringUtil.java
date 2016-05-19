package com.yinyang.yy.dhgate.utils;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{
  private static SecureRandom sr = new SecureRandom();
  private static Pattern PLT = Pattern.compile("<");
  private static Pattern PRT = Pattern.compile(">");
  private static Pattern PL_KUA = Pattern.compile("\\(");
  private static Pattern PR_KUA = Pattern.compile("\\)");
  private static Pattern PR_QUOT = Pattern.compile("\"");
  private static Pattern PR_EVAL = Pattern.compile("eval\\((.*)\\)");
  private static Pattern PR_JAVASCRIPT = Pattern.compile("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']");
  private static Pattern PR_SCRIPT = Pattern.compile("script");

  public static String getUniqueID(int len)
  {
    if (len < 1) len = 10;
    char[] alphabet = "12345abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ67890".toCharArray();
    byte[] b = new byte[len];
    sr.nextBytes(b);
    char[] out = new char[len];
    for (int i = 0; i < len; ++i) {
      int index = b[i] % alphabet.length;
      if (index < 0) index += alphabet.length;
      out[i] = alphabet[index];
    }
    return new String(out);
  }

  public static String getRandomNum(int len) {
    if (len < 1) len = 10;
    ++len;
    char[] alphabet = "123457809".toCharArray();
    byte[] b = new byte[len];
    sr.nextBytes(b);
    char[] out = new char[len];
    for (int i = 0; i < len; ++i) {
      int index = b[i] % alphabet.length;
      if (index < 0) index += alphabet.length;
      out[i] = alphabet[index];
    }
    return new String(out).substring(1);
  }

  public static String getUniqueID()
  {
    UUID u = UUID.randomUUID();
    long mostSigBits = u.getMostSignificantBits();
    long leastSigBits = u.getLeastSignificantBits();
    return digits(mostSigBits >> 32, 8) + digits(mostSigBits >> 16, 4) + 
      digits(mostSigBits, 4) + digits(leastSigBits >> 48, 4) + digits(leastSigBits, 12);
  }

  public static String removeSpecialChar(String sHtmlContent)
  {
    sHtmlContent = sHtmlContent.replaceAll("\n", "");
    sHtmlContent = sHtmlContent.replaceAll("\r", "");
    sHtmlContent = sHtmlContent.replaceAll("'", "`");
    return sHtmlContent;
  }

  public static String getFilenameExt(String filename)
  {
    if (filename == null) return "";

    int idx = filename.lastIndexOf(".");
    if (idx > 0)
      return filename.substring(idx + 1);

    return "";
  }

  public static boolean isDecimal(String str)
  {
    if ((str == null) || ("".equals(str.trim()))) return false;

    Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
    return pattern.matcher(str).matches();
  }

  public static boolean isInteger(String str)
  {
    if ((str == null) || ("".equals(str.trim()))) return false;

    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher isNum = pattern.matcher(str);

    return (isNum.matches());
  }

  public static String replaceIgnorecase(String stringname, String oldchar, String newchar)
  {
    if ((stringname == null) || ("".equalsIgnoreCase(stringname)))
      return stringname;
    Pattern old = Pattern.compile(oldchar, 2);
    Matcher newString = old.matcher(stringname);
    String str = newString.replaceAll(newchar);
    return str;
  }

  public static void main(String[] args)
    throws Exception
  {
    System.out.println(isDecimal("500.0"));
  }

  public static boolean isInnerList(String string, String[] strs) {
    for (int i = 0; i < strs.length; ++i) {
      String str = strs[i];
      if (string.equals(str))
        return true;

    }

    return false;
  }

  public static String toSemiangle(String src)
  {
    if (src == null) return null;

    StringBuffer outStr = new StringBuffer();
    String tempStr = "";
    byte[] b = (byte[])null;
    int len = src.length();
    for (int i = 0; i < len; ++i) {
      try {
        tempStr = src.substring(i, i + 1);
        b = tempStr.getBytes("unicode");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      if (b[3] == -1) {
        b[2] = (byte)(b[2] + 32);
        b[3] = 0;
        try {
          outStr.append(new String(b, "unicode"));
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
        }
      } else {
        outStr.append(tempStr);
      }

    }

    return outStr.toString();
  }

  public static boolean notEmpty(String str)
  {
    return ((str != null) && (str.trim().length() > 0));
  }

  public static boolean isEmpty(String str)
  {
    return (!(notEmpty(str)));
  }

  public static boolean isInArray(Object object, Object[] objects)
  {
    for (int i = 0; i < objects.length; ++i)
      if (object.equals(objects[i]))
        return true;


    return false;
  }

  private static String digits(long val, int digits)
  {
    long hi = 1L << digits * 4;
    return Long.toString(hi | val & hi - 1L, 36).substring(1);
  }

  public static String replaceHtml(String val)
  {
    if ((val == null) || ("".equals(val))) return val;
    val = val.replaceAll("<", "&lt;");
    val = val.replaceAll(">", "&gt;");
    val.replaceAll("'", "‘");
    return val;
  }

  public static String subLastString(String s, int size)
  {
    if (s.length() <= size) return s;
    return s.substring(s.length() - 3, s.length());
  }

  public static String escapeXss(String s)
  {
    if ((s == null) || ("".equals(s))) { return s;
    }

    s = PLT.matcher(s).replaceAll("&lt;");
    s = PRT.matcher(s).replaceAll("&gt;");

    s = PL_KUA.matcher(s).replaceAll("&#40;");
    s = PR_KUA.matcher(s).replaceAll("&#41;");

    s = PR_QUOT.matcher(s).replaceAll("&quot;");

    s = PR_EVAL.matcher(s).replaceAll("");

    s = PR_JAVASCRIPT.matcher(s).replaceAll("\"\"");

    s = PR_SCRIPT.matcher(s).replaceAll("");

    return s;
  }

  public static String escapeXssLittle(String s)
  {
    char ch;
    if (s == null) return null;
    StringBuffer buf = new StringBuffer(s.length() * 2);

    for (int i = 0; i < s.length(); ++i) {
      ch = s.charAt(i);

      switch (ch)
      {
      case '>':
        buf.append("&gt;");
        break;
      case '<':
        buf.append("&lt;");
        break;
      case '=':
      default:
        buf.append(ch);
      }
    }

    return buf.toString();
  }

  public static String escapeXssMore(String s)
  {
    char ch;
    if (s == null) return null;
    StringBuffer buf = new StringBuffer(s.length() * 2);

    for (int i = 0; i < s.length(); ++i) {
      ch = s.charAt(i);

      switch (ch)
      {
      case '>':
        buf.append("&gt;");
        break;
      case '<':
        buf.append("&lt;");
        break;
      case '"':
        buf.append("&quot;");
        break;
      case '&':
        buf.append("&amp;");
        break;
      default:
        buf.append(ch);
      }

    }

    String n = buf.toString();

    n = PR_EVAL.matcher(n).replaceAll("");

    n = PR_JAVASCRIPT.matcher(n).replaceAll("\"\"");

    n = PR_SCRIPT.matcher(n).replaceAll("");

    return n;
  }

  public static String escapeXssAll(String s)
  {
    return escapeXss(s);
  }

  public static String trimReturnURL(String s)
  {
    if ((s == null) || ("".equals(s))) return s;

    String a = "/";

    if (s.startsWith("/")) {
      return s;
    }

    if (s.startsWith("http"))
    {
      String ds = s;
      try {
        ds = URLDecoder.decode(s, "UTF-8");
      }
      catch (Exception localException)
      {
      }
      try {
        URL u = new URL(ds);
        String host = u.getHost();

        if ((host == null) || (
          (!(host.endsWith(".dhgate.com"))) && (host.indexOf("wholesale") <= 0))) 
        	//break label100;
        return s;
      }
      catch (java.net.MalformedURLException u)
      {
      }

    }

    label100: return a;
  }

  public static boolean isEnglishOrNumber(String str)
  {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
    return pattern.matcher(str).matches();
  }

  public static boolean checkEnglish(String str)
  {
    if ((str == null) || (str.trim().length() == 0)) return true;

    str = toSemiangle(str);
    int len = str.length();
    for (int i = 0; i < len; ++i)
      if (str.charAt(i) > 255)
        return false;


    return true;
  }

  public static boolean strEquals(String str1, String str2)
  {
    boolean bEmpty1 = isEmpty(str1);
    boolean bEmpty2 = isEmpty(str2);
    boolean bRet = false;
    if (bEmpty1) {
      if (bEmpty2)
        bRet = true;
      else
        bRet = false;

    }
    else if (bEmpty2)
      bRet = false;
    else {
      bRet = str1.trim().equals(str2.trim());
    }

    return bRet;
  }

  public static boolean isNotBlank(String str)
  {
    boolean is = false;
    if ((!(isBlank(str))) && (!("null".equals(str))))
      is = true;

    return is;
  }

  public static boolean isBlank(String str)
  {
    int strLen=0;
    if (str != null) 
    	if ((strLen = str.length()) != 0) 
    return true;

    for (int i = 0; i < strLen; ++i)
      if (!(Character.isWhitespace(str.charAt(i))))
        label15: return false;


    return true;
  }
}