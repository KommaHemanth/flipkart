package com.insignia.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.ProductMaterialDaoInterface;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.entity.ProductMaterial;
import com.insignia.model.ProductMaterialRequest;
import com.insignia.model.ProductMaterialResponse;


@ExtendWith(MockitoExtension.class)
public class TestProductMaterialServiceImpl {

	@InjectMocks
	private ProductMaterialServiceImpl productMaterialServiceImpl;

	@Mock
	private ProductMaterialDaoInterface productMaterialDaoInterface;

	@Mock
	private TokenDaoInterface tokenRepo;

	ProductMaterialRequest productMaterialRequest = new ProductMaterialRequest();
	ProductMaterialResponse productMaterialResponse = new ProductMaterialResponse();

	List<ProductMaterialResponse> productMaterialResponseList = new ArrayList<>();
	ProductMaterial productMaterial = new ProductMaterial();

	List<ProductMaterial> productMaterialsList = new ArrayList<>();

	public void dataInitilization() {

		productMaterialRequest.setCustomerSequenceNumber(5L);
		productMaterialRequest.setExpirationDuration(15);
		productMaterialRequest.setMaterialName("pink");
		productMaterialRequest.setMaterialDescription("for bench");

		productMaterialResponse.setSequenceNumber(5);
		productMaterialResponse.setMaterialName("pink");
		productMaterialResponse.setMaterialDescription("for bench");

		productMaterialResponseList.add(productMaterialResponse);

		productMaterial.setMaterialName("pink");
		productMaterial.setMaterialDescription("for bench");

		productMaterialsList.add(productMaterial);
	}

	@Test
	public void testSaveProductMaterial()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(productMaterialDaoInterface.findByMaterialName(anyString())).thenReturn(null);

		when(productMaterialDaoInterface.saveProductMaterial(productMaterial)).thenReturn(productMaterial);

		ProductMaterialResponse saveProductMaterial = productMaterialServiceImpl.saveProductMaterial(productMaterialRequest);
		
		assertNotNull(saveProductMaterial);
	}

	@Test
	public void testSaveProductMaterialDuplicateDataException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		dataInitilization();

		String MaterialName = "pink";
		when(productMaterialDaoInterface.findByMaterialName(anyString())).thenReturn(MaterialName);

		assertThrows(InvalidInputParametersException.class, () -> {

			productMaterialServiceImpl.saveProductMaterial(productMaterialRequest);
		});
		verify(productMaterialDaoInterface, times(1)).findByMaterialName(anyString());
	}

	@Test
	public void testUpdateMeasurementUnits()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		productMaterialRequest.setSequenceNumber(5);
		Integer sequenceNumber = 5;
		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(productMaterialDaoInterface.findByMaterialName(anyString())).thenReturn(null);

		when(productMaterialDaoInterface.findBySequenceNumber(sequenceNumber))
				.thenReturn(Optional.of(productMaterial));
		when(productMaterialDaoInterface.updateProductMaterial(productMaterial)).thenReturn(productMaterial);

		ProductMaterialResponse updateProductMaterial = productMaterialServiceImpl
				.updateProductMaterial(productMaterialRequest);

		assertNotNull(updateProductMaterial);
	}

	@Test
	public void testUpdateMeasurementUnitsDuplicateDataException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		dataInitilization();

		String MaterialName = "pink";
		when(productMaterialDaoInterface.findByMaterialName(anyString())).thenReturn(MaterialName);

		assertThrows(InvalidInputParametersException.class, () -> {

			productMaterialServiceImpl.updateProductMaterial(productMaterialRequest);
		});
		verify(productMaterialDaoInterface, times(1)).findByMaterialName(anyString());
	}

	@Test
	public void testUpdateMeasurementUnitsDetailsNoExistException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		dataInitilization();
		productMaterialRequest.setSequenceNumber(5);
		Integer sequenceNumber = 5;
		when(productMaterialDaoInterface.findBySequenceNumber(sequenceNumber)).thenReturn(Optional.empty());

		assertThrows(InvalidInputParametersException.class, () -> {

			productMaterialServiceImpl.updateProductMaterial(productMaterialRequest);
		});
		verify(productMaterialDaoInterface, times(1)).findBySequenceNumber(sequenceNumber);
	}

	@Test
	public void testDeleteMeasurementUnits()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();
		String MaterialName = "pink";
		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(productMaterialDaoInterface.findByMaterialName(anyString())).thenReturn(MaterialName);

		doNothing().when(productMaterialDaoInterface).deleteProductMaterial(MaterialName);

		productMaterialServiceImpl.deleteProductMaterial(productMaterialRequest);

		verify(productMaterialDaoInterface, times(1)).deleteProductMaterial(MaterialName);
	}

	@Test
	public void testDeleteMeasurementUnitsDetailsNotExistedException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		dataInitilization();

		when(productMaterialDaoInterface.findByMaterialName(anyString())).thenReturn(null);

		assertThrows(InvalidInputParametersException.class, () -> {

			productMaterialServiceImpl.deleteProductMaterial(productMaterialRequest);
		});
		verify(productMaterialDaoInterface, times(1)).findByMaterialName(anyString());
	}

	@Test
	public void testGetAllMeasurementUnits()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(productMaterialDaoInterface.getAllProductMaterials()).thenReturn(productMaterialsList);

		List<ProductMaterialResponse> allProductMaterial = productMaterialServiceImpl
				.getAllProductMaterials(productMaterialRequest);

		assertNotNull(allProductMaterial);
	}

}