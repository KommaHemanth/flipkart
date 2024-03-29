package com.insignia.daoImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.daoInterface.OrderDaoInterface;
import com.insignia.entity.OrderDetails;
import com.insignia.repo.OrderRepository;
import com.insignia.repo.TokensEntityRepo;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDaoInterface {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private TokensEntityRepo tokensEntityRepo;

	@Autowired
	private EntityManager entityManager;

	@Override
	public OrderDetails CreateOrder(OrderDetails orderdetails) throws InvalidInputParametersException {

		return orderRepository.save(orderdetails);
	}

	@Override
	public OrderDetails updateOrder(OrderDetails orderDetails) throws InvalidInputParametersException {

		return entityManager.merge(orderDetails);
	}

	@Override
	public Optional<OrderDetails> findById(Long orderSequenceNumber) {

		return orderRepository.findById(orderSequenceNumber);
	}

	@Override
	public void deleteOrder(Long orderSequenceNumber) {
		orderRepository.deleteById(orderSequenceNumber);

	}

	@Override
	public List<OrderDetails> getALLorderById(Long customerSequenceNumber) throws InvalidInputParametersException {
		return orderRepository.findByCustomerSequenceNumber(customerSequenceNumber);

	}

	@Override
	public Boolean isTokenNotValid(Long customerSequenceNumber) {
		Date currentTimeStamp = new Date();
		return tokensEntityRepo.checkTokenValidity(customerSequenceNumber, currentTimeStamp).isEmpty();
	}
}
