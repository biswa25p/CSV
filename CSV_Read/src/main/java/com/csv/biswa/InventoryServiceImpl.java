package com.csv.biswa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	InventoryDao inventoryDao;
	@Override
	public ResponseEntity<Response> insertData(String path) {
		//create file object with the help of path
		File csvFile = new File(path);
		BufferedReader br;
		//create database object
		InventoryTable inventoryTable;
		try {
			//read data from file
			br = new BufferedReader(new FileReader(csvFile));
			String line = "";
			br.readLine();
			//extract data from file and save in table
			while ((line = br.readLine()) != null) {
			String[] arr = line.split(",");
			inventoryTable = new InventoryTable();
			inventoryTable.setCode(arr[0]);
			inventoryTable.setName(arr[1]);
			inventoryTable.setBatch(arr[2]);
			inventoryTable.setStock(Integer.parseInt(arr[3]));
			inventoryTable.setDeal(Integer.parseInt(arr[4]));
			inventoryTable.setFree(Integer.parseInt(arr[5]));
			inventoryTable.setMrp(Float.parseFloat(arr[6]));
			inventoryTable.setRate(Float.parseFloat(arr[7]));
			Date date= null;
			try {
				//convert string date to database date
				date = new SimpleDateFormat("dd/MM/yyyy").parse(arr[8]);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			inventoryTable.setExp(date);
			inventoryTable.setCompany(arr[9]);
			//Handle exception if supplier is empty
			try {
				
				inventoryTable.setSupplier(arr[10]);
			}catch (Exception e) {
			}
			inventoryDao.save(inventoryTable);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return  Response.buildResponse(HttpStatus.OK);
	}
	//method for get products by product name
	@Override
	public ResponseEntity<Response> getproductsByName(String name) {
		List<InventoryTable> data = inventoryDao.getproductsByName(name);
		if(null==data || data.isEmpty())
			return Response.buildResponse("No Data Found", HttpStatus.NO_CONTENT);
		else 
			return Response.buildResponse(data, HttpStatus.OK);
	}
	//method for search products by product name
	@Override
	public ResponseEntity<Response> search(String name) {
		List<InventoryTable> data = inventoryDao.findByNameContaining(name);
		if(null==data || data.isEmpty())
			return Response.buildResponse("No Data Found", HttpStatus.NO_CONTENT);
		else 
			return Response.buildResponse(data, HttpStatus.OK);
	}
	//method for get products by supplier name
	@Override
	public ResponseEntity<Response> productsBySuppliers(String name) {
		List<InventoryTable> data = inventoryDao.productsBySuppliers(name);
		if(null==data || data.isEmpty())
			return Response.buildResponse("No Data Found", HttpStatus.NO_CONTENT);
		else 
			return Response.buildResponse(data, HttpStatus.OK);
	}
	
}
