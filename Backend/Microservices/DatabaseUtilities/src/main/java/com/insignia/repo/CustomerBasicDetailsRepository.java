package com.insignia.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insignia.entity.CustomerBasicDetailsEntity;

public interface CustomerBasicDetailsRepository extends JpaRepository<CustomerBasicDetailsEntity, Serializable> {
	
	public static final String fetchOtpDetails = "SELECT new CustomerBasicDetailsEntity(otp, otpExpiryAt,customerSequenceNumber) FROM CustomerBasicDetailsEntity"
			+ " WHERE applicationId = :application_id AND tenantId = :tenant_id AND customerId = :customer_id";

	
	public static final String getPassword = "select customer_password from customer_basic_details where application_id = :application_id AND tenant_id = :tenant_id AND customer_id = :customer_id";
	
	public static final String updatePassword = "Update customer_basic_details set customer_password =:customer_password where customer_id =:customer_id";
	
	public static final String getOtp = "select OTP, OTP_expiry_at from customer_basic_details where application_id = :application_id AND tenant_id = :tenant_id AND customer_id = :customer_id";
	
	public static final String getCustomerAndStoreInformation=	"SELECT cbd.full_Name, cbd.registered_on, csd.store_name, csd.store_contact, csd.store_address, csd.business_type FROM customer_basic_details cbd LEFT JOIN customer_store_details csd ON cbd.customer_sequence_number = csd.customer_sequence_number";

	@Modifying
	@Query(value = updatePassword, nativeQuery = true)
	public void updatePassword(@Param("customer_password") String customerPassword, @Param("customer_id") String customerId);
	
	@Query(value = fetchOtpDetails)
	public CustomerBasicDetailsEntity fetchOtpDetails(@Param("application_id") String applicationId,
			@Param("tenant_id") String tenantId, @Param("customer_id") String customerId);
	
	
	public final static String FETCH_USER_DETAILS = "SELECT CONCAT(customer_id,application_id,tenant_id) AS userName,COALESCE(customer_password,'') AS PASSWORD,cbd.customer_sequence_number,rol.role_name,rol.role_id,rol.permission1,rol.permission2, rol.permission3,rol.permission4,rol.role_approved_date, rol.role_revoked_date, rol.permission_change_date,rol.updated_permissions,cbd.OTP,cbd.is_otp_authenticated,cbd.OTP_generated_at,cbd.OTP_expiry_at FROM customer_basic_details AS cbd LEFT JOIN roles_and_permissions AS rol ON cbd.customer_sequence_number=rol.customer_sequence_number WHERE CONCAT(customer_id,application_id,tenant_id)=:userName or (customer_id=:userName and customer_password='')";
	

	@Query(value = FETCH_USER_DETAILS, nativeQuery = true)
	public List<Object[]> fetchUserDetails(@Param("userName") String userName);
	
	
	@Query(value = getPassword, nativeQuery = true)
	public Object[] getPassword(@Param("application_id") String applicationId,
			@Param("tenant_id") String tenantId, @Param("customer_id") String customerId);
	
	
	@Query(value = getOtp, nativeQuery = true)
	public List<Object[]> getOtp(@Param("application_id") String applicationId,
			@Param("tenant_id") String tenantId, @Param("customer_id") String customerId);


	@Query(value = getCustomerAndStoreInformation, nativeQuery = true)
	public List<Object[]> getCustomerAndStoreInformation();


}
