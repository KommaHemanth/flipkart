package com.insignia.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.insignia.conistant.OtpConstants;
import com.insignia.daoInterface.OtpDaoInterface;
import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.entity.RolesAndPermissions;
import com.insignia.model.AuthenticationRequest;
import com.insignia.model.AuthenticationResponse;
import com.insignia.serviceInterface.OtpServiceInterface;

@Service

public class OtpServiceImpl implements OtpServiceInterface {

	@Autowired
	private OtpDaoInterface otpDao;

	@Value("${spring.mail.username}")
	private String username;

	@Autowired
	private JavaMailSender javaMailSender;

	public OtpServiceImpl() {
		super();
	}

	public OtpServiceImpl(JavaMailSender javaMailSender) {
		super();

		this.javaMailSender = javaMailSender;
	}

	public OtpServiceImpl(OtpDaoInterface otpDao) {
		super();

		this.otpDao = otpDao;
	}

	@Override
	public AuthenticationResponse generateAndSaveOtp(AuthenticationRequest authenticationRequest)
			throws MessagingException {
		CustomerBasicDetailsEntity customerEntity = new CustomerBasicDetailsEntity();
		AuthenticationResponse authResponse = new AuthenticationResponse();
		RolesAndPermissions rolesAndPermissions = new RolesAndPermissions();

		CustomerBasicDetailsEntity existingDetails = fetchOtpDetails(authenticationRequest);

		String text = "1234567890";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		IntStream.rangeClosed(1, 6).forEach(i -> sb.append(text.charAt(random.nextInt(text.length()))));

		if (existingDetails == null) {

			existingDetails = new CustomerBasicDetailsEntity();
			CustomerBasicDetailsEntity otp = generateResponse(authenticationRequest, existingDetails,
					rolesAndPermissions, sb);
			CustomerBasicDetailsEntity saveCustomerEntity = otpDao.generateAndSaveOtp(otp);
			authResponse.setOtp(saveCustomerEntity.getOtp());

		} else {

			if (existingDetails.getOtp()==null|| existingDetails.getOtpExpiryAt() == null) {
				CustomerBasicDetailsEntity generateRandomOtp = generateResponse(authenticationRequest, existingDetails,
						rolesAndPermissions, sb);
				CustomerBasicDetailsEntity randomOtp = otpDao.mergeCustomerDetails(generateRandomOtp);
				authResponse.setOtp(randomOtp.getOtp());
				customerEntity.setOtp(randomOtp.getOtp());

			} else if (existingDetails.getOtpExpiryAt().compareTo(new Date()) > 0) {

				authResponse.setOtp(existingDetails.getOtp());
				return authResponse;
			} else {
				
				CustomerBasicDetailsEntity generateRandomOtp = generateResponse(authenticationRequest, existingDetails,
						rolesAndPermissions, sb);
				CustomerBasicDetailsEntity randomOtp = otpDao.mergeCustomerDetails(generateRandomOtp);
				authResponse.setOtp(randomOtp.getOtp());
				customerEntity.setOtp(randomOtp.getOtp());
				
				existingDetails.setOtp(randomOtp.getOtp());
				existingDetails.setOtpExpiryAt(randomOtp.getOtpExpiryAt());
			}

		}
		return authResponse;

	}

	private CustomerBasicDetailsEntity generateResponse(AuthenticationRequest authenticationRequest,
			CustomerBasicDetailsEntity existingDetails, RolesAndPermissions rolesAndPermissions, StringBuilder sb)
			throws MessagingException {
		authenticationRequest.setOtp(sb.toString());
		mailSender(authenticationRequest);

		existingDetails.setApplicationId(authenticationRequest.getApplicationId());
		existingDetails.setTenantId(authenticationRequest.getTenantId());
		existingDetails.setCustomerId(authenticationRequest.getUserId());
		existingDetails.setCustomerEmail(authenticationRequest.getEmail());
		existingDetails.setOtp(sb.toString());
		existingDetails.setOtpGeneratedAt(new Date());
		existingDetails.setOtpExpiryAt(
				new Date(System.currentTimeMillis() + 1000 * 60 * authenticationRequest.getOtpExpiryDuration()));
		List<RolesAndPermissions> rolesAndPermissionsList = new ArrayList<>();
		rolesAndPermissions.setRoleName(OtpConstants.roleName);
		rolesAndPermissionsList.add(rolesAndPermissions);
		existingDetails.setRolesAndPermissions(rolesAndPermissionsList);
		return existingDetails;
	}

	private void mailSender(AuthenticationRequest authenticationRequest) throws MessagingException {
		MimeMessage mailMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);

		messageHelper.setTo(authenticationRequest.getUserId());
		messageHelper.setFrom(username != null ? username : "test@gmail.com");

		messageHelper.setSubject("This is otp verification");
		messageHelper.setText(authenticationRequest.getOtp());

		javaMailSender.send(mailMessage);
	}

	private CustomerBasicDetailsEntity fetchOtpDetails(AuthenticationRequest authenticationRequest) {

		CustomerBasicDetailsEntity customerBasicDetailsEntity = new CustomerBasicDetailsEntity();
		customerBasicDetailsEntity.setCustomerId(authenticationRequest.getUserId());
		customerBasicDetailsEntity.setApplicationId(authenticationRequest.getApplicationId());
		customerBasicDetailsEntity.setTenantId(authenticationRequest.getTenantId());
		return otpDao.fetchOtpDetails(customerBasicDetailsEntity);
	}

}
