package com.tut.entity;

import javax.persistence.Table;

public class Business_Data{
	public String bName;
	public String bDetail;
	//@Table
	public Business_Data() {
		// TODO Auto-generated constructor stub
	}
	
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	
	public String getbDetail() {
		return bDetail;
	}
	public void setbDetail(String bDetail) {
		this.bDetail = bDetail;
	}
	
	@Override
	public String toString() {
		return "Business_Data [bName=" + bName + ", bDetail=" + bDetail + "]";
	}
	
	public Business_Data(String bName, String bDetail) {
		super();
		this.bName = bName;
		this.bDetail = bDetail;
	}

}
