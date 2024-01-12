package com.insignia.daoImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.exception.ConstraintViolationException;
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
import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.entity.CustomerPersonalDetails;
import com.insignia.model.AddressRequest;
import com.insignia.model.AddressResponse;
import com.insignia.model.CustomerBasicDetailsRequest;
import com.insignia.model.CustomerManagementServiceRequest;
import com.insignia.model.CustomerManagementServiceResponse;
import com.insignia.model.CustomerPersonalDetailsRequest;
import com.insignia.model.CustomerPersonalDetailsResponse;
import com.insignia.model.RolesAndPermissionsRequest;
import com.insignia.model.RolesAndPermissionsResponse;
import com.insignia.repo.AddressRepository;
import com.insignia.repo.CustomerBasicDetailsRepository;
import com.insignia.repo.CustomerPersonalDetailsRepository;
import com.insignia.repo.RolesAndPermissionRepository;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")

public class TestCustomerDaoImpl {

	@Mock
	private CustomerBasicDetailsRepository customerBasicDetailsRepo;

	@Mock
	private CustomerPersonalDetailsRepository customerPersonalDetailsRepo;

	@Mock
	private RolesAndPermissionRepository rolesAndPermissionsRepo;

	@Mock
	private AddressRepository addressDetailsRepo;

	@InjectMocks
	private CustomerDaoImpl customerDaoImpl;
	
	

	CustomerManagementServiceRequest customerManagementServiceRequest = new CustomerManagementServiceRequest();
	CustomerManagementServiceResponse customerManagementServiceResponse = new CustomerManagementServiceResponse();

	AddressRequest addressRequest = new AddressRequest();
	AddressResponse addressRes = new AddressResponse();
	AddressDetails addressDetails = new AddressDetails();
	CustomerBasicDetailsRequest customerBasicDetailsRequest = new CustomerBasicDetailsRequest();
	CustomerBasicDetailsEntity customerBasicDetailsEntity = new CustomerBasicDetailsEntity();
	CustomerPersonalDetailsRequest customerPersonalDetailsRequest = new CustomerPersonalDetailsRequest();
	CustomerPersonalDetails customerPersonalDetails = new CustomerPersonalDetails();
	CustomerPersonalDetailsResponse customerPersonalDetailsResponse = new CustomerPersonalDetailsResponse();
	RolesAndPermissionsRequest rolesAndPermissionsRequest = new RolesAndPermissionsRequest();
	RolesAndPermissionsResponse rolesAndPermissionsResponse = new RolesAndPermissionsResponse();

