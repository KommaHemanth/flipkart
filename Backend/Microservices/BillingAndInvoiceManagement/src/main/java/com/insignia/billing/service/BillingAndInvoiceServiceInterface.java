package com.insignia.billing.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.insignia.billing.model.BillingAndInvoiceDetailsModel;
import com.insignia.billing.model.BillingAndInvoiceRequest;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;

@Service
public interface BillingAndInvoiceServiceInterface {
	
	public void createInvoice(BillingAndInvoiceRequest billingAndInvoiceRequest) throws TokenExpiredException,InvalidInputParametersException, NumberFormatException, ParseException;
	
	public void updateInvoice(BillingAndInvoiceRequest billingAndInvoiceRequest) throws TokenExpiredException,InvalidInputParametersException, NumberFormatException, ParseException;
	
	public List<BillingAndInvoiceDetailsModel> listAllInvoice(BillingAndInvoiceRequest billingAndInvoiceRequest) throws TokenExpiredException,InvalidInputParametersException;
	
	public List<BillingAndInvoiceDetailsModel> listInvoice(BillingAndInvoiceRequest billingAndInvoiceRequest) throws TokenExpiredException,InvalidInputParametersException;
}
