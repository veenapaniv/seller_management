package com.cis4660.seller_management.dao.impl;

import java.beans.Statement;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.cis4660.seller_management.dao.InventoryDao;
import com.cis4660.seller_management.model.Inventory;
import com.fasterxml.jackson.databind.JsonNode;

@Repository
public class InventoryDaoImpl extends JdbcDaoSupport implements InventoryDao{
	@Autowired
	DataSource dataSource;
	
	@Value("${spring.datasource.url}")
	String databaseURL;
    String user = "root";
    String password = "test";
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	//Getting list of inventory from database and adding it to list of inventory object
	@Override
	public List<Inventory> getInventory() {
		String inventoryQuery = "SELECT * FROM inventory";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(inventoryQuery);
		List<Inventory> result = new ArrayList<Inventory>();
		for(Map<String, Object> row:rows){
			Inventory inventory = new Inventory();
			inventory.setProductId((int)row.get("ProductId"));
			inventory.setProductName(((String)row.get("ProductName")));
			inventory.setQuantity(((int)row.get("Quantity")));
			inventory.setAmount(((float)row.get("Amount")));
			inventory.setShippingRate(((float)row.get("Shipping_Rate")));
			inventory.setLastUpdated(((java.sql.Timestamp)row.get("Last_Updated")));
			inventory.setImage(getImage((int)row.get("ProductId")));
			getChannels(inventory);
			getSalesAndReturn(inventory);
			result.add(inventory);
		}
		return result;
	}
	
