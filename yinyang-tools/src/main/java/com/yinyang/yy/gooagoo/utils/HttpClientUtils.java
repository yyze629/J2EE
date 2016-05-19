package com.yinyang.yy.gooagoo.utils;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
//import org.apache.http.entity.mime.MultipartEntity;
//import org.apache.http.entity.mime.content.ByteArrayBody;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import com.yinyang.yy.common.YYException;
import com.yinyang.yy.common.exception.PlatformException;

/**
 * HttpClient工具类
 * 
 * @author Administrator
 *
 */
public abstract class HttpClientUtils
{
    /**
     * HTTP GET请求
     * 
     * @param reqURL
     * @return
     * @throws YYException
     */
    public static String sendGetRequest(String reqURL) throws YYException
    {
        AssertUtils.notEmpty(reqURL, "【HttpClientUtils-sendGetRequest】传入参数reqURL为空");
        String respContent = null; //响应内容  
        HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例  
        HttpGet httpGet = new HttpGet(reqURL); //创建org.apache.http.client.methods.HttpGet  
        try
        {
            HttpResponse response = httpClient.execute(httpGet); //执行GET请求  
            HttpEntity entity = response.getEntity(); //获取响应实体  
            if (null != entity)
            {
                respContent = EntityUtils.toString(entity, "UTF-8");

            }
        }
        catch (ClientProtocolException e)
        {
            throw new PlatformException("【HttpClientUtils-sendGetRequest】协议异常", e);
        }
        catch (IOException e)
        {
            throw new PlatformException("【HttpClientUtils-sendGetRequest】网络异常,如HTTP服务器未启动等", e);
        }
        finally
        {
            httpClient.getConnectionManager().shutdown();
        }
        return respContent;
    }

    /**
     * HTTP POST请求
     * @param reqURL
     * @param reqData
     * @return
     */
   /* public static String sendPostRequest(String reqURL, HashMap<String, String> reqData) throws STException
    {
        AssertUtils.notEmpty(reqURL, "【HttpClientUtils-sendPostRequest】传入参数reqURL为空");
        AssertUtils.notNull(reqData, "【HttpClientUtils-sendPostRequest】传入参数reqData为NULL");
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Integer.valueOf(Configuration.CONNECTION_TIMEOUT));
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, Integer.valueOf(Configuration.SOCKET_TIMEOUT));
        HttpPost httpPost = new HttpPost(reqURL);
        try
        {
            if (null != reqData && reqData.size() > 0)
            {
                List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                for (String key : reqData.keySet())
                {
                    formParams.add(new BasicNameValuePair(key, reqData.get(key)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
            }
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity)
            {
                return EntityUtils.toString(entity, "UTF-8");
            }
            else
            {
                throw new PlatformException("【HTTP POST请求】返回数据为NULL");
            }
        }
        catch (ConnectTimeoutException cte)
        {
            throw new PlatformException("【HTTP POST请求】连接超时", cte);
        }
        catch (SocketTimeoutException ste)
        {
            throw new PlatformException("【HTTP POST请求】读取超时", ste);
        }
        catch (Exception e)
        {
            throw new PlatformException("【HTTP POST请求】通信异常", e);
        }
        finally
        {
            httpClient.getConnectionManager().shutdown();
        }
    }*/

