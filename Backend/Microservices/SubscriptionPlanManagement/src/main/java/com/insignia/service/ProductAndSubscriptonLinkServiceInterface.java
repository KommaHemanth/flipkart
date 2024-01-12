package com.insignia.service;

import java.util.List;
import java.util.Optional;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.ProductSubscriptionLinkRequest;
import com.insignia.model.SubscriptonLinkResponse;

public interface ProductAndSubscriptonLinkServiceInterface {

	public SubscriptonLinkResponse associateSubscriptionPlanWithProduct(ProductSubscriptionLinkRequest productSubscriptionLinkRequest) throws TokenExpiredException, InvalidInputParametersException;
	public void removeSubscriptionPlanForProduct(ProductSubscriptionLinkRequest productSubscriptionLinkRequest) throws InvalidInputParametersException, TokenExpiredException;
	public Optional<List<SubscriptonLinkResponse>> getAllSubscriptionPlanForProduct(ProductSubscriptionLinkRequest productSubscriptionLinkRequest) throws InvalidInputParametersException, TokenExpiredException;
}
