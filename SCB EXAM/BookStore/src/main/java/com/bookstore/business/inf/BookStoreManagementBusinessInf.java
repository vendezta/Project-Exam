package com.bookstore.business.inf;

import com.bookstore.model.dto.Books;
import com.bookstore.model.dto.Customer;
import com.bookstore.model.dto.Orders;

public interface BookStoreManagementBusinessInf {
	public Customer getCustomers(Long id);
	public void deleteOrder(Long id);
	public String getAmount(Orders orders);
	public Books getBooks();
}
