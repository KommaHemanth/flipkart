package com.insignia.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insignia.constants.OrderValidatorConstant;
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
import com.insignia.model.OrderStatus;
import com.insignia.model.ProductDetailsResponse;
import com.insignia.service.OrderServiceInterface;

@Service
public class OrderServiceImpl implements OrderServiceInterface {

	@Autowired
	private OrderDaoInterface orderDaoInterface;

	@Autowired
	private TokenDaoInterface tokenDao;

	@Transactional
	@Override
	public OrderResponse createOrder(OrderRequest orderRequest)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(orderRequest.getCustomerSequenceNumber(), orderRequest.getExpirationDuration());

		OrderDetails orderEntity = new OrderDetails();

		orderEntity.setOrderId(orderRequest.getOrderId());
		orderEntity.setOrderDate(orderRequest.getOrderDate());
		orderEntity.setOrderStatus(orderRequest.getOrderStatus());
		orderEntity.setInvoiceId(orderRequest.getInvoiceId());
		orderEntity.setCustomerSequenceNumber(orderRequest.getCustomerSequenceNumber());
		orderEntity.setAddressSequenceNumber(orderRequest.getAddressSequenceNumber());

		List<OrderAndProductLinkRequest> orderAndProductLinkRequestList = orderRequest
				.getOrderAndProductLinkRequestList();
		if (orderAndProductLinkRequestList != null) {

			List<OrderAndProductLink> orderAndProductLinkList = new ArrayList<>();
			for (OrderAndProductLinkRequest orderAndProductLinkRequest : orderAndProductLinkRequestList) {
				OrderAndProductLink orderAndProductLink = new OrderAndProductLink();
				orderAndProductLink.setProductQuantity(orderAndProductLinkRequest.getProductQuantity());
				orderAndProductLink.setOrderDetails(orderEntity);

				ProductDetails productDetails = new ProductDetails();
				productDetails.setProductSequenceNumber(orderAndProductLinkRequest.getProductSequenceNumber());
				orderAndProductLink.setProductDetails(productDetails);
				orderAndProductLinkList.add(orderAndProductLink);

			}
			orderEntity.setOrderAndProductLinkList(orderAndProductLinkList);
		}
		OrderDetails orderDetailsEntity = orderDaoInterface.CreateOrder(orderEntity);
		return createResponseForOrderDetailsEntity(orderDetailsEntity);
	}

	@Transactional
	@Override
	public OrderResponse updateOrder(OrderRequest orderRequest)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(orderRequest.getCustomerSequenceNumber(), orderRequest.getExpirationDuration());

		Optional<OrderDetails> orderDetailsList = orderDaoInterface.findById(orderRequest.getOrderSequenceNumber());

		if (orderDetailsList.isPresent()) {

			OrderDetails orderEntity = orderDetailsList.get();

			orderEntity.setOrderStatus(orderRequest.getOrderStatus());

			OrderDetails updateOrder = orderDaoInterface.updateOrder(orderEntity);

			OrderResponse responce = createResponce(orderRequest, updateOrder);

			return responce;
		} else {
			throw new InvalidInputParametersException(OrderValidatorConstant.orderDetailsIsnotFoundMessage,
					OrderValidatorConstant.orderDetailsIsnotFoundMessageErrorCode);
		}

	}

	@Transactional
	@Override
	public void deleteOrder(Long orderSequenceNumber, Long customerSequenceNumber, Integer expirationDuration)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(customerSequenceNumber, expirationDuration);

		Optional<OrderDetails> orderDetailsList = orderDaoInterface.findById(orderSequenceNumber);

		if (!orderDetailsList.isPresent()) {
			throw new InvalidInputParametersException(OrderValidatorConstant.orderDetailsIsnotFoundMessageErrorCode,
					OrderValidatorConstant.orderDetailsIsnotFoundMessage);
		} else {
			OrderDetails orderDetails = orderDetailsList.get();
			if (OrderStatus.CA.name().equals(orderDetails.getOrderStatus())) {
				orderDaoInterface.deleteOrder(orderSequenceNumber);
			} else {
				throw new InvalidInputParametersException(OrderValidatorConstant.validateOrderStatusDeleteErrorCode,
						OrderValidatorConstant.validateOrderStatusDeleteMessage);
			}
		}
	}

	@Transactional
	@Override
	public List<OrderResponse> getAllorderByCustomer(Long customerSequenceNumber, Integer expirationDuration)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(customerSequenceNumber, expirationDuration);
		List<OrderResponse> orderResponceList = new ArrayList<>();

		List<OrderDetails> orderDetailsList = orderDaoInterface.getALLorderById(customerSequenceNumber);

		for (OrderDetails orderDetails : orderDetailsList) {

			OrderResponse orderResponce = createResponseForOrderDetailsEntity(orderDetails);

			orderResponceList.add(orderResponce);
		}
		return orderResponceList;
	}

