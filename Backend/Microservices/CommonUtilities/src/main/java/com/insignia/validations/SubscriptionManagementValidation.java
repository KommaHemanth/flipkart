package com.insignia.validations;

import java.util.regex.Pattern;

import com.insignia.constants.SubscriptionManagementConstant;
import com.insignia.customExceptions.InvalidInputParametersException;

public class SubscriptionManagementValidation {

	public static void ValidatePlanId(String planId, int length) throws InvalidInputParametersException {

		if (planId == null || planId.trim().isEmpty()) {
			throw new InvalidInputParametersException(SubscriptionManagementConstant.validatePlanIdErrorCode,
					SubscriptionManagementConstant.validatePlanIdMessage);

		} else if (planId.length() > length) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validatePlanIdLengthMessageErrorCode,
					SubscriptionManagementConstant.validatePlanIdLengthMessage);

		}
	}

	public static void ValidatePlanName(String planName, int length) throws InvalidInputParametersException {

		if (planName == null || planName.trim().isEmpty()) {
			throw new InvalidInputParametersException(SubscriptionManagementConstant.validatePlanNameErrorCode,
					SubscriptionManagementConstant.validatePlanNameMessage);

		} else if (planName.length() > length) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validatePlanIdLengthMessageErrorCode,
					SubscriptionManagementConstant.validatePlanIdLengthMessage);

		}
	}
	
	public static void validatePlanActivationDate(String planActivationDate, String planTilldate)
			throws InvalidInputParametersException {

		if (planActivationDate == null || planActivationDate.trim().isEmpty() || planTilldate == null || planTilldate.trim().isEmpty()) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validatePlanActivationDateMessageErrorCode,
					SubscriptionManagementConstant.validatePlanActivationDateMessage);
		}

		if (!Pattern.compile(SubscriptionManagementConstant.regularExpressionForDateFormat).matcher(planActivationDate)
				.matches() || !Pattern.compile(SubscriptionManagementConstant.regularExpressionForDateFormat).matcher(planTilldate)
				.matches()) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validatePlanActivationDateCharactersErrorCode,
					SubscriptionManagementConstant.validatePlanActivationDateExpressionMessage);
		}
	}

	public static void validatePlanDeActivationDate(String planDeActivationDate, int length)
			throws InvalidInputParametersException {

		if (planDeActivationDate == null || planDeActivationDate.trim().isEmpty()) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validatePlanDeActivationDateMessageErrorCode,
					SubscriptionManagementConstant.validatePlanDeActivationDateMessage);
		}

		if (!Pattern.compile(SubscriptionManagementConstant.regularExpressionForDateFormat)
				.matcher(planDeActivationDate).matches()) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validatePlanDeActivationDateCharactersErrorCode,
					SubscriptionManagementConstant.validatePlanDeActivationDateExpressionMessage);
		}
	}

	public static void validatePlanPricing(Float planPricing, int length) throws InvalidInputParametersException {

		if (planPricing != null && String.valueOf(planPricing).length() > length) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validatePlanPricingLengthMessageErrorCode,
					SubscriptionManagementConstant.validatePlanPricingLengthMessage);
		}
	}

	public static void ValidatePlanDescription(String planDescription, int length)
			throws InvalidInputParametersException {

		if (planDescription != null && planDescription.length() > length) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validatePlanDescriptionLengthMessageErrorCode,
					SubscriptionManagementConstant.validatePlanDescriptionLengthMessage);

		}
	}

	public static void validatePlanDuration(Integer planDuration, int length) throws InvalidInputParametersException {

		if (planDuration != null && String.valueOf(planDuration).length() > length) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validatePlanDurationLengthMessageErrorCode,
					SubscriptionManagementConstant.validatePlanDurationLengthMessage);
		}
	}
	public static void validatePercentageDiscount(Float percentageDiscount, int length) throws InvalidInputParametersException {

		if (percentageDiscount != null && String.valueOf(percentageDiscount).length() > length) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validatePercentageDiscountLengthMessageErrorCode,
					SubscriptionManagementConstant.validatePercentageDiscountLengthMessage);
		}
	}


}
