package com.insignia.validations;

import com.insignia.constants.ProductValidatorConstants;
import com.insignia.customExceptions.InvalidInputParametersException;

public class ProductValidator {

	public static void ValidateProductId(String productId, int length) throws InvalidInputParametersException {
		if (productId == null || productId.trim().isEmpty()) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validProductIdErrorCode,
					ProductValidatorConstants.validProductIdMessage);

		} else if (productId.length() > length) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validProductIdLengthErrorCode,
					ProductValidatorConstants.validProductIdLengthMessage);

		}

	}

	public static void ValidateProductName(String productName, int length) throws InvalidInputParametersException {
		if (productName == null || productName.trim().isEmpty()) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validProductNameErrorCode,
					ProductValidatorConstants.validProductNameMessage);

		} else if (productName.length() > length) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validProductNameLengthErrorCode,
					ProductValidatorConstants.validProductNameLengthMessage);

		}

	}

	public static void ValidateMeasuringQuantity(String measuringQuantity, int length)
			throws InvalidInputParametersException {
		if (measuringQuantity != null && measuringQuantity.length() > length) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validMeasuringQuantityLengthErrorCode,
					ProductValidatorConstants.validMeasuringQuantityLength);

		}
	}

	public static void ValidateMeasuringUnit(String measuringUnit, int length) throws InvalidInputParametersException {
		if (measuringUnit != null && measuringUnit.length() > length) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validMeasuringQuantityLengthErrorCode,
					ProductValidatorConstants.validMeasuringQuantityLength);

		}
	}

	public static void ValidateDescription(String description, int length) throws InvalidInputParametersException {
		if (description != null && description.length() > length) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validDescriptionLengthErrorCode,
					ProductValidatorConstants.validDescriptionLength);

		}

	}

	public static void validateProductPerUnitActualPrice(Float productPerUnitActualPrice, int length)
			throws InvalidInputParametersException {

		if (productPerUnitActualPrice != null && String.valueOf(productPerUnitActualPrice).length() > length) {
			throw new InvalidInputParametersException(
					ProductValidatorConstants.validateProductPerUnitActualPriceLengthErrorCode,
					ProductValidatorConstants.validateProductPerUnitActualPriceLengthMessage);
		}
	}

	public static void validateProductPerUnitCurrentPrice(Float productPerUnitCurrentPrice, int length)
			throws InvalidInputParametersException {

		if (productPerUnitCurrentPrice != null && String.valueOf(productPerUnitCurrentPrice).length() > length) {
			throw new InvalidInputParametersException(
					ProductValidatorConstants.validateProductPerUnitCurrentPriceLengthErrorCode,
					ProductValidatorConstants.validateProductPerUnitCurrentPriceLengthMessage);
		}
	}

	public static void validateProductLength(Float productLength, int length) throws InvalidInputParametersException {

		if (productLength != null && String.valueOf(productLength).length() > length) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validateProductLengthErrorCode,
					ProductValidatorConstants.validateProductLengthMessage);
		}
	}

	public static void validateHeight(Float height, int length) throws InvalidInputParametersException {

		if (height != null && String.valueOf(height).length() > length) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validateHeightLengthErrorCode,
					ProductValidatorConstants.validateHeightLengthMessage);
		}
	}

	public static void validateWidth(Float width, int length) throws InvalidInputParametersException {

		if (width != null && String.valueOf(width).length() > length) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validateWidthLengthErrorCode,
					ProductValidatorConstants.validateWidthLengthMessage);
		}
	}

	public static void validateDimensionUnit(String dimensionUnit, int length) throws InvalidInputParametersException {

		if (dimensionUnit != null && dimensionUnit.length() > length) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validateDimensionUnitLengthErrorCode,
					ProductValidatorConstants.validateDimensionUnitLengthMessage);
		}
	}
	
	public static void validateMaterials(String materials, int length) throws InvalidInputParametersException {

		if (materials != null && materials.length() > length) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validateMaterialsLengthErrorCode,
					ProductValidatorConstants.validateMaterialsLengthMessage);
		}
	}

	public static void validateColours(String colours, int length) throws InvalidInputParametersException {

		if (colours != null && colours.length() > length) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validateColoursLengthErrorCode,
					ProductValidatorConstants.validateColoursLengthMessage);
		}
	}

}
