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

import com.jspider.app.Bus_Ticket_booking.dto.BookingHistoryDto;
import com.jspider.app.Bus_Ticket_booking.entity.BookingHistoryEntity;
import com.jspider.app.Bus_Ticket_booking.service.BookingHistoryService;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("history")
public class BookingHistoryController 
{
	
	@Autowired
	BookingHistoryService service;
	
	
	@Operation(summary = "Used to Save Booking History Details")
	@PostMapping
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> saveBookingHistoryDto(@RequestBody BookingHistoryEntity history)
	{
		return service.saveBookingHistory(history);
	}
	
	
	@Operation(summary = "Used to Find Booking History Details")
	@GetMapping
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> findBookingHistory(@RequestParam int id)
	{
		return service.findBookingHistory(id);
	}
	
	
	@Operation(summary = "Used to Delete Booking History Details")
	@DeleteMapping
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> deleteBookingHistory(@RequestParam int id)
	{
		return service.deleteBookingHistory(id);
	}
	
	
	
	@Operation(summary = "Used to Update Booking History Details")
	@PutMapping
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> updateAadhar(@RequestBody BookingHistoryEntity pay , @RequestParam int id)
	{
		return service.updateBookingHistory(pay, id);
	}
	
	
	
	@Operation(summary = "Used to Get All Booking History Details")
	@GetMapping("bookinghistoryDetails")
	public ResponseEntity<ResponseStructure<List<BookingHistoryDto>>> getAllAadhar()
	{
		return service.getAllBookingHistorys();
	}
}

