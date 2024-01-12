package com.insignia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterCriteria {

	private boolean isToGetAllProduct;
	private String material;
	private String colour;
	private Float minPrice;
	private Float maxPrice;
	private String sortingCriteria;
	private String subCategory;
	private String availability;
	
	
}
