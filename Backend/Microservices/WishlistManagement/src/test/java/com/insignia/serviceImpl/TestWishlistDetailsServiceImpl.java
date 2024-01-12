package com.insignia.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.daoInterface.WishlistDaoInterface;
import com.insignia.entity.ProductDetails;
import com.insignia.entity.WishlistDetailsEntity;
import com.insignia.model.ProductDetailsRequest;
import com.insignia.model.WishlistDetails;
import com.insignia.model.WishlistDetailsRequest;
import com.insignia.model.WishlistManagementResponse;

@ExtendWith(MockitoExtension.class)
public class TestWishlistDetailsServiceImpl {

	@InjectMocks
	private WishlistServiceImpl serviceImpl;

	@Mock
	private WishlistDaoInterface daoRepo;

	@Mock
	private TokenDaoInterface tokenRepo;

	List<WishlistDetailsEntity> wishlistDetailsEntityList = new ArrayList<>();
	WishlistDetailsEntity wishlistDetailsEntity = new WishlistDetailsEntity();

	WishlistDetailsRequest wishlistDetailsRequest = new WishlistDetailsRequest();
	WishlistDetails wishlistDetailsResponse = new WishlistDetails();
	WishlistManagementResponse wishlistManagementResponse = new WishlistManagementResponse();

	List<WishlistDetails> wishlistDetailsResponseList = new ArrayList<>();

	ProductDetailsRequest productDetailsRequest = new ProductDetailsRequest();
	List<ProductDetailsRequest> productDetailsRequestList = new ArrayList<>();

	ProductDetails productDetails = new ProductDetails();
	
	public void dataInitilization() {

		wishlistDetailsRequest.setCustomerSequenceNumber(8L);
		wishlistDetailsRequest.setExpirationDuration(15);

		productDetailsRequest.setProductSequenceNumber(1L);
		productDetailsRequest.setQuantity(8);

		productDetailsRequestList.add(productDetailsRequest);

		wishlistDetailsRequest.setProductDetailsRequestList(productDetailsRequestList);

		wishlistDetailsEntity.setWishlistId(5L);
		wishlistDetailsEntity.setCustomerSequenceNumber(8L);
		wishlistDetailsEntity.setProductSequenceNumber(5L);
		wishlistDetailsEntity.setQuantity(8);

		wishlistDetailsEntityList.add(wishlistDetailsEntity);

		wishlistDetailsResponse.setWishlistId(5L);
		wishlistDetailsResponse.setProductSequenceNumber(5L);
		wishlistDetailsResponse.setQuantity(8);
		wishlistDetailsResponse.setProductName("mobile");
		wishlistDetailsResponse.setDescription("HI");
		wishlistDetailsResponse.setProductImage("phone");

		wishlistDetailsResponseList.add(wishlistDetailsResponse);

		productDetails.setProductId("5L");
		productDetails.setProductName("Health");
		productDetails.setDescription("Its very");
		productDetails.setMeasuringQuantity("Pure");
		productDetails.setMeasuringUnit("5");
		productDetails.setProductImage("Drive");
		productDetails.setProductPerUnitActualPrice(5f);
		productDetails.setProductPerUnitCurrentPrice(6f);
	}

	@Test
	public void testSaveWishlistForCustomer() throws TokenExpiredException, InvalidInputParametersException {
		
		dataInitilization();

		Long customerSequenceNumber = 8L;
		List<Long> productSequenceNumber = Arrays.asList(1L);

		when(daoRepo.getCustomerWishlist(customerSequenceNumber)).thenReturn(wishlistDetailsEntityList);
		when(daoRepo.findWishlistDetailByProductSequenceNumber(customerSequenceNumber, productSequenceNumber))
				.thenReturn(wishlistDetailsEntityList);

		ArgumentMatcher<List<WishlistDetailsEntity>> wishlistListMatcher = list -> true;

		when(daoRepo.saveWishlistForCustomer(argThat(wishlistListMatcher))).thenReturn(wishlistDetailsEntityList);

		WishlistManagementResponse saveWishlistForCustomer = serviceImpl
				.saveWishlistForCustomer(wishlistDetailsRequest);

		assertNotNull(saveWishlistForCustomer);
	}

	@Test
	public void testGetCustomerWishlist() throws TokenExpiredException, InvalidInputParametersException {
		dataInitilization();

		Long customer_sequence_number = 8L;
		Integer expirationDuration = 5;

		List<Object[]> mockData = new ArrayList<>();
		Object[] data1 = { 1, "Insignia", "hi" };
		mockData.add(data1);
		when(daoRepo.getProductDetails(any())).thenReturn(Optional.of(productDetails));

		Mockito.when(daoRepo.getCustomerWishlist(customer_sequence_number)).thenReturn(wishlistDetailsEntityList);

		WishlistManagementResponse customerWishlist = serviceImpl.getCustomerWishlist(customer_sequence_number,
				expirationDuration);
		assertNotNull(customerWishlist);
	}

	@Test
	public void testRemoveProductFromWishlist() throws TokenExpiredException, InvalidInputParametersException {
		dataInitilization();

		Long customer_sequence_number = 8L;
		Integer expirationDuration = 5;

		List<Long> productId = new ArrayList<Long>();
		productId.add(1L);
		productId.add(2L);
		productId.add(3L);

		serviceImpl.removeProductFromWishlist(customer_sequence_number, productId, expirationDuration);

		verify(daoRepo, times(1)).removeProductFromWishlist(customer_sequence_number, productId);

	}

	@Test
	public void testdeleteCustomerWishList() throws TokenExpiredException, InvalidInputParametersException {
		dataInitilization();

		Long customer_sequence_number = 8L;
		Integer expirationDuration = 5;

		serviceImpl.deleteCustomerWishList(customer_sequence_number, expirationDuration);

		verify(daoRepo, times(1)).deleteCustomerWishList(customer_sequence_number);

	}

}
