package com.insignia.daoImpl;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.daoInterface.CategoryDaoInterface;
import com.insignia.entity.ProductCategory;
import com.insignia.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import static com.insignia.constants.CategoryConstant.validateCategoryName;
import static com.insignia.constants.CategoryConstant.validateCategoryNameCode;

@Repository
public class CategoryDaoImpl implements CategoryDaoInterface {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Optional<ProductCategory> findByCategoryId(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<ProductCategory> fetchAllCategories() {
        return categoryRepository.fetchAllCategory();
    }

    @Transactional(rollbackOn = InvalidInputParametersException.class)
    @Override
    public ProductCategory saveCategory(ProductCategory productCategory) throws InvalidInputParametersException {
        try {
            return categoryRepository.save(productCategory);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidInputParametersException(validateCategoryNameCode,
                    validateCategoryName);
        }
    }

    @Override
    public Optional<List<ProductCategory>> getInactiveCategories() {
        return categoryRepository.getInactiveCategories();
    }

    @Override
    public ProductCategory findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }


}
