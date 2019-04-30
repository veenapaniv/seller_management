package com.cis4660.seller_management.dao.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.cis4660.seller_management.dao.DashboardDao;
import com.cis4660.seller_management.model.Inventory;
import com.cis4660.seller_management.model.Order;

@Repository
public class DashboardDaoImpl extends JdbcDaoSupport implements DashboardDao {
	
	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	/**
	 * This method returns a List of orders which are in 'Confirmed' status today
	 */
	@Override
	public List<Order> getTodaysConfirmedOrders() {
		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// get a java date (java.util.Date) from the Calendar instance.
		// this java date will represent the current date, or "now".
		java.util.Date currentDate = calendar.getTime();
		// now, create a java.sql.Date from the java.util.Date
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		String ordersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date like '"+date+"%' and status = 'confirmed' and Orders.productId IN (Select productId from Inventory where userId = '1');";
		
		List<Order> orders = getOrdersList(ordersListSql);
		
		return orders;
	}
	
	
	/**
	 * This method returns a List of orders which are in 'Cancelled' status today
	 */
	@Override
	public List<Order> getTodaysCancelledOrders() {
		Calendar calendar = Calendar.getInstance();

		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		String ordersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date like '"+date+"%' and status = 'cancelled' and Orders.productId IN (Select productId from Inventory where userId = '1')";
		
		List<Order> orders = getOrdersList(ordersListSql);
		
		
		return orders;
	}
	
	/**
	 * This method returns a List of orders which are in 'Returned' status today
	 */
	@Override
	public List<Order> getTodaysReturnedOrders() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		String ordersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date like '"+date+"%' and status = 'returned' and Orders.productId IN (Select productId from Inventory where userId = '1')";
		List<Order> orders = getOrdersList(ordersListSql);
		