//----------------------------------------------------------------------------------------------------
	@Transactional
	@Override
	public Optional<OrderResponse> getOrderById(Long orderSequenceNumber, Long customerSequenceNumber,
			Integer expirationDuration) throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(customerSequenceNumber, expirationDuration);
		Optional<OrderDetails> orderDetailsList = orderDaoInterface.findById(orderSequenceNumber);
		if (!orderDetailsList.isEmpty()) {
			OrderDetails orderDetails = orderDetailsList.get();

			OrderResponse createResponse = createResponseForOrderDetailsEntity(orderDetails);

			return Optional.of(createResponse);
		}
		return Optional.empty();

	}

	private OrderResponse createResponseForOrderDetailsEntity(OrderDetails orderDetails) {
	    OrderResponse orderResponse = new OrderResponse();
	    orderResponse.setOrderSequenceNumber(orderDetails.getOrderSequenceNumber());
	    orderResponse.setOrderId(orderDetails.getOrderId());
	    orderResponse.setOrderDate(orderDetails.getOrderDate());
	    orderResponse.setOrderStatus(orderDetails.getOrderStatus());
	    orderResponse.setInvoiceId(orderDetails.getInvoiceId());
	    orderResponse.setCustomerSequenceNumber(orderDetails.getCustomerSequenceNumber());
	    orderResponse.setAddressSequenceNumber(orderDetails.getAddressSequenceNumber());

	    List<OrderAndProductLinkResponse> orderAndProductLinkResponses = new ArrayList<>();

	    List<OrderAndProductLink> orderAndProductLinkList = orderDetails.getOrderAndProductLinkList();
	    if (orderAndProductLinkList != null) {
	        for (OrderAndProductLink orderAndProductLink : orderAndProductLinkList) {
	            OrderAndProductLinkResponse orderAndProductLinkResponse = new OrderAndProductLinkResponse();
	            orderAndProductLinkResponse.setProductQuantity(orderAndProductLink.getProductQuantity());

	            ProductDetails productDetails = orderAndProductLink.getProductDetails();
	            ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
	            
	            productDetailsResponse.setProductSequenceNumber(productDetails.getProductSequenceNumber());
	            productDetailsResponse.setProductId(productDetails.getProductId());
	            productDetailsResponse.setProductName(productDetails.getProductName());
	            productDetailsResponse.setDescription(productDetails.getDescription());
	            productDetailsResponse.setMeasuringQuantity(productDetails.getMeasuringQuantity());
	            productDetailsResponse.setMeasuringUnit(productDetails.getMeasuringUnit());
	            productDetailsResponse.setSubcategoryId(productDetails.getSubcategoryId());
	            productDetailsResponse.setProductImage(productDetails.getProductImage());
	            productDetailsResponse.setProductPerUnitActualPrice(productDetails.getProductPerUnitActualPrice());
	            productDetailsResponse.setProductPerUnitCurrentPrice(productDetails.getProductPerUnitCurrentPrice());
	            productDetailsResponse.setProductLength(productDetails.getLength());
	            productDetailsResponse.setWidth(productDetails.getWidth());
	            productDetailsResponse.setHeight(productDetails.getHeight());
	            productDetailsResponse.setDimensionUnit(productDetails.getDimensionUnit());
	            productDetailsResponse.setMaterials(productDetails.getMaterials());
	            productDetailsResponse.setColours(productDetails.getColours());

	            orderAndProductLinkResponse.setProductDetailsResponse(productDetailsResponse);
	            orderAndProductLinkResponses.add(orderAndProductLinkResponse);
	        }
	    }

	    orderResponse.setOrderAndProductLinkResponseList(orderAndProductLinkResponses);

	    return orderResponse;
	}


	private OrderResponse createResponce(OrderRequest orderRequest, OrderDetails orderDetails) {
		OrderResponse responce = new OrderResponse();
		responce.setOrderId(orderRequest.getOrderId());
		responce.setOrderDate(orderRequest.getOrderDate());
		responce.setOrderStatus(orderRequest.getOrderStatus());
		responce.setCustomerSequenceNumber(orderRequest.getCustomerSequenceNumber());
		responce.setAddressSequenceNumber(orderRequest.getAddressSequenceNumber());
		responce.setInvoiceId(orderRequest.getInvoiceId());

		return responce;
	}

}
