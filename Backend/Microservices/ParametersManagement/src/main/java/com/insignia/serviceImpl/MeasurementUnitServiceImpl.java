package com.insignia.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insignia.constants.MeasurementUnitConstant;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.MeasurementUnitsDaoInterface;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.entity.MeasurementUnits;
import com.insignia.model.MeasurementUnitRequest;
import com.insignia.model.MeasurementUnitResponse;
import com.insignia.serviceInterface.MeasurementUnitServiceInterface;

@Service
public class MeasurementUnitServiceImpl implements MeasurementUnitServiceInterface {

	@Autowired
	private MeasurementUnitsDaoInterface measurementUnitsDaoInterface;

	@Autowired
	private TokenDaoInterface tokenDao;

	@Transactional
	@Override
	public MeasurementUnitResponse saveMeasurementUnits(MeasurementUnitRequest measurementUnitRequest)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(measurementUnitRequest.getCustomerSequenceNumber(),
				measurementUnitRequest.getExpirationDuration());

		Object measurementUnitName = measurementUnitsDaoInterface.findByUnitName(measurementUnitRequest.getUnitName());

		if (measurementUnitName != null
				&& measurementUnitName.toString().equalsIgnoreCase(measurementUnitRequest.getUnitName())) {
			throw new InvalidInputParametersException(MeasurementUnitConstant.duplicateDataInMeasurementUnitErrorCode,
					MeasurementUnitConstant.duplicateDataInMeasurementUnitMessage);
		}
		MeasurementUnits measurementUnitsEntity = new MeasurementUnits();
		measurementUnitsEntity.setUnitName(measurementUnitRequest.getUnitName());
		measurementUnitsEntity.setUnitDescription(measurementUnitRequest.getUnitDescription());
		MeasurementUnits measurementUnits = measurementUnitsDaoInterface.saveMeasurementUnits(measurementUnitsEntity);

		return createResponseForMeasurementUnitEntity(measurementUnits);
	}

	@Transactional
	@Override
	public MeasurementUnitResponse updateMeasurementUnits(MeasurementUnitRequest measurementUnitRequest)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(measurementUnitRequest.getCustomerSequenceNumber(),
				measurementUnitRequest.getExpirationDuration());

		if (measurementUnitRequest.isUnitNameUpdated()) {
			Object measurementUnitName = measurementUnitsDaoInterface
					.findByUnitName(measurementUnitRequest.getUnitName());

			if (measurementUnitName != null
					&& measurementUnitName.toString().equalsIgnoreCase(measurementUnitRequest.getUnitName())) {
				throw new InvalidInputParametersException(
						MeasurementUnitConstant.duplicateDataInMeasurementUnitErrorCode,
						MeasurementUnitConstant.duplicateDataInMeasurementUnitMessage);
			}
		}
		Optional<MeasurementUnits> measurementUnitsList = measurementUnitsDaoInterface
				.findBySequenceNumber(measurementUnitRequest.getSequenceNumber());

		if (measurementUnitsList.isPresent()) {

			MeasurementUnits measurementUnits = measurementUnitsList.get();

			if (measurementUnitRequest.isUnitNameUpdated()) {
				measurementUnits.setUnitName(measurementUnitRequest.getUnitName());
			}
			if (measurementUnitRequest.isUnitDescriptionUpdated()) {
				measurementUnits.setUnitDescription(measurementUnitRequest.getUnitDescription());
			}
			MeasurementUnits updateMeasurementUnits = measurementUnitsDaoInterface
					.updateMeasurementUnits(measurementUnits);

			return createResponseForMeasurementUnitEntity(updateMeasurementUnits);

		} else {
			throw new InvalidInputParametersException(MeasurementUnitConstant.detailsNotExistedErrorCode,
					MeasurementUnitConstant.detailsNotExistedMessage);
		}

	}

	@Transactional
	@Override
	public void deleteMeasurementUnits(MeasurementUnitRequest measurementUnitRequest)
			throws InvalidInputParametersException, TokenExpiredException {

		tokenDao.checkTokenValidity(measurementUnitRequest.getCustomerSequenceNumber(),
				measurementUnitRequest.getExpirationDuration());

		Object measurementUnitName = measurementUnitsDaoInterface.findByUnitName(measurementUnitRequest.getUnitName());

		if (measurementUnitName != null
				&& measurementUnitName.toString().equalsIgnoreCase(measurementUnitRequest.getUnitName())) {
			measurementUnitsDaoInterface.deleteMeasurementUnits(measurementUnitRequest.getUnitName());

		} else {
			throw new InvalidInputParametersException(MeasurementUnitConstant.detailsNotExistedErrorCode,
					MeasurementUnitConstant.detailsNotExistedMessage);
		}
	}

	@Transactional
	@Override
	public List<MeasurementUnitResponse> getAllMeasurementUnits(MeasurementUnitRequest measurementUnitRequest)
			throws TokenExpiredException {

		tokenDao.checkTokenValidity(measurementUnitRequest.getCustomerSequenceNumber(),
				measurementUnitRequest.getExpirationDuration());

		List<MeasurementUnitResponse> measurementUnitResponseList = new ArrayList<>();
		List<MeasurementUnits> measurementUnitsList = measurementUnitsDaoInterface.getAllMeasurementUnits();

		if (measurementUnitsList != null) {
			for (MeasurementUnits measurementUnits : measurementUnitsList) {
				MeasurementUnitResponse measurementUnitResponse = createResponseForMeasurementUnitEntity(
						measurementUnits);
				measurementUnitResponseList.add(measurementUnitResponse);
			}
		}

		return measurementUnitResponseList;
	}

	private MeasurementUnitResponse createResponseForMeasurementUnitEntity(MeasurementUnits measurementUnits) {
		MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
		measurementUnitResponse.setSequenceNumber(measurementUnits.getSequenceNumber());
		measurementUnitResponse.setUnitName(measurementUnits.getUnitName());
		measurementUnitResponse.setUnitDescription(measurementUnits.getUnitDescription());
		return measurementUnitResponse;
	}

}
