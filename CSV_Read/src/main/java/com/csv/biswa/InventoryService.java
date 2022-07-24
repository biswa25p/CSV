package com.csv.biswa;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface InventoryService {
	/**
	 * This Method Is For insert data from CSV to DB
	 * 
	 * @param String path
	 * @return ResponseEntity
	 * @since 23 July 2022
	 * @author Biswabhusan Prusty
	 */
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Response> insertData(String path);

	/**
	 * This Method Is For get products WRT name
	 * 
	 * @param String path
	 * @return ResponseEntity
	 * @since 23 July 2022
	 * @author Biswabhusan Prusty
	 */
	@Transactional(readOnly = true)
	public ResponseEntity<Response> getproductsByName(String name);

	/**
	 * This Method Is For search products by name
	 * 
	 * @param String path
	 * @return ResponseEntity
	 * @since 23 July 2022
	 * @author Biswabhusan Prusty
	 */
	@Transactional(readOnly = true)
	public ResponseEntity<Response> search(String name);

	/**
	 * This Method Is For get products by supplier name
	 * 
	 * @param String path
	 * @return ResponseEntity
	 * @since 23 July 2022
	 * @author Biswabhusan Prusty
	 */
	@Transactional(readOnly = true)
	public ResponseEntity<Response> productsBySuppliers(String name);
}
