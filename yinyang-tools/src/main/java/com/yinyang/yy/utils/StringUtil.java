package com.yinyang.yy.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * String工具类
 * 
 * @Title: StringUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午2:58:50
 * @version V1.0
 */
@SuppressWarnings({"rawtypes","unchecked","deprecation","unused"})
public class StringUtil {

	protected final static Log log = LogFactory.getLog(StringUtil.class);
	
	public static final String REGEX_SPACE = " ";
	public static final Integer WORD_NUMBER = 3;
	public static final Integer WORD_LENGTH = 50;

	public static String getString(Double d) {
		if (d == null) {
			return "0";
		}

		return String.valueOf(d);
	}

	public static String getString(Long d) {
		if (d == null) {
			return "0";
		}

		return String.valueOf(d);
	}

	public static String reverse(String s) {
		if (s == null || "".equals(s)) {
			return s;
		}

		int length = s.length();
		String reverse = "";
		for (int i = 0; i < length; i++)
			reverse = s.charAt(i) + reverse;
		return reverse;
	}

	public static String subString(String src, String remove) {
		src = src.replaceAll(remove, "");
		return src;
	}

	public static String trim(String s) {
		if (s == null || "".equals(s)) {
			return s;
		}

		return s.trim();
	}

	/**
	 * 
	 * @Description: 替换指定的字符为空
	 *
	 * @param original
	 * @param beRaplaced
	 * @return String
	 * @create time 2013-8-2 上午11:21:41
	 */
	public static String replaceAll(String original, String beRaplaced) {
		if (original != null && !"".equals(original)) {
			if (original.indexOf(beRaplaced) != -1) {
				original = original.replaceAll(",", "");
			}
		}
		return original;
	}

	/*public static void main(String[] args) {
		Long d = (Long) StringUtil.convert("23423,34234", 2);
		System.out.println(d);
	}*/

	/**
	 * 
	 * @Description: 将"45.3%"类型的字符串转换成45.3
	 *
	 * @param str
	 * @return Double
	 * @create time 2013-10-16 下午4:02:26
	 */
	public static Double convert(String str) {
		Double d = 0D;
		if (str != null && !"".equals(str)) {
			if (str.indexOf("%") != -1) {
				str = str.replaceAll("%", "");
			}
			if (str.indexOf(",") != -1) {
				str = str.replaceAll(",", "");
			}

			if (str != null) {
				d = Double.valueOf(str);
			}
		}

		return d;
	}

	public static Object convert(String str, Integer returnType) {
		if (returnType != null) {
			if (str != null && !"".equals(str)) {
				if (str.indexOf("%") != -1) {
					str = str.replaceAll("%", "");
				}
				if (str.indexOf(",") != -1) {
					str = str.replaceAll(",", "");
				}
			}

			if (returnType == 1) {// 1表示返回的是整形
				Integer d = 0;
				if (str != null) {
					d = Integer.valueOf(str);
				}
				return d;
			} else if (returnType == 2) {// 2表示返回的是Long
				Long d = 0L;
				if (str != null) {
					d = Long.valueOf(str);
				}
				return d;
			} else {
				return str;
			}
		} else {
			return str;
		}
	}

	public static boolean compareStrSplitByComma(String s, String s1) {
		if (s == null || s1 == null)
			return false;
		String as[] = s.split(",");
		String as1[] = s1.split(",");
		HashMap hashmap = new HashMap();
		if (as.length != as1.length)
			return false;
		for (int i = 0; i < as1.length; i++)
			hashmap.put(as1[i], as1[i]);

		for (int j = 0; j < as.length; j++)
			if (!hashmap.containsKey(as[j]))
				return false;

		return true;
	}

	public static String doubleToCurrency(double d) {
		Object aobj[] = { new Double(d) };
		return MessageFormat.format("{0,number,\uFFE5,#,###,###,###,###,###,##0.00}", aobj);
	}

	public static String encodeString(String s, String s1, String s2) {
		if (s == null || s1 == null || s2 == null)
			return null;
		String s3 = null;
		try {
			s3 = new String(s.getBytes(s1), s2);
		} catch (UnsupportedEncodingException unsupportedencodingexception) {
			s3 = s;
		}
		return s3;
	}

