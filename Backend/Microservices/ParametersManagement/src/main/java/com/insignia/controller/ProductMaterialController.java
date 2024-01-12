package com.insignia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insignia.constant.MeasurementConstants;
import com.insignia.constant.ProductMaterialConstants;
import com.insignia.constants.CommonConstant;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.ProductMaterialRequest;
import com.insignia.model.ProductMaterialResponse;
import com.insignia.serviceInterface.ProductMaterialServiceInterface;
import com.insignia.validations.ProductMaterialValidation;

@CrossOrigin
@RestController
@RequestMapping("/productMaterial")
public class ProductMaterialController {

	@Autowired
	private ProductMaterialServiceInterface productMaterialServiceInterface;

	@PostMapping("/saveProductMaterial")
	public ResponseEntity<?> saveProductMaterial(@RequestBody ProductMaterialRequest productMaterialRequest)
			throws InvalidInputParametersException {
		try {

			validationsForProductMaterial(productMaterialRequest);

			return ResponseEntity.ok(productMaterialServiceInterface.saveProductMaterial(productMaterialRequest));
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ProductMaterialResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ProductMaterialResponse(CommonConstant.validateTokenErrorCode, CommonConstant.validateToken));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductMaterialResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	@PutMapping("/updateProductMaterial")
	public ResponseEntity<?> updateProductMaterial(@RequestBody ProductMaterialRequest productMaterialRequest)
			throws InvalidInputParametersException {
		try {
			validationsForProductMaterial(productMaterialRequest);
			return ResponseEntity.ok(productMaterialServiceInterface.updateProductMaterial(productMaterialRequest));
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ProductMaterialResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ProductMaterialResponse(CommonConstant.validateTokenErrorCode, CommonConstant.validateToken));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductMaterialResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	@PostMapping("/deleteProductMaterial")
	public ResponseEntity<?> deleteProductMaterial(@RequestBody ProductMaterialRequest productMaterialRequest)
			throws InvalidInputParametersException {
		try {
			productMaterialServiceInterface.deleteProductMaterial(productMaterialRequest);
			return ResponseEntity.ok(MeasurementConstants.successMessageForDelete);
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ProductMaterialResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ProductMaterialResponse(CommonConstant.validateTokenErrorCode, CommonConstant.validateToken));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductMaterialResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}

	}

	@PostMapping("/getAllProductMaterials")
	public ResponseEntity<?> getAllProductMaterials(@RequestBody ProductMaterialRequest productMaterialRequest) {
		try {
			return ResponseEntity.ok(productMaterialServiceInterface.getAllProductMaterials(productMaterialRequest));
		} catch (TokenExpiredException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ProductMaterialResponse(CommonConstant.validateTokenErrorCode, CommonConstant.validateToken));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductMaterialResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	private void validationsForProductMaterial(ProductMaterialRequest productMaterialRequest)
			throws InvalidInputParametersException {
		ProductMaterialValidation.ValidateMaterialName(productMaterialRequest.getMaterialName(),
				ProductMaterialConstants.materialNameLength);
		ProductMaterialValidation.ValidateMaterialDescription(productMaterialRequest.getMaterialDescription(),
				ProductMaterialConstants.materialDescriptionLength);
	}
}