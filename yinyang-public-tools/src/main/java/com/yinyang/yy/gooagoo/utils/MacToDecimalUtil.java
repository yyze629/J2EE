package com.yinyang.yy.gooagoo.utils;

/*
 *  Mac地址转换为十进制数值
 */
public class MacToDecimalUtil
{
    //private final static String INPUT = "{\"cmd\":\"heartbeat\",\"macAddr\":\"00:00:00:00:00:fe\"}";
    private final static String INPUT = "e0:83:fb:56:00:fe";

    public MacToDecimalUtil()
    {
        macToDecimal(INPUT);
    }

    public static int macToDecimal(String mac)
    {
        String[] temp = mac.split(":");
        int[] num = new int[6];
        int i = 0;
        try
        {
            for (String str : temp)
            {
                System.out.println("str--->" + str); //---
                if (IsNumericUtil.isNumeric(str))
                {
                    num[i] = Integer.parseInt(str);
                }
                else
                {
                    num[i] = Integer.parseInt(str, 16);
                }
                System.out.println("num[i]--->" + num[i]); //---
                i++;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return 0;
    }

    public static void main(String[] args)
    {
        new MacToDecimalUtil();
    }
}
