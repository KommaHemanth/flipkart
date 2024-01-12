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
import com.insignia.daoInterface.MeasurementUnitsDaoInterface;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.entity.MeasurementUnits;
import com.insignia.model.MeasurementUnitRequest;
import com.insignia.model.MeasurementUnitResponse;

@ExtendWith(MockitoExtension.class)
public class TestMeasurementUnitsServiceImpl {

	@InjectMocks
	private MeasurementUnitServiceImpl measurementUnitServiceImpl;

	@Mock
	private MeasurementUnitsDaoInterface measurementUnitsDaoInterface;

	@Mock
	private TokenDaoInterface tokenRepo;

	MeasurementUnitRequest measurementUnitRequest = new MeasurementUnitRequest();
	MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();

	List<MeasurementUnitResponse> measurementUnitResponseList = new ArrayList<>();
	MeasurementUnits measurementUnits = new MeasurementUnits();

	List<MeasurementUnits> measurementUnitsList = new ArrayList<>();

	public void dataInitilization() {

		measurementUnitRequest.setCustomerSequenceNumber(5L);
		measurementUnitRequest.setExpirationDuration(15);
		measurementUnitRequest.setUnitName("inches");
		measurementUnitRequest.setUnitDescription("for bench");

		measurementUnitResponse.setSequenceNumber(5);
		measurementUnitResponse.setUnitName("inches");
		measurementUnitResponse.setUnitDescription("for bench");

		measurementUnitResponseList.add(measurementUnitResponse);

		measurementUnits.setUnitName("inches");
		measurementUnits.setUnitDescription("for bench");

		measurementUnitsList.add(measurementUnits);
	}

	@Test
	public void testSaveMeasurementUnits()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(measurementUnitsDaoInterface.findByUnitName(anyString())).thenReturn(null);

		when(measurementUnitsDaoInterface.saveMeasurementUnits(measurementUnits)).thenReturn(measurementUnits);

		MeasurementUnitResponse saveMeasurementUnits = measurementUnitServiceImpl
				.saveMeasurementUnits(measurementUnitRequest);

		assertNotNull(saveMeasurementUnits);
	}

	@Test
	public void testSaveMeasurementUnitsDuplicateDataException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		dataInitilization();

		String unitName = "inches";
		when(measurementUnitsDaoInterface.findByUnitName(anyString())).thenReturn(unitName);

		assertThrows(InvalidInputParametersException.class, () -> {

			measurementUnitServiceImpl.saveMeasurementUnits(measurementUnitRequest);
		});
		verify(measurementUnitsDaoInterface, times(1)).findByUnitName(anyString());
	}

	@Test
	public void testUpdateMeasurementUnits()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		measurementUnitRequest.setSequenceNumber(5);
		Integer sequenceNumber = 5;
		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(measurementUnitsDaoInterface.findByUnitName(anyString())).thenReturn(null);

		when(measurementUnitsDaoInterface.findBySequenceNumber(sequenceNumber))
				.thenReturn(Optional.of(measurementUnits));
		when(measurementUnitsDaoInterface.updateMeasurementUnits(measurementUnits)).thenReturn(measurementUnits);

		MeasurementUnitResponse saveMeasurementUnits = measurementUnitServiceImpl
				.updateMeasurementUnits(measurementUnitRequest);

		assertNotNull(saveMeasurementUnits);
	}

	@Test
	public void testUpdateMeasurementUnitsDuplicateDataException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		dataInitilization();

		String unitName = "inches";
		when(measurementUnitsDaoInterface.findByUnitName(anyString())).thenReturn(unitName);

		assertThrows(InvalidInputParametersException.class, () -> {

			measurementUnitServiceImpl.updateMeasurementUnits(measurementUnitRequest);
		});
		verify(measurementUnitsDaoInterface, times(1)).findByUnitName(anyString());
	}

	@Test
	public void testUpdateMeasurementUnitsDetailsNoExistException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		dataInitilization();
		measurementUnitRequest.setSequenceNumber(5);
		Integer sequenceNumber = 5;
		when(measurementUnitsDaoInterface.findBySequenceNumber(sequenceNumber)).thenReturn(Optional.empty());

		assertThrows(InvalidInputParametersException.class, () -> {

			measurementUnitServiceImpl.updateMeasurementUnits(measurementUnitRequest);
		});
		verify(measurementUnitsDaoInterface, times(1)).findBySequenceNumber(sequenceNumber);
	}

	@Test
	public void testDeleteMeasurementUnits()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();
		String unitName = "inches";
		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(measurementUnitsDaoInterface.findByUnitName(anyString())).thenReturn(unitName);

		doNothing().when(measurementUnitsDaoInterface).deleteMeasurementUnits(unitName);

		measurementUnitServiceImpl.deleteMeasurementUnits(measurementUnitRequest);

		verify(measurementUnitsDaoInterface, times(1)).deleteMeasurementUnits(unitName);
	}

	@Test
	public void testDeleteMeasurementUnitsDetailsNotExistedException()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {

		dataInitilization();

		when(measurementUnitsDaoInterface.findByUnitName(anyString())).thenReturn(null);

		assertThrows(InvalidInputParametersException.class, () -> {

			measurementUnitServiceImpl.deleteMeasurementUnits(measurementUnitRequest);
		});
		verify(measurementUnitsDaoInterface, times(1)).findByUnitName(anyString());
	}

	@Test
	public void testGetAllMeasurementUnits()
			throws InvalidInputParametersException, ParseException, TokenExpiredException {
		dataInitilization();

		doNothing().when(tokenRepo).checkTokenValidity(any(), any());

		when(measurementUnitsDaoInterface.getAllMeasurementUnits()).thenReturn(measurementUnitsList);

		List<MeasurementUnitResponse> allMeasurementUnits = measurementUnitServiceImpl
				.getAllMeasurementUnits(measurementUnitRequest);

		assertNotNull(allMeasurementUnits);
	}

}