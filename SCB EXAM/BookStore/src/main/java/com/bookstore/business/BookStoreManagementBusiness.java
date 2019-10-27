package com.bookstore.business;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.business.inf.BookStoreManagementBusinessInf;
import com.bookstore.dao.MstBookDAO;
import com.bookstore.dao.MstCustomerDAO;
import com.bookstore.dao.TxnOrderDAO;
import com.bookstore.model.dao.MstCustomer;
import com.bookstore.model.dto.Books;
import com.bookstore.model.dto.Customer;
import com.bookstore.model.dto.Orders;
import com.bookstore.util.BookStoreUtil;

@Service
public class BookStoreManagementBusiness implements BookStoreManagementBusinessInf {

	@Autowired
	public BookStoreManagementBusiness(MstCustomerDAO customerDao, TxnOrderDAO txnOrderDao, MstBookDAO mstBookDAO) {
		this.customerDao = customerDao;
		this.txnOrderDao = txnOrderDao;
		this.mstBookDAO = mstBookDAO;
	}
	
	private MstCustomerDAO customerDao;
	private MstBookDAO mstBookDAO;
	private TxnOrderDAO txnOrderDao;

	@Override
	public Customer getCustomers(Long id) {
		MstCustomer mstCustomer = customerDao.findById(id);
		
		Customer customer = new Customer();
		customer.setUsername(mstCustomer.getUsername());
		customer.setPassword(mstCustomer.getPassword());
		customer.setName(mstCustomer.getName());
		customer.setSurename(mstCustomer.getSurname());
		customer.setDateOfBirth(mstCustomer.getDateOfBirth());
		customer.setBooks(txnOrderDao.findByMstCustomerId(mstCustomer.getId()).stream().map(txnOrder -> txnOrder.getMstBook().getId()).collect(Collectors.toList()));		
		return customer;
	}

	@Override
	public void deleteOrder(Long id) {
		txnOrderDao.findByMstCustomerId(id).stream().forEach(txnOrder -> {txnOrderDao.delete(txnOrder);});
	}

	@Override
	public String getAmount(Orders orders) {
	
		ArrayList<Double> prices = new ArrayList<Double>();
		orders.getOrders().stream().forEach(id -> {
			prices.add(txnOrderDao.findById(id).getMstBook().getPrice());
		});
		
		String json = "{ \"price\": \""+BookStoreUtil.convertDecimal(prices.stream().mapToDouble(Double::doubleValue).sum())+"\" }";

		return json;
	}

	@Override
	public Books getBooks() {
		Books books = new Books();
		books.setBooks(mstBookDAO.findAllByOrderByRecommendedDesc());
		return books;
	}
	
	

}
