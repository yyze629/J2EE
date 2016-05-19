package com.yinyang.yy.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * Json工具类[基于net.sf.json包]
 * 
 * @Title: JsonUtils.java
 * @Package com.dhgate.wisdom.util
 * @author yinyang@dhgate.com
 * @date 2015年12月8日 上午11:07:52
 * @version V1.0
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class JsonUtils {
	
	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final Log log = LogFactory.getLog(JsonUtils.class);
	
    /** 
     * 由字符串反序列化成实体类  针对的是一个实体，此实体中的属性不包括自定义的类型，如Teacher类型，或者List<Teacher>类型  
     * @param source 传入json中的字符串 
     * @param beanClass 实体类的类型 
     * @return 实体类 
     */  
    public static Object getObjFromJsonArrStr(String source,Class<?> beanClass){  
    	JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(source);  
    	return  JSONObject.toBean(jsonObject,beanClass);          
    }
    
    /** 
     * 由字符串反序列化成实体类  针对的是一个实体，此实体中的属性包括自定义的类型，如Teacher类型，或者List<Teacher>类型 
     * @param jsonArrStr 
     * @param clazz 
     * @param classMap 
     * @return 
     */
	public static Object getObjFromJsonArrStr(String jsonArrStr, Class<?> clazz, Map classMap) {    
    	JSONObject jsonObj = JSONObject.fromObject(jsonArrStr);    
    	return JSONObject.toBean(jsonObj, clazz, classMap);              
    }
	
    /** 
     * 将string转换成listBean 
     * @param jsonArrStr 需要反序列化的字符串 
     * @param clazz 被反序列化之后的类 
     * @return 实体list 
     */  
    public static List<?> getListFromJsonArrStr(String jsonArrStr, Class<?> clazz) {    
    	JSONArray jsonArr = JSONArray.fromObject(jsonArrStr);    
    	List list = new ArrayList();    
    	for (int i = 0; i < jsonArr.size(); i++)   
    	{    
    		list.add(JSONObject.toBean(jsonArr.getJSONObject(i), clazz));    
    	}    
    	return list;    
    }   
    
    /** 
     * 将string转换成listBean 属性中包含实体类等 如List<Student> 而Student中含有属性List<Teacher> 
     * @param jsonArrStr 需要反序列化的字符串 
     * @param clazz 反序列化后的类 
     * @param classMap 将属性中包含的如Teacher加入到一个Map中，格式如map.put("teacher",Teacher.class) 
     * @return 反序列化后的字符串 
     * 使用示例： 
       Map classMap = new HashMap(); 
       //必须要对Parent进行初始化 否则不识别 
       Teacher p = new Teacher(); 
       classMap.put("teacher", p.getClass()); 
       List mlist = JSONTransfer.getListFromJsonArrStr(resultStr, Student.class, classMap); 
     */  
    public static List<?> getListFromJsonArrStr(String jsonArrStr, Class<?> clazz, Map classMap) {    
    	JSONArray jsonArr = JSONArray.fromObject(jsonArrStr);    
    	List list = new ArrayList();    
    	for (int i = 0; i < jsonArr.size(); i++)   
    	{              
    		list.add(JSONObject.toBean(jsonArr.getJSONObject(i), clazz, classMap));    
    	}    
    	return list;    
    }  
         
    /** 
     * 序列化操作，无论是单个的对象，还是list，抑或是list中的属性仍包含list，都可以直接序列化成String类型 
     * @param obj 需要被序列化的对象 
     * @return 序列化之后的字符串 
     */
    public static String getJsonArrStrFromList(Object obj)   
    {   
    	String jsonStr = null;    
    	if (obj == null) {
    		return "{}";    
    	}    
        JsonConfig jsonConfig = new JsonConfig();    
        log.debug("JsonUtils注册日期处理器...");    
        jsonConfig.registerJsonValueProcessor(Date.class , new JsonUtils().new JsonDateValueProcessorUtil(FORMAT));
        /** 判断是否是list */
        if (obj instanceof Collection || obj instanceof Object[]) {    
        	jsonStr = JSONArray.fromObject(obj, jsonConfig).toString();    
        } else {    
        	jsonStr = JSONObject.fromObject(obj, jsonConfig).toString();    
        }        
        return jsonStr;   
    }  
    
    /**
     * 日期格式化内部类 
     * for 格式化日期
     * @Title: JsonUtils.java
     * @Package com.dhgate.wisdom.util
     * @author yinyang@dhgate.com
     * @date 2015年12月8日 上午11:06:03
     * @version V1.0
     */
    class JsonDateValueProcessorUtil implements JsonValueProcessor{
    	
    	private String dataFormat = "yyyy-MM-dd HH:mm:ss";
    	//private final Log log = LogFactory.getLogger(JsonDateValueProcessorUtil.class);
    	
    	public JsonDateValueProcessorUtil(){
    	}
    	
    	public JsonDateValueProcessorUtil(String format){
    		this.dataFormat = format;
    	}
    	
    	public Object processArrayValue(Object value, JsonConfig config) {   
    		return process(value);   
    	}   
    	
    	public Object processObjectValue(String key, Object value, JsonConfig config) {   
    		return process(value);   
    	}   
    	
    	private Object process(Object value){   
    		if(value instanceof Date){   
    			String format = "";
    			try {
    				SimpleDateFormat sdf = new SimpleDateFormat(this.dataFormat,Locale.UK);
    				format = sdf.format(value);
    			} catch (Exception e) {
    				log.error("JsonUtils日期格式化内部类JsonDateValueProcessorUtil类日期转换异常,"+e);
    			}
    			return format;   
    		} 
    		return value == null ? "" : value.toString();   
    	}
    	
    	/**
    	 * object转json [日期默认为yyyy-MM-dd HH:mm:ss格式]
    	 * @param object
    	 * @return
    	 */
    	public String toJson(Object object){
    		JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessorUtil());
            
    		String jsonString = JSONArray.fromObject(object, jsonConfig).toString();
    		
    		return StringUtils.isBlank(jsonString) ? null : jsonString;
    	}
    	
    	public String toJson(Object object, String dateFormat){
    		JsonConfig jsonConfig = new JsonConfig();
    		if(StringUtils.isBlank(dateFormat)){
    			log.warn("JsonUtils工具类,日期格式化内部类JsonDateValueProcessorUtil入参dateFormat:["+dateFormat+"]异常,已自动转为默认格式:[yyyy-MM-dd HH:mm:ss]!");
    			jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessorUtil());
    		}else{
    			jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessorUtil(dateFormat));
    		}
    		
    		String jsonString = JSONArray.fromObject(object, jsonConfig).toString();
    		
    		return StringUtils.isBlank(jsonString) ? null : jsonString;
    	}
    }//内部类
}
