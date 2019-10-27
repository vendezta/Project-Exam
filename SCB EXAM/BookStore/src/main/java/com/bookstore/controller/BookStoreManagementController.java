package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.business.inf.BookStoreManagementBusinessInf;
import com.bookstore.model.dao.MstCustomer;
import com.bookstore.model.dto.Books;
import com.bookstore.model.dto.Customer;
import com.bookstore.model.dto.Orders;

import io.swagger.annotations.ApiOperation;

@RestController
public class BookStoreManagementController {

	@Autowired
	private BookStoreManagementBusinessInf customerManagementBusinessInf;
	
	@ApiOperation(value = "Get Customer Information", response = MstCustomer.class)
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomers(@PathVariable Long id) {
		return ResponseEntity.ok(customerManagementBusinessInf.getCustomers(id));
	}
	
	@ApiOperation(value = "Delete Books Order History")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
		customerManagementBusinessInf.deleteOrder(id);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Get Amount for Books Ordered", response = String.class)
	@RequestMapping(value = "/users/orders", method = RequestMethod.POST)
	public ResponseEntity<String> getAmountForOrders(@RequestBody Orders orders) {
		return ResponseEntity.ok(customerManagementBusinessInf.getAmount(orders));
	}
	
	@ApiOperation(value = "Get Books", response = String.class)
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ResponseEntity<Books> getBooks() {
		return ResponseEntity.ok(customerManagementBusinessInf.getBooks());
	}
}
