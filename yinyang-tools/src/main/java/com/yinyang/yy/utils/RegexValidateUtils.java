package com.yinyang.yy.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.Perl5Substitution;
import org.apache.oro.text.regex.Util;

/**
 * 正则表达式工具类
 * 
 * @Title: RegexValidateUtils.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午2:26:55
 * @version V1.0
 */
public class RegexValidateUtils {

	private static Log log = LogFactory.getLog(RegexValidateUtils.class);
	
	// 验证文件后缀
	// Pattern p =
	// Pattern.compile(".+\\.js$",Pattern.CASE_INSENSITIVE);//不区分大小写的,默认只有一个参数时是区分大小写
	// System.out.println(p.matcher("dads.jS").matches());

	/**
	 * 对一字符串进行正则验证 验证一个字符串中长度必须大于6位，同时必须包含至少一个字符和一个数值
	 * 
	 * @param str
	 *            待验证的字符串
	 * 
	 * @return 通过验证时返回null 如果验证出现问题，会返回对应的失败原因
	 * @throws Exception
	 */
	public static String validateString(String str) throws Exception {
		// 验证长度
		if (validateLength(str) == null) {
			// 验证字符元素
			return isContainNumber(str);
		} else {
			return validateLength(str);
		}

	}

	/**
	 * 验证content中必须同时存在数字和字母
	 * 
	 * @param content
	 * @return 通过验证时，返回null 否则返回对应提示
	 * @throws Exception
	 * 
	 */
	public static String isContainNumber(String content) throws Exception {
		String message = "必须包含数字和字母";
		String restring = "^.*(([a-zA-Z]+.*[0-9]+)|([0-9]+.*[a-zA-Z]+)).*$"; // 必须六位以上,包含数字和字母
		Pattern pattern = Pattern.compile(restring);
		if (pattern.matcher(content).matches()) {
			return null;
		}
		return message;
	}

	/**
	 * 验证content的长度必须大于等六位
	 * 
	 * @param content
	 * @return 通过验证时，返回null 否则返回对应提示
	 * @throws Exception
	 */
	public static String validateLength(String content) throws Exception {
		String message = "必须六位以上";
		String restring = "^.{6,}$";
		Pattern pattern = Pattern.compile(restring);
		if (pattern.matcher(content).matches()) {
			return null;
		}
		return message;
	}

	/**
	 * 如果字符串url是否以指定的数组的任一字符串结尾
	 * 
	 * @param url
	 *            指定字符串
	 * @param noFomat
	 *            格式集[".js",".html"] <<主要是用于文件格式的验证，必须以"."开头加后缀名 如： .js >>
	 * @return url不是以指定的数组的任一字符串结尾 返回 true 否则返回false
	 */
	public static boolean validateUrl(String url, String[] noFomat) {
		// 正则非
		String endStr = "";
		// 为空时返回true
		if (noFomat == null || noFomat.length - 0 == 0 || url == null) {
			return true;
		} else {
			for (int i = 0; i < noFomat.length; i++) {
				if (i - 0 == 0) {
					endStr = "((\\" + noFomat[i] + ")";
				} else {
					endStr = endStr + "|(\\" + noFomat[i] + ")";
				}

				if (i - noFomat.length + 1 == 0) {
					endStr = endStr + ")$";
				}
			}
		}
		// 定义正则
		String reg = "^.*";
		// 验证文件后缀
		// 其中参数意义：不区分大小写的,默认只有一个参数时是区分大小写
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(reg + endStr, java.util.regex.Pattern.CASE_INSENSITIVE);
		return !p.matcher(url).matches();
	}

