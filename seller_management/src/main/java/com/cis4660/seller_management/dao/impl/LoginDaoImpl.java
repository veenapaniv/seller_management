package com.cis4660.seller_management.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.cis4660.seller_management.dao.LoginDao;
import com.cis4660.seller_management.model.User;



@Repository
public class LoginDaoImpl  extends JdbcDaoSupport implements LoginDao {
	
	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	/**
	 * This method makes a call to the internal validatePassword method
	 */
	@Override
	public boolean validateCredentials(String email, String password) {
		//return user.equalsIgnoreCase("veenapani.v@gmail.com") && password.equals("password123");
		return validatePassword(email,password);
		
	}
	/**
	 * This method validates if the password entered matches the user's password in the database
	 */
	@Override
	public boolean validatePassword(String email,String password) {
		String sql = "SELECT * FROM user WHERE username = ?";
		User currentUser = new User();
		try {
		currentUser = (User)getJdbcTemplate().queryForObject(sql, new Object[] {email},new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rwNumber) throws SQLException {
				User user = new User();
				user.setUserId(rs.getString("userId"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		}
		catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return false;
		}
		if(currentUser.getPassword().equals(password))
		{
			return true;
		}
		else {
			return false;
		}
		
	}
	/**
	 * This method returns the userID for the specified email
	 */
	@Override
	public String getUserID(String email) {
		String sql = "SELECT userId FROM user WHERE Username = ?";
		User currentUser = (User)getJdbcTemplate().queryForObject(sql, new Object[] {email},new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rwNumber) throws SQLException {
				User user = new User();
				user.setUserId(rs.getString("userId"));
				return user;
			}
		});
		return currentUser.getUserId();
	}

}
