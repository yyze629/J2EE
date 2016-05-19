package com.yinyang.sellerpay.web.form;

import java.io.Serializable;

public class SelectBankForm extends SellerPayBaseForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4743116086604735826L;
	
	/**
	 * 当前登录的卖家ID。
	 */
	private String currentSupplierId;
	
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
	 * 支付类型:01  线上。02 线下( 网银/柜台转账   邮局转账)  
	 */
	private String paymentType;
	
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
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getCurrentSupplierId() {
		return currentSupplierId;
	}
	public void setCurrentSupplierId(String currentSupplierId) {
		this.currentSupplierId = currentSupplierId;
	}
	
}
