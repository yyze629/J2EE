package com.yinyang.sellerpay.web.action.paye;

import java.io.IOException;
import java.io.PrintWriter;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.yinyang.sellerpay.web.action.SellerPayBaseAction;
import com.yinyang.sellerpay.web.form.CallbackBusinessForm;
import com.yinyang.sellerpay.web.service.PayEaseDelegate;
import com.yinyang.sellerpay.web.util.PayEaseConstant;
/**
 * 业务回调处理Action
 * @author baixingang
 *
 */
@ParentPackage("default")
@Namespace("/")
public class CallbackBusinessAction extends SellerPayBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5208689932973757679L;
	private static final Log log = LogFactory.getLogger(CallbackBusinessAction.class);
	
	private CallbackBusinessForm callbackForm;
	
	@Action(value = "callbackBusiness", results = { 
			@Result(name = "success", type = "velocity", location = "/content/callback/callback.vm")
			})
	public String callbackBusiness() throws Exception{
		String dispatcheAction = SUCCESS;
		try {
			
			String supplierid = this.getSupplierId();
			
			if(callbackForm == null){
				callbackForm = new CallbackBusinessForm();
			}
			callbackForm.setSupplierId(supplierid);
			callbackForm.setRemarks(PayEaseConstant.getSPTipsContent(callbackForm.getRemarks()));
			log.info("Callback{supplierid:"+supplierid+",orderNumber:"+callbackForm.getOrderNumber()+"}:CallBackURL="+callbackForm.getCallBackURL());
			log.info("Callback{supplierid:"+supplierid+",orderNumber:"+callbackForm.getOrderNumber()+"}:orderNumber="+callbackForm.getOrderNumber());
			log.info("Callback{supplierid:"+supplierid+",orderNumber:"+callbackForm.getOrderNumber()+"}:status="+callbackForm.getStatus());
			log.info("Callback{supplierid:"+supplierid+",orderNumber:"+callbackForm.getOrderNumber()+"}:remarks="+callbackForm.getRemarks());
			request.setAttribute("callBackForm", callbackForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			throw e;
			
		}
		return dispatcheAction;
	}
	
	@SuppressWarnings("unused")
	private void outJson(Object object) {
        PrintWriter out = null;
        if (object == null) {
            try {
                out = response.getWriter();
                out.println("null");
            } catch (IOException e) {
                log.error(e);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(object);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        try {
            out = response.getWriter();
            out.println(jsonObject);
        } catch (IOException e) {
            log.error(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

	public CallbackBusinessForm getCallbackForm() {
		return callbackForm;
	}

	public void setCallbackForm(CallbackBusinessForm callbackForm) {
		this.callbackForm = callbackForm;
	}
	

	
}
