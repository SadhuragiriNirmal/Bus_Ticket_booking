package com.jspider.app.Bus_Ticket_booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Payment")
public class PaymentEntity
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int payment_Id;

	private String Payment_Type;
	
	private double Paid_Amount;
	
	private String Payment_Status;


	public int getPayment_Id() {
		return payment_Id;
	}


	public void setPayment_Id(int payment_Id) {
		this.payment_Id = payment_Id;
	}


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
