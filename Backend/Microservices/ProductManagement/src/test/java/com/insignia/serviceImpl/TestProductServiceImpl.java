package com.insignia.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.ProductDaoInterface;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.entity.ProductDetails;
import com.insignia.entity.ProductSubCategory;
import com.insignia.model.ProductDetailsResponse;
import com.insignia.model.ProductFilterCriteria;
import com.insignia.model.ProductManagementRequest;
import com.insignia.model.ProductRequest;
import com.insignia.model.ProductResponse;
import com.insignia.serviceimpl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestProductServiceImpl {

	@InjectMocks
	private ProductServiceImpl productServiceImpl;

	@Mock
	private ProductDaoInterface productDaoRepo;

	@Mock
	private TokenDaoInterface tokenRepo;

	List<ProductResponse> productResponseList = new ArrayList<>();

	ProductRequest productRequest = new ProductRequest();
	ProductResponse productResponse = new ProductResponse();

	ProductDetails productDetails = new ProductDetails();
	List<ProductDetails> productDetailsList = new ArrayList<>();

	ProductSubCategory productSubCategory = new ProductSubCategory();
	ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();

	ProductManagementRequest productManagementRequest = new ProductManagementRequest();

	ProductFilterCriteria productFilterCriteria = new ProductFilterCriteria();

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
		productRequest.isProductNameUpdated();

		productFilterCriteria.setColour("Red");
		productFilterCriteria.setMaterial("wood");
		productFilterCriteria.setMinPrice(12f);
		productFilterCriteria.setMaxPrice(4f);
		productFilterCriteria.setSortingCriteria("ASC");
		productFilterCriteria.setSubCategory("62");
		productFilterCriteria.setAvailability("yes");

		productManagementRequest.setProductRequest(productRequest);
		productManagementRequest.setFilterCriteria(productFilterCriteria);

		productDetailsResponse.setProductSequenceNumber(5L);
		productDetailsResponse.setProductId("5L");
		productDetailsResponse.setProductName("mobile");
		productDetailsResponse.setDescription("Very smart Mobile");
		productDetailsResponse.setMeasuringQuantity("2");
		productDetailsResponse.setMeasuringUnit("5");
		productDetailsResponse.setSubcategoryId(5L);
		productDetailsResponse.setProductImage("Mobile");

		productResponse.setProductDetailsResponse(productDetailsResponse);
		productResponseList.add(productResponse);

		productDetails.setProductId("5L");
		productDetails.setProductName("mobile");
		productDetails.setDescription("Very smart Mobile");
		productDetails.setMeasuringQuantity("2");
		productDetails.setMeasuringUnit("5");
		productDetails.setSubcategoryId(5L);
		productDetails.setProductImage("Mobile");

		productDetailsList.add(productDetails);

		productSubCategory.setCategoryId(5L);
		productSubCategory.setSubCategoryName("phone");

		productSubCategory.setSubCategoryDescription("Its vary high");
		productSubCategory.setSubCategoryImagePath("D drive");

	}

	public ProductDetails getProductDetails() {
		productDetails.setProductSequenceNumber(5L);
		productDetails.setProductId("5L");
		productDetails.setProductName("mobile");
		productDetails.setDescription("Very smart Mobile");
		productDetails.setMeasuringQuantity("2");
		productDetails.setMeasuringUnit("5");
		productDetails.setSubcategoryId(5L);
		productDetails.setProductImage("Mobile");
		return productDetails;
	}

	public ProductSubCategory getProductSubCategory() {

		productSubCategory.setCategoryId(5L);
		productSubCategory.setSubCategoryName("phone");

		productSubCategory.setSubCategoryDescription("Its vary high");
		productSubCategory.setSubCategoryImagePath("D drive");
		productSubCategory.setSubCategoryId(1L);

		return productSubCategory;
	}

	public ProductResponse getProductResponse() {
		ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();

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
	public void testAddProduct() throws TokenExpiredException, InvalidInputParametersException {

		dataInitilization();
		getProductSubCategory();
		Long subcategoryId = 5L;

		dataInitilization();
		getProductDetails();
		productRequest = new ProductRequest();
		productRequest.setProductName("HI");
		productRequest.setDescription("ItsGood");
		productRequest.setMeasuringUnit("4");
		productRequest.setMeasuringQuantity("5");
		productRequest.setSubcategoryId(5L);
		productRequest.setProductPerUnitActualPrice(2f);
		productRequest.setProductPerUnitCurrentPrice(3f);
		productRequest.setProductLength(3f);
		productRequest.setHeight(2f);
		productRequest.setWidth(1f);
		productRequest.setDimensionUnit("Inches");
		productRequest.setProductSequenceNumber(5L);
		productRequest.setProductImage("Apple");
		productRequest.setMaterials("Mobile");
		productRequest.setColours("Pink");

		productManagementRequest.setProductRequest(productRequest);

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(productDaoRepo.findByProductSubCategory(subcategoryId)).thenReturn(true);

		Mockito.when(productDaoRepo.addProduct(Mockito.any(ProductDetails.class))).thenReturn(productDetails);

		ProductResponse addProduct = productServiceImpl.addProduct(productManagementRequest);

		assertNotNull(addProduct);
	}

	@Test
	public void testAddProductSubcategoryId_IsNotFoundException()
			throws TokenExpiredException, InvalidInputParametersException {

		dataInitilization();
		Long subcategoryId = 5L;

		when(productDaoRepo.findByProductSubCategory(subcategoryId)).thenReturn(false);

		assertThrows(InvalidInputParametersException.class, () -> {
			productServiceImpl.addProduct(productManagementRequest);

		});
		verify(productDaoRepo, times(1)).findByProductSubCategory(subcategoryId);

	}

	@Test
	public void testUpdateProduct() throws TokenExpiredException, InvalidInputParametersException {

		dataInitilization();
		getProductDetails();
		productRequest = new ProductRequest();
		productRequest.setProductName("HI");
		productRequest.setDescription("ItsGood");
		productRequest.setMeasuringUnit("4");
		productRequest.setMeasuringQuantity("5");
		productRequest.setSubcategoryId(5L);
		productRequest.setProductPerUnitActualPrice(2f);
		productRequest.setProductPerUnitCurrentPrice(3f);
		productRequest.setProductLength(3f);
		productRequest.setHeight(2f);
		productRequest.setWidth(1f);
		productRequest.setDimensionUnit("Inches");
		productRequest.setProductSequenceNumber(5L);
		productRequest.setProductImage("Apple");
		productRequest.setMaterials("Mobile");
		productRequest.setColours("Pink");

		productRequest.isColoursUpdated();

		productManagementRequest.setProductRequest(productRequest);
		Long productSequenceNumber = 5L;

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		Optional<ProductDetails> optional = Optional.ofNullable(productDetails);
		when(productDaoRepo.findById(productSequenceNumber)).thenReturn(optional);

		getProductSubCategory();
		Long subcategoryId = 5L;

		when(productDaoRepo.findByProductSubCategory(subcategoryId)).thenReturn(true);

		Mockito.when(productDaoRepo.updateProduct(productDetails)).thenReturn(productDetails);

		ProductResponse updateProduct = productServiceImpl.updateProduct(productManagementRequest);

		assertNotNull(updateProduct);

		verify(productDaoRepo, times(1)).findById(productSequenceNumber);
		verify(productDaoRepo, times(1)).findByProductSubCategory(subcategoryId);
		verify(productDaoRepo, times(1)).updateProduct(any());

	}

	@Test
	public void testUpdateProduct_ProductDetailsNotFound() {
		productManagementRequest.setProductRequest(productRequest);
		when(productDaoRepo.findById(any())).thenReturn(Optional.empty());

		assertThrows(InvalidInputParametersException.class,
				() -> productServiceImpl.updateProduct(productManagementRequest));

		verify(productDaoRepo, times(1)).findById(any());

	}

	@Test
	public void testDeleteByproductId() throws TokenExpiredException, InvalidInputParametersException {

		Long productSequenceNumber = 5L;
		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(productDaoRepo.findById(productSequenceNumber)).thenReturn(Optional.of(productDetails));
		productServiceImpl.deleteProductById(productSequenceNumber, customerSequenceNumber, expirationDuration);

		verify(productDaoRepo, times(1)).deleteProductById(productSequenceNumber);

	}

	@Test
	public void testDeleteProduct_ProductDetailsNotFound() {

		Long productSequenceNumber = 5L;
		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;
		when(productDaoRepo.findById(any())).thenReturn(Optional.empty());

		assertThrows(InvalidInputParametersException.class, () -> productServiceImpl
				.deleteProductById(productSequenceNumber, customerSequenceNumber, expirationDuration));

		verify(productDaoRepo, times(1)).findById(any());
	}

	@Test
	public void testGetProductById() throws TokenExpiredException, InvalidInputParametersException {

		dataInitilization();
		getProductResponse();

		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;
		Long productSequenceNumber = 5L;

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(productDaoRepo.findById(productSequenceNumber)).thenReturn(Optional.of(productDetails));
		Optional<ProductResponse> productById = productServiceImpl.getProductById(productSequenceNumber,
				customerSequenceNumber, expirationDuration);

		assertNotNull(productById);
	}

	@Test
	public void testGetProductByIdEmptyList() throws TokenExpiredException, InvalidInputParametersException {

		dataInitilization();
		getProductResponse();

		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		Optional<ProductResponse> productById = productServiceImpl.getProductById(customerSequenceNumber,
				customerSequenceNumber, expirationDuration);

		assertNotNull(productById);
	}

	@Test
	public void testGetAllProductsList() throws TokenExpiredException, InvalidInputParametersException {

		dataInitilization();
		getProductResponse();

		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		Mockito.when(productDaoRepo.getAllProducts()).thenReturn(productDetailsList);

		List<ProductResponse> allProducts = productServiceImpl.getAllProducts(customerSequenceNumber,
				expirationDuration);

		assertNotNull(allProducts);
	}

	@Test
	public void testSubcategoryIdNotFoundException() throws TokenExpiredException, InvalidInputParametersException {

		dataInitilization();
		Long subcategoryId = 5L;

		when(productDaoRepo.findByProductSubCategory(subcategoryId)).thenReturn(false);

		assertThrows(InvalidInputParametersException.class,
				() -> productServiceImpl.addProduct(productManagementRequest));

		assertThrows(InvalidInputParametersException.class,
				() -> productServiceImpl.updateProduct(productManagementRequest));

	}

	@Test
	public void testSubcategoryIdNotFoundInUpdateException()
			throws TokenExpiredException, InvalidInputParametersException {

		dataInitilization();
		Long subcategoryId = 5L;

		dataInitilization();
		getProductDetails();
		productRequest = new ProductRequest();
		productRequest.setProductName("HI");
		productRequest.setDescription("ItsGood");
		productRequest.setMeasuringUnit("4");
		productRequest.setMeasuringQuantity("5");
		productRequest.setSubcategoryId(5L);

		productRequest.setProductSequenceNumber(5L);

		productManagementRequest.setProductRequest(productRequest);
		when(productDaoRepo.findById(any())).thenReturn(Optional.of(productDetails));
		when(productDaoRepo.findByProductSubCategory(subcategoryId)).thenReturn(false);

		assertThrows(InvalidInputParametersException.class,
				() -> productServiceImpl.updateProduct(productManagementRequest));

	}

	@Test
	public void testFilterCriteria() throws TokenExpiredException, InvalidInputParametersException {

		dataInitilization();
		getProductResponse();

		productFilterCriteria.setColour("Red");
		productFilterCriteria.setMaterial("Wood");
		productFilterCriteria.setMinPrice(12f);
		productFilterCriteria.setMaxPrice(4f);
		productFilterCriteria.setSortingCriteria("ASC");
		productFilterCriteria.setSubCategory("62");
		productFilterCriteria.setAvailability(productFilterCriteria.getAvailability());

		Map<String, Object> filterCriteria = new HashMap<>();
		filterCriteria.put("Colour", "Red");
		filterCriteria.put("Material", "Wood");
		filterCriteria.put("MinPrice", 12f);
		filterCriteria.put("MaxPrice", 4f);
		filterCriteria.put("SortingCriteria", "ASC");
		filterCriteria.put("SubCategory", "62");
		filterCriteria.put("Availability", "yes");

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		Mockito.when(productDaoRepo.filterProducts(filterCriteria)).thenReturn(productDetailsList);

		List<ProductResponse> allProducts = productServiceImpl.filterProduct(productManagementRequest);

		assertNotNull(allProducts);
	}

	@Test
	public void testFilterCriteriaWithGetAllProducts() throws TokenExpiredException, InvalidInputParametersException {

		dataInitilization();
		getProductResponse();

		productFilterCriteria.setToGetAllProduct(true);

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		List<ProductResponse> allProducts = productServiceImpl.filterProduct(productManagementRequest);

		assertNotNull(allProducts);
	}

}
