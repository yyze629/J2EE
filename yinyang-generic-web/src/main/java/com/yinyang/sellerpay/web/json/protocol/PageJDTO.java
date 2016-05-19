package com.yinyang.sellerpay.web.json.protocol;

import com.dhgate.common.util.Page;

public class PageJDTO {
	
	/**
	 * 总条数.
	 */
	private Long count;
	/**
	 * 总页数
	 */
	private Integer pages;
	/**
	 * 每页大小
	 */
	private Integer pageSize;
	/**
	 * 当前页码
	 */
	private Integer pageNo;
	/**
	 * 上一页
	 */
	private Integer prePageNo;
	/**
	 * 下一页
	 */
	private Integer nextPageNo;
	public void initByPage(Page page){
		if(page != null && page.getPageBean() != null){
			this.count = page.getPageBean().getCount();
			this.nextPageNo = page.getPageBean().getNextPageNo();
			this.pageNo = page.getPageBean().getPage();
			this.pages = page.getPageBean().getPages();
			this.pageSize = page.getPageBean().getPageSize();
			this.prePageNo = page.getPageBean().getPrePageNo();
		}
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPrePageNo() {
		return prePageNo;
	}
	public void setPrePageNo(Integer prePageNo) {
		this.prePageNo = prePageNo;
	}
	public Integer getNextPageNo() {
		return nextPageNo;
	}
	public void setNextPageNo(Integer nextPageNo) {
		this.nextPageNo = nextPageNo;
	}
	
	

}
