package com.insignia.validations;

import com.insignia.constants.OrderValidatorConstant;
import com.insignia.customExceptions.InvalidInputParametersException;

public class OrderValidater {

	public static void ValidateOrderId(Long OrderId, int length) throws InvalidInputParametersException {
		if (OrderId == null) {
			throw new InvalidInputParametersException(OrderValidatorConstant.validOrderIdErrorCode,
					OrderValidatorConstant.validOrderId);

		} else if (OrderId > length) {
			throw new InvalidInputParametersException(OrderValidatorConstant.validOrderIdLengthErrorCode,
					OrderValidatorConstant.validOrderIdLength);
		}
	}

	public static void ValidateOrderStatus(String OrderStatus, int length) throws InvalidInputParametersException {
		if (OrderStatus == null || OrderStatus == "" || OrderStatus.isBlank()) {
			throw new InvalidInputParametersException(OrderValidatorConstant.validOrder_statusErrorCode,
					OrderValidatorConstant.validOrder_status);

		} else if (OrderStatus.length() > length) {
			throw new InvalidInputParametersException(OrderValidatorConstant.validOrderLengthErrorCode,
					OrderValidatorConstant.validOrder_statusLength);

		}

	}

	public static void ValidInvoiceId(String InvoiceId, int length) throws InvalidInputParametersException {
		if (InvoiceId == null || InvoiceId == "" || InvoiceId.isBlank()) {
			throw new InvalidInputParametersException(OrderValidatorConstant.validInvoiceIdErrorCode,
					OrderValidatorConstant.validInvoiceId);

		} else if (InvoiceId.length() > length) {
			throw new InvalidInputParametersException(OrderValidatorConstant.validInvoiceIdLengthErrorCode,
					OrderValidatorConstant.validInvoiceIdLength);
		}
	}

}
