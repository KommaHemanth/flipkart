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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "measurement_units")
public class MeasurementUnits {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence_number", length = 4)
	private Integer sequenceNumber;

	@Column(name = "unit_name", length = 12)
	private String unitName;

	@Column(name = "unit_description", length = 128)
	private String unitDescription;
	
}
