package com.yinyang.sellerpay.web.json.protocol;

import java.util.ArrayList;
import java.util.List;


/**
 * 类目查询接口返回信息。
 * @author baixingang
 *
 */
public class ProdCategoryResp {
	
	private Integer code;
	private String msg;
	
	private List<ProdCategory> result = new ArrayList<ProdCategory>();

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<ProdCategory> getResult() {
		return result;
	}

	public void setResult(List<ProdCategory> result) {
		this.result = result;
	}
	
	


}
