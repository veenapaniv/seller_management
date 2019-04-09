package com.cis4660.seller_management.dao.impl;

import java.beans.Statement;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.cis4660.seller_management.dao.InventoryDao;
import com.cis4660.seller_management.model.Inventory;

@Repository
public class InventoryDaoImpl extends JdbcDaoSupport implements InventoryDao{
	@Autowired
	DataSource dataSource;
	
	 @Value("${spring.datasource.url}")
	 String databaseURL;
	
	//String databaseURL = "jdbc:mysql://localhost:3306/demo";
    String user = "root";
    String password = "test";
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	@Override
	public List<Inventory> getInventory() {
		String sql = "SELECT * FROM inventory";
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
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
			result.add(inventory);
		}
		return result;
		
	}
	
	public String getImage(int id) {
		String base64Image = "";
		 String sql = "SELECT * FROM inventory WHERE ProductId ="+id;
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery(sql);
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
	
	
}
