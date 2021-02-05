package com.biswa.csv.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biswa.csv.jpa.UserTable;

public interface UserDao extends JpaRepository<UserTable, Long> {

}
