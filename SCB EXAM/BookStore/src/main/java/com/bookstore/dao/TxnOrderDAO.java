package com.bookstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.model.dao.TxnOrder;

@Repository
public interface TxnOrderDAO extends JpaRepository<TxnOrder, Integer> {
	List<TxnOrder> findByMstCustomerId(long id);
	void deleteByMstCustomerId(long id);
	TxnOrder findById(long id);
}
