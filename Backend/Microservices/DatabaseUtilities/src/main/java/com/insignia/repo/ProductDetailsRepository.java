package com.insignia.repo;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insignia.entity.ProductDetails;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Serializable> {

	public final static String findProductDetailsByProductSequenceNumber = "select * from product_details where product_sequence_number=:product_sequence_number";

	public final static String findSelectedProductListQuery = "Select * From product_details Where product_sequence_number In (:productSequenceNumberList)";

	@Query(value = findProductDetailsByProductSequenceNumber, nativeQuery = true)
	public Optional<ProductDetails> getProductDetails(@Param("product_sequence_number") Long productSequenceNumber);

	@Query(value = findSelectedProductListQuery, nativeQuery = true)
	public List<ProductDetails> findSelectedProductList(
			@Param("productSequenceNumberList") List<Long> productSequenceNumberList);

}
