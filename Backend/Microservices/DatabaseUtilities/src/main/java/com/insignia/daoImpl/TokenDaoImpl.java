package com.insignia.daoImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insignia.constants.CommonConstant;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.TokenDaoInterface;
import com.insignia.repo.TokensEntityRepo;

@Repository
public class TokenDaoImpl implements TokenDaoInterface {

	@Autowired
	private TokensEntityRepo tokenRepo;

	public void checkTokenValidity(Long customer_sequence_number, Integer expirationDuration)
			throws TokenExpiredException {

		if (tokenRepo.checkTokenValidity(customer_sequence_number, new Date()).isEmpty()) {
			throw new TokenExpiredException(CommonConstant.validateTokenErrorCode, CommonConstant.validateToken);

		} else {

			if (expirationDuration != null && expirationDuration > 0) {
				tokenRepo.updateTokenValidity((new Date(System.currentTimeMillis() + 1000 * 60 * expirationDuration)),
						new Date(), customer_sequence_number);
			}
		}
	}
}
