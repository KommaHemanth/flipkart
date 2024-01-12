package com.insignia.serviceImpl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.insignia.constants.CustomerBasicDetailsConstants;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.JwtDao;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.decryption.PasswordDecoder;
import com.insignia.encryption.PasswordEncoder;
import com.insignia.entity.TokensEntity;
import com.insignia.model.AuthenticationRequest;
import com.insignia.model.AuthenticationResponse;
import com.insignia.repo.TokensEntityRepo;
import com.insignia.serviceInterface.IJwtService;
import com.insignia.util.Constants;

@Service
public class IJwtServiceImpl implements IJwtService {

	@Autowired
	private JwtDao jwtdao;

	@Autowired
	private TokenDaoInterface tokenDao;

	public AuthenticationResponse fetchExistTokenIfPresent(AuthenticationResponse authRes,
			AuthenticationRequest authReq) throws Exception {
		Optional<List<TokensEntity>> tokenDetails = jwtdao.fetchTokendetails(authRes.getCustomerSeqNumber());

		if (tokenDetails.isPresent()) {
			Optional<TokensEntity> isLongLivedToken = tokenDetails.get().stream()
					.filter(p -> p.getIsLongLivedToken() && p.getTokenRevokedAt() == null).findFirst();

			if (isLongLivedToken.isPresent()) {

				longLivedToken(isLongLivedToken.get(), authRes);

				return authRes;

			} else {

				Optional<TokensEntity> activeJwtToken = tokenDetails.get().stream().filter(p -> !p.getIsTokenExpired()
						&& p.getTokenRevokedAt() == null && p.getTokenExpiresAt().compareTo(new Date()) == 1)
						.findFirst();

				activeOrExpiredToken(activeJwtToken, authRes, authReq);

				return authRes;

			}
		} else {
			createTokenDetails(authReq.getExpirationTime(), authReq.isRememberMeSelected(),
					authRes.getCustomerSeqNumber());
			authRes.setTokenStatus(Constants.statusNewToken);
			return authRes;
		}
	}

	private void activeOrExpiredToken(Optional<TokensEntity> activeJwtToken, AuthenticationResponse authRes,
			AuthenticationRequest authReq) {
		if (activeJwtToken.isPresent()) {
			authRes.setExpirationTime(activeJwtToken.get().getTokenExpiresAt());
			authRes.setType(activeJwtToken.get().getTokenType());
			authRes.setToken(activeJwtToken.get().getTokenDetails());
			authRes.setTokenStatus(Constants.statusTokenValid);
		} else {
			deleteTokenDetails(authRes.getCustomerSeqNumber());
			createTokenDetails(authReq.getExpirationTime(), authReq.isRememberMeSelected(),
					authRes.getCustomerSeqNumber());
			authRes.setTokenStatus(TokensEntityRepo.createToken);
		}
	}

	private void longLivedToken(TokensEntity tokensEntity, AuthenticationResponse authRes) {
		authRes.setExpirationTime(tokensEntity.getTokenExpiresAt());
		authRes.setType(tokensEntity.getTokenType());
		authRes.setToken(tokensEntity.getTokenDetails());
		authRes.setTokenStatus(Constants.statusTokenLongLived);
	}

	public void updateTokenDetails(Integer expirationTime, Boolean isRememberMeSelected, String jwt,
			Long customerSeqNumber) {
		jwtdao.updateTokenDetails(expirationTime, isRememberMeSelected, jwt, customerSeqNumber);

	}

	public void deleteTokenDetails(Long seqNo) {
		jwtdao.deleteTokenDetails(seqNo);
	}

	public void createTokenDetails(Integer expirationTime, Boolean isRememberMeSelected, Long customerSeqNumber) {
		jwtdao.createTokenDetails(expirationTime, isRememberMeSelected, customerSeqNumber);
	}

	@Transactional
	@Modifying
	@Override
	public void updatePassword(AuthenticationRequest authenticationRequest)
			throws InvalidInputParametersException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, TokenExpiredException {

		String oldPassword = authenticationRequest.getPassword();

		if (authenticationRequest.getIsOTPAuthentication()) {

			if (authenticationRequest.getoTP() == null || authenticationRequest.getoTP().trim().isEmpty()) {
				throw new InvalidInputParametersException(CustomerBasicDetailsConstants.validateOTPErrorCode,
						CustomerBasicDetailsConstants.validateOTP);
			} else {
				List<Object[]> getOtpFromDatabase = jwtdao.getOtp(authenticationRequest.getApplicationId(),
						authenticationRequest.getTenantId(), authenticationRequest.getUserId());

				if (!authenticationRequest.getoTP().equalsIgnoreCase((String) getOtpFromDatabase.get(0)[0])) {

					throw new InvalidInputParametersException(CustomerBasicDetailsConstants.validateInvalidOtpErrorCode,
							CustomerBasicDetailsConstants.validateInvalidOtp);
				}

				if ((getOtpFromDatabase.get(0)[1]) == null
						|| (((Date) getOtpFromDatabase.get(0)[1])).compareTo(new Date()) < 0) {

					throw new InvalidInputParametersException(CustomerBasicDetailsConstants.validateOTPExpiredErrorCode,
							CustomerBasicDetailsConstants.validateOTPExpired);
				}
			}

		} else {

			tokenDao.checkTokenValidity(authenticationRequest.getCustomerSequenceNumber(),
					authenticationRequest.getExpirationDuration());

			if (oldPassword == null || oldPassword.trim().isEmpty()) {
				throw new InvalidInputParametersException(CustomerBasicDetailsConstants.oldPasswordErrorCode,
						CustomerBasicDetailsConstants.oldPasswordErrorMessage);
			} else {
				Object[] password = jwtdao.getPassword(authenticationRequest.getApplicationId(),
						authenticationRequest.getTenantId(), authenticationRequest.getUserId());

				if (!oldPassword.equals(PasswordDecoder.getDecryptedPassword(String.valueOf(password[0])))) {
					throw new InvalidInputParametersException(CustomerBasicDetailsConstants.oldPasswordErrorCode,
							CustomerBasicDetailsConstants.oldPasswordErrorMessage);
				}
			}

		}

		jwtdao.updatePassword(PasswordEncoder.getEncryptedPassword(authenticationRequest.getNewPassword()),
				authenticationRequest.getUserId());

	}

	@Override
	public AuthenticationResponse checkTokenValidity(Long customerSequenceNumber) throws TokenExpiredException {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();

		try {

			tokenDao.checkTokenValidity(customerSequenceNumber, 0);

			authenticationResponse.setTokenStatus(Constants.statusTokenValid);
		} catch (TokenExpiredException e) {
			authenticationResponse.setTokenStatus(Constants.statusExpired);
		}

		return authenticationResponse;
	}

}
