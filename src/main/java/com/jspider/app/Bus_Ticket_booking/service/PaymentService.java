package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.PaymentDao;
import com.jspider.app.Bus_Ticket_booking.dto.PaymentDto;
import com.jspider.app.Bus_Ticket_booking.entity.Payment;
import com.jspider.app.Bus_Ticket_booking.entity.Ticket;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@Service
public class PaymentService 
{
	@Autowired
	PaymentDao dao;
	
	@Autowired
	PaymentDto dto;
	
	@Autowired
	TicketService ticketservice;
	
	//save payment
	
	public ResponseEntity<ResponseStructure<PaymentDto>> savePayment(Payment pay, String uemail, String busno, String departureDate)
	{
		ResponseStructure<PaymentDto> structure = new ResponseStructure<>();
		Payment existPayment = dao.savePayment(pay);		
		if(existPayment != null)
		{
			
			dto.setPaidAmount(existPayment.getPaidAmount());
			dto.setPaymentStatus(existPayment.getPaymentStatus());
			dto.setPaymentType(existPayment.getPaymentType());
			structure.setData(dto);
			structure.setMessage("Payment saved successfully........");
			structure.setStatus(HttpStatus.CREATED.value());
			if(existPayment.getTicket() != null && existPayment != null) {
				
				Ticket ticket = existPayment.getTicket();
				ticket.setPayment(existPayment);
				ticketservice.saveTicket(ticket, uemail, busno, departureDate);
			}
			
			return new ResponseEntity<ResponseStructure<PaymentDto>>(structure, HttpStatus.CREATED);
		}
		else
		{
			return null;
		}
	}
	
	
	//get payment
	
	public ResponseEntity<ResponseStructure<PaymentDto>> findPayment(int id)
	{
		ResponseStructure<PaymentDto> structure = new ResponseStructure<>();
		Payment existPayment = dao.findById(id);
		
		if(existPayment != null)
		{
			dto.setPaidAmount(existPayment.getPaidAmount());
			dto.setPaymentStatus(existPayment.getPaymentStatus());
			dto.setPaymentType(existPayment.getPaymentType());
			structure.setData(dto);
			structure.setMessage("Payment History Details Got successfully........");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<PaymentDto>>(structure, HttpStatus.FOUND);
		}
		else return null;
		
	}
	
	
	//update payment
	
	public ResponseEntity<ResponseStructure<PaymentDto>> updatePayment(Payment pay, int pyid)
	{
        ResponseStructure<PaymentDto> structure = new ResponseStructure<>();
		
		Payment existPayment = dao.updatePayment(pay, pyid);
		
		if(existPayment != null)
		{
			dto.setPaidAmount(existPayment.getPaidAmount());
			dto.setPaymentStatus(existPayment.getPaymentStatus());
			dto.setPaymentType(existPayment.getPaymentType());
			structure.setData(dto);
			structure.setMessage("Payment Details Updated successfully........");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<PaymentDto>>(structure, HttpStatus.OK);
		}
		else
		{
			return null;
		}
	}
	
	//delete payment
	
	public ResponseEntity<ResponseStructure<PaymentDto>> deletePayment(int pyid)
	{
		ResponseStructure<PaymentDto> structure = new ResponseStructure<>();
		Payment existPayment = dao.deletePayment(pyid);
		
		if(existPayment != null)
		{
			dto.setPaidAmount(existPayment.getPaidAmount());
			dto.setPaymentStatus(existPayment.getPaymentStatus());
			dto.setPaymentType(existPayment.getPaymentType());
			structure.setData(dto);
			structure.setMessage("Payment History Details Deleted successfully........");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<PaymentDto>>(structure, HttpStatus.OK);
		}
		else
		{
			return null;
		}
		
	}
	
	
	//get all payment
	
	public ResponseEntity<ResponseStructure<List<PaymentDto>>> getAllPayment()
	{
		ResponseStructure<List<PaymentDto>> structure = new ResponseStructure<>();
		List<Payment> existPayments = dao.getAllPaymentHistory();
		List<PaymentDto> newPayment = new ArrayList<>();
		
		if(existPayments != null) {
			
			for(Payment payment : existPayments)
			{
				dto.setPaidAmount(payment.getPaidAmount());
				dto.setPaymentStatus(payment.getPaymentStatus());
				dto.setPaymentType(payment.getPaymentType());
				newPayment.add(dto);
			}
			structure.setData(newPayment);
			structure.setMessage("All payment History Get successfully........");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<PaymentDto>>>(structure, HttpStatus.FOUND);
		}
		else return null;
		
	}
}
