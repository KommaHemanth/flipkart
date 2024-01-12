package com.insignia.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SubscriptionResponse {
	
	private String errorCode;
	private String errorMessage;
	
	private Long sequenceNumber;
	private String planId;
	private String planName;
	private String planDescription;
	private Integer planDuration;
	private Float planPricing;
	private String planActivationStatus;
	private Date planActivationDate;
	private Date planDeactivationDate;
	private Float percentageDiscount;
	private Date planActiveTill;
	
	private List<Long> associatedProductSequenceNumberList = new ArrayList<Long>();

	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanDescription() {
		return planDescription;
	}

	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}

	public Integer getPlanDuration() {
		return planDuration;
	}

	public void setPlanDuration(Integer planDuration) {
		this.planDuration = planDuration;
	}

	public Float getPlanPricing() {
		return planPricing;
	}

	public void setPlanPricing(Float planPricing) {
		this.planPricing = planPricing;
	}

	public String getPlanActivationStatus() {
		return planActivationStatus;
	}

	public void setPlanActivationStatus(String planActivationStatus) {
		this.planActivationStatus = planActivationStatus;
	}

	public Date getPlanActivationDate() {
		return planActivationDate;
	}

	public void setPlanActivationDate(Date planActivationDate) {
		this.planActivationDate = planActivationDate;
	}

	public Date getPlanDeactivationDate() {
		return planDeactivationDate;
	}

	public void setPlanDeactivationDate(Date planDeactivationDate) {
		this.planDeactivationDate = planDeactivationDate;
	}

	public Float getPercentageDiscount() {
		return percentageDiscount;
	}

	public void setPercentageDiscount(Float percentageDiscount) {
		this.percentageDiscount = percentageDiscount;
	}

	public SubscriptionResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public SubscriptionResponse() {

	}

	public Date getPlanActiveTill() {
		return planActiveTill;
	}

	public void setPlanActiveTill(Date planActiveTill) {
		this.planActiveTill = planActiveTill;
	}

	
	public List<Long> getAssociatedProductSequenceNumberList() {
		return associatedProductSequenceNumberList;
	}

	public void setAssociatedProductSequenceNumberList(List<Long> associatedProductSequenceNumberList) {
		this.associatedProductSequenceNumberList = associatedProductSequenceNumberList;
	}

	@Override
	public String toString() {
		return "SubscriptionResponse [sequenceNumber=" + sequenceNumber + ", errorCode=" + errorCode + ", errorMessage="
				+ errorMessage + ", planId=" + planId + ", planName=" + planName + ", planDescription="
				+ planDescription + ", planDuration=" + planDuration + ", planPricing=" + planPricing
				+ ", planActivationStatus=" + planActivationStatus + ", planActivationDate=" + planActivationDate
				+ ", planDeactivationDate=" + planDeactivationDate + ", percentageDiscount=" + percentageDiscount
				+ ", planActiveTill=" + planActiveTill + "]";
	}

}