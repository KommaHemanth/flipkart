package com.insignia.billing.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.insignia.entity.BillingAndInvoiceDetails;
import com.insignia.entity.Discount;

public class BillingAndInvoiceDetailsModel {
	private Long invoiceNumber;
	private Long customerSequenceNumber;
	private Long orderId;
	private Date invoiceDate;
	private Date dueDate;
	private String status;
	private Date dateOfPayment;
	private String currency;
	private String modeOfPayment;
	private List<DiscountModel> discount;

	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Long getCustomerSequenceNumber() {
		return customerSequenceNumber;
	}

	public void setCustomerSequenceNumber(Long customerSequenceNumber) {
		this.customerSequenceNumber = customerSequenceNumber;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Date getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(Date dateOfPayment) {
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
	public List<DiscountModel> getDiscount() {
		return discount;
	}

	public void setDiscount(List<DiscountModel> discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "BillingAndInvoiceDetails [invoiceNumber=" + invoiceNumber + ", customerSequenceNumber="
				+ customerSequenceNumber + ", orderId=" + orderId + ", invoiceDate=" + invoiceDate + ", dueDate="
				+ dueDate + ", status=" + status + ", amount=" + ", dateOfPayment=" + dateOfPayment + ", currency=" + currency + ", modeOfPayment=" + modeOfPayment
				+ ", customerBasicDetails=" + "]";
	}

	public BillingAndInvoiceDetailsModel(Long invoiceNumber, Long customerSequenceNumber, Long orderId, Date invoiceDate,
			Date dueDate, String status, Date dateOfPayment,
			String currency, String modeOfPayment) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.customerSequenceNumber = customerSequenceNumber;
		this.orderId = orderId;
		this.invoiceDate = invoiceDate;
		this.dueDate = dueDate;
		this.status = status;
		this.dateOfPayment = dateOfPayment;
		this.currency = currency;
		this.modeOfPayment = modeOfPayment;
	}
	
	public static DiscountModel convertToDiscountModel(Discount entity)
	{
		if(entity == null) {
	        return null;
	    }
	    return new DiscountModel(
	        entity.getSequenceNumber(),
	        entity.getOrderId(),
	        entity.getCustomerSequenceNumber(),
	        entity.getProductId(),
	        entity.getDiscountApplied(),
	        entity.getAmoubtToPay());
	}
	
	public static BillingAndInvoiceDetailsModel convertToModel(BillingAndInvoiceDetails entity, List<Discount> discountsForThisEntity) {
	    if(entity == null) {
	        return null;
	    }
	    BillingAndInvoiceDetailsModel model = new BillingAndInvoiceDetailsModel(
	        entity.getInvoiceNumber(),
	        entity.getCustomerSequenceNumber(),
	        entity.getOrderId(),
	        entity.getInvoiceDate(),
	        entity.getDueDate(),
	        entity.getStatus(),
	        entity.getDateOfPayment(),
	        entity.getCurrency(),
	        entity.getModeOfPayment()
	    );
	    model.setDiscount(discountsForThisEntity.stream().map(BillingAndInvoiceDetailsModel::convertToDiscountModel).collect(Collectors.toList()));
	    return model;
	}

	public static List<BillingAndInvoiceDetailsModel> convertToModelList(List<BillingAndInvoiceDetails> entities, List<Discount> allDiscounts) {
		return entities.stream().map(entity -> {
	        List<Discount> discountsForThisEntity = allDiscounts.stream()
	            .filter(discount -> 
	                discount.getCustomerSequenceNumber().equals(entity.getCustomerSequenceNumber()) && 
	                discount.getOrderId().equals(entity.getOrderId())
	            )
	            .collect(Collectors.toList());
	        return convertToModel(entity, discountsForThisEntity);
	    }).collect(Collectors.toList());
		
	}

}
