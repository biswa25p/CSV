package com.biswa.csv.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biswa.csv.bean.Response;
import com.biswa.csv.bean.UserBean;
import com.biswa.csv.dao.AddressDao;
import com.biswa.csv.dao.UserDao;
import com.biswa.csv.jpa.AddressTable;
import com.biswa.csv.jpa.UserTable;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private AddressDao addressDao;
	
	public ResponseEntity<Response> addUser(List<UserBean> usersBean) throws Exception{
		
		/*Iterate all users and add one by one*/
		AddressTable address = null;
		UserTable user = null;
		for (UserBean userBean : usersBean) {
			/*Create and set address bean*/
			address = new AddressTable();
			
			address.setCity(userBean.getCity());
			address.setCountry(userBean.getCountry());
			address.setLine1(userBean.getLine1());
			address.setLine2(userBean.getLine2());
			address.setPostalCode(userBean.getPostalCode());
			address.setState(userBean.getState());
			/*Save Address 1st*/
			addressDao.save(address);
			
			user = new UserTable();
			
			user.setFirstName(userBean.getFirstName());
			user.setLastName(userBean.getLastName());
			user.setAddress(address);
			user.setDOB(convertStringToUtilDate(userBean.getDob(),"MM/dd/yyyy"));
			/*Save User*/
			userDao.save(user);
		}
		return  Response.buildResponse(HttpStatus.OK);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<Response> getUsers() throws Exception{
		List<UserBean> users= new ArrayList();
		/*Fetch All User Data From Database*/
		List<UserTable> dbUsers = userDao.findAll();
		UserBean user = null;
		/*iterate all users and prepare reesponse bean*/
		for (UserTable userTable : dbUsers) {
			user = new UserBean();
			user.setCity(userTable.getAddress().getCity());
			user.setCountry(userTable.getAddress().getCountry());
			user.setDob(convertUtilDateToString(userTable.getDOB(), "MM/dd/yyyy"));
			user.setFirstName(userTable.getFirstName());
			user.setLastName(userTable.getLastName());
			user.setLine1(userTable.getAddress().getLine1());
			user.setLine2(userTable.getAddress().getLine2());
			user.setPostalCode(userTable.getAddress().getPostalCode());
			user.setState(userTable.getAddress().getState());
			user.setAge(calculateAge(userTable.getDOB()));
			users.add(user);
		}
		/*Check Users Are Present Or Not*/
		if(users.isEmpty()) {
			return Response.buildResponse("No Data Found", HttpStatus.NO_CONTENT);
		} else {
			return Response.buildResponse(users, HttpStatus.OK);
		}
		
	}
	
	/**
	 * This Method Is Used For Convert String Date Date To Util
	 * 
	 * @param String date which you want to convert
	 * @param String format in which you want to convert
	 * @return java.util.Date 
	 * @since 4th Feb 2021
	 * @author Biswabhusan Prusty
	 */
	public Date convertStringToUtilDate(String date , String format) throws ParseException {
		 return new SimpleDateFormat(format).parse(date);
	}
	/**
	 * This Method Is Used For Convert Util Date Date To String
	 * 
	 * @param ava.util.Date date which you want to convert
	 * @param String format in which you want to convert
	 * @return String 
	 * @since 4th Feb 2021
	 * @author Biswabhusan Prusty
	 */
	public String convertUtilDateToString(Date date , String format) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(format);  
		 return dateFormat.format(date);
	}
	/**
	 * This Method Is Used For calculate Age With Respect To Current Date
	 * 
	 * @param java.util.Date date which you want to calculate age
	 * @return Integer
	 * @since 4th Feb 2021
	 * @author Biswabhusan Prusty
	 */
	public Integer calculateAge(Date date) throws ParseException {
	      Instant instant = date.toInstant();
	      ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
	      LocalDate givenDate = zone.toLocalDate();
	      //Calculating the difference between given date to current date.
	      Period period = Period.between(givenDate, LocalDate.now());
		return period.getYears();
	}
}
