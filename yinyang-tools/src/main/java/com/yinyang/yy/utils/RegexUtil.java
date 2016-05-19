package com.yinyang.yy.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 正则表达式工具类
 * email unicode 
 * @Title: RegexUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午3:44:06
 * @version V1.0
 */
public class RegexUtil {
	
	private static final Log log = LogFactory.getLog(RegexUtil.class);

	//private static String emailpattern = "(?im)\\b[A-Z0-9._%-]+@[A-Z0-9._%-]+\\.[A-Z]{2,4}\\b";
	private static String emailpattern = "(?im)^([a-zA-Z0-9_\\-])+(\\.([a-zA-Z0-9_\\-])+)*@((\\[(((([0-1])?([0-9])?[0-9])|(2[0-4][0-9])|(2[0-5][0-5])))\\.(((([0-1])?([0-9])?[0-9])|(2[0-4][0-9])|(2[0-5][0-5])))\\.(((([0-1])?([0-9])?[0-9])|(2[0-4][0-9])|(2[0-5][0-5])))\\.(((([0-1])?([0-9])?[0-9])|(2[0-4][0-9])|(2[0-5][0-5]))\\]))|((([a-zA-Z0-9])+(([\\-])+([a-zA-Z0-9])+)*\\.)+([a-zA-Z])+(([\\-])+([a-zA-Z0-9])+)*))$";
	private static String filter_email_pattern = "(?i)\\b[A-Z0-9._%-]+@[A-Z0-9._%-]+\\b";
	private static String filter_trim_atchar_pattern = "([\\s]*@[\\s]*)+";
	//匹配中文字符的正则表达式：
	private static String chinesepattern = "[\\u4e00-\\u9fa5]";
	//匹配双字节字符(包括汉字在内)：
	private static String unicodepattern = "[^\\x00-\\xff]";

	//身份证验证正则表达式
	//private static String idcode15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	private static String idcode15 = "^[1-9]{1}[0-9]{7}((0[1-9]{1})|(1[0-2]))(([0|1|2][0-9])|3[0-1])[0-9]{3}$";
	//private static String idcode18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])(\\d{4}|\\d{3}X)$";
	private static String idcode18 = "^[1-9]{1}[0-9]{5}[1-2]{1}[0-9]{3}((0[1-9]{1})|(1[0-2]{1}))(([0|1|2][0-9])|3[0-1])(([0-9]{4})|([0-9]{3}X))$";

	private static String idcode11 = "^[A-Za-z]{1,2}[0-9]{6}[(][0-9aA][)]+$";
	private static String idcode10 = "^[A-Za-z]{1}[1-2]{1}[0-9]{8}$|^[A-Za-z]{1,2}[0-9]{6}[(][0-9aA][)]+$";

	/**
	 *
	 */
	public RegexUtil() {
		super();
		// TODO 自动生成构造函数存根
	}

	public static boolean IsvalidEmail(String vEmailaddress, String vEmailPattern) {
		boolean FoundMatch = false;
		String emailaddress = vEmailaddress;
		String pattern = vEmailPattern;

		if (emailaddress == null) {
			return FoundMatch;
		}

		if (pattern == null || pattern.length() == 0) {
			pattern = emailpattern;
		}

		emailaddress = emailaddress.trim();

		try {
			Pattern Regex = Pattern.compile(pattern, Pattern.CANON_EQ);
			Matcher RegexMatcher = Regex.matcher(emailaddress);
			FoundMatch = RegexMatcher.find();
		} catch (PatternSyntaxException ex) {
			log.warn("Syntax error in the regular expression");
			// Syntax error in the regular expression
		}

		return FoundMatch;
	}

	public static boolean IsvalidEmail(String emailaddress) {

		return IsvalidEmail(emailaddress, emailpattern);
	}

	/**
	 *判断字符串中是否包含中文字符
	 * @param vValue
	 * @return
	 */
	public static boolean IncludeChinese(String vValue) {
		boolean FoundMatch = false;
		String pattern = chinesepattern;
		String str = vValue.trim();

		try {
			Pattern Regex = Pattern.compile(pattern, Pattern.CANON_EQ);
			Matcher RegexMatcher = Regex.matcher(str);
			FoundMatch = RegexMatcher.find();
		} catch (PatternSyntaxException ex) {
			log.warn("Syntax error in the regular expression");
			// Syntax error in the regular expression
		}

		return FoundMatch;
	}

