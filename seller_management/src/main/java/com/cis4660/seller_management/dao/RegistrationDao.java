package com.cis4660.seller_management.dao;

public interface RegistrationDao {

	boolean registerUser(String emailId, String password, String firstname, String lastname, String phone,
			String address);

}
