package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.PaymentDao;
import com.jspider.app.Bus_Ticket_booking.dto.PaymentDto;
import com.jspider.app.Bus_Ticket_booking.entity.PaymentEntity;
import com.jspider.app.Bus_Ticket_booking.exception.PaymentIdNotFound;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@Service
public class PaymentService 
{
	@Autowired
	PaymentDao pDao;
	
	@Autowired
	PaymentDto pDto;
	
	
	public ResponseEntity<ResponseStructure<PaymentDto>> savePayment(PaymentEntity history)
	{
		ResponseStructure<PaymentDto> structure = new ResponseStructure<>();
		
		PaymentEntity saveHistory = pDao.savePayment(history);
		
		if(saveHistory != null)
		{
			
			pDto.setPaid_Amount(saveHistory.getPaid_Amount());
			pDto.setPayment_Status(saveHistory.getPayment_Status());
			pDto.setPayment_Type(saveHistory.getPayment_Type());
			
			structure.setData(pDto);
			structure.setMessage("Payment saved successfully........");
			structure.setStatus(HttpStatus.CREATED.value());
			
			return new ResponseEntity<ResponseStructure<PaymentDto>>(structure, HttpStatus.CREATED);
		}
		else
		{
			return null;
		}
	}
	
	
	
	
	public ResponseEntity<ResponseStructure<PaymentDto>> findPayment(int id)
	{
		ResponseStructure<PaymentDto> structure = new ResponseStructure<>();
		
		PaymentEntity findPayment = pDao.findById(id);
		
		if(findPayment != null)
		{
			pDto.setPaid_Amount(findPayment.getPaid_Amount());
			pDto.setPayment_Status(findPayment.getPayment_Status());
			pDto.setPayment_Type(findPayment.getPayment_Type());
			
			structure.setData(pDto);
			structure.setMessage("Payment History Details Got successfully........");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<PaymentDto>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new PaymentIdNotFound("Payment ID Not Found in the Database");
		}
	}
	
	
	
	
	public ResponseEntity<ResponseStructure<PaymentDto>> updatePayment(PaymentEntity pay, int id)
	{
        ResponseStructure<PaymentDto> structure = new ResponseStructure<>();
		
		PaymentEntity ex_pay = pDao.updatePayment(pay, id);
		
		if(ex_pay !=pay)
		{
			pDto.setPaid_Amount(ex_pay.getPaid_Amount());
			pDto.setPayment_Status(ex_pay.getPayment_Status());
			pDto.setPayment_Type(ex_pay.getPayment_Type());
			
			structure.setData(pDto);
			structure.setMessage("Payment Details Updated successfully........");
			structure.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<PaymentDto>>(structure, HttpStatus.OK);
		}
		else
		{
			return null;
		}
	}
	
	
	
	public ResponseEntity<ResponseStructure<PaymentDto>> deletePayment(int id)
	{
		ResponseStructure<PaymentDto> structure = new ResponseStructure<>();
		
		PaymentEntity pay = pDao.deletePayment(id);
		
		if(pay != null)
		{
			pDto.setPaid_Amount(pay.getPaid_Amount());
			pDto.setPayment_Status(pay.getPayment_Status());
			pDto.setPayment_Type(pay.getPayment_Type());
			
			structure.setData(pDto);
			structure.setMessage("Payment History Details Deleted successfully........");
			structure.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<PaymentDto>>(structure, HttpStatus.OK);
		}
		else
		{
			return null;
		}
		
	}
	
	
	
	public ResponseEntity<ResponseStructure<List<PaymentDto>>> getAllPaymentHistorys()
	{
		ResponseStructure<List<PaymentDto>> structure = new ResponseStructure<>();
		
		List<PaymentEntity> paymentHistoryEntity = pDao.getAllPaymentHistory();
		
		List<PaymentDto> paymentHistory = new ArrayList<>();
		
		if(paymentHistoryEntity != null)
		{
			
			for(PaymentEntity p : paymentHistoryEntity)
			{
				pDto.setPaid_Amount(p.getPaid_Amount());
				pDto.setPayment_Status(p.getPayment_Status());
				pDto.setPayment_Type(p.getPayment_Type());
				
				paymentHistory.add(pDto);
			}
			
			structure.setData(paymentHistory);
			structure.setMessage("All payment History Get successfully........");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<PaymentDto>>>(structure, HttpStatus.FOUND);
		}
		else
		{
			return null;
		}
		
	}
}
