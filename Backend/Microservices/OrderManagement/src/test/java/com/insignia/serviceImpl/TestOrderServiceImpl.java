package com.insignia.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.OrderDaoInterface;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.entity.OrderAndProductLink;
import com.insignia.entity.OrderDetails;
import com.insignia.entity.ProductDetails;
import com.insignia.model.OrderAndProductLinkRequest;
import com.insignia.model.OrderAndProductLinkResponse;
import com.insignia.model.OrderRequest;
import com.insignia.model.OrderResponse;
import com.insignia.model.ProductDetailsResponse;
import com.insignia.serviceimpl.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestOrderServiceImpl {

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;

	@Mock
	private OrderDaoInterface orderDaoRepo;

	@Mock
	private TokenDaoInterface tokenRepo;

	List<OrderAndProductLinkResponse> orderAndProductLinkResponseList = new ArrayList<>();
	List<OrderDetails> orderDetailsList = new ArrayList<>();
	List<OrderResponse> orderResponseList = new ArrayList<>();
	OrderRequest orderRequest = new OrderRequest();
	OrderResponse orderResponse = new OrderResponse();

	OrderDetails orderDetails = new OrderDetails();

	OrderAndProductLinkResponse orderAndProductLinkResponse = new OrderAndProductLinkResponse(); 
	ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
	
	ProductDetails productDetails = new ProductDetails();
	List<ProductDetails> productDetailsList = new ArrayList<>();
	
	OrderAndProductLink orderAndProductLink = new OrderAndProductLink();
	List<OrderAndProductLink> orderAndProductLinkList = new ArrayList<>();
	
	OrderAndProductLinkRequest orderAndProductLinkRequest = new OrderAndProductLinkRequest();
	List<OrderAndProductLinkRequest> orderAndProductLinkRequestList = new ArrayList<>();
	public void dataInitilization() {
		orderRequest.setCustomerSequenceNumber(5L);
		orderRequest.setExpirationDuration(15);
		orderRequest.setOrderSequenceNumber(5L);

		orderRequest.setOrderStatus("CA");
		orderRequest.setOrderId(5L);
		orderRequest.setInvoiceId("123");
		orderRequest.setOrderDate(new Date());
		orderRequest.setCustomerSequenceNumber(5L);
		orderRequest.setAddressSequenceNumber(1L);

		orderResponse.setOrderSequenceNumber(5L);
		orderResponse.setOrderStatus("CA");
		orderResponse.setOrderId(5L);
		orderResponse.setInvoiceId("123");
		orderResponse.setCustomerSequenceNumber(5L);
		orderResponse.setAddressSequenceNumber(1L);

		orderResponseList.add(orderResponse);

		orderDetails.setOrderStatus("CA");
		orderDetails.setOrderId(5L);
		orderDetails.setInvoiceId("123");
		orderDetails.setOrderDate(new Date());
		orderDetails.setCustomerSequenceNumber(5L);
		orderDetails.setAddressSequenceNumber(1L);
		orderDetailsList.add(orderDetails);
		

		productDetailsResponse.setProductSequenceNumber(5L);
		productDetailsResponse.setProductId("5L");
		productDetailsResponse.setProductName("Mobile");
		productDetailsResponse.setDescription("Small Mobile");
		productDetailsResponse.setMeasuringQuantity("2");
		productDetailsResponse.setMeasuringUnit("5");
		productDetailsResponse.setSubcategoryId(5L);
		productDetailsResponse.setProductImage("Mobile");
		productDetailsResponse.setProductPerUnitActualPrice(4f);
		productDetailsResponse.setProductPerUnitCurrentPrice(4f);
		productDetailsResponse.setWidth(4f);
		productDetailsResponse.setHeight(4f);
		productDetailsResponse.setDimensionUnit("inches");
		orderAndProductLinkResponse.setProductQuantity(5);
		orderAndProductLinkResponse.setProductDetailsResponse(productDetailsResponse);
		orderAndProductLinkResponseList.add(orderAndProductLinkResponse);
	
	
		productDetails.setProductSequenceNumber(5L);
		productDetails.setProductId("5L");
		productDetails.setProductName("Mobile");
		productDetails.setDescription("Small Mobile");
		productDetails.setMeasuringQuantity("2");
		productDetails.setMeasuringUnit("5");
		productDetails.setSubcategoryId(5L);
		productDetails.setProductImage("Mobile");
		productDetails.setProductPerUnitActualPrice(4f);
		productDetails.setProductPerUnitCurrentPrice(4f);
		productDetails.setLength(4f);
		productDetails.setWidth(4f);
		productDetails.setHeight(4f);
		productDetails.setDimensionUnit("inches");

		productDetailsList.add(productDetails);
		orderResponse.setOrderAndProductLinkResponseList(orderAndProductLinkResponseList);
		
	
		orderAndProductLink.setOrderDetails(orderDetails);
		orderAndProductLink.setProductDetails(productDetails);
		orderAndProductLink.setProductQuantity(5);
		orderAndProductLinkList.add(orderAndProductLink);
		
		orderDetails.setOrderAndProductLinkList(orderAndProductLinkList);
		
		orderAndProductLinkRequest.setProductQuantity(5);
		orderAndProductLinkRequest.setProductSequenceNumber(5L);
		
		orderAndProductLinkRequestList.add(orderAndProductLinkRequest);
		
		orderRequest.setOrderAndProductLinkRequestList(orderAndProductLinkRequestList);
	}

	public OrderDetails getOrderDetails() {
		orderDetails.setOrderSequenceNumber(5L);

		orderDetails.setOrderStatus("CA");
		orderDetails.setOrderId(5L);
		orderDetails.setInvoiceId("123");
		orderDetails.setOrderDate(new Date());
		orderDetails.setCustomerSequenceNumber(5L);
		orderDetails.setAddressSequenceNumber(1L);
		return orderDetails;
	}

	@Test
	public void testCreateOrder() throws TokenExpiredException, InvalidInputParametersException {

		dataInitilization();

		when(orderDaoRepo.CreateOrder(any(OrderDetails.class))).thenReturn(orderDetails);

		OrderResponse createOrder = orderServiceImpl.createOrder(orderRequest);

		assertNotNull(createOrder);
	}

	@Test
	public void testUpdateOrder() throws TokenExpiredException, InvalidInputParametersException {

		Long orderSequenceNumber = 5L;

		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setOrderSequenceNumber(5L);
		orderRequest.setOrderStatus("PE");

		getOrderDetails();
		Optional<OrderDetails> optional = Optional.ofNullable(orderDetails);

		when(orderDaoRepo.findById(orderSequenceNumber)).thenReturn(optional);
		when(orderDaoRepo.updateOrder(orderDetails)).thenReturn(orderDetails);

		OrderResponse updateOrder = orderServiceImpl.updateOrder(orderRequest);

		assertNotNull(updateOrder);
	}

	@Test
	public void testUpdateOrder_OrderDetailsNotFoundException() {

		when(orderDaoRepo.findById(any())).thenReturn(Optional.empty());

		assertThrows(InvalidInputParametersException.class, () -> orderServiceImpl.updateOrder(orderRequest));

	}

	@Test
	public void testDeleteByOrderSequenceNumber() throws TokenExpiredException, InvalidInputParametersException {

		getOrderDetails();

		Long orderSequenceNumber = 5L;
		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		Optional<OrderDetails> optional = Optional.ofNullable(orderDetails);
		when(orderDaoRepo.findById(orderSequenceNumber)).thenReturn(optional);

		orderServiceImpl.deleteOrder(orderSequenceNumber, customerSequenceNumber, expirationDuration);

		verify(orderDaoRepo, times(1)).deleteOrder(orderSequenceNumber);

	}

	@Test
	public void testDeleteOrder_OrderDetailsNotFoundException()
			throws TokenExpiredException, InvalidInputParametersException {

		Long orderSequenceNumber = 5L;
		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		when(orderDaoRepo.findById(orderSequenceNumber)).thenReturn(Optional.empty());

		assertThrows(InvalidInputParametersException.class,
				() -> orderServiceImpl.deleteOrder(orderSequenceNumber, customerSequenceNumber, expirationDuration));

		verify(orderDaoRepo, times(1)).findById(any());

	}

	@Test
	public void testFordeleteOrder_OrderCannotBeDeleted()
			throws TokenExpiredException, InvalidInputParametersException {

		getOrderDetails();

		Long orderSequenceNumber = 5L;
		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		orderDetails.setOrderStatus("DE");

		Optional<OrderDetails> optional = Optional.ofNullable(orderDetails);
		when(orderDaoRepo.findById(orderSequenceNumber)).thenReturn(optional);

		assertThrows(InvalidInputParametersException.class,
				() -> orderServiceImpl.deleteOrder(orderSequenceNumber, customerSequenceNumber, expirationDuration));

		verify(orderDaoRepo, times(1)).findById(any());

	}

	@Test
	public void testGetAllOrderByCustomer() throws TokenExpiredException, InvalidInputParametersException {

		dataInitilization();

		Long customerCartSequenceNumber = 5L;
		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		when(orderDaoRepo.getALLorderById(customerCartSequenceNumber)).thenReturn(orderDetailsList);

		List<OrderResponse> allorderByCustomer = orderServiceImpl.getAllorderByCustomer(customerSequenceNumber, expirationDuration);
		assertNotNull(allorderByCustomer);
	}

	@Test
	public void testForGetOrderById() throws TokenExpiredException, InvalidInputParametersException {

		Long orderSequenceNumber = 5L;
		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		getOrderDetails();
		Optional<OrderDetails> optional = Optional.ofNullable(orderDetails);

		Mockito.when(orderDaoRepo.findById(orderSequenceNumber)).thenReturn(optional);
		Optional<OrderResponse> orderById = orderServiceImpl.getOrderById(orderSequenceNumber, customerSequenceNumber,
				expirationDuration);

		assertNotNull(orderById);
	}

	@Test
	public void testForGetOrderByIdEmptyList() throws TokenExpiredException, InvalidInputParametersException {

		Long orderSequenceNumber = 5L;
		Long customerSequenceNumber = 5L;
		Integer expirationDuration = 15;

		Optional<OrderResponse> orderById = orderServiceImpl.getOrderById(orderSequenceNumber, customerSequenceNumber,
				expirationDuration);

		assertNotNull(orderById);
	}

}
