package com.insignia.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insignia.entity.ProductColour;

public interface ProductColorRepository extends JpaRepository<ProductColour, Serializable> {

	public static final String COLOUR_NAME = "colour_name";

	public static final String findByColourName = "select colour_name from product_colour where colour_name = :colour_name";

	public static final String deleteByColourName = "delete from product_colour where colour_name = :colour_name";
	
	
	@Query(value = findByColourName, nativeQuery = true)
	public Object findByColourName(@Param(COLOUR_NAME) String colourName);

	
	@Modifying
	@Query(value = deleteByColourName, nativeQuery = true)
	public void deleteByColourName(@Param(COLOUR_NAME) String colourName);
}
