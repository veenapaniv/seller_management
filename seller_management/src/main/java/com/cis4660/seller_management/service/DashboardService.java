package com.cis4660.seller_management.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cis4660.seller_management.model.Order;

public interface DashboardService {

	//public boolean loadAllDashboardData();
	
	public JSONObject getLastWeeksData();
	public JSONObject getLastMonthsData();
	public JSONObject getTodaysData();
	public JSONObject getUsersProducts(String userId);
	public JSONObject getTrendingProductsThisMonth();
	String getContact();
	

}
