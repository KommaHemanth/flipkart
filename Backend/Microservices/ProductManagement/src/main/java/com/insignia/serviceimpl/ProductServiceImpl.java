package com.insignia.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insignia.constants.CommonConstant;
import com.insignia.constants.ProductValidatorConstants;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.ProductDaoInterface;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.entity.ProductDetails;
import com.insignia.model.ProductDetailsResponse;
import com.insignia.model.ProductFilterCriteria;
import com.insignia.model.ProductManagementRequest;
import com.insignia.model.ProductRequest;
import com.insignia.model.ProductResponse;
import com.insignia.service.ProductServiceInterface;

@Service
public class ProductServiceImpl implements ProductServiceInterface {

	@Autowired
	private ProductDaoInterface productDaoInterface;

	@Autowired
	private TokenDaoInterface tokenDao;

	@Transactional
	@Override
	public ProductResponse addProduct(ProductManagementRequest productManagementRequest)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(productManagementRequest.getCustomerSequenceNumber(),
				productManagementRequest.getExpirationDuration());
		ProductRequest productRequest = productManagementRequest.getProductRequest();
		if (!productDaoInterface.findByProductSubCategory(productRequest.getSubcategoryId())) {
			throw new InvalidInputParametersException(ProductValidatorConstants.subcategoryIdIsNotFoundErrorCode,
					ProductValidatorConstants.subcategoryIdIsNotFoundMessage);
		}

		ProductDetails updateProductDetails = productDaoInterface
				.addProduct(createRequestForProductDetailsEntity(productRequest));

