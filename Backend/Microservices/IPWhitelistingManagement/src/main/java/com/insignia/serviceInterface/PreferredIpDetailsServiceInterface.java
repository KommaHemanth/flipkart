package com.insignia.serviceInterface;

import java.util.List;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.PreferredIpDetailsRequest;
import com.insignia.model.PreferredIpDetailsResponse;

public interface PreferredIpDetailsServiceInterface {

	public PreferredIpDetailsResponse savePreferredIpDetails(PreferredIpDetailsRequest ipWhitelistingManagementRequest) throws InvalidInputParametersException, TokenExpiredException;

	public List<PreferredIpDetailsResponse> getAllPreferredIpDetails(PreferredIpDetailsRequest ipWhitelistingManagementRequest) throws TokenExpiredException;

	public void deletePreferredIpDetails(PreferredIpDetailsRequest ipWhitelistingManagementRequest) throws InvalidInputParametersException, TokenExpiredException;
}
