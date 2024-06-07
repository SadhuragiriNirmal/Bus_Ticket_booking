package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.BookingHistoryDao;
import com.jspider.app.Bus_Ticket_booking.dao.BusDao;
import com.jspider.app.Bus_Ticket_booking.dao.TicketDao;
import com.jspider.app.Bus_Ticket_booking.dto.BookingHistoryDto;
import com.jspider.app.Bus_Ticket_booking.dto.BusDto;
import com.jspider.app.Bus_Ticket_booking.dto.PassengerDto;
import com.jspider.app.Bus_Ticket_booking.dto.PaymentDto;
import com.jspider.app.Bus_Ticket_booking.dto.TicketDto;
import com.jspider.app.Bus_Ticket_booking.dto.UserDto;
import com.jspider.app.Bus_Ticket_booking.entity.BookingHistory;
import com.jspider.app.Bus_Ticket_booking.entity.Bus;
import com.jspider.app.Bus_Ticket_booking.entity.Payment;
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
	BookingHistoryDto bhDto;
	
	@Autowired
	BusDto busDto;
	
	@Autowired
	BusDao busDao;
	
	@Autowired
	BookingHistoryDao bhDao;
	
	@Autowired
	PassengerService pservice;
	
	@Autowired
	UserService uservice;
	
	@Autowired
	BookingHistoryService bhservice;

	@Autowired
	BusService busService;
	
	@Autowired
	SeatService seatService;
	
	    //save Ticket
	
		public ResponseEntity<ResponseStructure<TicketDto>> saveTicket(Ticket ticket, int userid, int busid, int seatid) {
			
		    ResponseStructure<TicketDto> structure = new ResponseStructure<>();
		    
		    if(ticket != null) {
		    	
		    	Ticket processedTicket  = ticketBookingProgress(ticket, userid, busid);
		        Ticket existTicket = dao.saveTicket(processedTicket);
		        
		        
				if(existTicket != null) {
					
					pservice.assignTicketToPassenger(existTicket);//ticket assigning invoke for passenger
			    	busService.assignTicketsToBus(existTicket, busid);//assigne ticket to bus
			    	seatService.assignPassengerToSeat(existTicket.getPassenger(), seatid);//assigne pasenger to seat
			    	uservice.assignTicketToUser(existTicket,userid);//ticket assigining invoke for user
					UserDto udto = uservice.findByUserId(userid).getBody().getData();
					PassengerDto pdto = pservice.findByPassengerId(existTicket.getPassenger().getPassengerid()).getBody().getData();
					dto.setTicketNumber(existTicket.getTicketNumber());
					dto.setTicketCategory(existTicket.getTicketCategory());
					dto.setPassenger(pdto);
					dto.setUser(udto);
					dto.setPayment(convertPaymenyToPaymenDto(existTicket.getPayment()));
					dto.setBookingHistory(convertBookingHistoryDto(existTicket.getBookingHistory()));
					dto.setBus(convertBusToBusDto(existTicket.getBus()));
					structure.setMessage("Tickets saved successfully");
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setData(dto);	
					return new ResponseEntity<ResponseStructure<TicketDto>>(structure,HttpStatus.CREATED);
					
				}else return null;
					
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
					
					//ticket cancelling process
					ticketCancellingProcess(exTicket);
				}
				
				
				Ticket existTicket = dao.deleteTicket(ticketid);
				if(existTicket != null) {
					
					dto.setTicketNumber(existTicket.getTicketNumber());
			        dto.setTicketCategory(existTicket.getTicketCategory());
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
		
		//payment to payment Dto conversion
		public PaymentDto convertPaymenyToPaymenDto(Payment pay) {
			
			if(pay != null) {
				
				pyDto.setPaidAmount(pay.getPaidAmount());
				pyDto.setPaymentStatus(pay.getPaymentStatus());
				pyDto.setPaymentType(pay.getPaymentType());
	            return pyDto;
			}
			else return null;
		}
		
		
		//bus to bus Dto conversion
		
		public BusDto convertBusToBusDto(Bus bus) {
			
			if(bus != null) {
				
				busDto.setCompany(bus.getCompany());
				busDto.setBusno(bus.getBusno());
				busDto.setBusCapacity(bus.getBusCapacity());
				busDto.setBusType(bus.getBusType());
				busDto.setDeparturePlace(bus.getDeparturePlace());
				busDto.setArrivalPlace(bus.getArrivalPlace());
				busDto.setDepartureDate(bus.getDepartureDate());
				busDto.setArrivalDate(bus.getArrivalDate());
				busDto.setDepartureTime(bus.getDepartureTime());
				busDto.setArrivalTime(bus.getArrivalTime());
				busDto.setJourneyDuration(bus.getJourneyDuration());
				return busDto;
				
			}
			else return null;

		}
		
		
		//booking history to booking Histroy dto conversion
		
		public BookingHistoryDto convertBookingHistoryDto(BookingHistory bh) {
			
			if(bh != null) {
				
				bhDto.setBookedDate(bh.getBookedDate());
				bhDto.setJourneyDate(bh.getJourneyDate());
				bhDto.setSeatAvilable(bh.getSeatAvilable());
				return bhDto;
			}
			else return null;
			
		}
		
		//Ticket booking progress 

		public Ticket ticketBookingProgress(Ticket ticket, int userid, int busid) {
			
			Bus bus = busDao.findBusByid(busid);
			ticket.setTicketCategory(ticket.getPassenger().getPAgeCategory());
	    	ticket.setTicketNumber(bus.getBusno());
	    	ticket.setBus(busDao.findBusByid(busid));//set bus to ticket
	    	ticket.setUser(uservice.dao.findByUserId(userid));//set user to ticket
	        BookingHistory existBookingHistory = bhservice.initialzeBookingHistory(ticket, userid, busid);
	        ticket.setBookingHistory(existBookingHistory);//set bh to ticket
			return ticket;
		}
		
		
		//Ticket cancelling process
		
		public void ticketCancellingProcess(Ticket ticket) {
			
			//updated user respected to ticket deletion. return type is void.
			uservice.updateUserTickets(ticket);
			//updated Booking history respected to user . return type is void.
			bhservice.updateBooKingHistoryToUser(ticket.getBookingHistory());
			//brake relation between seat and passenger
			seatService.brakeRelationBtwPassengerAndSeat(ticket);
			//updated bus respected to ticket deletion. return type is void.
			busService.updateBusTickets(ticket);
			
		}

}
