package com.yinyang.yy.gooagoo.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.yinyang.yy.common.YYException;

/**
 * 对象处理工具
 * 
 * @author Administrator
 *
 */
public class ObjectUtils
{
    /**
     * 将对象转化为字节数组
     * 
     * @param object
     * @return
     * @throws YYException 
     */
    public static byte[] toBytes(Object object) throws YYException
    {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try
        {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        }
        catch (Exception e)
        {
            throw new YYException("【ObjectUtils-toBytes】获取数量异常", e, "object:" + object);
        }
        finally
        {
            if (baos != null)
            {
                try
                {
                    baos.close();
                }
                catch (IOException e)
                {
                    throw new YYException("【ObjectUtils-toBytes】关闭流异常", e);
                }
            }

            if (oos != null)
            {
                try
                {
                    oos.close();
                }
                catch (IOException e)
                {
                    throw new YYException("【ObjectUtils-toBytes】关闭流异常", e);
                }
            }
        }
    }

    /**
     * 将字节数组转化为对象
     * 
     * @param bytes
     * @return
     * @throws YYException 
     */
    public static Object toObject(byte[] bytes) throws YYException
    {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try
        {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e)
        {
            throw new YYException("【ObjectUtils-toObject】获取数量异常", e, "bytes:" + bytes);
        }
        finally
        {
            if (bais != null)
            {
                try
                {
                    bais.close();
                }
                catch (IOException e)
                {
                    throw new YYException("【ObjectUtils-toObject】关闭流异常", e);
                }
            }

            if (ois != null)
            {
                try
                {
                    ois.close();
                }
                catch (IOException e)
                {
                    throw new YYException("【ObjectUtils-toObject】关闭流异常", e);
                }
            }
        }
    }

}
