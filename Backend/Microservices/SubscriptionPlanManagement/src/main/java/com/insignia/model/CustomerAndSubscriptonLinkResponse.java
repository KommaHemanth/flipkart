package com.insignia.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomerAndSubscriptonLinkResponse {

	private Long sequenceNumber;
	private Long subscriptionPlanSequenceNumber;

	private SubscriptionResponse subscriptionResponse;

}
