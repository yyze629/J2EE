package com.yinyang.sellerpay.dao.api.dto;

import java.util.Date;
import java.util.List;

public class PayOrderDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long orderId;
	private Long businessId;
	private String supplierId;
	private String businessNumber;
	private String orderNumber;
	private String paymentType;
	private Double payAmount;
	private String payDesc;
	private String status;
	private String operator;
	private Date createDate;
	private Date updateDate;
	private String remarks;
	/**
	 * 订单数字指纹。
	 */
	private String payMd5Info;
	private List<PayFlowDTO> payFlowList;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
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
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPayMd5Info() {
		return payMd5Info;
	}
	public void setPayMd5Info(String payMd5Info) {
		this.payMd5Info = payMd5Info;
	}
	public List<PayFlowDTO> getPayFlowList() {
		return payFlowList;
	}
	public void setPayFlowList(List<PayFlowDTO> payFlowList) {
		this.payFlowList = payFlowList;
	}
	
	
}
