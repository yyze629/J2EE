package com.yinyang.sellerpay.web.action;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.dhgate.common.util.DHgateSession;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.dhgate.merchant.dto.UserSessionDTO;
import com.dhgate.skeleton.web.action.BaseAction;
import com.yinyang.sellerpay.web.service.PayEaseDelegate;
import com.yinyang.sellerpay.web.util.AppConstant;
import com.yinyang.sellerpay.web.util.Constant;
import com.yinyang.sellerpay.web.util.DHDiamondManager;
import com.yinyang.sellerpay.web.util.DHgateSellerSessionFactory;
import com.yinyang.sellerpay.web.util.ProdManageConstant;

/***
 * 
* @Description: TODO
*
* @author baixingang
* @version 1.0
* @create time 2013-7-1 上午10:30:29
 */
public class SellerPayBaseAction extends BaseAction {
	private static final long serialVersionUID = 8113261741491629552L;
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected Map<String, Object> session;
	protected Integer selectpagesize;
	protected Integer page;
	protected String dhpath;
	protected final Log logger = LogFactory.getLogger(SellerPayBaseAction.class);
	
	@Autowired
	PayEaseDelegate productDelegate;

	public SellerPayBaseAction() {
	}
	

	/**
	 * 从本系统的cache里取supplierID
	 * 
	 * @return
	 */
	protected String getSupplierId() {
		DHgateSession session = (DHgateSession) DHgateSellerSessionFactory.getDHgateMinimalSession(request, response);
		String minimalSessionString = (String) session.getAttribute(Constant.DHgateMinimalSession);
		return getMinimalSessionField("supplierid", minimalSessionString);
	}
	

	/**
	 * Convenience method to get the request
	 * 
	 * @return current request
	 */
	protected HttpServletRequest getRequest() {
		return this.request;
	}

	/**
	 * Convenience method to get the response
	 * 
	 * @return current response
	 */
	protected HttpServletResponse getResponse() {
		return this.response;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		NumberFormat number = NumberFormat.getInstance();
		number.setMinimumFractionDigits(2);
		number.setMaximumFractionDigits(2);
		number.setRoundingMode(RoundingMode.HALF_UP);
		this.request.setAttribute("number", number);
		this.setAppParameters(request);
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void put(String key, Object obj) {
		request.setAttribute(key, obj);
	}
	
	
	private void setAppParameters(HttpServletRequest request){
		if(request != null){
			NumberFormat number = NumberFormat.getInstance();
			number.setMinimumFractionDigits(2);
			number.setMaximumFractionDigits(2);
			number.setRoundingMode(RoundingMode.HALF_UP);
			request.setAttribute("number", number);
			
			DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
			decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
			request.setAttribute("decimalFormat", decimalFormat);
			
			request.setAttribute("dhresource", AppConstant.DH_Resource);
			request.setAttribute("dhresource_js", AppConstant.DH_Resource_JS);
			request.setAttribute("dhresource_css", AppConstant.DH_Resource_CSS);
			request.setAttribute("appimage", AppConstant.APP_IMAGE);
			request.setAttribute("appsupplierdomainname", AppConstant.DHGATE_SELLER_DOMAIN);
			
			//request.setAttribute("jsversion", "2015-03-23");
			
			// request.setAttribute("isDebug", AppConstant.IS_DEBUG_STATUS);
			request.setAttribute("isDebug", false);
			
			request.setAttribute("appseller", AppConstant.DHGATE_SELLER_DOMAIN);
			request.setAttribute("appbuyer", AppConstant.DHGATE_BUYER_DOMAIN);
			
			request.setAttribute("apprubuyer", AppConstant.DHGATE_BUYER_RU_DOMAIN);
			request.setAttribute("appdebuyer", AppConstant.DHGATE_BUYER_DE_DOMAIN);
			request.setAttribute("appfrbuyer", AppConstant.DHGATE_BUYER_FR_DOMAIN);
			request.setAttribute("appesbuyer", AppConstant.DHGATE_BUYER_ES_DOMAIN);
			request.setAttribute("appitbuyer", AppConstant.DHGATE_BUYER_IT_DOMAIN);
			request.setAttribute("appptbuyer", AppConstant.DHGATE_BUYER_PT_DOMAIN);
			
		}
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


	public Integer getSelectpagesize() {
		return selectpagesize;
	}
	public void setSelectpagesize(Integer selectpagesize) {
		this.selectpagesize = selectpagesize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getDhpath() {
		return dhpath;
	}
	public void setDhpath(String dhpath) {
		this.dhpath = dhpath;
	}
	
}
