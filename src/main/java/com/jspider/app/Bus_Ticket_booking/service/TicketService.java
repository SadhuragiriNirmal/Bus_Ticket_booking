package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.TicketDao;
import com.jspider.app.Bus_Ticket_booking.dto.PassengerDto;
import com.jspider.app.Bus_Ticket_booking.dto.TicketDto;
import com.jspider.app.Bus_Ticket_booking.dto.UserDto;
import com.jspider.app.Bus_Ticket_booking.entity.Ticket;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;


@Service
public class TicketService {
	
	@Autowired
	TicketDao dao;
	
	@Autowired
	TicketDto dto;
	
	@Autowired
	PassengerService pservice;
	
	@Autowired
	UserService uservice;
	
	    //save Ticket
	
		public ResponseEntity<ResponseStructure<List<TicketDto>>> saveTicket(List<Ticket> tickets, int userid) {
			
		    ResponseStructure<List<TicketDto>> structure = new ResponseStructure<>();
		    List<TicketDto> ticketsDto = new ArrayList<TicketDto>();
		    
		    if(tickets != null) {
		    	
		    	for(Ticket t:tickets) {
		    		
		    		    t.setUser(uservice.dao.findByUserId(userid));//set user to ticket
		    		    Ticket existTicket =  dao.saveTicket(t);
		    		   	pservice.assignTicketToPassenger(t);//ticket assigning invoke for passenger
		    		    
						if(existTicket != null) {
							
							UserDto udto = uservice.findByUserId(userid).getBody().getData();
			    		    PassengerDto pdto = pservice.findByPassengerId(existTicket.getPassenger().getPassengerid()).getBody().getData();
					        dto.setFrom(existTicket.getFrom());
					        dto.setTo(existTicket.getTo());
					        dto.setVia(existTicket.getVia());
					        dto.setBusno(existTicket.getBusno());
					        dto.setSeatno(existTicket.getSeatno());
					        dto.setAgeCategory(existTicket.getAgeCategory());
					        dto.setPrice(existTicket.getPrice());
					        dto.setUser(udto);
					        dto.setPassenger(pdto);
							ticketsDto.add(dto);
							
						}
						else return null;
		    		
		    	}
		    	uservice.assignTicketToUser(tickets,userid);//ticket assigining invoke for user
		    	structure.setMessage("Tickets saved successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(ticketsDto);	
				return new ResponseEntity<ResponseStructure<List<TicketDto>>>(structure,HttpStatus.CREATED);	
		    	
		    	
		    }else return null;
		
		}
		
		//get Ticket
		
		public ResponseEntity<ResponseStructure<TicketDto>>  findByTicketId(int ticketid) {
			
			ResponseStructure<TicketDto> structure = new ResponseStructure<TicketDto>();
			Ticket existTicket = dao.findByTicketId(ticketid);
			if(existTicket != null) {
				
				dto.setFrom(existTicket.getFrom());
			    dto.setTo(existTicket.getTo());
			    dto.setVia(existTicket.getVia());
			    dto.setBusno(existTicket.getBusno());
			    dto.setSeatno(existTicket.getSeatno());
			    dto.setAgeCategory(existTicket.getAgeCategory());
			    dto.setPrice(existTicket.getPrice());
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

				dto.setFrom(existTicket.getFrom());
			    dto.setTo(existTicket.getTo());
			    dto.setVia(existTicket.getVia());
			    dto.setBusno(existTicket.getBusno());
			    dto.setSeatno(existTicket.getSeatno());
			    dto.setAgeCategory(existTicket.getAgeCategory());
			    dto.setPrice(existTicket.getPrice());
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
				
				
				Ticket deletedTicket = dao.deleteTicket(ticketid);
				if(deletedTicket != null) {
					
					dto.setFrom(deletedTicket.getFrom());
				    dto.setTo(deletedTicket.getTo());
				    dto.setVia(deletedTicket.getVia());
				    dto.setBusno(deletedTicket.getBusno());
				    dto.setSeatno(deletedTicket.getSeatno());
				    dto.setAgeCategory(deletedTicket.getAgeCategory());
				    dto.setPrice(deletedTicket.getPrice());
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
					ticktesDto.setFrom(t.getFrom());
				    ticktesDto.setTo(t.getTo());
				    ticktesDto.setVia(t.getVia());
				    ticktesDto.setBusno(t.getBusno());
				    ticktesDto.setSeatno(t.getSeatno());
				    ticktesDto.setAgeCategory(t.getAgeCategory());
				    ticktesDto.setPrice(t.getPrice());
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
