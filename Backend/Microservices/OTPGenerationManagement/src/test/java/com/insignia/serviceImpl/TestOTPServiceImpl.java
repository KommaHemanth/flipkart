package com.insignia.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

import com.insignia.daoInterface.OtpDaoInterface;
import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.entity.RolesAndPermissions;
import com.insignia.model.AuthenticationRequest;
import com.insignia.model.AuthenticationResponse;

@ExtendWith(MockitoExtension.class)
public class TestOTPServiceImpl {

	@Value("${spring.mail.username}")
	private String username;

	@Mock
	private OtpDaoInterface otpDao;

	@Mock
	private JavaMailSender javaMailSender;

	@InjectMocks
	private OtpServiceImpl otpService;

	@Mock
	private MimeMessage mimeMessage;

	AuthenticationRequest authenticationRequest = new AuthenticationRequest();
	CustomerBasicDetailsEntity customerBasicDetailsEntity = new CustomerBasicDetailsEntity();
	AuthenticationResponse authenticationResponse = new AuthenticationResponse();
	CustomerBasicDetailsEntity initializeCustmerDetailsEntity = new CustomerBasicDetailsEntity();
	RolesAndPermissions rolesAndPermissions = new RolesAndPermissions();

	public void dataInitilization() throws MessagingException {
		rolesAndPermissions.setRoleName("user");
		authenticationRequest.setApplicationId("112");
		authenticationRequest.setTenantId("1124");
		authenticationRequest.setUserId("lakshminagu54@gmail.com");
		authenticationRequest.setEmail("lakshminagu54@gmail.com");
		authenticationRequest.setOtpExpiryDuration(5);
		authenticationRequest.setOtp("345698");

		customerBasicDetailsEntity.setApplicationId("112");
		customerBasicDetailsEntity.setTenantId("1124");
		customerBasicDetailsEntity.setCustomerId("2545");
		customerBasicDetailsEntity.setCustomerPassword("5485");
		customerBasicDetailsEntity.setCustomerSequenceNumber(8L);
		customerBasicDetailsEntity.setCustomerEmail("lakshminagu54@gmail.com");
		customerBasicDetailsEntity.setUserName("Lakshmi");
		customerBasicDetailsEntity.setOtp("096587");
		customerBasicDetailsEntity.setOtpExpiryAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60));

		authenticationResponse.setOtp("345698");

	}

//	@Test
//	public void existingOtpTest() throws MessagingException, NoSuchMethodException, SecurityException,
//			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		
//		authenticationRequest.setOtp("345698");
//		customerBasicDetailsEntity.setOtpExpiryAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60));
//		
//		when(otpDao.fetchOtpDetails(any(CustomerBasicDetailsEntity.class))).thenReturn(customerBasicDetailsEntity);
//		assertNotNull(otpService.generateAndSaveOtp(authenticationRequest));
//	
//	}
//	
	@Test
	public void testCreateResponse() throws Exception {

		dataInitilization();

		OtpDaoInterface otpDao = Mockito.mock(OtpDaoInterface.class);
		OtpServiceImpl otpService = new OtpServiceImpl(otpDao);
		when(otpDao.fetchOtpDetails(any(CustomerBasicDetailsEntity.class))).thenReturn(customerBasicDetailsEntity);
		Method method = OtpServiceImpl.class.getDeclaredMethod("fetchOtpDetails", AuthenticationRequest.class);
		method.setAccessible(true);
		Object invoke = method.invoke(otpService, authenticationRequest);

		assertNotNull(invoke);
	}

	@Test
	public void testGenerateResponse() throws Exception {
		dataInitilization();

		javaMailSender = mock(JavaMailSender.class);
		when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
		otpService = new OtpServiceImpl(javaMailSender);

		Method method = OtpServiceImpl.class.getDeclaredMethod("generateResponse", AuthenticationRequest.class,
				CustomerBasicDetailsEntity.class, RolesAndPermissions.class, StringBuilder.class);
		method.setAccessible(true);
		Object invoke = method.invoke(otpService, authenticationRequest, customerBasicDetailsEntity,rolesAndPermissions,
				new StringBuilder("123456"));
		assertNotNull(invoke);
	}

	@Test
	public void testMailSender() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

		OtpDaoInterface otpDao = Mockito.mock(OtpDaoInterface.class);
		authenticationRequest.setOtp("789765");
		authenticationRequest.setUserId("lakshminagu54@gmail.com");
		authenticationRequest.setEmail("exampletest@gmail.com");

		OtpServiceImpl otpService = new OtpServiceImpl(otpDao);

		org.springframework.test.util.ReflectionTestUtils.setField(otpService, "username", "125254");

		mimeMessage = Mockito.mock(MimeMessage.class);
		javaMailSender = mock(JavaMailSender.class);
		when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
		otpService = new OtpServiceImpl(javaMailSender);

		Method method = OtpServiceImpl.class.getDeclaredMethod("mailSender", AuthenticationRequest.class);
		method.setAccessible(true);

		assertNull(method.invoke(otpService, authenticationRequest));
	}

}
