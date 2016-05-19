package com.yinyang.sellerpay.web.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DHconfig {
	private static DHconfig dhconfig = new DHconfig();
	private static Log log = LogFactory.getLog(dhconfig.getClass());

	private static Configuration config = null;
	private static String configfile = "config.properties";
	static {
		try {
			config = new PropertiesConfiguration(configfile);
		} catch (ConfigurationException e) {
			e.printStackTrace();
			log.error("load properties file error,file name =" + configfile);
		}
	}

	public static String getString(String key) {
		return config.getString(key);
	}

	public static String[] getStringArray(String key) {
		return config.getStringArray(key);
	}

	public static void main(String[] args) {
		String serverurl = DHconfig.getString("track_thread_names");
		System.out.println(serverurl);
	}

	public static int getInt(String key) {
		return config.getInt(key);
	}
}