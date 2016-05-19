package com.yinyang.yy.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.yinyang.yy.common.YYException;

/**
 * url工具类 通过url获取文件 or 获取class路径
 * 
 * @Title: UrlUtils.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午4:30:40
 * @version V1.0
 */
public class UrlUtils {
	
	public static final Log logger = LogFactory.getLog(UrlUtils.class);

	/**
	 * 读取日志配置文件
	 * 
	 * @throws YYException
	 */
	public static void initLog4jProperties() {
		// 未打包时读取配置
		String file = FileUtils.class.getClassLoader().getResource("log4j.properties").getFile();
		if (new java.io.File(file).exists()) {
			PropertyConfigurator.configure(file);
			return;
		}

		// 读取jar包内配置文件
		InputStream in = FileUtils.class.getClassLoader().getResourceAsStream("log4j.properties");
		Properties p = new Properties();
		try {
			p.load(in);
			PropertyConfigurator.configure(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得类所在的文件
	 * 
	 * @param clazz
	 * @return
	 */
	public static File getClassFile(Class<?> clazz) {
		URL path = clazz.getResource(clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1) + ".classs");
		if (path == null) {
			String name = clazz.getName().replaceAll("[.]", "/");
			path = clazz.getResource("/" + name + ".class");
		}
		return new File(path.getFile());
	}

	/**
	 * 得到类的绝对路径
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getClassAbsolutePath(Class<?> clazz) {
		try {
			return java.net.URLDecoder.decode(getClassFile(clazz).getAbsolutePath(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 取得类所在的ClassPath目录，比如tomcat下的classes路径
	 * 
	 * @param clazz
	 * @return
	 */
	public static File getClassRootPathFile(Class<?> clazz) {
		File file = getClassFile(clazz);
		int count = clazz.getName().split("[.]").length;
		for (int i = 0; i < count; i++) {
			file = file.getParentFile();
		}
		if (file.getName().toUpperCase().endsWith(".JAR!")) {
			file = file.getParentFile();
		}
		return file;
	}

	/**
	 * 取得当前类所在的ClassPath路径
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getClassRootPath(Class<?> clazz) {
		try {
			String absolutePath = getClassRootPathFile(clazz).getAbsolutePath();
			return java.net.URLDecoder.decode(absolutePath, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 获取URL
	 * 
	 * @param fileName
	 * @return
	 */
	public static URL getUrl(String fileName) {
		return UrlUtils.class.getClassLoader().getResource(fileName);
	}

	/**
	 * 通过URL读取文件内容
	 */
	@SuppressWarnings("finally")
	public static String readUrl(String url) {
		String content = "";
		try {
			URL urlfile = new URL(url);
			URLConnection connection = urlfile.openConnection();
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(10000);
			BufferedReader in = null;
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));// 通过设置超时机制,优化远程文件操作
			String inputLine = in.readLine();
			while (inputLine != null) {
				content += inputLine + "\r\n";
				inputLine = in.readLine();
			}
			in.close();
		} catch (Exception e) {
			logger.error("Read url error!" + e.getMessage() + ":" + url);
		} finally {
			return content;
		}
	}

	/**
	 * 
	 * @param regstr
	 * @return
	 */
	public static String reg(String regstr) {
		if (StringUtil.isEmpty(regstr)) {
			return "";
		}
		return regstr.replaceAll("%", "dhgate_bfh").replaceAll("&", "dhgate_yh").replaceAll("#", "dhgate_jh").trim();
	}

	public static String replaceReg(String regstr) {
		if (StringUtil.isEmpty(regstr)) {
			return "";
		}
		return regstr.replaceAll("dhgate_bfh", "%").replaceAll("dhgate_yh", "&").replaceAll("dhgate_jh", "#").trim();
	}
}
