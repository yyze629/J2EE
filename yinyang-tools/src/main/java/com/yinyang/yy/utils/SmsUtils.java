package com.yinyang.yy.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 短信发送工具类
 * 还可生成手机验证码
 * @Title: SmsUtils.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午3:46:13
 * @version V1.0
 */
public class SmsUtils {

	private static final Log log = LogFactory.getLog(SmsUtils.class);

	private static String URL;

	// init
	static {
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sms.properties"));
			URL = (String) props.get("url");
		} catch (IOException e) {
			log.error(e);
		}
	}

	/**
	 * 即时发送短信
	 * 
	 * @param mobiles
	 *            多个手机号,以小写,分格
	 * @param content
	 *            内容
	 * @param priority
	 *            优先级
	 * @return
	 */
	public static boolean send_immediately(String mobiles, String content, int priority) {
		boolean bret = false;
		PostMethod method = null;

		if (mobiles == null || mobiles.trim().length() < 1 || content == null || content.trim().length() < 1 || priority < 1 || priority > 5) {
			return false;
		}

		try {
			HttpClient client = new HttpClient();
			/*** add by leidengyan:添加超时的限制 */
			HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();
			// 设置连接超时时间(单位毫秒)
			managerParams.setConnectionTimeout(5000);
			// 设置读数据超时时间(单位毫秒)
			managerParams.setSoTimeout(5000);

			method = new PostMethod(URL);
			method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			method.setParameter("m", mobiles.trim());
			method.setParameter("c", content.trim());
			method.setParameter("p", "" + priority);

			int statusCode = client.executeMethod(method);

			if (statusCode == HttpStatus.SC_OK) {
				byte[] responseBody = method.getResponseBody();
				String ret = new String(responseBody, "UTF-8");

				if ("ok".equalsIgnoreCase(ret.trim())) {
					bret = true;
				}
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
		}
		return bret;
	}
	
	/**生成手机验证码
	 * 
	 * @param Str
	 * @return
	 */
	public static String gen4ByteCode(int length) {
		String radStr = "ABCDEFGHJKMNPQRSTUVWXYZ23456789";
		byte[] chArr = radStr.getBytes();
		StringBuffer generateRandStr = new StringBuffer();
		java.util.Random r = new java.util.Random();
		for (int i = 0; i < length; i++) {
			int randNum = r.nextInt(radStr.length());
			generateRandStr.append((char) chArr[randNum]);
		}
		return generateRandStr.toString();

	}

	/**生成手机验证码
	 * 
	 * @param Str
	 * @return
	 */
	public static String gen6ByteCode(int length) {
		String radStr = "1234567890";
		byte[] chArr = radStr.getBytes();
		StringBuffer generateRandStr = new StringBuffer();
		java.util.Random r = new java.util.Random();
		for (int i = 0; i < length; i++) {
			int randNum = r.nextInt(radStr.length());
			generateRandStr.append((char) chArr[randNum]);
		}
		return generateRandStr.toString();

	}
}
