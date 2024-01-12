package com.insignia.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WishlistDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long wishlistId;
	private Long productSequenceNumber;
	private Date timestamp;
	private Integer quantity;

	private String productId;
	private String productName;
	private String description;
	private String measuringQuantity;
	private String measuringUnit;
	private String productImage;
	private Float productPerUnitActualPrice;
	private Float productPerUnitCurrentPrice;

	public Long getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(Long wishlistId) {
		this.wishlistId = wishlistId;
	}

	public Long getProductSequenceNumber() {
		return productSequenceNumber;
	}

	public void setProductSequenceNumber(Long productSequenceNumber) {
		this.productSequenceNumber = productSequenceNumber;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeasuringQuantity() {
		return measuringQuantity;
	}

	public void setMeasuringQuantity(String measuringQuantity) {
		this.measuringQuantity = measuringQuantity;
	}

	public String getMeasuringUnit() {
		return measuringUnit;
	}

	public void setMeasuringUnit(String measuringUnit) {
		this.measuringUnit = measuringUnit;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Float getProductPerUnitActualPrice() {
		return productPerUnitActualPrice;
	}

	public void setProductPerUnitActualPrice(Float productPerUnitActualPrice) {
		this.productPerUnitActualPrice = productPerUnitActualPrice;
	}

	public Float getProductPerUnitCurrentPrice() {
		return productPerUnitCurrentPrice;
	}

	public void setProductPerUnitCurrentPrice(Float productPerUnitCurrentPrice) {
		this.productPerUnitCurrentPrice = productPerUnitCurrentPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "WishlistDetails [wishlistId=" + wishlistId + ", productSequenceNumber=" + productSequenceNumber
				+ ", timestamp=" + timestamp + ", quantity=" + quantity + ", productId=" + productId + ", productName="
				+ productName + ", description=" + description + ", measuringQuantity=" + measuringQuantity
				+ ", measuringUnit=" + measuringUnit + ", productImage=" + productImage + ", productPerUnitActualPrice="
				+ productPerUnitActualPrice + ", productPerUnitCurrentPrice=" + productPerUnitCurrentPrice + "]";
	}

	
}
