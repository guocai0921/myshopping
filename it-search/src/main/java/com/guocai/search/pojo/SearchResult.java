package com.guocai.search.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long recordCount;
	private List<Item> itemList;
	private Integer pageCount;
	private Integer curPage;
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	
}
