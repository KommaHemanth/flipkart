package com.insignia.validations;

import com.insignia.constants.ProductColourConstant;
import com.insignia.customExceptions.InvalidInputParametersException;

public class ProductColourValidation {

	public static void ValidateColourName(String colourName, int length) throws InvalidInputParametersException {
		if (colourName == null || colourName.trim().isEmpty()) {
			throw new InvalidInputParametersException(ProductColourConstant.validateColourNameErrorCode,
					ProductColourConstant.validateColourNameMessage);

		} else if (colourName.length() > length) {
			throw new InvalidInputParametersException(ProductColourConstant.validateColourNameLengthErrorCode,
					ProductColourConstant.validateColourNameLengthMessage);

		}

	}

	public static void ValidateColourDescription(String colourDescription, int length)
			throws InvalidInputParametersException {
		if (colourDescription != null && colourDescription.length() > length) {
			throw new InvalidInputParametersException(ProductColourConstant.validateColourDescriptionLengthErrorCode,
					ProductColourConstant.validateColourDescriptionLengthMessage);

		}

	}
}
