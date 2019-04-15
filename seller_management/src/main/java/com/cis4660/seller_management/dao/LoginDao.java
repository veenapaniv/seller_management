package com.cis4660.seller_management.dao;

public interface LoginDao {
	boolean validateCredentials(String email, String password);
	public boolean validatePassword(String email,String password);
	public String getUserID(String email);

}
