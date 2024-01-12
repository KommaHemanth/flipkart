package com.insignia.validations;

import com.insignia.constants.ProductMaterialConstant;
import com.insignia.customExceptions.InvalidInputParametersException;

public class ProductMaterialValidation {

	public static void ValidateMaterialName(String materialName, int length) throws InvalidInputParametersException {
		if (materialName == null || materialName.trim().isEmpty()) {
			throw new InvalidInputParametersException(ProductMaterialConstant.validateMaterialNameErrorCode,
					ProductMaterialConstant.validateMaterialNameMessage);

		} else if (materialName.length() > length) {
			throw new InvalidInputParametersException(ProductMaterialConstant.validateMaterialNameLengthErrorCode,
					ProductMaterialConstant.validateMaterialNameLengthMessage);

		}

	}

	public static void ValidateMaterialDescription(String materialDescription, int length)
			throws InvalidInputParametersException {
		if (materialDescription != null && materialDescription.length() > length) {
			throw new InvalidInputParametersException(
					ProductMaterialConstant.validateMaterialDescriptionLengthErrorCode,
					ProductMaterialConstant.validateMaterialDescriptionLengthMessage);

		}

	}
}
