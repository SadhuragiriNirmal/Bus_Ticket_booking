package com.jspider.app.Bus_Ticket_booking.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.BookingHistoryDao;
import com.jspider.app.Bus_Ticket_booking.dao.TicketDao;
import com.jspider.app.Bus_Ticket_booking.dao.UserDao;
import com.jspider.app.Bus_Ticket_booking.dto.BookingHistoryDto;
import com.jspider.app.Bus_Ticket_booking.dto.PassengerDto;
import com.jspider.app.Bus_Ticket_booking.dto.PaymentDto;
import com.jspider.app.Bus_Ticket_booking.dto.TicketDto;
import com.jspider.app.Bus_Ticket_booking.dto.UserDto;
import com.jspider.app.Bus_Ticket_booking.entity.BookingHistory;
import com.jspider.app.Bus_Ticket_booking.entity.Passenger;
import com.jspider.app.Bus_Ticket_booking.entity.Payment;
import com.jspider.app.Bus_Ticket_booking.entity.Ticket;
import com.jspider.app.Bus_Ticket_booking.entity.User;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;



@Service
public class UserService {
	
	@Autowired
	UserDao dao;
	
	@Autowired
	UserDto dto;
	
	@Autowired
	TicketDao tdao;
	
	@Autowired
	BookingHistoryDao bhdao;
	
	@Autowired
	TicketDto tdto;
	
	@Autowired
	PaymentDto paydto;
	
	@Autowired
	PassengerDto pdto;
	
