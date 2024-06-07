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

import com.jspider.app.Bus_Ticket_booking.dto.SeatDto;
import com.jspider.app.Bus_Ticket_booking.entity.Seat;
import com.jspider.app.Bus_Ticket_booking.service.SeatService;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@RestController
@RequestMapping("seat")
public class SeatController {
	
	@Autowired
	SeatService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<SeatDto>> saveSeat(@RequestBody Seat seat, @RequestParam int busid){
		
		return service.saveSeat(seat, busid);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<SeatDto>> findBySeatId(@RequestParam int seatid){
		
		return service.findBySeatId(seatid);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<SeatDto>> updateSeat(@RequestBody Seat seat, @RequestParam int seatid){
		
		return service.updateSeat(seat, seatid);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<SeatDto>> deleteSeat(@RequestParam int seatid){
		
		return service.deleteSeat(seatid);
	}
	
	@GetMapping("seats")
	public ResponseEntity<ResponseStructure<List<SeatDto>>> getAllSeat(){
		
		return service.getAllSeat();
	}

}
