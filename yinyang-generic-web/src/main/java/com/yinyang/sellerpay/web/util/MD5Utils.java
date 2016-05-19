package com.yinyang.sellerpay.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

/** 
 * MD5工具类
 * 非常重要：tomcat必须与项目编码一致，否则项目发布到tomcat之后导致MD5生成出来不一致
 * 设置方法如：useBodyEncodingForURI="true" URIEncoding="UTF-8" 注意顺序
 * 同时eclipse -Preferences-general-workspace-text file encodeing 设置为UTF-8
 * @author  作者：yinyang E-mail: 
 * @date 创建时间：2015年7月30日 上午09:15:38 
 * @version 1.0  
 */
public class MD5Utils {

	private static Logger logger = Logger.getLogger(MD5Utils.class);
	
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
        	logger.error("转MD5码出错", e);
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
        	logger.error("md5 file文件转码出错," + file.getAbsolutePath(),e);
            return null;
        } finally {
            try {
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				logger.error("md5 file 关闭流出错," + file.getAbsolutePath(),e);
			}
        }
    }
	
}
