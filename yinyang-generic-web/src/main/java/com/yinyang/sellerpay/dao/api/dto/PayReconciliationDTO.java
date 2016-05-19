package com.yinyang.sellerpay.dao.api.dto;

import java.util.Date;

public class PayReconciliationDTO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reconciliationId;
	private String payFlowId;
	private String channelId;
	private String VMid;
	private String VOid;
	private String VStatus;
	private String VDesc;
	private String VPmodeDesc;
	private Double VAmountActual;
	private String VAmountActualStr;
	private String VYmd;
	private String VMoneytypeActual;
	private String VPstatus;
	private String VPstring;
	private String VIsvirement;
	private String VSign;
	private String operator;
	private Date createDate;
	private Date updateDate;
	private String remarks;
	public String getReconciliationId() {
		return reconciliationId;
	}
	public void setReconciliationId(String reconciliationId) {
		this.reconciliationId = reconciliationId;
	}
	public String getPayFlowId() {
		return payFlowId;
	}
	public void setPayFlowId(String payFlowId) {
		this.payFlowId = payFlowId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getVOid() {
		return VOid;
	}
	public void setVOid(String vOid) {
		VOid = vOid;
	}
	public String getVStatus() {
		return VStatus;
	}
	public void setVStatus(String vStatus) {
		VStatus = vStatus;
	}
	public String getVDesc() {
		return VDesc;
	}
	public void setVDesc(String vDesc) {
		VDesc = vDesc;
	}
	public String getVPmodeDesc() {
		return VPmodeDesc;
	}
	public void setVPmodeDesc(String vPmodeDesc) {
		VPmodeDesc = vPmodeDesc;
	}
	public Double getVAmountActual() {
		return VAmountActual;
	}
	public void setVAmountActual(Double vAmountActual) {
		VAmountActual = vAmountActual;
	}
	public String getVYmd() {
		return VYmd;
	}
	public void setVYmd(String vYmd) {
		VYmd = vYmd;
	}
	public String getVMoneytypeActual() {
		return VMoneytypeActual;
	}
	public void setVMoneytypeActual(String vMoneytypeActual) {
		VMoneytypeActual = vMoneytypeActual;
	}
	public String getVPstatus() {
		return VPstatus;
	}
	public void setVPstatus(String vPstatus) {
		VPstatus = vPstatus;
	}
	public String getVPstring() {
		return VPstring;
	}
	public void setVPstring(String vPstring) {
		VPstring = vPstring;
	}
	public String getVIsvirement() {
		return VIsvirement;
	}
	public void setVIsvirement(String vIsvirement) {
		VIsvirement = vIsvirement;
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getVSign() {
		return VSign;
	}
	public void setVSign(String vSign) {
		VSign = vSign;
	}
	public String getVMid() {
		return VMid;
	}
	public void setVMid(String vMid) {
		VMid = vMid;
	}
	public String getVAmountActualStr() {
		return VAmountActualStr;
	}
	public void setVAmountActualStr(String vAmountActualStr) {
		VAmountActualStr = vAmountActualStr;
	}
	
}