	public void dataInitilization() {

		customerBasicDetailsRequest.setApplicationId("112");
		customerBasicDetailsRequest.setTenantId("1124");
		customerBasicDetailsRequest.setUserId("2545");
		customerBasicDetailsRequest.setPassword("5485");
		customerBasicDetailsRequest.setCustomerSequenceNumber(8L);
		customerBasicDetailsRequest.setEmailId("lakshminagu54@gmail.com");
		customerBasicDetailsRequest.setUserName("Lakshmi");
		customerBasicDetailsRequest.setExpirationDuration(5);

		customerManagementServiceRequest.setCustomerBasicDetailsRequest(customerBasicDetailsRequest);

		List<RolesAndPermissionsRequest> rolesAndPermissionsList = new ArrayList<>();

		rolesAndPermissionsRequest.setRoleId(5);
		rolesAndPermissionsRequest.setCustomerSequenceNumber(8L);
		rolesAndPermissionsRequest.setPermission1(null);
		rolesAndPermissionsRequest.setPermission2(null);
		rolesAndPermissionsRequest.setPermission3(null);
		rolesAndPermissionsRequest.setPermission4(null);
		rolesAndPermissionsRequest.setPermissionChangeDate(null);
		rolesAndPermissionsRequest.setRoleApprovedDate(null);
		rolesAndPermissionsRequest.setRoleName("admin");
		rolesAndPermissionsRequest.setRoleRevokedDate(null);
		rolesAndPermissionsRequest.setUpdatedPermissions("yes");
		rolesAndPermissionsRequest.setExpirationDuration(5);

		rolesAndPermissionsList.add(rolesAndPermissionsRequest);

		customerManagementServiceRequest.setRolesAndPermissionsRequestList(rolesAndPermissionsList);

		List<CustomerPersonalDetailsRequest> customerPersonalDetailsRequestList = new ArrayList<>();

		customerPersonalDetailsRequest.setCustomerSequenceNumber(8L);
		customerPersonalDetailsRequest.setSequenceNumber(5);
		customerPersonalDetailsRequest.setFirstName("lakshmi");
		customerPersonalDetailsRequest.setLastName("Pragallapati");
		customerPersonalDetailsRequest.setMiddleName("Nagu");
		customerPersonalDetailsRequest.setAge("29");
		customerPersonalDetailsRequest.setGender("female");
		customerPersonalDetailsRequest.setCustomerMobileNumber("1234567891");
		customerPersonalDetailsRequest.setAlternativeMobileNumber("765897653");
		customerPersonalDetailsRequest.setCustomerEmailId("lakshmisidarth4@gmail.com");
		customerPersonalDetailsRequest.setAlternativeEmailId("sidharthlakshmi4@gmail.com");
		customerPersonalDetailsRequest.setCustomerLandlineNumber("8765987");
		customerPersonalDetailsRequest.setExpirationDuration(5);

		customerPersonalDetailsRequestList.add(customerPersonalDetailsRequest);

		customerManagementServiceRequest.setCustomerPersonalDetailsRequestList(customerPersonalDetailsRequestList);

		List<AddressRequest> addressRequestList = new ArrayList<>();

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
		addressRequest.setisBillingAddress(false);
		addressRequest.setIsDefaultAddress(false);
		addressRequest.setExpirationDuration(5);

		addressRequestList.add(addressRequest);

		customerManagementServiceRequest.setAddressRequestList(addressRequestList);

		List<RolesAndPermissionsResponse> rolesAndPermissionsResponseList = new ArrayList<>();

		rolesAndPermissionsResponse.setPermission1(null);
		rolesAndPermissionsResponse.setPermission2(null);
		rolesAndPermissionsResponse.setPermission3(null);
		rolesAndPermissionsResponse.setPermission4(null);
		rolesAndPermissionsResponse.setPermissionChangeDate(null);
		rolesAndPermissionsResponse.setRoleApprovedDate(null);
		rolesAndPermissionsResponse.setRoleName("admin");
		rolesAndPermissionsResponse.setRoleRevokedDate(null);
		rolesAndPermissionsResponse.setUpdatedPermissions("yes");

		rolesAndPermissionsResponseList.add(rolesAndPermissionsResponse);

		customerManagementServiceResponse.setRolesAndPermissionsResponseList(rolesAndPermissionsResponseList);

		List<AddressResponse> addressResponseList = new ArrayList<>();

		addressRes.setAddressLine1("VinayakaTemple");
		addressRes.setAddressLine2("CinemaRoad");
		addressRes.setLandmark("Opp:ApolloHospital");
		addressRes.setCity("Kakinada");
		addressRes.setState("AndhraPradesh");
		addressRes.setCountry("India");
		addressRes.setEmailId("lakshmisidharth678@gmail.com");
		addressRes.setZipCode("8765");
		addressRes.setMobileNumber("9875689378");
		addressRes.setLandlineNumber("98765895");
		addressRes.setBillingAddress(false);
		addressRes.setDefaultAddress(false);

		addressResponseList.add(addressRes);

		customerManagementServiceResponse.setAddressResponseList(addressResponseList);

		List<CustomerPersonalDetailsResponse> customerPersonalDetailsResponseList = new ArrayList<>();

		customerPersonalDetailsResponse.setFirstName("lakshmi");
		customerPersonalDetailsResponse.setLastName("Pragallapati");
		customerPersonalDetailsResponse.setMiddleName("Nagu");
		customerPersonalDetailsResponse.setAge("29");
		customerPersonalDetailsResponse.setGender("female");
		customerPersonalDetailsResponse.setCustomerMobileNumber("1234567891");
		customerPersonalDetailsResponse.setAlternativeMobileNumber("765897653");
		customerPersonalDetailsResponse.setCustomerEmailId("lakshmisidarth4@gmail.com");
		customerPersonalDetailsResponse.setAlternativeEmailId("sidharthlakshmi4@gmail.com");
		customerPersonalDetailsResponse.setCustomerLandlineNumber("8765987");

		customerPersonalDetailsResponseList.add(customerPersonalDetailsResponse);
		customerManagementServiceResponse.setCustomerPersonalDetailsResponseList(customerPersonalDetailsResponseList);

	}

