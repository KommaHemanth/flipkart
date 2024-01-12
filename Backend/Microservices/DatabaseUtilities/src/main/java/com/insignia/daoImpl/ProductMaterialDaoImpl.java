package com.insignia.daoImpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insignia.daoInterface.ProductMaterialDaoInterface;
import com.insignia.entity.ProductMaterial;
import com.insignia.repo.ProductMaterialRepository;

@Repository
public class ProductMaterialDaoImpl implements ProductMaterialDaoInterface {

	@Autowired
	private ProductMaterialRepository productMaterialRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public ProductMaterial saveProductMaterial(ProductMaterial productMaterial) {

		return productMaterialRepository.save(productMaterial);
	}

	@Override
	public ProductMaterial updateProductMaterial(ProductMaterial productMaterial) {
		return entityManager.merge(productMaterial);
	}

	@Override
	public void deleteProductMaterial(String unitName) {
		productMaterialRepository.deleteByMaterialName(unitName);

	}

	@Override
	public List<ProductMaterial> getAllProductMaterials() {
		return productMaterialRepository.findAll();
	}

	@Override
	public Object findByMaterialName(String materialName) {
		return productMaterialRepository.findByMaterialName(materialName);
	}

	@Override
	public Optional<ProductMaterial> findBySequenceNumber(Integer sequenceNumber) {
		return productMaterialRepository.findById(sequenceNumber);
	}

}
