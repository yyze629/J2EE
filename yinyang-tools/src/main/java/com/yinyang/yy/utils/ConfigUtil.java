package com.yinyang.yy.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件工具类
 * 
 * @Title: ConfigUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午1:19:55
 * @version V1.0
 */
public class ConfigUtil {
	private static Properties p = new Properties();
	// 用静态代码块
	static {
		try {

			String filePath = System.getProperty("user.dir") + "/conf/jdbc.properties";
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			p.load(in);
			// p.load(ConfigUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			/*
			 * p.load(ClassLoader.getSystemResourceAsStream( "db.properties"));
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getDriver() {
		return p.getProperty("database.driver");
	}

	public static String getUrl() {
		return p.getProperty("database.url");
	}

	public static String getUser() {
		return p.getProperty("database.user");
	}

	public static String getPwd() {
		return p.getProperty("database.pwd");
	}

	public static String getMessageUrl() {
		return p.getProperty("database.messageUrl");
	}

}