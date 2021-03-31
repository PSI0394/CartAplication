package com.tut.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tut.entity.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