	@Test
	public void testSaveAllCustomerDetails() throws InvalidInputParametersException {
		dataInitilization();
		CustomerBasicDetailsEntity cbde = new CustomerBasicDetailsEntity();

		when(customerBasicDetailsRepo.save(cbde)).thenReturn(cbde);

		assertNotNull(customerDaoImpl.saveAllCustomerDetails(cbde));
		}

	@Test
	public void testSaveAllCustomerDetails_DataIntegrityViolationException() throws InvalidInputParametersException {

		CustomerBasicDetailsEntity cbde = new CustomerBasicDetailsEntity();

		when(customerBasicDetailsRepo.save(cbde)).thenThrow(DataIntegrityViolationException.class);

		assertThrows(InvalidInputParametersException.class, () -> customerDaoImpl.saveAllCustomerDetails(cbde));

	}

	@Test
	public void testUpdateAllCustomerDetails_DataIntegrityViolationException() throws InvalidInputParametersException {

		EntityManager entityManager = mock(EntityManager.class);

		customerDaoImpl = new CustomerDaoImpl(customerBasicDetailsRepo, entityManager);

		CustomerBasicDetailsEntity cbde = new CustomerBasicDetailsEntity();

		when(entityManager.merge(cbde)).thenThrow(ConstraintViolationException.class);

		assertThrows(ConstraintViolationException.class, () -> customerDaoImpl.updateAllCustomerDetails(cbde));

	}

	@Test
	public void testUpdateAllCustomerDetails() throws InvalidInputParametersException {

		EntityManager entityManager = mock(EntityManager.class);

		customerDaoImpl = new CustomerDaoImpl(customerBasicDetailsRepo, entityManager);
		dataInitilization();

		customerBasicDetailsRequest.setCustomerSequenceNumber(8L);
		customerBasicDetailsRequest.setApplicationId("112");
		customerBasicDetailsRequest.setTenantId("1124");
		customerBasicDetailsRequest.setUserId("2545");

		List<AddressRequest> addressRequestList = new ArrayList<>();

		addressRequest.setCustomerSequenceNumber(8L);
		addressRequest.setSequenceNumber(15);
		addressRequest.setAddressLine1("Mahalakshmi");
		addressRequest.setCity("USA");
		addressRequest.setState("AndhraPradesh");
		addressRequest.setCountry("India");
		addressRequest.setEmailId("lakshmisidharth678@gmail.com");
		addressRequest.setZipCode("87658");

		addressRequestList.add(addressRequest);

		customerManagementServiceRequest.setAddressRequestList(addressRequestList);

		CustomerBasicDetailsEntity customerBasicDetailsEntity = new CustomerBasicDetailsEntity();

		when(entityManager.merge(customerBasicDetailsEntity)).thenReturn(customerBasicDetailsEntity);

		// Call the method under test
		CustomerBasicDetailsEntity updatedEntity = customerDaoImpl.updateAllCustomerDetails(customerBasicDetailsEntity);

		assertNotNull(updatedEntity);

	}

	@Test
	public void testDeleteCustomerAssociatedDetails() {
		Long customerSequenceNumber = 1L;

		doNothing().when(customerBasicDetailsRepo).deleteById(customerSequenceNumber);

		customerDaoImpl.deleteCustomerAssociatedDetails(customerSequenceNumber);

		verify(customerBasicDetailsRepo, times(1)).deleteById(customerSequenceNumber);
	}

	@Test
	public void testGetAllCustomerData() {
		Long customerSequenceNumber = 1L;

		CustomerBasicDetailsEntity cbde = new CustomerBasicDetailsEntity();

		when(customerBasicDetailsRepo.findById(customerSequenceNumber)).thenReturn(Optional.of(cbde));

		customerDaoImpl.getCustomerDetails(customerSequenceNumber);

		verify(customerBasicDetailsRepo, times(1)).findById(customerSequenceNumber);
	}



}
