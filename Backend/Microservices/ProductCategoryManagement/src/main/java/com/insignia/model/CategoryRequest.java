package com.insignia.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryRequest {

	private Long categoryId;
	private String categoryName;
	private String categoryDescription;
	private String categoryImagePath;
	private String defaultImage;
	private Long customerSequenceNumber;
	private Integer expirationDuration;

}
