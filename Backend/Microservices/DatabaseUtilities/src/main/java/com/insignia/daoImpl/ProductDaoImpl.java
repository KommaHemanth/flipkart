package com.insignia.daoImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.insignia.constants.ProductValidatorConstants;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.daoInterface.ProductDaoInterface;
import com.insignia.entity.ProductDetails;
import com.insignia.repo.ProductDetailsRepository;
import com.insignia.repo.ProductSubCategoryRepo;
import com.insignia.repo.TokensEntityRepo;

@Repository
public class ProductDaoImpl implements ProductDaoInterface {

	private static final String STR3 = ";";

	private static final String STRING = "";

	private static final String AND2 = " and ";

	private static final String STR2 = "'";

	private static final String STR = "%'";

	private static final String COLOURS_LIKE = "colours LIKE '%";

	private static final String MATERIALS_LIKE = "materials LIKE '%";

	private static final String SUBCATEGORY_ID = "subcategory_id = '";

	private static final String PRODUCT_PER_UNIT_CURRENT_PRICE2 = "product_per_unit_current_price >= '";

	private static final String PRODUCT_PER_UNIT_CURRENT_PRICE = "product_per_unit_current_price <= '";

	private static final String ORDER_BY_PRODUCT_NAME = "ORDER BY product_name ";

	private static final String SELECT_FROM_PRODUCT_DETAILS_WHERE = "select * from product_details where ";

	@Autowired
	private ProductDetailsRepository productRepo;

	@Autowired
	private ProductSubCategoryRepo productSubCategoryRepo;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private TokensEntityRepo tokensEntityRepo;

	@Modifying
	@Transactional(rollbackOn = InvalidInputParametersException.class)
	@Override
	public ProductDetails addProduct(ProductDetails productRequest) throws InvalidInputParametersException {
		try {
			return productRepo.save(productRequest);
		} catch (DataIntegrityViolationException e) {
			throw new InvalidInputParametersException(ProductValidatorConstants.validateProductDetailsErrorCode,
					ProductValidatorConstants.validateProductDetailsMessage);

		}

	}

	@Override
	public boolean findByProductSubCategory(Long subcategoryId) {

		return productSubCategoryRepo.existsById(subcategoryId);
	}

	@Override
	public ProductDetails updateProduct(ProductDetails request) throws InvalidInputParametersException {

		return entityManager.merge(request);

	}

	@Override
	public void deleteProductById(Long productSequenceNumber) {
		productRepo.deleteById(productSequenceNumber);

	}

	@Override
	public Optional<ProductDetails> findById(Long productSequenceNumber) {

		return productRepo.findById(productSequenceNumber);
	}

	@Override
	public List<ProductDetails> getAllProducts() {
		return productRepo.findAll();

	}

	@Override
	public List<ProductDetails> getSelectedProductDetailsList(List<Long> productSequenceNumberList) {

		return productRepo.findSelectedProductList(productSequenceNumberList);
	}

	@Override
	public Boolean isTokenNotValid(Long customerSequenceNumber) {
		Date currentTimeStamp = new Date();
		return tokensEntityRepo.checkTokenValidity(customerSequenceNumber, currentTimeStamp).isEmpty();
	}

	@Override
	public List<ProductDetails> filterProducts(Map<String, Object> filterCriteria) {
		StringBuilder query = new StringBuilder();
		query.append(SELECT_FROM_PRODUCT_DETAILS_WHERE);

		String and = STRING;

		if (filterCriteria.get(ProductValidatorConstants.filter_colour) != null) {
			query.append(COLOURS_LIKE).append(filterCriteria.get(ProductValidatorConstants.filter_colour).toString()).append(STR);
			and = AND2;
		}
		if (filterCriteria.get(ProductValidatorConstants.filter_material) != null) {
			query.append(and);
			query.append(MATERIALS_LIKE).append(filterCriteria.get(ProductValidatorConstants.filter_material).toString()).append(STR);
			if (and.isEmpty()) {
				and = AND2;
			}
		}
		if (filterCriteria.get(ProductValidatorConstants.filter_subcategory) != null) {
			query.append(and);
			query.append(SUBCATEGORY_ID).append(filterCriteria.get(ProductValidatorConstants.filter_subcategory).toString()).append(STR2);
			if (and.isEmpty()) {
				and = AND2;
			}
		}
		if (filterCriteria.get(ProductValidatorConstants.filter_min_price) != null) {
			query.append(and);
			query.append(PRODUCT_PER_UNIT_CURRENT_PRICE2).append(filterCriteria.get(ProductValidatorConstants.filter_min_price).toString())
					.append(STR2);
			if (and.isEmpty()) {
				and = AND2;
			}
		}
		if (filterCriteria.get(ProductValidatorConstants.filter_max_price) != null) {
			query.append(and);
			query.append(PRODUCT_PER_UNIT_CURRENT_PRICE).append(filterCriteria.get(ProductValidatorConstants.filter_max_price).toString()).append(STR2);
			if (and.isEmpty()) {
				and = AND2;
			}
		}
		if (filterCriteria.get(ProductValidatorConstants.filter_sorting_criteria) != null) {
			query.append(ORDER_BY_PRODUCT_NAME).append(filterCriteria.get(ProductValidatorConstants.filter_sorting_criteria).toString());
		}
		query.append(STR3);
		return entityManager.createNativeQuery(query.toString(), ProductDetails.class).getResultList();

	}

}
