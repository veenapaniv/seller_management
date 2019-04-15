package com.cis4660.seller_management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cis4660.seller_management.dao.RegistrationDao;
import com.cis4660.seller_management.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	RegistrationDao registrationDao;

	@Override
	public boolean registerUser(String emailId, String password, String firstname, String lastname, String phone,
			String address) {
		return registrationDao.registerUser(emailId, password, firstname, lastname, phone,
				address);
	}
}
