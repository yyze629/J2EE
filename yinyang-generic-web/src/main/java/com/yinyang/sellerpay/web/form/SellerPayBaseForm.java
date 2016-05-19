package com.yinyang.sellerpay.web.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.yinyang.sellerpay.dao.api.dto.PayOrderDTO;

public abstract class SellerPayBaseForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6045602668825085536L;
	
	
	/**
	 * 卖家ID。
	 */
	private String supplierId;
	/**
	 * 业务编号。
	 */
	private String businessNumber;
	/**
	 * 平台订单号。
	 */
	private String orderNumber;
	/**
	 * 支付金额。
	 */
	private Double payAmount;
	/**
	 * 支付描述。
	 */
	private String payDesc;
	/**
	 * 支付描述,byte形式。
	 */
	private byte[] payDescByte;
	/**
	 * 订单数字指纹。
	 */
	private String payMd5Info;
	/**
	 * 回调URL。
	 */
	private String callBackURL;
	/**
	 * 订单状态。
	 */
	private String status;
	/**
	 * 订单状态描述。
	 */
	private String remarks;
	
	public PayOrderDTO initPayOrder(){
		PayOrderDTO dto = null;
		if(StringUtils.isBlank(this.supplierId) || StringUtils.isBlank(this.businessNumber) || StringUtils.isBlank(this.orderNumber) 
				|| this.payAmount == null || StringUtils.isBlank(this.payDesc) || StringUtils.isBlank(this.payMd5Info)){
			return dto;
		}
		dto = new PayOrderDTO();
		dto.setSupplierId(supplierId);
		dto.setBusinessNumber(businessNumber);
		dto.setOrderNumber(orderNumber);
		dto.setPayAmount(payAmount);
		dto.setPayDesc(payDesc);
		dto.setPayMd5Info(payMd5Info);
		dto.setOperator(supplierId);
		dto.setCreateDate(new Date(System.currentTimeMillis()));
		return dto;
		
	}
	
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayDesc() {
		return payDesc;
	}
	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}
	public String getPayMd5Info() {
		return payMd5Info;
	}
	public void setPayMd5Info(String payMd5Info) {
		this.payMd5Info = payMd5Info;
	}

	public byte[] getPayDescByte() {
		return payDescByte;
	}

	public void setPayDescByte(byte[] payDescByte) {
		this.payDescByte = payDescByte;
	}

	public String getCallBackURL() {
		return callBackURL;
	}

	public void setCallBackURL(String callBackURL) {
		this.callBackURL = callBackURL;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
