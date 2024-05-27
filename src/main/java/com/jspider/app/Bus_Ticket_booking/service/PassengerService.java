package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.PassengerDao;
import com.jspider.app.Bus_Ticket_booking.dto.PassengerDto;
import com.jspider.app.Bus_Ticket_booking.entity.Passenger;
import com.jspider.app.Bus_Ticket_booking.entity.Ticket;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;



@Service
public class PassengerService {

	@Autowired
	PassengerDao dao;
	
	@Autowired
    PassengerDto dto;
	
	
	//save Passenger
	
	public ResponseEntity<ResponseStructure<PassengerDto>> savePassenger(Passenger p) {
		
	    ResponseStructure<PassengerDto> structure = new ResponseStructure<PassengerDto>();
		Passenger existPassenger =  dao.savePassenger(p);
		if(existPassenger != null) {
			
			dto.setPname(existPassenger.getPname());
			dto.setPemail(existPassenger.getPemail());
			dto.setPmobileno(existPassenger.getPmobileno());
			dto.setpAge(existPassenger.getpAge());
			dto.setpAgeCategory(existPassenger.getpAgeCategory());
			dto.setPaddress(existPassenger.getPaddress());
			structure.setMessage("Passenger saved successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<PassengerDto>>(structure,HttpStatus.CREATED);
			
		}
		else return null;	
	}
	
	//get Passenger
	
	public ResponseEntity<ResponseStructure<PassengerDto>>  findByPassengerId(int Passengerid) {
		
		ResponseStructure<PassengerDto> structure = new ResponseStructure<PassengerDto>();
		Passenger existPassenger = dao.findByPassengerId(Passengerid);
		if(existPassenger != null) {
			
			dto.setPname(existPassenger.getPname());
			dto.setPemail(existPassenger.getPemail());
			dto.setPmobileno(existPassenger.getPmobileno());
			dto.setpAge(existPassenger.getpAge());
			dto.setpAgeCategory(existPassenger.getpAgeCategory());
			dto.setPaddress(existPassenger.getPaddress());
			structure.setMessage("Passenger got successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<PassengerDto>>(structure,HttpStatus.FOUND);
		}
		else return null;
			
	}
	
	
	//update Passenger
	
	public ResponseEntity<ResponseStructure<PassengerDto>> updatePassenger(Passenger p, int Passengerid) {
		
		ResponseStructure<PassengerDto> structure = new ResponseStructure<PassengerDto>();
		Passenger existPassenger = dao.updatePassenger(p, Passengerid);
		if(existPassenger != null) {
			
			dto.setPname(existPassenger.getPname());
			dto.setPemail(existPassenger.getPemail());
			dto.setPmobileno(existPassenger.getPmobileno());
			dto.setpAge(existPassenger.getpAge());
			dto.setpAgeCategory(existPassenger.getpAgeCategory());
			dto.setPaddress(existPassenger.getPaddress());
			structure.setMessage("Passenger updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<PassengerDto>>(structure,HttpStatus.OK);
		}
		else return null;
		
	}
	
	
	//delete Passenger
	
	public ResponseEntity<ResponseStructure<PassengerDto>> deletePassenger(int Passengerid) {
		
		ResponseStructure<PassengerDto> structure = new ResponseStructure<PassengerDto>();
		Passenger existPassenger = dao.deletePassenger(Passengerid);
		if(existPassenger != null) {
			
			dto.setPname(existPassenger.getPname());
			dto.setPemail(existPassenger.getPemail());
			dto.setPmobileno(existPassenger.getPmobileno());
			dto.setpAge(existPassenger.getpAge());
			dto.setpAgeCategory(existPassenger.getpAgeCategory());
			dto.setPaddress(existPassenger.getPaddress());
			structure.setMessage("Passenger deleted successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<PassengerDto>>(structure,HttpStatus.OK);
		}
		else return null;			
	}
	
	
	//get all Passenger
	
	public ResponseEntity<ResponseStructure<List<PassengerDto>>> getAllPassenger() {
		
		ResponseStructure<List<PassengerDto>> structure = new ResponseStructure<>();
		List<Passenger> existPassengers = dao.getAllPassenger();
		List<PassengerDto> Passengers = new ArrayList<>();
		if(existPassengers != null) {
			
			for(Passenger p: existPassengers) {
				
				dto.setPname(p.getPname());
				dto.setPemail(p.getPemail());
				dto.setPmobileno(p.getPmobileno());
				dto.setpAge(p.getpAge());
				dto.setpAgeCategory(p.getpAgeCategory());
				dto.setPaddress(p.getPaddress());
				Passengers.add(dto);
			}
			
			structure.setMessage("Passengers got successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(Passengers);
			return new ResponseEntity<ResponseStructure<List<PassengerDto>>>(structure,HttpStatus.FOUND);
			
		}
		else return null;
			
	}
	
    //assign ticket passenger
	
	public void assignTicketToPassenger(Ticket t) {
		
		
	    Passenger p = t.getPassenger();
	    if(p != null) {
	    	
	    	p.setTicket(t);
	    	dao.updatePassenger(p, p.getPassengerid());
	    }
	    else return;
		
	}
	
	
}
