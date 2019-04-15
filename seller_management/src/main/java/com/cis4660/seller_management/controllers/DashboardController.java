package com.cis4660.seller_management.controllers;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cis4660.seller_management.service.DashboardService;

@Controller
public class DashboardController {
	@Autowired
	DashboardService dashboardService;
	
	private final Logger log = LoggerFactory.getLogger(DashboardController.class);
	
	@RequestMapping(value="/dashboard")
	public ModelAndView firstPage(ModelMap model) {
		return new ModelAndView("dashboard");
	}
	
	@RequestMapping(value="/todaysOrders", method = RequestMethod.GET)
	@ResponseBody
	public String todaysData() {
		
		String todaysData = dashboardService.getTodaysData().toString();
		return todaysData;
		
	}
	
	@RequestMapping(value="/weeks_orders", method = RequestMethod.GET)
	@ResponseBody
	public String weeksData() {
		
		String weeksData = dashboardService.getLastWeeksData().toString();
		return weeksData;
		
	}
	
	@RequestMapping(value="/months_orders", method = RequestMethod.GET)
	@ResponseBody
	public String monthsData() {
		
		String monthsData = dashboardService.getLastWeeksData().toString();
		return monthsData;
		
	}
	
	@RequestMapping(value="/products", method = RequestMethod.GET)
	@ResponseBody
	public String productsData(@RequestParam(value="userId", required=true) String userId) {
		
		String products = dashboardService.getUsersProducts(userId).toString();
		return products;
		
	}
	
	@RequestMapping(value="trendingProducts",method=RequestMethod.GET)
	@ResponseBody
	public String getTrendingProductsThisMonth() {
		
		return dashboardService.getTrendingProductsThisMonth().toString();
		
	}

}
