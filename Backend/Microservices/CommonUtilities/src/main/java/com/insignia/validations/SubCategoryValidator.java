package com.insignia.validations;

import com.insignia.constants.SubCategoryConstant;
import com.insignia.customExceptions.InvalidInputParametersException;

public class SubCategoryValidator {

    //TODO: As per requirement need to address
    public static void validateSubCategoryName(String value, int length) throws InvalidInputParametersException {
        if (value == null || value == "" || value.trim().isBlank()) {
            throw new InvalidInputParametersException(SubCategoryConstant.validSubCategoryNameLengthCode,
                    SubCategoryConstant.validSubCategoryNameLength);

        } else if (value.length() > length) {
            throw new InvalidInputParametersException(SubCategoryConstant.validSubCategoryNameLengthCode,
                    SubCategoryConstant.validSubCategoryNameLength);

        }
    }

    public static void validateSubCategoryDescription(String value, int length) throws InvalidInputParametersException {
        if (value == null || value == "" || value.trim().isBlank()) {
            throw new InvalidInputParametersException(SubCategoryConstant.validSubCategoryDescriptionLengthCode,
                    SubCategoryConstant.validSubCategoryDescriptionLength);

        } else if (value.length() > length) {
            throw new InvalidInputParametersException(SubCategoryConstant.validSubCategoryDescriptionLengthCode,
                    SubCategoryConstant.validSubCategoryDescriptionLength);

        }
    }

    public static void validateSubCategoryImagePath(String value, int length) throws InvalidInputParametersException {
        if (value == null || value == "" || value.trim().isBlank()) {
            throw new InvalidInputParametersException(SubCategoryConstant.validSubCategoryImagePathLengthCode,
                    SubCategoryConstant.validSubCategoryImagePathLength);

        } else if (value.length() > length) {
            throw new InvalidInputParametersException(SubCategoryConstant.validSubCategoryImagePathLengthCode,
                    SubCategoryConstant.validSubCategoryImagePathLength);

        }
    }
}
