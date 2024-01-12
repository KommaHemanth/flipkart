package com.insignia.billing.model;

import java.util.List;

public class BillingAndInvoiceRequest {
	
	private String invoiceNumber;
	private String customerSequenceNumber;
	private String orderId;
	private String invoiceDate;
	private String dueDate;
	private String status;
	private List<DiscountModel> discounts;
	private String dateOfPayment;
	private String currency;
	private String modeOfPayment;
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getCustomerSequenceNumber() {
		return customerSequenceNumber;
	}
	public void setCustomerSequenceNumber(String customerSequenceNumber) {
		this.customerSequenceNumber = customerSequenceNumber;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<DiscountModel> getDiscounts() {
		return discounts;
	}
	public void setDiscounts(List<DiscountModel> discounts) {
		this.discounts = discounts;
	}
	public String getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(String dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	
	public BillingAndInvoiceRequest()
	{
		
	}
	public BillingAndInvoiceRequest(String customerSequenceNumber, String orderId,
			String invoiceDate, String dueDate, String status,List<DiscountModel> discount,
			String dateOfPayment, String currency, String modeOfPayment) {
		this.customerSequenceNumber = customerSequenceNumber;
		this.orderId = orderId;
		this.invoiceDate = invoiceDate;
		this.dueDate = dueDate;
		this.status = status;
		this.discounts=discount;
		this.dateOfPayment = dateOfPayment;
		this.currency = currency;
		this.modeOfPayment = modeOfPayment;
	}
	
	public BillingAndInvoiceRequest(String customerSequenceNumber,String orderId,String status,String dateOfPayment,String currency,String modeOfPayment)
	{
		this.customerSequenceNumber=customerSequenceNumber;
		this.orderId=orderId;
		this.status=status;
		this.dateOfPayment=dateOfPayment;
		this.currency=currency;
		this.modeOfPayment=modeOfPayment;
	}
	
	
	
	
}