	/**
	 * 验证指定的字符串是不是符点型数值
	 * 
	 * @param value
	 *            某指定的字符串
	 * @return 字符串符合数值格式时返回true,否则返回false
	 */
	public static boolean isFloat(String value) {
		String restring = "^-?(\\d)+((.\\d+)|(\\d)*)$";
		Pattern pattern = Pattern.compile(restring);
		if (pattern.matcher(value).matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 获取 order by 开始位置
	 * 
	 * @param orderbysql
	 * @return
	 */
	public static int getOrderByIndex(String orderbysql) {
		int index = -1;
		if (StringUtils.isBlank(orderbysql)) {
			return index;
		}
		String regStr = "\\s+(order)\\s+(by)";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(orderbysql.toLowerCase());
		if (matcher.find()) {
			index = matcher.start();
		}
		return index;
	}

	/**
	 * 获取 group by 开始位置
	 * 
	 * @param groupbysql
	 * @return
	 */
	public static int getGroupByIndex(String groupbysql) {
		int index = -1;
		if (StringUtils.isBlank(groupbysql)) {
			return index;
		}
		String regStr = "\\s+(group)\\s+(by)";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(groupbysql.toLowerCase());
		if (matcher.find()) {
			index = matcher.start();
		}
		return index;
	}

	/**
	 * 获取 group by 开始位置
	 * 
	 * @param sql
	 * @return
	 */
	public static int getFromIndex(String sql) {
		int index = -1;
		if (StringUtils.isBlank(sql)) {
			return index;
		}

		String s = replaceFrom(sql).toLowerCase();

		int startIndex = s.indexOf(" from ");
		int lastIndex = s.lastIndexOf(" from ");
		if (startIndex - lastIndex != 0) {
			return index;
		}
		index = startIndex;
		return index;
	}

	/**
	 * 去掉无用的 from
	 * 
	 * @param str
	 * @return
	 */
	private static String replaceFrom(String str) {
		Pattern pt = Pattern.compile("\\(([\\s\\S]+?)\\)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pt.matcher(str);
		while (matcher.find()) {
			String group = matcher.group(1);
			String t1 = group.toLowerCase().replace(" from ", " abcd ");
			str = str.replace(group, t1);
		}
		return str;
	}

	/**
	 * 匹配单列单字段数据(match single row and single field)
	 * 
	 * @param str
	 *            字符串
	 * @param regex
	 *            正则式
	 * @param isFull
	 * 			　是否包括匹配部分的字符
	 * @return
	 */
	public static synchronized String matchSRowSField(String str, String regex,
			boolean isFull) {
		List<MatchResult> resultList= matcher(str,regex);
		if(resultList.size() >= 1){
			MatchResult result = resultList.get(0);
			if (isFull == true) {
				return result.group(0);
			} else {
				return result.group(1);
			}
		}
		return null;
	}
	
	/**
	 * 匹配单列多字段数据(match single row and multi field)
	 * 
	 * @param str
	 *            字符串
	 * @param regex
	 *            正则式
	 * @return
	 */
	public static synchronized ResultSet matchSRowMField(String str, String regex) {
		List<MatchResult> resultList= matcher(str,regex);
		if(resultList.size() >= 1){
			MatchResult result = resultList.get(0);
			ResultSet rs = new ResultSet();
			for(int j=1;j<result.groups();j++){
				rs.add(result.group(j));
			}
			return rs;
		}
		return null;
	}
	
	/**
	 * 匹配多列单字段数据(match multi row and single field)
	 * 
	 * @param str
	 *            字符串
	 * @param regex
	 *            正则式
	 * @param isFull
	 * 			　是否包括匹配部分的字符
	 *  
	 * @return
	 */
	public static synchronized List<String> matchMRowSField(String str, String regex,
			boolean isFull) {
		List<MatchResult> resultList= matcher(str,regex);

		LinkedList<String> list = new LinkedList<String>();

		for(int i=0;i<resultList.size();i++){
			MatchResult result = (MatchResult)resultList.get(i);
			if (isFull == true) {
				list.add(result.group(0));
			} else {
				list.add(result.group(1));
			}
		}
		return list;
	}
	
	/**
	 * 匹配多列多字段数据(match multi row and multi field)
	 * 
	 * @param str
	 *            字符串
	 * @param regex
	 *            正则式
	 * @return
	 */
	public static synchronized List<ResultSet> matchMRowMField(String str, String regex) {
		List<MatchResult> resultList= matcher(str,regex);

		LinkedList<ResultSet> resultSetObjList = new LinkedList<ResultSet>();
		
		for(int i=0;i<resultList.size();i++){
			MatchResult result = resultList.get(i);
			ResultSet rs = new ResultSet();
			for(int j=1;j<result.groups();j++){
				rs.add(result.group(j));
			}
			resultSetObjList.add(rs);
		}
		return resultSetObjList;
	}
	
	/**
	 * 配数据,返回MatchResult对象数据
	 * 
	 * @param str
	 *            字符串
	 * @param regex
	 *            正则式
	 * @return
	 */
	public static synchronized List<MatchResult> matcher(String str, String regex) {
		org.apache.oro.text.regex.Pattern pattern = null;

		PatternCompiler com = new Perl5Compiler();
		LinkedList<MatchResult> resultList = new LinkedList<MatchResult>();
		
		try {
			pattern = com.compile(regex, Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.SINGLELINE_MASK);
		} catch (MalformedPatternException e) {
			log.error(e);
		}
		PatternMatcherInput input = new PatternMatcherInput(str);
		PatternMatcher matcher = new Perl5Matcher();
		
		while (matcher.contains(input, pattern)) {
			resultList.add(matcher.getMatch());
		}
		return resultList;
	}
	
	/**
	 * 在字符串中替换字符串。
	 * 
	 * @param str
	 *            原始字符串
	 * @param reg
	 *            正则式
	 * @param str2
	 *            替换成为字符串
	 * 
	 * @param replacecounter
	 *            替换次数,为0则replaceAll
	 * 
	 */

	public static synchronized String replace(String str, String reg,
			String replaceStr, int replacecounter) {
		String result = str;
		
		org.apache.oro.text.regex.Pattern pattern = null;
		
		if (replacecounter == 0) {
			replacecounter = Util.SUBSTITUTE_ALL;
		}
		String _str = str;
		String _reg = reg;

		PatternCompiler com = new Perl5Compiler();
		try {
			pattern = com.compile(_reg, Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.SINGLELINE_MASK);
		} catch (MalformedPatternException e) {
			log.error(e);
		}
		PatternMatcher matcher = new Perl5Matcher();
		result = Util.substitute(matcher, pattern, new Perl5Substitution(
				replaceStr), _str, replacecounter);
		return result;
	}
	
	/**
	 * 特定的换值方式
	 * @param str
	 * @param param
	 * @param value
	 * @return
	 */
	public static synchronized String replaceParamValue(String _str,String param,String value){
		return _str.replaceAll("\\{\\$" + param + "\\}",value);
	}
	
	/**
	 * 特定的换值方式,一定换多个值
	 * @param str
	 * @param paramMap
	 * @return
	 */
	public static synchronized String replaceParamValue(String _str,Map<String, String> paramMap){
		String str = _str;
		Iterator<String> it = paramMap.keySet().iterator();
		while(it.hasNext()){
			String param = (String)it.next();
			String value = (String)paramMap.get(param);
			str = str.replaceAll("\\{\\$" + param + "\\}",value);
		}
		return str;
	}
	
	public static boolean isMatch(String regex,String str){
	    
	    org.apache.oro.text.regex.Pattern pattern = null;

        PatternCompiler com = new Perl5Compiler();
        try {
            pattern = com.compile(regex, Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.SINGLELINE_MASK);
        } catch (MalformedPatternException e) {
        	log.error(e);
        }
        PatternMatcherInput input = new PatternMatcherInput(str);
        PatternMatcher matcher = new Perl5Matcher();
        
        return matcher.contains(input, pattern);
	    
	}
	
	public static void main(String[] args) {
		/*
		 * try {
		 * 
		 * 
		 * System.out.println(RegexValidateUtils.isContainNumber("dss1fasd;f"));
		 * System.out.println(RegexValidateUtils.isContainNumber("2dsf"));
		 * System.out.println(RegexValidateUtils.isContainNumber("dsf3"));
		 * System.out.println(RegexValidateUtils.isContainNumber("dsf"));
		 * System.out.println(RegexValidateUtils.isContainNumber("123")); }
		 * catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		// System.out.println(RegexValidateUtils.isFloat("-012416545.000"));
		String countSql = "select 1,(select id from ddddd) FROM abc1 where 1=1  and faa id in (select id from abc2)  distinct a order by   ";

		int order_int = getOrderByIndex(countSql);
		if (order_int > 1) {
			countSql = countSql.substring(0, order_int);
		}

		/**
		 * 特殊关键字过滤, distinct ,union ,group by
		 */
		if (countSql.toLowerCase().indexOf(" distinct ") > -1 || countSql.toLowerCase().indexOf(" union ") > -1 || RegexValidateUtils.getGroupByIndex(countSql) > -1) {
			countSql = "SELECT count(*)  frame_row_count FROM (" + countSql + ") temp_frame_noob_table_name WHERE 1=1 ";
		} else {
			int fromIndex = RegexValidateUtils.getFromIndex(countSql);
			if (fromIndex > -1) {
				countSql = "SELECT COUNT(*) " + countSql.substring(fromIndex);
			} else {
				countSql = "SELECT count(*)  frame_row_count FROM (" + countSql + ") temp_frame_noob_table_name WHERE 1=1 ";
			}
		}

	}
}
