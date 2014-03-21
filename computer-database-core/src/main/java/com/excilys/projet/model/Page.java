package com.excilys.projet.model;

import java.util.List;

public class Page<E, ORDER_TYPE> {
	private List<E> items;
	private int recordCount;
	private int totalPage;
	private int currentPage;
	private ORDER_TYPE order;
	
	private String search;

	public List<E> getItems() {
		return items;
	}

	public void setItems(List<E> items) {
		this.items = items;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public ORDER_TYPE getOrder() {
		return order;
	}

	public void setOrder(ORDER_TYPE order) {
		this.order = order;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	

}
