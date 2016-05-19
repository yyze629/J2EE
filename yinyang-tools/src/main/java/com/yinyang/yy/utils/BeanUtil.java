package com.yinyang.yy.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

/**
 * bean拷贝工具类
 * 
 * @Title: BeanUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午4:06:18
 * @version V1.0
 */
public class BeanUtil{
	/**字段值拷贝,如果src包括NULL值,也进行拷贝 ->常用于:po转成dto**/
	public static void copyProperties(Object src, Object dest) {
		BeanUtils.copyProperties(src,dest);
	}

	/**字段值拷贝,如果src包括NULL值,不进行拷贝 ->常用于:dto转成po **/
	public static void copyPropertiesWithoutNULL(Object src, Object dest){
		try{
			Field[] srcFields = src.getClass().getDeclaredFields();
			for(Field srcField:srcFields){
				//1.取值
				srcField.setAccessible(true);
				Object srcValue =  srcField.get(src);
				if(srcValue==null){
					continue;
				}
				//2.取得目标对象相同名称的域
				//2.1 忽视 serialVersionUID
				String srcFieldName = srcField.getName(); 
				if(srcFieldName.equals("serialVersionUID")){
					continue;
				}
				//2.2目标如果无此域,则转过
				Field destField = null;
				try{
					destField = dest.getClass().getDeclaredField( srcFieldName );
				}catch(NoSuchFieldException ex){
					continue;
				}
				if(destField==null){
					continue;
				}
				//3.复制
				destField.setAccessible(true);
				destField.set(dest, srcValue);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 属性赋值
	 * @param dest  赋值对象
	 * @param orig  源对象
	 * @throws Exception
	 */
	public static void copyBean(Object dest, Object orig){
		try{
			BeanUtils.copyProperties(dest, orig);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 属性复制
	 * @param <T>
	 * @param toBeanClass  复制到对象的class
	 * @param fromBean    属性来源对象
	 * @return 复制出的对象
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static <T> T copyBean(Class<T> toBeanClass, Object fromBean){
		T toBean = null;
		try{
			toBean = toBeanClass.newInstance();
			BeanUtils.copyProperties(toBean, fromBean);
		}catch(Exception e){
			e.printStackTrace();
		}
		return toBean;
	}

	/**
	 * list集合bean属性复制
	 * @param <T>
	 * @param toBeanClass
	 * @param fromBeanList
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static <T> List<T> copyList(Class<T> toBeanClass, List<?> fromBeanList){
		List<T> destList = new ArrayList<T>();
		try{
			for (Object fromBean : fromBeanList) {
				destList.add(BeanUtil.copyBean(toBeanClass, fromBean));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return destList;
	}
}