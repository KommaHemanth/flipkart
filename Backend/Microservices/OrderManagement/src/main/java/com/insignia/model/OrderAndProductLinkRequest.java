package com.insignia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAndProductLinkRequest {
	
	private Integer productQuantity;

	private Long productSequenceNumber;
}
