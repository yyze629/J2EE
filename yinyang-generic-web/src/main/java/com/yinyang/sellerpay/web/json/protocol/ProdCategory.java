package com.yinyang.sellerpay.web.json.protocol;

/**
 * 类目查询，类目信息定义。
 * @author baixingang
 *
 */
public class ProdCategory {
	
	/**类目id*/
	private String catePubId;
	/**类目显示值-名称*/
	private String pubName;
	/**类目显示值-名称(中文)*/
	private String pubNameCn;
	/**标记类目是否为叶子节点。1:是叶子节点，0:非叶子节点。*/
	private String leaf;
	
	public String getLeaf() {
		return leaf;
	}
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	public String getCatePubId() {
		return catePubId;
	}
	public void setCatePubId(String catePubId) {
		this.catePubId = catePubId;
	}
	public String getPubName() {
		return pubName;
	}
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}
	public String getPubNameCn() {
		return pubNameCn;
	}
	public void setPubNameCn(String pubNameCn) {
		this.pubNameCn = pubNameCn;
	}
	
	

}
