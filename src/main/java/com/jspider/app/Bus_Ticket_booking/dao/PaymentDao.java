package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.entity.PaymentEntity;
import com.jspider.app.Bus_Ticket_booking.repo.PaymentRepo;

@Repository
public class PaymentDao 
{
	@Autowired
	PaymentRepo repo;
	
	
	
	
	public PaymentEntity savePayment(PaymentEntity pay)
	{
		return repo.save(pay);
	}
	
	
	public PaymentEntity findById(int id)
	{
		Optional<PaymentEntity> oppay  = repo.findById(id);
		
		if(oppay.isPresent())
		{
			return oppay.get();
		}
		else
		{
			return null;
		}
	}
	
	
	public PaymentEntity deletePayment(int  id)
	{
        PaymentEntity pay  = findById(id);
		
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
	
	
	public PaymentEntity updatePayment(PaymentEntity pay,  int  id)
	{
        PaymentEntity ex_pay  = findById(id);
		
		if(ex_pay!=null)
		{
			pay.setPayment_Id(id);
			
			return repo.save(pay);
		}
		else
		{
			return null;
		}
	}
	
	
	public List<PaymentEntity> getAllPaymentHistory()
	{
		List<PaymentEntity> paymentHistory = repo.findAll();
		
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

