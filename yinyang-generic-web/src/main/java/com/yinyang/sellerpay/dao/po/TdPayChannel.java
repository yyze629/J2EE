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

import com.yinyang.sellerpay.dao.api.dto.PayChannelDTO;

/**
 * TdPayChannel entity. @author MyEclipse Persistence Tools
 */
//@Entity
//@Table(name = "TD_PAY_CHANNEL")
public class TdPayChannel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String channelId;
	private String payUrl;
	private String callbackUrl;
	private String status;
	private Date createDate;
	private Date updateDate;
	private String remarks;

	// Constructors

	/** default constructor */
	public TdPayChannel() {
	}

	/** full constructor */
	public TdPayChannel(String payUrl, String callbackUrl, String status,
			Date createDate, Date updateDate, String remarks) {
		this.payUrl = payUrl;
		this.callbackUrl = callbackUrl;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.remarks = remarks;
	}
	
	public PayChannelDTO po2dto(){
		PayChannelDTO dto  = new PayChannelDTO();
		
		dto.setCallbackUrl(callbackUrl);
		dto.setChannelId(channelId);
		dto.setCreateDate(createDate);
		dto.setPayUrl(payUrl);
		dto.setRemarks(remarks);
		dto.setStatus(status);
		dto.setUpdateDate(updateDate);
		
		return dto;
	}
	
	public void dto2po(PayChannelDTO dto){
		if(dto == null){
			return ;
		}
		
		this.callbackUrl = dto.getCallbackUrl();
		this.channelId = dto.getChannelId();
		this.createDate = dto.getCreateDate();
		this.payUrl = dto.getPayUrl();
		this.remarks = dto.getRemarks();
		this.status = dto.getStatus();
		this.updateDate = dto.getUpdateDate();
	}

	// Property accessors
	@Id
	@Column(name = "CHANNEL_ID", unique = true, nullable = false, length = 40)
	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	@Column(name = "PAY_URL", length = 300)
	public String getPayUrl() {
		return this.payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
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