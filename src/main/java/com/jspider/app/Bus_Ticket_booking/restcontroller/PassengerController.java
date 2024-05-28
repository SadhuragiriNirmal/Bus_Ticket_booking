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

import com.jspider.app.Bus_Ticket_booking.dto.PassengerDto;
import com.jspider.app.Bus_Ticket_booking.entity.Passenger;
import com.jspider.app.Bus_Ticket_booking.service.PassengerService;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;



@RestController
@RequestMapping("passenger")
public class PassengerController {
	
	@Autowired
	PassengerService passengerService;
	
	//save Passenger
		@PostMapping
		public ResponseEntity<ResponseStructure<PassengerDto>> savePassenger(@RequestBody Passenger p){
			
			return passengerService.savePassenger(p);
		}
		
		//get Passenger
		@GetMapping
		public ResponseEntity<ResponseStructure<PassengerDto>> findPassengerByID(@RequestParam int passengerid){
			
			return passengerService.findByPassengerId(passengerid);
		}
		
		//update Passenger
		@PutMapping
		public ResponseEntity<ResponseStructure<PassengerDto>> updatePassenger(@RequestBody Passenger p, @RequestParam int passengerid){
			
			return passengerService.updatePassenger(p, passengerid);
		}
		
		
		//delete Passenger
		@DeleteMapping
		public ResponseEntity<ResponseStructure<PassengerDto>> deletePassenger(@RequestParam int passengerid){
			
			return passengerService.deletePassenger(passengerid);
		}
		
		//get all Passenger
		@GetMapping("passengers")
		public ResponseEntity<ResponseStructure<List<PassengerDto>>> getAllPassenger(){
			
			return passengerService.getAllPassenger();
		}

}
