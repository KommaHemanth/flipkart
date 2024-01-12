package com.insignia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_subscription_plan_link")
public class ProductSubscriptionPlanLink {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "sequence_number")
	private Long sequenceNumber;

	@Column(name = "product_sequence_number")
	private Long productSequenceNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subscription_plan_sequence_number")
	private SubscriptionDetails subscriptionDetails;

}
