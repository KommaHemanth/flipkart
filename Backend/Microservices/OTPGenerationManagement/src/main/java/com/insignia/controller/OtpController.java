package com.insignia.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insignia.conistant.OtpConstants;
import com.insignia.constants.CommonConstant;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.model.AuthenticationRequest;
import com.insignia.model.AuthenticationResponse;
import com.insignia.serviceInterface.OtpServiceInterface;
import com.insignia.validations.CustomerBasicDetailsValidation;

@RestController
@CrossOrigin
public class OtpController {

	@Autowired
	private OtpServiceInterface otpServiceInterface;

	@PostMapping("/otpService")
	public ResponseEntity<?> generateAndSaveOtp(@RequestBody AuthenticationRequest authenticationRequest)
			throws MessagingException {
		try {
			//Validating user id same as email because in otp case, email id will be used as used id too.
			CustomerBasicDetailsValidation.ValidateCustomerEmail(authenticationRequest.getUserId(),
				OtpConstants.emaillength);
			CustomerBasicDetailsValidation.ValidateApplicationId(authenticationRequest.getApplicationId(),
					OtpConstants.applicationIdlength);
			CustomerBasicDetailsValidation.ValidateTenantId(authenticationRequest.getTenantId(),
					OtpConstants.tenantIdlength);
			CustomerBasicDetailsValidation.ValidateCustomerEmail(authenticationRequest.getEmail(),
				OtpConstants.emaillength);

			return ResponseEntity.ok(otpServiceInterface.generateAndSaveOtp(authenticationRequest));

		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AuthenticationResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AuthenticationResponse(CommonConstant.validateUnexpectedErrorCode,
							CommonConstant.validateUnexpectedErrorMessage));
		}
	}

}
