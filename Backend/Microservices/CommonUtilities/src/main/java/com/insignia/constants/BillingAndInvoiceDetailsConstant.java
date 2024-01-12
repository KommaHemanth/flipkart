package com.insignia.constants;

public class BillingAndInvoiceDetailsConstant {
	
	public static final boolean statusTrue = true;
	public static final boolean statusFalse = false;
	
	public static final String Null = null;
	
	public static final String validCustomerSequenceNumberErrorCode = "700";
	public static final String validCustomerSequenceNumberErrorMessage = "Please enter the Customer Sequence Number.";
	
	public static final String validOrderIdErrorCode = "701";
	public static final String validOrderIdErrorMessage = "Please enter the Order Id.";
	
	
	public static final String validInvoiceDateErrorCode = "702";
	public static final String validInvoiceDateErrorMessage = "Please enter the Invoice Date.";
	
	public static final String validDueDateErrorCode = "703";
	public static final String validDueDateErrorMessage = "Please enter the Due Date.";
	
	
	public static final String validCurrencyErrorCode = "705";
	public static final String validCurrencyErrorMessage = "Please enter the Currency.";
	
	public static final String validModeOfPaymentErrorCode = "706";
	public static final String validModeOfPaymentErrorMessage = "Please enter the Mode of Payment.";
	
	public static final String validCustomerSequenceNumberLengthErrorCode = "707";
	public static final String validCustomerSequenceNumberLengthErrorMessage = "Please enter Customer Sequence Number length upto 30 characters only.";
	
	public static final String validOrderIdLengthErrorCode = "708";
	public static final String validOrderIdLengthErrorMessage = "Please enter Order Id length upto 30 characters only.";
	
	
	public static final String validInvoiceDateLengthErrorCode = "709";
	public static final String validInvoiceDateLengthErrorMessage = "Please enter the Invoice Date upto 10 characters only.";
	
	public static final String validDueDateLengthErrorCode = "710";
	public static final String validDueDateLengthErrorMessage = "Please enter the Due Date upto 10 characters only.";
	
	public static final String validStatusLengthErrorCode = "711";
	public static final String validStatusLengthErrorMessage = "Please enter the Status upto 16 characters only.";
	
	public static final String validAmountErrorCode = "712";
	public static final String validAmountErrorMessage = "Please enter the Amount.";
	
	public static final String validAmountLengthErrorCode = "713";
	public static final String validAmountLengthErrorMessage = "Please enter the Amount Integer part upto 10 characters and decimal part to 2 characters only.";
	
	
	public static final String validDateOfPaymentLengthErrorCode = "716";
	public static final String validDateOfPaymentLengthErrorMessage = "Please enter the Date of Payment upto 10 characters only.";
	
	public static final String validCurrencyLengthErrorCode = "717";
	public static final String validCurrencyLengthErrorMessage = "Please enter the Currency upto 15 characters only.";
	
	public static final String validModeOfPaymentLengthErrorCode = "718";
	public static final String validModeOfPaymentLengthErrorMessage = "Please enter the Mode of Payment upto 7 character only.";
	
	public static final String validInvoiceDateExpressionErrorCode = "719";
	public static final String validInvoiceDateExpressionErrorMessage = "Invoice date is not in the valid format (dd-mm-yyyy).";
	
	public static final String validDueDateExpressionErrorCode = "720";
	public static final String validDueDateExpressionErrorMessage = "Due date is not in the valid format (dd-mm-yyyy).";
	
	public static final String validDateOfPaymentExpressionErrorCode = "721";
	public static final String validDateOfPaymentExpressionErrorMessage = "Date of Payment is not in the valid format (dd-mm-yyyy).";
	
	public static final String validDiscountAppliedLengthErrorCode = "722";
	public static final String validDiscountAppliedLengthErrorMessage = "Please enter the Discount Applied Integer part upto 10 characters and decimal part to 2 characters only.";
	
	public static final String validStatusErrorCode = "723";
	public static final String validStatusErrorMessage = "Please enter the Status.";
	
	public static final String validDateOfPaymentErrorCode = "724";
	public static final String validDateOfPaymentErrorMessage = "Please enter the Date of Payment.";
	
	public static final String duplicateInvoiceErrorCode = "725";
	public static final String duplicateInvoiceErrorMessage = "An Invoice withe the same details already exists.";
	
	public static final String createInvoiceErrorCode = "726";
	public static final String createInvoiceErrorMessage = "Failed to add the Invoice.";
	
	public static final String updateInvoiceErrorCode = "727";
	public static final String updateInvoiceErrorMessage = "Failed to update the Invoice.";
	
	public static final String listInvoiceErrorCode = "728";
	public static final String listInvoiceErrorMessage = "Failed to list the Invoice.";
	
	public static final String noUserFoundErrorCode = "729";
	public static final String noUserFoundErrorMessage = "No Account is found with the given details to add the invoice.";
	
	public static final String noDataFoundErrorCode = "730";
	public static final String noDataFoundErrorMessage = "There is no invoice in the database associated with the given user data.";
	
	public static final String regularExpression = "^(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[0-2])[-](\\d{4})$";
	public static final String regularExpression1 = "^(?:\\d{1,10}|\\d{0,8}\\.\\d{1,2}|\\d{0,9}\\.\\d{1})$";
	
	

}
