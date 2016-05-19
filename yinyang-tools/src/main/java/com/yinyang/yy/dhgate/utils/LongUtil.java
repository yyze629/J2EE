package com.yinyang.yy.dhgate.utils;

public class LongUtil
{
  public static boolean longEquals(Long long1, Long long2)
  {
    boolean bEmpty1 = long1 == null;
    boolean bEmpty2 = long2 == null;
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
      bRet = long1.longValue() == long2.longValue();
    }

    return bRet;
  }

  public static boolean notEmpty(Long lon)
  {
    return (lon != null);
  }

  public static boolean isEmpty(Long lon)
  {
    return (!(notEmpty(lon)));
  }
}