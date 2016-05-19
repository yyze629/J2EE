<%-- <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link href="http://www.dhresource.com/2008/web20/seller/css/seller.css" rel="stylesheet" type="text/css" />
	<link href="http://www.dhresource.com/dhs/mos/css/public/common20140922.css" rel="stylesheet" type="text/css" />
	<link href="http://www.dhresource.com/2008/web20/seller/css/general_popup_box.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="http://www.dhresource.com/seller/js/common.js"></script>
    <title>错误页面</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>

	<div class="main-500">
		<h3>很抱歉，您本次访问的网页出现问题，无法显示。</h3>
		<div class="box">
	        <dl>
	            <dt>请可以尝试以下操作：</dt>
	            <dd>
	                <ul>
	                    <li>按F5或者菜单上的<a href="#">刷新</a>按钮，刷新重试一次。</li>
	                    <li><a href="javascript:history.back()">返回到上一页</a>。</li>
	                    <li>转到 <a href="http://seller.dhgate.com">seller.dhgate.com</a> 首页。  </li>
	                </ul>
	            </dd>
	        </dl>
	    </div>
	    <span id="errorID" style="color:white;"></span>
	    
	    <textarea name="error" id="error" style="display:none;"><s:property value="exception"/>
	    <%
	    	String errorShort = (String)request.getAttribute("errorShort");
	    	out.print(errorShort);
	     %>
		</textarea>
		<textarea name="errorMsg" id="errorMsg" style="display:none;"><s:property value="exception.message"/></textarea>
		
		<textarea name="exceptionStack" id="exceptionStack" style="display:none;"><s:property value="exceptionStack"/>
		<%
	    	String errorStack = (String)request.getAttribute("errorStack");
	    	out.print(errorStack);
	     %>
		</textarea>
	    
	</div>
  </body>
</html>
 --%>