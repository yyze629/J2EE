package com.yinyang.sellerpay.dao.server;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.dhgate.apsaras.access.spring.ApsarasClassPathXmlApplicationContext;

/**
 * 利用netty进行rpc通信，同时注册zookeeper，现在暂时没用了，已去掉zookeeper和netty改装成web项目
 * 
 * @Title: Startup.java
 * @Package com.yinyang.sellerpay.dao.server
 * @author yinyang@dhgate.com
 * @date 2015年12月25日 下午3:41:09
 * @version V1.0
 */
public class Startup {

    
	/**
	 * 第一次启服务用   
	 * cd /d F:\word8.5\temp\msg32\dhgate-msg-api
	 * mvn clean install eclipse:eclipse   然后刷新工程，启动startup.javf
	 *  
	 * 在api目录 打包 mvn package 会打在target下。把这个jar包放到。commonEJB的lib下。
	 * 
	 * 第二  以后修改代码启服务用
	 * cd /d F:\word8.5\temp\msg32\
	 * mvn clean install eclipse:eclipse   然后刷新工程，启动startup.javf
	 * 
	 * 
	 * 实用本地服务的时候 jobss  jdk中加入下面内容 -DserverIP=192.168.211.45：8084 
	 * 
	 * 测试用地址 指向本地。修改linux的环境变量
	 * vi .bash_profile
	 * export serverIP=192.168.222.172
	 * 
	 */
	public static void main(String[] args) {
	    AbstractApplicationContext  context =  new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml","classpath:applicationContext-apsaras.xml","classpath:applicationContext-ehcache.xml"});
		context.registerShutdownHook();
	}

}
