package com.yinyang.yy.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;

/**
 * Http发送请求工具类
 * from b2b2c  
 * POST GET PUT
 * @Title: HttpUtil.java
 * @Package com.yinyang.yy.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午2:11:52
 * @version V1.0
 */
@SuppressWarnings("deprecation")
public class HttpUtil {
	private static final Log log = LogFactory.getLog(HttpUtil.class);
	private static final int sotimeout = 5000;
	private static final int connectTimeout = 1000;
	private static final String ENCODING_UTF_8 = "utf-8";

	/**
	 * 返回一个http request 的responseString
	 * 
	 * Content-Type =  form     application/x-www-form-urlencoded
	 * @param url
	 * @param parameters
	 *            要post的parameters
	 * @return string
	 * @throws Exception
	 */
	public static String postRequest(String url, Map<String, String> parameters) throws Exception {
		return postRequest(url, parameters, 0, null, null, ENCODING_UTF_8);
	}

	/**
	 * Content-Type =  form     application/x-www-form-urlencoded
	 * @param url
	 * @param parameters
	 * @param requestHeadMap
	 * @param body
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String postRequest(String url, Map<String, String> parameters, Map<String, String> requestHeadMap, String body, String encoding) throws Exception {
		return postRequest(url, parameters, 0, requestHeadMap, body, encoding);
	}

	/**
	 * Content-Type =  form     application/x-www-form-urlencoded
	 * @param url
	 * @param parameters
	 * @param timeoutmiliseconds
	 * @param requestHeadMap
	 * @param body
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String postRequest(String url, Map<String, String> parameters, int timeoutmiliseconds, Map<String, String> requestHeadMap, String body, String encoding) throws Exception {
		HttpClient client = new HttpClient();
		if (timeoutmiliseconds > 0) {
			HttpClientParams params = new HttpClientParams();
			params.setSoTimeout(timeoutmiliseconds);
			client.setParams(params);
		}
		client.getParams().setContentCharset(encoding);
		PostMethod post = null;
		post = new PostMethod(url);
		if (requestHeadMap != null) {
			Set<Map.Entry<String, String>> set = requestHeadMap.entrySet();
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				post.addRequestHeader(entry.getKey(), entry.getValue());
			}
		}

		if (ENCODING_UTF_8.equalsIgnoreCase(encoding)) {
			post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		} else {
			post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=GBK");
		}

		if (StringUtils.isNotBlank(body)) {
			// post.setRequestBody(body);
			post.setRequestEntity(new StringRequestEntity(body));
		}
		try {
			// 处理提交parameters
			Set<String> parameterkeySet = parameters.keySet();
			Iterator<String> parameterkeySetNames = parameterkeySet.iterator();
			while (parameterkeySetNames.hasNext()) {
				String parameterkey = parameterkeySetNames.next();
				if (parameterkey != null) {
					String parametervalue = parameters.get(parameterkey);
					if (parametervalue != null) {
						post.setParameter(parameterkey, parametervalue);
					}
				}
			}

			int result = client.executeMethod(post);
			if (reqFinish(result)) {
				InputStream inputStream = post.getResponseBodyAsStream();
				return getResponseAsString(inputStream, encoding);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			post.releaseConnection();
		}
	}

	/**
	 * post方式发送request请求[优化]
	 * 已优化 ！！！optimization
	 * Content-Type =  application/json  
	 * @param url
	 * @param parameters
	 * @param timeoutmiliseconds
	 * @param requestHeadMap
	 * @param body
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String postRequestOpt(String url, Map<String, String> parameters, int timeoutmiliseconds, Map<String, String> requestHeadMap, String body, String encoding) throws Exception {
		
		PostMethod post = null;
		
		try {
			post = new PostMethod(url);
			HttpClient client = new HttpClient();
			if (timeoutmiliseconds > 0) {
				HttpClientParams params = new HttpClientParams();
				params.setSoTimeout(timeoutmiliseconds);
				client.setParams(params);
			}
			client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
			client.getParams().setContentCharset(encoding);
			
			/** 处理提交parameters   HeadMap */
			if (requestHeadMap != null) {
				Set<Map.Entry<String, String>> set = requestHeadMap.entrySet();
				for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
					post.addRequestHeader(entry.getKey(), entry.getValue());
				}
			}

			if (ENCODING_UTF_8.equalsIgnoreCase(encoding)) {
				//post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
				/**setRequestHeader  or  addRequestHeader应该没关系*/
				post.addRequestHeader("Content-Type", "application/json;UTF-8");
			} else {
				//post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=GBK");
				post.addRequestHeader("Content-Type", "application/json;GBK");
			}

			/** 处理提交parameters    ParamMap !!!注意与body顺序 */
			Set<String> parameterkeySet = parameters.keySet();
			Iterator<String> parameterkeySetNames = parameterkeySet.iterator();
			while (parameterkeySetNames.hasNext()) {
				String parameterkey = parameterkeySetNames.next();
				if (parameterkey != null) {
					String parametervalue = parameters.get(parameterkey);
					if (parametervalue != null) {
						post.setParameter(parameterkey, parametervalue);
					}
				}
			}
			
			if (StringUtils.isNotBlank(body)) {
				/**
				 * !!!!!  body取不到值，是空的  原因 是  application/是form 还是  json决定
				 * 		  body取到的有时候是map  是由顺序决定的！  这里 必须紧跟着 client.executeMethod(post);
				 * 				若   ParamMap  放在  post.setRequestBody(body);之后处理 则导致取到的值是map  而不是body
				 * 			HeadMap与body顺序无所谓 不影响
				 * ***/
				
				/** 第一种方式   直接获取到 body   eg: body 但是 收不到 paramMap  只   只能收到  url?后面的param*/
				post.setRequestBody(body);

				/** 第二种方式  直接获取到 body    eg: body 但是 收不到 paramMap  只   只能收到  url?后面的param*/
				//post.setRequestEntity(new StringRequestEntity(body));// 接收body没有数据？ app/json 设置后还是在map里面

				/**
				 *  第三种方式 接收值既包括 paramMap 也 包括  body(URL编码之后的值) eg: testPrarmMap=abc&data=body   
				 *  注意 第三种方式   ParamMap 与  body顺序不影响了，即使ParamMap放body后面处理
				 *  		只是  会返回   eg: data=body&testPrarmMap=abc
				 *  */
				/*((PostMethod) post).addParameter("data", body); 
				HttpMethodParams param = post.getParams(); 
				param.setContentCharset(encoding);*/
				 
			}
			
			int result = client.executeMethod(post);
			if (reqFinish(result)) {
				InputStream inputStream = post.getResponseBodyAsStream();
				
				String getResponse = getResponseAsStringByByteArray(inputStream, encoding);
				return getResponse;
				
				//return getResponseAsString(inputStream, encoding);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if(post != null){
				post.releaseConnection();
			}
		}
	}

	
	/**
	 * put方式 发送JSON数据请求
	 * 参照 shopify-web httpclientutil 
	 * @param url
	 * @param jsonStr
	 * @return
	 */
	public static String putJsonRequest(String url,String jsonStr){
		if(StringUtils.isBlank(url)) return null;
		HttpClient client = new HttpClient();
		client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		client.getParams().setContentCharset("UTF-8");
		PutMethod put = new PutMethod(url);
		
		if(StringUtils.isNotBlank(jsonStr)){
			put.setRequestHeader( "Content-type" , "application/json;utf-8" ); 
			put.setRequestBody(jsonStr);
		}
		String result = null;
		try {
			int code = client.executeMethod(put);
			if(code == HttpStatus.SC_OK){
				InputStream is = put.getResponseBodyAsStream();
				result = getResponseAsString(is, "UTF-8");
			}else{
				log.error("(putJsonRequest fail)code:"+code+",url:"+url+",jsonStr:"+jsonStr);
			}
		} catch (HttpException e) {
			log.error("(putJsonRequest fail)HttpException,url:"+url+",jsonStr:"+jsonStr);
			log.error(e);
		} catch (IOException e) {
			log.error("(putJsonRequest fail)IOException,url:"+url+",jsonStr:"+jsonStr);
			log.error(e);
		} catch(Exception e){
			log.error("(putJsonRequest fail)url:"+url+",jsonStr:"+jsonStr);
			log.error(e);
		}finally{
			put.releaseConnection();
		}
		return result;
	}
	
	/**
	 * POST方式发送JSON数据
	 * 参照 shopify-web httpclientutil
	 * @param url
	 * @param jsonStr
	 * @return
	 */
	public static String postJsonRequest(String url,String jsonStr){
		if(StringUtils.isBlank(url)) {
			return null;
		}
		HttpClient client = new HttpClient();
		client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		client.getParams().setContentCharset("utf-8");
		PostMethod post = new PostMethod(url);
		
		if(StringUtils.isNotBlank(jsonStr)){
			post.setRequestHeader( "Content-type" , "application/json;utf-8" ); 
			post.setRequestBody(jsonStr);
		}
		String result = null;
		try {
			int code = client.executeMethod(post);
			//if(code == HttpStatus.SC_CREATED){
			if(code == HttpStatus.SC_OK){
				InputStream is = post.getResponseBodyAsStream();
				result = getResponseAsString(is, "utf-8");
			}else{
				log.error("(postJsonRequest fail)code:"+code+",url:"+url+",jsonStr:"+jsonStr);
			}
		} catch (HttpException e) {
			log.error("(postJsonRequest fail)HttpException,url:"+url+",jsonStr:"+jsonStr);
			log.error(e);
		} catch (IOException e) {
			log.error("(postJsonRequest fail)IOException,url:"+url+",jsonStr:"+jsonStr);
			log.error(e);
		} catch(Exception e){
			log.error("(postJsonRequest fail)url:"+url+",jsonStr:"+jsonStr);
			log.error(e);
		}finally{
			post.releaseConnection();
		}
		return result;
	}
	
	/**
	 * use get method to return http request response String
	 * 
	 * @param url
	 * @return String
	 * @throws Exception
	 */
	public static String getRequest(String url) {
		if (url == null || "".equals(url))
			return "";
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset(ENCODING_UTF_8);
		GetMethod method = new GetMethod(url);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		method.setRequestHeader("Connection", "close");

		String retstring = null;
		try {
			int result = client.executeMethod(method);
			if (reqFinish(result)) {
				retstring = method.getResponseBodyAsString();
			}
		} catch (IOException e) {
			log.info("IOException :" + e.getMessage());
		} finally {
			method.releaseConnection();
		}
		return retstring;
	}

	/**
	 * 请求是否完成
	 * 
	 * @param result
	 * @return
	 */
	private static boolean reqFinish(int result) {
		return result == HttpStatus.SC_OK || result == HttpStatus.SC_BAD_REQUEST;
	}

	public static String getRequestWithTimeout(String url, Map<String, String> requestHeadMap, String encoding, int connTime, int soTime) {
		if (url == null || "".equals(url))
			return "";
		HttpClient client = new HttpClient();
		if (connTime == 0) {
			connTime = connectTimeout;
		}
		if (soTime == 0) {
			soTime = sotimeout;
		}
		client.getHttpConnectionManager().getParams().setConnectionTimeout(connTime);
		client.getHttpConnectionManager().getParams().setSoTimeout(soTime);
		client.getParams().setContentCharset(encoding);

		GetMethod method = new GetMethod(url);
		method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=GBK");
		method.addRequestHeader("Connection", "close");
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

		if (requestHeadMap != null) {
			Set<Map.Entry<String, String>> set = requestHeadMap.entrySet();
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				method.addRequestHeader(entry.getKey(), entry.getValue());
			}
		}
		String retstring = null;
		try {
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				retstring = method.getResponseBodyAsString();
				InputStream inputStream = method.getResponseBodyAsStream();
				retstring = getResponseAsString(inputStream, encoding);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
			retstring = "-1";
		} finally {
			method.releaseConnection();
		}
		return retstring;
	}

	public static InputStream getRequestInputStream(String url, Map<String, String> requestHeadMap, String encoding, int connTime, int soTime) {
		InputStream retStream = null;
		if (url == null || "".equals(url))
			return null;
		HttpClient client = new HttpClient();
		if (connTime == 0) {
			connTime = connectTimeout;
		}
		if (soTime == 0) {
			soTime = sotimeout;
		}
		client.getHttpConnectionManager().getParams().setConnectionTimeout(connTime);
		client.getHttpConnectionManager().getParams().setSoTimeout(soTime);

		client.getParams().setContentCharset(encoding);

		GetMethod method = new GetMethod(url);
		if (ENCODING_UTF_8.equalsIgnoreCase(encoding)) {
			method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		} else {
			method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=GBK");
		}
		method.addRequestHeader("Connection", "close");
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

		if (requestHeadMap != null) {
			Set<Map.Entry<String, String>> set = requestHeadMap.entrySet();
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				method.addRequestHeader(entry.getKey(), entry.getValue());
			}
		}

		try {
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				retStream = method.getResponseBodyAsStream();
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return retStream;
	}

	public static InputStream getRequestInputStream(String url) {
		if (url == null || "".equals(url))
			return null;

		GetMethod getMethod = new GetMethod(url);
		HttpClient client = new HttpClient();
		InputStream retStream = null;
		try {
			int result = client.executeMethod(getMethod);
			if (result == HttpStatus.SC_OK) {
				retStream = getMethod.getResponseBodyAsStream();
			}
		} catch (IOException e) {
			e.printStackTrace();
			retStream = null;
		}
		return retStream;
	}

	public static byte[] getRequestByte(String url) {
		if (url == null || "".equals(url))
			return null;
		GetMethod getMethod = new GetMethod(url);
		getMethod.setRequestHeader("Connection", "close");
		HttpClient client = new HttpClient();
		byte[] responseBody = null;
		try {
			int result = client.executeMethod(getMethod);
			if (result == HttpStatus.SC_OK) {
				responseBody = getMethod.getResponseBody();
			}
		} catch (IOException e) {
			e.printStackTrace();
			responseBody = null;
		}

		return responseBody;
	}

	/**
	 * 提交文件到URL，并返回结果
	 * 
	 * @param url
	 *            接收文件流的URL地址
	 * @param file
	 *            文件
	 * @return
	 */
	public static String postMultipart(String url, File file) {
		String result = null;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		try {
			client.getHttpConnectionManager().getParams().setConnectionTimeout(connectTimeout * 5);
			client.getHttpConnectionManager().getParams().setSoTimeout(sotimeout * 2);
			FilePart fp = new FilePart(file.getName(), file);
			MultipartRequestEntity mrp = new MultipartRequestEntity(new Part[] { fp }, post.getParams());
			post.setRequestEntity(mrp);
			int rst = client.executeMethod(post);
			if (rst == HttpStatus.SC_OK) {
				InputStream inputStream = post.getResponseBodyAsStream();
				result = getResponseAsString(inputStream, "UTF-8");
				log.debug(result);
			} else {
				log.error("postMultipart url: " + url + " | httpstatus: " + rst);
			}
		} catch (Exception e) {
			log.error("上传图片异常：", e);
		} finally {
			post.releaseConnection();
		}
		return result;
	}

	private static String getResponseAsString(InputStream is, String encoding) throws UnsupportedEncodingException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
		FileCopyUtils.copy(is, baos);
		return baos.toString(encoding);

	}
	
	public static String getResponseAsStringByByteArray(InputStream is, String encoding) throws IOException {
		if(is == null) return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString(encoding);
	}
}
