package com.yinyang.yy.superutils.thirdparty.mail;

import java.util.HashMap;
import java.util.Map;

/**
 * 常用的SMTP服务器
 * 
 * @author gaofei
 * 
 */
@SuppressWarnings("serial")
public class SmtpServer {

	public static Map<String, String> smtp_163 = new HashMap<String, String>() {
		{

			put("smtp", "smtp.163.com");
			put("port", "25");

		}
	};

	public static Map<String, String> smtp_sina = new HashMap<String, String>() {
		{

			put("smtp", "smtp.sina.com");
			put("port", "25");

		}
	};

	public static Map<String, String> smtp_sohu = new HashMap<String, String>() {
		{

			put("smtp", "smtp.sohu.com");
			put("port", "25");

		}
	};

	public static Map<String, String> smtp_126 = new HashMap<String, String>() {
		{

			put("smtp", "smtp.126.com");
			put("port", "25");

		}
	};

	public static Map<String, String> smtp_yahoo = new HashMap<String, String>() {
		{

			put("smtp", "smtp.mail.yahoo.com");
			put("port", "25");

		}
	};

	public static Map<String, String> smtp_foxmail = new HashMap<String, String>() {
		{

			put("smtp", "smtp.foxmail.com");
			put("port", "25");

		}
	};

}