		return createResponceForProductDetailsEntity(updateProductDetails);

	}

	@Transactional
	@Override
	public ProductResponse updateProduct(ProductManagementRequest productManagementRequest)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(productManagementRequest.getCustomerSequenceNumber(),
				productManagementRequest.getExpirationDuration());
		ProductRequest productRequest = productManagementRequest.getProductRequest();
		if (productRequest.isProductIdUpdated()) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validateProductIdNotUpdatedErrorCode,
					ProductValidatorConstants.validateProductIdNotUpdatedMessage);
		}
		Optional<ProductDetails> optionproduct = productDaoInterface
				.findById(productRequest.getProductSequenceNumber());

		if (optionproduct.isPresent()) {

			ProductDetails productEntity = optionproduct.get();

			if (productEntity != null) {
				if (productRequest.isProductNameUpdated()) {
					productEntity.setProductName(productRequest.getProductName());
				}
				if (productRequest.isDescriptionUpdated()) {
					productEntity.setDescription(productRequest.getDescription());
				}
				if (productRequest.isMeasuringUnitUpdated()) {
					productEntity.setMeasuringUnit(productRequest.getMeasuringUnit());
				}
				if (productRequest.isMeasuringQuantityUpdated()) {
					productEntity.setMeasuringQuantity(productRequest.getMeasuringQuantity());
				}
				if (productRequest.isSubcategoryIdUpdated()) {
					if (!productDaoInterface.findByProductSubCategory(productRequest.getSubcategoryId())) {
						throw new InvalidInputParametersException(
								ProductValidatorConstants.subcategoryIdIsNotFoundErrorCode,
								ProductValidatorConstants.subcategoryIdIsNotFoundMessage);
					}

					productEntity.setSubcategoryId(productRequest.getSubcategoryId());
				}
				if (productRequest.isProductImageUpdated()) {
					productEntity.setProductImage(productRequest.getProductImage());
				}
				if (productRequest.isProductPerUnitActualPriceUpdated()) {
					productEntity.setProductPerUnitActualPrice(productRequest.getProductPerUnitActualPrice());
				}
				if (productRequest.isProductPerUnitCurrentPriceUpdated()) {
					productEntity.setProductPerUnitCurrentPrice(productRequest.getProductPerUnitCurrentPrice());
				}
				if (productRequest.isLengthUpdated()) {
					productEntity.setLength(productRequest.getProductLength());
				}
				if (productRequest.isHeightUpdated()) {
					productEntity.setHeight(productRequest.getHeight());
				}
				if (productRequest.isWidthUpdated()) {
					productEntity.setWidth(productRequest.getWidth());
				}
				if (productRequest.isDimensionUnitUpdated()) {
					productEntity.setDimensionUnit(productRequest.getDimensionUnit());
				}
				if (productRequest.isMaterialsUpdated()) {
					productEntity.setMaterials(productRequest.getMaterials());
				}
				if (productRequest.isColoursUpdated()) {
					productEntity.setColours(productRequest.getColours());
				}
			}
			return createResponceForProductDetailsEntity(productDaoInterface.updateProduct(productEntity));

		} else {
			throw new InvalidInputParametersException(ProductValidatorConstants.productDetailsIsNotFoundErrorCode,
					ProductValidatorConstants.productDetailsIsNotFoundMessage);
		}

	}

	@Transactional
	@Override
	public void deleteProductById(Long productSequenceNumber, Long customerSequenceNumber, Integer expirationDuration)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(customerSequenceNumber, expirationDuration);

		Optional<ProductDetails> productDetails = productDaoInterface.findById(productSequenceNumber);

		if (productDetails.isPresent()) {
			productDaoInterface.deleteProductById(productSequenceNumber);

		} else {
			throw new InvalidInputParametersException(ProductValidatorConstants.productDetailsIsNotFoundErrorCode,
					ProductValidatorConstants.productDetailsIsNotFoundMessage);
		}
	}

	@Transactional
	@Override
	public Optional<ProductResponse> getProductById(Long productSequenceNumber, Long customerSequenceNumber,
			Integer expirationDuration) throws InvalidInputParametersException, TokenExpiredException {

		if (customerSequenceNumber != CommonConstant.nonLoggedCustomerSequenceNumber) {
			tokenDao.checkTokenValidity(customerSequenceNumber, expirationDuration);
		}

		Optional<ProductDetails> getProductDetails = productDaoInterface.findById(productSequenceNumber);

		if (getProductDetails.isPresent()) {
			ProductDetails productDetails = getProductDetails.get();

			return Optional.of(createResponceForProductDetailsEntity(productDetails));
		}

		return Optional.empty();
	}

	@Transactional
	@Override
	public List<ProductResponse> getAllProducts(Long customerSequenceNumber, Integer expirationDuration)
			throws TokenExpiredException, InvalidInputParametersException {

		return getAllProductsInternal(customerSequenceNumber, expirationDuration);
	}

	private List<ProductResponse> getAllProductsInternal(Long customerSequenceNumber, Integer expirationDuration)
			throws TokenExpiredException {
		if (customerSequenceNumber != CommonConstant.nonLoggedCustomerSequenceNumber) {
			tokenDao.checkTokenValidity(customerSequenceNumber, expirationDuration);
		}

		List<ProductResponse> productResponseList = new ArrayList<>();
		List<ProductDetails> productDetailsList = productDaoInterface.getAllProducts();
		if (productDetailsList != null && !productDetailsList.isEmpty()) {
			for (ProductDetails productDetails : productDetailsList) {
				productResponseList.add(createResponceForProductDetailsEntity(productDetails));
			}

		}
		return productResponseList;
	}

	@Transactional
	@Override
	public List<ProductResponse> filterProduct(ProductManagementRequest productManagementRequest)
			throws TokenExpiredException {

		ProductFilterCriteria productFilterCriteria = productManagementRequest.getFilterCriteria();

		Long customerSequenceNumber = productManagementRequest.getCustomerSequenceNumber();
		Integer expirationDuration = productManagementRequest.getExpirationDuration();

		if (productFilterCriteria.isToGetAllProduct()) {
			return getAllProductsInternal(customerSequenceNumber, expirationDuration);
		} else {
			return filterProductInternal(customerSequenceNumber, expirationDuration, productFilterCriteria);
		}
	}

	private List<ProductResponse> filterProductInternal(Long customerSequenceNumber, Integer expirationDuration,
			ProductFilterCriteria productFilterCriteria) throws TokenExpiredException {
		if (customerSequenceNumber != CommonConstant.nonLoggedCustomerSequenceNumber) {
			tokenDao.checkTokenValidity(customerSequenceNumber, expirationDuration);
		}

		return filterProducts(productFilterCriteria);
	}

	private List<ProductResponse> filterProducts(ProductFilterCriteria productFilterCriteria) {
		Map<String, Object> filterCriteria = new HashMap<String, Object>();

		if (productFilterCriteria.getColour() != null) {
			filterCriteria.put(ProductValidatorConstants.filter_colour, productFilterCriteria.getColour());
		}
		if (productFilterCriteria.getMaterial() != null) {
			filterCriteria.put(ProductValidatorConstants.filter_material, productFilterCriteria.getMaterial());
		}
		if (productFilterCriteria.getMinPrice() != null) {
			filterCriteria.put(ProductValidatorConstants.filter_min_price, productFilterCriteria.getMinPrice());
		}
		if (productFilterCriteria.getMaxPrice() != null) {
			filterCriteria.put(ProductValidatorConstants.filter_max_price, productFilterCriteria.getMaxPrice());
		}
		if (productFilterCriteria.getSortingCriteria() != null) {
			filterCriteria.put(ProductValidatorConstants.filter_sorting_criteria,
					productFilterCriteria.getSortingCriteria());
		}
		if (productFilterCriteria.getSubCategory() != null) {
			filterCriteria.put(ProductValidatorConstants.filter_subcategory, productFilterCriteria.getSubCategory());
		}
		if (productFilterCriteria.getAvailability() != null) {
			filterCriteria.put(ProductValidatorConstants.filter_availability, productFilterCriteria.getAvailability());
		}

		List<ProductResponse> productResponseList = new ArrayList<>();
		List<ProductDetails> productDetailsList = productDaoInterface.filterProducts(filterCriteria);

		if (productDetailsList != null && !productDetailsList.isEmpty()) {
			for (ProductDetails productDetails : productDetailsList) {
				productResponseList.add(createResponceForProductDetailsEntity(productDetails));
			}

		}
		return productResponseList;
	}

	private ProductDetails createRequestForProductDetailsEntity(ProductRequest productRequest) {
		ProductDetails productEntity = new ProductDetails();

		productEntity.setProductId(productRequest.getProductId());
		productEntity.setProductName(productRequest.getProductName());
		productEntity.setDescription(productRequest.getDescription());
		productEntity.setMeasuringUnit(productRequest.getMeasuringUnit());
		productEntity.setMeasuringQuantity(productRequest.getMeasuringQuantity());
		productEntity.setSubcategoryId(productRequest.getSubcategoryId());
		productEntity.setProductImage(productRequest.getProductImage());
		productEntity.setProductPerUnitActualPrice(productRequest.getProductPerUnitActualPrice());
		productEntity.setProductPerUnitCurrentPrice(productRequest.getProductPerUnitCurrentPrice());
		productEntity.setLength(productRequest.getProductLength());
		productEntity.setWidth(productRequest.getWidth());
		productEntity.setHeight(productRequest.getHeight());
		productEntity.setDimensionUnit(productRequest.getDimensionUnit());
		productEntity.setMaterials(productRequest.getMaterials());
		productEntity.setColours(productRequest.getColours());
		return productEntity;
	}

	private ProductResponse createResponceForProductDetailsEntity(ProductDetails productDetails) {
		ProductResponse productResponce = new ProductResponse();

		ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
		productDetailsResponse.setProductSequenceNumber(productDetails.getProductSequenceNumber());
		productDetailsResponse.setProductId(productDetails.getProductId());
		productDetailsResponse.setProductName(productDetails.getProductName());
		productDetailsResponse.setDescription(productDetails.getDescription());
		productDetailsResponse.setMeasuringQuantity(productDetails.getMeasuringQuantity());
		productDetailsResponse.setMeasuringUnit(productDetails.getMeasuringUnit());
		productDetailsResponse.setSubcategoryId(productDetails.getSubcategoryId());
		productDetailsResponse.setProductImage(productDetails.getProductImage());
		productDetailsResponse.setProductPerUnitActualPrice(productDetails.getProductPerUnitActualPrice());
		productDetailsResponse.setProductPerUnitCurrentPrice(productDetails.getProductPerUnitCurrentPrice());
		productDetailsResponse.setProductLength(productDetails.getLength());
		productDetailsResponse.setWidth(productDetails.getWidth());
		productDetailsResponse.setHeight(productDetails.getHeight());
		productDetailsResponse.setDimensionUnit(productDetails.getDimensionUnit());
		productDetailsResponse.setMaterials(productDetails.getMaterials());
		productDetailsResponse.setColours(productDetails.getColours());
		productResponce.setProductDetailsResponse(productDetailsResponse);
		return productResponce;
	}

}
