package com.cis4660.seller_management.service;

public interface RegistrationService {

	boolean registerUser(String emailId, String password, String firstname, String lastname, String phone,
			String address);
	

}
