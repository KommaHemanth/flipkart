package com.insignia.validations;

import com.insignia.constants.CategoryConstant;
import com.insignia.customExceptions.InvalidInputParametersException;

public class CategoryValidator {

    //TODO: As per requirement need to address
    public static void validateCategoryName(String value, int length) throws InvalidInputParametersException {
        if (value == null || value == "" || value.trim().isBlank()) {
            throw new InvalidInputParametersException(CategoryConstant.validCategoryNameLengthCode,
                    CategoryConstant.validCategoryNameLength);

        } else if (value.length() > length) {
            throw new InvalidInputParametersException(CategoryConstant.validCategoryNameLengthCode,
                    CategoryConstant.validCategoryNameLength);

        }
    }

    public static void validateCategoryDescription(String value, int length) throws InvalidInputParametersException {
        if (value == null || value == "" || value.trim().isBlank()) {
            throw new InvalidInputParametersException(CategoryConstant.validCategoryDescriptionLengthCode,
                    CategoryConstant.validCategoryDescriptionLength);

        } else if (value.length() > length) {
            throw new InvalidInputParametersException(CategoryConstant.validCategoryDescriptionLengthCode,
                    CategoryConstant.validCategoryDescriptionLength);

        }
    }

    public static void validateCategoryImagePath(String value, int length) throws InvalidInputParametersException {
        if (value == null || value == "" || value.trim().isBlank()) {
            throw new InvalidInputParametersException(CategoryConstant.validCategoryImagePathLengthCode,
                    CategoryConstant.validCategoryImagePathLength);

        } else if (value.length() > length) {
            throw new InvalidInputParametersException(CategoryConstant.validCategoryImagePathLengthCode,
                    CategoryConstant.validCategoryImagePathLength);

        }
    }
}
