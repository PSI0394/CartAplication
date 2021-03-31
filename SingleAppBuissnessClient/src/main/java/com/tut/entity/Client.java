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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.core.sym.Name;


@Entity
@Table
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;
	
	private String clientName;
	
	private String cDetails;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "CtoB",   
    joinColumns = { @JoinColumn(name = "clientId",referencedColumnName = "clientId") },
    inverseJoinColumns = {@JoinColumn(name = "businessId",referencedColumnName = "businessId")}) 
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	
	@JoinColumn(name = "bId",referencedColumnName = "businessId")
	private Set<Business> business=new HashSet<>();;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Client(int clientId, String clientName, String cDetails, Set<Business> business) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.cDetails = cDetails;
		this.business = business;
	}



	public String getcDetails() {
		return cDetails;
	}
	public void setcDetails(String cDetails) {
		this.cDetails = cDetails;
	}
	public Set<Business> getBusiness() {
		return business;
	}
	public void setBusiness(Set<Business> business) {
		this.business = business;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}



	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", cDetails=" + cDetails + ", business="
				+ business + "]";
	}
	
	
	
	

}
