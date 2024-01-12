package com.insignia.service;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.CategoryRequest;
import com.insignia.model.SubCategoryRequest;
import com.insignia.model.SubCategoryResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SubCategoryServiceInterface {

    SubCategoryResponse saveSubCategory(SubCategoryRequest subcategoryRequest)throws InvalidInputParametersException, TokenExpiredException;

    Optional<List<SubCategoryResponse>> getSubCategories(SubCategoryRequest subcartRequest) throws TokenExpiredException, InvalidInputParametersException;


    SubCategoryResponse updateSubCategory(SubCategoryRequest subcategoryRequest) throws InvalidInputParametersException, TokenExpiredException;

    String disableSubCategory(SubCategoryRequest subCategoryRequest) throws TokenExpiredException, InvalidInputParametersException;

    SubCategoryResponse enableSubCategory(SubCategoryRequest subCategoryRequest) throws TokenExpiredException, InvalidInputParametersException;
}

