package com.insignia.daoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.model.AuthenticationRequest;
import com.insignia.model.AuthenticationResponse;
import com.insignia.repo.CustomerBasicDetailsRepository;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class TestOtpDaoImpl {

	@Mock
	private CustomerBasicDetailsRepository customRepo;
	
	@InjectMocks
	private OtpDaoImpl otpDao;
	
	AuthenticationRequest authenticationRequest = new AuthenticationRequest();
	CustomerBasicDetailsEntity customerBasicDetailsEntity = new CustomerBasicDetailsEntity();
	AuthenticationResponse authenticationResponse = new AuthenticationResponse();

	
	@Test
    public void testGenerateAndSaveOtp_Success() {
        CustomerBasicDetailsEntity inputEntity = new CustomerBasicDetailsEntity();
        when(customRepo.save(any())).thenReturn(inputEntity); 

        CustomerBasicDetailsEntity resultEntity = otpDao.generateAndSaveOtp(inputEntity);

        
        assertNotNull(resultEntity);
        assertEquals(inputEntity, resultEntity);
        
    }
	
	@Test
    public void testMergeCustomerDetails_Success() {
		EntityManager entityManager = mock(EntityManager.class);
		
		otpDao = new OtpDaoImpl(customRepo, entityManager);
		CustomerBasicDetailsEntity inputEntity = new CustomerBasicDetailsEntity(); 
		when(entityManager.merge(customerBasicDetailsEntity)).thenReturn(customerBasicDetailsEntity);
		
		CustomerBasicDetailsEntity resultEntity = otpDao.mergeCustomerDetails(customerBasicDetailsEntity);
		assertNotNull(resultEntity);
		assertEquals(inputEntity.getCustomerId(), resultEntity.getCustomerId());
    }

	 @Test
	    public void testFetchOtpDetails_Success() {	      
	        when(customRepo.fetchOtpDetails(any(), any(), any())).thenReturn(customerBasicDetailsEntity);
	        CustomerBasicDetailsEntity resultEntity = otpDao.fetchOtpDetails(customerBasicDetailsEntity);
	        
	        assertNotNull(resultEntity);
	    }

}
