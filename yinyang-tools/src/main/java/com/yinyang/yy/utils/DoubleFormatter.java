package com.yinyang.yy.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Double格式化
 * 
 * @Title: DoubleFormatter.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午2:57:11
 * @version V1.0
 */
public class DoubleFormatter {

	private static final Log log = LogFactory.getLog(DoubleFormatter.class);
	public static final String PERCENTAGE = "#.##%";

	/**
	 * 
	 * @Description: 将Double类型的值以百分比形式返回
	 *
	 * @param target
	 * @param format
	 * @return Double
	 * @create time 2013-5-31 下午1:58:13
	 */
	public static String formatDouble(Double target, String format) {
		if (target == null) {
			return null;
		}
		String rs = "";
		DecimalFormat df = new DecimalFormat(",##0.00");

		if (PERCENTAGE.equals(format)) {
			target = mul(target, 100L);
			/*
			 * if (target.doubleValue()== 0.0) { rs = "0.00%"; return rs; }
			 */

			rs = df.format(target) + "%";
			return rs;
		} else {
			rs = df.format(target);
			return rs;
		}

	}

	public static String formatDouble(String targetObj, String format) {

		if (targetObj == null || "".equals(targetObj)) {
			return null;
		}

		Double target = Double.valueOf(targetObj);

		String rs = "";
		DecimalFormat df = new DecimalFormat(",##0.00");

		if (PERCENTAGE.equals(format)) {
			target = mul(target, 100L);

			rs = df.format(target) + "%";
			return rs;
		} else {
			rs = df.format(target);
			return rs;
		}

	}

	public static String formatLong(Long target) {
		if (target == null) {
			return null;
		}
		String rs = "";
		DecimalFormat df = new DecimalFormat(",###");

		rs = df.format(target);
		return rs;

	}

	public static String formatLong(String targetObj, String format) {

		if (targetObj == null || "".equals(targetObj)) {
			return null;
		}

		Double target = Double.valueOf(targetObj);

		String rs = "";
		DecimalFormat df = new DecimalFormat(",##0");

		rs = df.format(target);
		return rs;

	}

	public static double mul(Double d1, Long d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).doubleValue();
	}

	public static double div(double d1, double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		if (d2 == 0) {
			throw new IllegalArgumentException("The d2 must be not zero");
		}
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static Double divExt(Double d1, Double d2, int scale) {
		if (d1 == null || d2 == null) {
			return null;
		}
		if (d2.intValue() == 0) {
			return null;
		}
		return div(d1, d2, scale);
	}

	public static void main(String[] args) {
		String l = formatLong(100225141L);
		log.info(l);
	}

}
