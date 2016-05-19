package com.yinyang.sellerpay.dao.api.dto;

import java.util.Date;
import java.util.List;

public class PayFlowDTO implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String payFlowId;
	private String channelId;
	private Long orderId;
	private Long businessId;
	private Long VMid;
	private String VOid;
	private String VPmode;
	private String VPmodeDesc;
	private String VYmd;
	private Double VAmount;
	private String VAmountStr;
	private String VMoneytype;
	private String VUrl;
	private Double VAmountActual;
	private String VAmountActualStr;
	private String VMoneytypeActual;
	private String VPstatus;
	private String VPstring;
	private String VIsvirement;
	private String operator;
	private Date createDate;
	private Date updateDate;
	private String remarks;
	private String VMd5info;
	private String VMd5money;
	private String VRcvname;
	private String VSign;
	private List<PayReconciliationDTO> payReconciliationList;
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
	public Long getVMid() {
		return VMid;
	}
	public void setVMid(Long vMid) {
		VMid = vMid;
	}
	public String getVOid() {
		return VOid;
	}
	public void setVOid(String vOid) {
		VOid = vOid;
	}
	public String getVPmode() {
		return VPmode;
	}
	public void setVPmode(String vPmode) {
		VPmode = vPmode;
	}
	public String getVPmodeDesc() {
		return VPmodeDesc;
	}
	public void setVPmodeDesc(String vPmodeDesc) {
		VPmodeDesc = vPmodeDesc;
	}
	public String getVYmd() {
		return VYmd;
	}
	public void setVYmd(String vYmd) {
		VYmd = vYmd;
	}
	public Double getVAmount() {
		return VAmount;
	}
	public void setVAmount(Double vAmount) {
		VAmount = vAmount;
	}
	public String getVMoneytype() {
		return VMoneytype;
	}
	public void setVMoneytype(String vMoneytype) {
		VMoneytype = vMoneytype;
	}
	public String getVUrl() {
		return VUrl;
	}
	public void setVUrl(String vUrl) {
		VUrl = vUrl;
	}
	public Double getVAmountActual() {
		return VAmountActual;
	}
	public void setVAmountActual(Double vAmountActual) {
		VAmountActual = vAmountActual;
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
	public String getVMd5info() {
		return VMd5info;
	}
	public void setVMd5info(String vMd5info) {
		VMd5info = vMd5info;
	}
	public String getVRcvname() {
		return VRcvname;
	}
	public void setVRcvname(String vRcvname) {
		VRcvname = vRcvname;
	}
	public String getVMd5money() {
		return VMd5money;
	}
	public void setVMd5money(String vMd5money) {
		VMd5money = vMd5money;
	}
	public String getVSign() {
		return VSign;
	}
	public void setVSign(String vSign) {
		VSign = vSign;
	}
	public String getVAmountStr() {
		return VAmountStr;
	}
	public void setVAmountStr(String vAmountStr) {
		VAmountStr = vAmountStr;
	}
	public String getVAmountActualStr() {
		return VAmountActualStr;
	}
	public void setVAmountActualStr(String vAmountActualStr) {
		VAmountActualStr = vAmountActualStr;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public List<PayReconciliationDTO> getPayReconciliationList() {
		return payReconciliationList;
	}
	public void setPayReconciliationList(
			List<PayReconciliationDTO> payReconciliationList) {
		this.payReconciliationList = payReconciliationList;
	}
	
	
}
