package com.cis4660.seller_management.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Map;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cis4660.seller_management.model.Inventory;
import com.cis4660.seller_management.service.DashboardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/dashboard")
public class DashboardController {
	//Field Set dependency injection for an object of DashboardService
	@Autowired
	DashboardService dashboardService;
	
	private final Logger log = LoggerFactory.getLogger(DashboardController.class);
	
	/**
	 * This method is a GET request to redirect
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	public ModelAndView firstPage(ModelMap model,HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("userId")) {
					String userId = c.getValue();					
				}
			}
		}
		return new ModelAndView("dashboard");
	}
	/**
	 * This method is also a GET Request from the dashboard to load sales, channels, trending products and profit calculator.
	 * @param request
	 * @param inventory
	 * @param model
	 * @return
	 */
	@ModelAttribute
	@RequestMapping(method = RequestMethod.GET)
	public String productsData(HttpServletRequest request,@ModelAttribute("products") Inventory inventory,ModelMap model) {
		String userId = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("userId")) {
					userId = c.getValue();					
				}
			}
		}
		
		List<Inventory> products = dashboardService.getUsersProducts(userId);
		
		int todaysConfirmed = dashboardService.getTodaysConfirmedOrdersSize();
		int todaysCancelled = dashboardService.getTodaysCancelledOrdersSize();
		int todaysReturned = dashboardService.getTodaysReturnedOrdersSize();
		
		List<String> todaysConfirmedChannels = dashboardService.getTodaysConfirmedChannels();
		List<String> todaysCancelledChannels = dashboardService.getTodaysCancelledChannels();
		List<String> todaysReturnedChannels = dashboardService.getTodaysReturnedChannels();
		
		model.addAttribute("todaysConfirmed",todaysConfirmed);
		model.addAttribute("todaysCancelled",todaysCancelled);
		model.addAttribute("todaysReturned",todaysReturned);
		
		model.addAttribute("todaysConfirmedChannels", todaysConfirmedChannels);
		model.addAttribute("todaysCancelledChannels", todaysCancelledChannels);
		model.addAttribute("todaysReturnedChannels",todaysReturnedChannels);
		
		
		int weekConfirmed = dashboardService.getWeeksConfirmedOrdersSize();
		int weekCancelled = dashboardService.getWeeksCancelledOrdersSize();
		int weekReturned = dashboardService.getWeeksReturnedOrdersSize();
		
		List<String> weekConfirmedChannels = dashboardService.getWeeksConfirmedChannels();
		List<String> weekCancelledChannels = dashboardService.getWeeksCancelledChannels();
		List<String> weekReturnedChannels = dashboardService.getWeeksReturnedChannels();
		
		int monthsConfirmed = dashboardService.getMonthsConfirmedOrdersSize();
		int monthsCancelled = dashboardService.getMonthsCancelledOrdersSize();
		int monthsReturned = dashboardService.getMonthsReturnedOrdersSize();
		
		List<String> monthConfirmedChannels = dashboardService.getMonthsConfirmedChannels();
		List<String> monthCancelledChannels = dashboardService.getMonthsCancelledChannels();
		List<String> monthReturnedChannels = dashboardService.getMonthsReturnedChannels();
		
		
		model.addAttribute("todaysConfirmed",todaysConfirmed);
		model.addAttribute("todaysCancelled",todaysCancelled);
		model.addAttribute("todaysReturned",todaysReturned);
		
		model.addAttribute("todaysConfirmedChannels", todaysConfirmedChannels);
		model.addAttribute("todaysCancelledChannels", todaysCancelledChannels);
		model.addAttribute("todaysReturnedChannels",todaysReturnedChannels);
		
		
		model.addAttribute("weekConfirmed",weekConfirmed);
		model.addAttribute("weekCancelled",weekCancelled);
		model.addAttribute("weekReturned",weekReturned);
		
		model.addAttribute("weekConfirmedChannels", weekConfirmedChannels);
		model.addAttribute("weekCancelledChannels", weekCancelledChannels);
		model.addAttribute("weekReturnedChannels",weekReturnedChannels);
		
		
		model.addAttribute("monthsConfirmed",monthsConfirmed);
		model.addAttribute("monthsCancelled",monthsCancelled);
		model.addAttribute("monthsReturned",monthsReturned);
		
		model.addAttribute("monthConfirmedChannels", monthConfirmedChannels);
		model.addAttribute("monthCancelledChannels", monthCancelledChannels);
		model.addAttribute("monthReturnedChannels",monthReturnedChannels);
		
		model.addAttribute("products", products);
		model.addAttribute("items",dashboardService.getTrendingProductsThisMonth());
		return "dashboard";
		
	}
	/**
	 * This method is to return the trending products in the current month
	 * @return
	 */
	@RequestMapping(value="trendingProducts",method=RequestMethod.GET)
	@ResponseBody
	public String getTrendingProductsThisMonth() {
		
		return dashboardService.getTrendingProductsThisMonth().toString();
		
	}
	

}
