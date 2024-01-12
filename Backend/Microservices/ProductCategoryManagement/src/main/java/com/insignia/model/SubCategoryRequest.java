package com.insignia.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubCategoryRequest {

    private Long categoryId;
    private Long subCategoryId;
    private String subCategoryName;
    private String subCategoryDescription;
    private String subCategoryImagePath;
    private String subCategoryDefaultImage;
    private Long customerSequenceNumber;
    private Integer expirationDuration;
}
