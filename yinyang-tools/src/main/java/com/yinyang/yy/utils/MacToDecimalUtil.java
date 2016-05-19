package com.yinyang.yy.utils;

/**
 * Mac地址转换为十进制数值
 * 
 * @Title: MacToDecimalUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午1:19:41
 * @version V1.0
 */

public class MacToDecimalUtil
{

    public static int macToDecimal(String mac)
    {
        String[] temp = mac.split(":");
        int[] num = new int[temp.length];
        int result = 0;
        int i = 0;

        try
        {
            for (String str : temp)
            {
                //System.out.print("str--->" + str); //---------
                if (IsNumericUtil.isNumeric(str))
                {
                    num[i] = Integer.parseInt(str);
                }
                else
                {
                    num[i] = Integer.parseInt(str, 16);
                }
                //System.out.println("   num[i]--->" + num[i]); //---------
                result += num[i];
                i++;
            }
            //System.out.println("返回值result--->" + result); //---------
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return result;
    }
}
