package com.jspider.app.Bus_Ticket_booking.restcontroller;

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
import com.jspider.app.Bus_Ticket_booking.entity.Payment;
import com.jspider.app.Bus_Ticket_booking.service.PaymentService;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;


@RestController
@RequestMapping("payment")
public class PaymentController 
{
	@Autowired
	PaymentService service;
	
	//save payment
	
	@PostMapping
	public ResponseEntity<ResponseStructure<PaymentDto>> savePaymnetDto(@RequestBody Payment pay, @RequestParam int userid)
	{
		return service.savePayment(pay, userid);
	}
	
	//get payment
	
	@GetMapping
	public ResponseEntity<ResponseStructure<PaymentDto>> findPaymentHistory(@RequestParam int payid)
	{
		return service.findPayment(payid);
	}
	
	//update payment
	
	@PutMapping
	public ResponseEntity<ResponseStructure<PaymentDto>> updateAadhar(@RequestBody Payment pay , @RequestParam int payid)
	{
		return service.updatePayment(pay, payid);
	}
	
	//delete payment
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<PaymentDto>> deletePaymentHistory(@RequestParam int payid)
	{
		return service.deletePayment(payid);
	}
	
	//get all payment
	
	@GetMapping("paymentDetails")
	public ResponseEntity<ResponseStructure<List<PaymentDto>>> getAllPayments()
	{
		return service.getAllPayment();
	}
}
