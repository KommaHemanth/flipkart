package com.insignia.billing.model;

import java.math.BigDecimal;

public class DiscountModel {
	private Long sequenceNumber;
	private Long orderId;
	private Long customerSequenceNumber;
	private Long productId;
	private BigDecimal discountApplied;
	private BigDecimal amountToPay;
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getCustomerSequenceNumber() {
		return customerSequenceNumber;
	}
	public void setCustomerSequenceNumber(Long customerSequenceNumber) {
		this.customerSequenceNumber = customerSequenceNumber;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public BigDecimal getDiscountApplied() {
		return discountApplied;
	}
	public void setDiscountApplied(BigDecimal discountApplied) {
		this.discountApplied = discountApplied;
	}
	public BigDecimal getAmountToPay() {
		return amountToPay;
	}
	public void setAmountToPay(BigDecimal amountToPay) {
		this.amountToPay = amountToPay;
	}
	
	public DiscountModel()
	{
		
	}
	public DiscountModel(Long productId, BigDecimal discountApplied, BigDecimal amountToPay) {
		super();
		this.productId = productId;
		this.discountApplied = discountApplied;
		this.amountToPay = amountToPay;
	}
	public DiscountModel(Long sequenceNumber, Long orderId, Long customerSequenceNumber, Long productId,
			BigDecimal discountApplied, BigDecimal amountToPay) {
		super();
		this.sequenceNumber = sequenceNumber;
		this.orderId = orderId;
		this.customerSequenceNumber = customerSequenceNumber;
		this.productId = productId;
		this.discountApplied = discountApplied;
		this.amountToPay = amountToPay;
	}
	
	
	
}
