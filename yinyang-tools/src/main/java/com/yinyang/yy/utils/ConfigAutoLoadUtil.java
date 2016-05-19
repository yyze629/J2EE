package com.yinyang.yy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 配置文件变动自动加载
 * 目的: 程序配置信息 说明:
 * 实现对配置文件(/usr/local/project/mailsend.properties)的动态加载
 * 
 * @Title: ConfigAutoLoadUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午4:44:04
 * @version V1.0
 */
public class ConfigAutoLoadUtil {
	private static final Log logger = LogFactory.getLog(ConfigAutoLoadUtil.class);
	// private static final String configFilename =
	// "E:/mailsend.properties";//文件路径
	private static final String configFilename = "/usr/local/project/mailsend.properties";// 文件路径
	private static long configFileLastModified = 0;// 属性文件最后修改时间
	private static Properties prop = null;// 属性对象
	private static ReentrantLock propLock = new ReentrantLock();// 属性对象互斥锁,避免加载属性对象时与其它线程的访问发生冲突
	private static Timer configFileListener = null;// 属性文件监听器

	static {
		// 1.初始化加载属性文件
		InputStream in = null;
		try {
			File configFile = new File(configFilename);
			prop = new Properties();
			in = new FileInputStream(configFile);
			prop.load(in);
			configFileLastModified = configFile.lastModified();
			logger.info("Configration file [" + configFilename + "] has been loaded successfully!");
		} catch (FileNotFoundException e) {
			prop = null;
			logger.error("Configration file [" + configFilename + "] is not exist! please check!");
		} catch (Exception e) {
			prop = null;
			logger.error("Configration file [" + configFilename + "] can't been read! please check!", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
				}
			}
		}
		// 2.如果加载成功,启动属性文件监听器
		if (prop != null && configFileListener == null) {
			configFileListener = new Timer("ConfigFileListener", true);
			configFileListener.schedule(new TimerTask() {
				public void run() {
					// 监听文件变化,变化时重新加载
					Properties temp_prop = prop;// 备份一下,防止原来的属性丢失
					InputStream in = null;
					try {
						File configFile = new File(configFilename);
						if (configFile.lastModified() > configFileLastModified) {
							propLock.lock();
							prop = new Properties();
							in = new FileInputStream(configFile);
							prop.load(in);
							configFileLastModified = configFile.lastModified();
							propLock.unlock();
							logger.info("Configration file [" + configFilename + "] has been modified, reload successfully!");
						}
					} catch (FileNotFoundException e) {
						prop = temp_prop;// 重置为原来的
						logger.error("Configration file [" + configFilename + "] is not exist! please check!");
					} catch (Exception e) {
						prop = temp_prop;// 重置为原来的
						logger.error("Configration file [" + configFilename + "] can't been read! please check!", e);
					} finally {
						if (propLock.isHeldByCurrentThread()) {
							propLock.unlock();
						}
						if (in != null) {
							try {
								in.close();
							} catch (IOException ex) {
							}
						}
					}
				}
			}, 1000, 3000);
		}
	}

	/**
	 * 获取一个属性值(字符串)
	 */
	public static String getString(String key) {
		propLock.lock();
		String value = prop.getProperty(key);
		propLock.unlock();
		return value;
	}

	/**
	 * 获取一个属性值(数字)
	 */
	public static int getInt(String key) {
		return Integer.parseInt(getString(key).trim());
	}
}