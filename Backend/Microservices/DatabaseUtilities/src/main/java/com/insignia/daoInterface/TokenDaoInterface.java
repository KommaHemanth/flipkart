package com.insignia.daoInterface;

import com.insignia.customExceptions.TokenExpiredException;

public interface TokenDaoInterface {

	public void checkTokenValidity(Long customer_sequence_number, Integer expirationDuration) throws TokenExpiredException;

}
