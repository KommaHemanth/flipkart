package com.insignia.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insignia.entity.ProductMaterial;

public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Serializable> {

	public static final String MATERIAL_NAME = "material_name";
	
	public static final String findByMaterialName = "select material_name from product_material where material_name = :material_name";
	
	public static final String deleteByMaterialName = "delete from product_material where material_name = :material_name";

	@Query(value = findByMaterialName, nativeQuery = true)
	public Object findByMaterialName(@Param("material_name") String materialName);
	
	@Modifying
	@Query(value = deleteByMaterialName, nativeQuery = true)
	public void deleteByMaterialName(@Param(MATERIAL_NAME) String materialName);

}
