package com.insignia.daoInterface;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.entity.ProductDetails;

public interface ProductDaoInterface {

	public ProductDetails addProduct(ProductDetails productDetails) throws InvalidInputParametersException;

	public ProductDetails updateProduct(ProductDetails productDetails) throws InvalidInputParametersException;

	public void deleteProductById(Long productSequenceNumber);

	public Optional<ProductDetails> findById(Long productSequenceNumber);
	
	public List<ProductDetails>getAllProducts();

	public Boolean isTokenNotValid(Long customerSequenceNumber);

	public boolean findByProductSubCategory(Long subcategoryId);
	
	public  List<ProductDetails> getSelectedProductDetailsList(List<Long>productSequenceNumberList);
	
	public List<ProductDetails> filterProducts(Map<String, Object> filterCriteria);
}
