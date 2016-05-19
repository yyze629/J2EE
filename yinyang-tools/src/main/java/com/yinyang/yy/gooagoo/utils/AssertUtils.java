package com.yinyang.yy.gooagoo.utils;

import java.util.Collection;
import java.util.Map;

import com.yinyang.yy.common.YYException;


/**
 * 断言工具类
 * 
 * @author Administrator
 *
 */
public class AssertUtils
{
    /**
     * 判断是否为True
     * 
     * @param expression
     * @param message
     * @throws YYException 
     */
    public static void isTrue(boolean expression, String message) throws YYException
    {
        if (!expression)
        {
            throw new YYException(message);
        }
    }

    /**
     * 判断对象是否为NULL
     * 
     * @param object
     * @param message
     * @throws YYException 
     */
    public static void isNull(Object object, String message) throws YYException
    {
        if (object != null)
        {
            throw new YYException(message);
        }
    }

    /**
     * 判断对象是否不为NULL
     * 
     * @param object
     * @param message
     * @throws YYException 
     */
    public static void notNull(Object object, String message) throws YYException
    {
        if (object == null)
        {
            throw new YYException(message);
        }
    }

    /**
     * 判断字符串是否为空
     * 
     * @param str
     * @param message
     * @throws YYException
     */
    public static void notEmpty(String str, String message) throws YYException
    {
        if (str == null || "".equals(str))
        {
            throw new YYException(message);
        }
    }

    /**
     * 判断数组不为空
     * 
     * @param array
     * @param message
     * @throws YYException 
     */
    public static void notNull(Object[] array, String message) throws YYException
    {
        if (array == null || array.length != 0)
        {
            throw new YYException(message);
        }
    }

    /**
     * 判断byte数组不为空
     * 
     * @param array
     * @param message
     * @throws YYException 
     */
    public static void notNull(byte[] bytes, String message) throws YYException
    {
        if (bytes == null || bytes.length == 0)
        {
            throw new YYException(message);
        }
    }

    /**
     * 判断数组里对象不为NULL
     * 
     * @param array
     * @param nullMes
     * @param eleNullMsg
     * @throws YYException
     */
    public static void notNull(Object[] array, String nullMes, String eleNullMsg) throws YYException
    {
        if (array != null)
        {
            for (int i = 0; i < array.length; i++)
            {
                if (array[i] == null)
                {
                    throw new YYException(eleNullMsg);
                }
            }
        }
        else
        {
            throw new YYException(nullMes);
        }
    }

    /**
     * 判断集合是否为NULL
     * 
     * @param collection
     * @param message
     * @throws YYException 
     */
    public static void notNull(Collection<?> collection, String message) throws YYException
    {
        if (collection == null || collection.size() == 0)
        {
            throw new YYException(message);
        }
    }

    /**
     * 判断Map是否为Null
     * 
     * @param map
     * @param message
     * @throws YYException 
     */
    public static void notNull(Map<?, ?> map, String message) throws YYException
    {
        if (map == null || map.size() == 0)
        {
            throw new YYException(message);
        }
    }

    /**
     * 判断类型
     * 
     * @param clazz
     * @param obj
     * @param mesage
     * @throws YYException
     */
    public static void isInstanceOf(Class<?> clazz, Object obj, String mesage) throws YYException
    {
        notNull(clazz, "【AssertUtils-isInstanceOf】【clazz不能为空】");
        if (!clazz.isInstance(obj))
        {
            throw new YYException(mesage);
        }
    }

    /**
     * 判断继承关系
     * 
     * @param superType
     * @param subType
     * @param message
     * @throws YYException
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, String message) throws YYException
    {
        notNull(superType, "【AssertUtils-isAssignable】【superType不能为空】");
        if (subType == null || !superType.isAssignableFrom(subType))
        {
            throw new YYException(message);
        }
    }

    /**
     * 判断是否为True
     * 
     * @param expression
     * @param message
     * @throws YYException 
     */
    public static void isEqual(int fir, int sec, String message) throws YYException
    {
        if (fir != sec)
        {
            throw new YYException(message);
        }
    }

    /**
     * 判断是否为True
     * 
     * @param expression
     * @param message
     * @throws YYException 
     */
    public static void isEqual(String fir, String sec, String message) throws YYException
    {
        notEmpty(fir, "【AssertUtils-isEqual】第一个字符串为空");
        notEmpty(sec, "【AssertUtils-isEqual】第二个字符串为空");
        if (!fir.equals(sec))
        {
            throw new YYException(message);
        }
    }
}
