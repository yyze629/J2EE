package com.yinyang.yy.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 *  判断是否是数字
 * 
 * @Title: IsNumericUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午1:20:22
 * @version V1.0
 */
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
