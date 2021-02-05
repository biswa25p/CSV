package com.biswa.csv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biswa.csv.bean.Response;
import com.biswa.csv.bean.UserBean;
import com.biswa.csv.service.UserService;
@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;


	/**
	 * API For Add Multiple Users
	 * 
	 * @param List<UserBean>
	 * @return ResponseEntity
	 * @since 4th Feb 2021
	 * @author Biswabhusan Prusty
	 */
	@PostMapping(value = "add", consumes = { "application/json", "text/plain",
			"text/plain;charset=UTF-8" }, produces = { "application/json", "text/plain" })
	
	public ResponseEntity<Response> addUser(@RequestBody List<UserBean> usersBean) {
		try {
			return userService.addUser(usersBean);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.buildResponse("Interval Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/**
	 * API For View All Users
	 * 
	 * @return ResponseEntity Having All User Details
	 * @since 4th Feb 2021
	 * @author Biswabhusan Prusty
	 */
	@GetMapping(value = "view")
	public ResponseEntity<Response> getUsers(){
		try {
			return userService.getUsers();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.buildResponse("Interval Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
