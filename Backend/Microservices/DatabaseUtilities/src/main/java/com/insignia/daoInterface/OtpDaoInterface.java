package com.insignia.daoInterface;

import com.insignia.entity.CustomerBasicDetailsEntity;

public interface OtpDaoInterface {

	public CustomerBasicDetailsEntity generateAndSaveOtp(CustomerBasicDetailsEntity customerBasicDetailsEntity);
	public CustomerBasicDetailsEntity fetchOtpDetails(CustomerBasicDetailsEntity customerBasicDetailsEntity);
	public CustomerBasicDetailsEntity mergeCustomerDetails(CustomerBasicDetailsEntity entity);
	
}
