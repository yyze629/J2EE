package com.yinyang.sellerpay.dao.api.dto;

import java.util.Date;

public class PayFlowQueryDTO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PayFlowDTO payFlowDTO;
	
	/**
	 * 排序订单流水ID
	 */
	private String orderPayFlowId;
	/**
	 * 排序订单流水号
	 */
	private String orderVOid;
	/**
	 * 订单流水ID数组
	 */
	private String[] payFlowIds;
	/**
	 * 排序列(字段)。
	 */
	private String orderByColumn;
	/***
	 * 0:降序；1：升序；
	 */
	private String orderByColumnAsc;
	/**
	 * 订单流水状态数组。
	 */
	private String[] VPstatusArray;
	
	private Date createDateStart;
	
	private Date createDateEnd;
	

	public PayFlowDTO getPayFlowDTO() {
		return payFlowDTO;
	}

	public void setPayFlowDTO(PayFlowDTO payFlowDTO) {
		this.payFlowDTO = payFlowDTO;
	}

	public String getOrderPayFlowId() {
		return orderPayFlowId;
	}

	public void setOrderPayFlowId(String orderPayFlowId) {
		this.orderPayFlowId = orderPayFlowId;
	}

	public String[] getPayFlowIds() {
		return payFlowIds;
	}

	public void setPayFlowIds(String[] payFlowIds) {
		this.payFlowIds = payFlowIds;
	}

	public String getOrderByColumn() {
		return orderByColumn;
	}

	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}

	public String getOrderByColumnAsc() {
		return orderByColumnAsc;
	}

	public void setOrderByColumnAsc(String orderByColumnAsc) {
		this.orderByColumnAsc = orderByColumnAsc;
	}

	public String[] getVPstatusArray() {
		return VPstatusArray;
	}

	public void setVPstatusArray(String[] vPstatusArray) {
		VPstatusArray = vPstatusArray;
	}

	public Date getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getOrderVOid() {
		return orderVOid;
	}

	public void setOrderVOid(String orderVOid) {
		this.orderVOid = orderVOid;
	}

}
