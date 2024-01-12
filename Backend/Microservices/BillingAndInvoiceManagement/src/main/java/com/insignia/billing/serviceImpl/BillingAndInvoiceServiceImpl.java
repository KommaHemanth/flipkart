package com.insignia.billing.serviceImpl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insignia.billing.dao.BillingAndInvoiceDaoInterface;
import com.insignia.billing.daoImpl.BillingAndInvoiceDaoImpl;
import com.insignia.billing.model.BillingAndInvoiceDetailsModel;
import com.insignia.billing.model.BillingAndInvoiceRequest;
import com.insignia.billing.repo.BillingAndInvoiceRepository;
import com.insignia.billing.service.BillingAndInvoiceServiceInterface;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.stringConstant.BillingAndInvoiceDetailsConstant;


@Service
public class BillingAndInvoiceServiceImpl implements BillingAndInvoiceServiceInterface{
	
    @Autowired
    private BillingAndInvoiceDaoInterface billingAndInvoiceDaoInterface;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public BillingAndInvoiceServiceImpl(BillingAndInvoiceRepository customerPaymentMethodRepository){
    	billingAndInvoiceDaoInterface=new BillingAndInvoiceDaoImpl(customerPaymentMethodRepository);
    }

    @Override
    public void createInvoice(BillingAndInvoiceRequest billingAndInvoiceRequest) throws TokenExpiredException,InvalidInputParametersException, NumberFormatException, ParseException
    {
    	if (isTokenNotValid(Long.parseLong(billingAndInvoiceRequest.getCustomerSequenceNumber()))) 
    	{
    		throw new TokenExpiredException(BillingAndInvoiceDetailsConstant.validateTokenErrorCode,BillingAndInvoiceDetailsConstant.validateToken); 
    	}
		BillingAndInvoiceDetails billingAndInvoiceDetails = new BillingAndInvoiceDetails(Long.parseLong(billingAndInvoiceRequest.getCustomerSequenceNumber()),Long.parseLong(billingAndInvoiceRequest.getOrderId()),formatter.parse(billingAndInvoiceRequest.getInvoiceDate()),formatter.parse(billingAndInvoiceRequest.getDueDate()),billingAndInvoiceRequest.getStatus(),formatter.parse(billingAndInvoiceRequest.getDateOfPayment()),billingAndInvoiceRequest.getCurrency(),billingAndInvoiceRequest.getModeOfPayment());
		List<DiscountModel> discountModel = billingAndInvoiceRequest.getDiscounts();
    	int a=billingAndInvoiceDaoInterface.createInvoice(billingAndInvoiceDetails,discountModel);
    	if(a==0)
    	{
    		throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.noUserFoundErrorCode,BillingAndInvoiceDetailsConstant.noUserFoundErrorMessage);
    	}
    }
    
    @Override
    public void updateInvoice(BillingAndInvoiceRequest billingAndInvoiceRequest) throws TokenExpiredException,InvalidInputParametersException, NumberFormatException, ParseException
    {
    	if (isTokenNotValid(Long.parseLong(billingAndInvoiceRequest.getCustomerSequenceNumber()))) 
    	{
    		throw new TokenExpiredException(BillingAndInvoiceDetailsConstant.validateTokenErrorCode,BillingAndInvoiceDetailsConstant.validateToken); 
    	}
		BillingAndInvoiceDetails billingAndInvoiceDetails = new BillingAndInvoiceDetails(Long.parseLong(billingAndInvoiceRequest.getCustomerSequenceNumber()),Long.parseLong(billingAndInvoiceRequest.getOrderId()),billingAndInvoiceRequest.getStatus(),formatter.parse(billingAndInvoiceRequest.getDateOfPayment()),billingAndInvoiceRequest.getCurrency(),billingAndInvoiceRequest.getModeOfPayment());
    	int a=billingAndInvoiceDaoInterface.updateInvoice(billingAndInvoiceDetails);
    	if(a==0)
    	{
    		throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.noUserFoundErrorCode,BillingAndInvoiceDetailsConstant.noUserFoundErrorMessage);
    	}
    }
    
    @Override
    public List<BillingAndInvoiceDetailsModel> listAllInvoice(BillingAndInvoiceRequest billingAndInvoiceRequest) throws TokenExpiredException,InvalidInputParametersException
    {
    	if (isTokenNotValid(Long.parseLong(billingAndInvoiceRequest.getCustomerSequenceNumber()))) 
    	{
    		throw new TokenExpiredException(BillingAndInvoiceDetailsConstant.validateTokenErrorCode,BillingAndInvoiceDetailsConstant.validateToken); 
    	}
		BillingAndInvoiceDetails billingAndInvoiceDetails = new BillingAndInvoiceDetails(Long.parseLong(billingAndInvoiceRequest.getCustomerSequenceNumber()));
    	List<BillingAndInvoiceDetailsModel> billingAndInvoiceDetailsModel=null;
    	billingAndInvoiceDetailsModel=billingAndInvoiceDaoInterface.listAllInvoice(billingAndInvoiceDetails);
    	if(billingAndInvoiceDetailsModel == null || billingAndInvoiceDetailsModel.isEmpty()) {
        	throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.noDataFoundErrorCode,BillingAndInvoiceDetailsConstant.noDataFoundErrorMessage);
        } 
        return billingAndInvoiceDetailsModel;
    }
	
	private Boolean isTokenNotValid(Long customer_sequence_number)
	{
		return billingAndInvoiceDaoInterface.isTokenNotValid(customer_sequence_number); 
	}

	@Override
	public List<BillingAndInvoiceDetailsModel> listInvoice(BillingAndInvoiceRequest billingAndInvoiceRequest) throws TokenExpiredException, InvalidInputParametersException {
		if (isTokenNotValid(Long.parseLong(billingAndInvoiceRequest.getCustomerSequenceNumber()))) 
    	{
    		throw new TokenExpiredException(BillingAndInvoiceDetailsConstant.validateTokenErrorCode,BillingAndInvoiceDetailsConstant.validateToken); 
    	}
		BillingAndInvoiceDetails billingAndInvoiceDetails = new BillingAndInvoiceDetails(Long.parseLong(billingAndInvoiceRequest.getCustomerSequenceNumber()),Long.parseLong(billingAndInvoiceRequest.getOrderId()));
		List<BillingAndInvoiceDetailsModel> billingAndInvoiceDetailsModel=null;
    	billingAndInvoiceDetailsModel=billingAndInvoiceDaoInterface.listInvoice(billingAndInvoiceDetails);
    	if(billingAndInvoiceDetailsModel == null || billingAndInvoiceDetailsModel.isEmpty()) {
        	throw new InvalidInputParametersException(BillingAndInvoiceDetailsConstant.noDataFoundErrorCode,BillingAndInvoiceDetailsConstant.noDataFoundErrorMessage);
        } 
        return billingAndInvoiceDetailsModel;
	}
}
