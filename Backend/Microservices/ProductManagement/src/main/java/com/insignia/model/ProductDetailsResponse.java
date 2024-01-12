package com.insignia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsResponse {

	private Long productSequenceNumber;
	private String productId;
	private String productName;
	private String description;
	private String measuringQuantity;
	private String measuringUnit;
	private Long subcategoryId;
	private String productImage;
	private Float productPerUnitActualPrice;
	private Float productPerUnitCurrentPrice;
	private Float productLength;
	private Float width;
	private Float height;
	private String dimensionUnit;
	private String materials;
	private String colours;
}
