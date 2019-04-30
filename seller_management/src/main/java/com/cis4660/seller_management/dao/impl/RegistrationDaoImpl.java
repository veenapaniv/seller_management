 package com.cis4660.seller_management.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.cis4660.seller_management.dao.LoginDao;
import com.cis4660.seller_management.dao.RegistrationDao;
import com.cis4660.seller_management.dao.UserDao;
import com.cis4660.seller_management.model.User;

@Repository
public class RegistrationDaoImpl extends JdbcDaoSupport implements RegistrationDao {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserDao userDao;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
		setJdbcTemplate(jdbcTemplate);
	}
	
	/**
	 * This method helps in inserting the details to the database to register the user
	 */
	@Override
	public boolean registerUser(String emailId, String password, String firstname, String lastname, String phone,
			String address) {
		long tel = 0;
		try {
		 tel = Long.parseLong(phone);
		}
		catch(NumberFormatException n) {
			//output error page			
		}
		return insertUserDetailsToDb(emailId, password, firstname, lastname, tel,	address);
	}

	
	/**1.same email id don’t allow
	2. Empty fields don’t save to db - server side validations
	3. Popup successful registration and error in registration messages
	*/

	private boolean insertUserDetailsToDb(String emailId, String password, String firstname, String lastname,
			long phone, String address) {
		//validate if email is already registered
		if(validateDuplicateEmail(emailId) == false) {
			if(validateOtherFields(password, firstname, lastname, phone, address) == true) {
				//get number of users, in order to insert the correct userID
				String getUserCountSql = "select * from User";
				List<Map<String,Object>> number_of_users= jdbcTemplate.queryForList(getUserCountSql);
				
				//userId will be 1 more than the count of users in the system
				int userId = number_of_users.size()+1;
				//update the User table with the new user details
				int insertUser = jdbcTemplate.update("INSERT INTO USER VALUES(?,?,?,?,?,?,?)",userId,emailId,password,firstname,lastname,phone,address);
				if(insertUser >= 1) {
					return true;
				}
		}			
		}
		
		return false;
		
	}
	
	/**
	 * Validate and returns a boolean by validating the other fields
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param phone
	 * @param address
	 * @return
	 */

	private boolean validateOtherFields(String password, String firstname, String lastname, long phone, String address) {
		if ((null == password) || (password.isEmpty()) || (null == firstname) || (firstname.isEmpty())
				|| (null == lastname) || (lastname.isEmpty()) || (phone <= 0)
				|| (null == address) || (address.isEmpty())) {
			return false;
		}
		return true;
	}

	private boolean validateDuplicateEmail(String emailId) {
		User user = new User();
		try {
		if((null != emailId) && !emailId.isEmpty()) {
			try {
			 user = userDao.getUserByEmail(emailId);
			}
			catch(EmptyResultDataAccessException e) {//do nothing
				}
			
			if(user.getUsername().equals(emailId)) {
				return true;
			}
		}
		}
		catch(Exception e) {
		}
		return false;
	}

}
