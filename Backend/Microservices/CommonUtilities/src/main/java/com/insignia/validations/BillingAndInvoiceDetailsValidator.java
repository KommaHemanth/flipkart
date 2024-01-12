package com.insignia.validations;

import com.insignia.constants.BillingAndInvoiceDetailsConstant;
import com.insignia.customExceptions.InvalidInputParametersException;

public class BillingAndInvoiceDetailsValidator {
	
	public static void ValidateCustomerSequenceNumber(String customerSequenceNumber, int length) throws InvalidInputParametersException {

		if (customerSequenceNumber == null || customerSequenceNumber == "" || customerSequenceNumber.isBlank()) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validCustomerSequenceNumberErrorCode,
					BillingAndInvoiceDetailsConstant.validCustomerSequenceNumberErrorMessage);

		} else if (customerSequenceNumber.length() > length) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validCustomerSequenceNumberErrorCode,
					BillingAndInvoiceDetailsConstant.validCustomerSequenceNumberErrorMessage);

		}

	}

	public static void ValidateOrderId(String orderId, int length) throws InvalidInputParametersException {

		if (orderId == null || orderId == "" || orderId.isBlank()) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validOrderIdErrorCode,
					BillingAndInvoiceDetailsConstant.validOrderIdErrorMessage);

		} else if (orderId.length() > length) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validOrderIdLengthErrorCode,
					BillingAndInvoiceDetailsConstant.validOrderIdLengthErrorMessage);

		}

	}
	public static void ValidateInvoiceDate(String invoiceDate, int length) throws InvalidInputParametersException {

		if (invoiceDate == null || invoiceDate == "" || invoiceDate.isBlank()) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validInvoiceDateErrorCode,
					BillingAndInvoiceDetailsConstant.validInvoiceDateErrorMessage);

		} else if (invoiceDate.length() > length) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validInvoiceDateLengthErrorCode,
					BillingAndInvoiceDetailsConstant.validInvoiceDateLengthErrorMessage);

		} else if (!invoiceDate.matches(BillingAndInvoiceDetailsConstant.regularExpression)) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validInvoiceDateExpressionErrorCode,
					BillingAndInvoiceDetailsConstant.validInvoiceDateExpressionErrorMessage);
		}

	}
	
	public static void ValidateDueDate(String dueDate, int length) throws InvalidInputParametersException {

		if (dueDate == null || dueDate == "" || dueDate.isBlank()) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validDueDateErrorCode,
					BillingAndInvoiceDetailsConstant.validDueDateErrorMessage);

		} else if (dueDate.length() > length) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validDueDateLengthErrorCode,
					BillingAndInvoiceDetailsConstant.validDueDateLengthErrorMessage);

		} else if (!dueDate.matches(BillingAndInvoiceDetailsConstant.regularExpression)) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validDueDateExpressionErrorCode,
					BillingAndInvoiceDetailsConstant.validDueDateExpressionErrorMessage);
		}

	}
	
	public static void ValidateStatus(String status, int length, boolean flag) throws InvalidInputParametersException {

		if(flag)
		{
			if (status == null || status == "" || status.isBlank()) {
				throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validStatusErrorCode,
						BillingAndInvoiceDetailsConstant.validStatusErrorMessage);

			}
		}
		if (status.length() > length) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validStatusLengthErrorCode,
					BillingAndInvoiceDetailsConstant.validStatusLengthErrorMessage);

		}

	}
	
	public static void ValidateAmount(String amount) throws InvalidInputParametersException {

		if (amount == null || amount == "" || amount.isBlank()) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validAmountErrorCode,
					BillingAndInvoiceDetailsConstant.validAmountErrorMessage);

		} else if (!amount.matches(BillingAndInvoiceDetailsConstant.regularExpression1)) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validAmountLengthErrorCode,
					BillingAndInvoiceDetailsConstant.validAmountLengthErrorMessage);
		}

	}
	
	public static void ValidateDiscountApplied(String discountApplied) throws InvalidInputParametersException {

		if (!discountApplied.matches(BillingAndInvoiceDetailsConstant.regularExpression1)) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validDiscountAppliedLengthErrorCode,
					BillingAndInvoiceDetailsConstant.validDiscountAppliedLengthErrorMessage);
		}

	}
	
	public static void ValidateDateofPayment(String dateOfPayment, int length,boolean flag) throws InvalidInputParametersException {

		if(flag)
		{
			if (dateOfPayment == null || dateOfPayment == "" || dateOfPayment.isBlank()) {
				throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validDateOfPaymentErrorCode,
						BillingAndInvoiceDetailsConstant.validDateOfPaymentErrorMessage);

			}
		}
		if (dateOfPayment.length() > length) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validDateOfPaymentLengthErrorCode,
					BillingAndInvoiceDetailsConstant.validDateOfPaymentLengthErrorMessage);

		} else if (!dateOfPayment.matches(BillingAndInvoiceDetailsConstant.regularExpression)) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validDateOfPaymentExpressionErrorCode,
					BillingAndInvoiceDetailsConstant.validDateOfPaymentExpressionErrorMessage);
		}

	}
	
	public static void ValidateCurrency(String currency, int length,boolean flag) throws InvalidInputParametersException {

		if(flag)
		{
			if (currency == null || currency == "" || currency.isBlank()) {
				throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validCurrencyErrorCode,
						BillingAndInvoiceDetailsConstant.validCurrencyErrorMessage);

			}
		}
		if (currency.length() > length) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validCurrencyLengthErrorCode,
					BillingAndInvoiceDetailsConstant.validCurrencyLengthErrorMessage);

		}

	}
	public static void ValidateModeOfPayment(String modeOfPayment, int length,boolean flag) throws InvalidInputParametersException {

		if(flag)
		{
			if (modeOfPayment == null || modeOfPayment == "" || modeOfPayment.isBlank()) {
				throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validModeOfPaymentErrorCode,
						BillingAndInvoiceDetailsConstant.validModeOfPaymentErrorMessage);

			}
		}
		if (modeOfPayment.length() > length) {
			throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.validModeOfPaymentLengthErrorCode,
					BillingAndInvoiceDetailsConstant.validModeOfPaymentLengthErrorMessage);

		}

	}
	
	

}
