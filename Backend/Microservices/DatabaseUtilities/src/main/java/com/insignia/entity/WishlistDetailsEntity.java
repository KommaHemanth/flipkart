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
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "customer_wishlist_details")
public class WishlistDetailsEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "wishlist_id")
	private Long wishlistId;

	@Column(name = "customer_sequence_number")
	private Long customerSequenceNumber;

	@Column(name = "product_sequence_number")
	private Long productSequenceNumber;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "timestamp")
	private Date timestamp;

}
