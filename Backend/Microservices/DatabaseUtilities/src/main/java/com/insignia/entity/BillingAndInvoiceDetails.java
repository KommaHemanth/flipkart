package com.insignia.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="customer_invoice_details")
public class BillingAndInvoiceDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_number")
	private Long invoiceNumber;

	@Column(name = "customer_sequence_number",unique=true)
	private Long customerSequenceNumber;

	@Column(name = "order_id",unique=true)
	private Long orderId;

	@Column(name = "invoice_date")
	private Date invoiceDate;

	@Column(name = "due_date")
	private Date dueDate;

	@Column(name = "status")
	private String status;

	@Column(name = "date_of_payment")
	private Date dateOfPayment;

	@Column(name = "currency")
	private String currency;

	@Column(name = "mode_of_payment")
	private String modeOfPayment;

	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Long getCustomerSequenceNumber() {
		return customerSequenceNumber;
	}

	public void setCustomerSequenceNumber(Long customerSequenceNumber) {
		this.customerSequenceNumber = customerSequenceNumber;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}


	@Override
	public String toString() {
		return "BillingAndInvoiceDetails [invoiceNumber=" + invoiceNumber + ", customerSequenceNumber="
				+ customerSequenceNumber + ", orderId=" + orderId + ", invoiceDate=" + invoiceDate + ", dueDate="
				+ dueDate + ", status=" + status
				+ ", dateOfPayment=" + dateOfPayment + ", currency=" + currency + ", modeOfPayment=" + modeOfPayment
				+ ", customerBasicDetails=" + "]";
	}

	public BillingAndInvoiceDetails()
	{
		
	}
	public BillingAndInvoiceDetails(Long customerSequenceNumber, Long orderId, Date invoiceDate,
			Date dueDate, String status, Date dateOfPayment,
			String currency, String modeOfPayment) {
		super();
		this.customerSequenceNumber = customerSequenceNumber;
		this.orderId = orderId;
		this.invoiceDate = invoiceDate;
		this.dueDate = dueDate;
		this.status = status;
		this.dateOfPayment = dateOfPayment;
		this.currency = currency;
		this.modeOfPayment = modeOfPayment;
	}
	
	public BillingAndInvoiceDetails(Long customerSequenceNumber,Long orderId,String status,Date dateOfPayment,String currency,String modeOfPayment)
	{
		this.customerSequenceNumber=customerSequenceNumber;
		this.orderId=orderId;
		this.status=status;
		this.dateOfPayment=dateOfPayment;
		this.currency=currency;
		this.modeOfPayment=modeOfPayment;
	}
	
	public BillingAndInvoiceDetails(Long customerSequenceNumber,Long orderId)
	{
		this.customerSequenceNumber=customerSequenceNumber;
		this.orderId=orderId;
	}
	
	public BillingAndInvoiceDetails(Long customerSequenceNumber)
	{
		this.customerSequenceNumber=customerSequenceNumber;
	}
	
	
}