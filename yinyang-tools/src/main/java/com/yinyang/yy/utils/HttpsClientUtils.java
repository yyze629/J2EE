package com.yinyang.yy.utils;

import java.io.InputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.*;
import java.net.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/** 
 * https协议工具类
 * @author  作者：yinyang E-mail: 
 * @date 创建时间：2015年5月20日 下午3:12:44 
 * @version 1.0  
 */
public class HttpsClientUtils {
	
	private static Log log = LogFactory.getLog(HttpsClientUtils.class);
	
	/**
	 * https上传/下载
	 * @return  返回xml
	 */
	@SuppressWarnings("deprecation")
	public static String httpsPostMethod(String url, String contentString) {
		String result = "";
		SSLContext sc=null;
		try {
			sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
			System.setProperty("java.protocol.handler.pkgs", "javax.net.ssl");
			//HTTPS hostname wrong
			HostnameVerifier hv = new HostnameVerifier() {
				public boolean verify(String urlHostName, SSLSession session) {
					return urlHostName.equals(session.getPeerHost());
				}
			};
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			URL console = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
			conn.setConnectTimeout(30000);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			DataOutputStream dout = new DataOutputStream(conn.getOutputStream());
			if (contentString != null)
				dout.writeBytes(contentString);
			dout.flush();
			dout.close();
			InputStream is = conn.getInputStream();
			DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = indata.readLine();
				if (ret != null && !ret.trim().equals("")) {
					result = result + new String(ret.getBytes("ISO-8859-1"), "GBK");
				}
			}
			conn.disconnect();
		} catch (Exception e) {
			log.error("Https协议下post方式上传/下载异常："+result+",请求url:["+url+"],参数:["+contentString+"]",e);
		}
		return result;
	}
	
	private static class TrustAnyTrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}
	@SuppressWarnings("unused")
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			// TODO Auto-generated method stub
			return true;
		}
	}
}
