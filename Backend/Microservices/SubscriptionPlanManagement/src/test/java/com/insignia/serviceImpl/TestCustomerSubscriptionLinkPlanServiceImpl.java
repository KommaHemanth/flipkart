package com.insignia.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.CustomerAndSubscriptonLinkDaoInterface;
import com.insignia.daoInterface.SubscriptionDaoInterface;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.entity.CustomerSubscriptionPlanLink;
import com.insignia.entity.SubscriptionDetails;
import com.insignia.model.SubscriptonLinkRequest;
import com.insignia.model.SubscriptonLinkResponse;
import com.insignia.model.PlanStatus;
import com.insignia.model.SubscriptionResponse;

@ExtendWith(MockitoExtension.class)
public class TestCustomerSubscriptionLinkPlanServiceImpl {

	@InjectMocks
	private CustomerAndSubscriptonLinkServiceImpl serviceImpl;

	@Mock
	private CustomerAndSubscriptonLinkDaoInterface daoRepo;

	@Mock
	private SubscriptionDaoInterface subscriptionDaoInterface;

	@Mock
	private TokenDaoInterface tokenRepo;

	SubscriptonLinkRequest customerAndSubscriptonLinkRequest = new SubscriptonLinkRequest();
	SubscriptonLinkResponse customerAndSubscriptonLinkResponse = new SubscriptonLinkResponse();
	CustomerSubscriptionPlanLink customerSubscriptionPlanLink = new CustomerSubscriptionPlanLink();

	List<SubscriptonLinkResponse> customerAndSubscriptonLinkResponseList = new ArrayList<>();
	SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
	List<SubscriptionResponse> subscriptionResponseList = new ArrayList<>();
	Optional<List<SubscriptonLinkResponse>> optionalCustomerAndSubscriptionLinkResponse = Optional
			.ofNullable(customerAndSubscriptonLinkResponseList);
	SubscriptionDetails subscriptionDetails = new SubscriptionDetails();

	List<CustomerSubscriptionPlanLink> customerSubscriptionPlanLinkList = new ArrayList<>();

	public void dataInitilization() throws ParseException {
		customerAndSubscriptonLinkRequest.setCustomerSequenceNumber(6L);
		customerAndSubscriptonLinkRequest.setExpirationDuration(15);
		customerAndSubscriptonLinkRequest.setSubscriptionPlanSequenceNumber(5L);

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
		customerSubscriptionPlanLink.setCustomerSequenceNumber(10L);

		subscriptionDetails.setPlanActivationStatus(PlanStatus.ACTIVE.name());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date planActivationDate = dateFormat.parse("2023-11-29 12:00:00");
		subscriptionDetails.setPlanActivationDate(planActivationDate);

		Date planActiveTill = dateFormat.parse("3030-12-06 12:00:00");
		subscriptionDetails.setPlanActiveTill(planActiveTill);

		customerSubscriptionPlanLink.setSubscriptionDetails(subscriptionDetails);
		customerSubscriptionPlanLinkList.add(customerSubscriptionPlanLink);
	}

	@Test
	public void testAssociateSubscriptionPlanWithCustomer()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();
		SubscriptonLinkRequest customerAndSubscriptonLinkRequest = new SubscriptonLinkRequest();
		customerAndSubscriptonLinkRequest.setCustomerSequenceNumber(10L);
		customerAndSubscriptonLinkRequest.setSubscriptionPlanSequenceNumber(15L);

		SubscriptionDetails subscriptionDetails = new SubscriptionDetails();

		subscriptionDetails.setPlanActivationStatus(PlanStatus.ACTIVE.name());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date planActivationDate = dateFormat.parse("2023-11-29 12:00:00");
		subscriptionDetails.setPlanActivationDate(planActivationDate);

		Date planActiveTill = dateFormat.parse("3030-12-06 12:00:00");
		subscriptionDetails.setPlanActiveTill(planActiveTill);

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(daoRepo.findCustomerSubscritipionDetails(any(), any())).thenReturn(Optional.empty());
		when(subscriptionDaoInterface.findBySequenceNumber(15L)).thenReturn(Optional.of(subscriptionDetails));
		when(daoRepo.associateSubscriptionPlanWithCustomer(customerSubscriptionPlanLink))
				.thenReturn(customerSubscriptionPlanLink);

