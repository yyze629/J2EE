package com.yinyang.sellerpay.web.util;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.dhgate.common.util.DHgateSession;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.dhgate.merchant.api.MerchantService;
import com.dhgate.sellerauth.dto.MyDhgatePageDto;
import com.dhgate.skeleton.web.util.CookieUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.yinyang.sellerpay.web.service.ApsarasServiceFactory;
import com.yinyang.sellerpay.web.service.SellerAuthDelegate;

/**
 * 安全检查拦截器。实现身份认证和授权检查两个功能。
 * 扩展MethodFilterInterceptor类，是为了提供excludeMethods功能，即对指定的方法名不进行拦截。
 * @author shengyangchun@dhgate.com
 * 
 */
public class SecurityInterceptor extends MethodFilterInterceptor {
    private static final long serialVersionUID = -8852694586628070811L;

    private static final Log logger = LogFactory.getLogger(SecurityInterceptor.class);
    private static String cssjsVersionInit;
	
    public static final String DHgateMinimalSession="DHgateMinimalSession";
    //@Autowired
    //SellerAuthDelegate sellerAuthDelegate;
    
    //private MerchantService merchantService = ApsarasServiceFactory.getMerchantService();
    
    @Override
	public String doIntercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		String sessionID; // seller系统种的cookie中存在的sessionid
		
		/*if(1==1)
			return null;*/
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		
		ctx.getValueStack().set("dhpath", request.getParameter("dhpath"));
		logger.info(request.getContextPath());
		postProcessDhpath(request,request.getContextPath(),request.getServletPath());
		
		/* 首先检查是否有cookie */
		sessionID = CookieUtil.getCookie(request, "dhc_s");
		if (StringUtils.isBlank(sessionID)) {
			logger.warn("Cookie doesn't exist,redirect to login page");
			
//			return Action.LOGIN;
			
			String returnURL = "http://seller.dhgate.com/usr/loginsign.do?returnURL="
					+ URLEncoder.encode(postProcessReturnUrl(request).toString(), "UTF-8");
			response.sendRedirect(returnURL);
			return null;
			
		}
		
		/* 根据cookie去seller的cache里取登录标识 */
		String sellerId = null;
		DHgateSession session = (DHgateSession) DHgateSellerSessionFactory.getDHgateMinimalSession(request, response);
		
		/***显示昵称*/
		UserSession userSession = (UserSession) (session.getAttribute(Constant.DHgateSyiSession));
    	if(userSession!=null && userSession.getNickname()!=null){
    		request.setAttribute("titlenickname" , userSession.getNickname()) ;
    	}
		
		String minimalSessionStr = (String) session.getAttribute(DHgateMinimalSession);
		if (minimalSessionStr == null || sessionID == null) {
			//跳转到登陆
			//			return Action.LOGIN;
			
			String returnURL = "http://seller.dhgate.com/usr/loginsign.do?returnURL="
					+ URLEncoder.encode(postProcessReturnUrl(request).toString(), "UTF-8");
			response.sendRedirect(returnURL);
			return null;
		}
		String suppliersubid ="";
		sellerId = DHgateSellerSessionFactory.getMinimalSessionField("supplierid", minimalSessionStr);
		suppliersubid = DHgateSellerSessionFactory.getMinimalSessionField("suppliersubid", minimalSessionStr);
		String nickname = DHgateSellerSessionFactory.getMinimalSessionField("domainname", minimalSessionStr);
		request.setAttribute("nickname" , nickname) ;

		/* seller的cache里没有登录标识，跳转到登录页 */
		if (StringUtils.isBlank(sellerId)) {
			logger.warn("Can't get the seller login flag which relate to cookie " + sessionID + " ,redirect to login page ");
//			return Action.LOGIN;
			
			postProcessReturnUrl(request);
			String returnURL = "http://seller.dhgate.com/usr/loginsign.do?returnURL="
					+ URLEncoder.encode(postProcessReturnUrl(request).toString(), "UTF-8");
			response.sendRedirect(returnURL);
			return null;
		}

		MyDhgatePageDto pageVo = null;
		
		
		String dhpath = (String)request.getAttribute("dhpath");
		if(dhpath==null || "".equals(dhpath)){
			 dhpath = "10001,21001,0202";
		}
		
		String path = request.getContextPath();
		