	/**
	 *判断字符串中是否包含双字节字符
	 * @author jiangxj
	 */
	public static boolean IncludeUnicode(String vValue) {
		boolean FoundMatch = false;
		String pattern = unicodepattern;
		String str = vValue.trim();

		try {
			Pattern Regex = Pattern.compile(pattern, Pattern.CANON_EQ);
			Matcher RegexMatcher = Regex.matcher(str);
			FoundMatch = RegexMatcher.find();
		} catch (PatternSyntaxException ex) {
			log.warn("Syntax error in the regular expression");
			// Syntax error in the regular expression
		}

		return FoundMatch;

	}

	public static String FilterEmail(String sValue) {
		if (sValue == null || sValue.length() == 0) {
			return sValue;
		}

		String ResultString = "";
		try {
			sValue = TrimAtChar(sValue);
			ResultString = sValue.replaceAll(filter_email_pattern, "");
		} catch (PatternSyntaxException ex) {
			// Syntax error in the regular expression
		} catch (IllegalArgumentException ex) {
			// Syntax error in the replacement text (unescaped $ signs?)
		} catch (IndexOutOfBoundsException ex) {
			// Non-existent backreference used the replacement text
		}

		return ResultString;
	}

	/**
	 *将“  @  ”、“@@@ @@ @”替换成“@”
	 * @param sValue
	 * @return
	 */
	public static String TrimAtChar(String sValue) {
		if (sValue == null || sValue.length() == 0) {
			return sValue;
		}

		String ResultString = "";
		try {
			ResultString = sValue.replaceAll(filter_trim_atchar_pattern, "@");
		} catch (PatternSyntaxException ex) {
			// Syntax error in the regular expression
		} catch (IllegalArgumentException ex) {
			// Syntax error in the replacement text (unescaped $ signs?)
		} catch (IndexOutOfBoundsException ex) {
			// Non-existent backreference used the replacement text
		}

		return ResultString;
	}

	public static String ReplaceChineseInterpunction(String sValue) {
		if (sValue == null || sValue.length() == 0) {
			return sValue;
		}

		String ResultString = sValue;

		ResultString = ResultString.replaceAll("～", "~");
		ResultString = ResultString.replaceAll("！", "!");
		ResultString = ResultString.replaceAll("＠", "@");
		ResultString = ResultString.replaceAll("＃", "#");
		ResultString = ResultString.replaceAll("￥", "rmb");
		ResultString = ResultString.replaceAll("％", "%");
		ResultString = ResultString.replaceAll("＾", "^");
		ResultString = ResultString.replaceAll("＆", "&");
		ResultString = ResultString.replaceAll("×", "*");
		ResultString = ResultString.replaceAll("（", "(");
		ResultString = ResultString.replaceAll("）", ")");
		ResultString = ResultString.replaceAll("……", "...");
		ResultString = ResultString.replaceAll("＋", "+");
		ResultString = ResultString.replaceAll("｜", "|");
		ResultString = ResultString.replaceAll("｛", "{");
		ResultString = ResultString.replaceAll("｝", "}");
		ResultString = ResultString.replaceAll("：", ":");
		ResultString = ResultString.replaceAll("”", "\"");
		ResultString = ResultString.replaceAll("《", "<");
		ResultString = ResultString.replaceAll("》", ">");
		ResultString = ResultString.replaceAll("？", "?");
		ResultString = ResultString.replaceAll("，", ",");
		ResultString = ResultString.replaceAll("。", ".");
		ResultString = ResultString.replaceAll("、", ",");
		ResultString = ResultString.replaceAll("；", ";");
		ResultString = ResultString.replaceAll("’", "'");
		ResultString = ResultString.replaceAll("〔", "[");
		ResultString = ResultString.replaceAll("〕", "]");
		ResultString = ResultString.replaceAll("＝", "=");
		ResultString = ResultString.replaceAll("－", "-");
		ResultString = ResultString.replaceAll("★", "*");
		ResultString = ResultString.replaceAll("【", "[");
		ResultString = ResultString.replaceAll("】", "]");
		ResultString = ResultString.replaceAll("〈", "<");
		ResultString = ResultString.replaceAll("〉", ">");

		return ResultString;
	}

