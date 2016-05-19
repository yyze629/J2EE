package com.yinyang.sellerpay.dao.util;


import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;

import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;
import com.taobao.diamond.utils.ParseUtils;

public class DHDiamondManager {
	static Log log = LogFactory.getLogger(DHDiamondManager.class);

	private static Map<String, Properties> propertiesCache = new ConcurrentHashMap<String, Properties>();

	private static final Object lock = new Object();

	/**
	 * Initialize Diamond Manager and properties cache by group and dataId.
	 * 
	 * @param group
	 * @param dataId
	 * @param propertiesCacheKey
	 */
	private static void initializeDiamondManager(final String group, final String dataId, final String propertiesCacheKey) {

		DiamondManager manager = new DefaultDiamondManager(group, dataId, new ManagerListener() {

			public Executor getExecutor() {
				return null;
			}

			// 监听服务端配置信息变化
			public void receiveConfigInfo(String configInfo) {
				// 客户端处理数据的逻辑
				log.info("config updated:" + configInfo);
				propertiesCache.put(propertiesCacheKey, covertToProperties(configInfo));
			}

		});

		String configInfo = manager.getAvailableConfigureInfomation(5000);
		propertiesCache.put(propertiesCacheKey, covertToProperties(configInfo));
	}

	// Convert properties from config, it may be null in code level.
	// But it can not be null if the change is made from Diamond console.
	private static Properties covertToProperties(String config) {
		Properties properties = null;
		if (config != null) {
			properties = ParseUtils.parserConfStr2Properties(config);
		}
		return properties;
	}

	/**
	 * 获取properties 对象
	 * 
	 * @param group
	 * @param dataId
	 * @return
	 */
	public static Properties getDiamondProperties(String group, String dataId) {
		String propertiesCacheKey = group + "_" + dataId;

		if (!propertiesCache.containsKey(propertiesCacheKey)) {
			synchronized (lock) {
				if (!propertiesCache.containsKey(propertiesCacheKey)) {
					// Initialize Diamond manage once for one
					// propertiesCacheKey.
					initializeDiamondManager(group, dataId, propertiesCacheKey);
				}
			}
		}
		return propertiesCache.get(propertiesCacheKey);
	}

	/**
	 * 
	 * @param group
	 * @param dataId
	 * @param properties
	 *            key
	 * @return
	 */
	public static String getDiamondPropertiesValue(String group, String dataId, String key) {
		String value = null;
		// 如果出现异常。默认返回null
		try {
			Properties pro = getDiamondProperties(group, dataId);
			if (null != pro) {
				value = pro.getProperty(key);
			}
		} catch (Exception e) {
			log.error(e);
			value = null;
		}

		return value;
	}
	/****
	 * @param key
	 * */
	public static String getDiamondValue(String key){
		String value = getDiamondPropertiesValue(DependenciesServiceConstant.GROUP,DependenciesServiceConstant.DATA_ID,key);
		return value;
	}
}
