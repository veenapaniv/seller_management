package com.cis4660.seller_management.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public boolean validateCredentials(String email, String password) {
		//return user.equalsIgnoreCase("veenapani.v@gmail.com") && password.equals("password123");
		return validatePassword(email,password);
		
	}
	
	@Override
	public boolean validatePassword(String email,String password) {
		String sql = "SELECT * FROM user WHERE username = ?";
		User currentUser = (User)getJdbcTemplate().queryForObject(sql, new Object[] {email},new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rwNumber) throws SQLException {
				User user = new User();
				user.setUserId(rs.getString("userId"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		if(currentUser.getPassword().equals(password))
		{
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public String getUserID(String email) {
		String sql = "SELECT userId FROM user WHERE Username = ?";
		User currentUser = (User)getJdbcTemplate().queryForObject(sql, new Object[] {email},new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rwNumber) throws SQLException {
				User user = new User();
				user.setUserId(rs.getString("userId"));
//				user.setUsername(rs.getString("username"));
//				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		return currentUser.getUserId();
	}

}
