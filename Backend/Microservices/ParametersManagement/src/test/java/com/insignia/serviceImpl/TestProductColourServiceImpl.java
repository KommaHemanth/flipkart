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
import com.insignia.daoInterface.ProductColourDaoInterface;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.entity.ProductColour;
import com.insignia.model.ProductColourRequest;
import com.insignia.model.ProductColourResponse;

@ExtendWith(MockitoExtension.class)
public class TestProductColourServiceImpl {

	@InjectMocks
	private ProductColourServiceImpl productColourServiceImpl;

	@Mock
	private ProductColourDaoInterface productColourDaoInterface;

	@Mock
	private TokenDaoInterface tokenRepo;

	ProductColourRequest productColourRequest = new ProductColourRequest();
	ProductColourResponse productColourResponse = new ProductColourResponse();

	List<ProductColourResponse> productColourResponseList = new ArrayList<>();
	ProductColour productColour = new ProductColour();

	List<ProductColour> productColoursList = new ArrayList<>();

	public void dataInitilization() {

		productColourRequest.setCustomerSequenceNumber(5L);
		productColourRequest.setExpirationDuration(15);
		productColourRequest.setColourName("pink");
		productColourRequest.setColourDescription("for bench");

		productColourResponse.setSequenceNumber(5);
		productColourResponse.setColourName("pink");
		productColourResponse.setColourDescription("for bench");

		productColourResponseList.add(productColourResponse);

		productColour.setColourName("pink");
		productColour.setColourDescription("for bench");

		productColoursList.add(productColour);
	}

	@Test
	public void testSaveProductColour()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(productColourDaoInterface.findByColourName(anyString())).thenReturn(null);

		when(productColourDaoInterface.saveProductColour(productColour)).thenReturn(productColour);

		ProductColourResponse saveProductColour = productColourServiceImpl.saveProductColour(productColourRequest);
		
		assertNotNull(saveProductColour);
	}

	@Test
	public void testSaveProductColourDuplicateDataException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		dataInitilization();

		String colourName = "pink";
		when(productColourDaoInterface.findByColourName(anyString())).thenReturn(colourName);

		assertThrows(InvalidInputParametersException.class, () -> {

			productColourServiceImpl.saveProductColour(productColourRequest);
		});
		verify(productColourDaoInterface, times(1)).findByColourName(anyString());
	}

	@Test
	public void testUpdateMeasurementUnits()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		productColourRequest.setSequenceNumber(5);
		Integer sequenceNumber = 5;
		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(productColourDaoInterface.findByColourName(anyString())).thenReturn(null);

		when(productColourDaoInterface.findBySequenceNumber(sequenceNumber))
				.thenReturn(Optional.of(productColour));
		when(productColourDaoInterface.updateProductColour(productColour)).thenReturn(productColour);

		ProductColourResponse updateProductColour = productColourServiceImpl
				.updateProductColour(productColourRequest);

		assertNotNull(updateProductColour);
	}

	@Test
	public void testUpdateMeasurementUnitsDuplicateDataException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		dataInitilization();

		String colourName = "pink";
		when(productColourDaoInterface.findByColourName(anyString())).thenReturn(colourName);

		assertThrows(InvalidInputParametersException.class, () -> {

			productColourServiceImpl.updateProductColour(productColourRequest);
		});
		verify(productColourDaoInterface, times(1)).findByColourName(anyString());
	}

	@Test
	public void testUpdateMeasurementUnitsDetailsNoExistException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		dataInitilization();
		productColourRequest.setSequenceNumber(5);
		Integer sequenceNumber = 5;
		when(productColourDaoInterface.findBySequenceNumber(sequenceNumber)).thenReturn(Optional.empty());

		assertThrows(InvalidInputParametersException.class, () -> {

			productColourServiceImpl.updateProductColour(productColourRequest);
		});
		verify(productColourDaoInterface, times(1)).findBySequenceNumber(sequenceNumber);
	}

	@Test
	public void testDeleteMeasurementUnits()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();
		String colourName = "pink";
		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(productColourDaoInterface.findByColourName(anyString())).thenReturn(colourName);

		doNothing().when(productColourDaoInterface).deleteProductColour(colourName);

		productColourServiceImpl.deleteProductColour(productColourRequest);

		verify(productColourDaoInterface, times(1)).deleteProductColour(colourName);
	}

	@Test
	public void testDeleteMeasurementUnitsDetailsNotExistedException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		dataInitilization();

		when(productColourDaoInterface.findByColourName(anyString())).thenReturn(null);

		assertThrows(InvalidInputParametersException.class, () -> {

			productColourServiceImpl.deleteProductColour(productColourRequest);
		});
		verify(productColourDaoInterface, times(1)).findByColourName(anyString());
	}

	@Test
	public void testGetAllMeasurementUnits()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(productColourDaoInterface.getAllProductColour()).thenReturn(productColoursList);

		List<ProductColourResponse> allProductColour = productColourServiceImpl
				.getAllProductColour(productColourRequest);

		assertNotNull(allProductColour);
	}

}