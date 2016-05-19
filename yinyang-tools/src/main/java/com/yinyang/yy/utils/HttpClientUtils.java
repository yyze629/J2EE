package com.yinyang.yy.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/** 
 * http协议工具类
 * @author  作者：yinyang E-mail: 
 * @date 创建时间：2015年5月20日 下午3:12:44 
 * @version 1.0  
 */
public class HttpClientUtils {
	
	private static final String CONTENT_CHARSET = "GB2312";
	private static Log log = LogFactory.getLog(HttpClientUtils.class);
	
	/**
	 * get方式下载/上传
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public  static  String  getMethod(String url) throws UnsupportedEncodingException{
		if(url==null || "".equals(url)){
			log.warn("get方式请求url为空!");
			return null;
		}
		log.debug("get方式请求url:["+url+"]");
		
	    HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = httpConnectionManager.getParams();
        params.setConnectionTimeout(30000); 
        params.setSoTimeout(30000); 
        params.setDefaultMaxConnectionsPerHost(32);//very important!! 
        params.setMaxTotalConnections(50);//very important!! 
       
        HttpClient client = new HttpClient(httpConnectionManager); 
	    GetMethod get = new GetMethod(url);
		//设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
	    get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler()); 
		//执行getMethod
		int statusCode = 0;
		StringBuffer resBuffer = new StringBuffer("");
		try {
			statusCode = client.executeMethod(get);
			if (statusCode != HttpStatus.SC_OK) {
			    resBuffer.append("Method failed: " + get.getStatusLine());
			}else{
				 InputStream responseBody = get.getResponseBodyAsStream();
				 BufferedReader br = new BufferedReader(new InputStreamReader(responseBody,CONTENT_CHARSET));  
				 String resTemp = "";  
				 while((resTemp = br.readLine()) != null){  
					 resBuffer.append(resTemp);  
				 }
			}
		} catch (Exception e) {
			resBuffer.append(e.getMessage());
		}finally{
			get.releaseConnection();
		}
		log.info("get方式返回结果："+resBuffer.toString()+",请求url:["+url+"]");
		return resBuffer.toString();
	}
	
	/**
	 * post方式下载
	 * @param url
	 * @param jsonParamString
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String  postMethod(String url,String jsonParamString){  
	    HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = httpConnectionManager.getParams();
        params.setConnectionTimeout(30000); 
        params.setSoTimeout(30000); 
        params.setDefaultMaxConnectionsPerHost(32);//very important!! 
        params.setMaxTotalConnections(50);//very important!! 
        
        HttpClient client = new HttpClient(httpConnectionManager); 
        PostMethod post = new PostMethod(url);  
        post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, CONTENT_CHARSET);
        //post.addRequestHeader("Content-Type","text/html;charset="+CONTENT_CHARSET);
        //post.setRequestHeader("Content-Type", "text/html;charset="+CONTENT_CHARSET);
        post.setRequestBody(jsonParamString);    
        //post.setRequestEntity(new ByteArrayRequestEntity(param.toString().getBytes()));
        post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3,true)); 
        String result="";
		byte[] responseBody = null;
    	int statusCode = 0;
        try {
			    statusCode = client.executeMethod(post);
			    //System.out.println("statusCode " + statusCode);
			if (statusCode != HttpStatus.SC_OK) {
				result="Method failed: " + post.getStatusLine();
			}else{
				responseBody = post.getResponseBody();
				result=new String(responseBody,CONTENT_CHARSET);
			    BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(),CONTENT_CHARSET));  
			    StringBuffer stringBuffer = new StringBuffer();  
			    String str = "";  
			    while((str = reader.readLine())!=null){  
			        stringBuffer.append(str);  
			    }  
			    result = new String(stringBuffer.toString());  //stringBuffer.toString().getBytes(CONTENT_CHARSET),"gb2312"
			}
		} catch (Exception e) {
			result=e.getMessage();
		}finally{
			post.releaseConnection();
		}
        log.info("post方式下载返回结果："+result+",请求url:["+url+"],参数:["+jsonParamString+"]");
		return result;
    }  
	
	/*public static class UTF8PostMethod extends PostMethod{
	    public UTF8PostMethod(String url){
	        super(url);
	    }
	    @Override
	    public String getRequestCharSet() {
	        //return super.getRequestCharSet();
	        return "UTF-8";
	     }
	}*/
	
	/**
	 * post方式上传
	 * @param url
	 * @param jsonMap
	 * @return
	 */
	public static String uploadParam(String url,Map<String,String> jsonMap){
		//String url = "http://localhost:8080/IphoneTest/rece.do?method=receive";
		/*log.debug("get方式下载请求url:["+url+"]");
		
	    HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = httpConnectionManager.getParams();
        params.setConnectionTimeout(30000); 
        params.setSoTimeout(30000); 
        params.setDefaultMaxConnectionsPerHost(32);//very important!! 
        params.setMaxTotalConnections(50);//very important!! 
       
        HttpClient client = new HttpClient(httpConnectionManager); 
	    GetMethod get = new GetMethod(url);
		//设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
	    get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler()); 
		//执行getMethod
		int statusCode = 0;*/
		
		String result="";
		byte[] responseBody = null;
	    HttpClient client = new HttpClient();
	    PostMethod post = new PostMethod(url);
	    //填入各个表单域的值
	    NameValuePair[] data = { new NameValuePair("username", "username"),
	    new NameValuePair("passwd", "123456") };
	    //将表单的值放入postMethod中
	    post.setRequestBody(data);
	    try {
			//执行postMethod
			int statusCode = client.executeMethod(post);
			if (statusCode != HttpStatus.SC_OK) {
				result = "Method failed: " + post.getStatusLine();
			} else {
				responseBody = post.getResponseBody();
				result = new String(responseBody, CONTENT_CHARSET);
				BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(), CONTENT_CHARSET));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					stringBuffer.append(str);
				}
				result = new String(stringBuffer.toString()); //stringBuffer.toString().getBytes(CONTENT_CHARSET),"gb2312"
			}
		} catch (Exception e) {
			log.error("post方式上传异常："+result+",请求url:["+url+"],参数:["+jsonMap+"]",e);
		}
	    log.info("post方式上传返回结果："+result+",请求url:["+url+"],参数:["+jsonMap+"]");
		return result;
	}
	
	/*public static String uploadParam(String url,Map<String,String> jsonMap){
		//String url = "http://localhost:8080/IphoneTest/rece.do?method=receive";
		String result="";
		byte[] responseBody = null;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		//填入各个表单域的值
		NameValuePair[] data = { new NameValuePair("username", "username"),
				new NameValuePair("passwd", "123456") };
		//将表单的值放入postMethod中
		post.setRequestBody(data);
		try {
			//执行postMethod
			int statusCode = client.executeMethod(post);
			if (statusCode != HttpStatus.SC_OK) {
				result = "Method failed: " + post.getStatusLine();
			} else {
				responseBody = post.getResponseBody();
				result = new String(responseBody, CONTENT_CHARSET);
				BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(), CONTENT_CHARSET));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					stringBuffer.append(str);
				}
				result = new String(stringBuffer.toString()); //stringBuffer.toString().getBytes(CONTENT_CHARSET),"gb2312"
			}
		} catch (Exception e) {
			log.error("post方式上传异常："+result+",请求url:["+url+"],参数:["+jsonMap+"]",e);
		}
		log.info("post方式上传返回结果："+result+",请求url:["+url+"],参数:["+jsonMap+"]");
		return result;
	}*/
}
