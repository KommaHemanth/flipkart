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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_and_product_link")
public class OrderAndProductLink {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence_number")
	private Long sequenceNumber;

	@Column(name = "product_quantity")
	private Integer productQuantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_sequence_number")
	private OrderDetails orderDetails;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_sequence_number")
	private ProductDetails productDetails;
}