	public boolean isValidIdCard(String idCard) {
		boolean ret = false;
		if (idCard == null || "".equals(idCard.trim()))
			return ret;

		//idCard = " D385468(0)";

		//    	Pattern Regex = Pattern.compile(pattern,Pattern.CANON_EQ);
		//    	Matcher RegexMatcher = Regex.matcher( emailaddress );
		//    	FoundMatch = RegexMatcher.find();

		idCard = idCard.toUpperCase();
		// 身份证验证长度验证
		int sLength = idCard.length();
		if (sLength == 18 || sLength == 15 || sLength == 11 || sLength == 10) {
			if (sLength == 15 || sLength == 18) {
				//中国大陆身份证验证    	  
				//身份证地区编号
				//            	CityList = { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
				//            				21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
				//            				33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",
				//            				41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",
				//            				46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",
				//            				54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",
				//            				65:" 新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}; 	
				//如湖北武汉市江岸区1975-12-31生辰男：15位- 420102 751231 215 ；18位 - 420102 19751231 2115 或 420102 19751231 211x 
				String[] provinceId = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36:", "37", "41",
						"42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91" };
				String[] provinceName = { "北京", "天津", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "上海", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南",
						"湖北", "湖南", "广东", "广西", "海南", "重庆", "四川", "贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", " 新疆", "台湾", "香港", "澳门", "国外" };
				Map<String, String> provinceMap = new HashMap<String, String>();
				for (int i = 0; i < provinceId.length; i++) {
					provinceMap.put(provinceId[i], provinceName[i]);
				}
				;

				String strBirthday = null;
				if (sLength == 15) {
					//String isIDCard15 = "/^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$/";
					Pattern Regex = Pattern.compile(idcode15, Pattern.CANON_EQ);
					Matcher RegexMatcher = Regex.matcher(idCard);
					ret = RegexMatcher.find();
					if (ret) {
						strBirthday = "19" + idCard.substring(6, 8) + "-" + idCard.substring(8, 10) + "-" + idCard.substring(10, 12);
					}
				} else {
					//Pattern Regex = Pattern.compile(idcode18,Pattern.CANON_EQ);
					//Matcher RegexMatcher = Regex.matcher( idCard );
					//ret = RegexMatcher.find();
					Pattern p1 = Pattern.compile(idcode18);//Pattern.CASE_INSENSITIVE忽略大小写
					Matcher m1 = p1.matcher(idCard);
					ret = m1.matches();

					if (ret) {
						strBirthday = idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14);
					}
				}

				if (strBirthday != null) {
					//生日校验
					Date dBirthday = null;
					DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
					try {
						dBirthday = format1.parse(strBirthday);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						dBirthday = null;
					}
					if (dBirthday != null) {

						//出生日期在1939年到2000年之间。	  
						try {
							Date dBirthday1939 = format1.parse("1939-01-01");
							Date dBirthday1999 = format1.parse("1999-01-01");

							if (dBirthday.after(dBirthday1939) && dBirthday.before(dBirthday1999)) {
								ret = true;
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}

				//地区校验  
			} else if (sLength == 10 || sLength == 11) {

				//香港的身份证 
				//身份证号码的结构，可以用XYabcdef(z)表示。「X」可能是「空格」或是一个英文字母，「Y」则必定是英文字母。
				//「abcdef」代表一个六位数字，而「z」是作为检码之用，它的可能选择是0,1,2,...,9,A(代表10)。
				if (sLength == 11) {
					Pattern Regex = Pattern.compile(idcode11, Pattern.CANON_EQ);
					Matcher RegexMatcher = Regex.matcher(idCard);
					ret = RegexMatcher.find();
				} else {
					//1.	台湾身份证总共有10位数字。第一位是字母。后面九位是数字。 台湾省份证的第一位的字母代表地区分别以A——Z表示
					//2.	第二位数字代表性别 男性是1，女性是2
					//3.	第三位到第九位为任意的一串数字
					//4.	第十位为验证码。 第十位数字
					Pattern Regex = Pattern.compile(idcode10, Pattern.CANON_EQ);
					Matcher RegexMatcher = Regex.matcher(idCard);
					ret = RegexMatcher.find();
				}
				//ret =true;

			}
		}
		return ret;
	}
}
