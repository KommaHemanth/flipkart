package com.insignia.daoImpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insignia.daoInterface.ProductColourDaoInterface;
import com.insignia.entity.ProductColour;
import com.insignia.repo.ProductColorRepository;

@Repository
public class ProductColourDaoImpl implements ProductColourDaoInterface {

	@Autowired
	private ProductColorRepository productColorRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public ProductColour saveProductColour(ProductColour productColour) {

		return productColorRepository.save(productColour);
	}

	@Override
	public ProductColour updateProductColour(ProductColour productColour) {
		return entityManager.merge(productColour);
	}

	@Override
	public void deleteProductColour(String colourName) {
		productColorRepository.deleteByColourName(colourName);

	}

	@Override
	public List<ProductColour> getAllProductColour() {
		return productColorRepository.findAll();
	}

	@Override
	public Object findByColourName(String colourName) {
		return productColorRepository.findByColourName(colourName);
	}

	@Override
	public Optional<ProductColour> findBySequenceNumber(Integer sequenceNumber) {
		return productColorRepository.findById(sequenceNumber);
	}

}
