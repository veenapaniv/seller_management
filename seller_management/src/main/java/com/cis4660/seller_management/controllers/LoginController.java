package com.cis4660.seller_management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cis4660.seller_management.model.User;
import com.cis4660.seller_management.service.LoginService;
import com.cis4660.seller_management.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	LoginService service;
	
	@RequestMapping(value="/login")
	public ModelAndView firstPage() {
		return new ModelAndView("login");
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String handleLogin(@RequestParam String email,@RequestParam String pwd, ModelMap model) {
		if(!service.validateCredentials(email, pwd)) {
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}
		model.put("userID", service.getUserId(email));
		return "dashboard";
		
	}
}
