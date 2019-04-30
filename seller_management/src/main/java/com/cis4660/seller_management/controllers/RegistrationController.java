package com.cis4660.seller_management.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cis4660.seller_management.service.RegistrationService;

@Controller
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	/**
	 * This is the default signup controller
	 * @return
	 */
	@RequestMapping(value="/signup")
	public ModelAndView signUp() {
		return new ModelAndView("signup");
	}
	/**
	 * This method redirects to registration page on click of the registration link on the dashboard
	 * @return
	 */
	 @RequestMapping(value = "/sign_up_action", method = RequestMethod.GET)
	    public String redirect() {
	        return "redirect:signup";
	    }
	
	/**
	 * This method is to handle the registration functionality and save to DB.
	 * @param emailId
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param phone
	 * @param address
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public String handleLogin(@RequestParam String emailId,@RequestParam String password, @RequestParam String firstname, @RequestParam String lastname,@RequestParam String phone,@RequestParam String address, ModelMap model) {
		if(!registrationService.registerUser(emailId, password,firstname,lastname,phone,address)) {
			model.put("errorMessage", "Registration Unsuccessful");
			return "login";
		}
		return "login";
		
	}
}
