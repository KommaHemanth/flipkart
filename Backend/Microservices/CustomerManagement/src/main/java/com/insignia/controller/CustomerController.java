package com.insignia.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insignia.constant.CustomerManagementConstants;
import com.insignia.constants.CommonConstant;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.AddressRequest;
import com.insignia.model.AddressResponse;
import com.insignia.model.CustomerManagementServiceRequest;
import com.insignia.model.CustomerManagementServiceResponse;
import com.insignia.model.CustomerPersonalDetailsRequest;
import com.insignia.model.CustomerStoreDetailsRequest;
import com.insignia.model.RolesAndPermissionsRequest;
import com.insignia.serviceInterface.CustomerServiceInterface;
import com.insignia.validations.AddressDetailsValidation;
import com.insignia.validations.CustomerBasicDetailsValidation;
import com.insignia.validations.CustomerPersonalDetailsValidation;
import com.insignia.validations.CustomerStoreDetailsValidation;
import com.insignia.validations.RolesAndPermissonsValidations;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerServiceInterface serviceRepo;

	@PostMapping("/saveCustomerDetails")
	public ResponseEntity<?> saveAllCustomerDetails(
			@RequestBody CustomerManagementServiceRequest customerManagementDetails) {
		try {

			validateCustomerAllDetails(customerManagementDetails);
			CustomerBasicDetailsValidation.ValidatePassword(
					customerManagementDetails.getCustomerBasicDetailsRequest().getPassword(),
					CustomerManagementConstants.passwordlength);

			return ResponseEntity.ok(serviceRepo.saveAllCustomerDetails(customerManagementDetails));

		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomerManagementServiceResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerManagementServiceResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	@PutMapping("/updateCustomerDetails")
	public ResponseEntity<?> updateAllCustomerDetails(
			@RequestBody CustomerManagementServiceRequest customerManagementDetails) {

		try {
			validateCustomerAllDetails(customerManagementDetails);

			return ResponseEntity.ok(serviceRepo.updateAllCustomerDetails(customerManagementDetails));

		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomerManagementServiceResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AddressResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerManagementServiceResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	@DeleteMapping("/deleteCustomerDetails/{customerSequenceNumber}/{expirationDuration}")
	public ResponseEntity<?> deleteCustomerAssociatedDetails(@PathVariable Long customerSequenceNumber,
			@PathVariable Integer expirationDuration) {
		try {

			serviceRepo.deleteCustomerAssociatedDetails(customerSequenceNumber, expirationDuration);

			return ResponseEntity.ok("Record Successfully Deleted");

		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AddressResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerManagementServiceResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}

	}

	@GetMapping("/getCustomerDetails/{customerSequenceNumber}/{expirationDuration}")
	public ResponseEntity<?> getCustomerDetails(@PathVariable Long customerSequenceNumber,
			@PathVariable Integer expirationDuration) {
		try {

			return ResponseEntity.ok(serviceRepo.getCustomerDetails(customerSequenceNumber, expirationDuration));

		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AddressResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerManagementServiceResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	@GetMapping("/getAllCustomerData/{customerSequenceNumber}/{expirationDuration}")
	public ResponseEntity<?> getAllCustomerData(@PathVariable Long customerSequenceNumber,
			@PathVariable Integer expirationDuration) {
		try {

			return ResponseEntity.ok(serviceRepo.getAllCustomerData(customerSequenceNumber, expirationDuration));

		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AddressResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerManagementServiceResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	@GetMapping("/getCustomerAndStoreInformation/{customerSequenceNumber}/{expirationDuration}")
	public ResponseEntity<?> getCustomerAndStoreInformation(@PathVariable Long customerSequenceNumber,
			@PathVariable Integer expirationDuration) throws ParseException {
		try {

			return ResponseEntity
					.ok(serviceRepo.getCustomerAndStoreInformation(customerSequenceNumber, expirationDuration));

		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AddressResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomerManagementServiceResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	private void validateCustomerAllDetails(CustomerManagementServiceRequest customerManagementDetails)
			throws InvalidInputParametersException {

		validateCustomerBasicDetails(customerManagementDetails);

		validateAddressDetails(customerManagementDetails);

		validateCustomerPersonalDetails(customerManagementDetails);

		validateRolesAndPermissions(customerManagementDetails);

		validateCustomerStoreDetails(customerManagementDetails);

	}

	private void validateCustomerStoreDetails(CustomerManagementServiceRequest customerManagementDetails)
			throws InvalidInputParametersException {

		if (customerManagementDetails.getCustomerStoreDetailsRequestList() != null) {

			for (CustomerStoreDetailsRequest customerStoreDetailsRequest : customerManagementDetails
					.getCustomerStoreDetailsRequestList()) {

				CustomerStoreDetailsValidation.ValidateMarkupFactor(customerStoreDetailsRequest.getMarkupFactor(),
						CustomerManagementConstants.markupFactorLength);
				CustomerStoreDetailsValidation.ValidateHearAboutUs(customerStoreDetailsRequest.getHearAboutUs(),
						CustomerManagementConstants.hearAboutUsLength);
				CustomerStoreDetailsValidation.ValidateMarkets(customerStoreDetailsRequest.getMarkets(),
						CustomerManagementConstants.marketsLength);
				CustomerStoreDetailsValidation.ValidateStoreName(customerStoreDetailsRequest.getStoreName(),
						CustomerManagementConstants.storeNameLength);
				CustomerStoreDetailsValidation.ValidateStoreContact(customerStoreDetailsRequest.getStoreContact(),
						CustomerManagementConstants.storeContactLength);
				CustomerStoreDetailsValidation.ValidateStoreAddress(customerStoreDetailsRequest.getStoreAddress(),
						CustomerManagementConstants.storeAddressLength);
				CustomerStoreDetailsValidation.ValidateStoreCountry(customerStoreDetailsRequest.getStoreCountry(),
						CustomerManagementConstants.storeCountryLength);
				CustomerStoreDetailsValidation.ValidateStoreState(customerStoreDetailsRequest.getStoreState(),
						CustomerManagementConstants.storeStateLength);
				CustomerStoreDetailsValidation.ValidateStoreCity(customerStoreDetailsRequest.getStoreCity(),
						CustomerManagementConstants.storeCityLength);
				CustomerStoreDetailsValidation.ValidateStoreZipCode(customerStoreDetailsRequest.getStoreZipCode(),
						CustomerManagementConstants.storeZipCodeLength);
				CustomerStoreDetailsValidation.ValidateTelephone(customerStoreDetailsRequest.getTelephone(),
						CustomerManagementConstants.storeTelephoneLength);
				CustomerStoreDetailsValidation.ValidateWebsite(customerStoreDetailsRequest.getWebsite(),
						CustomerManagementConstants.storeWebsiteLength);
				CustomerStoreDetailsValidation.ValidateResaleLicense(customerStoreDetailsRequest.getResaleLicense(),
						CustomerManagementConstants.storeResaleLicenseLength);
				CustomerStoreDetailsValidation.ValidateBusinessType(customerStoreDetailsRequest.getBusinessType(),
						CustomerManagementConstants.storeBusinessTypeLength);
			}
		}
	}

	private void validateRolesAndPermissions(CustomerManagementServiceRequest customerManagementDetails)
			throws InvalidInputParametersException {

		if (customerManagementDetails.getRolesAndPermissionsRequestList() != null) {

			for (RolesAndPermissionsRequest rolesAndPermissionsRequest : customerManagementDetails
					.getRolesAndPermissionsRequestList()) {

				RolesAndPermissonsValidations.ValidateRoleName(rolesAndPermissionsRequest.getRoleName(),
						CustomerManagementConstants.roleNameLength);
				RolesAndPermissonsValidations.ValidateUpdatePermissions(
						rolesAndPermissionsRequest.getUpdatedPermissions(),
						CustomerManagementConstants.updatePermissionsLength);

			}
		}
	}

	private void validateCustomerPersonalDetails(CustomerManagementServiceRequest customerManagementDetails)
			throws InvalidInputParametersException {

		if (customerManagementDetails.getCustomerPersonalDetailsRequestList() != null) {

			for (CustomerPersonalDetailsRequest customerPersonalRequest : customerManagementDetails
					.getCustomerPersonalDetailsRequestList()) {

				CustomerPersonalDetailsValidation.ValidateFirstName(customerPersonalRequest.getFirstName(),
						CustomerManagementConstants.firstNameLength);
				CustomerPersonalDetailsValidation.ValidateLastName(customerPersonalRequest.getLastName(),
						CustomerManagementConstants.lastNameLength);
				CustomerPersonalDetailsValidation.ValidateMiddleName(customerPersonalRequest.getMiddleName(),
						CustomerManagementConstants.middleNameLength);
				CustomerPersonalDetailsValidation.ValidateAge(customerPersonalRequest.getAge(),
						CustomerManagementConstants.ageLength);
				CustomerPersonalDetailsValidation.ValidateGender(customerPersonalRequest.getGender(),
						CustomerManagementConstants.genderLength);
				CustomerPersonalDetailsValidation.ValidateCustomerEmailId(customerPersonalRequest.getCustomerEmailId(),
						CustomerManagementConstants.customerEmailIdLength);
				CustomerPersonalDetailsValidation.ValidateAlternativeEmailId(
						customerPersonalRequest.getAlternativeEmailId(),
						CustomerManagementConstants.alternativeEmailIdLength);
				CustomerPersonalDetailsValidation.ValidateCustomeMobileNumber(
						customerPersonalRequest.getCustomerMobileNumber(),
						CustomerManagementConstants.customerMobileNumberLength);
				CustomerPersonalDetailsValidation.ValidateAlternativeMobileNumber(
						customerPersonalRequest.getAlternativeMobileNumber(),
						CustomerManagementConstants.alternativeMobileNumberLength);
				CustomerPersonalDetailsValidation.ValidateCustomerLandlineNumber(
						customerPersonalRequest.getCustomerLandlineNumber(),
						CustomerManagementConstants.customerLandlineNumberLength);

			}
		}
	}

	private void validateAddressDetails(CustomerManagementServiceRequest customerManagementDetails)
			throws InvalidInputParametersException {
		if (customerManagementDetails.getAddressRequestList() != null) {

			for (AddressRequest addressRequest : customerManagementDetails.getAddressRequestList()) {

				AddressDetailsValidation.ValidateAddressLine1(addressRequest.getAddressLine1(),
						CustomerManagementConstants.addresLine1length);
				AddressDetailsValidation.ValidateCity(addressRequest.getCity(), CustomerManagementConstants.cityLength);
				AddressDetailsValidation.ValidateLandmark(addressRequest.getLandmark(),
						CustomerManagementConstants.landMarkLength);
				AddressDetailsValidation.ValidateState(addressRequest.getState(),
						CustomerManagementConstants.stateLength);
				AddressDetailsValidation.ValidateCountry(addressRequest.getCountry(),
						CustomerManagementConstants.countryLength);
				AddressDetailsValidation.ValidateZipCode(addressRequest.getZipCode(),
						CustomerManagementConstants.zipCodeLength);
				AddressDetailsValidation.ValidateAddressLine2(addressRequest.getAddressLine2(),
						CustomerManagementConstants.addresLine2length);
				AddressDetailsValidation.ValidateEmailId(addressRequest.getEmailId(),
						CustomerManagementConstants.emailIdLength);
				AddressDetailsValidation.ValidateMobileNumber(addressRequest.getMobileNumber(),
						CustomerManagementConstants.mobilenumberLength);
				AddressDetailsValidation.ValidateLandLineNumber(addressRequest.getLandlineNumber(),
						CustomerManagementConstants.landlineNumberLength);
			}
		}
	}

	private void validateCustomerBasicDetails(CustomerManagementServiceRequest customerManagementDetails)
			throws InvalidInputParametersException {

		if (customerManagementDetails.getCustomerBasicDetailsRequest() != null) {

			CustomerBasicDetailsValidation.ValidateUserId(
					customerManagementDetails.getCustomerBasicDetailsRequest().getUserId(),
					CustomerManagementConstants.userIdlength);
			CustomerBasicDetailsValidation.ValidateApplicationId(
					customerManagementDetails.getCustomerBasicDetailsRequest().getApplicationId(),
					CustomerManagementConstants.applicationIdlength);
			CustomerBasicDetailsValidation.ValidateTenantId(
					customerManagementDetails.getCustomerBasicDetailsRequest().getTenantId(),
					CustomerManagementConstants.tenantIdlength);

			CustomerBasicDetailsValidation.ValidateCustomerEmail(
					customerManagementDetails.getCustomerBasicDetailsRequest().getEmailId(),
					CustomerManagementConstants.customerEmailLength);
			CustomerBasicDetailsValidation.ValidateUsername(
					customerManagementDetails.getCustomerBasicDetailsRequest().getUserName(),
					CustomerManagementConstants.userNameLength);
		}
	}
}
