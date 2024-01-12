package com.insignia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.ProductDetailsResponse;
import com.insignia.model.ProductManagementRequest;
import com.insignia.model.ProductRequest;
import com.insignia.model.ProductResponse;
import com.insignia.service.ProductServiceInterface;

@ExtendWith(MockitoExtension.class)
public class TestProductController {

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductServiceInterface productServiceRepo;

	List<ProductResponse> productResponseList = new ArrayList<>();

	ProductManagementRequest productManagementRequest = new ProductManagementRequest();

	ProductRequest productRequest = new ProductRequest();
	ProductResponse productResponse = new ProductResponse();

	ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();

	public void dataInitilization() {

		productManagementRequest.setCustomerSequenceNumber(5L);
		productManagementRequest.setExpirationDuration(15);

		productRequest.setProductSequenceNumber(5L);
		productRequest.setProductId("5L");
		productRequest.setProductName("mobile");
		productRequest.setDescription("Very smart Mobile");
		productRequest.setMeasuringQuantity("2");
		productRequest.setMeasuringUnit("5");
		productRequest.setSubcategoryId(5L);
		productRequest.setProductImage("Mobile");
		productRequest.setProductPerUnitActualPrice(3F);
		productRequest.setProductPerUnitCurrentPrice(4F);

		productManagementRequest.setProductRequest(productRequest);

		productDetailsResponse.setProductSequenceNumber(5L);
		productDetailsResponse.setProductId("5L");
		productDetailsResponse.setProductName("mobile");
		productDetailsResponse.setDescription("Very smart Mobile");
		productDetailsResponse.setMeasuringQuantity("2");
		productDetailsResponse.setMeasuringUnit("5");
		productDetailsResponse.setSubcategoryId(5L);
		productDetailsResponse.setProductImage("Mobile");

		productResponseList.add(productResponse);

	}

	public ProductResponse getProductResponse() {
		productDetailsResponse.setProductSequenceNumber(5L);
		productDetailsResponse.setProductId("5L");
		productDetailsResponse.setProductName("mobile");
		productDetailsResponse.setDescription("Very smart Mobile");
		productDetailsResponse.setMeasuringQuantity("2");
		productDetailsResponse.setMeasuringUnit("5");
		productDetailsResponse.setSubcategoryId(5L);
		productDetailsResponse.setProductImage("Mobile");
		productResponse.setProductDetailsResponse(productDetailsResponse);
		return productResponse;
	}

