package com.yinyang.yy.gooagoo.utils;

import java.util.HashMap;

import com.yinyang.yy.common.YYException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * json工具类
 * 
 * @author Administrator
 *
 */
public class JsonUtils
{
    private final static Gson gson = new GsonBuilder().create();

    /**
     * 将JSON串转化成MAP
     * @param json
     * @return
     * @throws YYException
     */
    public static HashMap<String, String> getMapFromJson(String json) throws YYException
    {
        AssertUtils.notEmpty(json, "【JsonUtils-getMapFromJson】传入参数json为空");
        try
        {
            return gson.fromJson(json.trim(), new TypeToken<HashMap<String, String>>()
            {
            }.getType());
        }
        catch (Exception e)
        {
            throw new YYException("【JsonUtils-getMapFromJson】发生异常", e, "json=" + json);
        }
    }

    /**
     * 对象转化成JSON
     * 
     * @param obj
     * @return
     * @throws YYException 
     */
    public static String toJson(Object obj) throws YYException
    {
        AssertUtils.notNull(obj, "【JsonUtils-toJson】传入参数obj为空");
        return gson.toJson(obj);
    }

    /**
     * JSON转化成对象
     * 
     * @param json
     * @param clazz
     * @return
     * @throws YYException
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws YYException
    {
        AssertUtils.notEmpty(json, "【JsonUtils-fromJson】传入参数json为空");
        AssertUtils.notNull(clazz, "【JsonUtils-fromJson】传入参数clazz为空");
        T obj = gson.fromJson(json, clazz);
        if (obj == null)
        {
            throw new YYException("【JsonUtils-fromJson】JSON串无法解析", "json=" + json + ",clazz=" + clazz);
        }
        else
        {
            return obj;
        }
    }
}
