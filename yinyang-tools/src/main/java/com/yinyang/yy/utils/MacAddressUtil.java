package com.yinyang.yy.utils;

import java.util.Random;

/**
 * 生成随机MAC地址
 * @author YY
 */
public class MacAddressUtil
{
    private final static int LENGTH = 12;
    private static Random rand = new Random();

    /**
     * 产生随机码的MAC地址 
     * @return String
     */
    public static String creatRandomMAC()
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < LENGTH; i++)
        {
            if (i > 0 && i % 2 == 0)
            {
                result.append(":");
            }
            //产生0-1的随机数                      
            int random = rand.nextInt(2);
            switch (random)
            {
                case 0://数字                              
                    result.append(createRandomNum());
                    break;
                case 1://小写字母                              
                    result.append(createRandomLowerLetter());
                    break;
            }
        }
        return result.toString();
    }

    private static String createRandomNum()
    {
        return String.valueOf(rand.nextInt(10));
    }

    private static String createRandomLowerLetter()
    {
        //char类型a，转换为byte，数值为97  
        int a = 97;
        //97+26位随机数，小写字母byte范围 a~f
        int random = a + rand.nextInt(6);
        return String.valueOf((char) (byte) random);
    }
}