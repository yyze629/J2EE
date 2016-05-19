package com.yinyang.sellerpay.dao.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.yinyang.sellerpay.dao.api.dto.PayReconciliationDTO;

/**
 * TdPayReconciliation entity. @author MyEclipse Persistence Tools
 */
//@Entity
//@Table(name = "TD_PAY_RECONCILIATION")
public class TdPayReconciliation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reconciliationId;
	private String payFlowId;
	private String channelId;
	private String VOid;
	private String VStatus;
	private String VDesc;
	private String VPmodeDesc;
	private Double VAmountActual;
	private String VYmd;
	private String VMoneytypeActual;
	private String VPstatus;
	private String VPstring;
	private String VIsvirement;
	private String operator;
	private Date createDate;
	private Date updateDate;
	private String remarks;

	// Constructors

	/** default constructor */
	public TdPayReconciliation() {
	}

	/** minimal constructor */
	public TdPayReconciliation(String channelId) {
		this.channelId = channelId;
	}

	/** full constructor */
	public TdPayReconciliation(String payFlowId, String channelId, String VOid,
			String VStatus, String VDesc, String VPmodeDesc, Double VAmountActual,
			String VYmd, String VMoneytypeActual, String VPstatus,
			String VPstring, String VIsvirement,String operator, Date createDate,
			Date updateDate, String remarks) {
		this.payFlowId = payFlowId;
		this.channelId = channelId;
		this.VOid = VOid;
		this.VStatus = VStatus;
		this.VDesc = VDesc;
		this.VPmodeDesc = VPmodeDesc;
		this.VAmountActual = VAmountActual;
		this.VYmd = VYmd;
		this.VMoneytypeActual = VMoneytypeActual;
		this.VPstatus = VPstatus;
		this.VPstring = VPstring;
		this.VIsvirement = VIsvirement;
		this.operator = operator;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.remarks = remarks;
	}
	
	public PayReconciliationDTO po2dto(){
		PayReconciliationDTO dto = new PayReconciliationDTO();
		
		dto.setChannelId(channelId);
		dto.setCreateDate(createDate);
		dto.setPayFlowId(payFlowId);
		dto.setReconciliationId(reconciliationId);
		dto.setRemarks(remarks);
		dto.setUpdateDate(updateDate);
		dto.setVAmountActual(VAmountActual);
		dto.setVDesc(VDesc);
		dto.setVIsvirement(VIsvirement);
		dto.setVMoneytypeActual(VMoneytypeActual);
		dto.setVOid(VOid);
		dto.setVPmodeDesc(VPmodeDesc);
		dto.setVPstatus(VPstatus);
		dto.setVPstring(VPstring);
		dto.setVStatus(VStatus);
		dto.setVYmd(VYmd);
		dto.setOperator(operator);
		
		return dto;
	}
	
	public void dto2po(PayReconciliationDTO dto){
		if(dto == null){
			return ;
		}
		this.reconciliationId = dto.getReconciliationId();
		this.payFlowId =  dto.getPayFlowId();
		this.channelId = dto.getChannelId();
		this.VOid =  dto.getVOid();
		this.VStatus = dto.getVStatus();
		this.VDesc =  dto.getVDesc();
		this.VPmodeDesc =  dto.getVPmodeDesc();
		this.VAmountActual = dto.getVAmountActual();
		this.VYmd = dto.getVYmd();
		this.VMoneytypeActual =  dto.getVMoneytypeActual();
		this.VPstatus = dto.getVPstatus();
		this.VPstring = dto.getVPstring();
		this.VIsvirement = dto.getVIsvirement();
		this.operator = dto.getOperator();
		this.createDate = dto.getCreateDate();
		this.updateDate = dto.getUpdateDate();
		this.remarks = dto.getRemarks();
	}

	// Property accessors
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "RECONCILIATION_ID", unique = true, nullable = false, length = 40)
	public String getReconciliationId() {
		return this.reconciliationId;
	}

	public void setReconciliationId(String reconciliationId) {
		this.reconciliationId = reconciliationId;
	}

	@Column(name = "PAY_FLOW_ID", length = 40)
	public String getPayFlowId() {
		return this.payFlowId;
	}

	public void setPayFlowId(String payFlowId) {
		this.payFlowId = payFlowId;
	}

	@Column(name = "CHANNEL_ID", nullable = false, length = 40)
	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	@Column(name = "V_OID", length = 64)
	public String getVOid() {
		return this.VOid;
	}

	public void setVOid(String VOid) {
		this.VOid = VOid;
	}

	@Column(name = "V_STATUS", length = 2)
	public String getVStatus() {
		return this.VStatus;
	}

	public void setVStatus(String VStatus) {
		this.VStatus = VStatus;
	}

	@Column(name = "V_DESC", length = 512)
	public String getVDesc() {
		return this.VDesc;
	}

	public void setVDesc(String VDesc) {
		this.VDesc = VDesc;
	}

	@Column(name = "V_PMODE_DESC", length = 300)
	public String getVPmodeDesc() {
		return this.VPmodeDesc;
	}

	public void setVPmodeDesc(String VPmodeDesc) {
		this.VPmodeDesc = VPmodeDesc;
	}

	@Column(name = "V_AMOUNT_ACTUAL", precision = 20, scale = 4)
	public Double getVAmountActual() {
		return this.VAmountActual;
	}

	public void setVAmountActual(Double VAmountActual) {
		this.VAmountActual = VAmountActual;
	}

	@Column(name = "V_YMD", length = 12)
	public String getVYmd() {
		return this.VYmd;
	}

	public void setVYmd(String VYmd) {
		this.VYmd = VYmd;
	}

	@Column(name = "V_MONEYTYPE_ACTUAL", length = 2)
	public String getVMoneytypeActual() {
		return this.VMoneytypeActual;
	}

	public void setVMoneytypeActual(String VMoneytypeActual) {
		this.VMoneytypeActual = VMoneytypeActual;
	}

	@Column(name = "V_PSTATUS", length = 2)
	public String getVPstatus() {
		return this.VPstatus;
	}

	public void setVPstatus(String VPstatus) {
		this.VPstatus = VPstatus;
	}

	@Column(name = "V_PSTRING", length = 500)
	public String getVPstring() {
		return this.VPstring;
	}

	public void setVPstring(String VPstring) {
		this.VPstring = VPstring;
	}

	@Column(name = "V_ISVIREMENT", length = 2)
	public String getVIsvirement() {
		return this.VIsvirement;
	}

	public void setVIsvirement(String VIsvirement) {
		this.VIsvirement = VIsvirement;
	}
	
	@Column(name = "OPERATOR", length = 40)
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE", length = 19)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "REMARKS", length = 512)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}