		SubscriptonLinkResponse associateSubscriptionPlanWithCustomer = serviceImpl
				.associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);

		assertNotNull(associateSubscriptionPlanWithCustomer);
	}

	@Test
	public void testAssociateSubscriptionPlanIsNotActiveException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		subscriptionDetails.setPlanActivationStatus(PlanStatus.DEACTIVE.name());

		when(subscriptionDaoInterface.findBySequenceNumber(5L)).thenReturn(Optional.of(subscriptionDetails));

		assertThrows(InvalidInputParametersException.class, () -> {

			serviceImpl.associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);
		});
		verify(subscriptionDaoInterface, times(1)).findBySequenceNumber(any());

	}

	@Test
	public void testAssociateSubscriptionPlanIdIsNotPresentException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		when(subscriptionDaoInterface
				.findBySequenceNumber(customerAndSubscriptonLinkRequest.getSubscriptionPlanSequenceNumber()))
				.thenReturn(Optional.empty());

		assertThrows(InvalidInputParametersException.class, () -> {

			serviceImpl.associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);
		});
		verify(subscriptionDaoInterface, times(1)).findBySequenceNumber(any());

	}

	@Test
	public void testAssociateSubscriptionPlanAlreadyExistException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		subscriptionDetails.setPlanActivationStatus(PlanStatus.DEACTIVE.name());

		when(daoRepo.findCustomerSubscritipionDetails(customerAndSubscriptonLinkRequest.getCustomerSequenceNumber(),
				customerAndSubscriptonLinkRequest.getSubscriptionPlanSequenceNumber()))
				.thenReturn(Optional.of(customerSubscriptionPlanLink));

		assertThrows(InvalidInputParametersException.class, () -> {

			serviceImpl.associateSubscriptionPlanWithCustomer(customerAndSubscriptonLinkRequest);
		});
		verify(daoRepo, times(1)).findCustomerSubscritipionDetails(any(),any());

	}

	@Test
	public void testRemoveSubscriptionPlanIsCurrentlyActiveException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		customerAndSubscriptonLinkRequest.setToForceDelete(false);
		Long customerSequenceNumber = 6L;
		Long subscriptionPlanSequenceNumber = 5L;

		subscriptionDetails.setPlanActivationStatus(PlanStatus.ACTIVE.name());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date planActivationDate = dateFormat.parse("2023-11-29 12:00:00");
		subscriptionDetails.setPlanActivationDate(planActivationDate);

		Date planActiveTill = dateFormat.parse("3023-12-06 12:00:00");
		subscriptionDetails.setPlanActiveTill(planActiveTill);

		when(daoRepo.findCustomerSubscritipionDetails(customerSequenceNumber, subscriptionPlanSequenceNumber))
				.thenReturn(Optional.of(customerSubscriptionPlanLink));

		assertThrows(InvalidInputParametersException.class, () -> {

			serviceImpl.removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);
		});
		verify(daoRepo, times(1)).findCustomerSubscritipionDetails(any(),any());
	}

	@Test
	public void testRemoveSubscriptionPlanNotAvailableException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		Long customerSequenceNumber = 6L;
		Long subscriptionPlanSequenceNumber = 5L;

		subscriptionDetails.setPlanActivationStatus(PlanStatus.DEACTIVE.name());
		when(daoRepo.findCustomerSubscritipionDetails(customerSequenceNumber, subscriptionPlanSequenceNumber))
				.thenReturn(Optional.empty());

		assertThrows(InvalidInputParametersException.class, () -> {

			serviceImpl.removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);
		});
		verify(daoRepo, times(1)).findCustomerSubscritipionDetails(any(),any());
	}

	@Test
	public void testRemoveSubscriptionPlanForCustomer()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		customerAndSubscriptonLinkRequest.setToForceDelete(true);
		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(daoRepo.findCustomerSubscritipionDetails(any(), any()))
				.thenReturn(Optional.of(customerSubscriptionPlanLink));
		doNothing().when(daoRepo).removeSubscriptionPlanForCustomer(any(), any());

		serviceImpl.removeSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);

	}

	@Test
	public void testGetAllSubscriptionPlanForCustomerWithSingleRecord()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(daoRepo.findCustomerSubscritipionDetails(any(), any()))
				.thenReturn(Optional.of(customerSubscriptionPlanLink));

		Optional<List<SubscriptonLinkResponse>> allSubscriptionPlanForCustomer = serviceImpl
				.getAllSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);
		assertTrue(allSubscriptionPlanForCustomer.isPresent());

	}

	@Test
	public void testGetAllSubscriptionPlanForCustomerWithNonNullInput() throws Exception {
		dataInitilization();
		customerAndSubscriptonLinkRequest.setSubscriptionPlanSequenceNumber(null);

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());
		when(daoRepo.findAllCustomerSubscriptionDetails(any())).thenReturn(customerSubscriptionPlanLinkList);

		Optional<List<SubscriptonLinkResponse>> result = serviceImpl
				.getAllSubscriptionPlanForCustomer(customerAndSubscriptonLinkRequest);

		assertTrue(result.isPresent());

	}

}