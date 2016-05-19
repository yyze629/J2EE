package com.yinyang.yy.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 
 * from merchant
 * @Title: HttpFormpostClient.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午3:39:22
 * @version V1.0
 */
@SuppressWarnings({"deprecation","resource"})
public class HttpFormpostClient {
	private String httpurl;
	private List<NameValuePair> params = new ArrayList<NameValuePair>();

	public HttpFormpostClient(String httpurl) {
		this.httpurl = httpurl;
	}

	public void addUrlparams(String key, String value) {
		if (!"".equals(value))
			params.add(new BasicNameValuePair(key, value));
	}

	/**
	 * 利用httpclient执行post数据的操作
	 * @param readTimeout
	 * @return
	 */
	public String execute(int readTimeout) {
		String msg = wrapErrorMsg("save str connection problem");

		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, readTimeout);
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		try {
			HttpPost httppost = new HttpPost(httpurl);
			//添加参数
			httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = httpclient.execute(httppost);
			int stat = response.getStatusLine().getStatusCode();
			if (stat == 200) {
				HttpEntity rentity = response.getEntity();
				if (rentity != null) {
					msg = EntityUtils.toString(rentity);
				}
			} else {
				msg = wrapErrorMsg("save str http error" + stat);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.getConnectionManager().shutdown();
			} catch (Exception ignore) {
			}
		}
		return msg;
	}

	/**
	 * 利用httpclient执行post数据的操作
	 * @param readTimeout
	 * @return
	 */
	public String passportExecute(int readTimeout) {
		String msg = wrapErrorMsg("save str connection problem");

		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, readTimeout);
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		try {
			HttpPost httppost = new HttpPost(httpurl);
			//添加参数
			httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = httpclient.execute(httppost);
			int stat = response.getStatusLine().getStatusCode();
			if (stat == 200) {
				HttpEntity rentity = response.getEntity();
				if (rentity != null) {
					msg = wrapOkMsg(EntityUtils.toString(rentity));
				}
			} else {
				msg = wrapErrorMsg("save str http error" + stat);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.getConnectionManager().shutdown();
			} catch (Exception ignore) {
			}
		}
		return msg;
	}

	/**
	 * 封装返回消息
	 * @param msg
	 * @param msgcode
	 * @return
	 */
	private String wrapErrorMsg(String msg) {
		StringBuilder ret = new StringBuilder();
		return ret.append("{\"result\":\"0\", \"errorinfo\":\"").append(msg).append("\"}").toString();
	}

	private String wrapOkMsg(String msg) {
		StringBuilder ret = new StringBuilder();
		return ret.append("{\"result\":\"1\", \"passportId\":\"").append(msg).append("\"}").toString();
	}
}
