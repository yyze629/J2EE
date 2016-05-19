package com.yinyang.sellerpay.web.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class ValidateResult {
	private Boolean isSuccess;
	private List<String> msgList;
	
	public ValidateResult(){
		this.isSuccess = true;
		this.msgList = new ArrayList<String>();
	}

	public void addMsg(String msg){
		if(StringUtils.isNotBlank(msg)){
			this.msgList.add(msg);
		}
	}
	public String getMsg(){
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for(String msg : this.msgList){
			if(StringUtils.isNotBlank(msg)){
				if(i != 0){
					sb.append(",");
				}
				sb.append(msg);
			}
		}
		return sb.toString();
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public List<String> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<String> msgList) {
		this.msgList = msgList;
	}
	public static void main(String[] agrs){
		System.out.println(UUID.randomUUID().toString().toUpperCase());
	}
	
}