		return orders;
	}
	
	/**
	 * This method returns a List of orders which are in 'Confirmed' status last week
	 */
	@Override
	public List<Order> getLastWeeksConfirmedOrders() {
		//Get calendar instance to get date prior to 7 days.
		java.sql.Date sevenDaysdate = getDate(-7);
		
		//get one day after current date - current date not fetching proper results in the query
		java.sql.Date tomorrowDate = getDate(+1);
		
		String weekOrdersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date >= CAST('"+sevenDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'confirmed' and Orders.productId IN (Select productId from Inventory where userId = '1')";
		
		List<Order> orders = getOrdersList(weekOrdersListSql);
		return orders;
	}
	
	/**
	 * This method returns a List of orders which are in 'Cancelled' status last week
	 */
	@Override
	public List<Order> getLastWeeksCancelledOrders() {
		//Get calendar instance to get date prior to 7 days.
		java.sql.Date sevenDaysdate = getDate(-7);
		
		//get one day after current date - current date not fetching proper results in the query
		java.sql.Date tomorrowDate = getDate(+1);
		
		String weekOrdersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date >= CAST('"+sevenDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'cancelled' and Orders.productId IN (Select productId from Inventory where userId = '1')";
		
		List<Order> orders = getOrdersList(weekOrdersListSql);
		return orders;
	}
	
	/**
	 * This method returns a List of orders which are in 'Returned' status last week
	 */
	@Override
	public List<Order> getLastWeeksReturnedOrders() {
		//Get calendar instance to get date prior to 7 days.
		java.sql.Date sevenDaysdate = getDate(-7);
		
		//get one day after current date - current date not fetching proper results in the query
		java.sql.Date tomorrowDate = getDate(+1);
		
		String weekOrdersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date >= CAST('"+sevenDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'returned' and Orders.productId IN (Select productId from Inventory where userId = '1')";
		
		List<Order> orders = getOrdersList(weekOrdersListSql);
		return orders;
	}
	
	/**
	 * This method returns a List of orders which are in 'Confirmed' status during the last month
	 */
	@Override
	public List<Order> getLastMonthsConfirmedOrders() {
		//Get calendar instance to get date prior to 30 days.
				java.sql.Date thirtyDaysdate = getDate(-30);
				
				//get one day after current date - current date not fetching proper results in the query
				java.sql.Date tomorrowDate = getDate(+1);
				
				String monthOrdersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date >= CAST('"+thirtyDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'confirmed' and Orders.productId IN (Select productId from Inventory where userId = '1')";
				
				List<Order> orders = getOrdersList(monthOrdersListSql);
				return orders;
	}
	
	/**
	 * This method returns a List of orders which are in 'Cancelled' status during the last month
	 */
	@Override
	public List<Order> getLastMonthsCancelledOrders() {
		//Get calendar instance to get date prior to 30 days.
				java.sql.Date thirtyDaysdate = getDate(-30);
				
				//get one day after current date - current date not fetching proper results in the query
				java.sql.Date tomorrowDate = getDate(+1);
				
				String monthOrdersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date >= CAST('"+thirtyDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'cancelled' and Orders.productId IN (Select productId from Inventory where userId = '1')";
				
				List<Order> orders = getOrdersList(monthOrdersListSql);
				return orders;
	}
	
	/**
	 * This method returns a List of orders which are in 'Returned' status during the last month
	 */
	@Override
	public List<Order> getLastMonthsReturnedOrders() {
		//Get calendar instance to get date prior to 7 days.
				java.sql.Date thirtyDaysdate = getDate(-30);
				
				//get one day after current date - current date not fetching proper results in the query				
				java.sql.Date tomorrowDate = getDate(+1);
				
				String monthOrdersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date >= CAST('"+thirtyDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'returned' and Orders.productId IN (Select productId from Inventory where userId = '1')";
				
				List<Order> orders = getOrdersList(monthOrdersListSql);
				return orders;
	}
	
	/**
	 * This method returns a List of channels of which 'confirmed' products are a part of
	 */
	@Override
	public List<String> getTodaysConfirmedChannels() {
		List<Order> orderList = getTodaysConfirmedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
	/**
	 * This method returns a List of channels of which 'Cancelled' products are a part of
	 */
	@Override
	public List<String> getTodaysCancelledChannels() {
		List<Order> orderList = getTodaysCancelledOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
	/**
	 * This method returns a List of channels of which 'Returned' products are a part of
	 */
	@Override
	public List<String> getTodaysReturnedChannels() {
		List<Order> orderList = getTodaysReturnedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
	/**
	 * This method returns a List of channels of which 'confirmed' products are a part of during last week
	 */
	@Override
	public List<String> getWeeksConfirmedChannels() {
		List<Order> orderList = getLastWeeksConfirmedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
	/**
	 * This method returns a List of channels of which 'Cancelled' products are a part of during last week
	 */
	@Override
	public List<String> getWeeksCancelledChannels() {
		List<Order> orderList = getLastWeeksCancelledOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
	/**
	 * This method returns a List of channels of which 'Returned' products are a part of during last week
	 */
	@Override
	public List<String> getWeeksReturnedChannels() {
		List<Order> orderList = getLastWeeksReturnedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
	/**
	 * This method returns a List of channels of which 'confirmed' products are a part of during last month
	 */
	@Override
	public List<String> getMonthsConfirmedChannels() {
		List<Order> orderList = getLastMonthsConfirmedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
	/**
	 * This method returns a List of channels of which 'Cancelled' products are a part of during last month
	 */
	@Override
	public List<String> getMonthsCancelledChannels() {
		List<Order> orderList = getLastMonthsConfirmedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	/**
	 * This method returns a List of channels of which 'Returned' products are a part of during last month
	 */
	@Override
	public List<String> getMonthsReturnedChannels() {
		List<Order> orderList = getLastMonthsReturnedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	/**
	 * This method returns a List of products of the particular logged in user
	 */
	@Override
	public List<Inventory> getUsersProducts(String userID){
		
		String productList = "Select productName,Amount from Inventory where userId = '"+userID+"'";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(productList);
		List<Inventory> products = new ArrayList<Inventory>();

		for(Map<String, Object> row:rows) {
			Inventory product = new Inventory();
			product.setProductName((String)row.get("productName"));
			product.setAmount((float)row.get("Amount"));
			products.add(product);
		}
		
		return products;
		
	}
	
	/**
	 * This method returns a List of Order's based on the sql passed as parameter
	 * @param ordersListSql
	 * @return
	 */
	public List<Order> getOrdersList(String ordersListSql){
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(ordersListSql);
		List<Order> orders = new ArrayList<Order>();
		
		for(Map<String, Object> row:rows) {
			Order order = new Order();
			order.setOrderId((int)row.get("OrderId"));
			order.setProductId((int)row.get("ProductId"));
			order.setStatus((String)row.get("Status"));
			order.setOrdered_date((java.sql.Timestamp)row.get("Order_Date"));
			orders.add(order);
		}
		
		return orders;
		
	}
	
	/**
	 * Return List of channels based on the sql query conditions.
	 * @param orderList
	 * @return
	 */
	private List<String> getChannels(List<Order> orderList) {
		List<String> channels = new ArrayList<String>();
		List<String> productIds = new ArrayList<>();
		String getChannels;
		//Iterate over the orderList and extract ProductId's and add it to the List<String> 
		for(Order order : orderList) {
			String productId = Integer.toString(order.getProductId());
			productIds.add(productId);
		}
		//Java 8 implementation of Streams to create a 
		//comma separated String of the list of productId's in the List created above
		String commaSeparatedProductId = productIds.stream().collect(Collectors.joining(","));
		//use the commaSeparated list of product id's for querying the channels
		if(null != commaSeparatedProductId && !commaSeparatedProductId.isEmpty()) {
			 getChannels = "Select DISTINCT channelName from channel_product,channels where productId IN ("+commaSeparatedProductId+")";
				
				List<Map<String, Object>> rows = getJdbcTemplate().queryForList(getChannels);
				
				
				for(Map<String, Object> row:rows) {
					channels.add((String) row.get("channelName"));
				}
		}
		
		return channels;
	}
	
	/**
	 * This method helps in retrieving the list of trending products
	 */
	@Override
	public HashMap<String,Integer> getTrendingProductsThisMonth(){
		
		//Get calendar instance to get date prior to 30 days.
		java.sql.Date thirtyDaysdate = getDate(-30);
		
		//get one day after current date - current date not fetching proper results in the query
		java.sql.Date tomorrowDate = getDate(+1);
		
		String productSql = "select orders.productId,Inventory.productName AS productName, SUM(sales_Qty) AS salesQty From Orders,Sales,Inventory where orders.productId = inventory.productid and order_date >= CAST('"+thirtyDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'confirmed' and orders.productID = sales.productID and orders.productID IN (select productID from Inventory where userId = '1') GROUP BY orders.productId ORDER BY 'salesQty' DESC LIMIT 10";
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(productSql);
		List<Inventory> products = new ArrayList<Inventory>();
	    HashMap<String, Integer> productSales = new HashMap<String, Integer>();

		for(Map<String, Object> row:rows) {
			String prodName = (String) row.get("productName");
			Integer salesQty = ((BigDecimal)row.get("salesQty")).intValue();
			productSales.put(prodName, (Integer) salesQty);
		}
		
		return productSales;
	}
	/**
	 * This method helps in getting the contact.json file, and returns us the content as a String
	 */
	public String getContact() {
		String content = "";
		try {
			File file = new ClassPathResource("contact.json").getFile();
			content = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	/**
	 * Returns the Date for required days which are today, last week, last month
	 * @param days
	 * @return
	 */
	private java.sql.Date getDate(int days) {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		java.util.Date date = cal.getTime();
		java.sql.Date newDate = new java.sql.Date(date.getTime());
		return newDate;
		
	}

}
