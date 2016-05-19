package com.yinyang.yy.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * IP与国家城市判断
 * 
 * @Title: IPSearchUtils.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午4:01:24
 * @version V1.0
 */
@SuppressWarnings({"rawtypes","unchecked","unused"})
public class IPSearchUtils {

	//索引表的键值
	/**IP数据集的文件名*/
	public static String CONST_IPGATHER_FILE = "ip.dat";

	/**国家IP的文件名*/
	public static String CONST_IPCOUNTRY_FILE = "country.txt";

	/**城市IP的文件名*/
	public static String CONST_IPCITY_FILE = "city.txt";

	/**高校IP的文件名*/
	public static String CONST_IPCOLLEGE_FILE = "college.txt";

	/**系统中配置文件的路径*/
	public static String CONST_CONFIGFILE_PATH = null;

	/** 网站联盟cookie、session名称 **/
	public static String AD_FROM = "AD_FROM";
	public static String AD_INFO = "AD_INFO";
	
	private String FILE_IPINDEX = "ip.dat";
	static Logger log = Logger.getLogger(IPSearchUtils.class.getName());

	/**
	 * 用来封装ip相关信息，目前只有两个字段，ip所在的国家和地区
	 * 
	 */
	private class IPLocation {
		public String country, area;

		public IPLocation() {
			country = area = "";
		}

		public IPLocation getCopy() {
			IPLocation ret = new IPLocation();
			ret.country = country;
			ret.area = area;
			return ret;
		}
	}

	// 一些固定常量，比如记录长度等等
	private static final int IP_RECORD_LENGTH = 7;
	private static final byte REDIRECT_MODE_1 = 0x01;
	private static final byte REDIRECT_MODE_2 = 0x02;

	// 用来做为cache，查询一个ip时首先查看cache，以减少不必要的重复查找
	// 随机文件访问类
	private static RandomAccessFile ipFile = null;
	// 内存映射文件
	private static MappedByteBuffer mbb;
	// 起始地区的开始和结束的绝对偏移
	private static long ipBegin, ipEnd;

	String fileBar = System.getProperty("file.separator");
	private String countryfile = CONST_IPCOUNTRY_FILE;
	private String cityfile = CONST_IPCITY_FILE;
	private String collegefile = CONST_IPCOLLEGE_FILE;
	private HashMap countrymap = new HashMap();
	private HashMap citymap = new HashMap();
	private HashMap collegemap = new HashMap();
	private int country_max_len = 0;

	/**
	 * 构造函数
	 */
	public IPSearchUtils() {
		if (ipFile == null) {
			try {
				//文件路径
				FILE_IPINDEX = CONST_CONFIGFILE_PATH + fileBar + FILE_IPINDEX;
				ipFile = new RandomAccessFile(FILE_IPINDEX, "r");
			} catch (FileNotFoundException e) {
				ipFile = null;
			}
			// 如果打开文件成功，读取文件头信息
			if (ipFile != null) {
				try {
					ipBegin = readLong4(0);
					ipEnd = readLong4(4);
					long num = (ipEnd - ipBegin) / 7;
					if (ipBegin == -1 || ipEnd == -1) {
						ipFile.close();
						ipFile = null;
					}
				} catch (IOException e) {
					ipFile = null;
				}
			}
			// 映射IP信息文件到内存中
			try {
				FileChannel fc = ipFile.getChannel();
				mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, ipFile.length());
				mbb.order(ByteOrder.LITTLE_ENDIAN);

				String aline = null;
				countryfile = CONST_CONFIGFILE_PATH + fileBar + countryfile;
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(countryfile)));
				while ((aline = br.readLine()) != null) {
					aline = aline.trim();
					if (aline.length() > country_max_len)
						country_max_len = aline.length();
					countrymap.put(aline, aline);
				}
				br.close();

				cityfile = CONST_CONFIGFILE_PATH + fileBar + cityfile;
				br = new BufferedReader(new InputStreamReader(new FileInputStream(cityfile)));
				aline = br.readLine();
				String province = "";
				while (aline != null) {
					if (aline.indexOf("##########") >= 0) {
						aline = br.readLine();
						if (aline == null)
							break;
						province = aline.trim();
						while ((aline = br.readLine()) != null && aline.indexOf("##########") < 0) {
							citymap.put(aline.trim(), province);
						}
					}
				}
				br.close();

