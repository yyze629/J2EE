package com.yinyang.sellerpay.dao.api.dto;

import java.util.Date;

public class PayFlowTaskDTO  implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PayFlowDTO payFlowDTO;
	
	private PayAccountDTO payAccountDTO;
	
	private boolean isEndTask;
	
	public PayFlowTaskDTO(){
		this.isEndTask = false;
	}

	public PayFlowDTO getPayFlowDTO() {
		return payFlowDTO;
	}

	public void setPayFlowDTO(PayFlowDTO payFlowDTO) {
		this.payFlowDTO = payFlowDTO;
	}

	public PayAccountDTO getPayAccountDTO() {
		return payAccountDTO;
	}

	public void setPayAccountDTO(PayAccountDTO payAccountDTO) {
		this.payAccountDTO = payAccountDTO;
	}

	public boolean isEndTask() {
		return isEndTask;
	}

	public void setEndTask(boolean isEndTask) {
		this.isEndTask = isEndTask;
	}
	

}
