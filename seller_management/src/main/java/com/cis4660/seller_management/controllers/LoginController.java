package com.cis4660.seller_management.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cis4660.seller_management.model.User;
import com.cis4660.seller_management.service.DashboardService;
import com.cis4660.seller_management.service.LoginService;
import com.cis4660.seller_management.service.UserService;
import com.cis4660.seller_management.util.CookieUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	DashboardService dashboardService;
	
	@Autowired
	LoginService service;
	
	/**
	 * This method handles the Get request 
	 * where it returns the login jsp when the url is redirected to login**/
	@RequestMapping(value="/login")
	public ModelAndView firstPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("login");
		String username = CookieUtil.getCookieValue("seller-username", "", request);
		String password = CookieUtil.getCookieValue("seller-pwd", "", request);;
		model.addObject("username", username);
		model.addObject("password", password);
		return model;
	}
	
	/** This method handles the POST request, the credentials are 
	validated and the user on succesful authentication is redirected to dashboard. **/
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String handleLogin(@RequestParam String email,@RequestParam String pwd, @RequestParam(value="remember", required=false) boolean remember, ModelMap model,HttpServletResponse response) {
		System.out.println("remember: "+remember);
	
		if(!service.validateCredentials(email, pwd)) {
			//model.put("errorMessage", "Invalid Credentials");
			model.addAttribute("errorMessage","Invalid Credentials");
			return "login";
		}
		if(remember) {
			rememberUsernamePwd(email,pwd, response);
		}
		model.put("userID", service.getUserId(email));
		CookieUtil userCookie = new CookieUtil("userId",userService.getUserByEmail(email).getUserId());
		response.addCookie(userCookie);
		return "redirect:dashboard";
	}
	
	public void rememberUsernamePwd(String email, String pwd, HttpServletResponse response) {
		CookieUtil usernameCookie = new CookieUtil("seller-username", email);
		CookieUtil pwdCookie = new CookieUtil("seller-pwd", pwd);
		response.addCookie(usernameCookie);
		response.addCookie(pwdCookie);
	}
	
	@RequestMapping(value="/contact")
	public ModelAndView contact() {
		String contact = dashboardService.getContact();
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> map = null;
		try {
			map = mapper.readValue(contact, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ModelAndView model = new ModelAndView("contact");
		model.addObject("contact", map);
		return model;
	}
	
}
