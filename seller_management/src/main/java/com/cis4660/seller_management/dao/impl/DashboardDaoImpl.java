package com.cis4660.seller_management.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Override
	public List<Order> getAllOrders() {
		String ordersListSql = "Select * from Orders";
		
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
	
	
	
	@Override
	public List<Order> getTodaysCancelledOrders() {
		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// get a java date (java.util.Date) from the Calendar instance.
		// this java date will represent the current date, or "now".
		java.util.Date currentDate = calendar.getTime();
		// now, create a java.sql.Date from the java.util.Date
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		String ordersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date like '"+date+"%' and status = 'cancelled' and Orders.productId IN (Select productId from Inventory where userId = '1')";
		
		List<Order> orders = getOrdersList(ordersListSql);
		
		
		return orders;
	}
	
	@Override
	public List<Order> getTodaysReturnedOrders() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		String ordersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date like '"+date+"%' and status = 'returned' and Orders.productId IN (Select productId from Inventory where userId = '1')";
		List<Order> orders = getOrdersList(ordersListSql);
		
		return orders;
	}
	
	@Override
	public List<Order> getLastWeeksConfirmedOrders() {
		//Get calendar instance to get date prior to 7 days.
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		java.util.Date date = cal.getTime();
		java.sql.Date sevenDaysdate = new java.sql.Date(date.getTime());
		
		//get one day after current date - current date not fetching proper results in the query
		Calendar calCurr = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		java.util.Date currDate = calCurr.getTime();
		java.sql.Date tomorrowDate = new java.sql.Date(currDate.getTime());
		
		String weekOrdersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date >= CAST('"+sevenDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'confirmed' and Orders.productId IN (Select productId from Inventory where userId = '1')";
		
		List<Order> orders = getOrdersList(weekOrdersListSql);
		return orders;
	}
	
	@Override
	public List<Order> getLastWeeksCancelledOrders() {
		//Get calendar instance to get date prior to 7 days.
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		java.util.Date date = cal.getTime();
		java.sql.Date sevenDaysdate = new java.sql.Date(date.getTime());
		
		//get one day after current date - current date not fetching proper results in the query
		Calendar calCurr = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		java.util.Date currDate = calCurr.getTime();
		java.sql.Date tomorrowDate = new java.sql.Date(currDate.getTime());
		
		String weekOrdersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date >= CAST('"+sevenDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'cancelled' and Orders.productId IN (Select productId from Inventory where userId = '1')";
		
		List<Order> orders = getOrdersList(weekOrdersListSql);
		return orders;
	}
	
	@Override
	public List<Order> getLastWeeksReturnedOrders() {
		//Get calendar instance to get date prior to 7 days.
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		java.util.Date date = cal.getTime();
		java.sql.Date sevenDaysdate = new java.sql.Date(date.getTime());
		
		//get one day after current date - current date not fetching proper results in the query
		Calendar calCurr = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		java.util.Date currDate = calCurr.getTime();
		java.sql.Date tomorrowDate = new java.sql.Date(currDate.getTime());
		
		String weekOrdersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date >= CAST('"+sevenDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'returned' and Orders.productId IN (Select productId from Inventory where userId = '1')";
		
		List<Order> orders = getOrdersList(weekOrdersListSql);
		return orders;
	}
	

	@Override
	public List<Order> getLastMonthsConfirmedOrders() {
		//Get calendar instance to get date prior to 7 days.
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -30);
				java.util.Date date = cal.getTime();
				java.sql.Date thirtyDaysdate = new java.sql.Date(date.getTime());
				
				//get one day after current date - current date not fetching proper results in the query
				Calendar calCurr = Calendar.getInstance();
				cal.add(Calendar.DATE, +1);
				java.util.Date currDate = calCurr.getTime();
				java.sql.Date tomorrowDate = new java.sql.Date(currDate.getTime());
				
				String monthOrdersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date >= CAST('"+thirtyDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'confirmed' and Orders.productId IN (Select productId from Inventory where userId = '1')";
				
				List<Order> orders = getOrdersList(monthOrdersListSql);
				return orders;
	}
	
	@Override
	public List<Order> getLastMonthsCancelledOrders() {
		//Get calendar instance to get date prior to 7 days.
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -30);
				java.util.Date date = cal.getTime();
				java.sql.Date thirtyDaysdate = new java.sql.Date(date.getTime());
				
				//get one day after current date - current date not fetching proper results in the query
				Calendar calCurr = Calendar.getInstance();
				cal.add(Calendar.DATE, +1);
				java.util.Date currDate = calCurr.getTime();
				java.sql.Date tomorrowDate = new java.sql.Date(currDate.getTime());
				
				String monthOrdersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date >= CAST('"+thirtyDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'cancelled' and Orders.productId IN (Select productId from Inventory where userId = '1')";
				
				List<Order> orders = getOrdersList(monthOrdersListSql);
				return orders;
	}
	
	@Override
	public List<Order> getLastMonthsReturnedOrders() {
		//Get calendar instance to get date prior to 7 days.
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -30);
				java.util.Date date = cal.getTime();
				java.sql.Date thirtyDaysdate = new java.sql.Date(date.getTime());
				
				//get one day after current date - current date not fetching proper results in the query
				Calendar calCurr = Calendar.getInstance();
				cal.add(Calendar.DATE, +1);
				java.util.Date currDate = calCurr.getTime();
				java.sql.Date tomorrowDate = new java.sql.Date(currDate.getTime());
				
				String monthOrdersListSql = "Select Orders.ProductID as ProductId, OrderId, Status, Order_Date from Orders where order_date >= CAST('"+thirtyDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'returned' and Orders.productId IN (Select productId from Inventory where userId = '1')";
				
				List<Order> orders = getOrdersList(monthOrdersListSql);
				return orders;
	}

	@Override
	public List<String> getTodaysConfirmedChannels() {
		List<Order> orderList = getTodaysConfirmedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
	@Override
	public List<String> getTodaysCancelledChannels() {
		List<Order> orderList = getTodaysCancelledOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	@Override
	public List<String> getTodaysReturnedChannels() {
		List<Order> orderList = getTodaysReturnedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}

	@Override
	public List<String> getWeeksConfirmedChannels() {
		List<Order> orderList = getLastWeeksConfirmedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
	@Override
	public List<String> getWeeksCancelledChannels() {
		List<Order> orderList = getLastWeeksCancelledOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
	@Override
	public List<String> getWeeksReturnedChannels() {
		List<Order> orderList = getLastWeeksReturnedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}

	@Override
	public List<String> getMonthsConfirmedChannels() {
		List<Order> orderList = getLastMonthsConfirmedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
	@Override
	public List<String> getMonthsCancelledChannels() {
		List<Order> orderList = getLastMonthsConfirmedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
	@Override
	public List<String> getMonthsReturnedChannels() {
		List<Order> orderList = getLastMonthsReturnedOrders();
		List<String> channels = new ArrayList<String>();
		channels = getChannels(orderList);
		return channels;
	}
	
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
	
	private List<String> getChannels(List<Order> orderList) {
		List<String> channels = new ArrayList<String>();
		List<String> productIds = new ArrayList<>();
		String getChannels;
		for(Order order : orderList) {
			String productId = Integer.toString(order.getProductId());
			productIds.add(productId);
		}
		//Java 8 implementation of Streams to create a comma separated String of the list of productId's
		String commaSeparatedProductId = productIds.stream().collect(Collectors.joining(","));
		if(null != commaSeparatedProductId && !commaSeparatedProductId.isEmpty()) {
			 getChannels = "Select DISTINCT channelName from channel_product,channels where productId IN ("+commaSeparatedProductId+")";
				
				List<Map<String, Object>> rows = getJdbcTemplate().queryForList(getChannels);
				
				
				for(Map<String, Object> row:rows) {
					channels.add((String) row.get("channelName"));
				}
		}
		
		return channels;
	}
	
	@Override
	public HashMap<String,Integer> getTrendingProductsThisMonth(){
		
		//correct query
//		select orders.productId,Inventory.productName AS productName, SUM(sales_Qty) AS salesQty 
//		From Orders,Sales,Inventory 
//		where orders.productId = inventory.productid and order_date >= CAST('2019-04-11%' AS DATE) 
//			and order_date <= CAST('2019-04-14%' AS DATE) 
//		    and status = 'confirmed' and orders.productID = sales.productID 
//		    and orders.productID IN (select productID from Inventory where userId = 1) 
//		GROUP BY orders.productId
//		ORDER BY 'salesQty' DESC LIMIT 10;
		
		//Get calendar instance to get date prior to 7 days.
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		java.util.Date date = cal.getTime();
		java.sql.Date thirtyDaysdate = new java.sql.Date(date.getTime());
		
		//get one day after current date - current date not fetching proper results in the query
		Calendar calCurr = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		java.util.Date currDate = calCurr.getTime();
		java.sql.Date tomorrowDate = new java.sql.Date(currDate.getTime());
		
		String productSql = "select orders.productId,Inventory.productName AS productName, SUM(sales_Qty) AS salesQty From Orders,Sales,Inventory where orders.productId = inventory.productid and order_date >= CAST('"+thirtyDaysdate+"%' AS DATE) and order_date <= CAST('"+tomorrowDate+"%' AS DATE) and status = 'confirmed' and orders.productID = sales.productID and orders.productID IN (select productID from Inventory where userId = 1) GROUP BY orders.productId ORDER BY 'salesQty' DESC LIMIT 10";
		
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

}
