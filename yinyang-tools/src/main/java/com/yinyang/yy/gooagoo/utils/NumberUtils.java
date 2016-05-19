package com.yinyang.yy.gooagoo.utils;

import com.yinyang.yy.common.YYException;

/**
 * 数据工具类
 * 
 * @author Administrator
 *
 */
public class NumberUtils
{
    /**
     * 插入四个字节
     * @param n
     * @param bytes
     * @param start
     * @return
     */
    public static byte[] int2Byte(int n, byte[] bytes, int start)
    {
        bytes[start] = (byte) (n & 0xff);
        bytes[start + 1] = (byte) (n >> 8 & 0xff);
        bytes[start + 2] = (byte) (n >> 16 & 0xff);
        bytes[start + 3] = (byte) (n >> 24 & 0xff);
        return bytes;
    }

    /**
     * 4位字节数组转换成INT
     * @param intByte
     * @return
     * @throws YYException 
     */
    public static int bytesToInt(byte[] intByte)
    {
        int fromByte = 0;
        for (int i = 0; i < 4; i++)
        {
            int n = (intByte[i] < 0 ? intByte[i] + 256 : (int) intByte[i]) << (8 * i);
            fromByte += n;
        }
        return fromByte;
    }

    /**
     * byte转换成char
     * 
     * @param b
     * @return
     */
    public static char byteToChar(byte b)
    {
        return (char) (b & 0xff);
    }
}
