package com.insignia.validations;

import com.insignia.constants.AddressDetailsConstants;
import com.insignia.customExceptions.InvalidInputParametersException;

public class AddressDetailsValidation {

	
	public static void ValidateAddressLine1(String addressLine1, int length) throws InvalidInputParametersException {
		if (addressLine1 == null || addressLine1 == "" || addressLine1.isBlank()) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validAddressLine1ErrorCode,
					AddressDetailsConstants.validAddressLine1);

		} else if (addressLine1.length() > length) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validAddressLine1LengthErrorCode,
					AddressDetailsConstants.validAddressLine1Length);
		}

	}

	public static void ValidateAddressLine2(String addressLine2, int length) throws InvalidInputParametersException {
		if (addressLine2 != null && addressLine2.length() > length) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validAddressLine2LengthErrorCode,
					AddressDetailsConstants.validAddressLine2Length);

		}

	}

	public static void ValidateCity(String city, int length) throws InvalidInputParametersException {
		if (city == null || city == "" || city.isBlank()) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validCityErrorCode,
					AddressDetailsConstants.validCity);

		} else if (city.length() > length) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validCityLengthErrorCode,
					AddressDetailsConstants.validCityLength);

		}
	}

	public static void ValidateState(String state, int length) throws InvalidInputParametersException {
		if (state == null || state == "" || state.isBlank()) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validStateErrorCode,
					AddressDetailsConstants.validState);

		} else if (state.length() > length) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validStateLengthErrorCode,
					AddressDetailsConstants.validStateLength);

		}
	}

	public static void ValidateCountry(String country, int length) throws InvalidInputParametersException {
		if (country == null || country == "" || country.isBlank()) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validCountryErrorCode,
					AddressDetailsConstants.validCountry);

		} else if (country.length() > length) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validCountryLengthErrorCode,
					AddressDetailsConstants.validCountryLength);

		}
	}
	

	public static void ValidateZipCode(String zipCode, int length) throws InvalidInputParametersException {
		if (zipCode == null || zipCode == "" || zipCode.isBlank()) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validZipCodeErrorCode,
					AddressDetailsConstants.validZipCode);

		} else if (zipCode.length() > length) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validZipCodeLengthErrorCode,
					AddressDetailsConstants.validZipCodeLength);

		} else if (!zipCode.matches(AddressDetailsConstants.regularExpressionZipcode)) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validZipCodeInvalidCharactersErrorCode,
					AddressDetailsConstants.validZipCodeExpression);
		}
	}

	public static void ValidateLandmark(String landmark, int length) throws InvalidInputParametersException {
		if (landmark != null && landmark.length() > length) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validLandMarkLengthErrorCode,
					AddressDetailsConstants.validLandMarkLength);
		}
	}

	public static void ValidateMobileNumber(String mobileNumber, int length) throws InvalidInputParametersException {
		if (mobileNumber != null && mobileNumber.length() > length) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validMobileNumberLengthErrorCode,
					AddressDetailsConstants.validMobileNumberLength);
		} else if (mobileNumber != null && !mobileNumber.matches(AddressDetailsConstants.regularNumbericExpression)) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validMobileNumberCharactersErrorCode,
					AddressDetailsConstants.validMobileNumberCharacters);
		}
	}

	public static void ValidateLandLineNumber(String landlineNumber, int length)
			throws InvalidInputParametersException {
		if (landlineNumber != null && landlineNumber.length() > length) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validLandLineNumberLengthErrorCode,
					AddressDetailsConstants.validLandLineNumberLength);
		} else if (landlineNumber != null && !landlineNumber.matches(AddressDetailsConstants.regularNumbericExpression)) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validLandLineNumberCharactersErrorCode,
					AddressDetailsConstants.validLandLineNumberCharacters);
		}
	}

	public static void ValidateEmailId(String emailId, int length) throws InvalidInputParametersException {
		if (emailId != null && emailId.length() > length) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validEmailIdErrorCode,
					AddressDetailsConstants.validEmailIdLength);
		} else if (emailId != null && !emailId.matches(AddressDetailsConstants.regularExpressionZipcode)) {
			throw new InvalidInputParametersException(AddressDetailsConstants.validEmailIdInvalidCharactersErrorCode,
					AddressDetailsConstants.validEmailIdExpression);
		}
	}

}
