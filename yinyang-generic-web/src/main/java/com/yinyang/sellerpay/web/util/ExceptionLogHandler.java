package com.yinyang.sellerpay.web.util;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts2.ServletActionContext;

import com.dhgate.common.util.DHgateSession;
import com.dhgate.excption.adapter.BizExceptionAdapter;
import com.dhgate.excption.adapter.ExceptionMsg;
import com.dhgate.excption.adapter.util.Platform;
import com.dhgate.excption.adapter.util.SearchIP;
import com.dhgate.excption.adapter.util.StringUtil;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionLogHandler extends AbstractInterceptor {
    private static final long serialVersionUID = 1008901298342362080L;
    private static final Log logger = LogFactory.getLogger(ExceptionLogHandler.class);

    /**
     * 用于struts2.*拦截器异常日志处理
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
       try {
           String result = invocation.invoke();
           return result;
       } catch (Exception e) {
           HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
           HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
           
           Throwable sysT = (Throwable) request.getAttribute(Globals.EXCEPTION_KEY);
           StringWriter s = new StringWriter();
           String errStr = null;
           
           if(sysT == null){
               sysT = e;
           }
           if (sysT != null) {
                               sysT.printStackTrace(new PrintWriter(s));
                               errStr = s.toString();
                               sysT.printStackTrace();
                               
                               request.setAttribute("errorShort", sysT.toString());
                               request.setAttribute("errorStack", errStr);
                               
                               //获取header信息
                               Enumeration<String> headers = request.getHeaderNames();
                               StringBuffer headerStrBuf = new StringBuffer();
                               while (headers.hasMoreElements()) {
                                   String headername = headers.nextElement();
                                   String header = request.getHeader(headername);
                                   headerStrBuf.append(headername).append(": ").append(header).append("\r\n");
                               }
                               //获取参数
                               Enumeration<String> parameters = request.getParameterNames();
                               StringBuffer bodyStrBuf = new StringBuffer();
                               while (parameters.hasMoreElements()) {
                                   String parameterName = parameters.nextElement();
                                   String parameter = request.getParameter(parameterName);
                                   bodyStrBuf.append(parameterName).append("=").append(parameter).append("&");
                               }
                   //获取服务器ip
                   String serverIp = showAllIPs();
                   //获取sellerid
                   DHgateSession session = (DHgateSession) DHgateSellerSessionFactory.getDHgateMinimalSession(request, response);
                                     String minimalSessionStr = (String) session.getAttribute("DHgateMinimalSession");
                                     String sellerId = DHgateSellerSessionFactory.getMinimalSessionField("supplierid", minimalSessionStr);
               
                                     //监控异常封装对象
                                     ExceptionMsg expMsg = new ExceptionMsg();
                                     expMsg.setError(errStr);
                                     expMsg.setErrorShort(sysT.toString());
                                     expMsg.setErrorUrl(request.getRequestURL().toString());
                                     expMsg.setErrorHeader(headerStrBuf.toString());
                                     expMsg.setErrorBody(StringUtil.escapeXssLittleWithBracket(bodyStrBuf.toString()));
                                     expMsg.setErrorServerip(serverIp);
                                     expMsg.setUserreport(0l);
                                     expMsg.setPlatForm(Platform.Prodmanage);
                                     expMsg.setIp(SearchIP.getIpAddr(request));
                                     expMsg.setOrgid(sellerId);
                                     expMsg.setCreatedate(new Date());
                                     //发送
                                     try {
                                               BizExceptionAdapter.sendExcepton(expMsg);
                                     } catch (Exception ex) {
                    String actionName = invocation.getInvocationContext().getName();
                    logger.error("invoke actionName error: " + actionName);
                    logger.error(e);
                           }
           }
           return "error";  //与<global-results>中定义的异常转接路径名称
       }
    }
    
    
    /**
     * 取本机真实IP地址
     * 
     * @param request
     * @return
     */
    public static String showAllIPs() {
        NetworkInterface nif;
        Enumeration em;
        StringBuffer buffer = new StringBuffer();
        try {
            em = NetworkInterface.getByName("localhost").getNetworkInterfaces();
            while (em.hasMoreElements()) {
                nif = (NetworkInterface) em.nextElement();
                Enumeration me = nif.getInetAddresses();
                while (me.hasMoreElements()) {
                    String ip = me.nextElement().toString();
                    if (ip.contains("127.0.0.1")) {
                        continue;
                    }
                    if (ip.contains(".")) {
                        buffer.append(ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return buffer.substring(1);
    }
}

