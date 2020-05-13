package com.MyshoppingMall.bbs.vo;

import java.util.List;

public class BbsPage {


	private int total;
	private int currentPage;
	private List<Bbs> content;
	private int totalPage;
	private int startPage;
	private int endPage;
	
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