				collegefile = CONST_CONFIGFILE_PATH + fileBar + collegefile;
				br = new BufferedReader(new InputStreamReader(new FileInputStream(collegefile)));
				aline = br.readLine();
				province = "";
				while (aline != null) {
					if (aline.indexOf("##########") >= 0) {
						aline = br.readLine();
						if (aline == null)
							break;
						province = aline.trim();
						while ((aline = br.readLine()) != null && aline.indexOf("##########") < 0) {
							collegemap.put(aline.trim(), province);
						}
					}
				}
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
				ipFile = null;
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			}
		}
	}

	public boolean isValid(String ip, String[] ret) {
		try {
			String tmpStr = ip.trim();
			StringTokenizer st = new StringTokenizer(tmpStr, ".");
			for (int i = 0; i < 4; i++) {
				int tmp = (Integer.parseInt(st.nextToken()));
				if (tmp > 255 || tmp < 0)
					return false;
			}
			if (st.hasMoreTokens())
				return false;
			ret[0] = tmpStr;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static byte[] getAddress(String ip) {
		byte[] ret = new byte[4];
		try {
			StringTokenizer st = new StringTokenizer(ip, ".");
			for (int i = 0; i < 4; i++)
				ret[i] = (byte) (Integer.parseInt(st.nextToken()));
		} catch (Exception e) {
			log.error(e);
		}
		return ret;
	}

	/**
	 * 从内存映射文件的offset位置开始的3个字节读取一个int
	 * @param offset
	 * @return
	 */
	private int readInt3(int offset) {
		mbb.position(offset);
		return mbb.getInt() & 0x00FFFFFF;
	}

	/**
	 * 从内存映射文件的当前位置开始的3个字节读取一个int
	 * @return
	 */
	private int readInt3() {
		return mbb.getInt() & 0x00FFFFFF;
	}

	/**
	 * 根据ip搜索ip信息文件，得到IPLocation结构，所搜索的ip参数从类成员ip中得到
	 * @param ip 要查询的IP
	 * @return IPLocation结构
	 */
	private IPLocation getIPLocation(byte[] ip) {
		IPLocation info = null;
		long offset = locateIP(ip);
		if (offset != -1)
			info = getIPLocation(offset);
		if (info == null) {
			info = new IPLocation();
			info.country = "";
			info.area = "";
		}
		return info;
	}

	/**
	 * 从offset位置读取4个字节为一个long，因为java为big-endian格式，所以没办法
	 * 用了这么一个函数来做转换
	 * @param offset
	 * @return 读取的long值，返回-1表示读取文件失败
	 */
	private long readLong4(long offset) {
		long ret = 0;
		try {
			ipFile.seek(offset);
			ret |= (ipFile.readByte() & 0xFF);
			ret |= ((ipFile.readByte() << 8) & 0xFF00);
			ret |= ((ipFile.readByte() << 16) & 0xFF0000);
			ret |= ((ipFile.readByte() << 24) & 0xFF000000);
			return ret;
		} catch (IOException e) {
			return -1;
		}
	}

	/**
	 * 从offset位置读取3个字节为一个long，因为java为big-endian格式，所以没办法
	 * 用了这么一个函数来做转换
	 * @param offset 整数的起始偏移
	 * @return 读取的long值，返回-1表示读取文件失败
	 */
	private long readLong3(long offset) {
		long ret = 0;
		try {
			byte[] b3 = new byte[3];
			ipFile.seek(offset);
			ipFile.readFully(b3);
			ret |= (b3[0] & 0xFF);
			ret |= ((b3[1] << 8) & 0xFF00);
			ret |= ((b3[2] << 16) & 0xFF0000);
			return ret;
		} catch (IOException e) {
			return -1;
		}
	}

	/**
	 * 从当前位置读取3个字节转换成long
	 * @return 读取的long值，返回-1表示读取文件失败
	 */
	private long readLong3() {
		long ret = 0;
		try {
			byte[] b3 = new byte[3];
			ipFile.readFully(b3);
			ret |= (b3[0] & 0xFF);
			ret |= ((b3[1] << 8) & 0xFF00);
			ret |= ((b3[2] << 16) & 0xFF0000);
			return ret;
		} catch (IOException e) {
			return -1;
		}
	}

	/**
	 * 从offset位置读取四个字节的ip地址放入ip数组中，读取后的ip为big-endian格式，但是
	 * 文件中是little-endian形式，将会进行转换
	 * @param offset
	 * @param ip
	 */
	private void readIP(long offset, byte[] ip) {
		try {
			ipFile.seek(offset);
			ipFile.readFully(ip);
			byte temp = ip[0];
			ip[0] = ip[3];
			ip[3] = temp;
			temp = ip[1];
			ip[1] = ip[2];
			ip[2] = temp;
		} catch (IOException e) {
			log.error(e);
		}
	}

	/**
	 * 从offset位置读取四个字节的ip地址放入ip数组中，读取后的ip为big-endian格式，但是
	 * 文件中是little-endian形式，将会进行转换
	 * @param offset
	 * @param ip
	 */
	private void readIP(int offset, byte[] ip) {
		mbb.position(offset);
		mbb.get(ip);
		byte temp = ip[0];
		ip[0] = ip[3];
		ip[3] = temp;
		temp = ip[1];
		ip[1] = ip[2];
		ip[2] = temp;
	}

	private void displayBits(int value) {
		int c, displayMask = 1 << 7;
		int num;

		for (c = 1; c <= 8; c++) {
			num = value & displayMask;
			if (num > 0) {

			} else {
			}
			value <<= 1;
		}
	}

	/**
	 * 把类成员ip和beginIp比较，注意这个beginIp是big-endian的
	 * @param ip 要查询的IP
	 * @param beginIp 和被查询IP相比较的IP
	 * @return 相等返回0，ip大于beginIp则返回1，小于返回-1。
	 */
	private int compareIP(byte[] ip, byte[] beginIp) {
		for (int i = 0; i < 4; i++) {
			//displayBits(ip[i]);
			//displayBits(beginIp[i]);
			int r = compareByte(ip[i], beginIp[i]);
			if (r != 0)
				return r;
		}
		return 0;
	}

	/**
	 * 把两个byte当作无符号数进行比较
	 * @param b1
	 * @param b2
	 * @return 若b1大于b2则返回1，相等返回0，小于返回-1
	 */
	private int compareByte(byte b1, byte b2) {
		if ((b1 & 0xFF) > (b2 & 0xFF)) // 比较是否大于
			return 1;
		else if ((b1 ^ b2) == 0)// 判断是否相等
			return 0;
		else
			return -1;
	}

	private void value2ip(byte[] b4) {
		int a, b, c, d;

		a = b4[0] & 0xFF;
		b = b4[1] & 0xFF;
		c = b4[2] & 0xFF;
		d = b4[3] & 0xFF;
	}

	/**
	 * 这个方法将根据ip的内容，定位到包含这个ip国家地区的记录处，返回一个绝对偏移
	 * 方法使用二分法查找。
	 * @param ip 要查询的IP
	 * @return 如果找到了，返回结束IP的偏移，如果没有找到，返回-1
	 */
	private long locateIP(byte[] ip) {
		long m = 0, i = 0, j = 0, ii, jj, mm;
		int r;
		int a, b, c, d;
		byte[] b4 = new byte[4];

		// 比较第一个ip项
		readIP(ipBegin, b4);
		r = compareIP(ip, b4);
		if (r == 0)
			return ipBegin;
		else if (r < 0)
			return -1;

		// 开始二分搜索
		for (i = ipBegin, j = ipEnd; i < j;) {
			m = getMiddleOffset(i, j);
			readIP(m, b4);
			r = compareIP(ip, b4);
			ii = (i - ipBegin) / 7;
			jj = (j - ipBegin) / 7;
			mm = (m - ipBegin) / 7;
			if (r > 0)
				i = m;
			else if (r < 0) {
				if (m == j) {
					j -= IP_RECORD_LENGTH;
					m = j;
				} else
					j = m;
			} else
				return readLong3(m + 4);
		}
		// 如果循环结束了，那么i和j必定是相等的，这个记录为最可能的记录，但是并非
		//     肯定就是，还要检查一下，如果是，就返回结束地址区的绝对偏移
		ii = (i - ipBegin) / 7;
		jj = (j - ipBegin) / 7;
		mm = (m - ipBegin) / 7;

		m = readLong3(m + 4);
		readIP(m, b4);
		//value2ip(b4);
		r = compareIP(ip, b4);

		//ii = (i-ipBegin)/7;
		//jj = (j-ipBegin)/7;
		//mm = (m-ipBegin)/7;

		if (r <= 0)
			return m;
		else
			return -1;
	}

	//判断一个字符是否为英文字母或数字的函数
	private static boolean isEnLetterOrDigit(char ch) {
		return (((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z')) || ((ch >= '0') && (ch <= '9')) || (ch == ' '));
	}

	private boolean isEnglishWord(String word) {
		boolean isEnglishWord = true;
		word = word.trim();
		char[] contents = word.toCharArray();
		for (int i = 0; i < contents.length; i++) {
			if (isEnLetterOrDigit(contents[i])) {
				continue;
			} else {
				isEnglishWord = false;
				break;
			}
		}
		return isEnglishWord;
	}

	private int getProvince(String address) {
		if (address == null || address.length() < 4)
			return -1;

		int ret = -1;

		if (address.startsWith("国外"))
			ret = 0;
		else if (address.startsWith("北京"))
			ret = 1;
		else if (address.startsWith("上海"))
			ret = 2;
		else if (address.startsWith("天津"))
			ret = 3;
		else if (address.startsWith("重庆"))
			ret = 4;
		else if (address.startsWith("安徽"))
			ret = 5;
		else if (address.startsWith("福建"))
			ret = 6;
		else if (address.startsWith("甘肃"))
			ret = 7;
		else if (address.startsWith("广东"))
			ret = 8;
		else if (address.startsWith("广西"))
			ret = 9;
		else if (address.startsWith("贵州"))
			ret = 10;
		else if (address.startsWith("海南"))
			ret = 11;
		else if (address.startsWith("河北"))
			ret = 12;
		else if (address.startsWith("河南"))
			ret = 13;
		else if (address.startsWith("黑龙江"))
			ret = 14;
		else if (address.startsWith("湖北"))
			ret = 15;
		else if (address.startsWith("湖南"))
			ret = 16;
		else if (address.startsWith("吉林"))
			ret = 17;
		else if (address.startsWith("江苏"))
			ret = 18;
		else if (address.startsWith("江西"))
			ret = 19;
		else if (address.startsWith("辽宁"))
			ret = 20;
		else if (address.startsWith("内蒙古"))
			ret = 21;
		else if (address.startsWith("宁夏"))
			ret = 22;
		else if (address.startsWith("青海"))
			ret = 23;
		else if (address.startsWith("山东"))
			ret = 24;
		else if (address.startsWith("山西"))
			ret = 25;
		else if (address.startsWith("陕西"))
			ret = 26;
		else if (address.startsWith("四川"))
			ret = 27;
		else if (address.startsWith("西藏"))
			ret = 28;
		else if (address.startsWith("新疆"))
			ret = 29;
		else if (address.startsWith("云南"))
			ret = 30;
		else if (address.startsWith("浙江"))
			ret = 31;
		else if (address.startsWith("香港"))
			ret = 32;
		else if (address.startsWith("澳门"))
			ret = 32;
		else if (address.startsWith("台湾"))
			ret = 32;

		return ret;
	}

	private int getInProvince(String address) {
		if (address == null || address.length() < 4)
			return -1;

		int ret = -1;

		if (address.indexOf("北京") >= 0)
			ret = 1;
		else if (address.indexOf("上海") >= 0)
			ret = 2;
		else if (address.indexOf("天津") >= 0)
			ret = 3;
		else if (address.indexOf("重庆") >= 0)
			ret = 4;
		else if (address.indexOf("安徽") >= 0)
			ret = 5;
		else if (address.indexOf("福建") >= 0)
			ret = 6;
		else if (address.indexOf("甘肃") >= 0)
			ret = 7;
		else if (address.indexOf("广东") >= 0)
			ret = 8;
		else if (address.indexOf("广西") >= 0)
			ret = 9;
		else if (address.indexOf("贵州") >= 0)
			ret = 10;
		else if (address.indexOf("海南") >= 0)
			ret = 11;
		else if (address.indexOf("河北") >= 0)
			ret = 12;
		else if (address.indexOf("河南") >= 0)
			ret = 13;
		else if (address.indexOf("黑龙江") >= 0)
			ret = 14;
		else if (address.indexOf("湖北") >= 0)
			ret = 15;
		else if (address.indexOf("湖南") >= 0)
			ret = 16;
		else if (address.indexOf("吉林") >= 0)
			ret = 17;
		else if (address.indexOf("江苏") >= 0)
			ret = 18;
		else if (address.indexOf("江西") >= 0)
			ret = 19;
		else if (address.indexOf("辽宁") >= 0)
			ret = 20;
		else if (address.indexOf("内蒙古") >= 0)
			ret = 21;
		else if (address.indexOf("宁夏") >= 0)
			ret = 22;
		else if (address.indexOf("青海") >= 0)
			ret = 23;
		else if (address.indexOf("山东") >= 0)
			ret = 24;
		else if (address.indexOf("山西") >= 0)
			ret = 25;
		else if (address.indexOf("陕西") >= 0)
			ret = 26;
		else if (address.indexOf("四川") >= 0)
			ret = 27;
		else if (address.indexOf("西藏") >= 0)
			ret = 28;
		else if (address.indexOf("新疆") >= 0)
			ret = 29;
		else if (address.indexOf("云南") >= 0)
			ret = 30;
		else if (address.indexOf("浙江") >= 0)
			ret = 31;
		else if (address.indexOf("香港") >= 0)
			ret = 32;
		else if (address.indexOf("澳门") >= 0)
			ret = 32;
		else if (address.indexOf("台湾") >= 0)
			ret = 32;

		return ret;
	}

	private int getCountry(String address) {
		if (address == null || address.length() == 0)
			return -1;
		if (isEnglishWord(address))
			return 0;

		int ret = -1, len = 4;
		if (address.length() < len)
			return ret;

		String tmpstr = address.substring(0, len);
		while (len <= country_max_len) {
			if (countrymap.containsKey(tmpstr))
				return 0;
			else {
				len += 2;
				if (address.length() < len)
					break;
				tmpstr = address.substring(0, len);
			}
		}

		return ret;
	}

	private int getCity(String address) {
		if (address == null || address.length() < 2)
			return -1;
		int ret = -1;

		Set Keys = citymap.keySet();
		Iterator it = Keys.iterator();
		String city = null, province = null;
		while (it.hasNext()) {
			city = (String) it.next();
			if (address.indexOf(city) >= 0) {
				province = (String) citymap.get(city);
				ret = getProvince(province);
				return ret;
			}
		}

		return ret;
	}

	private int getCollege(String address) {
		if (address == null || address.length() < 2)
			return -1;
		int ret = -1, tmpret = -1;

		Set Keys = collegemap.keySet();
		Iterator it = Keys.iterator();
		String college = null, province = null;
		while (it.hasNext()) {
			college = (String) it.next();
			if (address.indexOf(college) >= 0) {
				province = (String) collegemap.get(college);
				ret = getProvince(province);
				if (college.length() <= 4)
					tmpret = ret;
				else
					return ret;
			}
		}

		if (ret < 0 && tmpret >= 0)
			ret = tmpret;
		return ret;
	}

	private int getCN(String address) {
		if (address == null || address.length() == 0)
			return -1;
		int ret = 0;

		ret = getCountry(address);
		if (ret < 0) {
			ret = getProvince(address);
			if (ret < 0) {
				ret = getInProvince(address);
				if (ret < 0) {
					ret = getCity(address);
					if (ret < 0) {
						ret = getCollege(address);
						if (ret < 0)
							ret = 0;
					}
				}
			}
		}
		return ret;
	}

	private String getRegion(int cn) {
		String regionlist = "";

		switch (cn) {
		case 0:
			regionlist = "国外";
			break;
		case 1:
			regionlist = "北京";
			break;
		case 2:
			regionlist = "上海";
			break;
		case 3:
			regionlist = "天津";
			break;
		case 4:
			regionlist = "重庆";
			break;
		case 5:
			regionlist = "安徽";
			break;
		case 6:
			regionlist = "福建";
			break;
		case 7:
			regionlist = "甘肃";
			break;
		case 8:
			regionlist = "广东";
			break;
		case 9:
			regionlist = "广西";
			break;
		case 10:
			regionlist = "贵州";
			break;
		case 11:
			regionlist = "海南";
			break;
		case 12:
			regionlist = "河北";
			break;
		case 13:
			regionlist = "河南";
			break;
		case 14:
			regionlist = "黑龙江";
			break;
		case 15:
			regionlist = "湖北";
			break;
		case 16:
			regionlist = "湖南";
			break;
		case 17:
			regionlist = "吉林";
			break;
		case 18:
			regionlist = "江苏";
			break;
		case 19:
			regionlist = "江西";
			break;
		case 20:
			regionlist = "辽宁";
			break;
		case 21:
			regionlist = "内蒙古";
			break;
		case 22:
			regionlist = "宁夏";
			break;
		case 23:
			regionlist = "青海";
			break;
		case 24:
			regionlist = "山东";
			break;
		case 25:
			regionlist = "山西";
			break;
		case 26:
			regionlist = "陕西";
			break;
		case 27:
			regionlist = "四川";
			break;
		case 28:
			regionlist = "西藏";
			break;
		case 29:
			regionlist = "新疆";
			break;
		case 30:
			regionlist = "云南";
			break;
		case 31:
			regionlist = "浙江";
			break;
		case 32:
			regionlist = "港澳台";
			break;
		}
		return regionlist;
	}

	/**
	 * 得到begin偏移和end偏移中间位置记录的偏移
	 * @param begin
	 * @param end
	 * @return
	 */
	private long getMiddleOffset(long begin, long end) {
		long records = (end - begin) / IP_RECORD_LENGTH;
		records >>= 1;
		if (records == 0)
			records = 1;
		return begin + records * IP_RECORD_LENGTH;
	}

	/**
	 * 给定一个ip国家地区记录的偏移，返回一个IPLocation结构
	 * @param offset 国家记录的起始偏移
	 * @return IPLocation对象
	 */
	private IPLocation getIPLocation(long offset) {
		try {
			// 跳过4字节ip
			ipFile.seek(offset + 4);

			// 读取第一个字节判断是否标志字节
			byte b = ipFile.readByte();
			IPLocation loc = new IPLocation();
			if (b == REDIRECT_MODE_1) {
				// 读取国家偏移
				long countryOffset = readLong3();

				// 跳转至偏移处
				ipFile.seek(countryOffset);

				// 再检查一次标志字节，因为这个时候这个地方仍然可能是个重定向
				b = ipFile.readByte();
				if (b == REDIRECT_MODE_2) {
					loc.country = readString(readLong3());
					ipFile.seek(countryOffset + 4);
				} else
					loc.country = readString(countryOffset);
				// 读取地区标志
				loc.area = readArea(ipFile.getFilePointer());
			} else if (b == REDIRECT_MODE_2) {
				loc.country = readString(readLong3());
				loc.area = readArea(offset + 8);
			} else {
				loc.country = readString(ipFile.getFilePointer() - 1);
				loc.area = readArea(ipFile.getFilePointer());
			}
			return loc;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 给定一个ip国家地区记录的偏移，返回一个IPLocation结构，此方法应用与内存映射文件方式
	 * @param offset 国家记录的起始偏移
	 * @return IPLocation对象
	 */
	private IPLocation getIPLocation(int offset) {
		// 跳过4字节ip
		mbb.position(offset + 4);
		// 读取第一个字节判断是否标志字节
		byte b = mbb.get();
		IPLocation loc = new IPLocation();
		if (b == REDIRECT_MODE_1) {
			// 读取国家偏移
			int countryOffset = readInt3();
			// 跳转至偏移处
			mbb.position(countryOffset);
			// 再检查一次标志字节，因为这个时候这个地方仍然可能是个重定向
			b = mbb.get();
			if (b == REDIRECT_MODE_2) {
				loc.country = readString(readInt3());
				mbb.position(countryOffset + 4);
			} else
				loc.country = readString(countryOffset);
			// 读取地区标志
			loc.area = readArea(mbb.position());
		} else if (b == REDIRECT_MODE_2) {
			loc.country = readString(readInt3());
			loc.area = readArea(offset + 8);
		} else {
			loc.country = readString(mbb.position() - 1);
			loc.area = readArea(mbb.position());
		}
		return loc;
	}

	/**
	 * 从offset偏移开始解析后面的字节，读出一个地区名
	 * @param offset 地区记录的起始偏移
	 * @return 地区名字符串
	 * @throws IOException
	 */
	private String readArea(long offset) throws IOException {
		ipFile.seek(offset);
		byte b = ipFile.readByte();
		if (b == REDIRECT_MODE_1 || b == REDIRECT_MODE_2) {
			long areaOffset = readLong3(offset + 1);
			if (areaOffset == 0)
				return "";
			else
				return readString(areaOffset);
		} else
			return readString(offset);
	}

	/**
	 * @param offset 地区记录的起始偏移
	 * @return 地区名字符串
	 */
	private String readArea(int offset) {
		mbb.position(offset);
		byte b = mbb.get();
		if (b == REDIRECT_MODE_1 || b == REDIRECT_MODE_2) {
			int areaOffset = readInt3();
			if (areaOffset == 0)
				return "";
			else
				return readString(areaOffset);
		} else
			return readString(offset);
	}

	/**
	 * 从offset偏移处读取一个以0结束的字符串
	 * @param offset 字符串起始偏移
	 * @return 读取的字符串，出错返回空字符串
	 */
	private String readString(long offset) {
		byte[] buf = new byte[100];
		try {
			ipFile.seek(offset);
			int i;
			for (i = 0, buf[i] = ipFile.readByte(); buf[i] != 0; buf[++i] = ipFile.readByte())
				;
			if (i != 0) {
				//String result = getString(buf, 0, i, "iso8859-1");
				//return result;
				return getString(buf, 0, i, "iso8859-1");
			}
		} catch (IOException e) {
			log.error(e);
		}
		return "";
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * @param b 字节数组
	 * @param offset 要转换的起始位置
	 * @param len 要转换的长度
	 * @param encoding 编码方式
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String getString(byte[] b, int offset, int len, String encoding) {
		try {
			return new String(b, offset, len, encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b, offset, len);
		}
	}

	/**
	 * 从内存映射文件的offset位置得到一个0结尾字符串
	 * @param offset 字符串起始偏移
	 * @return 读取的字符串，出错返回空字符串
	 */
	private String readString(int offset) {
		byte[] buf = new byte[100];
		try {
			mbb.position(offset);
			int i;
			for (i = 0, buf[i] = mbb.get(); buf[i] != 0; buf[++i] = mbb.get())
				;
			if (i != 0)
				return getString(buf, 0, i, "iso8859-1");
		} catch (IllegalArgumentException e) {
		}
		return "";
	}

	public boolean locateInChina(String ip) {

		String result = "";

		byte[] ret = getAddress(ip);
		IPLocation info = getIPLocation(ret);

		if (info != null) {
			if (!info.country.equalsIgnoreCase(" CZ88.net"))
				result += info.country;
			if (!info.area.equalsIgnoreCase(" CZ88.net"))
				result += info.area;
		}
		if (result.trim().equals("")) {
			return false;
		} else {
			try {
				result = new String(result.getBytes("iso-8859-1"), "GBK");
			} catch (UnsupportedEncodingException e) {
			}

			int cn = getCN(result);

			return (cn != 0);//0
			//String region = getRegion(cn);

		}

	}

	/**
	  * 根据请求信息返回请求ip
	  * @param request
	  * @return
	  */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (!StringUtils.isEmpty(ip) && ip.indexOf(",") >= 0) {
			ip = ip.substring(0, ip.indexOf(","));
		}
		return ip;
	}

	//	public static void main(String args[])
	//    {                 
	//	    long start= System.currentTimeMillis();
	//	    
	//        SearchIP sip = new SearchIP();	    
	//	    sip.locateRegion(args[0]);
	//	    
	//	    /*
	//	    SearchIP sip = new SearchIP();
	//		byte[] ret = sip.getAddress(args[0]);
	//		int a,b,c,d;
	//        a = ret[0] & 0xFF;
	//        b = ret[1] & 0xFF;
	//        c = ret[2] & 0xFF;
	//        d = ret[3] & 0xFF;
	//		IPLocation myloc = sip.getIPLocation(ret);
	//		
	//		String country,area;
	//		if( myloc.country.equalsIgnoreCase(" CZ88.net"))
	//			country = "";
	//		else
	//			country = myloc.country;
	//		if( myloc.area.equalsIgnoreCase( " CZ88.net"))
	//			area = "";
	//		else
	//			area = myloc.area;
	//		try
	//        {
	//            area  = new String(area.getBytes("iso-8859-1"),"GBK");
	//            country  = new String(country.getBytes("iso-8859-1"),"GBK");
	//        }
	//        catch(UnsupportedEncodingException e)
	//        {
	//        }
	//		
	//		*/
	//		
	//		double elapse = (double)(System.currentTimeMillis() - start);
	//			
	//    } 
}
