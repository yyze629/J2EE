package com.yinyang.yy.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * http client的工具类，用于取得某个url返回的内容 使用UTF-8的格式转码
 * from shopify
 * @Title: HttpClientUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午2:10:36
 * @version V1.0
 */
@SuppressWarnings("deprecation")
public class HttpClientUtil {
	
	private static final String UTF8 = "UTF-8";
	
	protected final static Log log = LogFactory.getLog(HttpClientUtil.class);

	/**
	 * 修改
	 * put方式
	 * @param url
	 * @param token
	 * @param jsonStr
	 * @return
	 */
	public static String putJsonRequest(String url,String token,String tokenHeadName, String jsonStr){
		if(StringUtils.isBlank(url) || StringUtils.isBlank(token)) return null;
		HttpClient client = new HttpClient();
		client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		client.getParams().setContentCharset(UTF8);
		PutMethod put = new PutMethod(url);
		if(StringUtils.isNotBlank(tokenHeadName)){
			put.setRequestHeader("X-"+tokenHeadName+"-Access-Token", token);
		}
		if(StringUtils.isNotBlank(jsonStr)){
			put.setRequestHeader( "Content-type" , "application/json;utf-8" ); 
			put.setRequestBody(jsonStr);
		}
		String result = null;
		try {
			int code = client.executeMethod(put);
			if(code == HttpStatus.SC_OK){
				InputStream is = put.getResponseBodyAsStream();
				result = getResponseAsString(is, UTF8);
			}else{
				log.error("(putJsonRequest fail)code:"+code+",url:"+url+",token:"+token+",jsonStr:"+jsonStr);
			}
		} catch (HttpException e) {
			log.error("(putJsonRequest fail)HttpException,url:"+url+",token:"+token+",jsonStr:"+jsonStr);
			log.error(e);
		} catch (IOException e) {
			log.error("(putJsonRequest fail)IOException,url:"+url+",token:"+token+",jsonStr:"+jsonStr);
			log.error(e);
		} catch(Exception e){
			log.error("(putJsonRequest fail)url:"+url+",token:"+token+",jsonStr:"+jsonStr);
			log.error(e);
		}finally{
			put.releaseConnection();
		}
		return result;
	}
	
	/**
	 * 删除
	 * @param url
	 * @param token
	 * @return
	 */
	public static String deleteJsonRequest(String url,String token,String tokenHeadName){
		if(StringUtils.isBlank(url) || StringUtils.isBlank(token)) return null;
		HttpClient client = new HttpClient();
		client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		client.getParams().setContentCharset(UTF8);
		DeleteMethod delete = new DeleteMethod(url);
		if(StringUtils.isNotBlank(tokenHeadName)){
			delete.setRequestHeader("X-"+tokenHeadName+"-Access-Token", token);
		}
		/*if(StringUtils.isNotBlank(jsonStr)){
			delete.setRequestHeader( "Content-type" , "application/json;utf-8" ); 
			//delete.setRequestBody(jsonStr);
		}*/
		String result = null;
		try {
			int code = client.executeMethod(delete);
			if(code == HttpStatus.SC_OK){
				InputStream is = delete.getResponseBodyAsStream();
				result = getResponseAsString(is, UTF8);
			}else{
				log.error("(deleteJsonRequest fail)code:"+code+",url:"+url+",token:"+token);
			}
		} catch (HttpException e) {
			log.error("(deleteJsonRequest fail)HttpException,url:"+url+",token:"+token);
			log.error(e);
		} catch (IOException e) {
			log.error("(deleteJsonRequest fail)IOException,url:"+url+",token:"+token);
			log.error(e);
		} catch(Exception e){
			log.error("(deleteJsonRequest fail)url:"+url+",token:"+token);
			log.error(e);
		}finally{
			delete.releaseConnection();
		}
		return result;
	}
	
	/**
	 * 新增create
	 * post方式
	 * @param url
	 * @param token
	 * @param jsonStr
	 * @return
	 */
	public static String postJsonRequest(String url,String token,String tokenHeadName,String jsonStr){
		if(StringUtils.isBlank(url) || StringUtils.isBlank(token)) {
			return null;
		}
		HttpClient client = new HttpClient();
		client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		client.getParams().setContentCharset(UTF8);
		PostMethod post = new PostMethod(url);
		if(StringUtils.isNotBlank(tokenHeadName)){
			post.setRequestHeader("X-"+tokenHeadName+"-Access-Token", token);
		}
		if(StringUtils.isNotBlank(jsonStr)){
			post.setRequestHeader( "Content-type" , "application/json;utf-8" ); 
			post.setRequestBody(jsonStr);
		}
		String result = null;
		try {
			int code = client.executeMethod(post);
			if(code == HttpStatus.SC_CREATED){
				InputStream is = post.getResponseBodyAsStream();
				Header h = post.getResponseHeader("X-Shopify-Shop-Api-Call-Limit");
				String v = h.getValue();
				System.out.println(v);
				
				result = getResponseAsString(is, UTF8);
			}else{
				log.error("(postJsonRequest fail)code:"+code+",url:"+url+",token:"+token+",jsonStr:"+jsonStr);
			}
		} catch (HttpException e) {
			log.error("(postJsonRequest fail)HttpException,url:"+url+",token:"+token+",jsonStr:"+jsonStr);
			log.error(e);
		} catch (IOException e) {
			log.error("(postJsonRequest fail)IOException,url:"+url+",token:"+token+",jsonStr:"+jsonStr);
			log.error(e);
		} catch(Exception e){
			log.error("(postJsonRequest fail)url:"+url+",token:"+token+",jsonStr:"+jsonStr);
			log.error(e);
		}finally{
			post.releaseConnection();
		}
		return result;
	}
	
