package com.insignia.daoInterface;

import java.util.List;
import java.util.Optional;

import com.insignia.entity.ProductColour;

public interface ProductColourDaoInterface {

	public ProductColour saveProductColour(ProductColour productColour);
	
	public ProductColour updateProductColour(ProductColour productColour);
	
	public void deleteProductColour(String colourName);
	
	public List<ProductColour> getAllProductColour();
	
	public Object findByColourName(String colourName);
	
	public Optional<ProductColour> findBySequenceNumber(Integer sequenceNumber);
}
