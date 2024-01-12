package com.insignia.constants;

public class OrderValidatorConstant {

	public static final String validateOrderMessage = "Details already existing in the system";
	public static final String validateOrderErrorCode = "220";

	public static final String validOrderId = "please enter valid orderId";
	public static final String validOrderIdErrorCode = "210";
	public static final String validOrderIdLength = "please enter orderId valid length";
	public static final String validOrderIdLengthErrorCode = "211";

	public static final String validOrder_status = "Please enter orderStatus";
	public static final String validOrder_statusErrorCode = "212";
	public static final String validOrder_statusLength = "Please enter status length upto 15 characters only";
	public static final String validOrderLengthErrorCode = "213";

	public static final String validInvoiceId = "Please enter InvoiceId";
	public static final String validInvoiceIdErrorCode = "214";
	public static final String validInvoiceIdLength = "Please enter valid InvoiceId length  only";
	public static final String validInvoiceIdLengthErrorCode = "215";

	public static final String regularExpression = "^[A-Za-z0-9_\\-.@]+$";

	public static final String orderDetailsIsnotFoundMessage = "Order details is  not found on the system.";
	public static final String orderDetailsIsnotFoundMessageErrorCode = "226";
	
	
	public static final String validateOrderDetailsMessage = "Details already existing in the system";
	public static final String validateOrderDetailsErrorCode = "227";

	
	public static final String validateUpdateMethodMessage = "only order status update is allowed.";
	public static final String validateUpdateMethodErrorCode = "228";
	
	public static final String validateOrderStatusEnumMessage= "Order status is invalid";
	public static final String validateOrderStatusEnumErrorCode = "229";
	
	public static final String validateOrderStatusDeleteMessage= "order can  be deleted only when the status is CA";
	public static final String validateOrderStatusDeleteErrorCode = "230";
	
	
}
