package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.TicketDao;
import com.jspider.app.Bus_Ticket_booking.dao.UserDao;
import com.jspider.app.Bus_Ticket_booking.dto.UserDto;
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
			
			dto.setUname(existUser.getUname());
			dto.setUemail(existUser.getUemail());
			dto.setMembership_type(existUser.getMembership_type());
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
		User existUser = dao.updateUser(u, userid);
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
			
			//brake relation between user and tickets if tickets is presented.
			if(user.getTickets() != null) {
				
				 brakeRelation(user);
			}
			
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
	
	public UserDto assignTicketToUser(List<Ticket> ticktes, int userid) {
		
		if(ticktes!=null) {
			 
			User u = dao.findByUserId(userid);
			
			
			if(u!=null) {
				
				List<Ticket> exTickets = u.getTickets();
				exTickets.addAll(ticktes);
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
	
	//brake the relation between user and tickets
	
	public User brakeRelation(User user) {
		
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
		
}
