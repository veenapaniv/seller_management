package com.cis4660.seller_management.service;


public interface LoginService {

	public boolean validateCredentials(String email, String password);

	public String getUserId(String email);
}
