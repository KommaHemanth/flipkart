package com.insignia.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insignia.entity.CartInformation;

@Repository
public interface CartRepo extends JpaRepository<CartInformation, Serializable> {

	List<CartInformation> findByCustomerSequenceNumber(Long customerSequenceNumber);	
	
}
