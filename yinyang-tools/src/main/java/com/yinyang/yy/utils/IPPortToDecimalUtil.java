package com.yinyang.yy.utils;

/**
 * IP和端口号转换为十进制数值
 * @Title: IPPortToDecimalUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午1:18:41
 * @version V1.0
 */
public class IPPortToDecimalUtil
{
    public static int iPAndPortToDecimal(String ipAndPort)
    {
        String[] temp = ipAndPort.split("\\."); //split(.)无效
        int[] num = new int[temp.length];
        int result = 0;
        int i = 0;

        for (String str : temp)
        {
            //System.out.print("str--->" + str); //---------
            num[i] = Integer.parseInt(str);
            //System.out.println("   num[i]--->" + num[i]); //---------
            result += num[i];
            i++;
        }
        //System.out.println("返回值result--->" + result); //---------
        return result;
    }
}
