package com.insignia.daoInterface;

import java.util.List;
import java.util.Optional;

import com.insignia.entity.SubscriptionDetails;

public interface SubscriptionDaoInterface {

	public SubscriptionDetails saveSubscriptionPlan(SubscriptionDetails subscriptionDetails);

	public SubscriptionDetails updateSubscriptionPlan(SubscriptionDetails subscriptionDetails);

	public void deleteSubscriptionPlan(Long sequenceNumber);

	public SubscriptionDetails activateSubscriptionPlan(SubscriptionDetails subscriptionDetails);

	public SubscriptionDetails deactivateSubscriptionPlan(SubscriptionDetails subscriptionDetails);

	public List<SubscriptionDetails> findAllSubscriptionDetails();

	public Optional<SubscriptionDetails> findBySequenceNumber(Long sequenceNumber);

	public Optional<SubscriptionDetails> findByPlanIdAndPlanName(String planId, String planName);

	public void deleteSubscriptionPlanByName(String planId, String planName);
}
