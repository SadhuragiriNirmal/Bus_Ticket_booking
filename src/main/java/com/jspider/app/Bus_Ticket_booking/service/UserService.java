package com.jspider.app.Bus_Ticket_booking.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.BookingHistoryDao;
import com.jspider.app.Bus_Ticket_booking.dao.BusDao;
import com.jspider.app.Bus_Ticket_booking.dao.SeatDao;
import com.jspider.app.Bus_Ticket_booking.dao.TicketDao;
import com.jspider.app.Bus_Ticket_booking.dao.UserDao;
import com.jspider.app.Bus_Ticket_booking.dto.BookingHistoryDto;
import com.jspider.app.Bus_Ticket_booking.dto.BusDto;
import com.jspider.app.Bus_Ticket_booking.dto.PassengerDto;
import com.jspider.app.Bus_Ticket_booking.dto.PaymentDto;
import com.jspider.app.Bus_Ticket_booking.dto.SeatDto;
import com.jspider.app.Bus_Ticket_booking.dto.TicketDto;
import com.jspider.app.Bus_Ticket_booking.dto.UserDto;
import com.jspider.app.Bus_Ticket_booking.entity.BookingHistory;
import com.jspider.app.Bus_Ticket_booking.entity.Bus;
import com.jspider.app.Bus_Ticket_booking.entity.Passenger;
import com.jspider.app.Bus_Ticket_booking.entity.Payment;
import com.jspider.app.Bus_Ticket_booking.entity.Seat;
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
	SeatDao seatDao;
	
	@Autowired
	BusDao busDao;
	
	@Autowired
	BookingHistoryDao bhdao;
	
	
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
				
				TicketDto tdto = new TicketDto();
				if(t != null) {
					
					tdto.setTicketNumber(t.getTicketNumber());
					tdto.setTicketCategory(t.getTicketCategory());
					
					if(t.getPassenger() != null && t.getPayment() != null && t.getBus() != null) {
						
						tdto.setPassenger(converToPassengerDto(t.getPassenger()));
						tdto.setPayment(convertToPaymentDto(t.getPayment()));
						tdto.setBus(convertBusToBusDto(t.getBus(),t));
					}
					ticketsDto.add(tdto);
				}
				
			}
			dto.setTicktes(ticketsDto);
			if(existUser.getBookingHistories() != null) {
				
				dto.setBookingHistories(convertToBookinghistorylist(existUser.getBookingHistories()));
			}
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
			
			updateSeatsToBus(user);
			brakeRelationBtwTicketAndBus(user);
			brakeRelationBtwUserAndBookingHistory(user);
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
	
    //update sests in bus for none impacted account deletion process
	
    public void updateSeatsToBus(User user) {
		  
    	List<Ticket> tickets = user.getTickets();
		for(Ticket t:tickets) {
			  
			Bus bus = t.getBus();
			List<Seat> seats = bus.getSeat();
			List<Seat> newSeats = new ArrayList<Seat>();
			  
			if(seats != null) {
				  
				for(Seat s:seats) {
					  
					if(s.getSeatid() != t.getPassenger().getSeat().getSeatid()) {
						  
						newSeats.add(s);
				    }
				}
				bus.setSeat(newSeats);
				busDao.updatebus(bus, bus.getBusid());
			}
		}
		  
    }
	
	//brake the relation between tickets and respective buses
	   
	public void brakeRelationBtwTicketAndBus(User user) {
		
		List<Ticket> tickets = user.getTickets();
		
		if(tickets != null) {
			
			for(Ticket ticket:tickets) {
				
				if(ticket.getBus() != null) {
					
					List<Ticket> newTickets = new ArrayList<Ticket>();
					Bus bus = ticket.getBus();
					List<Ticket> totalBusTickets = bus.getTicket();
					for(Ticket t:totalBusTickets) {
						
						if(t.getUser().getUserid() != user.getUserid()) {
							
							newTickets.add(t);
						}
					}
					bus.setTicket(newTickets);
					busDao.updatebus(bus, bus.getBusid());
				}
				
			}
			
		}
		
	}
	
	//brake relation btw user and booking history
	
	public void brakeRelationBtwUserAndBookingHistory(User user) {
		
		if(user != null) {
			
			user.setBookingHistories(null);
			dao.updateUser(user, user.getUserid());
			
		}
		
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
			
			PassengerDto pdto = new PassengerDto();
			pdto.setPname(p.getPname());
			pdto.setPaddress(p.getPaddress());
			pdto.setPAge(p.getPAge());
			pdto.setPAgeCategory(p.getPAgeCategory());
			pdto.setPemail(p.getPemail());
			pdto.setPmobileno(p.getPmobileno());
			pdto.setSeat(convertSeatToSeatDto(p.getSeat()));
			return pdto;
		}
		else return null;
		
	}
	
	//payment dto conversion
	
	public PaymentDto convertToPaymentDto(Payment py) {
		
		if(py != null) {
			
			PaymentDto paydto = new PaymentDto();
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
				
				BookingHistoryDto bhdto = new BookingHistoryDto();
				bhdto.setBookedDate(bh.getBookedDate());
				bhdto.setJourneyDate(bh.getJourneyDate());
				bhdto.setSeatAvilable(bh.getSeatAvilable());
				bookingHistroyDto.add(bhdto);
			}
			return bookingHistroyDto;
			
		}
		else return null;
	}
	
	
	
	//bus to bus Dto conversion
	
	public BusDto convertBusToBusDto(Bus bus, Ticket ticket) {
		
		if(bus != null) {
			
			BusDto busDto = new BusDto();
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
	
	//seat to seatDto conversion
	
	public SeatDto convertSeatToSeatDto(Seat seat) {
		
			if(seat != null) {
				
				SeatDto seatDto = new SeatDto();
				seatDto.setSeatno(seat.getSeatno());
				seatDto.setSeatType(seat.getSeatType());
				seatDto.setSeatPosition(seat.getSeatPosition());
				seatDto.setSeatPosition(seat.getSeatPosition());
				seatDto.setBookedDate(seat.getBookedDate());
				return seatDto;
			}
			else return null;
	}
	
	//User Login process
	
	public ResponseEntity<ResponseStructure<UserDto>> loginVerification(User user){
		
		if(user.getUemail() != null) {
			
		    User u = dao.findByuemail(user.getUemail());
		    
		    if(user.getUpassword().equals(u.getUpassword())) {
		    	
		    	return findByUserId(u.getUserid());
		    }
		    else return null;				
		}
		else return null;
	}
	
}