	@Autowired
	BookingHistoryDto bhdto;

	
	//save user
	
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(User u) {
		
	    ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
		User existUser =  dao.saveUser(u);
		if(existUser != null) {
			
			dto.setUname(existUser.getUname());
			dto.setUemail(existUser.getUemail());
			dto.setMembership_type(existUser.getMembership_type());
			structure.setMessage("user saved successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.CREATED);
			
		}
		else return null;	
	}
	
	//get user
	
	public ResponseEntity<ResponseStructure<UserDto>>  findByUserId(int userid) {
		
		ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
		User existUser = dao.findByUserId(userid);
		
		if(existUser != null) {
			
			List<Ticket> tickets = existUser.getTickets();
			List<TicketDto> ticketsDto = new ArrayList<TicketDto>();
			dto.setUname(existUser.getUname());
			dto.setUemail(existUser.getUemail());
			dto.setMembership_type(existUser.getMembership_type());
			for(Ticket t:tickets) {
				
				tdto.setTicketNumber(t.getTicketNumber());
				tdto.setTicketCategory(t.getTicketCategory());
				tdto.setPassenger(converToPassengerDto(t.getPassenger()));
				tdto.setPayment(convertToPaymentDto(t.getPayment()));
				ticketsDto.add(tdto);
			}
			dto.setTicktes(ticketsDto);
			dto.setBookingHistories(convertToBookinghistorylist(existUser.getBookingHistories()));	
			structure.setMessage("user got successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.FOUND);
		}
		else return null;
			
	}
	
	
	//update user
	
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(User u, int userid) {
		
		ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
		//assigne ticket to user if ticket presented
		User user =assignTicketToUserIfPresented(u, userid);
		User existUser = dao.updateUser(user, userid);
		if(existUser != null) {
			
			dto.setUname(existUser.getUname());
			dto.setUemail(existUser.getUemail());
			dto.setMembership_type(existUser.getMembership_type());
			structure.setMessage("user updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
		}
		else return null;
		
	}
	
	
	//delete user
	
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(int userid) {
		
		ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
		User user = dao.findByUserId(userid);
		if(user != null) {
			
			//brake relation between user and Ticket , And DELETE if tickets is presented.
			if(user.getTickets() != null) brakeRelationTicket(user);
			
			//brake relation between user and Booking histroy, And DELETE if booking history is presented.
			if(user.getBookingHistories() != null) brakeRelationBookingHistory(user);
			
			User existUser = dao.deleteUser(userid);
			if(existUser != null) {
				
				dto.setUname(existUser.getUname());
				dto.setUemail(existUser.getUemail());
				dto.setMembership_type(existUser.getMembership_type());
				structure.setMessage("user deleted successfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
			}
			else return null;
			
		}else return null;
			
	}
	
	
	//get all user
	
	public ResponseEntity<ResponseStructure<List<UserDto>>> getAllUser() {
		
		ResponseStructure<List<UserDto>> structure = new ResponseStructure<>();
		List<User> existUsers = dao.getAllUser();
		List<UserDto> users = new ArrayList<>();
		if(existUsers != null) {
			
			for(User u: existUsers) {
				
				UserDto userDto = new UserDto();
				userDto.setUname(u.getUname());
				userDto.setUemail(u.getUemail());
				userDto.setMembership_type(u.getMembership_type());
				users.add(userDto);
			}
			
			structure.setMessage("users got successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(users);
			return new ResponseEntity<ResponseStructure<List<UserDto>>>(structure,HttpStatus.FOUND);
			
		}
		else return null;
			
	}
	
	//assign ticket to user
	
	public UserDto assignTicketToUser(Ticket ticket, int userid) {
		
		if(ticket!=null) {
			 
			User u = dao.findByUserId(userid);
			
			if(u!=null) {
				
				List<Ticket> exTickets = u.getTickets();
				exTickets.add(ticket);
				u.setTickets(exTickets);
				User exUser =  dao.saveUser(u);
				
			    if(exUser != null) {
			    	
			    	dto.setUname(exUser.getUname());
					dto.setUemail(exUser.getUemail());
					dto.setMembership_type(exUser.getMembership_type());
					return dto;		
					
			    }else return null; // exuser is null
			    
			}else return null; //user is null

		}else return null;  //ticket is null
		
	}
	
	
	//update user ticket List
	
	public void updateUserTickets(Ticket ticket) {
		
		List<Ticket> tickets = ticket.getUser().getTickets();
		if(tickets != null) {
			
			List<Ticket> new_tickets = new ArrayList<Ticket>();
			for(Ticket t:tickets) {
				
				if(t.getTicketid() != ticket.getTicketid()) {
					
					new_tickets.add(t);
				}
			}
		    User user = ticket.getUser();
		    user.setTickets(new_tickets);
		    dao.updateUser(user, user.getUserid());
			
		}
		else return;
		
	}
	
	//brake the relation between user and tickets and delete the ticket
	
	public User brakeRelationTicket(User user) {
		
		if(user != null) {
			
			List<Ticket> tickets = user.getTickets();
			user.setTickets(null);
			dao.updateUser(user,user.getUserid());
			
			for(Ticket t:tickets) {
				
				t.setUser(null);;
				tdao.updateTicket(t, t.getTicketid());
				tdao.deleteTicket(t.getTicketid());
				
			}
			
			return user;
			
		}
		else return null;

	}
	
	//brake the relation between user, booking history and delete the booking histroty
	
	public User brakeRelationBookingHistory(User user) {
		
		if(user != null) {
			
			List<BookingHistory> bookingHistories = user.getBookingHistories();
			user.setBookingHistories(null);;
			dao.updateUser(user,user.getUserid());
			
			for(BookingHistory bh: bookingHistories) {
				
				bh.setUser(null);;
				bhdao.updateBookingHistory(bh, bh.getBhid());
				bhdao.deleteBookingHistory(bh.getBhid());
				
			}
			
			return user;
			
		}
		else return null;

	}
	
	//assigne ticket to user if tickes are present
	
	public User assignTicketToUserIfPresented(User u,int userid) {
		
		User user = dao.findByUserId(userid);
		if(user != null) {
			List<Ticket> tickets = user.getTickets();
			if(tickets != null) {
				
				u.setTickets(tickets);
			}
			return u;
		}
		else return null;
		
	}
	
	//passenger dto conversion
	
	public PassengerDto converToPassengerDto(Passenger p) {
		
		if(p != null) {
			
			pdto.setPname(p.getPname());
			pdto.setPaddress(p.getPaddress());
			pdto.setPAge(p.getPAge());
			pdto.setPAgeCategory(p.getPAgeCategory());
			pdto.setPemail(p.getPemail());
			pdto.setPmobileno(p.getPmobileno());
			return pdto;
		}
		else return null;
		
	}
	
	//payment dto conversion
	
	public PaymentDto convertToPaymentDto(Payment py) {
		
		if(py != null) {
			
			paydto.setPaymentType(py.getPaymentType());
			paydto.setPaidAmount(py.getPaidAmount());
			paydto.setPaymentStatus(py.getPaymentStatus());
			return paydto;
		}
		else return null;
	}
	
	//booking history list dto conversion
	
	public List<BookingHistoryDto> convertToBookinghistorylist(List<BookingHistory> bhs){
		
		if(bhs != null) {
			
			List<BookingHistoryDto> bookingHistroyDto = new ArrayList<BookingHistoryDto>(); 
			
			for(BookingHistory bh:bhs) {
				
				bhdto.setBookedDate(bh.getBookedDate());
				bhdto.setJourneyDate(bh.getJourneyDate());
				bhdto.setSeatAvilable(bh.getSeatAvilable());
				bookingHistroyDto.add(bhdto);
			}
			
			return bookingHistroyDto;
			
		}
		else return null;
	}
		
}
