package com.MyshoppingMall.bbs.vo;

import java.util.List;

public class BbsPage {


	private int total;
	private int currentPage;
	private List<Bbs> content;
	private int totalPage;
	private int startPage;
	private int endPage;
	private String searchValue;
	private String searchFilter;
	
	public BbsPage() {}
	
	public BbsPage(int total, int currentPage, int size, List<Bbs> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if (total == 0) {
			
			totalPage = 0;
			startPage = 0;
			endPage = 0;
			
		} else {
			
			totalPage = total / size;
			if (total % size > 0) {
				totalPage++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5*5+1;
			if (modVal == 0) {
				startPage -= 5;
			}
			endPage = startPage + 4;
			if(endPage > totalPage) {
				endPage = totalPage;
			}
		}
		
	}
	public BbsPage(int total, int currentPage, int size, List<Bbs> content, String searchFilter, String searchValue) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		this.searchFilter = searchFilter;
		this.searchValue = searchValue;
		if (total == 0) {
			
			totalPage = 0;
			startPage = 0;
			endPage = 0;
			
		} else {
			
			totalPage = total / size;
			if (total % size > 0) {
				totalPage++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5*5+1;
			if (modVal == 0) {
				startPage -= 5;
			}
			endPage = startPage + 4;
			if(endPage > totalPage) {
				endPage = totalPage;
			}
		}
		
	}

	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}
	public String getSearchFilter() {
		return searchFilter;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	public int getTotal() {
		return total;
	}
	
	public boolean hasNoBbs() {
		return total == 0;
	}
	
	public boolean hasBbs() {
		return total > 0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public List<Bbs> getContent() {
		return content;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	@Override
	public String toString() {
		return "BbsPage [total=" + total + ", currentPage=" + currentPage + ", content=" + content + ", totalPage="
				+ totalPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	
}
