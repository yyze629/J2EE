package com.yinyang.yy.superutils.j2se;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 获取 项目/文件 路径
 * @author <a href="http://www.xdemo.org/">http://www.xdemo.org/</a>
 * 252878950@qq.com
 */
public class PathUtils {
	
	/**
	 * 获取项目的绝对路径
	 * @return Sting 获取项目的绝对路径
	 */
	public static String getProjectAbsolutelyPath(){
		return System.getProperty("user.dir"); 
	}
	
	/**
	 * 获取所在的盘符
	 * @return String
	 */
	public static String getProjectDrivePath() {
		  return new File("/").getAbsolutePath();
	}
	
	/**
	 * 获取指定类的路径
	 * @param clazz
	 * @return String
	 */
	public static String getClassAbsolutePath(Class<?> clazz) {
		  return clazz.getResource("").getPath().substring(1);
	}
	
	public static String getJarPath() throws IOException, URISyntaxException {
		URL url = PathUtils.class.getProtectionDomain().getCodeSource().getLocation();
		String filePath = null;
		try {
			filePath = URLDecoder.decode(url.getPath(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);

		return new File(filePath).getAbsolutePath();
	}

}
