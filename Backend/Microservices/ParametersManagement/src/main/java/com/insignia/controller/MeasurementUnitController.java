package com.insignia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insignia.constant.MeasurementConstants;
import com.insignia.constants.CommonConstant;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.MeasurementUnitRequest;
import com.insignia.model.MeasurementUnitResponse;
import com.insignia.serviceInterface.MeasurementUnitServiceInterface;
import com.insignia.validations.MeasurementUnitsValidation;

@CrossOrigin
@RestController
@RequestMapping("/measurementUnit")
public class MeasurementUnitController {

	@Autowired
	private MeasurementUnitServiceInterface measurementUnitServiceInterface;

	@PostMapping("/saveMeasurementUnit")
	public ResponseEntity<?> saveMeasurementUnit(@RequestBody MeasurementUnitRequest measurementUnitRequest)
			throws InvalidInputParametersException {
		try {
			validationsForMeasurementUnit(measurementUnitRequest);

			return ResponseEntity.ok(measurementUnitServiceInterface.saveMeasurementUnits(measurementUnitRequest));
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new MeasurementUnitResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new MeasurementUnitResponse(CommonConstant.validateTokenErrorCode, CommonConstant.validateToken));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MeasurementUnitResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	@PutMapping("/updateMeasurementUnit")
	public ResponseEntity<?> updateMeasurementUnit(@RequestBody MeasurementUnitRequest measurementUnitRequest)
			throws InvalidInputParametersException {
		try {
			validationsForMeasurementUnit(measurementUnitRequest);
			return ResponseEntity.ok(measurementUnitServiceInterface.updateMeasurementUnits(measurementUnitRequest));
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new MeasurementUnitResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new MeasurementUnitResponse(CommonConstant.validateTokenErrorCode, CommonConstant.validateToken));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MeasurementUnitResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	@PostMapping("/deleteMeasurementUnit")
	public ResponseEntity<?> deleteMeasurementUnit(@RequestBody MeasurementUnitRequest measurementUnitRequest)
			throws InvalidInputParametersException {
		try {
			measurementUnitServiceInterface.deleteMeasurementUnits(measurementUnitRequest);
			return ResponseEntity.ok(MeasurementConstants.successMessageForDelete);
		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new MeasurementUnitResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new MeasurementUnitResponse(CommonConstant.validateTokenErrorCode, CommonConstant.validateToken));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MeasurementUnitResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}

	}

	@PostMapping("/getAllMeasurementUnits")
	public ResponseEntity<?> getAllMeasurementUnit(@RequestBody MeasurementUnitRequest measurementUnitRequest) {
		try {
			return ResponseEntity.ok(measurementUnitServiceInterface.getAllMeasurementUnits(measurementUnitRequest));
		} catch (TokenExpiredException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new MeasurementUnitResponse(CommonConstant.validateTokenErrorCode, CommonConstant.validateToken));

		} catch (Exception exe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MeasurementUnitResponse(
					CommonConstant.validateUnexpectedErrorCode, CommonConstant.validateUnexpectedErrorMessage));
		}
	}

	private void validationsForMeasurementUnit(MeasurementUnitRequest measurementUnitRequest)
			throws InvalidInputParametersException {
		MeasurementUnitsValidation.ValidateUnitName(measurementUnitRequest.getUnitName(),
				MeasurementConstants.unitNameLength);
		MeasurementUnitsValidation.ValidateUnitDescription(measurementUnitRequest.getUnitDescription(),
				MeasurementConstants.unitDescriptionLength);
	}
}