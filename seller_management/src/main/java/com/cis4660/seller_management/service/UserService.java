package com.cis4660.seller_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cis4660.seller_management.model.User;

@Service
public interface UserService {
	//void insertUser(User user);
	List<User> getAllUsers();
	User getUserById(String userId);
}
