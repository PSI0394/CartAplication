package com.tut.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tut.entity.Business;
@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {

}
