package com.insignia.daoInterface;

import java.util.List;
import java.util.Optional;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.entity.CustomerBasicDetailsEntity;

public interface CustomerDaoInterface {

	public CustomerBasicDetailsEntity saveAllCustomerDetails(CustomerBasicDetailsEntity cbde)
			throws InvalidInputParametersException;

	public Optional<CustomerBasicDetailsEntity> getCustomerDetails(Long customerSequenceNumber);

	public CustomerBasicDetailsEntity updateAllCustomerDetails(CustomerBasicDetailsEntity customerBasicDetailsEntity)
			throws InvalidInputParametersException;

	public void deleteCustomerAssociatedDetails(Long customerSequenceNumber);

	public List<CustomerBasicDetailsEntity> getAllCustomerData();

	public List<Object[]> getCustomerAndStoreInformation();
	
}
