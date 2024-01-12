package com.insignia.daoImpl;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insignia.daoInterface.OtpDaoInterface;
import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.repo.CustomerBasicDetailsRepository;

@Repository
public class OtpDaoImpl implements OtpDaoInterface {

	@Autowired
	private CustomerBasicDetailsRepository customeRepo;
	
	@Autowired
    private EntityManager entityManager;
	
	

	public OtpDaoImpl(CustomerBasicDetailsRepository customeRepo, EntityManager entityManager) {
		super();
		this.customeRepo = customeRepo;
		this.entityManager = entityManager;
	}

	@Override
	public CustomerBasicDetailsEntity generateAndSaveOtp(CustomerBasicDetailsEntity customerBasicDetailsEntity) {
			return customeRepo.save(customerBasicDetailsEntity);
	}
	
	@Transactional
    public CustomerBasicDetailsEntity mergeCustomerDetails(CustomerBasicDetailsEntity entity) {
        return entityManager.merge(entity);
    }

	@Override
	public CustomerBasicDetailsEntity fetchOtpDetails(CustomerBasicDetailsEntity customerBasicDetailsEntity) {
		return customeRepo.fetchOtpDetails(customerBasicDetailsEntity.getApplicationId(),
				customerBasicDetailsEntity.getTenantId(), customerBasicDetailsEntity.getCustomerId());
	}

}
