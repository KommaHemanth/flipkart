package com.insignia.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.insignia.constants.CommonConstant;
import com.insignia.constants.CustomerBasicDetailsConstants;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.RoleNotFoundException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.entity.RolesAndPermissions;
import com.insignia.model.AuthenticationRequest;
import com.insignia.model.AuthenticationResponse;
import com.insignia.serviceInterface.IJwtService;
import com.insignia.userdetailsservice.CustomUserDetailsService;
import com.insignia.util.Constants;
import com.insignia.util.JwtUtil;
import com.insignia.validations.CustomerBasicDetailsValidation;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private IJwtService service;

	@Value("${errorCodes.500}")
	private String internalServerError;

	@Value("${errorCodes.403}")
	private String badCredentials;

	@Value("${errorCodes.406}")
	private String decryptionError;

	@Value("${errorCodes.407}")
	private String unexpectedError;

	@Value("${defaultTokenExpirationTime}")
	private Integer defaultTokenExpirationTime;

	@Autowired
	public RestTemplate restTemplate;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		AuthenticationResponse authResp = new AuthenticationResponse();
		UserDetails userDetails = null;
		String customUserName = null;

		try {

			if (authenticationRequest.getIsToValidatePassword()) {
				userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmailId());
				String userPassword = userDetailsService.getCustomerBasicDetailsEntity() != null
						? userDetailsService.getCustomerBasicDetailsEntity().getCustomerPassword()
						: "";
				authenticationRequest.setPassword(userPassword);
				authenticationRequest
						.setExpirationTime(defaultTokenExpirationTime != null ? defaultTokenExpirationTime : 15);
				customUserName = authenticationRequest.getEmailId();

			} else if (authenticationRequest.getIsOTPAuthentication()) {

				if (authenticationRequest.getoTP() == null) {
					throw new InvalidInputParametersException(Constants.OTP);

				} else if (String.valueOf(authenticationRequest.getoTP()).length() != 6) {
					throw new InvalidInputParametersException(Constants.OTPLength);

				} else {
					userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getCustomUserName());
					CustomerBasicDetailsEntity detailsLoadedFromDataBase = userDetailsService
							.getCustomerBasicDetailsEntity();

					otpDetailsValidations(detailsLoadedFromDataBase, authenticationRequest);
					authenticationRequest.setPassword(detailsLoadedFromDataBase.getCustomerPassword());
					authenticationRequest
							.setExpirationTime(defaultTokenExpirationTime != null ? defaultTokenExpirationTime : 15);

					customUserName = authenticationRequest.getCustomUserName();
				}

			} else {
				CustomerBasicDetailsValidation.ValidateUserId(authenticationRequest.getUserId(),
						Constants.userIdlength);

				CustomerBasicDetailsValidation.ValidateApplicationId(authenticationRequest.getApplicationId(),
						Constants.applicationIdlength);
				CustomerBasicDetailsValidation.ValidateTenantId(authenticationRequest.getTenantId(),
						Constants.tenantIdlength);

				authenticationRequest.setExpirationTime(
						authenticationRequest.getExpirationTime() != null ? authenticationRequest.getExpirationTime()
								: defaultTokenExpirationTime);
				userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getCustomUserName());
				customUserName = authenticationRequest.getCustomUserName();
			}

			CustomerBasicDetailsEntity customerBasicData = userDetailsService.getCustomerBasicDetailsEntity();
			if (userDetails != null && !authenticationRequest.getIsToValidatePassword() && customerBasicData != null
					&& !userDetails.getPassword().equalsIgnoreCase(authenticationRequest.getPassword())
					&& !(authenticationRequest.getIsOTPAuthentication() && authenticationRequest.getoTP() != null)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(new AuthenticationResponse(Constants.errorCode403, badCredentials));
			}
			if (customUserName == null)
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(new AuthenticationResponse(Constants.errorCode403, badCredentials));
			if (customerBasicData != null && customerBasicData.getCustomerSequenceNumber() != null) {
				authResp.setCustomerSeqNumber(customerBasicData.getCustomerSequenceNumber());
				service.fetchExistTokenIfPresent(authResp, authenticationRequest);
			}

			if (authResp != null && authResp.getTokenStatus() != null
					&& authResp.getTokenStatus().equalsIgnoreCase(Constants.errorCode403)) {

				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(new AuthenticationResponse(Constants.errorCode403, badCredentials));
			} else if (authResp != null && authResp.getTokenStatus() != null
					&& (authResp.getTokenStatus().equalsIgnoreCase(Constants.statusTokenLongLived)
							|| authResp.getTokenStatus().equalsIgnoreCase(Constants.statusTokenValid))) {
				authResp.setRolesAndPermissions(customerBasicData.getRolesAndPermissions().get(0));
				return ResponseEntity.ok(authResp);
			}

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(customUserName, authenticationRequest.getPassword()));

			final String jwt = jwtTokenUtil.generateToken(userDetails, authenticationRequest.getExpirationTime());
			authResp.setToken(jwt);
			service.updateTokenDetails(authenticationRequest.getExpirationTime(),
					authenticationRequest.isRememberMeSelected(), jwt, authResp.getCustomerSeqNumber());
			RolesAndPermissions rolesAndPermissions = null;
			if (customerBasicData != null && !customerBasicData.getRolesAndPermissions().isEmpty()
					&& customerBasicData.getRolesAndPermissions().get(0) != null) {
				rolesAndPermissions = customerBasicData.getRolesAndPermissions().get(0);
			}

			return ResponseEntity.ok(new AuthenticationResponse(jwt, Constants.tokenType,
					jwtTokenUtil.extractExpiration(jwt), rolesAndPermissions));

		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AuthenticationResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (BadCredentialsException | UsernameNotFoundException e) {

			if (e.getCause() != null && e.getCause() instanceof RoleNotFoundException) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(new AuthenticationResponse(CustomerBasicDetailsConstants.validateRoleErrorCode,
								CustomerBasicDetailsConstants.validateRole));
			}

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new AuthenticationResponse(Constants.errorCode403, badCredentials));

		} catch (Exception e) {

			if (e.getClass() != null && (e.getClass().equals(InvalidKeyException.class)
					|| e.getClass().equals(NoSuchAlgorithmException.class)
					|| e.getClass().equals(NoSuchPaddingException.class)
					|| e.getClass().equals(IllegalBlockSizeException.class)
					|| e.getClass().equals(BadPaddingException.class))) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new AuthenticationResponse(Constants.errorCode406, decryptionError));
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new AuthenticationResponse(Constants.errorCode407, unexpectedError));
		}

	}

	private void otpDetailsValidations(CustomerBasicDetailsEntity detailsLoadedFromDataBase,
			AuthenticationRequest authenticationRequest) throws InvalidInputParametersException {

		if (detailsLoadedFromDataBase.getOtp() != null
				&& !detailsLoadedFromDataBase.getOtp().equalsIgnoreCase(authenticationRequest.getoTP())) {

			throw new InvalidInputParametersException(CustomerBasicDetailsConstants.validateInvalidOtpErrorCode,
					CustomerBasicDetailsConstants.validateInvalidOtp);

		}

		if (detailsLoadedFromDataBase.getOtp() != null && detailsLoadedFromDataBase.getOtpExpiryAt() != null
				&& detailsLoadedFromDataBase.getOtpExpiryAt().compareTo(new Date()) < 0) {

			throw new InvalidInputParametersException(CustomerBasicDetailsConstants.validateOTPExpiredErrorCode,
					CustomerBasicDetailsConstants.validateOTPExpired);

		}

	}

	@PutMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody AuthenticationRequest authenticationRequest)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		try {

			CustomerBasicDetailsValidation.ValidateNewPassword(authenticationRequest.getNewPassword(),
					Constants.passwordMaxlength, Constants.passwordMinlength);

			service.updatePassword(authenticationRequest);

			return ResponseEntity.ok(Constants.successMessageForUpdate);

		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AuthenticationResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AuthenticationResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthenticationResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}

	}

	@GetMapping("/checkTokenValidity/{customerSequenceNumber}")
	public ResponseEntity<?> checkTokenValidity(@PathVariable Long customerSequenceNumber) {
		try {

			return ResponseEntity.ok(service.checkTokenValidity(customerSequenceNumber));

		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AuthenticationResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthenticationResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}

	}

}
