package com.cis4660.seller_management.dao;

import java.util.List;

import com.cis4660.seller_management.model.User;

public interface UserDao {
	
	List<User> getAllUsers();
	User getUserById(String userId);
	User getUserByEmail(String email);

}
