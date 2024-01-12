package com.insignia.daoInterface;

import java.util.List;
import java.util.Optional;


import com.insignia.entity.ProductMaterial;

public interface ProductMaterialDaoInterface {

	public ProductMaterial saveProductMaterial(ProductMaterial productMaterial);
	
	public ProductMaterial updateProductMaterial(ProductMaterial productMaterial);
	
	public void deleteProductMaterial(String materialName);
	
	public List<ProductMaterial> getAllProductMaterials();
	
	public Object findByMaterialName(String materialName);
	
	public Optional<ProductMaterial> findBySequenceNumber(Integer sequenceNumber);
}
