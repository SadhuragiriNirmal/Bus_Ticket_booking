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

import com.jspider.app.Bus_Ticket_booking.dto.BookingHistoryDto;
import com.jspider.app.Bus_Ticket_booking.entity.BookingHistory;
import com.jspider.app.Bus_Ticket_booking.service.BookingHistoryService;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;


@RestController
@RequestMapping("bookinghistory")
public class BookingHistoryController 
{
	
	@Autowired
	BookingHistoryService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> saveBookingHistory(@RequestBody BookingHistory bh)
	{
		return service.saveBookingHistory(bh);
	}
	
	
	
	@GetMapping
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> findBookingHistory(@RequestParam int bhid)
	{
		return service.findBookingHistory(bhid);
	}
	
	
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> deleteBookingHistory(@RequestParam int bhid)
	{
		return service.deleteBookingHistory(bhid);
	}
	
	
	@PutMapping
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> updateBookingHistory(@RequestBody BookingHistory bh , @RequestParam int bhid)
	{
		return service.updateBookingHistory(bh, bhid);
	}
	
	
	@GetMapping("bookinghistorys")
	public ResponseEntity<ResponseStructure<List<BookingHistoryDto>>> getAllBookingHistory()
	{
		return service.getAllBookingHistorys();
	}
}

