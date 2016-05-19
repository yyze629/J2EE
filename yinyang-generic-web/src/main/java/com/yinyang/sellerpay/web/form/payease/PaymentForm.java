package com.yinyang.sellerpay.web.form.payease;

import java.io.Serializable;
import java.util.Date;

import com.yinyang.sellerpay.dao.api.dto.PayAccountDTO;
import com.yinyang.sellerpay.dao.api.dto.PayChannelDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowDTO;
import com.yinyang.sellerpay.dao.api.dto.PayOrderDTO;
import com.yinyang.sellerpay.web.form.SellerPayBaseForm;
import com.yinyang.sellerpay.web.util.DateUtil;
import com.yinyang.sellerpay.web.util.PayEaseConstant;

public class PaymentForm extends SellerPayBaseForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4743116086604735826L;
	
	/**
	 * 渠道ID；
	 */
	private String channelId;
	/**
	 * 业务ID。
	 */
	private Long businessId;
	/**
	 * 订单ID。
	 */
	private Long orderId;
	/**
	 * 支付方式编号。
	 */
	private String pmode;
	
	/**
	 * 支付URL。
	 */
	private String payUrl;
	
	public PayFlowDTO initPayFlow(PayAccountDTO payAccountDTO,PayOrderDTO payOrderDTO,PayChannelDTO payChannelDTO){
		PayFlowDTO dto = new PayFlowDTO();
		
		dto.setBusinessId(payOrderDTO.getBusinessId());
		dto.setCreateDate(new Date(System.currentTimeMillis()));
		dto.setChannelId(payChannelDTO.getChannelId());
		dto.setOrderId(payOrderDTO.getOrderId());
		dto.setVAmount(payOrderDTO.getPayAmount());
		dto.setVMid(Long.parseLong(payAccountDTO.getAccountNumber()));
		dto.setVMoneytype(PayEaseConstant.VMoneytype_RMB);
		dto.setVPmode(this.pmode);
		dto.setVPstatus(PayEaseConstant.VPstatus_Commit);
		dto.setVUrl(payChannelDTO.getCallbackUrl());
		dto.setVYmd(DateUtil.getDateyyyyMMddStr(new Date(System.currentTimeMillis())));
		
		return dto;
	}
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getPmode() {
		return pmode;
	}
	public void setPmode(String pmode) {
		this.pmode = pmode;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}
	
	

}
