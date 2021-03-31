package com.tut.entity;

public class Client_Details {
	
	private String cName;
	
	private String cDetail;
	
	public Client_Details() {
		// TODO Auto-generated constructor stub
	}

	public Client_Details(String cName, String cDetail) {
		super();
		this.cName = cName;
		this.cDetail = cDetail;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcDetail() {
		return cDetail;
	}

	public void setcDetail(String cDetail) {
		this.cDetail = cDetail;
	}
	

}
