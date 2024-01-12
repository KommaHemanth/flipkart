package com.insignia.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insignia.billing.constant.BillingAndInvoiceConstants;
import com.insignia.billing.model.BillingAndInvoiceDetailsModel;
import com.insignia.billing.model.BillingAndInvoiceRequest;
import com.insignia.billing.model.BillingAndInvoiceResponse;
import com.insignia.billing.model.DiscountModel;
import com.insignia.billing.service.BillingAndInvoiceServiceInterface;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.stringConstant.BillingAndInvoiceDetailsConstant;
import com.insignia.stringValidator.BillingAndInvoiceDetailsValidator;

@RestController
public class BillingAndInvoiceController {

	@Autowired
	private BillingAndInvoiceServiceInterface billingAndInvoiceServiceInterface;

	@PostMapping("/billing/createInvoice")
	public ResponseEntity<?> CreateInvoice(@RequestBody BillingAndInvoiceRequest billingAndInvoiceRequest) {
		
		try {
			BillingAndInvoiceDetailsValidator.ValidateCustomerSequenceNumber(billingAndInvoiceRequest.getCustomerSequenceNumber(),BillingAndInvoiceConstants.customerSequenceNumberLength);
			BillingAndInvoiceDetailsValidator.ValidateOrderId(billingAndInvoiceRequest.getOrderId(),BillingAndInvoiceConstants.orderIdLength);
			BillingAndInvoiceDetailsValidator.ValidateInvoiceDate(billingAndInvoiceRequest.getInvoiceDate(),BillingAndInvoiceConstants.dateLength);
			BillingAndInvoiceDetailsValidator.ValidateDueDate(billingAndInvoiceRequest.getDueDate(),BillingAndInvoiceConstants.dateLength);
			BillingAndInvoiceDetailsValidator.ValidateStatus(billingAndInvoiceRequest.getStatus(),BillingAndInvoiceConstants.orderIdLength,BillingAndInvoiceDetailsConstant.statusFalse);
			BillingAndInvoiceDetailsValidator.ValidateDateofPayment(billingAndInvoiceRequest.getDateOfPayment(),BillingAndInvoiceConstants.dateLength,BillingAndInvoiceDetailsConstant.statusFalse);
			BillingAndInvoiceDetailsValidator.ValidateCurrency(billingAndInvoiceRequest.getCurrency(),BillingAndInvoiceConstants.currencyLength,BillingAndInvoiceDetailsConstant.statusFalse);
			BillingAndInvoiceDetailsValidator.ValidateModeOfPayment(billingAndInvoiceRequest.getModeOfPayment(),BillingAndInvoiceConstants.modeOfPaymentLength,BillingAndInvoiceDetailsConstant.statusFalse);
			
			
			billingAndInvoiceServiceInterface.createInvoice(billingAndInvoiceRequest);
			BillingAndInvoiceResponse successResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusTrue,BillingAndInvoiceDetailsConstant.Null,BillingAndInvoiceDetailsConstant.Null);
			return ResponseEntity.ok(successResponse);
		} catch (TokenExpiredException e) {
			BillingAndInvoiceResponse errorResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusFalse,e.getErrorCode(),e.getStrMsg());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (InvalidInputParametersException e) {
			BillingAndInvoiceResponse errorResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusFalse,e.getErrorCode(),e.getStrMsg());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			BillingAndInvoiceResponse errorResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusFalse,BillingAndInvoiceDetailsConstant.createInvoiceErrorCode,BillingAndInvoiceDetailsConstant.createInvoiceErrorMessage);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		
	}

	@PutMapping("/billing/updateInvoice")
	public ResponseEntity<?> UpdateInvoice(@RequestBody BillingAndInvoiceRequest billingAndInvoiceRequest)
	{
		try {
			BillingAndInvoiceDetailsValidator.ValidateCustomerSequenceNumber(billingAndInvoiceRequest.getCustomerSequenceNumber(),BillingAndInvoiceConstants.customerSequenceNumberLength);
			BillingAndInvoiceDetailsValidator.ValidateOrderId(billingAndInvoiceRequest.getOrderId(),BillingAndInvoiceConstants.orderIdLength);
			BillingAndInvoiceDetailsValidator.ValidateStatus(billingAndInvoiceRequest.getStatus(),BillingAndInvoiceConstants.orderIdLength,BillingAndInvoiceDetailsConstant.statusTrue);
			BillingAndInvoiceDetailsValidator.ValidateDateofPayment(billingAndInvoiceRequest.getDateOfPayment(),BillingAndInvoiceConstants.dateLength,BillingAndInvoiceDetailsConstant.statusTrue);
			BillingAndInvoiceDetailsValidator.ValidateCurrency(billingAndInvoiceRequest.getCurrency(),BillingAndInvoiceConstants.currencyLength,BillingAndInvoiceDetailsConstant.statusTrue);
			BillingAndInvoiceDetailsValidator.ValidateModeOfPayment(billingAndInvoiceRequest.getModeOfPayment(),BillingAndInvoiceConstants.modeOfPaymentLength,BillingAndInvoiceDetailsConstant.statusTrue);
			
			billingAndInvoiceServiceInterface.updateInvoice(billingAndInvoiceRequest);
			BillingAndInvoiceResponse successResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusTrue,BillingAndInvoiceDetailsConstant.Null,BillingAndInvoiceDetailsConstant.Null);
			return ResponseEntity.ok(successResponse);
		} catch (TokenExpiredException e) {
			BillingAndInvoiceResponse errorResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusFalse,e.getErrorCode(),e.getStrMsg());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (InvalidInputParametersException e) {
			BillingAndInvoiceResponse errorResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusFalse,e.getErrorCode(),e.getStrMsg());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			BillingAndInvoiceResponse errorResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusFalse,BillingAndInvoiceDetailsConstant.updateInvoiceErrorCode,BillingAndInvoiceDetailsConstant.updateInvoiceErrorMessage);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	@GetMapping("/billing/listAllInvoice")
	public ResponseEntity<?> listAllInvoice(@RequestBody BillingAndInvoiceRequest billingAndInvoiceRequest)
	{
		try {
			BillingAndInvoiceDetailsValidator.ValidateCustomerSequenceNumber(billingAndInvoiceRequest.getCustomerSequenceNumber(),BillingAndInvoiceConstants.customerSequenceNumberLength);
			
			List<BillingAndInvoiceDetailsModel> billingAndInvoiceDetailsModel=billingAndInvoiceServiceInterface.listAllInvoice(billingAndInvoiceRequest);
			BillingAndInvoiceResponse successResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusTrue,BillingAndInvoiceDetailsConstant.Null,BillingAndInvoiceDetailsConstant.Null,billingAndInvoiceDetailsModel);
			return ResponseEntity.ok(successResponse);
		} catch (TokenExpiredException e) {
			BillingAndInvoiceResponse errorResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusFalse,e.getErrorCode(),e.getStrMsg());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (InvalidInputParametersException e) {
			BillingAndInvoiceResponse errorResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusFalse,e.getErrorCode(),e.getStrMsg());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			BillingAndInvoiceResponse errorResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusFalse,BillingAndInvoiceDetailsConstant.listInvoiceErrorCode,BillingAndInvoiceDetailsConstant.listInvoiceErrorMessage);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	@GetMapping("/billing/listInvoice")
	public ResponseEntity<?> listInvoice(@RequestBody BillingAndInvoiceRequest billingAndInvoiceRequest)
	{
		try{
			BillingAndInvoiceDetailsValidator.ValidateCustomerSequenceNumber(billingAndInvoiceRequest.getCustomerSequenceNumber(),BillingAndInvoiceConstants.customerSequenceNumberLength);
			BillingAndInvoiceDetailsValidator.ValidateOrderId(billingAndInvoiceRequest.getOrderId(),BillingAndInvoiceConstants.orderIdLength);
			
			List<BillingAndInvoiceDetailsModel> billingAndInvoiceDetailsModel=billingAndInvoiceServiceInterface.listInvoice(billingAndInvoiceRequest);
			BillingAndInvoiceResponse successResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusTrue,BillingAndInvoiceDetailsConstant.Null,BillingAndInvoiceDetailsConstant.Null,billingAndInvoiceDetailsModel);
			return ResponseEntity.ok(successResponse);
		} catch (TokenExpiredException e) {
			BillingAndInvoiceResponse errorResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusFalse,e.getErrorCode(),e.getStrMsg());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (InvalidInputParametersException e) {
			BillingAndInvoiceResponse errorResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusFalse,e.getErrorCode(),e.getStrMsg());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			BillingAndInvoiceResponse errorResponse = new BillingAndInvoiceResponse(BillingAndInvoiceDetailsConstant.statusFalse,BillingAndInvoiceDetailsConstant.listInvoiceErrorCode,BillingAndInvoiceDetailsConstant.listInvoiceErrorMessage);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
}
