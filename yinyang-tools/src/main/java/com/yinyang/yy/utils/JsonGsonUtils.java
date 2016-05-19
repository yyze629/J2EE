package com.yinyang.yy.utils;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yinyang.yy.common.YYException;


/** 
 * JSON工具类
 * @author  作者：yinyang E-mail: 
 * @date 创建时间：2015年4月8日 上午12:55:38 
 * @version 1.0  
 */
public class JsonGsonUtils
{
	/**
	 * JSON序列化.txt
	 * //注意这里的Gson的构建方式为GsonBuilder,区别于test1中的Gson gson = new Gson();  
     *  Gson gson = new GsonBuilder()  
     *  .excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性  
     *  .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式  
     *  .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//时间转化为特定格式    
     *  .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)//会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.  
     *  .setPrettyPrinting() //对json结果格式化.  
     *  .setVersion(1.0)    //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.  
     *                      //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么  
     *                      //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.  
     *  .create();  
	 */
	
    private final static Gson gson = new GsonBuilder().serializeNulls().create();

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