	/**
	 * 查询
	 * get方式
	 * @param url
	 * @param token
	 * @return
	 */
	public static String getRequest(String url,String token,String tokenHeadName){
		if(StringUtils.isBlank(url) || StringUtils.isBlank(token)) return null;
		HttpClient httpclient = new HttpClient();
		httpclient.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		httpclient.getParams().setContentCharset(UTF8);
		GetMethod get = new GetMethod(url);
		if(StringUtils.isNotBlank(tokenHeadName)){
			get.setRequestHeader("X-"+tokenHeadName+"-Access-Token", token);
		}
		
		String result = null;
		try {
			int code = httpclient.executeMethod(get);
			if(code == HttpStatus.SC_OK){
				InputStream inputstream = get.getResponseBodyAsStream();
				result = getResponseAsString(inputstream, UTF8);
			}else{
				log.error("(getRequest fail)code:"+code+",url:"+url+",token:"+token);
			}
		} catch (HttpException e) {
			log.error("(getRequest fail)HttpException,url:"+url+",token:"+token);
			log.error(e);
		} catch (IOException e) {
			log.error("(getRequest fail)IOException,url:"+url+",token:"+token);
			log.error(e);
		} catch(Exception e){
			log.error("(getRequest fail)url:"+url+",token:"+token);
			log.error(e);
		}finally{
			get.releaseConnection();
		}
		return result;
	}
	
	public static String getResponseAsString(InputStream is, String encoding) throws IOException {
		if(is == null) return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString(encoding);
	}

	public static String postShopifyRequest(String url, Map<String, String> parameters, int timeoutmiliseconds, Map<String, String> requestHeadMap, String body, String encoding,String token) throws Exception {
		HttpClient client = new HttpClient();
		Map<String,Object> map = new HashMap<String,Object>();
		if (timeoutmiliseconds > 0) {
			HttpClientParams params = new HttpClientParams();
			params.setSoTimeout(timeoutmiliseconds);
			client.setParams(params);
		}
		client.getParams().setContentCharset(encoding);
		PostMethod post = null;
		post = new PostMethod(url);
		post.setRequestHeader("X-Shopify-Access-Token", token);
		if (requestHeadMap != null) {
			Set<Map.Entry<String, String>> set = requestHeadMap.entrySet();
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				post.addRequestHeader(entry.getKey(), entry.getValue());
			}
		}

		if (UTF8.equalsIgnoreCase(encoding)) {
			post.addRequestHeader("Content-Type", "application/json;utf-8");
		} else {
			post.addRequestHeader("Content-Type", "application/json;GBK");
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
			StatusLine statusLine = post.getStatusLine();
			int code = post.getStatusCode();
			map.put("code", code);
			map.put("statusLine", statusLine);
			map.put("result", "OK");
			if (reqFinish(result)) {
				String json = JSONSerializer.toJSON(map).toString();
				return json;
			} else {
				map.put("result", "请求未完成!");
				String json = JSONSerializer.toJSON(map).toString();
				return json;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			post.releaseConnection();
		}
	}
	
	/**
	 * 请求是否完成
	 * @param result
	 * @return
	 */
	private static boolean reqFinish(int result){
		return result == HttpStatus.SC_OK || result == HttpStatus.SC_BAD_REQUEST;
	}
	
	public static void main(String[] args) {
		String url = "https://dhgate-3.myshopify.com/admin/products.json";
		String token =  "274d33c932c04f3149a08a59bb7ce462";//
		JSONObject json = new JSONObject();
		json.put("title", "Burton Custom Freestlye 1510058");
		json.put("body_html", "<strong>testGood snowboard!</strong>");
		json.put("vendor", "Burton");
		json.put("product_type", "Snowboard");
		json.put("tags", "Barnes & Noble, John's Fav, \"Big Air\"");
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("product", json);
		String result = postJsonRequest(url, token,"Shopify", jsonStr.toString());
		System.out.println(result);
	}
}