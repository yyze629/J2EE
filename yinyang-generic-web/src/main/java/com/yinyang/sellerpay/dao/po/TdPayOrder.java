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

import com.yinyang.sellerpay.dao.api.dto.PayOrderDTO;

/**
 * TdPayOrder entity. @author MyEclipse Persistence Tools
 */
//@Entity
//@Table(name = "TD_PAY_ORDER")
public class TdPayOrder implements java.io.Serializable {

	// Fields

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

	// Constructors

	/** default constructor */
	public TdPayOrder() {
	}

	/** minimal constructor */
	public TdPayOrder(Long businessId) {
		this.businessId = businessId;
	}

	/** full constructor */
	public TdPayOrder(Long businessId, String supplierId,
			String businessNumber, String orderNumber, String paymentType,
			Double payAmount, String payDesc, String status, String operator,
			Date createDate, Date updateDate, String remarks) {
		this.businessId = businessId;
		this.supplierId = supplierId;
		this.businessNumber = businessNumber;
		this.orderNumber = orderNumber;
		this.paymentType = paymentType;
		this.payAmount = payAmount;
		this.payDesc = payDesc;
		this.status = status;
		this.operator = operator;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.remarks = remarks;
	}
	
	public PayOrderDTO po2dto(){
		PayOrderDTO dto = new PayOrderDTO();
		
		dto.setBusinessId(businessId);
		dto.setBusinessNumber(businessNumber);
		dto.setCreateDate(createDate);
		dto.setOperator(operator);
		dto.setOrderId(orderId);
		dto.setOrderNumber(orderNumber);
		dto.setPayAmount(payAmount);
		dto.setPayDesc(payDesc);
		dto.setPaymentType(paymentType);
		dto.setRemarks(remarks);
		dto.setStatus(status);
		dto.setSupplierId(supplierId);
		dto.setUpdateDate(updateDate);
		
		return dto;
	}
	
	public void dto2po(PayOrderDTO dto){
		if(dto == null){
			return ;
		}
		this.orderId = dto.getOrderId();
		this.businessId = dto.getBusinessId();
		this.supplierId = dto.getSupplierId();
		this.businessNumber = dto.getBusinessNumber();
		this.orderNumber = dto.getOrderNumber();
		this.paymentType = dto.getPaymentType();
		this.payAmount = dto.getPayAmount();
		this.payDesc = dto.getPayDesc();
		this.status = dto.getStatus();
		this.operator = dto.getOperator();
		this.createDate = dto.getCreateDate();
		this.updateDate = dto.getUpdateDate();
		this.remarks = dto.getRemarks();
		
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "generator",sequenceName="SEQ_TD_PAY_ORDER",allocationSize=1)
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ORDER_ID", unique = true, nullable = false, precision = 20, scale = 0)
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

	@Column(name = "SUPPLIER_ID", length = 40)
	public String getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "BUSINESS_NUMBER", length = 40)
	public String getBusinessNumber() {
		return this.businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	@Column(name = "ORDER_NUMBER", length = 64)
	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "PAYMENT_TYPE", length = 2)
	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name = "PAY_AMOUNT", precision = 10, scale = 4)
	public Double getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	@Column(name = "PAY_DESC", length = 500)
	public String getPayDesc() {
		return this.payDesc;
	}

	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Column(name = "REMARKS", length = 300)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}