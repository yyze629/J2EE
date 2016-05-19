package com.yinyang.sellerpay.web.json.protocol.attr;

import java.util.List;

public class ProductSubmitReq {
	
	private String productid;
	private Long itemcode;
	private String catePubId;
	private List<ProductSubmitAttrvalJDTO> productAttrvalList;
	
	
	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public Long getItemcode() {
		return itemcode;
	}

	public void setItemcode(Long itemcode) {
		this.itemcode = itemcode;
	}

	public String getCatePubId() {
		return catePubId;
	}

	public void setCatePubId(String catePubId) {
		this.catePubId = catePubId;
	}

	public List<ProductSubmitAttrvalJDTO> getProductAttrvalList() {
		return productAttrvalList;
	}

	public void setProductAttrvalList(
			List<ProductSubmitAttrvalJDTO> productAttrvalList) {
		this.productAttrvalList = productAttrvalList;
	}

	
}
