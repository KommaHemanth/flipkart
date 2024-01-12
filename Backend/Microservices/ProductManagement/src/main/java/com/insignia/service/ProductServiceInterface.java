package com.insignia.service;

import java.util.List;
import java.util.Optional;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.ProductManagementRequest;
import com.insignia.model.ProductResponse;

public interface ProductServiceInterface {
	public ProductResponse addProduct(ProductManagementRequest productManagementRequest)
			throws InvalidInputParametersException, TokenExpiredException;

	public ProductResponse updateProduct(ProductManagementRequest productManagementRequest)
			throws InvalidInputParametersException, TokenExpiredException;

	public void deleteProductById(Long productSequenceNumber, Long customerSequenceNumber, Integer expirationDuration)
			throws InvalidInputParametersException, TokenExpiredException;

	public Optional<ProductResponse> getProductById(Long productSequenceNumber, Long customerSequenceNumber,
			Integer expirationDuration) throws InvalidInputParametersException, TokenExpiredException;

	public List<ProductResponse> getAllProducts(Long customerSequenceNumber, Integer expirationDuration)
			throws TokenExpiredException, InvalidInputParametersException;

	public List<ProductResponse> filterProduct(ProductManagementRequest productManagementRequest) throws TokenExpiredException;
}
