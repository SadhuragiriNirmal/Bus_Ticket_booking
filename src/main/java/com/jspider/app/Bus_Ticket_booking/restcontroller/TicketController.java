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

import com.jspider.app.Bus_Ticket_booking.dto.TicketDto;
import com.jspider.app.Bus_Ticket_booking.entity.Ticket;
import com.jspider.app.Bus_Ticket_booking.service.TicketService;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;



@RestController
@RequestMapping("ticket")
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
	//save Ticket
		@PostMapping
		public ResponseEntity<ResponseStructure<TicketDto>> saveTicket(@RequestBody Ticket t, @RequestParam int userid, @RequestParam int busid, @RequestParam int seatid){
			
			return ticketService.saveTicket(t, userid, busid, seatid);
		}
		
		//get Ticket
		@GetMapping
		public ResponseEntity<ResponseStructure<TicketDto>> findTicketByID(@RequestParam int ticketid){
			
			return ticketService.findByTicketId(ticketid);
		}
		
		//update Ticket
		@PutMapping
		public ResponseEntity<ResponseStructure<TicketDto>> updateTicket(@RequestBody Ticket t, @RequestParam int ticketid){
			
			return ticketService.updateTicket(t, ticketid);
		}
		
		
		//delete Ticket
		@DeleteMapping
		public ResponseEntity<ResponseStructure<TicketDto>> deleteTicket(@RequestParam int ticketid){
			
			return ticketService.deleteTicket(ticketid);
		}
		
		//get all Ticket
		@GetMapping("tickets")
		public ResponseEntity<ResponseStructure<List<TicketDto>>> getAllTicket(){
			
			return ticketService.getAllTicket();
		}
	    	


}