	public static boolean isEmptyString(String s) {
		return s == null || s.trim().length() < 1 || s.trim().equalsIgnoreCase("null");
	}

	public static boolean isEmptyByLength(String s) {
		return s == null || s.trim().length() < 1 || s.trim().equalsIgnoreCase("null");
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() >= 1 && !s.trim().equalsIgnoreCase("null");
	}

	public static boolean isNumber(String s) {
		if (isEmptyString(s))
			return false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c < '0' || c > '9')
				return false;
		}

		return true;
	}

	public static String[] getNumStringArray(String s) {
		if (s == null)
			return null;
		ArrayList arraylist = new ArrayList();
		Pattern pattern = Pattern.compile("([0-9])+");
		for (Matcher matcher = pattern.matcher(s); matcher.find(); arraylist.add(matcher.group()))
			;
		return (String[]) arraylist.toArray(new String[0]);
	}

	public static int stringToInt(String s) {
		return stringToInt(s, -1);
	}

	public static int stringToInt(String s, int i) {
		int j = i;
		if (s != null)
			try {
				j = Integer.parseInt(s);
			} catch (NumberFormatException numberformatexception) {
				j = i;
			}
		return j;
	}

	public static String toGBKString(String s) {
		return encodeString(s, "ISO8859_1", "GBK");
	}

	/**
	 * 
	 * 空格分词，每个单词首字母大写
	 * 
	 * @param key
	 * @return
	 */
	public static String capitalizeFirstLetter(String word) {
		if (isNotEmpty(word)) {
			String[] keys = word.split(" ");
			StringBuffer buffer = new StringBuffer();
			for (String value : keys) {
				if (value.length() > 0) {
					StringBuffer sbuffer = new StringBuffer();
					char c = value.charAt(0);
					sbuffer.append(Character.toUpperCase(c));
					if (value.length() > 1) {
						sbuffer.append(value.substring(1));
					}
					buffer.append(sbuffer.toString());
				}
				buffer.append(" ");
			}
			return buffer.toString().trim();
		} else {
			return word;
		}
	}

	/**
	 * 生成一个uuid的hashcode
	 * 
	 * @return
	 */
	public static String uuidHashCode() {
		UUID uuid = UUID.randomUUID();
		Long uu = Long.parseLong(String.valueOf(uuid.hashCode())) + Long.parseLong(String.valueOf(Integer.MAX_VALUE));

		return uu.toString();
	}

	public static String lowerCaseWord(String word) {
		if (StringUtils.isEmpty(word)) {
			return word;
		} else {
			return word.toLowerCase();
		}
	}

	/**
	 * @desc 判断是否存在非字母和数字
	 * @author yuanchunzhao
	 * @date 2014-07-08
	 */
	public static boolean haveNotCharacter(String str) {
		if (isEmpty(str)) {
			return true;
		}
		str = str.replaceAll(REGEX_SPACE, "");
		char[] cKey = str.trim().toLowerCase().toCharArray();
		for (char c : cKey) {
			if (!((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @desc 将字符串转换为List，指定regex
	 * @author yuanchunzhao
	 * @date 2014-07-08
	 */
	public static List<String> stringToList(String str, String regex) {
		List<String> result = new ArrayList<String>();
		if (isEmpty(str)) {
			return result;
		}
		String[] arrs = str.split(regex);
		if (arrs == null || arrs.length == 0) {
			return result;
		}
		for (String word : arrs) {
			if (isEmpty(word)) {
				continue;
			}
			result.add(word);
		}
		return result;
	}

	/**
	 * @desc 将list装换为String，指定regex
	 * @author yuanchunzhao
	 * @date 2014-07-08
	 */
	public static String listToString(List<String> list, String regex) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			if (isNotEmpty(str.trim())) {
				sb.append(str.trim()).append(regex);
			}
		}
		int end = sb.toString().lastIndexOf(regex);
		return sb.toString().substring(0, end).trim();
	}

	public final static String encodeShowroomKeyword(String keyword) {
		if (keyword == null)
			return "";
		String s = keyword;
		try {
			// log.info(" encodeShowroomKeyword start : "+keyword);
			// String s = keyword.replaceAll(regEx, " ");
			s = keyword.replaceAll("\\s+", " ");
			s = URLEncoder.encode(s);
			// s = s.replaceAll("%","%25");

			// s = s.replaceAll("\"","%2522");
			// log.info(" encodeShowroomKeyword   end : "+s);
		} catch (Exception e) {
		}
		return s;
	}

	public final static String decodeShowroomKeyword(String keyword) {
		if (keyword == null)
			return "";
		String s = keyword;
		try {
			// log.info(" decodeShowroomKeyword start : "+keyword);
			// String s = keyword.replaceAll("[\\+]+",DELIMITER_SHOWROOM_SPACE);
			// String s = keyword.replaceAll("%25","%");
			s = URLDecoder.decode(keyword);

			// s=s.replaceAll("%2522","\"");
			// log.info(" decodeShowroomKeyword   end : "+s);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return s;
	}
	
	/** TODO add  from merchant web*/
	private final static String regEx = "[^\\w%\\(\\)\\^\\|\\+-]+"; //去非数字字母
	private final static String DELIMITER = "-";
	private final static int KEYWORD_LENGTH_IN_URL = 40; //url总长度控制在80个字符左右
	
	private static SecureRandom sr;
	static {
		sr = new SecureRandom();
	}

	/**
	 * 根据指定长度，获取随机字符串
	 * @param len
	 * @return
	 */
	public static String getUniqueID(int len) {
		if (len < 1)
			len = 10;
		final char[] alphabet = "12345abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ67890".toCharArray();
		byte[] b = new byte[len];
		sr.nextBytes(b);
		char[] out = new char[len];
		for (int i = 0; i < len; i++) {
			int index = b[i] % alphabet.length;
			if (index < 0)
				index += alphabet.length;
			out[i] = alphabet[index];
		}
		return new String(out);
	}

	public static String getRandomNum(int len) {
		if (len < 1)
			len = 10;
		len = len + 1;
		final char[] alphabet = "123457809".toCharArray();
		byte[] b = new byte[len];
		sr.nextBytes(b);
		char[] out = new char[len];
		for (int i = 0; i < len; i++) {
			int index = b[i] % alphabet.length;
			if (index < 0)
				index += alphabet.length;
			out[i] = alphabet[index];
		}
		return new String(out).substring(1);
	}

	/**
	 * 返回UUID，这是一个24位的UUID,使用jdk的uuid生成
	 * @return 24 bit length uuid 
	 * @author sunbaoming
	 */
	public static String getUniqueID() {
		UUID u = UUID.randomUUID();
		long mostSigBits = u.getMostSignificantBits();
		long leastSigBits = u.getLeastSignificantBits();
		return (digits(mostSigBits >> 32, 8) + digits(mostSigBits >> 16, 4) + digits(mostSigBits, 4) + digits(leastSigBits >> 48, 4) + digits(
				leastSigBits, 12));

	}

	/**
	 * 去掉html文件换行符
	 * @param sHtmlContent html内容
	 * @return 去掉了\r\n并把'替换成`
	 */
	public static String removeSpecialChar(String sHtmlContent) {
		sHtmlContent = sHtmlContent.replaceAll("\n", "");
		sHtmlContent = sHtmlContent.replaceAll("\r", "");
		sHtmlContent = sHtmlContent.replaceAll("'", "`");
		return sHtmlContent;
	}

	/**
	 * 返回文件名最后.以后的扩展名
	 * @param filename 文件名
	 * @return 扩展名
	 */
	public static String getFilenameExt(String filename) {
		if (filename == null)
			return "";

		int idx = filename.lastIndexOf(".");
		if (idx > 0) {
			return filename.substring(idx + 1);
		} else {
			return "";
		}
	}

	/**
	 * 浮点型判断
	 * @param str
	 * @return
	 */
	public static boolean isDecimal(String str) {
		if (str == null || "".equals(str.trim()))
			return false;

		Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为正整数型
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (str == null || "".equals(str.trim()))
			return false;

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/***
	 * 文字中替换字符串，忽略大小写
	 *@param stringname
	 *@param oldchar
	 *@param newchar
	 *@author leidengyan
	 */
	public static String replaceIgnorecase(String stringname, String oldchar, String newchar) {
		if (stringname == null || "".equalsIgnoreCase(stringname))
			return stringname;
		Pattern old = Pattern.compile(oldchar, Pattern.CASE_INSENSITIVE);
		Matcher newString = old.matcher(stringname);
		String str = newString.replaceAll(newchar);
		return str;
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(StringUtil.isDecimal(""));
		// System.out.println(StringUtil.isDecimal(null));
		// System.out.println(StringUtil.isDecimal("abc"));
		// System.out.println(StringUtil.isDecimal("123abc"));
		// System.out.println(StringUtil.isDecimal("abc123"));
		// System.out.println(StringUtil.isDecimal("123.2.2"));
		// System.out.println(StringUtil.isDecimal("123.2"));
		// System.out.println(StringUtil.isDecimal("0.2"));
		//        String id = getRandomNum(10);
		//        System.out.println(id);
		//    	System.out.println("中国 + " + checkEnglish("中国"));
		//    	System.out.println("213！ + " + checkEnglish("213！"));
		//    	System.out.println("2中3！ + " + checkEnglish("2中3！"));
		//    	System.out.println("2＠3！ + " + checkEnglish("2＠3！"));
		//    	System.out.println("213jfslfjlsdfjls + " + checkEnglish("213jfslfjlsdfjls"));
		
	}

	public static boolean isInnerList(String string, String[] strs) {
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			if (string.equals(str)) {
				return true;
			}

		}
		return false;
	}

	/**
	 * 替换字符串里的全角字符为半角字符 src.replace("/[^\uFF00-\uFFFF]$/g", "");
	 * @param src
	 * @return
	 */
	public static String toSemiangle(String src) {
		if (src == null)
			return null;

		StringBuffer outStr = new StringBuffer();
		String tempStr = "";
		byte[] b = null;
		int len = src.length();
		for (int i = 0; i < len; i++) {
			try {
				tempStr = src.substring(i, i + 1);
				if (tempStr.equals("　")) {
					tempStr = " ";
				}
				b = tempStr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e) {
				log.error(e);
			}
			if (b[3] == -1) {
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;
				try {
					outStr.append(new String(b, "unicode"));
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else {
				outStr.append(tempStr);
			}
		}

		return outStr.toString();
	}

	/**字符串不为空(null或者空白字符)返回true,否则false
	* 
	* @param Str
	* @return
	*/
	public static boolean notEmpty(String str) {
		if (str != null && str.trim().length() > 0)
			return true;
		else
			return false;
	}

	/**字符串为空(null或者空白字符)返回true,否则false
	 * 
	 * @param Str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return !notEmpty(str);
	}

	/**
	 * 判断是否在数组中
	 * 1: 在
	 * 0: 不在
	 * @param object
	 * @param objects
	 * @return
	 */
	public static boolean isInArray(Object object, Object[] objects) {
		for (int i = 0; i < objects.length; i++) {
			if (object.equals(objects[i])) {
				return true;
			}
		}
		return false;
	}

	public static String htmlToString(String src) {
		return src;
		/*String htmlStr = src; //含html标签的字符串
		String textStr ="";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
			String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
		
			p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); //过滤script标签

			p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); //过滤style标签
		
			p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); //过滤html标签
		
			textStr = htmlStr;
			//(?<email>(?![ ])(\w|[.])*@(\w|[.])*)
		
		}catch(Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}  
		
		return textStr;//返回文本字符串
		*/

	}

	/***
	 * 判断字符串是否不为空
	 * 在StringUtils类不为空条件的基础上还加了string不能为"null"
	 * @author leidengyan
	 * @since 2010-3-25
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		boolean is = false;
		if (StringUtils.isNotBlank(str) && !"null".equals(str)) {
			is = true;
		}
		return is;
	}

	private static String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		return Long.toString(hi | (val & (hi - 1)), 36).substring(1);
	}

	/***
	 * 过滤回复信息，协议备注中的html代码
	 * @param val
	 * @return
	 */
	public static String replaceHtml(String val) {
		if (StringUtils.isBlank(val))
			return val;
		val = val.replaceAll("<", "&lt;");
		val = val.replaceAll(">", "&gt;");
		val.replaceAll("'", "‘");
		return val;
	}

	/**
	 * 返回字符串的后 指定位 子串
	 * @param s
	 * @param size
	 * @return
	 */
	public static String subLastString(String s, int size) {
		if (s.length() <= size)
			return s;
		return s.substring(s.length() - 3, s.length());
	}

	private static Pattern PLT = Pattern.compile("<");
	private static Pattern PRT = Pattern.compile(">");

	private static Pattern PL_KUA = Pattern.compile("\\(");
	private static Pattern PR_KUA = Pattern.compile("\\)");

	private static Pattern PR_QUOT = Pattern.compile("\"");

	private static Pattern PR_EVAL = Pattern.compile("eval\\((.*)\\)");
	private static Pattern PR_JAVASCRIPT = Pattern.compile("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']");
	private static Pattern PR_SCRIPT = Pattern.compile("script");

	/**
	 * 转义xss注入使用的串 替换 > < " & ' /
	 * @param s
	 * @return
	 */
	public static String escapeXss(String s) {
		if (s == null)
			return null;
		StringBuffer buf = new StringBuffer(s.length() * 2);

		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);

			switch (ch) {
			case '>':
				buf.append("&gt;");
				break;
			case '<':
				buf.append("&lt;");
				break;
			case '"':
				buf.append("&quot;");
				break;
			case '&':
				buf.append("&amp;");
				break;
			case '\'':
				buf.append("&#x27;");
				break;
			case '/':
				buf.append("&#x2F;");
				break;
			default:
				buf.append(ch);
				break;
			}
		}

		String n = buf.toString();
		return n;
	}

	/**
	 * 转义xss注入使用的串,只替换 > <
	 * @param s 原串
	 * @return　转义后的串
	 */
	public static String escapeXssLittle(String s) {
		if (s == null)
			return null;
		StringBuffer buf = new StringBuffer(s.length() * 2);

		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);

			switch (ch) {
			case '>':
				buf.append("&gt;");
				break;
			case '<':
				buf.append("&lt;");
				break;

			default:
				buf.append(ch);
				break;
			}
		}
		return buf.toString();

	}

	/**
	 * 转义xss注入使用的串,只替换 > <
	 * @param s 原串
	 * @return　转义后的串
	 */
	public static String escapeXssLittleWithBracket(String s) {
		if (s == null)
			return null;
		StringBuffer buf = new StringBuffer(s.length() * 2);

		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);

			switch (ch) {
			case '>':
				buf.append("&gt;");
				break;
			case '<':
				buf.append("&lt;");
				break;
			case '(':
				buf.append("&#40;");
				break;
			case ')':
				buf.append("&#41;");
				break;
			default:
				buf.append(ch);
				break;
			}
		}
		return buf.toString();

	}

	/**
	 * 转义xss注入使用的串 替换 > < " & ' / script javascript eval
	 * @param s 原串
	 * @return　转义后的串
	 */
	public static String escapeXssMore(String s) {
		if (s == null)
			return null;
		StringBuffer buf = new StringBuffer(s.length() * 2);

		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);

			switch (ch) {
			case '>':
				buf.append("&gt;");
				break;
			case '<':
				buf.append("&lt;");
				break;
			case '"':
				buf.append("&quot;");
				break;
			case '&':
				buf.append("&amp;");
				break;
			case '\'':
				buf.append("&#x27;");
				break;
			case '/':
				buf.append("&#x2F;");
				break;
			default:
				buf.append(ch);
				break;
			}
		}

		String n = buf.toString();
		//s = s.replaceAll("eval\\((.*)\\)", "");
		n = PR_EVAL.matcher(n).replaceAll("");
		//s = s.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");        
		n = PR_JAVASCRIPT.matcher(n).replaceAll("\"\"");
		//script -> ""
		n = PR_SCRIPT.matcher(n).replaceAll("");

		return n;
	}

	/**
	 * 转义xss注入使用的串
	 * @param s 原串
	 * @return　转义后的串
	 */
	public static String escapeXssAll(String s) {
		return escapeXss(s);
	}

	/**
	 * 防SQL注入的函数，对于要直接拼串的变量先执行这个方法以返回正确的转义字符串
	 * 这个版本针对oracle
	 * see {@link http://oraqa.com/2006/03/20/how-to-escape-single-quotes-in-strings/}
	 * see {@link https://www.owasp.org/index.php/SQL_Injection_Prevention_Cheat_Sheet#Oracle_Escaping}
	 * @param s 要转义的字符串，如从request.getParameter("xx")取得的用户输入串
	 * @return 转义后的安全的字符串
	 */
	public static String escapeSQL(String s) {
		if (s == null)
			return s;
		StringBuffer sb = new StringBuffer(s.length() * 2);

		for (char c : s.toCharArray()) {

			if (c == '\'') {
				sb.append("\'\'");
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 判断一个字符串中是否包含不希望出现的特殊字符.只要src中出现在badchar中的任何一个字符,就会返回true
	 * @param src 要判断的原字符串 
	 * @param badchar 要过滤和判断的非法字符串，如"#@!^%'"
	 * @return 如果包含至少一个不希望出来的字符，返回true
	 */
	public static boolean ifHasChar(String src, String badchar) {
		if (badchar == null)
			return false;

		int length = badchar.length();

		//一个一个字符地找，只要找到一个，就退出
		for (int i = 0; i < length; i++) {
			char c = badchar.charAt(i);
			if (src.indexOf(c) > -1) {
				return true;
			}
		}

		//循环都没有找到，直接返回false
		return false;
	}

	/**
	 * 判断并过滤returnURL,只允许.dhgate.com的跳转，其它不合法地址，统一返回/
	 * @param s returnURL
	 * @return 处理过的returnURL
	 */
	public static String trimReturnURL(String s) {

		if (s == null || "".equals(s))
			return s;

		String a = "/";

		//说明是相对地址，直接返回
		if (s.startsWith("/")) {
			return s;
		}

		if (s.startsWith("http")) {

			//这一步是为了能够对于encode过的URL进行处理，先解出来再取host
			String ds = s;
			try {
				ds = URLDecoder.decode(s, "UTF-8");
			} catch (Exception e) {
				//ignore 
			}

			try {
				URL u = new URL(ds);
				String host = u.getHost();

				if (host != null) {
					//如果带有host，那么包括.dhgate.com就返回正常的URL，否则说可能是钓鱼，返回/
					//这里有点彩蛋,目前联盟站的域名里都包括wholesale
					//wholesale-outlets.com global-wholesale.com wholesalefromhere.com wholesaleshenzhen.com等
					if (host.endsWith(".dhgate.com") || host.indexOf("wholesale") > 0) {
						return s;
					}
				}

			} catch (MalformedURLException e) {
			}
		}

		return a;
	}

	/**
	 * 判断一个字符串是否是英文字母或者数字组成
	 * @param str
	 * @return
	 */
	public static boolean isEnglishOrNumber(String str) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
		return pattern.matcher(str).matches();
	}

	/**
	 * 是否是英文字符
	 * @param str
	 * @return
	 */
	public static boolean checkEnglish(String str) {
		if (str == null || str.trim().length() == 0)
			return true;
		// 全角转半角
		str = toSemiangle(str);
		int len = str.length();
		for (int i = 0; i < len; i++) {
			if (str.charAt(i) > 255) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断两个字符串是相同(去除前后的空字符串比较)
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean strEquals(String str1, String str2) {
		boolean bEmpty1 = isEmpty(str1);
		boolean bEmpty2 = isEmpty(str2);
		boolean bRet = false;
		if (bEmpty1) {
			if (bEmpty2) {
				bRet = true;
			} else {
				bRet = false;
			}
		} else {
			if (bEmpty2) {
				bRet = false;
			} else {
				bRet = str1.trim().equals(str2.trim());
			}
		}
		return bRet;
	}

	/**
	 * 取得返回info中的参数值
	 * 
	 * @param info   info信息
	 * @param param  参数名字
	 * @return
	 */
	public static String getParamFromInfo(String info, String param) {

		//截取amount=后的字符串
		String api = "";
		try {
			int index = info.indexOf("&" + param);

			if (index < 0) {
				index = info.startsWith(param + "=") ? 0 : -1;
			} else {
				index += 1;
			}
			if (index > -1) {
				api = info.substring(index + param.length() + 1);
				//截取第一个“&”前的字符串，得到的即amount=后的金额
				if (api.indexOf("&") > -1) {
					api = api.substring(0, api.indexOf("&"));
				}
			}

		} catch (Exception e) {
			log.error("从返回info中取参数值失败，info：" + info + "; param : " + param);
		}

		return api;
	}

	//获取标准URL add by chenlei
	public static String getCanonicalUrl(String productName, String productid) {
		String filterProName = formatKeyword(productName.replaceAll("&quot;", ""));
		String canonicalUrl = filterProName + "/p-" + productid + ".html";
		return canonicalUrl;
	}

	public final static String formatKeyword(String keyword) {
		if (keyword == null)
			return "new-style";
		try {
			String s = keyword.replaceAll(regEx, " ");
			s = s.toLowerCase().trim();
			String regex = "free[\\s-_]*shipping[^\\w]*";
			s = s.replaceAll(regex, "");
			s = s.replaceAll("[^\\w]+", DELIMITER);
			s = s.replaceAll("-+", DELIMITER);
			s = URLEncoder.encode(s);

			if (s.length() > KEYWORD_LENGTH_IN_URL) {
				int i = s.indexOf(DELIMITER, KEYWORD_LENGTH_IN_URL);
				if (i > -1) {
					s = s.substring(0, i);
				}
			}
			return s.toLowerCase();
		} catch (Exception e) {
			log.error("正则表达式处理错误: " + keyword);
			return "new-style";
		}
	}

	/** 
	* 判断字符串的URL编码 
	* 
	* @param str 
	* @return 
	*/
	public static String getUrlEncodingType(String str) {
		String encode = "UTF-8";
		try {
			if (str.equals(URLEncoder.encode(URLDecoder.decode(str, encode), encode))) {
				return encode;
			}
		} catch (Exception exception2) {
		}

		encode = "GB2312";
		try {
			if (str.equals(URLEncoder.encode(URLDecoder.decode(str, encode), encode))) {
				return encode;
			}
		} catch (Exception exception2) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(URLEncoder.encode(URLDecoder.decode(str, encode), encode))) {
				return encode;
			}
		} catch (Exception exception2) {
		}

		encode = "GBK";
		try {
			if (str.equals(URLEncoder.encode(URLDecoder.decode(str, encode), encode))) {
				return encode;
			}
		} catch (Exception exception2) {
		}
		return "UTF-8";
	}

	/**
	 * 解码字符串
	 */
	public static String getUrlEncoding(String str) {
		String encodeType = "UTF-8";
		//还原 | 符号
		if (str.indexOf("%7C") != -1) {
			str = str.replace("%7C", "|");
		}
		if (str.indexOf("|") != -1) {
			String[] strs = str.split("\\|");
			if (strs.length >= 2) {
				encodeType = getUrlEncodingType(strs[strs.length - 2]);
			}
		}
		try {
			if (str != null) {
				str = URLDecoder.decode(str == null ? "" : str.toLowerCase(), encodeType);
				int ff = str.indexOf("%");
				if (ff > 0) {
					//一次转义不成。要二次转义
					str = URLDecoder.decode(str, encodeType);
				}
			}
		} catch (Throwable e) {
			log.error(e);
		}
		return str;
	}
}
