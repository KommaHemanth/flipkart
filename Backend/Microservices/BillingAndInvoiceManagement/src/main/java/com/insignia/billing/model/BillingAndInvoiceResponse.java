package com.insignia.billing.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillingAndInvoiceResponse {
	private Long customerSequenceNumber;
	private boolean success;
	private String errorCode;
	private String errorMessage;
	private List<BillingAndInvoiceDetailsModel> billingAndInvoiceDetailsModel;
	public Long getCustomerSequenceNumber() {
		return customerSequenceNumber;
	}
	public void setCustomerSequenceNumber(Long customerSequenceNumber) {
		this.customerSequenceNumber = customerSequenceNumber;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<BillingAndInvoiceDetailsModel> getBillingAndInvoiceDetailsModel() {
		return billingAndInvoiceDetailsModel;
	}
	public void setBillingAndInvoiceDetailsModel(List<BillingAndInvoiceDetailsModel> billingAndInvoiceDetailsModel) {
		this.billingAndInvoiceDetailsModel = billingAndInvoiceDetailsModel;
	}
	
	@Override
	public String toString() {
		return "BillingAndInvoiceResponse [customerSequenceNumber=" + customerSequenceNumber + ", success=" + success
				+ ", errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", billingAndInvoiceDetailsModel="
				+ billingAndInvoiceDetailsModel + "]";
	}
	
	public BillingAndInvoiceResponse()
	{
		
	}
	
	
	public BillingAndInvoiceResponse(boolean success, String errorCode, String errorMessage) {
		super();
		this.success = success;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public BillingAndInvoiceResponse(boolean success, String errorCode, String errorMessage,
			List<BillingAndInvoiceDetailsModel> billingAndInvoiceDetailsModel) {
		super();
		this.success = success;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.billingAndInvoiceDetailsModel = billingAndInvoiceDetailsModel;
	}
	
	
	

}