    /**
     * HTTP 上传文件
     * @param reqURL 账单系统URL
     * @param _file 账单，订单文件
     * @param mac 转以器mac
     * @return
     */
    /*public static String upload(String reqURL, File _file) throws STException
    {
        AssertUtils.notEmpty(reqURL, "【HttpClientUtils-upload】传入参数reqURL为空");
        AssertUtils.notNull(_file, "【HttpClientUtils-upload】传入参数_file为NULL");
        HttpClient httpclient = null;
        try
        {
            BasicHttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 5 * 1000);
            HttpConnectionParams.setSoTimeout(httpParams, 5 * 1000);
            httpclient = new DefaultHttpClient(httpParams);
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials("root", "system");
            credsProvider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), creds);
            ((DefaultHttpClient) httpclient).setCredentialsProvider(credsProvider);
            HttpPost httppost = new HttpPost(reqURL);
            FileBody file = new FileBody(_file);
            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("imgFile", file);
            reqEntity.addPart("fileName", new StringBody(_file.getName()));
            reqEntity.addPart("imagePath", new StringBody("pending/"));
            reqEntity.addPart("imagenum", new StringBody("1"));
            httppost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httppost);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode())
            {
                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null)
                {
                    return EntityUtils.toString(httpEntity, "UTF-8");
                }
                else
                {
                    throw new PlatformException("【HTTP POST请求】返回数据为NULL");
                }
            }
            else
            {
                throw new PlatformException("【HTTP POST请求】上传文件失败");
            }
        }
        catch (Exception e)
        {
            throw new PlatformException("【HTTP 上传文件】上传文件异常", e);
        }
        finally
        {
            if (httpclient != null)
            {
                httpclient.getConnectionManager().shutdown();
            }
        }
    }*/

    //测试上传流
    /**
     * HTTP 上传文件
     * @param reqURL 账单系统URL
     * @param _file 账单，订单文件
     * @param mac 转以器mac
     * @param fileName 文件名
     * @return
     */
    /*public static String upload(String reqURL, byte[] data, String mac, String fileName) throws STException, ClientProtocolException, IOException
    {
        AssertUtils.notEmpty(reqURL, "【HttpClientUtils-upload】传入参数reqURL为空");
        AssertUtils.notNull(data, "【HttpClientUtils-upload】传入参数data数据NULL");
        AssertUtils.notNull(mac, "【HttpClientUtils-upload】传入参数mac为NULL");
        AssertUtils.notNull(fileName, "【HttpClientUtils-upload】传入参数fileName为NULL");
        HttpClient httpclient = null;
        try
        {
            BasicHttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 5 * 1000);
            HttpConnectionParams.setSoTimeout(httpParams, 5 * 1000);
            httpclient = new DefaultHttpClient(httpParams);
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials("root", "system");
            credsProvider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), creds);
            ((DefaultHttpClient) httpclient).setCredentialsProvider(credsProvider);
            HttpPost httppost = new HttpPost(reqURL);
            MultipartEntity reqEntity = new MultipartEntity();

            ByteArrayBody fileBytes = new ByteArrayBody(data, fileName);
            httppost.setEntity(reqEntity);

            HttpResponse response = null;
            //try
            // {
            reqEntity.addPart("imgFile", fileBytes);
            reqEntity.addPart("fileName", new StringBody(fileName));
            reqEntity.addPart("mac", new StringBody(mac));
            reqEntity.addPart("imagePath", new StringBody("pending/"));
            reqEntity.addPart("imagenum", new StringBody("1"));

            response = httpclient.execute(httppost);
            // }
             
             catch (ClientProtocolException e)
             {
                 throw e;
             }
             catch (IOException e)
             {

                 throw e;
             }
            

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode())
            {
                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null)
                {
                    try
                    {
                        return EntityUtils.toString(httpEntity, "UTF-8");
                    }
                    catch (ParseException e)
                    {
                        throw new PlatformException("【HTTP POST请求】返回数据解析异常，" + e.getMessage());
                    }
                    catch (IOException e)
                    {
                        throw new PlatformException("【HTTP POST请求】返回数据IO异常，" + e.getMessage());
                    }
                }
                else
                {
                    throw new PlatformException("【HTTP POST请求】返回数据为NULL");
                }

            }
            else
            {
                throw new PlatformException("【HTTP POST请求】上传文件失败,服务端状态码是" + response.getStatusLine().getStatusCode());
            }
        }
        finally
        {
            if (httpclient != null)
            {
                httpclient.getConnectionManager().shutdown();
            }
        }
    }*/
}