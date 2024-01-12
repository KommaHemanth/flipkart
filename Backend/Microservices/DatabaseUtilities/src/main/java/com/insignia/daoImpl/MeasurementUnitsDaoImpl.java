package com.insignia.daoImpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insignia.daoInterface.MeasurementUnitsDaoInterface;
import com.insignia.entity.MeasurementUnits;
import com.insignia.repo.MeasurementUnitRepository;

@Repository
public class MeasurementUnitsDaoImpl implements MeasurementUnitsDaoInterface {

	@Autowired
	private MeasurementUnitRepository measurementUnitRepository;
	
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public MeasurementUnits saveMeasurementUnits(MeasurementUnits measurementUnits) {
		return measurementUnitRepository.save(measurementUnits);
	}

	@Override
	public MeasurementUnits updateMeasurementUnits(MeasurementUnits measurementUnits) {
		return entityManager.merge(measurementUnits);
	}

	@Override
	public void deleteMeasurementUnits(String unitName) {
		measurementUnitRepository.deleteByUnitName(unitName);

	}

	@Override
	public List<MeasurementUnits> getAllMeasurementUnits() {
		return measurementUnitRepository.findAll();
	}

	@Override
	public Object findByUnitName(String unitName) {
		return measurementUnitRepository.findByUnitName(unitName);
	}

	@Override
	public Optional<MeasurementUnits> findBySequenceNumber(Integer sequenceNumber) {
		
		return measurementUnitRepository.findById(sequenceNumber);
	}
	

}
