package com.cis4660.seller_management.service;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cis4660.seller_management.model.Inventory;
import com.cis4660.seller_management.model.Order;

public interface DashboardService {

	public List<Inventory> getUsersProducts(String userId);
	public Map getTrendingProductsThisMonth();
	int getTodaysConfirmedOrdersSize();
	int getTodaysCancelledOrdersSize();
	int getTodaysReturnedOrdersSize();
	
	List<String> getTodaysConfirmedChannels();
	List<String> getTodaysCancelledChannels();
	List<String> getTodaysReturnedChannels();
	
	int getWeeksConfirmedOrdersSize();
	int getWeeksCancelledOrdersSize();
	int getWeeksReturnedOrdersSize();
	
	List<String> getWeeksConfirmedChannels();
	List<String> getWeeksCancelledChannels();
	List<String> getWeeksReturnedChannels();
	
	int getMonthsConfirmedOrdersSize();
	int getMonthsCancelledOrdersSize();
	int getMonthsReturnedOrdersSize();
	

	List<String> getMonthsConfirmedChannels();
	List<String> getMonthsCancelledChannels();
	List<String> getMonthsReturnedChannels();
	String getContact();

	

}
