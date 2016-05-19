package com.yinyang.yy.utils;

import java.util.Random;

/**
 * 生成数字和字母的混合字符串
 * 
 * @Title: RandomUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午1:20:55
 * @version V1.0
 */
public class RandomUtil
{
    private static Random rand = new Random();

    /**
     * 产生一个指定位数的随机码(由数字或大小写字母构成) 
     * @param randomLen 指定位数
     * @param hasLetter 是否包括字母,true包括,false不包括
     * @return
     */
    public static String creatRandom(int randomLen, boolean hasLetter)
    {
        StringBuilder result = null;
        if (randomLen >= 1)
        {
            result = new StringBuilder();
            if (hasLetter)
            {
                for (int i = 0; i < randomLen; i++)
                {
                    //产生0-2的随机数                      
                    int random = rand.nextInt(3);
                    switch (random)
                    {
                        case 0://数字                              
                            result.append(createRandomNum());
                            break;
                        case 1://小写字母                              
                            result.append(createRandomLowerLetter());
                            break;
                        case 2://大写字母                              
                            result.append(createRandomUpperLetter());
                            break;
                    }
                }
            }
            else if (!hasLetter)
            {
                for (int i = 0; i < randomLen; i++)
                {
                    result.append(createRandomNum());
                }
            }
            return result.toString();
        }
        return null;
    }

    private static String createRandomNum()
    {
        return String.valueOf(rand.nextInt(10));
    }

    private static String createRandomLowerLetter()
    {
        //char类型a，转换为byte，数值为97  
        int a = 97;
        //97+26位随机数，小写字母byte范围
        int random = a + rand.nextInt(26);
        return String.valueOf((char) (byte) random);
    }

    private static String createRandomUpperLetter()
    {
        //char类型A，转换为byte，数值为65
        byte A = 65;
        //65+26位随机数，小写字母byte范围
        int random = A + rand.nextInt(26);

        return String.valueOf((char) (byte) random);
    }
}
