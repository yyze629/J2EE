

package com.yinyang.yy.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 * byte处理工具类
 * 提供一些便利的byte处理工具
 * jianghang 2011-10-9 下午06:22:13
 * @Title: ByteUtils.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午4:52:17
 * @version V1.0
 */
public class ByteUtils {

    public static String bigIntToHex(BigInteger big) {
        return big.toString(16);
    }

    public static BigInteger hexToBigInt(String hex) {
        return new BigInteger(hex, 16);
    }

    public static String bytesToString(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new NestableRuntimeException(e);
        }
    }

    public static byte[] stringToBytes(String string) {
        try {
            return string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new NestableRuntimeException(e);
        }
    }

    public static String bytesToBase64String(byte[] bytes) {
        try {
            return new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new NestableRuntimeException(e);
        }
    }

    public static byte[] base64StringToBytes(String string) {
        try {
            return Base64.decodeBase64(string.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new NestableRuntimeException(e);
        }
    }

    public static byte[] int2bytes(int i) {
        byte[] b = new byte[4];
        int2bytes(i, b, 0);
        return b;
    }

    public static int bytes2int(byte[] b) {
        ByteBuffer buf = ByteBuffer.allocate(4);
        buf.put(b);
        buf.flip();
        return buf.getInt();
    }

    public static byte[] long2bytes(long l) {
        byte[] b = new byte[8];
        long2bytes(l, b, 0);
        return b;
    }

    public static long bytes2long(byte[] b) {
        ByteBuffer buf = ByteBuffer.allocate(8);
        buf.put(b);
        buf.flip();
        return buf.getLong();
    }

    public static int int2bytes(int i, byte[] data, int offset) {
        data[offset++] = (byte) ((i >> 24) & 0xff);
        data[offset++] = (byte) ((i >> 16) & 0xff);
        data[offset++] = (byte) ((i >> 8) & 0xff);
        data[offset] = (byte) (i & 0xff);
        return 4;
    }

    public static int bytes2int(byte[] data, int offset) {
        ByteBuffer buffer = ByteBuffer.wrap(data, offset, 4);
        return buffer.getInt();
    }

    public static int long2bytes(long l, byte[] data, int offset) {
        data[offset++] = (byte) ((l >> 56) & 0xff);
        data[offset++] = (byte) ((l >> 48) & 0xff);
        data[offset++] = (byte) ((l >> 40) & 0xff);
        data[offset++] = (byte) ((l >> 32) & 0xff);
        data[offset++] = (byte) ((l >> 24) & 0xff);
        data[offset++] = (byte) ((l >> 16) & 0xff);
        data[offset++] = (byte) ((l >> 8) & 0xff);
        data[offset] = (byte) (l & 0xff);
        return 8;
    }

    public static long bytes2long(byte[] data, int offset) {
        ByteBuffer buffer = ByteBuffer.wrap(data, offset, 8);
        return buffer.getLong();
    }

}
