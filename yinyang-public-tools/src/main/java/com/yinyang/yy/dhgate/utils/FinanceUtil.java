package com.yinyang.yy.dhgate.utils;

public class FinanceUtil
{
  public static double getFormatValue(double value)
  {
    value *= 100.0D;
    value = Math.round(value);
    value /= 100.0D;

    return value;
  }

  public static double getFormatValueOld(double value)
  {
    value *= 10000.0D;
    value = Math.round(value);
    value /= 10000.0D;

    return value;
  }

  public static float getFormatValueFloat(float value)
  {
    value *= 100.0F;
    value = Math.round(value);
    value /= 100.0F;

    return value;
  }
}