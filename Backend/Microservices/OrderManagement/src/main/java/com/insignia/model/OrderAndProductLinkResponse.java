package com.insignia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAndProductLinkResponse {

	private Integer productQuantity;

	private ProductDetailsResponse productDetailsResponse;
}
