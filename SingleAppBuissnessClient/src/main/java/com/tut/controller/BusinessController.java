package com.tut.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tut.dao.BusinessRepository;
import com.tut.dao.ClientRepository;
import com.tut.entity.Business;
import com.tut.entity.Business_Data;
import com.tut.entity.Client;
import com.tut.entity.Client_Details;

@CrossOrigin
@Transactional
@RestController
@RequestMapping("/Business")
public class BusinessController {
	@Autowired
	SessionFactory sFactory;
	
	@Autowired
	BusinessRepository b_repo;
	
	@Autowired
	ClientRepository c_repo;
	
	@GetMapping("/BusinessList")
	public List<Business_Data> businessList() {

		String hqlString = "Select businessName,bDetails from Business ";
		Session session = sFactory.openSession();
		Query query = session.createQuery(hqlString);
		List<Business_Data> list =new ArrayList<>();
		list= query.list();
		return list;
	}
	
	@GetMapping("/BusinessRelatedClientList/{bname}")
	public List<Object> businessRelatedClientList(@PathVariable String bname) {

		String hqlString = "Select clients from Business where businessName= :name";
		Session session = sFactory.openSession();
		Query query = session.createQuery(hqlString);
		query.setString("name", bname);
		List<Client> list1 =new ArrayList<>();
		list1= query.list();
	
		List<Business_Data> list2=new ArrayList<>();
		List<Object> list3=new ArrayList<>();
		for (int i = 0; i < list1.size(); i++) {
			int id=list1.get(i).getClientId();
			Query query1 = session.createQuery("Select clientName, cDetails from Client where clientId= :i");
			query1.setInteger("i", id);
			list2= query1.list();
			list3.add(list2);
			
		}
		
		return list3;
	}

	@GetMapping("/ClientRelatedBusinessList/{cname}")
	public List<Object> clientRelatedBusinessList(@PathVariable String cname) {

		String hqlString = "Select business from Client where clientName= :name";
		Session session = sFactory.openSession();
		Query query = session.createQuery(hqlString);
		query.setString("name", cname);
		List<Business> list =new ArrayList<>();
		list= query.list();
		
		List<Business_Data> list2=new ArrayList<>();
		List<Object> list3=new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			int id=list.get(i).getBuisinessId();
			Query query1 = session.createQuery("Select businessName, bDetails from Business where businessId= :i");
			query1.setInteger("i", id);
			list2= query1.list();
			list3.add(list2);
			
		}
		
		return list3;
	}
	
	
	@DeleteMapping("/DeleteBusiness/{bname}")
	public String deleteBusiness(@PathVariable String bname) {
		String hqlString = " from Business where businessName= :name";
		Session session = sFactory.openSession();
		Query query = session.createQuery(hqlString);
		query.setString("name", bname);
        List<Business> list=query.list();
        Business business1=list.get(0);
       // Business business=new Business(list.get(0).getBuisinessId(), list.get(0).getbDetails(), list.get(0).getClients());
        
		session.delete(business1);
		return "Id = "+business1.getBuisinessId()+" Name = "+business1.getBuisinessName()+" BusinessCart Delete";
	}
	
	@PutMapping("/InsertBusiness")
	public ResponseEntity<String> insertBusiness(@RequestBody Business business)
	{
		Session session=sFactory.getCurrentSession();
		System.out.println(business.toString());
		session.saveOrUpdate(business);
		String tString="Record inserted";
		return new ResponseEntity<String>(tString, HttpStatus.CREATED);
		
	}
}
