package com.tut.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.tut.dao.BusinessRepository;
import com.tut.dao.ClientRepository;
import com.tut.entity.Business;
import com.tut.entity.Business_Data;
import com.tut.entity.Client;
class A{
	Business_Data business_Data;
	
}
@CrossOrigin
@RestController
@Transactional

public class MappingController {

	private static final RequestMethod[] POST = null;
	@Autowired
	SessionFactory sFactory;
	@Autowired
	BusinessRepository b_repo;
	
	@Autowired
	ClientRepository c_repo;

	@GetMapping("/get")
	public Business saveString() {
		Session session = sFactory.openSession();
		return session.get(Business.class, 1);

	}

	@GetMapping("/show")
	public List<String> show() {
//		Session session=sFactory.getCurrentSession();
//		
//		return session.get(Business.class, id);
		String hqlString = "Select businessName from Business ";
		Session session = sFactory.openSession();
		Query query = session.createQuery(hqlString);

		List<String> list = query.list();
		return list;
	}

	@GetMapping("/showc")
	public List<String> showclient() {
//		Session session=sFactory.getCurrentSession();
//		
//		return session.get(Business.class, id);
		String hqlString = "Select clientName from Client ";
		Session session = sFactory.openSession();
		Query query = session.createQuery(hqlString);

		List<String> list = query.list();
		return list;
	}

	@GetMapping("/showClient/{Bname}")
	public Set<Client> showClient(@PathVariable String Bname) {
		String hqlString = " from Business where businessName= :b";
		Session session = sFactory.openSession();

		Query query = session.createQuery(hqlString);
		query.setString("b", Bname);
		List<Business> list = query.list();
		Business business = list.get(0);
       // System.out.println(business.toString());
		Set<Client> list2 = business.getClients();

		System.err.println(list2);
		Iterator<Client> itr = list2.iterator();

		Business_Data clientsobj[] =new Business_Data[2] ;
		List<Business_Data> clients=new ArrayList<>();
		Business_Data b=null;
		List<Integer> idIntegers=new ArrayList<>();
		// traversing over HashSet
		int i = 0;
		while (itr.hasNext()) {
			Client client=itr.next();
			if (client!=null) {
				
			
			System.out.println(client);
			//for (int i = 0; client==null; i++) {
				System.out.println(client.toString());
//				clients[i].setbName( client.getClientName());
//				clients[i].setbDetail(client.getcDetails());
				
//				b.setbName( client.getClientName());
//				b.setbDetail(client.getcDetails());
				System.out.println(client);
				idIntegers.add(client.getClientId());
				b=new Business_Data(client.getClientName(),client.getcDetails());
				clients.add(b);
				clientsobj[i]=b;
				System.out.println("**************************************");
				System.out.println(clients);
				System.out.println("**************************************");
				//clients[i]=new Business_Data(client.getClientName(),client.getcDetails());
//				clients.toString();
				i=i+1;
			//}
			//Business_Data b=new Business_Data();
//			b.setbName( client.getClientName());
//			b.setbDetail(client.getcDetails());
//			clients.add(b);
			}
		}
		
		
		return list2;
	}

	@GetMapping("/showBusiness/{Cname}")
	public List<String> showBusiness(@PathVariable String Cname) {
		String hqlString = " from Client where clientName= :b";
		Session session = sFactory.openSession();

		Query query = session.createQuery(hqlString);
		query.setString("b", Cname);

		List<Client> list = query.list();

		Client client = list.get(0);
		System.out.println(client.toString());
		// int id = client.getClientId();
		Set<Business> list2 = client.getBusiness();
        System.out.println(list2);
		Iterator<Business> itr = list2.iterator();

		List<String> bList = new ArrayList();
		
		// traversing over HashSet
		while (itr.hasNext()) {
			String string = itr.next().getBuisinessName();
			bList.add(string);
			System.out.println(string);
		}
		return bList;

	}
	
	@GetMapping("getoneclient")
	public Client client1233()
	{
		Session session=sFactory.getCurrentSession();
		return session.get(Client.class,1);
		
	}
	//@RequestMapping(value = "/Save", method = POST, produces = "application/json")
	@PutMapping("/Save")
	public ResponseEntity<String> insertBusiness(@RequestBody Business business)
	{
		Session session=sFactory.getCurrentSession();
	
		System.out.println(business.toString());
		session.saveOrUpdate(business);
	
		String tString=" number of id record inserted";
	
		return new ResponseEntity<String>(tString, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("DeleteClient/{cname}")
	public ResponseEntity<String> deleteClient(@PathVariable String cname){
		
		String hqlString = " from Client where clientName= :b";
		Session session = sFactory.openSession();
		Query query = session.createQuery(hqlString);
		query.setString("b", cname);
		List<Client> list = query.list();
		Client client = list.get(0);
		c_repo.delete(client);
		return new ResponseEntity<String>("Record Deleted",HttpStatus.CREATED);
	}
	
	@DeleteMapping("DeleteBusiness/{bname}")
	public ResponseEntity<String> deleteBusiness(@PathVariable String bname){
		
		String hqlString = " from Business where businessName= :b";
		Session session = sFactory.openSession();
		Query query = session.createQuery(hqlString);
		query.setString("b", bname);
		List<Business> list = query.list();
		Business business = list.get(0);
		System.out.println(business);
		
		b_repo.delete(business);
		
		return new ResponseEntity<String>("Record Deleted",HttpStatus.CREATED);	
		
			
	}
	
	
}
