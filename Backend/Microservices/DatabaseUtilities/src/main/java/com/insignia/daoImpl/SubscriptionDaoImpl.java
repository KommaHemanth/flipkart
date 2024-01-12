package com.insignia.daoImpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insignia.daoInterface.SubscriptionDaoInterface;
import com.insignia.entity.SubscriptionDetails;
import com.insignia.repo.UserSubscriptionRepository;

@Repository
@Transactional
public class SubscriptionDaoImpl implements SubscriptionDaoInterface {

	@Autowired
	private UserSubscriptionRepository userRepo;

	@Autowired
	private EntityManager entityManager;

	@Override
	public SubscriptionDetails saveSubscriptionPlan(SubscriptionDetails subscriptionDetails) {
		return userRepo.save(subscriptionDetails);

	}

	@Override
	public SubscriptionDetails updateSubscriptionPlan(SubscriptionDetails subscriptionDetails) {
		return entityManager.merge(subscriptionDetails);

	}

	@Override
	public void deleteSubscriptionPlan(Long sequenceNumber) {
		userRepo.deleteById(sequenceNumber);
	}

	@Override
	public List<SubscriptionDetails> findAllSubscriptionDetails() {
		return userRepo.findAll();
	}

	@Override
	public Optional<SubscriptionDetails> findBySequenceNumber(Long sequenceNumber) {
		return userRepo.findById(sequenceNumber);
	}

	@Override
	public SubscriptionDetails deactivateSubscriptionPlan(SubscriptionDetails subscriptionDetails) {
		userRepo.deactivateSubscriptionPlan(subscriptionDetails.getPlanId(), subscriptionDetails.getPlanName(),
				subscriptionDetails.getPlanDeactivationDate());
		return subscriptionDetails;
	}

	@Override
	public SubscriptionDetails activateSubscriptionPlan(SubscriptionDetails subscriptionDetails) {
		userRepo.activateSubscriptionPlan(subscriptionDetails.getPlanId(), subscriptionDetails.getPlanName(),
				subscriptionDetails.getPlanActivationDate());
		return subscriptionDetails;

	}

	@Override
	public void deleteSubscriptionPlanByName(String planId, String planName) {
		userRepo.deleteSubscriptionPlanByName(planId, planName);

	}

	@Override
	public Optional<SubscriptionDetails> findByPlanIdAndPlanName(String planId, String planName) {
		
		return userRepo.findByPlanIdAndName(planId, planName);
	}

}
