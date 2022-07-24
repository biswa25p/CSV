package com.csv.biswa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
	@Autowired
	InventoryService inventoryService;

	/**
	 * API For insert data from CSV to DB
	 * 
	 * @param file location
	 * @return ResponseEntity
	 * @since 23 July 2022
	 * @author Biswabhusan Prusty
	 */
	@GetMapping(value = "add")
	public ResponseEntity<Response> insertData() {
		try {
			return inventoryService.insertData("/TestFile.csv");
		} catch (Exception e) {
			e.printStackTrace();
			return Response.buildResponse("Interval Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * API For get products by name
	 * 
	 * @param String name
	 * @return ResponseEntity
	 * @since 23 July 2022
	 * @author Biswabhusan Prusty
	 */
	@PostMapping(value = "getproductsByName", consumes = { "application/json", "text/plain",
			"text/plain;charset=UTF-8" }, produces = { "application/json", "text/plain" })
	public ResponseEntity<Response> getproductsByName(@RequestBody String name) {
		try {
			return inventoryService.getproductsByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.buildResponse("Interval Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * API For search name
	 * 
	 * @param String name
	 * @return ResponseEntity
	 * @since 23 July 2022
	 * @author Biswabhusan Prusty
	 */
	@PostMapping(value = "search", consumes = { "application/json", "text/plain",
			"text/plain;charset=UTF-8" }, produces = { "application/json", "text/plain" })
	public ResponseEntity<Response> search(@RequestBody String name) {
		try {
			return inventoryService.search(name);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.buildResponse("Interval Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * API For get products that didn’t yet expire by supplier name
	 * 
	 * @param String supplier name
	 * @return ResponseEntity
	 * @since 23 July 2022
	 * @author Biswabhusan Prusty
	 */
	@PostMapping(value = "productsBySuppliers", consumes = { "application/json", "text/plain",
			"text/plain;charset=UTF-8" }, produces = { "application/json", "text/plain" })
	public ResponseEntity<Response> productsBySuppliers(@RequestBody String name) {
		try {
			return inventoryService.productsBySuppliers(name);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.buildResponse("Interval Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
