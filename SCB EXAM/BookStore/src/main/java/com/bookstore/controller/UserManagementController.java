package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.business.inf.UserManagementBusinessInf;
import com.bookstore.model.dto.Customer;
import com.bookstore.model.dto.JwtResponse;

@RestController
@CrossOrigin
public class UserManagementController {
	
	@Autowired
	private UserManagementBusinessInf authenticationBusinessInf;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Customer authenticationRequest) throws Exception {

		authenticationBusinessInf.authenticate(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());

		String token = authenticationBusinessInf.getUserDetail(authenticationRequest.getUsername());

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody Customer customer) throws Exception {
		authenticationBusinessInf.register(customer);
		return ResponseEntity.ok().build();
	}

}
