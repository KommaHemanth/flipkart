package com.insignia.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insignia.constants.SubscriptionManagementConstant;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.SubscriptionDaoInterface;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.entity.SubscriptionDetails;
import com.insignia.model.PlanStatus;
import com.insignia.model.SubscriptionRequest;
import com.insignia.model.SubscriptionResponse;
import com.insignia.service.SubscriptionServiceInterface;

@Service
public class SubscriptionServiceImpl implements SubscriptionServiceInterface {

	@Autowired
	private SubscriptionDaoInterface subscriptionDao;

	@Autowired
	private TokenDaoInterface tokenDao;

	@Transactional
	@Override
	public SubscriptionResponse saveSubscriptionPlan(SubscriptionRequest subscriptionRequest)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(subscriptionRequest.getCustomerSequenceNumber(),
				subscriptionRequest.getExpirationDuration());

		Optional<SubscriptionDetails> subscriptionDetailsList = subscriptionDao
				.findByPlanIdAndPlanName(subscriptionRequest.getPlanId(), subscriptionRequest.getPlanName());

		if (subscriptionDetailsList.isPresent()) {
			SubscriptionDetails subscriptionDetails = subscriptionDetailsList.get();

			String activationStatus = subscriptionDetails.getPlanActivationStatus();

			if (PlanStatus.NEW.name().equalsIgnoreCase(activationStatus)) {
				throw new InvalidInputParametersException(
						SubscriptionManagementConstant.validateSubscriptionDetailsDuplicateDataMessageErrorCode,
						SubscriptionManagementConstant.validateSubscriptionDetailsDuplicateDataMessage);
			} else if (PlanStatus.ACTIVE.name().equalsIgnoreCase(activationStatus)) {
				throw new InvalidInputParametersException(
						SubscriptionManagementConstant.validateSubscriptionDetailsDuplicateDataActivateStateMessageErrorCode,
						SubscriptionManagementConstant.validateSubscriptionDetailsDuplicateDataActivateStateMessage);
			} else if (PlanStatus.DEACTIVE.name().equalsIgnoreCase(activationStatus)) {
				throw new InvalidInputParametersException(
						SubscriptionManagementConstant.validateSubscriptionDetailsDuplicateDataDe_ActivateStateMessageErrorCode,
						SubscriptionManagementConstant.validateSubscriptionDetailsDuplicateDataDe_ActivateStateMessage);
			}
		}

		SubscriptionDetails subscriptionDetails = new SubscriptionDetails();
		subscriptionDetails.setPlanId(subscriptionRequest.getPlanId());
		subscriptionDetails.setPlanName(subscriptionRequest.getPlanName());
		subscriptionDetails.setPlanActivationStatus(PlanStatus.NEW.name());
		subscriptionDetails.setPlanDescription(subscriptionRequest.getPlanDescription());
		subscriptionDetails.setPlanDuration(subscriptionRequest.getPlanDuration());
		subscriptionDetails.setPlanPricing(subscriptionRequest.getPlanPricing());
		subscriptionDetails.setPercentageDiscount(subscriptionRequest.getPercentageDiscount());

		SubscriptionDetails createdSubscriptionDetails = subscriptionDao.saveSubscriptionPlan(subscriptionDetails);
		SubscriptionResponse subscriptionResponse = createResponseForSubscriptionEntity(createdSubscriptionDetails);

