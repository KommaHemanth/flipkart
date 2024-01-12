package com.insignia.serviceInterface;

import javax.mail.MessagingException;

import com.insignia.model.AuthenticationRequest;
import com.insignia.model.AuthenticationResponse;

public interface OtpServiceInterface {
	public AuthenticationResponse generateAndSaveOtp(AuthenticationRequest authenticationRequest)
			throws MessagingException;

}
