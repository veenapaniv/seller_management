package com.cis4660.seller_management.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cis4660.seller_management.controllers.DashboardController;
import com.cis4660.seller_management.dao.DashboardDao;
import com.cis4660.seller_management.model.Inventory;
import com.cis4660.seller_management.service.DashboardService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class DashboardServiceImpl implements DashboardService {
	private final Logger log = LoggerFactory.getLogger(DashboardServiceImpl.class);
	@Autowired
	DashboardDao dashboardDao;

	@Override
	public JSONObject getTodaysData() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		//get the sales data
		int confirmedSales = dashboardDao.getTodaysConfirmedOrders().size();
		int cancelledSales = dashboardDao.getTodaysCancelledOrders().size();
		int returnedSales = dashboardDao.getTodaysReturnedOrders().size();
		
		//get channels data
		List<String> confirmedChannelList = dashboardDao.getTodaysConfirmedChannels();
		List<String> cancelledChannelList = dashboardDao.getTodaysCancelledChannels();
		List<String> returnedChannelList = dashboardDao.getTodaysReturnedChannels();
		
		//convert them to String
		String confirmedChannelJson = gson.toJson(confirmedChannelList);
		String cancelledChannelJson = gson.toJson(cancelledChannelList);
		String returnedChannelJson = gson.toJson(returnedChannelList);
		
		//Put all data into JSONObject
		JSONObject todaysJson = new JSONObject();
		todaysJson.put("confirmedSales", confirmedSales);
		todaysJson.put("cancelledSales", cancelledSales);
		todaysJson.put("returnedSales", returnedSales);
		JSONArray confirmedChannelArray = new JSONArray(confirmedChannelJson);
		JSONArray cancelledChannelArray = new JSONArray(cancelledChannelJson);
		JSONArray returnedChannelArray = new JSONArray(returnedChannelJson);
		todaysJson.put("confirmedChannels", confirmedChannelArray);
		todaysJson.put("cancelledChannels", cancelledChannelArray);
		todaysJson.put("returnedChannels", returnedChannelArray);
		
		return todaysJson;
		
	}
	
	@Override
	public JSONObject getLastWeeksData() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		//get the sales data
		int confirmedSales = dashboardDao.getLastWeeksConfirmedOrders().size();
		int cancelledSales = dashboardDao.getLastWeeksCancelledOrders().size();
		int returnedSales = dashboardDao.getLastWeeksReturnedOrders().size();
		
		//get channels data
		List<String> confirmedChannelList = dashboardDao.getWeeksConfirmedChannels();
		List<String> cancelledChannelList = dashboardDao.getWeeksCancelledChannels();
		List<String> returnedChannelList = dashboardDao.getWeeksReturnedChannels();
		
		//convert them to String
		String confirmedChannelJson = gson.toJson(confirmedChannelList);
		String cancelledChannelJson = gson.toJson(cancelledChannelList);
		String returnedChannelJson = gson.toJson(returnedChannelList);
		
		//Put all data into JSONObject
		JSONObject weeksJson = new JSONObject();
		weeksJson.put("confirmedSales", confirmedSales);
		weeksJson.put("cancelledSales", cancelledSales);
		weeksJson.put("returnedSales", returnedSales);
		JSONArray confirmedChannelArray = new JSONArray(confirmedChannelJson);
		JSONArray cancelledChannelArray = new JSONArray(cancelledChannelJson);
		JSONArray returnedChannelArray = new JSONArray(returnedChannelJson);
		weeksJson.put("confirmedChannels", confirmedChannelArray);
		weeksJson.put("cancelledChannels", cancelledChannelArray);
		weeksJson.put("returnedChannels", returnedChannelArray);
		
		return weeksJson;
		
	}
	
	@Override
	public JSONObject getLastMonthsData() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		//get the sales data
		int confirmedSales = dashboardDao.getLastMonthsConfirmedOrders().size();
		int cancelledSales = dashboardDao.getLastMonthsCancelledOrders().size();
		int returnedSales = dashboardDao.getLastMonthsReturnedOrders().size();
		
		//get channels data
		List<String> confirmedChannelList = dashboardDao.getMonthsConfirmedChannels();
		List<String> cancelledChannelList = dashboardDao.getMonthsCancelledChannels();
		List<String> returnedChannelList = dashboardDao.getMonthsReturnedChannels();
		
		//convert them to String
		String confirmedChannelJson = gson.toJson(confirmedChannelList);
		String cancelledChannelJson = gson.toJson(cancelledChannelList);
		String returnedChannelJson = gson.toJson(returnedChannelList);
		
		//Put all data into JSONObject
		JSONObject monthsJson = new JSONObject();
		monthsJson.put("confirmedSales", confirmedSales);
		monthsJson.put("cancelledSales", cancelledSales);
		monthsJson.put("returnedSales", returnedSales);
		JSONArray confirmedChannelArray = new JSONArray(confirmedChannelJson);
		JSONArray cancelledChannelArray = new JSONArray(cancelledChannelJson);
		JSONArray returnedChannelArray = new JSONArray(returnedChannelJson);
		monthsJson.put("confirmedChannels", confirmedChannelArray);
		monthsJson.put("cancelledChannels", cancelledChannelArray);
		monthsJson.put("returnedChannels", returnedChannelArray);
		
		
		return monthsJson;
		
	}
	
	@Override
	public JSONObject getUsersProducts(String userId) {
//		JSONObject productsJson = new JSONObject();
//		GsonBuilder gsonBuilder = new GsonBuilder();
//		Gson gson = gsonBuilder.create();
		JSONObject productCostJson = new JSONObject();
		JSONObject eachProductSales = new JSONObject();
		String str = "[{";

		
		HashMap<String,Float> productCostList = dashboardDao.getUsersProducts(userId);
//		String productIdStr = gson.toJson(productIdList);
//		JSONArray productIdArray = new JSONArray(productIdStr);
//		productsJson.put("productId", productIdArray);
//		System.out.println("this is users products"+productsJson);
		
		for(Entry<String, Float> productCost :productCostList.entrySet()) {
			 str = str+ "\"name\""+":"+productCost.getKey();
			 str = str+","+"\"cost\""+":"+productCost.getValue();
			 str = str+"}]";
			 JSONArray productSalesArr = new JSONArray(str);
			 eachProductSales.put("product", productSalesArr);
			
		}
		
	
		productCostJson.put("productCost", eachProductSales);  
		return productCostJson;
	}
	
	@Override
	public JSONObject getTrendingProductsThisMonth() {
		
		JSONObject productSales = new JSONObject();
		JSONObject eachProductSales = new JSONObject();
		String str = "[{";

		
		HashMap<String,Integer> productSalesMap = dashboardDao.getTrendingProductsThisMonth();

		for(Entry<String, Integer> productSale :productSalesMap.entrySet()) {
			 str = str+ "\"name\""+":"+productSale.getKey();
			 str = str+","+"\"sales\""+":"+productSale.getValue();
			 str = str+"}]";
			 JSONArray productSalesArr = new JSONArray(str);
			 eachProductSales.put("product", productSalesArr);
			
		}
		
	
		productSales.put("productSales", eachProductSales);  
		System.out.println("productSales"+productSales);
		
		//System.out.println("productSalesJson"+productSales.toString());
		return productSales;
		
	}

}
