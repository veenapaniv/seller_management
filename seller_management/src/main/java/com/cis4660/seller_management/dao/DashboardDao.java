package com.cis4660.seller_management.dao;

import java.util.HashMap;
import java.util.List;

import com.cis4660.seller_management.model.Inventory;
import com.cis4660.seller_management.model.Order;

public interface DashboardDao {

	List<Order> getTodaysConfirmedOrders();
	List<Order> getTodaysCancelledOrders();
	List<Order> getTodaysReturnedOrders();
	
	List<Order> getLastWeeksConfirmedOrders();
	List<Order> getLastWeeksCancelledOrders();
	List<Order> getLastWeeksReturnedOrders();
	
	List<Order> getLastMonthsConfirmedOrders();
	List<Order> getLastMonthsCancelledOrders();
	List<Order> getLastMonthsReturnedOrders();
	
	List<String> getTodaysConfirmedChannels();
	List<String> getTodaysCancelledChannels();
	List<String> getTodaysReturnedChannels();
	
	List<String> getWeeksConfirmedChannels();
	List<String> getWeeksCancelledChannels();
	List<String> getWeeksReturnedChannels();
	
	List<String> getMonthsConfirmedChannels();
	List<String> getMonthsCancelledChannels();
	List<String> getMonthsReturnedChannels();
	List<Inventory> getUsersProducts(String userID);
	HashMap<String,Integer> getTrendingProductsThisMonth();
	String getContact();
}
