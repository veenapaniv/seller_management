package com.cis4660.seller_management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cis4660.seller_management.model.User;
import com.cis4660.seller_management.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login")
	public ModelAndView firstPage() {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/users")
	public ModelAndView getUsers() {
		List<User> users = userService.getAllUsers();
		ModelAndView model = new ModelAndView("dashboard");
		for(User user :users )
			{
			System.out.println(user);
			}
		model.addObject("allUsers",users);
		return model;
	}

}
