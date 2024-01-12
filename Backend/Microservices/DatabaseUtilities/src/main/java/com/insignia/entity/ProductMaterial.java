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
@Table(name = "product_material")
public class ProductMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence_number", length = 4)
	private Integer sequenceNumber;

	@Column(name = "material_name", length = 30)
	private String materialName;

	@Column(name = "material_description", length = 128)
	private String materialDescription;
	
}
