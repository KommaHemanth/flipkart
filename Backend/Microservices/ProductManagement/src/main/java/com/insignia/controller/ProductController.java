package com.insignia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insignia.constant.ProductConstants;
import com.insignia.constants.CommonConstant;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.ProductManagementRequest;
import com.insignia.model.ProductRequest;
import com.insignia.model.ProductResponse;
import com.insignia.service.ProductServiceInterface;
import com.insignia.validations.ProductValidator;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductServiceInterface productService;

	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody ProductManagementRequest productManagementRequest) {
		try {
			ProductRequest productRequest = productManagementRequest.getProductRequest();
			ProductValidator.ValidateProductId(productRequest.getProductId(), ProductConstants.productIdLength);
			validationForProductRequest(productRequest);

			return ResponseEntity.ok(productService.addProduct(productManagementRequest));
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ProductResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ProductResponse(CommonConstant.validateTokenErrorCode, CommonConstant.validateToken));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	@PutMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@RequestBody ProductManagementRequest productManagementRequest)
			throws InvalidInputParametersException {
		try {
			ProductRequest productRequest = productManagementRequest.getProductRequest();
			validationForProductRequest(productRequest);
			return ResponseEntity.ok(productService.updateProduct(productManagementRequest));
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ProductResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ProductResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	@DeleteMapping("/deleteByproductId/{productSequenceNumber}/{customerSequenceNumber}/{expirationDuration}")
	public ResponseEntity<?> deleteByproductId(@PathVariable Long productSequenceNumber,
			@PathVariable Long customerSequenceNumber, @PathVariable Integer expirationDuration) {
		try {
			productService.deleteProductById(productSequenceNumber, customerSequenceNumber, expirationDuration);
			return ResponseEntity.ok(ProductConstants.successMessage);
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ProductResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ProductResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}

	}

	@GetMapping("/getproductById/{productSequenceNumber}/{customerSequenceNumber}/{expirationDuration}")
	public ResponseEntity<?> getproductById(@PathVariable Long productSequenceNumber,
			@PathVariable Long customerSequenceNumber, @PathVariable Integer expirationDuration) {
		try {
			return ResponseEntity.ok(
					productService.getProductById(productSequenceNumber, customerSequenceNumber, expirationDuration));
		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ProductResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}

	}

	@GetMapping("/getAllProducts/{customerSequenceNumber}/{expirationDuration}")
	public ResponseEntity<?> getAllProducts(@PathVariable Long customerSequenceNumber,
			@PathVariable Integer expirationDuration) {
		try {
			return ResponseEntity.ok(productService.getAllProducts(customerSequenceNumber, expirationDuration));
		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ProductResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}

	}

	@PostMapping("/filterCriteria")
	public ResponseEntity<?> filterCriteria(@RequestBody ProductManagementRequest productManagementRequest) {

		try {
			return ResponseEntity.ok(productService.filterProduct(productManagementRequest));

		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ProductResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}

	}

	private void validationForProductRequest(ProductRequest productRequest) throws InvalidInputParametersException {

		ProductValidator.ValidateProductName(productRequest.getProductName(), ProductConstants.productNameLength);
		ProductValidator.ValidateMeasuringQuantity(productRequest.getMeasuringUnit(),
				ProductConstants.productMeasuringQuantity);
		ProductValidator.ValidateMeasuringUnit(productRequest.getMeasuringQuantity(),
				ProductConstants.productMeasuringUnit);
		ProductValidator.ValidateDescription(productRequest.getDescription(), ProductConstants.descriptionLength);
		ProductValidator.validateProductPerUnitActualPrice(productRequest.getProductPerUnitActualPrice(),
				ProductConstants.productPerUnitActualPrice);
		ProductValidator.validateProductPerUnitCurrentPrice(productRequest.getProductPerUnitCurrentPrice(),
				ProductConstants.productPerUnitCurrentPrice);
		ProductValidator.validateProductLength(productRequest.getProductLength(), ProductConstants.productLength);
		ProductValidator.validateHeight(productRequest.getHeight(), ProductConstants.heightLenght);
		ProductValidator.validateWidth(productRequest.getWidth(), ProductConstants.widthLength);
		ProductValidator.validateDimensionUnit(productRequest.getDimensionUnit(), ProductConstants.dimensionUnitLength);
		ProductValidator.validateMaterials(productRequest.getMaterials(), ProductConstants.materialsLength);
		ProductValidator.validateColours(productRequest.getColours(), ProductConstants.coloursLength);
	}

}