	//Getting the image from database based on productId;
	public String getImage(int id) {
		String base64Image = "";
		 String imageQuery = "SELECT * FROM inventory WHERE ProductId ="+id;
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			PreparedStatement statement = connection.prepareStatement(imageQuery);
			ResultSet rs = statement.executeQuery(imageQuery);
			if(rs.next()) {
				  	Blob blob = rs.getBlob("ProductImage");
	                InputStream inputStream = blob.getBinaryStream();
	                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                byte[] buffer = new byte[4096];
	                int bytesRead = -1;
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);                  
	                }
	                byte[] imageBytes = outputStream.toByteArray();
	                base64Image = Base64.getEncoder().encodeToString(imageBytes);
	                inputStream.close();
	                outputStream.close();
			}
		} catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }  
		return base64Image;
		}
	
	//Adding a product in database in products table
	@Override
	public void insertProduct(Inventory inventory) {
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			String insertInventory = "INSERT INTO inventory values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insertInventory);
			int productId = createProductId();
            statement.setInt(1, productId);
            statement.setString(2, inventory.getProductName());
            statement.setInt(4, inventory.getQuantity());
            statement.setFloat(5, inventory.getAmount());
            statement.setFloat(6, inventory.getShippingRate());
            statement.setTimestamp(7, getCurrentDate());
            statement.setString(8, inventory.getUserId());
            System.out.println("userId: " +inventory.getUserId());
            File uploadedFile = new File("C:\\Users\\Swathi Uppu\\Desktop\\Sem2\\Java\\Project\\SellerManagement\\seller_management\\src\\main\\resources\\static\\images\\"+inventory.getUploadedFile().getPath());
            FileInputStream inputStream= new FileInputStream(uploadedFile);
            statement.setBlob(3, inputStream);
            statement.executeUpdate();
            addSales(inventory, productId);
            addChannels(inventory, productId);
		}catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }  
	}
	
	//Adding sales details when a product is added. Initial value of sold and returned is 0
	public void addSales(Inventory inventory, int productId) {
		String insertSales = "INSERT INTO sales " +
				"(SaleId, ProductId, Sales_Qty, Return_Qty) VALUES (?,?,0,0)" ;
		getJdbcTemplate().update(insertSales, new Object[]{
				createSalesId(), productId
		});
	}
	
	//Adding a record in channel_product table based on the channels selected while adding a product
	public void addChannels(Inventory inventory, int productId) {
		List<String> channels = inventory.getChannels();
		for(String channel: channels) {
			String channelsQuery = "INSERT INTO channel_product " +
					"(ChannelId, ProductId) VALUES (?,?)" ;
			getJdbcTemplate().update(channelsQuery, new Object[]{
					getChannelIds(channel), productId
			});
		}
	}
	
	//Retrieving channelIds based on channelName
	public int getChannelIds(String channelName) {
		String channelQuery = "select ChannelId from channels where ChannelName = '"+channelName+"'";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(channelQuery);
		for(Map<String, Object> row:rows){
			return (Integer)row.get("ChannelId");
		}
		return 0;
	}
	
	//Setting current date for last_updated column while adding a product
	public Timestamp getCurrentDate() {
		Date date= new Date();
		long time = date.getTime();		     
		Timestamp ts = new Timestamp(time);
		return ts;
	}
	
	//Retrieving channels based on productId
	public List<Integer> getChannels(int productId) {
		String channelsQuery = "SELECT channelId from channel_product where productId = "+productId;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(channelsQuery);
		List<Integer> channels = new ArrayList<Integer>();
		for(Map<String, Object> row:rows){
			channels.add((int)row.get("ChannelId"));
		}
		return channels;
	}
	
	//retriving channels based on productId
	public void getChannels(Inventory inventory) {
		List<Integer> channelIds = getChannels(inventory.getProductId());
		List<String> channelNames = new ArrayList<String>();
		for(Integer channelId: channelIds) {
			String channelsQuery = "SELECT * FROM channels where ChannelId = "+ channelId;
			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(channelsQuery);
			for(Map<String, Object> row:rows){
				channelNames.add((String)row.get("ChannelName"));
			}
			inventory.setChannels(channelNames);
		}
	}
	
	//Getting sales and return values to display in inventory page
	public void getSalesAndReturn(Inventory inventory) {
		String sql = "select * from sales where ProductId= "+inventory.getProductId();
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		for(Map<String, Object> row:rows){
			inventory.setSold((Integer)row.get("Sales_Qty"));
			inventory.setReturned((Integer)row.get("Return_Qty"));
		}
	}
	
	//Creating random productId
	public int createProductId() {
		int id= (int) Math.round((Math.random()) *100000);
		while(!validProductId(id)) {
			id= (int) Math.round((Math.random()) *100000);
		}
		return id;
	}
	
	//Checking if random productId is already present in database
	public boolean validProductId(int productId) {
		String productQuery = "Select productId from inventory where productId="+productId;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(productQuery);
		if(rows.size() > 0) {
			return false;
		}else {
			return true;
		}
	}
	
	//Creating random salesId
	public int createSalesId() {
		int id= (int) Math.round((Math.random()) *100000);
		while(!validSalesId(id)) {
			id= (int) Math.round((Math.random()) *100000);
		}
		return id;
	}
	
	//Checking if random salesId is already present in database
	public boolean validSalesId(int salesId) {
		String salesQuery = "Select SaleId from sales where SaleId="+salesId;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(salesQuery);
		if(rows.size() > 0) {
			return false;
		}else {
			return true;
		}
	}
	
	//Retriving product details based on productId
	@Override
	public Inventory getProductById(int productId) {
		String inventoryQuery = "SELECT * FROM inventory where ProductId="+productId;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(inventoryQuery);
		Inventory inventory = new Inventory();
		for(Map<String, Object> row:rows){
			inventory.setProductId((int)row.get("ProductId"));
			inventory.setProductName(((String)row.get("ProductName")));
			inventory.setQuantity(((int)row.get("Quantity")));
			inventory.setAmount(((float)row.get("Amount")));
			inventory.setShippingRate(((float)row.get("Shipping_Rate")));
			inventory.setLastUpdated(((java.sql.Timestamp)row.get("Last_Updated")));
			inventory.setImage(getImage((int)row.get("ProductId")));
			getChannels(inventory);
			getSalesAndReturn(inventory);
		}
		return inventory;
	}
	
	//Updating a product in database
	@Override
	public void updateProduct(Inventory inventory) {
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			String updateInventory = "update inventory " +
					"set ProductName = ?, ProductImage = ?, Quantity = ?, Amount = ?, Shipping_Rate = ?, Last_Updated =? where ProductId = "+inventory.getProductId();
			PreparedStatement statement = connection.prepareStatement(updateInventory);
            statement.setString(1, inventory.getProductName());
            File uploadedFile = new File("C:\\Users\\Swathi Uppu\\Desktop\\Sem2\\Java\\Project\\SellerManagement\\seller_management\\src\\main\\resources\\static\\images\\"+inventory.getUploadedFile().getPath());
            FileInputStream inputStream= new FileInputStream(uploadedFile);
            statement.setBlob(2, inputStream);
            statement.setInt(3, inventory.getQuantity());
            statement.setFloat(4, inventory.getAmount());
            statement.setFloat(5, inventory.getShippingRate());
            statement.setTimestamp(6, getCurrentDate());
            statement.executeUpdate();
            //updateSales(inventory, inventory.getProductId());
            updateChannels(inventory, inventory.getProductId());
		}catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }  
	}
	
	public void deleteChannel(int productId) {
		String deleteQuery = "delete from channel_product where ProductId = ?";
		getJdbcTemplate().update(deleteQuery, new Object[]{
				 productId
		});
	}
	
	//Updating channels in database
	public void updateChannels(Inventory inventory, int productId) {
		deleteChannel(productId);
		addChannels(inventory, productId);
	}
	
	//deleting sales in database when product is deleted
	public void deleteSales(int productId) {
		String deleteProduct = "delete from sales where ProductId = ?";
		getJdbcTemplate().update(deleteProduct, new Object[]{
				 productId
		});
	}
	//Deleting products in database
	@Override
	public void deleteProduct(int productId) {
		deleteChannel(productId);
		deleteSales(productId);
		String deleteProduct = "delete from inventory where ProductId = ?";
		getJdbcTemplate().update(deleteProduct, new Object[]{
				 productId
		});
	}
	
}
