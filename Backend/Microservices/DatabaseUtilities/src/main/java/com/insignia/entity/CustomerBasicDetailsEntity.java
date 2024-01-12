package com.insignia.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customer_basic_details")
public class CustomerBasicDetailsEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "customer_sequence_number")
	private Long customerSequenceNumber;

	@Column(name = "fullName")
	private String fullName;

	@Column(name = "application_id")
	private String applicationId;

	@Column(name = "tenant_id")
	private String tenantId;

	@Column(name = "customer_id")
	private String customerId;

	@Column(name = "customer_password")
	private String customerPassword;

	@Column(name = "customer_email")
	private String customerEmail;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "OTP", length = 6)
	private String otp;

	@Column(name = "otp_generated_at")
	private Date otpGeneratedAt;

	@Column(name = "otp_expiry_at")
	private Date otpExpiryAt;

	@Column(name = "is_otp_authenticated")
	private Boolean isotpAuthenticated;
	
	@Column(name = "registered_on")
	 @Temporal(TemporalType.DATE)
	private Date registeredOn;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_sequence_number")
	private List<AddressDetails> addressDetails;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_sequence_number")
	private List<CustomerPersonalDetails> customerPersonalDetails;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_sequence_number")
	private List<RolesAndPermissions> rolesAndPermissions;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TokensEntity tokensEntity;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_sequence_number")
	private List<AppPreferenceDetailsEntity> appPreferenceDetailsEntity;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_sequence_number")
	private List<CustomerStoreDetails> customerStoreDetailsEntity;

	public Long getCustomerSequenceNumber() {
		return customerSequenceNumber;
	}

	public void setCustomerSequenceNumber(Long customerSequenceNumber) {
		this.customerSequenceNumber = customerSequenceNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getOtpGeneratedAt() {
		return otpGeneratedAt;
	}

	public void setOtpGeneratedAt(Date otpGeneratedAt) {
		this.otpGeneratedAt = otpGeneratedAt;
	}

	public Date getOtpExpiryAt() {
		return otpExpiryAt;
	}

	public void setOtpExpiryAt(Date otpExpiryAt) {
		this.otpExpiryAt = otpExpiryAt;
	}

	public Boolean getIsotpAuthenticated() {
		return isotpAuthenticated;
	}

	public void setIsotpAuthenticated(Boolean isotpAuthenticated) {
		this.isotpAuthenticated = isotpAuthenticated;
	}

	public List<AddressDetails> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(List<AddressDetails> addressDetails) {
		this.addressDetails = addressDetails;
	}

	public List<CustomerPersonalDetails> getCustomerPersonalDetails() {
		return customerPersonalDetails;
	}

	public void setCustomerPersonalDetails(List<CustomerPersonalDetails> customerPersonalDetails) {
		this.customerPersonalDetails = customerPersonalDetails;
	}

	public List<RolesAndPermissions> getRolesAndPermissions() {
		return rolesAndPermissions;
	}

	public void setRolesAndPermissions(List<RolesAndPermissions> rolesAndPermissions) {
		this.rolesAndPermissions = rolesAndPermissions;
	}

	public TokensEntity getTokensEntity() {
		return tokensEntity;
	}

	public void setTokensEntity(TokensEntity tokensEntity) {
		this.tokensEntity = tokensEntity;
	}

	public List<AppPreferenceDetailsEntity> getAppPreferenceDetailsEntity() {
		return appPreferenceDetailsEntity;
	}

	public void setAppPreferenceDetailsEntity(List<AppPreferenceDetailsEntity> appPreferenceDetailsEntity) {
		this.appPreferenceDetailsEntity = appPreferenceDetailsEntity;
	}

	public List<CustomerStoreDetails> getCustomerStoreDetailsEntity() {
		return customerStoreDetailsEntity;
	}

	public void setCustomerStoreDetailsEntity(List<CustomerStoreDetails> customerStoreDetailsEntity) {
		this.customerStoreDetailsEntity = customerStoreDetailsEntity;
	}

	
	public Date getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}

	public CustomerBasicDetailsEntity(String applicationId, String tenantId, String customerId, String customerPassword,
			String customerEmail, String userName, String otp, Date otpGeneratedAt, Date otpExpiryAt,
			Boolean isotpAuthenticated, List<AddressDetails> addressDetails,
			List<CustomerPersonalDetails> customerPersonalDetails, List<RolesAndPermissions> rolesAndPermissions,
			TokensEntity tokensEntity) {
		super();
		this.applicationId = applicationId;
		this.tenantId = tenantId;
		this.customerId = customerId;
		this.customerPassword = customerPassword;
		this.customerEmail = customerEmail;
		this.userName = userName;
		this.otp = otp;
		this.otpGeneratedAt = otpGeneratedAt;
		this.otpExpiryAt = otpExpiryAt;
		this.isotpAuthenticated = isotpAuthenticated;
		this.addressDetails = addressDetails;
		this.customerPersonalDetails = customerPersonalDetails;
		this.rolesAndPermissions = rolesAndPermissions;
		this.tokensEntity = tokensEntity;
	}

	public CustomerBasicDetailsEntity() {

	}

	public CustomerBasicDetailsEntity(String otp, Date otpExpiryAt, Long customerSequenceNumber) {
		super();
		this.otp = otp;
		this.otpExpiryAt = otpExpiryAt;
		this.customerSequenceNumber = customerSequenceNumber;
	}

	public CustomerBasicDetailsEntity(String applicationId, String tenantId, String customerId) {
		super();
		this.applicationId = applicationId;
		this.tenantId = tenantId;
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "CustomerBasicDetailsEntity [customerSequenceNumber=" + customerSequenceNumber + ", fullName=" + fullName
				+ ", applicationId=" + applicationId + ", tenantId=" + tenantId + ", customerId=" + customerId
				+ ", customerPassword=" + customerPassword + ", customerEmail=" + customerEmail + ", userName="
				+ userName + ", otp=" + otp + ", otpGeneratedAt=" + otpGeneratedAt + ", otpExpiryAt=" + otpExpiryAt
				+ ", isotpAuthenticated=" + isotpAuthenticated + ", registeredOn=" + registeredOn + ", addressDetails="
				+ addressDetails + ", customerPersonalDetails=" + customerPersonalDetails + ", rolesAndPermissions="
				+ rolesAndPermissions + ", tokensEntity=" + tokensEntity + ", appPreferenceDetailsEntity="
				+ appPreferenceDetailsEntity + ", customerStoreDetailsEntity=" + customerStoreDetailsEntity + "]";
	}

}
