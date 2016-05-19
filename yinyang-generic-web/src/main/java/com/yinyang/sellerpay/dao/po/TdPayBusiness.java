package com.yinyang.sellerpay.dao.po;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yinyang.sellerpay.dao.api.dto.PayBusinessDTO;

/**
 * TdPayBusiness entity. @author MyEclipse Persistence Tools
 */
//@Entity
//@Table(name = "TD_PAY_BUSINESS")
public class TdPayBusiness implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long businessId;
	private Long accountId;
	private String businessNumber;
	private String signKey;
	private String reconciliationUrl;
	private String callbackUrl;
	private String status;
	private Date createDate;
	private Date updateDate;
	private String remarks;

	// Constructors

	/** default constructor */
	public TdPayBusiness() {
	}

	/** minimal constructor */
	public TdPayBusiness(Long accountId) {
		this.accountId = accountId;
	}

	/** full constructor */
	public TdPayBusiness(Long accountId, String businessNumber,
			String signKey, String reconciliationUrl, String callbackUrl,
			String status, Date createDate, Date updateDate, String remarks) {
		this.accountId = accountId;
		this.businessNumber = businessNumber;
		this.signKey = signKey;
		this.reconciliationUrl = reconciliationUrl;
		this.callbackUrl = callbackUrl;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.remarks = remarks;
	}
	
	public PayBusinessDTO po2dto(){
		PayBusinessDTO dto = new PayBusinessDTO();
		
		dto.setAccountId(accountId);
		dto.setBusinessId(businessId);
		dto.setBusinessNumber(businessNumber);
		dto.setCallbackUrl(callbackUrl);
		dto.setCreateDate(createDate);
		dto.setReconciliationUrl(reconciliationUrl);
		dto.setRemarks(remarks);
		dto.setSignKey(signKey);
		dto.setStatus(status);
		dto.setUpdateDate(updateDate);
		
		return dto;
	}
	
	public void dto2po(PayBusinessDTO dto){
		if(dto == null){
			return ;
		}
		this.accountId = dto.getAccountId();
		this.businessId = dto.getBusinessId();
		this.businessNumber = dto.getBusinessNumber();
		this.callbackUrl = dto.getCallbackUrl();
		this.createDate = dto.getCreateDate();
		this.reconciliationUrl = dto.getReconciliationUrl();
		this.remarks = dto.getRemarks();
		this.signKey = dto.getSignKey();
		this.status = dto.getStatus();
		this.updateDate = dto.getUpdateDate();
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "generator",sequenceName="SEQ_TD_PAY_BUSINESS",allocationSize=1)
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "BUSINESS_ID", unique = true, nullable = false, precision = 20, scale = 0)
	public Long getBusinessId() {
		return this.businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	@Column(name = "ACCOUNT_ID", nullable = false, precision = 20, scale = 0)
	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "BUSINESS_NUMBER", length = 40)
	public String getBusinessNumber() {
		return this.businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	@Column(name = "SIGN_KEY", length = 40)
	public String getSignKey() {
		return this.signKey;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	@Column(name = "RECONCILIATION_URL", length = 300)
	public String getReconciliationUrl() {
		return this.reconciliationUrl;
	}

	public void setReconciliationUrl(String reconciliationUrl) {
		this.reconciliationUrl = reconciliationUrl;
	}

	@Column(name = "CALLBACK_URL", length = 300)
	public String getCallbackUrl() {
		return this.callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Column(name = "REMARKS", length = 300)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}