package com.insignia.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insignia.entity.BillingAndInvoiceDetails;


@Repository
public interface BillingAndInvoiceRepository extends JpaRepository<BillingAndInvoiceDetails, Serializable> {
	
//	@Modifying
//    @Transactional
//    @Query(value = "INSERT INTO customer_billing_and_invoice_details (customer_sequence_number, order_id, invoice_date, due_date, status, date_of_payment, currency, mode_of_payment) VALUES (:customerSequenceNumber, :orderId, :invoiceDate, :dueDate, :status, :dateOfPayment, :currency, :modeOfPayment);", nativeQuery = true)
//	int createInvoice(Long customerSequenceNumber, Long orderId,Date invoiceDate, Date dueDate, String status, Date dateOfPayment, String currency, String modeOfPayment);
//	
//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE customer_billing_and_invoice_details SET status = :status, date_of_payment = :dateOfPayment, currency = :currency, mode_of_payment = :modeOfPayment WHERE customer_sequence_number = :customerSequenceNumber AND order_id = :orderId ;",nativeQuery = true)
//	int updateInvoice(
//	    @Param("status") String status,
//	    @Param("dateOfPayment") Date dateOfPayment,
//	    @Param("currency") String currency,
//	    @Param("modeOfPayment") String modeOfPayment,
//	    @Param("customerSequenceNumber") Long customerSequenceNumber,
//	    @Param("orderId") Long orderId
//	);
//	
//	@Query(value = "SELECT invoice_number, customer_sequence_number, order_id, invoice_date, due_date, status,  date_of_payment, currency, mode_of_payment FROM customer_billing_and_invoice_details WHERE customer_sequence_number = :customerSequenceNumber ;", nativeQuery = true)
//	List<BillingAndInvoiceDetails> listAllInvoice(
//		@Param("customerSequenceNumber") Long customerSequenceNumber
//	);
//	
//	@Query(value = "SELECT invoice_number, customer_sequence_number, order_id, invoice_date, due_date, status,  date_of_payment, currency, mode_of_payment FROM customer_billing_and_invoice_details WHERE customer_sequence_number = :customerSequenceNumber AND order_Id = :orderId ;", nativeQuery = true)
//	List<BillingAndInvoiceDetails> listInvoice(
//		@Param("customerSequenceNumber") Long customerSequenceNumber,
//		@Param("orderId") Long orderId
//	);
//	
//	
//	@Query(value = "SELECT token_expires_at FROM tokens_table WHERE customer_sequence_number = :customer_sequence_number AND token_type = 'JWT' AND (token_expires_at > CURRENT_TIMESTAMP OR token_expires_at IS NULL);", nativeQuery = true)
//	public List<Object[]> isTokenNotValid(Long customer_sequence_number);
//
//	
	
} 