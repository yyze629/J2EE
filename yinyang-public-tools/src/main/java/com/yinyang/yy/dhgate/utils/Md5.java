package com.yinyang.yy.dhgate.utils;

import java.security.MessageDigest;

public class Md5
{
  public static String getMd5(String s)
  {
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'a', 'b', 'c', 'd', 'e', 'f' };
    try
    {
      byte[] strTemp = s.getBytes();
      MessageDigest mdTemp = MessageDigest.getInstance("md5");
      mdTemp.update(strTemp);
      byte[] md = mdTemp.digest();
      int j = md.length;
      char[] str = new char[j * 2];
      int k = 0;
      for (int i = 0; i < j; ++i) {
        byte byte0 = md[i];
        str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
        str[(k++)] = hexDigits[(byte0 & 0xF)];
      }
      return new String(str); } catch (Exception e) {
    }
    return null;
  }

  public static String getMd5Password(String password, String sault1, String sault2)
  {
    if ((password == null) || (sault1 == null) || (sault2 == null)) return null;
    try {
      String md = getMd5(password + sault1);
      md = getMd5(md + sault2);
      return md; } catch (Exception e) {
    }
    return null;
  }
}