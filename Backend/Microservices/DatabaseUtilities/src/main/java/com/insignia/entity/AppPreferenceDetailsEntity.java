package com.insignia.entity;

import java.util.Date;

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
@Table(name="app_preference_details")
public class AppPreferenceDetailsEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="preference_id")
	private Long preferenceId;
		
	@Column(name="preference_type")
	private String preferenceType;
	
	@Column(name="preference_value")
	private String preferenceValue;
	
	@Column(name="preference_modified_on")
	private Date preferenceModifiedOn;
	
	@Column(name = "customer_sequence_number")
	private Long customerSequenceNumber;
	
	@Column(name = "application_id")
	private Integer applicationId;
	
}
