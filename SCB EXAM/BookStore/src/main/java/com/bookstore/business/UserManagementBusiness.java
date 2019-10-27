package com.bookstore.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.business.inf.UserManagementBusinessInf;
import com.bookstore.dao.MstCustomerDAO;
import com.bookstore.model.dao.MstCustomer;
import com.bookstore.model.dto.Customer;
import com.bookstore.util.JwtUtility;

@Service
public class UserManagementBusiness implements UserManagementBusinessInf {

	@Autowired
	public UserManagementBusiness(AuthenticationManager authenticationManager, JwtUtility utility,
			UserDetailsBusiness jwtUserDetailsBusinessImp, MstCustomerDAO mstCustomerDAO, PasswordEncoder bcryptEncoder) {
		this.authenticationManager = authenticationManager;
		this.utility = utility;
		this.jwtUserDetailsBusinessImp = jwtUserDetailsBusinessImp;
		this.mstCustomerDAO = mstCustomerDAO;
		this.bcryptEncoder = bcryptEncoder;
	}

	private UserDetailsBusiness jwtUserDetailsBusinessImp;
	private AuthenticationManager authenticationManager;
	private JwtUtility utility;
	private MstCustomerDAO mstCustomerDAO;
	private PasswordEncoder bcryptEncoder;

	@Override
	public void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@Override
	public String getUserDetail(String username) throws Exception {
		final UserDetails userDetails = jwtUserDetailsBusinessImp.loadUserByUsername(username);
		final String token = utility.generateToken(userDetails);
		return token;
	}

	@Override
	public void register(Customer customer) {
		MstCustomer mstCustomer = new MstCustomer();
		mstCustomer.setUsername(customer.getUsername());
		mstCustomer.setPassword(bcryptEncoder.encode(customer.getPassword()));
		mstCustomer.setName(customer.getName());
		mstCustomer.setSurname(customer.getSurename());
		mstCustomer.setDateOfBirth(customer.getDateOfBirth());
		
		mstCustomerDAO.save(mstCustomer);
	}

}
