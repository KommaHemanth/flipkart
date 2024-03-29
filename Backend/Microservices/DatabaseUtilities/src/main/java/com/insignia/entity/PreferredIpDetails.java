package com.insignia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "preferred_ip_details")
public class PreferredIpDetails {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "sequence_number")
	private Long sequenceNumber;

	@Column(name = "customer_sequence_number")
	private Long customerSequenceNumber;

	@Column(name = "ip_details", length = 40, nullable = false)
	private String ipDetails;
	
	@Column(name = "ip_type", length = 4, nullable = false)
	private String ipType;
}
