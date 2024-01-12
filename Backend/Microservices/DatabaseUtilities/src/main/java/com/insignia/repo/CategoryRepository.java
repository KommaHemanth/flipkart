package com.insignia.repo;

import com.insignia.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Serializable> {

    @Query("select c from ProductCategory c where c.isSoftDeleted =false and c.categoryId=?1")
    Optional<ProductCategory> findByCategoryId(Long categoryId);

    @Query("select c from ProductCategory c where c.isSoftDeleted =false ")
    List<ProductCategory> fetchAllCategory();

    @Query("select c from ProductCategory c where c.isSoftDeleted =true ")
    Optional<List<ProductCategory>> getInactiveCategories();

    ProductCategory findByCategoryName(String categoryName);
}