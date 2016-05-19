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

import com.yinyang.sellerpay.dao.api.dto.PayFlowDTO;

/**
 * TdPayFlow entity. @author MyEclipse Persistence Tools
 */
//@Entity
//@Table(name = "TD_PAY_FLOW")
public class TdPayFlow implements java.io.Serializable {

	// Fields

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
	private String VMoneytype;
	private String VUrl;
	private Double VAmountActual;
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
	public TdPayFlow() {
	}

	/** minimal constructor */
	public TdPayFlow(String channelId, Long orderId, Long businessId) {
		this.channelId = channelId;
		this.orderId = orderId;
		this.businessId = businessId;
	}

	/** full constructor */
	public TdPayFlow(String channelId, Long orderId,
			Long businessId, Long VMid, String VOid, String VPmode,
			String VPmodeDesc,String VYmd, Double VAmount, String VMoneytype, String VUrl,
			Double VAmountActual, String VMoneytypeActual, String VPstatus,
			String VPstring, String VIsvirement, String operator,Date createDate,
			Date updateDate, String remarks) {
		this.channelId = channelId;
		this.orderId = orderId;
		this.businessId = businessId;
		this.VMid = VMid;
		this.VOid = VOid;
		this.VPmode = VPmode;
		this.VPmodeDesc = VPmodeDesc;
		this.VYmd = VYmd;
		this.VAmount = VAmount;
		this.VMoneytype = VMoneytype;
		this.VUrl = VUrl;
		this.VAmountActual = VAmountActual;
		this.VMoneytypeActual = VMoneytypeActual;
		this.VPstatus = VPstatus;
		this.VPstring = VPstring;
		this.VIsvirement = VIsvirement;
		this.operator = operator;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.remarks = remarks;
	}
	
	public PayFlowDTO po2dto(){
		PayFlowDTO dto = new PayFlowDTO();
		
		dto.setBusinessId(businessId);
		dto.setCreateDate(createDate);
		dto.setChannelId(channelId);
		dto.setOrderId(orderId);
		dto.setPayFlowId(payFlowId);
		dto.setRemarks(remarks);
		dto.setUpdateDate(updateDate);
		dto.setVAmount(VAmount);
		dto.setVAmountActual(VAmountActual);
		dto.setVIsvirement(VIsvirement);
		dto.setVMid(VMid);
		dto.setVMoneytype(VMoneytype);
		dto.setVMoneytypeActual(VMoneytypeActual);
		dto.setVOid(VOid);
		dto.setVPmode(VPmode);
		dto.setVPstatus(VPstatus);
		dto.setVPstring(VPstring);
		dto.setVUrl(VUrl);
		dto.setVYmd(VYmd);
		dto.setOperator(operator);
		
		return dto;
	}
	
	public void dto2po(PayFlowDTO dto){
		if(dto == null){
			return ;
		}
		this.payFlowId = dto.getPayFlowId();
		this.channelId = dto.getChannelId();
		this.orderId = dto.getOrderId();
		this.businessId = dto.getBusinessId();
		this.VMid = dto.getVMid();
		this.VOid = dto.getVOid();
		this.VPmode = dto.getVPmode();
		this.VYmd = dto.getVYmd();
		this.VAmount = dto.getVAmount();
		this.VMoneytype = dto.getVMoneytype();
		this.VUrl = dto.getVUrl();
		this.VAmountActual = dto.getVAmountActual();
		this.VMoneytypeActual = dto.getVMoneytypeActual();
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
	@Column(name = "PAY_FLOW_ID", unique = true, nullable = false, length = 40)
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

	@Column(name = "ORDER_ID", nullable = false, precision = 20, scale = 0)
	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Column(name = "BUSINESS_ID", nullable = false, precision = 20, scale = 0)
	public Long getBusinessId() {
		return this.businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	@Column(name = "V_MID", precision = 10, scale = 0)
	public Long getVMid() {
		return this.VMid;
	}

	public void setVMid(Long VMid) {
		this.VMid = VMid;
	}

	@Column(name = "V_OID", length = 64)
	public String getVOid() {
		return this.VOid;
	}

	public void setVOid(String VOid) {
		this.VOid = VOid;
	}

	@Column(name = "V_PMODE", length = 6)
	public String getVPmode() {
		return this.VPmode;
	}

	public void setVPmode(String VPmode) {
		this.VPmode = VPmode;
	}
	
	@Column(name = "V_PMODE_DESC", length = 300)
	public String getVPmodeDesc() {
		return this.VPmodeDesc;
	}

	public void setVPmodeDesc(String VPmodeDesc) {
		this.VPmodeDesc = VPmodeDesc;
	}

	@Column(name = "V_YMD", length = 12)
	public String getVYmd() {
		return this.VYmd;
	}

	public void setVYmd(String VYmd) {
		this.VYmd = VYmd;
	}

	@Column(name = "V_AMOUNT", precision = 20, scale = 4)
	public Double getVAmount() {
		return this.VAmount;
	}

	public void setVAmount(Double VAmount) {
		this.VAmount = VAmount;
	}

	@Column(name = "V_MONEYTYPE", length = 2)
	public String getVMoneytype() {
		return this.VMoneytype;
	}

	public void setVMoneytype(String VMoneytype) {
		this.VMoneytype = VMoneytype;
	}

	@Column(name = "V_URL", length = 300)
	public String getVUrl() {
		return this.VUrl;
	}

	public void setVUrl(String VUrl) {
		this.VUrl = VUrl;
	}

	@Column(name = "V_AMOUNT_ACTUAL", precision = 20, scale = 4)
	public Double getVAmountActual() {
		return this.VAmountActual;
	}

	public void setVAmountActual(Double VAmountActual) {
		this.VAmountActual = VAmountActual;
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