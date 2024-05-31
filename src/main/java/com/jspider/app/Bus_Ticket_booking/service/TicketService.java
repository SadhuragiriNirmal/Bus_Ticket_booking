package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.TicketDao;
import com.jspider.app.Bus_Ticket_booking.dto.PassengerDto;
import com.jspider.app.Bus_Ticket_booking.dto.PaymentDto;
import com.jspider.app.Bus_Ticket_booking.dto.TicketDto;
import com.jspider.app.Bus_Ticket_booking.dto.UserDto;
import com.jspider.app.Bus_Ticket_booking.entity.BookingHistory;
import com.jspider.app.Bus_Ticket_booking.entity.Ticket;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;


@Service
public class TicketService {
	
	@Autowired
	TicketDao dao;
	
	@Autowired
	TicketDto dto;
	
	@Autowired
	PaymentDto pyDto;
	
	@Autowired
	PassengerService pservice;
	
	@Autowired
	UserService uservice;
	
	@Autowired
	BookingHistoryService bhservice;

	
	    //save Ticket
	
		public ResponseEntity<ResponseStructure<TicketDto>> saveTicket(Ticket ticket, int userid) {
			
		    ResponseStructure<TicketDto> structure = new ResponseStructure<>();
		    
		    if(ticket != null) {
		    	
		    	ticket.setUser(uservice.dao.findByUserId(userid));//set user to ticket
		        Ticket existTicket = dao.saveTicket(ticket);
		        BookingHistory bookingHistory = new BookingHistory();
		        bookingHistory.setUser(uservice.dao.findByUserId(userid));
		        bhservice.saveBookingHistory(bookingHistory);;
		        pservice.assignTicketToPassenger(ticket);//ticket assigning invoke for passenger
		    		    
				if(existTicket != null) {
					
					UserDto udto = uservice.findByUserId(userid).getBody().getData();
					PassengerDto pdto = pservice.findByPassengerId(existTicket.getPassenger().getPassengerid()).getBody().getData();
					dto.setTicketNumber(existTicket.getTicketNumber());
					dto.setTicketCategory(existTicket.getTicketCategory());
					dto.setPassenger(pdto);
					dto.setUser(udto);
					dto.setPayment(pyDto);
							
				}
				else return null;
		    		
		    	uservice.assignTicketToUser(ticket,userid);//ticket assigining invoke for user
		    	structure.setMessage("Tickets saved successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dto);	
				return new ResponseEntity<ResponseStructure<TicketDto>>(structure,HttpStatus.CREATED);	
		    	
		    	
		    }else return null;
		
		}
		
		//get Ticket
		
		public ResponseEntity<ResponseStructure<TicketDto>>  findByTicketId(int ticketid) {
			
			ResponseStructure<TicketDto> structure = new ResponseStructure<TicketDto>();
			Ticket existTicket = dao.findByTicketId(ticketid);
			if(existTicket != null) {
				
				UserDto udto = uservice.findByUserId(existTicket.getUser().getUserid()).getBody().getData();
    		    PassengerDto pdto = pservice.findByPassengerId(existTicket.getPassenger().getPassengerid()).getBody().getData();
				dto.setTicketNumber(existTicket.getTicketNumber());
		        dto.setTicketCategory(existTicket.getTicketCategory());
		        dto.setPassenger(pdto);
		        dto.setUser(udto);
		        dto.setPayment(pyDto);
				structure.setMessage("Ticket got successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<TicketDto>>(structure,HttpStatus.FOUND);
			}
			else return null;
				
		}
		
		
		//update Ticket
		
		public ResponseEntity<ResponseStructure<TicketDto>> updateTicket(Ticket t, int ticketid) {
			
			ResponseStructure<TicketDto> structure = new ResponseStructure<TicketDto>();
			Ticket existTicket = dao.updateTicket(t, ticketid);
			if(existTicket != null) {

				UserDto udto = uservice.findByUserId(existTicket.getUser().getUserid()).getBody().getData();
    		    PassengerDto pdto = pservice.findByPassengerId(existTicket.getPassenger().getPassengerid()).getBody().getData();
				dto.setTicketNumber(existTicket.getTicketNumber());
		        dto.setTicketCategory(existTicket.getTicketCategory());
		        dto.setPassenger(pdto);
		        dto.setUser(udto);
		        dto.setPayment(pyDto);
				structure.setMessage("Ticket updated successfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<TicketDto>>(structure,HttpStatus.OK);
			}
			else return null;
			
		}
		
		
		//delete Ticket
		
		public ResponseEntity<ResponseStructure<TicketDto>> deleteTicket(int ticketid) {
			
			ResponseStructure<TicketDto> structure = new ResponseStructure<TicketDto>();
			
			if(ticketid != 0) {
				
				Ticket exTicket = dao.findByTicketId(ticketid);
				if(exTicket != null) {
					//updated user respected to ticket deletion. return type is void.
					uservice.updateUserTickets(exTicket);
					exTicket.setUser(null);
				}
				
				
				Ticket existTicket = dao.deleteTicket(ticketid);
				if(existTicket != null) {
					
					UserDto udto = uservice.findByUserId(existTicket.getUser().getUserid()).getBody().getData();
	    		    PassengerDto pdto = pservice.findByPassengerId(existTicket.getPassenger().getPassengerid()).getBody().getData();
					dto.setTicketNumber(existTicket.getTicketNumber());
			        dto.setTicketCategory(existTicket.getTicketCategory());
			        dto.setPassenger(pdto);
			        dto.setUser(udto);
			        dto.setPayment(pyDto);
					structure.setMessage("Ticket deleted successfully");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(dto);
					return new ResponseEntity<ResponseStructure<TicketDto>>(structure,HttpStatus.OK);
				}
				else return null;
				
				
			}return null;
			
			
						
		}
		
		
		//get all Ticket
		
		public ResponseEntity<ResponseStructure<List<TicketDto>>> getAllTicket() {
			
			ResponseStructure<List<TicketDto>> structure = new ResponseStructure<>();
			List<Ticket> existTickets = dao.getAllTicket();
			List<TicketDto> tickets = new ArrayList<>();
			if(existTickets != null) {
				
				for(Ticket t: existTickets) {
					
					TicketDto ticktesDto = new TicketDto();
					UserDto udto = uservice.findByUserId(t.getUser().getUserid()).getBody().getData();
	    		    PassengerDto pdto = pservice.findByPassengerId(t.getPassenger().getPassengerid()).getBody().getData();
					dto.setTicketNumber(t.getTicketNumber());
			        dto.setTicketCategory(t.getTicketCategory());
			        dto.setPassenger(pdto);
			        dto.setUser(udto);
			        dto.setPayment(pyDto);
					tickets.add(ticktesDto);
				}
				
				structure.setMessage("Tickets got successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(tickets);
				return new ResponseEntity<ResponseStructure<List<TicketDto>>>(structure,HttpStatus.FOUND);
				
			}
			else return null;
				
		}

}
