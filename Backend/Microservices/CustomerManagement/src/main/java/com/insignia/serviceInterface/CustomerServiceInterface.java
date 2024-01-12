package com.insignia.serviceInterface;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.CustomerManagementServiceRequest;
import com.insignia.model.CustomerManagementServiceResponse;

public interface CustomerServiceInterface {

	public CustomerManagementServiceResponse saveAllCustomerDetails(
			CustomerManagementServiceRequest customerManagementServiceDetails)
			throws InvalidInputParametersException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;

	public Optional<CustomerManagementServiceResponse> getCustomerDetails(Long customerSequenceNumber,
			Integer expirationDuration) throws TokenExpiredException;

	public CustomerManagementServiceResponse updateAllCustomerDetails(
			CustomerManagementServiceRequest customerManagementServiceDetails)
			throws TokenExpiredException, InvalidInputParametersException;

	public void deleteCustomerAssociatedDetails(Long customerSequenceNumber, Integer expirationDuration)
			throws TokenExpiredException;

	public List<CustomerManagementServiceResponse> getAllCustomerData(Long customerSequenceNumber,
			Integer expirationDuration) throws TokenExpiredException;

	public CustomerManagementServiceResponse getCustomerAndStoreInformation(Long customerSequenceNumber,
			Integer expirationDuration) throws TokenExpiredException, ParseException;
}
