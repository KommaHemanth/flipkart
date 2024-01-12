package com.insignia.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscription_plan_master_table")
public class SubscriptionDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence_number")
	private Long sequenceNumber;

	@Column(name = "plan_id", unique = true)
	private String planId;

	@Column(name = "plan_name")
	private String planName;

	@Column(name = "plan_description")
	private String planDescription;

	@Column(name = "plan_duration")
	private Integer planDuration;

	@Column(name = "plan_pricing")
	private Float planPricing;

	@Column(name = "plan_activation_status")
	private String planActivationStatus;

	@Column(name = "plan_activation_date")
	private Date planActivationDate;

	@Column(name = "plan_deactivation_date")
	private Date planDeactivationDate;
	
	@Column(name = "percentage_discount")
	private Float percentageDiscount;
	
	@Column(name = "active_till")
	private Date planActiveTill;
	
	@OneToMany(mappedBy = "subscriptionDetails")
	private List<CustomerSubscriptionPlanLink> customerSubscriptionPlanLink;
	
	@OneToMany(mappedBy = "subscriptionDetails")
	private List<ProductSubscriptionPlanLink> productSubscriptionPlanLink;
	
}
