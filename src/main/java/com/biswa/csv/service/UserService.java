package com.biswa.csv.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.biswa.csv.bean.Response;
import com.biswa.csv.bean.UserBean;

public interface UserService {

	/**
	 * This Method Is For Add Multiple Users
	 * 
	 * @param List<UserBean>
	 * @return ResponseEntity
	 * @since 4th Feb 2021
	 * @author Biswabhusan Prusty
	 */
	@Transactional(rollbackFor = Exception.class)
	ResponseEntity<Response> addUser(List<UserBean> usersBean) throws Exception;
	

	/**
	 * This Method API For Add Multiple Users
	 * 
	 * @param List<UserBean>
	 * @return ResponseEntity
	 * @since 4th Feb 2021
	 * @author Biswabhusan Prusty
	 */
	@Transactional(readOnly = true)
	public ResponseEntity<Response> getUsers() throws Exception;

}
