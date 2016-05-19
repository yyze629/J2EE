package com.yinyang.yy.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 
 * @Title: Md5PassWord.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午3:42:19
 * @version V1.0
 */
public class Md5PassWord {

	private static final Log log = LogFactory.getLog(Md5PassWord.class);

	// 第一次加密用key
	private final static String MD5_PASSWORD_1 = "IUIgGZSsca";
	// 第二次加密用key
	private final static String MD5_PASSWORD_2 = "uYVfGPTKn7";

	public static String getMd5PassWord(String password) {

		try {
			String md = MD5Utils.getMd5(password + MD5_PASSWORD_1);
			md = MD5Utils.getMd5(md + MD5_PASSWORD_2);
			log.debug(md);
			return md;
		} catch (Exception e) {
			return null;
		}
	}

	public static String getMd5PassWordP1(String password) {

		try {
			String md = MD5Utils.getMd5(password + MD5_PASSWORD_2);
			log.debug(md);
			return md;
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String arg[]) throws Exception {
		System.out.print(Md5PassWord.getMd5PassWord("123321"));
		// System.out.println(Md5.getMd5("111111" + MD5_PASSWORD_1));
	}

}
