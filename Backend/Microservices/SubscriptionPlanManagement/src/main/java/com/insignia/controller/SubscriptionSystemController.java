package com.insignia.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insignia.constant.SubscriptionConstants;
import com.insignia.constants.CommonConstant;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.SubscriptionRequest;
import com.insignia.model.SubscriptionResponse;
import com.insignia.service.SubscriptionServiceInterface;
import com.insignia.validations.SubscriptionManagementValidation;

@CrossOrigin
@RestController
@RequestMapping("/subscription")
public class SubscriptionSystemController {

	@Autowired
	private SubscriptionServiceInterface subscriptionServiceInterface;
	
	@PostMapping("/saveSubscription")
	public ResponseEntity<?> saveSubscriptionPlan(@RequestBody SubscriptionRequest subscriptionRequest) {
	
		try {
			validationForSubscriptionManagement(subscriptionRequest);

			return ResponseEntity.ok(subscriptionServiceInterface.saveSubscriptionPlan(subscriptionRequest));
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SubscriptionResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SubscriptionResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SubscriptionResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));

		}

	}

	@PutMapping("/updateSubscription")
	public ResponseEntity<?> modifySubscriptionPlan(@RequestBody SubscriptionRequest subscriptionRequest) {
		try {
			validationForSubscriptionManagement(subscriptionRequest);

			return ResponseEntity.ok(subscriptionServiceInterface.updateSubscriptionPlan(subscriptionRequest));
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SubscriptionResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SubscriptionResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SubscriptionResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));

		}
	}

	@PostMapping("/getSubscriptionDetails")
	public ResponseEntity<?> getSubscriptionDetails(@RequestBody SubscriptionRequest subscriptionRequest) {
		try {
			return ResponseEntity.ok(subscriptionServiceInterface.getSubscriptionDetails(subscriptionRequest));
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SubscriptionResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SubscriptionResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SubscriptionResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));

		}

	}

	@PutMapping("/activateSubscriptionPlan")
	public ResponseEntity<?> activateSubscriptionPlan(@RequestBody SubscriptionRequest subscriptionRequest)
			throws ParseException {

		try {
			SubscriptionManagementValidation.ValidatePlanId(subscriptionRequest.getPlanId(),
					SubscriptionConstants.planIdLength);
			SubscriptionManagementValidation.ValidatePlanName(subscriptionRequest.getPlanName(),
					SubscriptionConstants.planNameLength);
			SubscriptionManagementValidation.validatePlanActivationDate(subscriptionRequest.getPlanActivationDate(),
					subscriptionRequest.getPlanActiveTill());
			
			return ResponseEntity.ok(subscriptionServiceInterface.activateSubscriptionPlan(subscriptionRequest));
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SubscriptionResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SubscriptionResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SubscriptionResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));

		}
	}

	@PutMapping("/deactivateSubscriptionPlan")
	public ResponseEntity<?> deactivateSubscriptionPlan(@RequestBody SubscriptionRequest subscriptionRequest) {

		try {
			SubscriptionManagementValidation.ValidatePlanId(subscriptionRequest.getPlanId(),
					SubscriptionConstants.planIdLength);
			SubscriptionManagementValidation.ValidatePlanName(subscriptionRequest.getPlanName(),
					SubscriptionConstants.planNameLength);
			SubscriptionManagementValidation.validatePlanDeActivationDate(subscriptionRequest.getPlanDeactivationDate(),
					SubscriptionConstants.planDeActivationDateLength);
			
			return ResponseEntity.ok(subscriptionServiceInterface.deactivateSubscriptionPlan(subscriptionRequest));
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SubscriptionResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SubscriptionResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SubscriptionResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));

		}

	}

	@PostMapping("/deleteSubscriptionDetails")
	public ResponseEntity<?> deleteSubscriptionPlanByPlanName(@RequestBody SubscriptionRequest subscriptionRequest) {

		try {
			subscriptionServiceInterface.deleteSubscriptionPlan(subscriptionRequest);
			return ResponseEntity.ok(SubscriptionConstants.SuccessMessageForDelete);
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SubscriptionResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SubscriptionResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SubscriptionResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));

		}
	}

	private void validationForSubscriptionManagement(SubscriptionRequest subscriptionRequest)
			throws InvalidInputParametersException {
		SubscriptionManagementValidation.ValidatePlanId(subscriptionRequest.getPlanId(),
				SubscriptionConstants.planIdLength);
		SubscriptionManagementValidation.ValidatePlanName(subscriptionRequest.getPlanName(),
				SubscriptionConstants.planNameLength);
		SubscriptionManagementValidation.validatePlanPricing(subscriptionRequest.getPlanPricing(),
				SubscriptionConstants.planPlanPricingLength);
		SubscriptionManagementValidation.ValidatePlanDescription(subscriptionRequest.getPlanDescription(),
				SubscriptionConstants.planPlanDescriptionLength);
		SubscriptionManagementValidation.validatePlanDuration(subscriptionRequest.getPlanDuration(),
				SubscriptionConstants.planPlanDurationLength);
		SubscriptionManagementValidation.validatePercentageDiscount(subscriptionRequest.getPercentageDiscount(), SubscriptionConstants.percentageDiscountLength);
	}
}
