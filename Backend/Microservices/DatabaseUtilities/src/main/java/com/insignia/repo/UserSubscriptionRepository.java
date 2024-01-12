package com.insignia.repo;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.insignia.entity.SubscriptionDetails;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<SubscriptionDetails, Long> {

	public static final String PLAN_ACTIVATION_DATE = "plan_activation_date";
	public static final String PLAN_DEACTIVATION_DATE = "plan_deactivation_date";
	public static final String PLAN_NAME = "plan_name";
	public static final String PLAN_ID = "plan_id";
	public static final String queryToDeleteForPlanIdAndPlanName = "DELETE FROM subscription_plan_master_table WHERE plan_id = :plan_id And plan_name=:plan_name";
	public static final String queryToSetDeactivationStatus = "UPDATE subscription_plan_master_table "
			+ "SET plan_deactivation_date = :plan_deactivation_date "
			+ "WHERE plan_id = :plan_id AND plan_name = :plan_name";
	public static final String queryToSetActivationStatus = "UPDATE subscription_plan_master_table SET plan_activation_date = :plan_activation_date WHERE plan_id = :plan_id AND plan_name = :plan_name";
	public static final String queryToGetForPlanIdAndPlanName = "Select * from subscription_plan_master_table WHERE plan_id = :plan_id And plan_name =:plan_name";
	public static final String queryToFindById= "Select * from subscription_plan_master_table where sequence_number =:sequence_number";
	
	
	
	
	@Query(value = queryToGetForPlanIdAndPlanName, nativeQuery = true)
	public Optional<SubscriptionDetails> findByPlanIdAndName(@Param(PLAN_ID) String planId,
			@Param(PLAN_NAME) String planName);

	@Transactional
	@Modifying
	@Query(value = queryToSetActivationStatus, nativeQuery = true)
	void activateSubscriptionPlan(@Param(PLAN_ID) String planId, @Param(PLAN_NAME) String planName,
			@Param(PLAN_ACTIVATION_DATE) Date planActivationDate);

	@Transactional
	@Modifying
	@Query(value = queryToSetDeactivationStatus, nativeQuery = true)
	void deactivateSubscriptionPlan(@Param(PLAN_ID) String planId, @Param(PLAN_NAME) String planName,
			@Param(PLAN_DEACTIVATION_DATE) Date planDeactivationDate);
	
	
	@Query(value = queryToFindById, nativeQuery = true)
	public Optional<SubscriptionDetails> findById(@Param("sequence_number") Long sequenceNumber);

	@Transactional
	@Modifying
	@Query(value = queryToDeleteForPlanIdAndPlanName, nativeQuery = true)
	public void deleteSubscriptionPlanByName(@Param(PLAN_ID) String planId, @Param(PLAN_NAME) String planName);

}