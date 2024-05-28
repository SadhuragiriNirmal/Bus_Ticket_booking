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

import com.jspider.app.Bus_Ticket_booking.dto.UserDto;
import com.jspider.app.Bus_Ticket_booking.entity.User;
import com.jspider.app.Bus_Ticket_booking.service.UserService;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;



@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userservice;
	
	//save user
	@PostMapping
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(@RequestBody User u){
		
		return userservice.saveUser(u);
	}
	
	//get user
	@GetMapping
	public ResponseEntity<ResponseStructure<UserDto>> findUserByID(@RequestParam int userid){
		
		return userservice.findByUserId(userid);
	}
	
	//update user
	@PutMapping
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(@RequestBody User u, @RequestParam int userid){
		
		return userservice.updateUser(u, userid);
	}
	
	
	//delete user
	@DeleteMapping
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(@RequestParam int userid){
		
		return userservice.deleteUser(userid);
	}
	
	//get all user
	@GetMapping("users")
	public ResponseEntity<ResponseStructure<List<UserDto>>> getAllUser(){
		
		return userservice.getAllUser();
	}
    	

}