		return subscriptionResponse;
	}

	@Transactional
	@Override
	public SubscriptionResponse updateSubscriptionPlan(SubscriptionRequest subscriptionRequest)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(subscriptionRequest.getCustomerSequenceNumber(),
				subscriptionRequest.getExpirationDuration());
		
		Optional<SubscriptionDetails> subscriptionDetailsList = subscriptionDao
				.findBySequenceNumber(subscriptionRequest.getSequenceNumber());

		if (subscriptionDetailsList.isEmpty()) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validateSubscriptionDetailsNotFoundErrorCode,
					SubscriptionManagementConstant.validateSubscriptionDetailsNotFoundMessage);
		}

		SubscriptionDetails subscriptionDetails = subscriptionDetailsList.get();
		String activationStatus = subscriptionDetails.getPlanActivationStatus();

		if (PlanStatus.ACTIVE.name().equalsIgnoreCase(activationStatus)) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validateSubscriptionUpdateActivateStateErrorCode,
					SubscriptionManagementConstant.validateSubscriptionUpdatesActivateStateMessage);

		} else if (PlanStatus.DEACTIVE.name().equalsIgnoreCase(activationStatus)) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validateSubscriptionUpdateDeActivateStateErrorCode,
					SubscriptionManagementConstant.validateSubscriptionUpdatesDeActivateStateMessage);
		} else {

			if (subscriptionRequest.isUpdatedPlanId()) {
				subscriptionDetails.setPlanId(subscriptionRequest.getPlanId());
			}
			if (subscriptionRequest.isUpdatedPlanName()) {
				subscriptionDetails.setPlanName(subscriptionRequest.getPlanName());
			}
			if (subscriptionRequest.isUpdatedPlanDescription()) {
				subscriptionDetails.setPlanDescription(subscriptionRequest.getPlanDescription());
			}
			if (subscriptionRequest.isUpdatedPlanDuration()) {
				subscriptionDetails.setPlanDuration(subscriptionRequest.getPlanDuration());
			}
			if (subscriptionRequest.isUpdatedPlanPricing()) {
				subscriptionDetails.setPlanPricing(subscriptionRequest.getPlanPricing());
			}
			if (subscriptionRequest.isUpdatedPercentageDiscount()) {
				subscriptionDetails.setPercentageDiscount(subscriptionRequest.getPercentageDiscount());
			}

		}
		SubscriptionDetails getSubscriptionDetails = subscriptionDao.updateSubscriptionPlan(subscriptionDetails);

		SubscriptionResponse subscriptionResponse = createResponseForSubscriptionEntity(getSubscriptionDetails);
		return subscriptionResponse;
	}

	@Transactional
	@Override
	public Optional<List<SubscriptionResponse>> getSubscriptionDetails(SubscriptionRequest subscriptionRequest) throws InvalidInputParametersException, TokenExpiredException {
		tokenDao.checkTokenValidity(subscriptionRequest.getCustomerSequenceNumber(), subscriptionRequest.getExpirationDuration());
		
		List<SubscriptionResponse> responseList = new ArrayList<>();
		if (subscriptionRequest.getSequenceNumber() != null) {
			Optional<SubscriptionDetails> optionalSubscriptionDetails = subscriptionDao
					.findBySequenceNumber(subscriptionRequest.getSequenceNumber());

			if (optionalSubscriptionDetails.isPresent()) {
				SubscriptionDetails subscriptionDetails = optionalSubscriptionDetails.get();
				responseList.add(createResponseForSubscriptionEntity(subscriptionDetails));
			}
		} else {
			List<SubscriptionDetails> subscriptionDetailsList = subscriptionDao.findAllSubscriptionDetails();

			if (!subscriptionDetailsList.isEmpty()) {

				for (SubscriptionDetails subscriptionDetails : subscriptionDetailsList) {
					responseList.add(createResponseForSubscriptionEntity(subscriptionDetails));
				}
			}
		}
		return Optional.ofNullable(responseList);
	}
	
	@Transactional
	@Override
	public SubscriptionResponse activateSubscriptionPlan(SubscriptionRequest subscriptionRequest)
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		tokenDao.checkTokenValidity(subscriptionRequest.getCustomerSequenceNumber(),
				subscriptionRequest.getExpirationDuration());
		SubscriptionResponse response = null;

		Optional<SubscriptionDetails> subscriptionDetailsList = subscriptionDao
				.findByPlanIdAndPlanName(subscriptionRequest.getPlanId(), subscriptionRequest.getPlanName());

		if (!subscriptionDetailsList.isPresent()) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validateSubscriptionDetailsNotFoundErrorCode,
					SubscriptionManagementConstant.validateSubscriptionDetailsNotFoundMessage);
		}
		SubscriptionDetails subscriptionDetails = subscriptionDetailsList.get();
		if (PlanStatus.ACTIVE.name().equalsIgnoreCase(subscriptionDetails.getPlanActivationStatus())) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validateSubscriptionActivateStateErrorCode,
					SubscriptionManagementConstant.validateSubscriptionActivateStateMessage);
		}

		if (subscriptionRequest.isActivationDateUpdated()) {
			Date activationDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
					.parse(subscriptionRequest.getPlanActivationDate());

			Date planActiveTill = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
					.parse(subscriptionRequest.getPlanActiveTill());

			if (planActiveTill.compareTo(activationDate) <= 0) {
				throw new InvalidInputParametersException(
						SubscriptionManagementConstant.validateSubscriptionDateComparisionErrorCode,
						SubscriptionManagementConstant.validateActiveTillDateAfterActivationDateMessage);
			}

			subscriptionDetails.setPlanActivationDate(activationDate);
			subscriptionDetails.setPlanActivationStatus(PlanStatus.ACTIVE.name());
			subscriptionDetails.setPlanActiveTill(planActiveTill);
			subscriptionDetails.setPlanDeactivationDate(null);

			SubscriptionDetails activateSubscriptionPlan = subscriptionDao
					.activateSubscriptionPlan(subscriptionDetails);
			response = createResponseForSubscriptionEntity(activateSubscriptionPlan);
		}

		return response;
	}

	@Transactional
	@Override
	public SubscriptionResponse deactivateSubscriptionPlan(SubscriptionRequest subscriptionRequest)
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		tokenDao.checkTokenValidity(subscriptionRequest.getCustomerSequenceNumber(),
				subscriptionRequest.getExpirationDuration());

		SubscriptionResponse response = null;
		Optional<SubscriptionDetails> subscriptionDetailsList = subscriptionDao
				.findByPlanIdAndPlanName(subscriptionRequest.getPlanId(), subscriptionRequest.getPlanName());

		if (!subscriptionDetailsList.isPresent()) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validateSubscriptionDetailsNotFoundErrorCode,
					SubscriptionManagementConstant.validateSubscriptionDetailsNotFoundMessage);
		}

		SubscriptionDetails subscriptionDetails = subscriptionDetailsList.get();
		String activationStatus = subscriptionDetails.getPlanActivationStatus();

		if (subscriptionDetails.getPlanActivationDate() == null || PlanStatus.NEW.name().equals(activationStatus)) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validateSubscriptionDetailsNoActivationPlanErrorCode,
					SubscriptionManagementConstant.validateSubscriptionDetailsNoActivationPlanMessage);
		}

		if (PlanStatus.DEACTIVE.name().equalsIgnoreCase(activationStatus)) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validateSubscriptionDetailsDe_ActivateStateMessageErrorCode,
					SubscriptionManagementConstant.validateSubscriptionDetailsDe_ActivateStateMessage);
		}
		Date deActivationDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
				.parse(subscriptionRequest.getPlanDeactivationDate());

		if (deActivationDate.compareTo(subscriptionDetails.getPlanActivationDate()) < 0) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validateSubscriptionDateComparisionErrorCode,
					SubscriptionManagementConstant.validateSubscriptionDateComparisionMessage);
		}

		if (deActivationDate.compareTo(subscriptionDetails.getPlanActiveTill()) < 0) {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validateSubscriptionDateComparisionErrorCode,
					SubscriptionManagementConstant.validateSubscriptionDateComparisionForActiveTillMessage);
		}
		if (subscriptionRequest.isDeactivationDateUpdated()) {

			subscriptionDetails.setPlanDeactivationDate(deActivationDate);
			subscriptionDetails.setPlanActivationStatus(PlanStatus.DEACTIVE.name());

			SubscriptionDetails deactivateSubscriptionPlan = subscriptionDao
					.deactivateSubscriptionPlan(subscriptionDetails);
			response = createResponseForSubscriptionEntity(deactivateSubscriptionPlan);

		}

		return response;
	}

	@Transactional
	@Override
	public void deleteSubscriptionPlan(SubscriptionRequest subscriptionRequest)
			throws TokenExpiredException, InvalidInputParametersException {

		tokenDao.checkTokenValidity(subscriptionRequest.getCustomerSequenceNumber(),
				subscriptionRequest.getExpirationDuration());

		
		if (subscriptionRequest.getSequenceNumber() != null) {
			Optional<SubscriptionDetails> optionalSubscriptionDetails = subscriptionDao
					.findBySequenceNumber(subscriptionRequest.getSequenceNumber());

			if (optionalSubscriptionDetails.isEmpty()) {
				throw new InvalidInputParametersException(
						SubscriptionManagementConstant.validateSubscriptionDetailsNotFoundErrorCode,
						SubscriptionManagementConstant.validateSubscriptionDetailsNotFoundMessage);
			}

			SubscriptionDetails subscriptionDetails = optionalSubscriptionDetails.get();
			String activationStatus = subscriptionDetails.getPlanActivationStatus();

			if (PlanStatus.ACTIVE.name().equalsIgnoreCase(activationStatus)) {
				throw new InvalidInputParametersException(
						SubscriptionManagementConstant.validateSubscriptionDeleteActivateStateErrorCode,
						SubscriptionManagementConstant.validateSubscriptionDeleteActivateStateMessage);
			}

			subscriptionDao.deleteSubscriptionPlan(subscriptionRequest.getSequenceNumber());
		} else if (subscriptionRequest.getPlanId() != null && subscriptionRequest.getPlanName() != null) {

			Optional<SubscriptionDetails> existingDetailsByPlanIdAndPlanName = subscriptionDao
					.findByPlanIdAndPlanName(subscriptionRequest.getPlanId(), subscriptionRequest.getPlanName());

			if (existingDetailsByPlanIdAndPlanName.isEmpty()) {
				throw new InvalidInputParametersException(
						SubscriptionManagementConstant.validateSubscriptionDetailsNotFoundErrorCode,
						SubscriptionManagementConstant.validateSubscriptionDetailsNotFoundMessage);
			}

			SubscriptionDetails subscriptionDetails = existingDetailsByPlanIdAndPlanName.get();
			String activationStatusForSubscription = subscriptionDetails.getPlanActivationStatus();

			if (PlanStatus.ACTIVE.name().equalsIgnoreCase(activationStatusForSubscription)) {
				throw new InvalidInputParametersException(
						SubscriptionManagementConstant.validateSubscriptionDeleteActivateStateErrorCode,
						SubscriptionManagementConstant.validateSubscriptionDeleteActivateStateMessage);
			}
			subscriptionDao.deleteSubscriptionPlanByName(subscriptionRequest.getPlanId(),
					subscriptionRequest.getPlanName());
		}else {
			throw new InvalidInputParametersException(
					SubscriptionManagementConstant.validateSubscriptionDeleteErrorCode,
					SubscriptionManagementConstant.validateSubscriptionDeleteMessage);
		}
	}

	private SubscriptionResponse createResponseForSubscriptionEntity(SubscriptionDetails getSubscriptionDetails) {
		SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
		subscriptionResponse.setSequenceNumber(getSubscriptionDetails.getSequenceNumber());
		subscriptionResponse.setPlanId(getSubscriptionDetails.getPlanId());
		subscriptionResponse.setPlanName(getSubscriptionDetails.getPlanName());
		subscriptionResponse.setPlanDescription(getSubscriptionDetails.getPlanDescription());
		subscriptionResponse.setPlanDuration(getSubscriptionDetails.getPlanDuration());
		subscriptionResponse.setPlanPricing(getSubscriptionDetails.getPlanPricing());
		subscriptionResponse.setPlanActivationDate(getSubscriptionDetails.getPlanActivationDate());
		subscriptionResponse.setPlanDeactivationDate(getSubscriptionDetails.getPlanDeactivationDate());
		subscriptionResponse.setPlanActivationStatus(getSubscriptionDetails.getPlanActivationStatus());
		subscriptionResponse.setPercentageDiscount(getSubscriptionDetails.getPercentageDiscount());
		subscriptionResponse.setPlanActiveTill(getSubscriptionDetails.getPlanActiveTill());
		return subscriptionResponse;
	}

}
