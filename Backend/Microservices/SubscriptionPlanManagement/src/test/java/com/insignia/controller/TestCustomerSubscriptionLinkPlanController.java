package com.insignia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.entity.CustomerSubscriptionPlanLink;
import com.insignia.model.SubscriptonLinkRequest;
import com.insignia.model.SubscriptonLinkResponse;
import com.insignia.model.SubscriptionResponse;
import com.insignia.service.CustomerAndSubscriptonLinkServiceInterface;

@ExtendWith(MockitoExtension.class)
public class TestCustomerSubscriptionLinkPlanController {

	@InjectMocks
	private CustomerAndSubscriptonLinkController customerAndSubscriptonLinkController;

	@Mock
	private CustomerAndSubscriptonLinkServiceInterface serviceRepo;

	SubscriptonLinkRequest customerAndSubscriptonLinkRequest = new SubscriptonLinkRequest();
	SubscriptonLinkResponse customerAndSubscriptonLinkResponse = new SubscriptonLinkResponse();
	CustomerSubscriptionPlanLink customerSubscriptionPlanLink = new CustomerSubscriptionPlanLink();

	List<SubscriptonLinkResponse> customerAndSubscriptonLinkResponseList = new ArrayList<>();
	SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
	List<SubscriptionResponse> subscriptionResponseList = new ArrayList<>();
	Optional<List<SubscriptonLinkResponse>> optionalCustomerAndSubscriptionLinkResponse = Optional
			.ofNullable(customerAndSubscriptonLinkResponseList);

	public void dataInitilization() throws ParseException {
		customerAndSubscriptonLinkRequest.setCustomerSequenceNumber(5L);
		customerAndSubscriptonLinkRequest.setExpirationDuration(15);
		customerAndSubscriptonLinkRequest.setSubscriptionPlanSequenceNumber(5L);

		customerAndSubscriptonLinkResponse.setSequenceNumber(5L);
		customerAndSubscriptonLinkResponse.setSubscriptionPlanSequenceNumber(5L);

		subscriptionResponse.setSequenceNumber(5L);
		subscriptionResponse.setPlanId("1");
		subscriptionResponse.setPlanName("Health");
		subscriptionResponse.setPlanDescription("Its Kids");
		subscriptionResponse.setPlanDuration(3);
		subscriptionResponse.setPlanPricing(3f);
		subscriptionResponse.setPlanActivationStatus("Active");
		subscriptionResponse.setPercentageDiscount(3f);

		subscriptionResponseList.add(subscriptionResponse);

		customerAndSubscriptonLinkResponseList.add(customerAndSubscriptonLinkResponse);
		customerSubscriptionPlanLink.setCustomerSequenceNumber(5L);
		customerSubscriptionPlanLink.setSequenceNumber(5L);

	}

	@Test
	public void testAssociateSubscriptionPlanWithCustomer()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();
		when(serviceRepo.associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest))
				.thenReturn(customerAndSubscriptonLinkResponse);

		ResponseEntity<?> associateSubscriptionPlanWithCustomer = customerAndSubscriptonLinkController
				.associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);
		assertEquals(HttpStatus.OK, associateSubscriptionPlanWithCustomer.getStatusCode());
	}

	@Test
	public void testDeleteSubscriptionPlanWithCustomer()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		doNothing().when(serviceRepo).removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);
		customerAndSubscriptonLinkController.removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);
		verify(serviceRepo, times(1)).removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);
	}

	@Test
	public void testGetSubscriptionPlanWithCustomer()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();
		when(serviceRepo.getAllSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest))
				.thenReturn(optionalCustomerAndSubscriptionLinkResponse);

		ResponseEntity<?> getSubscriptionPlanWithCustomer = customerAndSubscriptonLinkController
				.getSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);
		assertEquals(HttpStatus.OK, getSubscriptionPlanWithCustomer.getStatusCode());
	}

	@Test
	public void testForTokenExpired() throws TokenExpiredException, InvalidInputParametersException, ParseException {
		dataInitilization();

		when(serviceRepo.associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest))
				.thenThrow(new TokenExpiredException(""));
		ResponseEntity<?> associateSubscriptionPlanWithCustomer = customerAndSubscriptonLinkController
				.associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);

		assertEquals(HttpStatus.BAD_REQUEST, associateSubscriptionPlanWithCustomer.getStatusCode());

		when(serviceRepo.getAllSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest))
				.thenThrow(new TokenExpiredException(""));
		ResponseEntity<?> getSubscriptionPlanWithCustomer = customerAndSubscriptonLinkController
				.getSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);

		assertEquals(HttpStatus.BAD_REQUEST, getSubscriptionPlanWithCustomer.getStatusCode());

		doThrow(new TokenExpiredException("")).when(serviceRepo)
				.removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);
		ResponseEntity<?> deleteSubscriptionPlanWithCustomer = customerAndSubscriptonLinkController
				.removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);

		assertEquals(HttpStatus.BAD_REQUEST, deleteSubscriptionPlanWithCustomer.getStatusCode());

	}

	@Test
	public void testForException() throws TokenExpiredException, InvalidInputParametersException, ParseException {
		dataInitilization();

		when(serviceRepo.associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest))
				.thenThrow(new NullPointerException(""));
		ResponseEntity<?> associateSubscriptionPlanWithCustomer = customerAndSubscriptonLinkController
				.associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);

		assertEquals(HttpStatus.BAD_REQUEST, associateSubscriptionPlanWithCustomer.getStatusCode());

		when(serviceRepo.getAllSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest))
				.thenThrow(new NullPointerException(""));
		ResponseEntity<?> getSubscriptionPlanWithCustomer = customerAndSubscriptonLinkController
				.getSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);

		assertEquals(HttpStatus.BAD_REQUEST, getSubscriptionPlanWithCustomer.getStatusCode());

		doThrow(new NullPointerException("")).when(serviceRepo)
				.removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);
		ResponseEntity<?> deleteSubscriptionPlanWithCustomer = customerAndSubscriptonLinkController
				.removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);

		assertEquals(HttpStatus.BAD_REQUEST, deleteSubscriptionPlanWithCustomer.getStatusCode());

	}

	@Test
	public void testForInvalidInputParametersException()
			throws InvalidInputParametersException, TokenExpiredException, ParseException {
		dataInitilization();

		doThrow(new InvalidInputParametersException("255", "Invalid data")).when(serviceRepo)
				.associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);

		ResponseEntity<?> associateSubscriptionPlanWithCustomer = customerAndSubscriptonLinkController
				.associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);

		verify(serviceRepo).associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);

		assertEquals(HttpStatus.BAD_REQUEST, associateSubscriptionPlanWithCustomer.getStatusCode());

		// getAllSubscriptionPlanForCustomer
		doThrow(new InvalidInputParametersException("255", "Invalid data")).when(serviceRepo)
				.getAllSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);

		ResponseEntity<?> getAllSubscriptionPlanForCustomer = customerAndSubscriptonLinkController
				.getSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);

		verify(serviceRepo).getAllSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);

		assertEquals(HttpStatus.BAD_REQUEST, getAllSubscriptionPlanForCustomer.getStatusCode());

		// deleteSubscriptionPlanWithCustomer
		doThrow(new InvalidInputParametersException("255", "Invalid data")).when(serviceRepo)
				.removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);
		

		ResponseEntity<?> removeSubscriptionPlanForCustomer = customerAndSubscriptonLinkController
				.removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);

		verify(serviceRepo).removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);
		

		assertEquals(HttpStatus.BAD_REQUEST, removeSubscriptionPlanForCustomer.getStatusCode());

	}

}
