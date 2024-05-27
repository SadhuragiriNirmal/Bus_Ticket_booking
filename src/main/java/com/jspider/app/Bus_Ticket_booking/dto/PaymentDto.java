package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;

@Component
public class PaymentDto 
{

	private String Payment_Type;

	private double Paid_Amount;
	
	private String Payment_Status;


	public String getPayment_Type() {
		return Payment_Type;
	}


	public void setPayment_Type(String payment_Type) {
		Payment_Type = payment_Type;
	}


	public double getPaid_Amount() {
		return Paid_Amount;
	}


	public void setPaid_Amount(double paid_Amount) {
		Paid_Amount = paid_Amount;
	}


	public String getPayment_Status() {
		return Payment_Status;
	}


	public void setPayment_Status(String payment_Status) {
		Payment_Status = payment_Status;
	}
		
	
}
