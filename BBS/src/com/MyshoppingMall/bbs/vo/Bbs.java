package com.MyshoppingMall.bbs.vo;

public class Bbs {

	private int bbsNo;
	private int bbsId;
	private String bbsTitle;
	private User user;
	private String bbsDate;
	private String bbsContent;
	private int bbsAvailable;
	
	public Bbs(){}

	public void setBbsNo(int bbsNo) {
		this.bbsNo = bbsNo;
	}
	public int getBbsNo() {
		return bbsNo;
	}
	public int getBbsId() {
		return bbsId;
	}

	public void setBbsId(int bbsId) {
		this.bbsId = bbsId;
	}

	public String getBbsTitle() {
		return bbsTitle;
	}

	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBbsDate() {
		return bbsDate;
	}
	public void setBbsDate(String bbsDate) {
		this.bbsDate = bbsDate;
	}
	
	public String getBbsContent() {
		return bbsContent;
	}

	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}

	public int getBbsAvailable() {
		return bbsAvailable;
	}

	public void setBbsAvailable(int bbsAvailable) {
		this.bbsAvailable = bbsAvailable;
	}
	
	
	
	
}
