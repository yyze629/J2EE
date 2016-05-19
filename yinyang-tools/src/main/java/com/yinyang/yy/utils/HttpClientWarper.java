package com.yinyang.yy.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 一个httpclient包装类，该包装使得httpclient可以接受来自任何站点的ssl证书。
 * @Title: HttpClientWarper.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午4:25:17
 * @version V1.0
 */
@SuppressWarnings({"deprecation"})
public class HttpClientWarper implements HttpClient {

    public static final Logger log = LoggerFactory.getLogger(HttpClientWarper.class);

    private final HttpClient   httpClient; 

    public HttpClientWarper(HttpClient httpClient) {
        if (httpClient == null) {
            throw new IllegalArgumentException("httpClient can not be null!");
        }
        this.httpClient = httpClient;
        warpClient();
    }

    /**
     * 重新包装默认的httpclient，允许httpclient接受任何ssl证书。
     * 
     * @param httpClient
     * @return
     */
    private HttpClient warpClient() {
        TrustManager easyTrustManager = new X509TrustManager() {

          // @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
                // 一律信任 huaiyu.du
            }

          //@Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
                // 一律信任 huaiyu.du
            }

           // @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        SSLContext sslcontext = null;;
        try {
            sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] {easyTrustManager}, null);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        } catch (KeyManagementException e) {
            log.error(e.getMessage(), e);
        }

        SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
        this.httpClient.getConnectionManager().getSchemeRegistry()
            .register(new Scheme("https", 443, sf));
        return this.httpClient;
    }

    public HttpParams getParams() {
        return httpClient.getParams();
    }

    public ClientConnectionManager getConnectionManager() {
        return httpClient.getConnectionManager();
    }

    public HttpResponse execute(HttpUriRequest request) throws IOException, ClientProtocolException {
        return httpClient.execute(request);
    }

    public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException,
        ClientProtocolException {
        return httpClient.execute(request, context);
    }

    public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException,
        ClientProtocolException {
        return httpClient.execute(target, request);
    }

    public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context)
        throws IOException, ClientProtocolException {
        return httpClient.execute(target, request, context);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler)
        throws IOException, ClientProtocolException {
        return httpClient.execute(request, responseHandler);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler,
        HttpContext context) throws IOException, ClientProtocolException {
        return httpClient.execute(request, responseHandler, context);
    }

    public <T> T execute(HttpHost target, HttpRequest request,
        ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return httpClient.execute(target, request, responseHandler);
    }

    public <T> T execute(HttpHost target, HttpRequest request,
        ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException,
        ClientProtocolException {
        return httpClient.execute(target, request, responseHandler, context);
    }

}
