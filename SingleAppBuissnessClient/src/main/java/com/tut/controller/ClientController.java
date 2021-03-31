package com.tut.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tut.dao.BusinessRepository;
import com.tut.dao.ClientRepository;
import com.tut.entity.Business_Data;

@CrossOrigin
@Transactional
@RestController
@RequestMapping("/Client")
public class ClientController {
	@Autowired
	SessionFactory sFactory;
	
	@Autowired
	BusinessRepository b_repo;
	
	@Autowired
	ClientRepository c_repo;
	
	@GetMapping("/clientList")
	public List<Business_Data> clientList() {
		String hqlString = "Select clientName,cDetails from Client ";
		Session session = sFactory.openSession();
		Query query = session.createQuery(hqlString);

		List<Business_Data> list = query.list();
		return list;
	}

}
