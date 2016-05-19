package com.yinyang.sellerpay.web.action.paye;

import java.io.IOException;
import java.io.PrintWriter;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.dhgate.common.metadata.ResultDto;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.yinyang.sellerpay.dao.api.dto.PayBusinessDTO;
import com.yinyang.sellerpay.dao.api.dto.PayOrderDTO;
import com.yinyang.sellerpay.web.action.SellerPayBaseAction;
import com.yinyang.sellerpay.web.form.SelectBankForm;
import com.yinyang.sellerpay.web.service.PayEaseDelegate;
import com.yinyang.sellerpay.web.util.PayEaseConstant;
import com.yinyang.sellerpay.web.validate.PayEaseValidate;
import com.yinyang.sellerpay.web.validate.ValidateResult;
/**
 * 支付方式选择处理Action
 * @author baixingang
 *
 */
//@ParentPackage("default")
//@Namespace("/")
public class SelectBankAction extends SellerPayBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5208689932973757679L;
	private static final Log log = LogFactory.getLogger(SelectBankAction.class);
	
	/*@Autowired
	PayEaseDelegate payEaseDelegate;
	
	private SelectBankForm selectBankForm;
	
	@Action(value = "selectBank", results = { 
			@Result(name = "success", type = "velocity", location = "/content/payease/selectbank.vm"),
			@Result(name = "callback", type = "redirectAction", params={
					"actionName","callbackBusiness",
					"namespace","/",
					"callbackForm.callBackURL","${selectBankForm.callBackURL}",
					"callbackForm.orderNumber","${selectBankForm.orderNumber}",
					"callbackForm.status","${selectBankForm.status}",
					"callbackForm.remarks","${selectBankForm.remarks}"})
			})
	public String selectBank() throws Exception{
		try {
			
			String supplierid = this.getSupplierId();
			
			if(selectBankForm == null){
				selectBankForm = new SelectBankForm();
			}
			selectBankForm.setCurrentSupplierId(supplierid);
			
			*//**1、支付业务编号校验。当支付业务编号不是已经配置好的，则查询不到信息，导致回调URL为空。*//*
			PayBusinessDTO payBusinessDTO = this.payEaseDelegate.getPayBusinessByNumber(selectBankForm.getBusinessNumber());
			if(payBusinessDTO == null || StringUtils.isBlank(payBusinessDTO.getCallbackUrl() )){
				log.info("callBackURL is null !{BusinessByNumber:"+selectBankForm.getBusinessNumber()+"}");
				throw new Exception("callBackURL is null !{BusinessByNumber:"+selectBankForm.getBusinessNumber()+"}");
			}
			
			this.selectBankForm.setCallBackURL(payBusinessDTO.getCallbackUrl());
			
			*//**2、支付请求参数校验*//*
			ValidateResult result = PayEaseValidate.selectBankParamsValidate(selectBankForm);
			if(result.getIsSuccess() == false){
				//请求参数有误。
				log.info(result.getMsg());
				this.selectBankForm.setStatus(PayEaseConstant.Status_Failed);
				this.selectBankForm.setRemarks(PayEaseConstant.Status_Failed_Desc_Params);
				request.setAttribute("callBackForm", selectBankForm);
				return "callback";
			}
			
			log.info("SelectBank:{supplierid:"+supplierid+",BusinessNumber:"+selectBankForm.getBusinessNumber()
					+",OrderNumber："+selectBankForm.getOrderNumber()+",PayAmount:"+selectBankForm.getPayAmount()+",PayMd5Info:"+selectBankForm.getPayMd5Info()+"}");
			*//**3、校验是否存在相同订单编号的订单。*//*
			PayOrderDTO payOrderDTO = this.payEaseDelegate.getPayOrderInfo(supplierid, selectBankForm.getBusinessNumber(), selectBankForm.getOrderNumber());
			if(payOrderDTO != null){
				if(PayEaseConstant.Status_Create.equals(payOrderDTO.getStatus())){
					*//**3.1存在相同订单号的订单，已经创建，尚未向首信易提交支付信息。校验秘钥信息，向首信易提交支付信息*//*
					payOrderDTO.setPayMd5Info(selectBankForm.getPayMd5Info());
					boolean isConfirmed = this.payEaseDelegate.validatePayOrderSign(payBusinessDTO, payOrderDTO);
					if(isConfirmed == false){
						this.selectBankForm.setStatus(PayEaseConstant.Status_Create);
						this.selectBankForm.setRemarks(PayEaseConstant.PayMd5Info_Error_Repeat);
						request.setAttribute("callBackForm", selectBankForm);
						return "callback";
					}else{
						//目前只有首信易支付。
						selectBankForm.setChannelId(PayEaseConstant.ChannelId_PayEase);
						selectBankForm.setBusinessId(payBusinessDTO.getBusinessId());
						selectBankForm.setOrderId(payOrderDTO.getOrderId());
						return SUCCESS;
					}
				}else if(PayEaseConstant.Status_Commit.equals(payOrderDTO.getStatus())){
					*//**
					 * 3.2存在相同订单号的订单，已经向首信易提交支付信息。
					 * 此时，用户可以再次提交支付请求，但需要用重新生成一个支付流水号，再向首信易发起支付请求。
					 * 对于，发生重复支付（重复扣款成功）的情况，需由财务审核，进行退款操作。
					 *//*
					//校验订单信息是否有修改。
					payOrderDTO.setPayMd5Info(selectBankForm.getPayMd5Info());
					boolean isConfirmed = this.payEaseDelegate.validatePayOrderSign(payBusinessDTO, payOrderDTO);
					if(isConfirmed == false){
						this.selectBankForm.setStatus(PayEaseConstant.Status_Failed);
						this.selectBankForm.setRemarks(PayEaseConstant.PayMd5Info_Error_Repeat);
						request.setAttribute("callBackForm", selectBankForm);
						return "callback";
					}else{
						//目前只有首信易支付。
						selectBankForm.setChannelId(PayEaseConstant.ChannelId_PayEase);
						selectBankForm.setBusinessId(payBusinessDTO.getBusinessId());
						selectBankForm.setOrderId(payOrderDTO.getOrderId());
						return SUCCESS;
					}
				}else if(PayEaseConstant.Status_Success.equals(payOrderDTO.getStatus())){
					*//**3.3存在相同订单号的订单，已经向首信易提交支付信息，并且首信已经返回支付成功。直接返回，提示“支付成功”*//*
					this.selectBankForm.setStatus(PayEaseConstant.Status_Success);
					this.selectBankForm.setRemarks(PayEaseConstant.Status_Success_Desc_Repeat);
					request.setAttribute("callBackForm", selectBankForm);
					return "callback";
				}else if(PayEaseConstant.Status_Failed.equals(payOrderDTO.getStatus())){
					*//**3.4存在相同订单号的订单，已经向首信易提交支付信息，并且首信已经返回支付失败。校验秘钥信息，可以重新向首信易提交支付信息*//*
					payOrderDTO.setPayMd5Info(selectBankForm.getPayMd5Info());
					boolean isConfirmed = this.payEaseDelegate.validatePayOrderSign(payBusinessDTO, payOrderDTO);
					if(isConfirmed == false){
						this.selectBankForm.setStatus(PayEaseConstant.Status_Failed);
						this.selectBankForm.setRemarks(PayEaseConstant.PayMd5Info_Error_Repeat);
						request.setAttribute("callBackForm", selectBankForm);
						return "callback";
					}else{
						//目前只有首信易支付。
						selectBankForm.setChannelId(PayEaseConstant.ChannelId_PayEase);
						selectBankForm.setBusinessId(payBusinessDTO.getBusinessId());
						selectBankForm.setOrderId(payOrderDTO.getOrderId());
						return SUCCESS;
					}
				}else{
					*//**3.5存在相同订单号的订单，订单状态未知。直接返回，提示“未知订单状态”*//*
					this.selectBankForm.setStatus(PayEaseConstant.Status_Failed);
					this.selectBankForm.setRemarks(PayEaseConstant.Status_Unknown_Desc);
					request.setAttribute("callBackForm", selectBankForm);
					return "callback";
				}
			}else{
				*//**3.6不存在相同订单号的订单。创建订单信息，校验秘钥信息，向首信易提交支付信息*//*
				payOrderDTO = this.selectBankForm.initPayOrder();
				if(payOrderDTO != null){
					boolean isConfirmed = this.payEaseDelegate.validatePayOrderSign(payBusinessDTO, payOrderDTO);
					if(isConfirmed == false){
						this.selectBankForm.setStatus(PayEaseConstant.Status_Create_Failed);
						this.selectBankForm.setRemarks(PayEaseConstant.PayMd5Info_Error);
						request.setAttribute("callBackForm", selectBankForm);
						return "callback";
					}else{
						//目前只有首信易支付。
						selectBankForm.setChannelId(PayEaseConstant.ChannelId_PayEase);
						selectBankForm.setBusinessId(payBusinessDTO.getBusinessId());
						payOrderDTO.setBusinessId(payBusinessDTO.getBusinessId());
						payOrderDTO.setStatus(PayEaseConstant.Status_Create);
						payOrderDTO.setPaymentType(PayEaseConstant.PaymentType_Online);
						ResultDto<Long> resultDto = this.payEaseDelegate.createPayOrder(payOrderDTO);
						if(resultDto.isAck() == false || resultDto.getData() == null){
							this.selectBankForm.setStatus(PayEaseConstant.Status_Create_Failed);
							this.selectBankForm.setRemarks(PayEaseConstant.Status_Create_Failed_Desc);
							request.setAttribute("callBackForm", selectBankForm);
							return "callback";
						}else{
							selectBankForm.setOrderId(resultDto.getData());
							return SUCCESS;
						}
					}
				}else{
					this.selectBankForm.setStatus(PayEaseConstant.Status_Create_Failed);
					this.selectBankForm.setRemarks(PayEaseConstant.PayMd5Info_Error);
					request.setAttribute("callBackForm", selectBankForm);
					return "callback";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			throw e;
			
		}
	}
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
	@JSON(serialize=false)
	public PayEaseDelegate getPayEaseDelegate() {
		return payEaseDelegate;
	}
	public void setPayEaseDelegate(PayEaseDelegate payEaseDelegate) {
		this.payEaseDelegate = payEaseDelegate;
	}
	public SelectBankForm getSelectBankForm() {
		return selectBankForm;
	}
	public void setSelectBankForm(SelectBankForm selectBankForm) {
		this.selectBankForm = selectBankForm;
	}*/
	
}
