package com.yinyang.sellerpay.web.json.protocol.attr;


public class ProductSubmitAttrvalJDTO {
	
	/**
	 * 属性ID
	 */
	private Long attrId;
	/**
	 * 0：新增；1：修改；2：删除；
	 */
	private Integer attrModifyType;
	
	/**
	 * 属性值ID，多个属性值以逗号分隔。
	 */
	private String attrValIdStr;
	
	public Long getAttrId() {
		return attrId;
	}
	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}
	public String getAttrValIdStr() {
		return attrValIdStr;
	}
	public void setAttrValIdStr(String attrValIdStr) {
		this.attrValIdStr = attrValIdStr;
	}
	public Integer getAttrModifyType() {
		return attrModifyType;
	}
	public void setAttrModifyType(Integer attrModifyType) {
		this.attrModifyType = attrModifyType;
	}
	
	
}
