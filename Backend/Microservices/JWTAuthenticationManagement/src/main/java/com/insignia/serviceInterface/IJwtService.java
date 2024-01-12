package com.insignia.serviceInterface;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.AuthenticationRequest;
import com.insignia.model.AuthenticationResponse;

public interface IJwtService {

	public void updateTokenDetails(Integer expirationTime, Boolean isRememberMeSelected, String jwt,
			Long customerSeqNumber);

	public void deleteTokenDetails(Long seqNo);

	public void createTokenDetails(Integer expirationTime, Boolean isRememberMeSelected, Long customerSeqNumber);

	public AuthenticationResponse fetchExistTokenIfPresent(AuthenticationResponse authRes,
			AuthenticationRequest authReq) throws Exception;

	public void updatePassword(AuthenticationRequest authenticationRequest)
			throws InvalidInputParametersException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, TokenExpiredException;

	public AuthenticationResponse checkTokenValidity(Long customerSequenceNumber) throws TokenExpiredException;
}
