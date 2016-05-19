package com.yinyang.sellerpay.dao.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yinyang.sellerpay.dao.api.dto.PayAccountDTO;

/**
 * TdPayAccount entity. @author MyEclipse Persistence Tools
 */
//@Entity
//@Table(name = "TD_PAY_ACCOUNT")
public class TdPayAccount implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long accountId;
	private String accountType;
	private String accountNumber;
	private String signKey;
	private String status;
	private Date createDate;
	private Date updateDate;
	private String remarks;

	// Constructors

	/** default constructor */
	public TdPayAccount() {
	}

	/** full constructor */
	public TdPayAccount(String accountType, String accountNumber,
			String signKey, String status, Date createDate, Date updateDate,
			String remarks) {
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.signKey = signKey;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.remarks = remarks;
	}
	
	public PayAccountDTO po2dto(){
		PayAccountDTO dto = new PayAccountDTO();
		
		dto.setAccountId(accountId);
		dto.setAccountNumber(accountNumber);
		dto.setAccountType(accountType);
		dto.setCreateDate(createDate);
		dto.setRemarks(remarks);
		dto.setSignKey(signKey);
		dto.setStatus(status);
		dto.setUpdateDate(updateDate);
		
		return dto;
	}
	
	public void dto2po(PayAccountDTO dto){
		if(dto == null){
			return ;
		}
		this.accountId = dto.getAccountId();
		this.accountNumber = dto.getAccountNumber();
		this.accountType = dto.getAccountType();
		this.createDate = dto.getCreateDate();
		this.remarks = dto.getRemarks();
		this.signKey = dto.getSignKey();
		this.status = dto.getStatus();
		this.updateDate = dto.getUpdateDate();
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "generator",sequenceName="SEQ_TD_PAY_ACCOUNT",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ACCOUNT_ID", unique = true, nullable = false, precision = 20, scale = 0)
	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "ACCOUNT_TYPE", length = 2)
	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Column(name = "ACCOUNT_NUMBER", length = 64)
	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "SIGN_KEY", length = 64)
	public String getSignKey() {
		return this.signKey;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
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