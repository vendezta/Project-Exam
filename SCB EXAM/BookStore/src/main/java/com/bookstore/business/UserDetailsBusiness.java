package com.bookstore.business;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookstore.dao.MstCustomerDAO;
import com.bookstore.model.dao.MstCustomer;

@Service
public class UserDetailsBusiness implements UserDetailsService {

	@Autowired
	private MstCustomerDAO customerDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MstCustomer mstCustomer = customerDao.findByUsername(username);
		if (mstCustomer == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(mstCustomer.getUsername(), mstCustomer.getPassword(),
				new ArrayList<>());
	}

}