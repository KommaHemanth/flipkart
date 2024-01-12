package com.insignia.model;

public class ProductColourRequest {

	private Long customerSequenceNumber;
	private Integer expirationDuration;
	private Integer sequenceNumber;
	private String colourName;
	private boolean isColourNameUpdated;
	private String colourDescription;
	private boolean isColourDescriptionUpdated;

	public Long getCustomerSequenceNumber() {
		return customerSequenceNumber;
	}

	public void setCustomerSequenceNumber(Long customerSequenceNumber) {
		this.customerSequenceNumber = customerSequenceNumber;
	}

	public Integer getExpirationDuration() {
		return expirationDuration;
	}

	public void setExpirationDuration(Integer expirationDuration) {
		this.expirationDuration = expirationDuration;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getColourName() {
		return colourName;
	}

	public void setColourName(String colourName) {
		this.colourName = colourName;
		this.isColourNameUpdated = true;
	}

	public boolean isColourNameUpdated() {
		return isColourNameUpdated;
	}

	public String getColourDescription() {
		return colourDescription;
	}

	public void setColourDescription(String colourDescription) {
		this.colourDescription = colourDescription;
		this.isColourDescriptionUpdated = true;
	}

	public boolean isColourDescriptionUpdated() {
		return isColourDescriptionUpdated;
	}

}
