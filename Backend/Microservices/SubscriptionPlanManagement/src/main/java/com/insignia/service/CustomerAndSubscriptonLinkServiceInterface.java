package com.insignia.service;

import java.util.List;
import java.util.Optional;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.SubscriptonLinkRequest;
import com.insignia.model.SubscriptonLinkResponse;

public interface CustomerAndSubscriptonLinkServiceInterface {

	public SubscriptonLinkResponse associateSubscriptionPlanWithCustomer(SubscriptonLinkRequest customerAndSubscriptonLinkRequest) throws TokenExpiredException, InvalidInputParametersException;
	public void removeSubscriptionPlanForCustomer(SubscriptonLinkRequest customerAndSubscriptonLinkRequest) throws InvalidInputParametersException, TokenExpiredException;
	public Optional<List<SubscriptonLinkResponse>> getAllSubscriptionPlanForCustomer(SubscriptonLinkRequest customerAndSubscriptonLinkRequest) throws InvalidInputParametersException, TokenExpiredException;
}
