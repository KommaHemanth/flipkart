package com.insignia.validations;

import com.insignia.constants.MeasurementUnitConstant;
import com.insignia.customExceptions.InvalidInputParametersException;

public class MeasurementUnitsValidation {

	public static void ValidateUnitName(String unitName, int length) throws InvalidInputParametersException {
		if (unitName == null || unitName.trim().isEmpty()) {
			throw new InvalidInputParametersException(MeasurementUnitConstant.validateUnitNameErrorCode,
					MeasurementUnitConstant.validateUnitNameMessage);

		} else if (unitName.length() > length) {
			throw new InvalidInputParametersException(MeasurementUnitConstant.validateUnitNameLengthErrorCode,
					MeasurementUnitConstant.validateUnitNameLengthMessage);

		}

	}

	public static void ValidateUnitDescription(String unitDescription, int length)
			throws InvalidInputParametersException {
		if (unitDescription != null && unitDescription.length() > length) {
			throw new InvalidInputParametersException(MeasurementUnitConstant.validateUnitDescriptionLengthErrorCode,
					MeasurementUnitConstant.validateUnitDescriptionLengthMessage);

		}

	}
}
