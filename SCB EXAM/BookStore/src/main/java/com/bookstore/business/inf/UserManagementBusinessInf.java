package com.bookstore.business.inf;

import com.bookstore.model.dto.Customer;

public interface UserManagementBusinessInf {
	public void authenticate(String username, String password) throws Exception;
	public String getUserDetail(String username) throws Exception;
	public void register(Customer customer);
}
