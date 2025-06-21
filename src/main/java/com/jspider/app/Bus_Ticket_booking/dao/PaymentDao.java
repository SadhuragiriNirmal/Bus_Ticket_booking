package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.entity.Payment;
import com.jspider.app.Bus_Ticket_booking.repository.PaymentRepo;



@Repository
public class PaymentDao 
{
	@Autowired
	PaymentRepo repo;
	
	
	
	public Payment savePayment(Payment pay)
	{
		return repo.save(pay);
	}
	
	
	public Payment findById(int id)
	{
		Optional<Payment> oppay  = repo.findById(id);
		
		if(oppay.isPresent())
		{
			return oppay.get();
		}
		else
		{
			return null;
		}
	}
	
	
	public Payment deletePayment(int  id)
	{
        Payment pay  = findById(id);
		
		if(pay!=null)
		{
			repo.delete(pay);
			return pay;
		}
		else
		{
			return null;
		}
	}
	
	
	public Payment updatePayment(Payment pay,  int  id)
	{
        Payment ex_pay  = findById(id);
		
		if(ex_pay!=null)
		{
			pay.setPaymentId(id);
			return repo.save(pay);
		}
		else
		{
			return null;
		}
	}
	
	
	public List<Payment> getAllPaymentHistory()
	{
		List<Payment> paymentHistory = repo.findAll();
		
		if(paymentHistory != null)
		{
			return paymentHistory;
		}
		else
		{
			return null;
		}
	}
}

