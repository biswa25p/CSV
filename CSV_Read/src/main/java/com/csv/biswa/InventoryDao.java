package com.csv.biswa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryDao extends JpaRepository<InventoryTable, Long> {
	@Query("SELECT a FROM InventoryTable a where a.name=:name")
	List<InventoryTable> getproductsByName(@Param("name") String name);

//	@Query("SELECT a FROM InventoryTable a where a.name like :name")
	@Query("SELECT a FROM InventoryTable a where a.name LIKE %?1%")
	List<InventoryTable> findByNameContaining(String name);

	@Query("SELECT a FROM InventoryTable a where a.supplier=:name and a.exp >= SYSDATE()")
	List<InventoryTable> productsBySuppliers(String name);


}
