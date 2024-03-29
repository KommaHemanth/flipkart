package com.insignia.constants;

public class CustomerBasicDetailsConstants {
	public static final String validUserId = "Please enter userid";
	public static final String validUserIdErrorCode = "300";
	public static final String validUserIdLength = "Please enter userid length upto 60 characters only";
	public static final String validUserIdLengthErrorCode = "301";
	public static final String validUserIdExpression = "Please provide valid userid, the allowed characters are [ A-Z, a-z, 0-9, _, -, .] only";
	public static final String validUserIDInvalidCharactersErrorCode = "302";

	public static final String validApplicationId = "Please enter applicationid";
	public static final String validApplicationIdErrorCode = "303";
	public static final String validApplicationIdLength = "Please enter applicationid length upto 30 characters only";
	public static final String validApplicationIdLengthErrorCode = "304";
	public static final String validApplicationIdExpression = "Please provide valid applicationid, the allowed characters are [ A-Z, a-z, 0-9, _, -, .] only";
	public static final String validApplicationIdInvalidCharactersErrorCode = "305";

	public static final String validTenantId = "Please enter tenantid";
	public static final String validTenantIdErrorCode = "306";
	public static final String validTenantIdLength = "Please enter tenantid length upto 30 characters only";
	public static final String validTenantIdLengthErrorCode = "307";
	public static final String validTenantIdExpression = "Please provide valid tenantid, the allowed characters are [ A-Z, a-z, 0-9, _, -, .] only";
	public static final String validTenantIdInvalidCharactersErrorCode = "308";

	public static final String validPassword = "Please enter password";
	public static final String validPasswordErrorCode = "309";
	public static final String validPasswordLength = "Please enter password length upto 16 characters only";
	public static final String validPasswordLengthErrorCode = "310";
	public static final String validPasswordExpression = "Please provide valid password, the allowed characters are [ A-Z, a-z, 0-9, _, -, .] only";
	public static final String validPasswordInvalidCharactersErrorCode = "311";

	public static final String validCustomerEmailLength = "Please enter customer email length upto 60 characters only";
	public static final String validCustomerEmailLengthErrorCode = "338";
	public static final String validCustomerEmailExpression = "Please provide valid customer email, the allowed characters are [ A-Z, a-z, 0-9, _, -, @,.] only";
	public static final String validCustomerEmailInvalidCharactersErrorCode = "339";

	
	public static final String validUserNameLength = "Please enter user name length upto 60 characters only";
	public static final String validUserNameLengthErrorCode = "340";
	public static final String validUserNameExpression = "Please provide valid user name , the allowed characters are [ A-Z, a-z, 0-9, _, -, .] only";
	public static final String validUserNameInvalidCharactersErrorCode = "341";

	public static final String validateCustomerDetailsMessage = "Details already existing in the system";
	public static final String validateCustomerDetailsErrorCode = "327";
	
	
	public static final String validateOTP= "Please enter OTP";
	public static final String validateOTPErrorCode = "329";
	
	public static final String validateOTPLength= "Please enter OTP length 4 characters only";
	public static final String validateOTPLengthErrorCode = "330";
	
	public static final String validateOTPExpired= "OTP is expired";
	public static final String validateOTPExpiredErrorCode = "331";
	
	public static final String validateOtpNotSent= "Your otp is not sent";
	public static final String validateOtpNotSentErrorCode = "332";
	
	
	public static final String validateInvalidOtp= "Invalid OTP";
	public static final String validateInvalidOtpErrorCode = "333";
	

	
	public static final String validNewPassword = "Please enter new password";
	public static final String validNewPasswordErrorCode = "334";
	public static final String validNewPasswordMaxLength = "Please enter new password maximum length upto 16 characters only";
	public static final String validNewPasswordLengthErrorCode = "335";
	public static final String validNewPasswordMinLength = "New password should be minimum length of 8 characters";
	public static final String validNewPasswordExpression = "Please provide valid new password, the allowed characters are [ A-Z, a-z, 0-9, _, -, .] only";
	public static final String validNewPasswordInvalidCharactersErrorCode = "336";

	
	
	public static final String validateRole= "No role is assigned to current user, so access denied";
	public static final String validateRoleErrorCode = "408";
	
	public final static String tokenType = "JWT";

	public final static String tokenAlgoritham = "HS256";

	
	public static final String oldPasswordErrorCode = "339";
	public final static String oldPasswordErrorMessage = "Old Password is incorrect.";
	
	public static final String regularExpression = "^[A-Za-z0-9_\\-.@]+$";
	
	public static final String regularExpressionForEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";


}
