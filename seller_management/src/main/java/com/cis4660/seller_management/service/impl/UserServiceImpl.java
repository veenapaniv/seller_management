package com.cis4660.seller_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cis4660.seller_management.dao.UserDao;
import com.cis4660.seller_management.model.User;
import com.cis4660.seller_management.service.UserService;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public List<User> getAllUsers() {
//		List<User> userList = userDao.getAllUsers();
//		for(User user :userList )
//		{
//		System.out.println(user);
//		}
		return userDao.getAllUsers();
			
		
	}

	@Override
	public User getUserById(String userId) {
		return userDao.getUserById(userId);
	}

}
