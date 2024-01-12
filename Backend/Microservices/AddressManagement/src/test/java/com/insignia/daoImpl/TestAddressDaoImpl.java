package com.insignia.daoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.entity.AddressDetails;
import com.insignia.model.AddressRequest;
import com.insignia.model.AddressResponse;
import com.insignia.repo.AddressRepository;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class TestAddressDaoImpl {

	@Mock
	private AddressRepository addressRepo;

	@InjectMocks
	private AddressDaoImpl addressDaoImpl;

	AddressRequest addressRequest = new AddressRequest();
	AddressResponse addressRes = new AddressResponse();
	AddressDetails addressDetails = new AddressDetails();

	@BeforeEach
	public void dataInitilization() {
		addressRequest.setCustomerSequenceNumber(8L);
		addressRequest.setSequenceNumber(8);
		addressRequest.setAddressLine1("VinayakaTemple");
		addressRequest.setAddressLine2("CinemaRoad");
		addressRequest.setLandmark("Opp:ApolloHospital");
		addressRequest.setCity("Kakinada");
		addressRequest.setState("AndhraPradesh");
		addressRequest.setCountry("India");
		addressRequest.setZipCode("8765");
		addressRequest.setMobileNumber("9875689378");
		addressRequest.setEmailId("lakshmisidharth678@gmail.com");
		addressRequest.setisBillingAddress(false);
		addressRequest.setIsDefaultAddress(false);
		addressRequest.setLandlineNumber("98765895");
		addressRequest.setExpirationDuration(5);

		addressRes.setAddressLine1("VinayakaTemple");
		addressRes.setAddressLine2("CinemaRoad");
		addressRes.setLandmark("Opp:ApolloHospital");
		addressRes.setCity("Kakinada");
		addressRes.setState("AndhraPradesh");
		addressRes.setCountry("India");
		addressRes.setZipCode("8765");
		addressRes.setMobileNumber("9875689378");
		addressRes.setEmailId("lakshmisidharth678@gmail.com");
		addressRes.setBillingAddress(false);
		addressRes.setDefaultAddress(false);
		addressRes.setLandlineNumber("98765895");

	}
	
	@Test
	public void testSaveAddress() throws InvalidInputParametersException {

		dataInitilization();
		AddressDetails addressDetails = new AddressDetails();
		
		when(addressRepo.save(addressDetails)).thenReturn(addressDetails);
		assertNotNull(addressDaoImpl.saveAddressDetails(addressDetails));

	}
	@Test
	public void testSaveAllCustomerDetails_DataIntegrityViolationException() throws InvalidInputParametersException {

		AddressDetails addressDetails = new AddressDetails();

		when(addressRepo.save(addressDetails)).thenThrow(DataIntegrityViolationException.class);

		assertThrows(InvalidInputParametersException.class, () -> addressDaoImpl.saveAddressDetails(addressDetails));

	}
	@Test
	public void testUpdateAddress() throws InvalidInputParametersException {

		dataInitilization();
		AddressDetails addressDetails = new AddressDetails();
		
		EntityManager entityManager = mock(EntityManager.class);
		addressDaoImpl = new AddressDaoImpl(addressRepo, entityManager);
		
		when(entityManager.merge(addressDetails)).thenReturn(addressDetails);
		assertNotNull(addressDaoImpl.updateAddressDetails(addressDetails));
	}

	
	@Test
	public void testUpdateAddress_DataIntegrityViolationException() throws InvalidInputParametersException {

		EntityManager entityManager = mock(EntityManager.class);
		addressDaoImpl = new AddressDaoImpl(addressRepo, entityManager);

		AddressDetails addressDetails = new AddressDetails(); 

		when(entityManager.merge(addressDetails)).thenThrow(DataIntegrityViolationException.class);

		assertThrows(InvalidInputParametersException.class, () -> addressDaoImpl.updateAddressDetails(addressDetails));
	}

	
	@Test
	public void testUpdateAddressConstraintViolationException() throws InvalidInputParametersException {

		EntityManager entityManager = mock(EntityManager.class);

		addressDaoImpl = new AddressDaoImpl(addressRepo, entityManager);

		AddressDetails addressDetails = new AddressDetails(); 

		when(entityManager.merge(addressDetails)).thenThrow(ConstraintViolationException.class);

		assertThrows(ConstraintViolationException.class, () -> addressDaoImpl.updateAddressDetails(addressDetails));
	}

	

	@Test
	public void testDeleteByAddressId() {

		Integer sequenceNumber = 1;

		addressDaoImpl.deleteByAddressId(sequenceNumber);
		verify(addressRepo, times(1)).deleteById(sequenceNumber);
	}

	@Test
	public void testDeleteAddressForCustomer() {

		Long customerSequenceNumber = 8L;

		addressDaoImpl.deleteAddressForCustomer(customerSequenceNumber);

		verify(addressRepo, times(1)).deleteByCustomerSequenceNumber(customerSequenceNumber);
	}

	@Test
	public void testGetAddressList() {

		Long customerSequenceNumber = 8L;
		
		List<AddressDetails> addressDetailsList = new ArrayList<>();

		AddressDetails addressRequest = new AddressDetails();
		addressRequest.setCustomerSequenceNumber(8L);
		addressRequest.setAddressLine1("VinayakaTemple");
		addressRequest.setAddressLine2("CinemaRoad");
		addressRequest.setLandmark("Opp:ApolloHospital");
		addressRequest.setCity("Kakinada");
		addressRequest.setState("AndhraPradesh");
		addressRequest.setCountry("India");
		addressRequest.setEmailId("lakshmisidharth678@gmail.com");
		addressRequest.setZipCode("8765");
		addressRequest.setMobileNumber("9875689378");
		addressRequest.setLandlineNumber("98765895");
		addressRequest.setBillingAddress(false);
		addressRequest.setDefaultAddress(false);
		

		AddressDetails addressRequest2 = new AddressDetails();
		addressRequest2.setCustomerSequenceNumber(9L);
		addressRequest2.setAddressLine1("VinayakaTemple");
		addressRequest2.setAddressLine2("CinemaRoad");
		addressRequest2.setLandmark("Opp:ApolloHospital");
		addressRequest2.setCity("Kakinada");
		addressRequest2.setState("AndhraPradesh");
		addressRequest2.setCountry("India");
		addressRequest2.setEmailId("lakshmisidharth678@gmail.com");
		addressRequest2.setZipCode("8765");
		addressRequest2.setMobileNumber("9875689378");
		addressRequest2.setLandlineNumber("98765895");
		addressRequest2.setBillingAddress(false);
		addressRequest2.setDefaultAddress(false);

		addressDetailsList.add(addressRequest);
		addressDetailsList.add(addressRequest2);

		when(addressRepo.findByCustomerSequenceNumber(customerSequenceNumber)).thenReturn(addressDetailsList);

		assertNotNull(addressDaoImpl.getAddressDetails(customerSequenceNumber));
	}

	
	@Test
    public void testFindByUserAddress() {
        Long customerSequenceNumber = 8L;
        Integer sequenceNumber = 1;

        // Create a mock AddressDetails object
        AddressDetails expectedAddress = new AddressDetails();
        expectedAddress.setCustomerSequenceNumber(customerSequenceNumber);
        expectedAddress.setSequenceNumber(sequenceNumber);

        // Mock the behavior of the addressRepo
        when(addressRepo.findByCustomerSequenceNumberAndSequenceNumber(customerSequenceNumber, sequenceNumber))
                .thenReturn(expectedAddress);

        // Call the method being tested
        AddressDetails result = addressDaoImpl.findByUserAddress(customerSequenceNumber, sequenceNumber);

        // Verify the method call
        verify(addressRepo, times(1)).findByCustomerSequenceNumberAndSequenceNumber(customerSequenceNumber, sequenceNumber);

        // Verify the result
        assertNotNull(result);
        assertEquals(expectedAddress, result);
    }

}
