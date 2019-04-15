package com.cis4660.seller_management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cis4660.seller_management.dao.LoginDao;
import com.cis4660.seller_management.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginDao loginDao;

	@Override
	public boolean validateCredentials(String email, String password) {
		return loginDao.validateCredentials(email, password);
	}

	@Override
	public String getUserId(String email) {
		if(null != loginDao.getUserID(email) && !loginDao.getUserID(email).isEmpty() ) {
			return loginDao.getUserID(email);
		}
		else {
		return "";
		}
	}
	

}
