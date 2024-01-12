package com.insignia.daoInterface;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryDaoInterface {


    Optional<ProductCategory> findByCategoryId(Long categoryId);

    List<ProductCategory> fetchAllCategories();

    ProductCategory saveCategory(ProductCategory productCategory) throws InvalidInputParametersException;

    Optional<List<ProductCategory>> getInactiveCategories();


    ProductCategory findByCategoryName(String categoryName);
}
