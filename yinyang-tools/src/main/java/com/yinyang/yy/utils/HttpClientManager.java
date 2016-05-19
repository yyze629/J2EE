package com.yinyang.yy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * from merchant 
 * @Title: HttpClientManager.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午3:38:49
 * @version V1.0
 */
public class HttpClientManager {

	private static final Log logger = LogFactory.getLog(HttpClientManager.class);
	private static final String ENCODE = "UTF-8";
	private static MultiThreadedHttpConnectionManager connectionManager = null;
	private static HttpClientManager instance = new HttpClientManager();

	public static HttpClientManager getInstance() {
		return instance;
	}

	private int maxThreadsTotal = 256;// 最大线程数
	private int maxThreadsPerHost = 32; // 分配给每个客户端的最大线程数
	private int connectionTimeout = 10000;// 连接超时时间,毫秒
	private int soTimeout = 3000;// 读取数据超时时间，毫秒

	public HttpClientManager() {
		init();
	}

	public void init() {
		connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(connectionTimeout);
		params.setMaxTotalConnections(maxThreadsTotal);
		params.setSoTimeout(soTimeout);
		if (maxThreadsTotal > maxThreadsPerHost) {
			params.setDefaultMaxConnectionsPerHost(maxThreadsPerHost);
		} else {
			params.setDefaultMaxConnectionsPerHost(maxThreadsTotal);
		}
		connectionManager.setParams(params);
	}

	/**
	 * 根据url地址取得数据,type为请求类型post,
	 */
	public String requestDataByUrl(String url, RequestTypeEnum type, Map<String, String> parmMap) {
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
		// get方式
		if (url != null && (!url.equals("")) && type == RequestTypeEnum.GET) {
			String sendUrl = getSendUrl(url, parmMap);
			GetMethod getMethod = new GetMethod(sendUrl);
			int statusCode = 0;
			try {
				statusCode = httpClient.executeMethod(getMethod);
				InputStream inputStream = getMethod.getResponseBodyAsStream();
				String rtn = null;
				if (inputStream != null) {
					rtn = IOUtils.toString(inputStream, EncodeEnum.UTF.getEncode());
				}
				logger.info("statusCode:" + statusCode + ",rtn:" + rtn);
				if (statusCode == HttpStatus.SC_OK) {
					return rtn;
				}
			} catch (HttpException e1) {
				logger.error(e1);
			} catch (IOException e2) {
				logger.error(e2);
			} finally {
				getMethod.releaseConnection();
			}
		} else if (url != null && (!url.equals("")) && (type == RequestTypeEnum.POST)) {
			// post方式
			HttpClient client = new HttpClient();
			client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
			client.getParams().setContentCharset(ENCODE);
			PostMethod p = new PostMethod(url);
			
			if (parmMap != null) {
				NameValuePair[] params = new NameValuePair[parmMap.size()];
				AtomicInteger atomicInteger = new AtomicInteger(0);
				for (Map.Entry<String, String> parm : parmMap.entrySet()) {
					NameValuePair parmValue = new NameValuePair(parm.getKey(), parm.getValue());
					params[atomicInteger.getAndIncrement()] = parmValue;
				}
				p.setRequestBody(params);
			}
			try {
				int statusCode = httpClient.executeMethod(p);
				InputStream inputStream = p.getResponseBodyAsStream();
				String rtn = null;
				if (inputStream != null) {
					rtn = IOUtils.toString(inputStream, EncodeEnum.UTF.getEncode());
				}
				logger.info("statusCode:" + statusCode + ",rtn:" + rtn);
				if (statusCode == HttpStatus.SC_OK) {
					return rtn;
				}

			} catch (HttpException e1) {
				logger.error(e1);
			} catch (IOException e2) {
				logger.error(e2);
			} finally {
				p.releaseConnection();
			}
		}
		return null;
	}

	public String getSendUrl(String url, Map<String, String> parmMap) {
		if (url == null) {
			return null;
		}
		StringBuffer str = new StringBuffer(url);
		if (parmMap != null) {
			int i = parmMap.size();
			AtomicInteger atomicInteger = new AtomicInteger(0);
			if (url.indexOf("?") <= -1) {
				str.append("?");
			} else {
				str.append("&");
			}
			for (Map.Entry<String, String> parm : parmMap.entrySet()) {
				str.append(parm.getKey());
				str.append("=");
				str.append(parm.getValue());
				if (i - atomicInteger.incrementAndGet() >= 1) {
					str.append("&");
				}
			}
		}

		return str.toString();
	}

	public static MultiThreadedHttpConnectionManager getConnectionManager() {
		return connectionManager;
	}

	public static void setConnectionManager(MultiThreadedHttpConnectionManager connectionManager) {
		HttpClientManager.connectionManager = connectionManager;
	}

	public int getMaxThreadsTotal() {
		return maxThreadsTotal;
	}

	public void setMaxThreadsTotal(int maxThreadsTotal) {
		this.maxThreadsTotal = maxThreadsTotal;
	}

	public int getMaxThreadsPerHost() {
		return maxThreadsPerHost;
	}

	public void setMaxThreadsPerHost(int maxThreadsPerHost) {
		this.maxThreadsPerHost = maxThreadsPerHost;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}
	
	enum RequestTypeEnum {
		POST, GET;
		public static RequestTypeEnum valueOfEnum(String type) {

			if (type.equalsIgnoreCase("post")) {
				return POST;
			} else if (type.equalsIgnoreCase("get")) {

				return GET;
			} else {

				return GET;
			}

		}
	}
	
	enum EncodeEnum {
		UTF("utf-8"), GBK("gbk"), GB2312("gb2312");

		private EncodeEnum(String encode) {

			this.encode = encode;

		}

		public static EncodeEnum valueOfEnum(String encode) {

			if (encode.equals("utf-8")) {

				return UTF;
			} else if (encode.equals("gbk")) {

				return GBK;
			} else if (encode.equals("gb2312")) {
				return GB2312;

			} else {
				return null;
			}
		}

		private String encode;

		public String getEncode() {
			return encode;
		}

		public void setEncode(String encode) {
			this.encode = encode;
		}
	}
}
