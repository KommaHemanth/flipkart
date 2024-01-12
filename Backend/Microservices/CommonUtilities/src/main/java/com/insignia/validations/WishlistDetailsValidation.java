package com.insignia.validations;

import com.insignia.constants.WishlistDetailsConstants;
import com.insignia.customExceptions.InvalidInputParametersException;

public class WishlistDetailsValidation {
	
	public static void ValidateCustomerSequenceNumber(Long customerSequenceNumber, int length) throws InvalidInputParametersException {

		if (customerSequenceNumber == null) {
			throw new InvalidInputParametersException(WishlistDetailsConstants.validCustomerSequenceNumberErrorCode,
					WishlistDetailsConstants.validCustomerSequenceNumber);
		}else if (customerSequenceNumber != null && String.valueOf(customerSequenceNumber).length() < length ) {
			throw new InvalidInputParametersException(WishlistDetailsConstants.validCustomerSequenceNumberLengthErrorCode,
					WishlistDetailsConstants.validCustomerSequenceNumberLength);
		}
	}
	
	public static void ValidateProductSequenceNumber(Long productSequenceNumber, int length) throws InvalidInputParametersException {

		if (productSequenceNumber == null) {
			throw new InvalidInputParametersException(WishlistDetailsConstants.validProductSequenceNumberErrorCode,
					WishlistDetailsConstants.validProductSequenceNumber);
		}else if (productSequenceNumber != null && String.valueOf(productSequenceNumber).length() < length ) {
			throw new InvalidInputParametersException(WishlistDetailsConstants.validProductSequenceNumberLengthErrorCode,
					WishlistDetailsConstants.validProductSequenceNumberLength);
		}
	}

	
	public static void ValidateQuantity(Integer quantity, int length) throws InvalidInputParametersException {

		if (quantity != null && String.valueOf(quantity).length() > length ) {
			throw new InvalidInputParametersException(WishlistDetailsConstants.validQuantityLengthErrorCode,
					WishlistDetailsConstants.validQuantityLength);
		}
	}
}
