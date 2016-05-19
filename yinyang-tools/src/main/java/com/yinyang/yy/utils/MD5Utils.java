package com.yinyang.yy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * MD5工具类
 * 非常重要：tomcat必须与项目编码一致，否则项目发布到tomcat之后导致MD5生成出来不一致
 * 设置方法如：useBodyEncodingForURI="true" URIEncoding="UTF-8" 注意顺序
 * 同时eclipse -Preferences-general-workspace-text file encodeing 设置为UTF-8
 * @author  作者：yinyang E-mail: 
 * @date 创建时间：2015年4月8日 上午10:15:38 
 * @version 1.0  
 */
public class MD5Utils {
	
	private static Log log = LogFactory.getLog(MD5Utils.class);

	/**
	 * 字符串转32位MD5码(小写)
	 * @param sourceStr 字符串源码
	 * @return MD5结果值
	 */
	public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString().toLowerCase();
        } catch (NoSuchAlgorithmException e) {
        	log.error("转MD5码出错", e);
        }
        return result;
    }
    
	/**
	 * 字符串转16位MD5码(小写)
	 * @param sourceStr 字符串源码
	 * @return MD5结果值
	 */
	public static String byte16MD5(String sourceStr) {
        String result = "";
        String temp = MD5(sourceStr);
        result = temp.substring(8, 24);
        return result;
    }
	
	/**
	 * 文件转32位MD5码(小写)
	 * @param file
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String MD5(File file) {
        FileInputStream fis = null;
        try {
        	MessageDigest md =  MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            //100KB each time
            byte[] buffer = new byte[102400];
            int length;
            long loopCount = 0;
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
                loopCount++;
            }
            return new String(Hex.encodeHex(md.digest())).toLowerCase();
        } catch (Exception e) {
        	log.error("md5 file文件转码出错," + file.getAbsolutePath(),e);
            return null;
        } finally {
            try {
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				log.error("md5 file 关闭流出错," + file.getAbsolutePath(),e);
			}
        }
    }
	
	public static String getMd5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("md5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 标准MD5加密
	 * 
	 * @param inStr
	 * @return
	 * @throws Exception
	 */
	public static String toMD5(String inStr) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(inStr.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					sb.append("0");
				sb.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			return null;
			//			e.printStackTrace();
		}
		return sb.toString().toUpperCase();
	}
	
	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args) {
		String cc = "agent_id=1956473&down_time=20150407115800&deal_user=天纵客服1|||e26deee1a796d51a";
		System.out.println(MD5(cc));
		
		String path = "E:/尹洋/test.xml";
		System.out.println("file:"+MD5(path));
	}

}
