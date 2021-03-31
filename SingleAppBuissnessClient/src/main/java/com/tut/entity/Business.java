package com.tut.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.catalina.Contained;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;
@Entity
@Table
public class Business {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int businessId;
	//@Column( nullable = false)
	private String businessName;
	
	private String bDetails;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@JoinTable(name = "CtoB",
    joinColumns = { @JoinColumn(name = "businessId",referencedColumnName = "businessId") },
    inverseJoinColumns = {@JoinColumn(name = "clientId",referencedColumnName = "clientId")}) 
	
	@JoinColumn(name = "cId",referencedColumnName = "clientId")
	private Set<Client> clients=new HashSet<>();;
	
	public Business() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Business(int businessId, String businessName, Set<Client> clients) {
		super();
		this.businessId = businessId;
		this.businessName = businessName;
		this.clients = clients;
	}
	
	
	public String getbDetails() {
		return bDetails;
	}
	public void setbDetails(String bDetails) {
		this.bDetails = bDetails;
	}
	public int getBuisinessId() {
		return businessId;
	}
	public void setBuisinessId(int buisinessId) {
		this.businessId = buisinessId;
	}
	public String getBuisinessName() {
		return businessName;
	}
	public void setBuisinessName(String buisinessName) {
		this.businessName = buisinessName;
	}
	public Set<Client> getClients() {
		return clients;
	}
	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
//	@Override
//	public String toString() {
//		return "Buissness [buisinessId=" + businessId + ", buisinessName=" + businessName + ", clients=" + clients
//				+ "]";
//	}
	
	
	

}
