package com.insignia.validations;

import com.insignia.constants.CartValidaterConstant;
import com.insignia.customExceptions.InvalidInputParametersException;

public class CartValidater {

	public static void ValidateproductQuantity(String productQuantity, int length)
			throws InvalidInputParametersException {
		if (productQuantity == null || productQuantity == "" || productQuantity.isBlank()) {
			throw new InvalidInputParametersException(CartValidaterConstant.validProductQuantityErrorCode,
					CartValidaterConstant.validProductQuantity);

		} else if (productQuantity.length() > length) {
			throw new InvalidInputParametersException(CartValidaterConstant.validProductQuantityLengthErrorCode,
					CartValidaterConstant.validProductQuantityLength);

		}

	}
	
	public static void ValidateCartSequenceNumber(Long cartSequenceNumber, int length) throws InvalidInputParametersException {

		if (cartSequenceNumber == null) {
			throw new InvalidInputParametersException(CartValidaterConstant.validCartSequenceNumberErrorCode,
					CartValidaterConstant.validCartSequenceNumber);

		} else if (cartSequenceNumber != null && Long.toString(cartSequenceNumber).length() > length) {
			throw new InvalidInputParametersException(CartValidaterConstant.validCartSequenceNumberLengthErrorCode,
					CartValidaterConstant.validCartSequenceNumberLength);
		}

	}
	
	public static void ValidateProductSequenceNumber(Long productSequenceNumber, int length) throws InvalidInputParametersException {

		if (productSequenceNumber == null) {
			throw new InvalidInputParametersException(CartValidaterConstant.validProductSequenceNumberErrorCode,
					CartValidaterConstant.validProductSequenceNumber);

		} else if (productSequenceNumber != null && Long.toString(productSequenceNumber).length() > length) {
			throw new InvalidInputParametersException(CartValidaterConstant.validProductSequenceNumberLengthErrorCode,
					CartValidaterConstant.validProductSequenceNumberLength);
		}

	}
	

}
