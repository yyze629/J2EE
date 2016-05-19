package com.yinyang.yy.superutils.thirdparty;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.yinyang.yy.superutils.test.Order;
import com.yinyang.yy.superutils.test.User;


/**
 * XML序列化与反序列化工具类
 * @author <a href="http://www.xdemo.org">xdemo.org</a>
 * 2015年2月28日
 * @see <a href="http://simple.sourceforge.net/download/stream/doc/tutorial/tutorial.php">Simple-XML</a>
 */
public class XmlUtils {
	
	/**
	 * 将Bean转换成XML（需要Simple-XML提供的注解）
	 * @throws Exception 
	 */
	public static void beanToXml(Object o,String xmlFile) throws Exception{
		
		Serializer serializer = new Persister();
		File file = new File(xmlFile);
		serializer.write(o, file);
	}
	
	public static Object xmlToBean(String xml,Object bean) throws Exception{
		File file = new File(xml);  
		Serializer serializer = new Persister();
		return serializer.read(bean,file);
	}
	
	public static void main(String[] args) throws Exception {
		User u=new User();
		u.setAddress("xxx");
		u.setName("GGG");
		u.setAge(22);
		List<Order> ss=new ArrayList<Order>();
		ss.add(new Order("22","x2001-12-12", new Date()));
		ss.add(new Order("33","x2021-02-02", new Date()));
		u.setOrders(ss);
		beanToXml(u, "D:\\x.xml");
		u=new User();
		xmlToBean("D:\\x.xml", u);
		System.out.println(u.getAddress());
	}

}
