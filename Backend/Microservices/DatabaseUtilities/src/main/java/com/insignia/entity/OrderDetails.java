package com.insignia.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_sequence_number")
	private Long orderSequenceNumber;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "order_status")
	private String orderStatus;

	@Column(name = "invoice_id")
	private String InvoiceId;

	@Column(name = "customer_sequence_number")
	private Long customerSequenceNumber;

	@Column(name = "address_sequence_number")
	private Long AddressSequenceNumber;

	@OneToMany(mappedBy = "orderDetails",cascade = CascadeType.ALL )
	private List<OrderAndProductLink> orderAndProductLinkList;
	

}
