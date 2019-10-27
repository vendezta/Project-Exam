package com.bookstore.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.model.dao.MstCustomer;

@Repository
public interface MstCustomerDAO extends CrudRepository<MstCustomer, Integer> {
	MstCustomer findByUsername(String username);
	MstCustomer findById(Long id);
}