	@Test
	public void testAddProduct() throws InvalidInputParametersException, TokenExpiredException {
		dataInitilization();

		when(productServiceRepo.addProduct(productManagementRequest)).thenReturn(productResponse);
		ResponseEntity<?> response = productController.addProduct(productManagementRequest);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testUpdateProduct() throws InvalidInputParametersException, TokenExpiredException {
		dataInitilization();

		when(productServiceRepo.updateProduct(productManagementRequest)).thenReturn(productResponse);
		ResponseEntity<?> response = productController.updateProduct(productManagementRequest);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testDeleteByproductId() throws Exception {

		Long productSequenceNumber = 5L;
		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		doNothing().when(productServiceRepo).deleteProductById(productSequenceNumber, customerSequenceNumber,
				expirationDuration);

		productController.deleteByproductId(productSequenceNumber, customerSequenceNumber, expirationDuration);
		verify(productServiceRepo, times(1)).deleteProductById(productSequenceNumber, customerSequenceNumber,
				expirationDuration);

	}

	@Test
	public void testGetproductById() throws TokenExpiredException, InvalidInputParametersException {
		dataInitilization();
		getProductResponse();

		Long productSequenceNumber = 5L;
		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		Optional<ProductResponse> optionalResponse = Optional.ofNullable(productResponse);

		when(productServiceRepo.getProductById(productSequenceNumber, customerSequenceNumber, expirationDuration))
				.thenReturn(optionalResponse);
		ResponseEntity<?> response = productController.getproductById(productSequenceNumber, customerSequenceNumber,
				expirationDuration);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testGetAllProducts() throws TokenExpiredException, InvalidInputParametersException {
		dataInitilization();
		getProductResponse();

		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		when(productServiceRepo.getAllProducts(customerSequenceNumber, expirationDuration))
				.thenReturn(productResponseList);
		ResponseEntity<?> response = productController.getAllProducts(customerSequenceNumber, expirationDuration);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testFilterCriteria() throws TokenExpiredException, InvalidInputParametersException {
		dataInitilization();
		getProductResponse();

		when(productServiceRepo.filterProduct(productManagementRequest)).thenReturn(productResponseList);
		ResponseEntity<?> filterCriteria = productController.filterCriteria(productManagementRequest);
		assertEquals(HttpStatus.OK, filterCriteria.getStatusCode());
	}

	@Test
	public void testForTokenExpired() throws TokenExpiredException, InvalidInputParametersException {
		dataInitilization();
		Long customerSequenceNumber = 5L;
		Long productSequenceNumber = 5L;
		Integer expirationDuration = 15;

		when(productServiceRepo.addProduct(productManagementRequest)).thenThrow(new TokenExpiredException(""));
		ResponseEntity<?> addProduct = productController.addProduct(productManagementRequest);

		assertEquals(HttpStatus.BAD_REQUEST, addProduct.getStatusCode());

		when(productServiceRepo.updateProduct(productManagementRequest)).thenThrow(new TokenExpiredException(""));
		ResponseEntity<?> updateProduct = productController.updateProduct(productManagementRequest);

		assertEquals(HttpStatus.BAD_REQUEST, updateProduct.getStatusCode());

		doThrow(new TokenExpiredException("")).when(productServiceRepo).deleteProductById(productSequenceNumber,
				customerSequenceNumber, expirationDuration);
		ResponseEntity<?> deleteByproductId = productController.deleteByproductId(productSequenceNumber,
				customerSequenceNumber, expirationDuration);

		assertEquals(HttpStatus.BAD_REQUEST, deleteByproductId.getStatusCode());

		when(productServiceRepo.getProductById(productSequenceNumber, customerSequenceNumber, expirationDuration))
				.thenThrow(new TokenExpiredException(""));
		ResponseEntity<?> getproductById = productController.getproductById(productSequenceNumber,
				customerSequenceNumber, expirationDuration);

		assertEquals(HttpStatus.BAD_REQUEST, getproductById.getStatusCode());

		when(productServiceRepo.getAllProducts(customerSequenceNumber, expirationDuration))
				.thenThrow(new TokenExpiredException(""));
		ResponseEntity<?> getAllProducts = productController.getAllProducts(customerSequenceNumber, expirationDuration);

		assertEquals(HttpStatus.BAD_REQUEST, getAllProducts.getStatusCode());

		when(productServiceRepo.filterProduct(productManagementRequest)).thenThrow(new TokenExpiredException(""));
		ResponseEntity<?> filterCriteria = productController.filterCriteria(productManagementRequest);

		assertEquals(HttpStatus.BAD_REQUEST, filterCriteria.getStatusCode());

	}

	@Test
	public void testForException() throws TokenExpiredException, InvalidInputParametersException {
		dataInitilization();
		Long customerSequenceNumber = 5L;
		Long customerCartSequenceNumber = 5L;
		Integer expirationDuration = 15;

		when(productServiceRepo.addProduct(productManagementRequest)).thenThrow(new NullPointerException(""));
		ResponseEntity<?> addProduct = productController.addProduct(productManagementRequest);

		assertEquals(HttpStatus.BAD_REQUEST, addProduct.getStatusCode());

		when(productServiceRepo.updateProduct(productManagementRequest)).thenThrow(new NullPointerException(""));
		ResponseEntity<?> updateProduct = productController.updateProduct(productManagementRequest);

		assertEquals(HttpStatus.BAD_REQUEST, updateProduct.getStatusCode());

		doThrow(new NullPointerException("")).when(productServiceRepo).deleteProductById(customerCartSequenceNumber,
				customerSequenceNumber, expirationDuration);
		ResponseEntity<?> deleteByproductId = productController.deleteByproductId(customerCartSequenceNumber,
				customerSequenceNumber, expirationDuration);

		assertEquals(HttpStatus.BAD_REQUEST, deleteByproductId.getStatusCode());

		when(productServiceRepo.getProductById(customerCartSequenceNumber, customerSequenceNumber, expirationDuration))
				.thenThrow(new NullPointerException(""));
		ResponseEntity<?> getproductById = productController.getproductById(customerCartSequenceNumber,
				customerSequenceNumber, expirationDuration);

		assertEquals(HttpStatus.BAD_REQUEST, getproductById.getStatusCode());

		when(productServiceRepo.getAllProducts(customerSequenceNumber, expirationDuration))
				.thenThrow(new NullPointerException(""));
		ResponseEntity<?> getAllProducts = productController.getAllProducts(customerSequenceNumber, expirationDuration);

		assertEquals(HttpStatus.BAD_REQUEST, getAllProducts.getStatusCode());

		when(productServiceRepo.filterProduct(productManagementRequest)).thenThrow(new NullPointerException(""));
		ResponseEntity<?> filterCriteria = productController.filterCriteria(productManagementRequest);

		assertEquals(HttpStatus.BAD_REQUEST, filterCriteria.getStatusCode());
	}

	@Test
	public void testForInvalidInputParametersException()
			throws InvalidInputParametersException, TokenExpiredException, ParseException {
		dataInitilization();

		Long productSequenceNumber = 5L;
		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		doThrow(new InvalidInputParametersException("255", "Invalid data")).when(productServiceRepo)
				.addProduct(productManagementRequest);

		ResponseEntity<?> response = productController.addProduct(productManagementRequest);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		doThrow(new InvalidInputParametersException("255", "Invalid data")).when(productServiceRepo)
				.updateProduct(productManagementRequest);

		ResponseEntity<?> updateProduct = productController.updateProduct(productManagementRequest);
		assertEquals(HttpStatus.BAD_REQUEST, updateProduct.getStatusCode());

		doThrow(new InvalidInputParametersException("255", "Invalid data")).when(productServiceRepo)
				.deleteProductById(productSequenceNumber, customerSequenceNumber, expirationDuration);

		ResponseEntity<?> deleteByproductId = productController.deleteByproductId(productSequenceNumber,
				customerSequenceNumber, expirationDuration);
		assertEquals(HttpStatus.BAD_REQUEST, deleteByproductId.getStatusCode());
	}

}
