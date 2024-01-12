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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_cart_information")
public class CustomerCartInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_cart_sequence_number")
	private Long customerCartSequenceNumber;

	@Column(name = "product_sequence_number")
	private Long productSequenceNumber;

	@Column(name = "cart_sequence_number")
	private Long cartSequenceNumber;

	@Column(name = "product_quantity")
	private String productQuantity;
	
}
