package com.yinyang.yy.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * java浮点精度计算工具类
 * 
 * @Title: MathUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午2:39:53
 * @version V1.0
 */
@SuppressWarnings("static-access")
public class MathUtil {

	public static double round(double value, int scale, int roundingMode) {

		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
		bd = null;
		return d;
	}

	public static double sum(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).doubleValue();
	}

	public static double sub(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.subtract(bd2).doubleValue();
	}

	public static double mul(double d1, double d2) {
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

	public static String compare(Double d1,Double d2){
		if(d1==null || d2==null){
			return "--";
		}
		if(d1>d2){
			return "1";
		}else if(d1.doubleValue()==d2.doubleValue()){
			return "0";
		}else if(d1<d2){
			return "-1";
		}
		return null;
	}

	public static Double divExt(Double d1, Double d2, int scale) {
		if (d1 == null || d2 == null) {
			return 0D;
		}
		if (d2 == 0.0) {
			return 0D;
		}
		return div(d1, d2, scale);
	}

	public static Double divExt(Long d1, Long d2, int scale) {
		if (d1 == null || d2 == null) {
			return null;
		}
		if (d2.intValue() == 0) {
			return null;
		}
		return div(Double.valueOf(d1), Double.valueOf(d2), scale);
	}

	public static Double abs(Double a){
		return Math.abs(a);
	}
	public static void main(String[] arg) {
		MathUtil.getCeilHalf(0.0);
		MathUtil cal = new MathUtil();
		double a = 0.005;
		double b = 0.11;
		double c = a + b;

		double d = cal.sum(a, b);

		System.out.println(c);
		System.out.println(d);

		DecimalFormat df = new DecimalFormat("#0.000");
		DecimalFormat df2 = new DecimalFormat("#0.00");

		for (int i = 0; i < 1; i++) {

			double a1 = Double.valueOf(df.format(Math.random() * 10 + Math.random()));
			double a2 = Double.valueOf(df2.format(Math.random() * 10 + Math.random()));

			System.out.println("AA1" + String.valueOf(a1));
			System.out.println("A2:" + String.valueOf(a2));
			System.out.println("AAaa3" + String.valueOf(cal.sum(a1, a2)));
			System.out.println("AAaa3_mul" + String.valueOf(cal.mul(a1, a2)));
		}
	}

	/**
	 * 精确到小数点后两位 四舍五入
	 */
	public static double getFormatScale2(double value) {
		value = value * 100;
		value = Math.round(value);
		value = value / 100;

		return value;
	}

	/**
	 * 向上取整，不足0.5按0.5计算，超过0.5按1计算
	 * */
	public static double getCeilHalf(double value) {
		//value = 7.00000;
		double result = 0;
		//System.out.println("--:"+value);
		double l_value = Math.floor(value);
		double decimal = value - l_value;
		if (decimal > 0 && decimal <= 0.5) {
			result = l_value + 0.5;
		} else if (decimal > 0.5) {
			result = l_value + 1;
		} else {
			result = l_value;
		}
		//System.out.println("--:"+result);	
		return result;
	}

}
