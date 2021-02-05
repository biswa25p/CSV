package com.biswa.csv.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biswa.csv.jpa.AddressTable;

public interface AddressDao extends JpaRepository<AddressTable, Long> {

}
