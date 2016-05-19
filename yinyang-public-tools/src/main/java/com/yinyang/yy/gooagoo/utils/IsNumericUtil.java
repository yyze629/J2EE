package com.yinyang.yy.gooagoo.utils;

/*
 *  判断是否是数字
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsNumericUtil
{
    public static boolean isNumeric(String str) throws Exception
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
