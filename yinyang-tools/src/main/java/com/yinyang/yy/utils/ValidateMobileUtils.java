package com.yinyang.yy.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 手机号码验证工具类
 * 
 * @Title: ValidateMobileUtils.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午3:29:44
 * @version V1.0
 */
public class ValidateMobileUtils {

	static Pattern p = Pattern.compile("^[0-9]{11}$");
	static Pattern p1 = Pattern.compile("^[0-9]{8}$");
	static Pattern p2 = Pattern.compile("^[0-9]*$");

	public static boolean checkMobile(String mobile) {
		if (checkCNMobile(mobile)) {
			return true;
		} else {
			return checkHKMobile(mobile);
		}
	}

	private static boolean checkCNMobile(String mobile) {
		Matcher match = p.matcher(mobile);
		return match.find();
	}

	private static boolean checkHKMobile(String mobile) {
		Matcher match = p1.matcher(mobile);
		return match.find();
	}
	
	public static boolean checkGlobalMobile(String mobile) {
		Matcher match = p2.matcher(mobile);
		return match.find();
	}

	public static boolean isMobileNO(String mobiles) {
		if (StringUtils.isBlank(mobiles)) {
			return false;
		}
		if (mobiles.length() == 8) {
			Pattern p = Pattern.compile("^[0-9]{8}$");
			Matcher m = p.matcher(mobiles);
			return m.matches();

		} else if (mobiles.length() == 11) {
			Pattern p = Pattern.compile("^((13[0-9])|(1[478][0-9])|(15[^4,\\D]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			return m.matches();
		} else {
			return false;
		}

	}
}
