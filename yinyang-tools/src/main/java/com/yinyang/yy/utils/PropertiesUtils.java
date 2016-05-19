package com.yinyang.yy.utils;

import java.util.Arrays;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * properties配置文件读取工具类
 * 
 * @Title: ShopifyConfig.java
 * @Package com.dhgate.shopify.web.util.searchlist
 * @author yinyang@dhgate.com
 * @date 2016年4月22日 下午8:17:41
 * @version V1.0
 */
public class PropertiesUtils {
	
	public static final String SHOPIFY_CALLBACK= "shopify.callback";
	public static final String WEBHOOK_CREATE= "webhook.create";
	public static final String WEBHOOK_QUERY= "webhook.query";
	public static final String WEBHOOK_TOPIC= "webhook.topic";
	
    private static PropertiesUtils dhconfig = new PropertiesUtils();
    private static Log log = LogFactory.getLog(dhconfig.getClass());
    
    private static Configuration config = null;
    private static String configfile = "readconfig.properties";
    
    static {
        try {
            config = new PropertiesConfiguration(configfile);
        } catch (ConfigurationException e) {
            e.printStackTrace();
            log.error("load properties file error,file name=" + configfile);
        }
    }

    public static String getString(String key) {
        return config.getString(key);
    }

    public static String[] getStringArray(String key) {
        return config.getStringArray(key);
    }

    public static int getInt(String key) {
        return config.getInt(key);
    }

    public static void main(String[] args) {
        String serverurl = PropertiesUtils.getString(WEBHOOK_CREATE);
        System.out.println(serverurl);
        String query = PropertiesUtils.getString(WEBHOOK_QUERY);
        System.out.println(query);
        String a = PropertiesUtils.getString("solrdata");
        System.out.println(a);
        String callback = PropertiesUtils.getString(SHOPIFY_CALLBACK);
        System.out.println(callback);
        String[] topic = PropertiesUtils.getStringArray(WEBHOOK_TOPIC);
        System.out.println(topic);
        
        System.out.println(topic.length);
        for (String string : topic) {
			System.out.println(string);
		}
        	
        System.out.println(Arrays.toString(topic));
    }
}