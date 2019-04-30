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
	public int getTodaysConfirmedOrdersSize() {
		return dashboardDao.getTodaysConfirmedOrders().size();
	}
	
	@Override
	public int getTodaysCancelledOrdersSize() {
		return dashboardDao.getTodaysCancelledOrders().size();
	}
	
	@Override
	public int getTodaysReturnedOrdersSize() {
		return dashboardDao.getTodaysReturnedOrders().size();
	}
	
	@Override
	public List<String> getTodaysConfirmedChannels(){
		return dashboardDao.getTodaysConfirmedChannels();
	}
	@Override
	public List<String> getTodaysCancelledChannels(){
		return dashboardDao.getTodaysCancelledChannels();
	}
	@Override
	public List<String> getTodaysReturnedChannels(){
		return dashboardDao.getTodaysReturnedChannels();
	}
	
	//weeks data
	@Override
	public int getWeeksConfirmedOrdersSize() {
		return dashboardDao.getLastWeeksConfirmedOrders().size();
	}
	
	@Override
	public int getWeeksCancelledOrdersSize() {
		return dashboardDao.getLastWeeksCancelledOrders().size();
	}
	
	@Override
	public int getWeeksReturnedOrdersSize() {
		return dashboardDao.getLastWeeksReturnedOrders().size();
	}
	
	@Override
	public List<String> getWeeksConfirmedChannels(){
		return dashboardDao.getWeeksConfirmedChannels();
	}
	@Override
	public List<String> getWeeksCancelledChannels(){
		return dashboardDao.getWeeksCancelledChannels();
	}
	@Override
	public List<String> getWeeksReturnedChannels(){
		return dashboardDao.getWeeksReturnedChannels();
	}
	
	//months data
		@Override
		public int getMonthsConfirmedOrdersSize() {
			return dashboardDao.getLastMonthsConfirmedOrders().size();
		}
		
		@Override
		public int getMonthsCancelledOrdersSize() {
			return dashboardDao.getLastMonthsCancelledOrders().size();
		}
		
		@Override
		public int getMonthsReturnedOrdersSize() {
			return dashboardDao.getLastMonthsReturnedOrders().size();
		}
		
		@Override
		public List<String> getMonthsConfirmedChannels(){
			return dashboardDao.getMonthsConfirmedChannels();
		}
		@Override
		public List<String> getMonthsCancelledChannels(){
			return dashboardDao.getMonthsCancelledChannels();
		}
		@Override
		public List<String> getMonthsReturnedChannels(){
			return dashboardDao.getMonthsReturnedChannels();
		}
	
	@Override
	public List<Inventory> getUsersProducts(String userId) {
		
		return dashboardDao.getUsersProducts(userId);

	}
	
	@Override
	public Map getTrendingProductsThisMonth() {
		
		Map<String,Integer> productSalesMap = dashboardDao.getTrendingProductsThisMonth();
		
		return productSalesMap;
		
	}
	@Override
	public String getContact() {
		return dashboardDao.getContact();
	}

}