		return null;//url
		//TODO
		/*logger.info("校验权限的地址:"+path +"| 用户ID为：" +sellerId);
		boolean isUrlLimit = sellerAuthDelegate.isUrlpathLimit(sellerId, path);
		logger.info("isUrlLimit:"+isUrlLimit);
		if (isUrlLimit) {
			logger.info("没有权限访问，跳转摘要页");
			response.sendRedirect("http://seller.dhgate.com/mydhgate/index.do");
		}
		if (StringUtils.isNotBlank(suppliersubid)
	              && !merchantService.isAuthForSupplierSub(sellerId, suppliersubid, request.getRequestURI())) {
			return "subError";
		}*/

		//检查学生卖家权限。
		/*String supplierid = this.getSupplierId(request, response);
		Integer isStudentSupplier = 0;
		if(StringUtils.isNotBlank(supplierid)){
			if(this.sellerAuthDelegate.isStudentSupplier(supplierid)){
				isStudentSupplier = 1;
				if(this.isStudentSupplierForbidUrl(request.getContextPath(),request.getServletPath())){
					return "subError";
				}
			}
		}
		request.setAttribute("isStudentSupplier" , isStudentSupplier) ;
		if(request.getServletPath().indexOf("errorAction") != -1){
			if(isStudentSupplier == 1){
				request.setAttribute("errorMsg" , "您的账号无此操作权限") ;
			}else{
				request.setAttribute("errorMsg" , "无权限操作，请确认使用权限开通成功，或尝试直接点击导航下二级菜单") ;
			}
		}
		pageVo = sellerAuthDelegate.getMyDhgatePageDtoProd(sessionID, sellerId,suppliersubid, dhpath, null);
		
		CookieUtil.addCookie("dhpath_c", pageVo.getMenuPath(), "seller.dhgate.com", -1, "/",
				(HttpServletResponse) response);
		request.setAttribute("myDHgateHead", pageVo.getHead());
		request.setAttribute("myDHgateLeft", pageVo.getLeft());
		request.setAttribute("myDHgateCrumb", pageVo.getCrumb());
		request.setAttribute("myDHgateLastCrumb", pageVo.getLastCrumb());
		request.setAttribute("myDHgateFooter", pageVo.getFooter());
		String jsversion = this.getCssVersionVal();
		request.setAttribute("jsversion", jsversion);

		return invocation.invoke();*/

	}
    /**
	 * 从本系统的cache里取supplierID
	 * 
	 * @return
	 */
	protected String getSupplierId(HttpServletRequest request,HttpServletResponse response) {
		DHgateSession session = (DHgateSession) DHgateSellerSessionFactory.getDHgateMinimalSession(request, response);
		String minimalSessionString = (String) session.getAttribute(Constant.DHgateMinimalSession);
		return getMinimalSessionField("supplierid", minimalSessionString);
	}
	private String getMinimalSessionField(String field, String minimalSessionString) {
		String fieldVal = null;
		if (field != null && minimalSessionString != null) {
			String[] keyVals = minimalSessionString.split(String.valueOf((char) 28));
			if (keyVals != null && keyVals.length > 0) {
				for (String keyVal : keyVals) {
					if (keyVal != null && keyVal.trim().length() > 0) {
						String[] keyValArry = keyVal.split(String.valueOf((char) 29));
						if (keyValArry != null && keyValArry.length == 2) {
							if (field.equalsIgnoreCase(keyValArry[0])) {
								fieldVal = keyValArry[1];
								break;
							}
						}
					}
				}
			}
		}
		return fieldVal;
	}
    /**
	 * 
	* @Description: 获取URL
	*
	* @return String
	* @create time 2013-7-18 下午12:02:14
	 */
	public String postProcessReturnUrl(HttpServletRequest request){
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		
		String returnUrl ="http://seller.dhgate.com" + contextPath +servletPath;//数据智囊的首页
		
		return returnUrl;
	}
	private boolean isStudentSupplierForbidUrl(String ContextPath,String URL){
		boolean isForbidUrl = false;
		if(ContextPath.indexOf("prodmanage")!=-1){// 产品管理
			if(URL.indexOf("shelf/prodQuickShelf")!=-1){//快速上架产品
				isForbidUrl = true;
			}
			if(URL.indexOf("inventory/doQueryInventory.do")!=-1){//备货管理
				isForbidUrl = true;
			}
			if(URL.indexOf("attrDefect/prodAttrDefect.do")!=-1){//产品诊断
				isForbidUrl = true;
			}
			if(URL.indexOf("audit/prodViolate.do")!=-1){//疑似侵权
				isForbidUrl = true;
			}
			if(URL.indexOf("audit/prodBrandComplaint.do")!=-1){//品牌商投诉
				isForbidUrl = true;
			}
			if(URL.indexOf("audit/prodfreeze.do")!=-1){//冻结
				isForbidUrl = true;
			}
		}
		return isForbidUrl;
	}
    /**
	 * 
	* @Description: 后处理dhpath
	*
	* @param request
	* @param URL void
	* @create time 2013-7-11 下午8:13:40
	 */
	private void postProcessDhpath(HttpServletRequest request,String ContextPath,String URL){
		String dhpath = "";
		
		dhpath = (String)request.getAttribute("dhpath");
		if(dhpath==null || "".equals(dhpath)){
			if(ContextPath.indexOf("prodmanage")!=-1){// 产品管理
				if(URL.indexOf("shelf/prodShelf")!=-1){//上架产品
					dhpath = "10001,21001,0202";
				}
				if(URL.indexOf("shelf/prodGoldStall")!=-1){//黄金展位产品
					dhpath = "10004,2610";
				}
				if(URL.indexOf("shelf/prodQuickShelf")!=-1){//快速上架产品
					dhpath = "10001,21001,0208";
				}
				if(URL.indexOf("downshelf/prodDownShelf")!=-1){//下架产品
					dhpath = "10001,21001,0202";
				}
				if(URL.indexOf("audit/prodAudit")!=-1){//待审核的产品
					dhpath = "10001,21001,0202";
				}
				if(URL.indexOf("audit/prodViolate")!=-1){//疑似侵权产品
					dhpath = "10001,70,7002";
				}
				if(URL.indexOf("audit/prodAuditFail")!=-1){//审核未通过产品
					dhpath = "10001,21001,0202";
				}
				if(URL.indexOf("audit/prodBrandComplaint")!=-1){//品牌商投诉产品
					dhpath = "10001,70,7002";
				}
				if(URL.indexOf("group/prodGroup")!=-1){//产品组管理
					dhpath = "10001,21001,0209";
				}
				if(URL.indexOf("appeal/prodAppeal")!=-1){//申诉管理
					dhpath = "10001,0210";
				}
				if(URL.indexOf("serviceset/prodLocalReturn")!=-1){//海外退货服务设定
					dhpath = "10001,21002,2402";
				}
				if(URL.indexOf("serviceset/prodPledgeSet")!=-1){//诚信服务设定
					dhpath = "10001,21002,2401";
				}
				
				//金枪鱼
				if(URL.indexOf("gold/shelf/prodShelf")!=-1 || URL.indexOf("gold/shelf/prodGoldStall")!=-1){//上架产品、黄金展位[金枪鱼]
					dhpath = "10001,28,2802";
				}
				
				if(URL.indexOf("gold/downshelf/prodDownShelf")!=-1){//下架产品[金枪鱼]
					dhpath = "10001,28,2803";
				}
				
				if(URL.indexOf("inventory/doQueryInventory.do")!=-1){//备货管理
					dhpath = "10001,0212";
				}
				
			}
		}
		
		request.setAttribute("dhpath",dhpath);
	}
	/***
	 * 根据传入的key获取css版本信息
	 * */
	public String getCssVersionVal(){
		String version = DHDiamondManager.getDiamondPropertiesValue(ProdManageConstant.CSS_GROUP,ProdManageConstant.CSS_DATA_ID,ProdManageConstant.KEY_OF_JSCSSVERSION);
		String effectType = DHDiamondManager.getDiamondPropertiesValue(ProdManageConstant.CSS_GROUP,ProdManageConstant.CSS_DATA_ID,ProdManageConstant.KEY_OF_JSCSSEFFECT);
    	if(StringUtils.isNotEmpty(version)){
    		//立即生效
			if ("1".equals(effectType)) {
				cssjsVersionInit = version;
			} 
			//重启生效 赋值原有的本地内存值
			else if ("2".equals(effectType)) {
				if(StringUtils.isEmpty(cssjsVersionInit)){
					cssjsVersionInit = version;
	    		}
	    		version = cssjsVersionInit;
			} else{
				cssjsVersionInit = version;//默认立即生效
			}
    	}
    	//没有成功取得值或者没有值
    	if(StringUtils.isEmpty(version)){
    		version = cssjsVersionInit;
    	}
    	//version 仍为空，赋值 当前系统时间
    	if(StringUtils.isEmpty(version)){
    		version = String.valueOf(System.currentTimeMillis());
    		cssjsVersionInit = version;
    		logger.error("Get version error from Diamond and System.currentTimeMillis: " + version);
    	}
    	return version;
	}

   
}
