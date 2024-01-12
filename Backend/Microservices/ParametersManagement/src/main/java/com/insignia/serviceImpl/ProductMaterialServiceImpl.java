package com.insignia.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insignia.constants.ProductMaterialConstant;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.ProductMaterialDaoInterface;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.entity.ProductMaterial;
import com.insignia.model.ProductMaterialRequest;
import com.insignia.model.ProductMaterialResponse;
import com.insignia.serviceInterface.ProductMaterialServiceInterface;

@Service
public class ProductMaterialServiceImpl implements ProductMaterialServiceInterface {

	@Autowired
	private ProductMaterialDaoInterface productMaterialDaoInterface;

	@Autowired
	private TokenDaoInterface tokenDao;

	@Transactional
	@Override
	public ProductMaterialResponse saveProductMaterial(ProductMaterialRequest productMaterialRequest)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(productMaterialRequest.getCustomerSequenceNumber(),
				productMaterialRequest.getExpirationDuration());
		Object materialName = productMaterialDaoInterface.findByMaterialName(productMaterialRequest.getMaterialName());

		if (materialName != null
				&& materialName.toString().equalsIgnoreCase(productMaterialRequest.getMaterialName())) {
			throw new InvalidInputParametersException(ProductMaterialConstant.duplicateDataInProductMaterialErrorCode,
					ProductMaterialConstant.duplicateDataInProductMaterialMessage);
		}

		ProductMaterial productMaterialEntity = new ProductMaterial();
		productMaterialEntity.setMaterialName(productMaterialRequest.getMaterialName());
		productMaterialEntity.setMaterialDescription(productMaterialRequest.getMaterialDescription());
		ProductMaterial productMaterial = productMaterialDaoInterface.saveProductMaterial(productMaterialEntity);

		return createResponseForProductMaterialEntity(productMaterial);
	}

	@Transactional
	@Override
	public ProductMaterialResponse updateProductMaterial(ProductMaterialRequest productMaterialRequest)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(productMaterialRequest.getCustomerSequenceNumber(),
				productMaterialRequest.getExpirationDuration());

		if (productMaterialRequest.isMaterialNameUpdated()) {
			Object materialName = productMaterialDaoInterface
					.findByMaterialName(productMaterialRequest.getMaterialName());

			if (materialName != null
					&& materialName.toString().equalsIgnoreCase(productMaterialRequest.getMaterialName())) {
				throw new InvalidInputParametersException(
						ProductMaterialConstant.duplicateDataInProductMaterialErrorCode,
						ProductMaterialConstant.duplicateDataInProductMaterialMessage);
			}

		}

		Optional<ProductMaterial> productMaterialList = productMaterialDaoInterface
				.findBySequenceNumber(productMaterialRequest.getSequenceNumber());

		if (productMaterialList.isPresent()) {

			ProductMaterial productMaterial = productMaterialList.get();

			if (productMaterialRequest.isMaterialNameUpdated()) {
				productMaterial.setMaterialName(productMaterialRequest.getMaterialName());
			}
			if (productMaterialRequest.isMaterialDescriptionUpdated()) {
				productMaterial.setMaterialDescription(productMaterialRequest.getMaterialDescription());
			}
			ProductMaterial updateProductMaterial = productMaterialDaoInterface.updateProductMaterial(productMaterial);

			return createResponseForProductMaterialEntity(updateProductMaterial);

		} else {
			throw new InvalidInputParametersException(ProductMaterialConstant.productMaterialDetailsNotExistedErrorCode,
					ProductMaterialConstant.productMaterialDetailsNotExistedMessage);
		}

	}

	@Transactional
	@Override
	public void deleteProductMaterial(ProductMaterialRequest productMaterialRequest)
			throws InvalidInputParametersException, TokenExpiredException {
		tokenDao.checkTokenValidity(productMaterialRequest.getCustomerSequenceNumber(),
				productMaterialRequest.getExpirationDuration());

		Object materialName = productMaterialDaoInterface.findByMaterialName(productMaterialRequest.getMaterialName());

		if (materialName != null
				&& materialName.toString().equalsIgnoreCase(productMaterialRequest.getMaterialName())) {
			productMaterialDaoInterface.deleteProductMaterial(productMaterialRequest.getMaterialName());
		} else {
			throw new InvalidInputParametersException(ProductMaterialConstant.productMaterialDetailsNotExistedErrorCode,
					ProductMaterialConstant.productMaterialDetailsNotExistedMessage);
		}
	}

	@Transactional
	@Override
	public List<ProductMaterialResponse> getAllProductMaterials(ProductMaterialRequest productMaterialRequest)
			throws TokenExpiredException {

		tokenDao.checkTokenValidity(productMaterialRequest.getCustomerSequenceNumber(),
				productMaterialRequest.getExpirationDuration());

		List<ProductMaterialResponse> productMaterialResponseList = new ArrayList<>();
		List<ProductMaterial> productMaterialList = productMaterialDaoInterface.getAllProductMaterials();

		if (productMaterialList != null) {
			for (ProductMaterial productMaterial : productMaterialList) {
				ProductMaterialResponse productMaterialResponse = createResponseForProductMaterialEntity(
						productMaterial);
				productMaterialResponseList.add(productMaterialResponse);
			}
		}

		return productMaterialResponseList;
	}

	private ProductMaterialResponse createResponseForProductMaterialEntity(ProductMaterial productMaterial) {
		ProductMaterialResponse productMaterialResponse = new ProductMaterialResponse();
		productMaterialResponse.setSequenceNumber(productMaterial.getSequenceNumber());
		productMaterialResponse.setMaterialName(productMaterial.getMaterialName());
		productMaterialResponse.setMaterialDescription(productMaterial.getMaterialDescription());
		return productMaterialResponse;
	}

}
