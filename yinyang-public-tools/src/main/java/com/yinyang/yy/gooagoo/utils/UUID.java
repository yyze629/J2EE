package com.yinyang.yy.gooagoo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 生成有序UUID 如果每台机器每个进程每纳秒产生33554431个值 至31690708年07月05日前不重复
 * @author 王宇
 *
 */
public class UUID
{
    private final static String mac = cover(Long.toString(Long.valueOf(GetMacs(), 16), 36), 7);
    private final static String pid = NUM32(Thread.currentThread().getId(), 3); //linux默认pid最大32767
    private static int i = new Random().nextInt(9);

    public static String getUUID()
    {
        String millis = NUM32(System.currentTimeMillis(), 12);
        String nanoTime = NUM32(System.nanoTime(), 5);
        String count = NUM32(getValue(), 5);
        return millis + nanoTime + count + mac + pid;
    }

    public static String NUM32(Long l, int wide)
    {
        String or = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] cs = new char[wide];

        while (wide > 0)
        {
            int surplus = (int) (l & 31);
            cs[wide - 1] = or.charAt(surplus);
            l = l >> 5;
            wide--;
        }
        return new String(cs);
    }

    private static synchronized long getValue()
    {
        if (i > 33554431L) //5位32进制极限值
        {
            i = 0;
        }
        i++;
        return i;
    }

    private static String cover(String str, int digit)
    {
        char[] original = "00000000000000000000000000000000".toCharArray();
        int point = original.length - 1;
        for (int i = str.length() - 1; i >= 0; i--)
        {
            original[point] = str.charAt(i);
            point--;
        }
        String result = new String(original, original.length - digit, digit);
        return result.toUpperCase();
    }

    private static String getOSName()
    {
        return System.getProperty("os.name").toLowerCase();
    }

    private static String getMACAddress(String commond, String regular)
    {
        Pattern pattern = Pattern.compile(regular); //windows "..-..-..-..-..-.."
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try
        {
            process = Runtime.getRuntime().exec(commond); //windows getmac
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find())
                {
                    mac = matcher.group().replaceAll("\\W", "");
                    break;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (bufferedReader != null)
                {
                    bufferedReader.close();
                }
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            finally
            {
                bufferedReader = null;
                process = null;
            }
        }
        if (mac == null || mac.isEmpty())
        {
            InetAddress addr;
            try
            {
                addr = InetAddress.getLocalHost();
                mac = addr.getHostAddress().replaceAll("\\W", "");
            }
            catch (UnknownHostException e)
            {
                e.printStackTrace();
            }
        }
        return mac;
    }

    private static String GetMacs()
    {
        String os = getOSName();
        if (os.startsWith("windows"))
        {
            return getMACAddress("getmac", "..-..-..-..-..-..");
        }
        else
        {
            return getMACAddress("ifconfig", "..:..:..:..:..:..");
        }
    }
}