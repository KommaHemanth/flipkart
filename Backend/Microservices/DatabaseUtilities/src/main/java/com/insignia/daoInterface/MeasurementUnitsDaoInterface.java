package com.insignia.daoInterface;

import java.util.List;
import java.util.Optional;

import com.insignia.entity.MeasurementUnits;

public interface MeasurementUnitsDaoInterface {

	public MeasurementUnits saveMeasurementUnits(MeasurementUnits measurementUnits);
	
	public MeasurementUnits updateMeasurementUnits(MeasurementUnits measurementUnits);
	
	public void deleteMeasurementUnits(String unitName);
	
	public List<MeasurementUnits> getAllMeasurementUnits();
	
	public Object findByUnitName(String unitName);
	
	public Optional<MeasurementUnits> findBySequenceNumber(Integer sequenceNumber);
}
