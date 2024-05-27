package com.jspider.app.Bus_Ticket_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.app.Bus_Ticket_booking.dto.PaymentDto;
import com.jspider.app.Bus_Ticket_booking.entity.PaymentEntity;
import com.jspider.app.Bus_Ticket_booking.service.PaymentService;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("payment")
public class PaymentController 
{
	@Autowired
	PaymentService service;
	
	
	
	@Operation(summary = "Used to Save Payment Details")
	@PostMapping
	public ResponseEntity<ResponseStructure<PaymentDto>> savePaymnetDto(@RequestBody PaymentEntity history)
	{
		return service.savePayment(history);
	}
	
	
	@Operation(summary = "Used to Find Payment Details")
	@GetMapping
	public ResponseEntity<ResponseStructure<PaymentDto>> findPaymentHistory(@RequestParam int id)
	{
		return service.findPayment(id);
	}
	
	
	@Operation(summary = "Used to Delete Payment Details")
	@DeleteMapping
	public ResponseEntity<ResponseStructure<PaymentDto>> deletePaymentHistory(@RequestParam int id)
	{
		return service.deletePayment(id);
	}
	
	
	
	@Operation(summary = "Used to Update Aadhar Details")
	@PutMapping
	public ResponseEntity<ResponseStructure<PaymentDto>> updateAadhar(@RequestBody PaymentEntity pay , @RequestParam int id)
	{
		return service.updatePayment(pay, id);
	}
	
	
	
	@Operation(summary = "Used to Get All Payment History Details")
	@GetMapping("paymentDetails")
	public ResponseEntity<ResponseStructure<List<PaymentDto>>> getAllPayments()
	{
		return service.getAllPaymentHistorys();
	}
}
