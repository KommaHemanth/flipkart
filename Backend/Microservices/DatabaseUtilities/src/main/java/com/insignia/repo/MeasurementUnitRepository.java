package com.insignia.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insignia.entity.MeasurementUnits;

public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnits, Serializable> {

	public static final String UNIT_NAME = "unit_name";
	public static final String findByUnitName = "select unit_name from measurement_units where unit_name = :unit_name";
	public static final String deleteByUnitName = "delete from measurement_units where unit_name = :unit_name";

	@Query(value = findByUnitName, nativeQuery = true)
	public Object findByUnitName(@Param(UNIT_NAME) String unitName);

	@Modifying
	@Query(value = deleteByUnitName, nativeQuery = true)
	public void deleteByUnitName(@Param(UNIT_NAME) String unitName);

}
