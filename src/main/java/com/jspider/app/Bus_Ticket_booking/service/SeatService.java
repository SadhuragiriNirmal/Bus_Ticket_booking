package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.SeatDao;
import com.jspider.app.Bus_Ticket_booking.dto.PassengerDto;
import com.jspider.app.Bus_Ticket_booking.dto.SeatDto;
import com.jspider.app.Bus_Ticket_booking.entity.Passenger;
import com.jspider.app.Bus_Ticket_booking.entity.Seat;
import com.jspider.app.Bus_Ticket_booking.entity.Ticket;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@Service
public class SeatService {
	
	@Autowired
	SeatDao dao;
	
	@Autowired
	SeatDto dto;
	
	@Autowired
    PassengerDto pdto;
	
	@Autowired
	BusService busService;
	
	//save Seat schedule
	
		public ResponseEntity<ResponseStructure<SeatDto>> saveSeat(Seat seat, int busid){
			
			ResponseStructure<SeatDto> structure = new ResponseStructure<SeatDto>();
			
			Seat existSeat = dao.saveSeat(seat);
			if(existSeat != null) {
				
				busService.assignSeatToBus(existSeat, busid);
				dto.setSeatno(existSeat.getSeatno());
				dto.setSeatType(existSeat.getSeatType());
				dto.setSeatPosition(existSeat.getSeatPosition());
				dto.setSeatPrice(existSeat.getSeatPrice());
				structure.setMessage("Seat saved successfully");
				structure.setData(dto);
				structure.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<SeatDto>>(structure,HttpStatus.CREATED);
			}
			else return null;
		}
		
		//get Seat schedule
		
		public ResponseEntity<ResponseStructure<SeatDto>> findBySeatId(int seatid){
			
			ResponseStructure<SeatDto> structure = new ResponseStructure<SeatDto>();
			Seat existSeat = dao.findSeatByid(seatid);
			if(existSeat != null) {
				
				dto.setSeatno(existSeat.getSeatno());
				dto.setSeatType(existSeat.getSeatType());
				dto.setSeatPosition(existSeat.getSeatPosition());
				dto.setSeatPrice(existSeat.getSeatPrice());
				dto.setPassenger(convertPassengerDto(existSeat.getPassenger()));
				structure.setMessage("Seat is found successfully");
				structure.setData(dto);
				structure.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<SeatDto>>(structure,HttpStatus.FOUND);
			}
			else return null;
		}
		
		//update Seat schedule
		
		public ResponseEntity<ResponseStructure<SeatDto>> updateSeat(Seat seat, int seatid){
			
			ResponseStructure<SeatDto> structure = new ResponseStructure<SeatDto>();
			Seat existSeat = dao.updateSeat(seat, seatid);
			if(existSeat != null) {
				
				dto.setSeatno(existSeat.getSeatno());
				dto.setSeatType(existSeat.getSeatType());
				dto.setSeatPosition(existSeat.getSeatPosition());
				dto.setSeatPrice(existSeat.getSeatPrice());
				dto.setPassenger(convertPassengerDto(existSeat.getPassenger()));
				structure.setMessage("Seat is update successfully");
				structure.setData(dto);
				structure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<SeatDto>>(structure,HttpStatus.OK);
			}
			else return null;
		}
		
		//delete Seat schedule
		
		public ResponseEntity<ResponseStructure<SeatDto>> deleteSeat(int seatid){
			
			ResponseStructure<SeatDto> structure = new ResponseStructure<SeatDto>();
			Seat existSeat = dao.deleteSeat(seatid);
			if(existSeat != null) {
				
				dto.setSeatno(existSeat.getSeatno());
				dto.setSeatType(existSeat.getSeatType());
				dto.setSeatPosition(existSeat.getSeatPosition());
				dto.setSeatPrice(existSeat.getSeatPrice());
				dto.setPassenger(convertPassengerDto(existSeat.getPassenger()));
				structure.setMessage("Seat is deleted successfully");
				structure.setData(dto);
				structure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<SeatDto>>(structure,HttpStatus.OK);
			}
			else return null;
		}
		
		//get all Seat schedule
		
		public ResponseEntity<ResponseStructure<List<SeatDto>>> getAllSeat(){
			
			ResponseStructure<List<SeatDto>> structure = new ResponseStructure<>();
			List<Seat> existSeat = dao.getAllSeat();
			List<SeatDto> newSeatDto = new ArrayList<SeatDto>();
			if(existSeat != null) {
				
				for(Seat seat:existSeat) {
					
					SeatDto seatDto = new SeatDto();
					seatDto.setSeatno(seat.getSeatno());
					seatDto.setSeatType(seat.getSeatType());
					seatDto.setSeatPosition(seat.getSeatPosition());
					seatDto.setSeatPrice(seat.getSeatPrice());
					seatDto.setPassenger(convertPassengerDto(seat.getPassenger()));
					newSeatDto.add(seatDto);
					
				}
				structure.setMessage("Seats are found successfully");
				structure.setData(newSeatDto);
				structure.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<List<SeatDto>>>(structure,HttpStatus.FOUND);
				
			}
			else return null;
		}
		

      //passenger to passenger dto conversion
		
	  public PassengerDto convertPassengerDto(Passenger p) {
		  
		  if(p != null) {
			  
		  		pdto.setPname(p.getPname());
		  		pdto.setPemail(p.getPemail());
		  		pdto.setPmobileno(p.getPmobileno());
		  		pdto.setPAge(p.getPAge());
		  		pdto.setPAgeCategory(p.getPAgeCategory());
		  		pdto.setPaddress(p.getPaddress());
		  		return pdto;
		  }
		  else return null;
	  }
	  
	  //assigne passenger to seat
	  
	  public void assignPassengerToSeat(Passenger passenger, int seatid) {
		  
		  if(passenger != null) {
			  
			  Seat seat = dao.findSeatByid(seatid); 
			  if(seat != null) {
				  
				  seat.setPassenger(passenger);
				  dao.updateSeat(seat, seatid);
				  
			  }
		  }
	  }
	  
	  //brake the relation between passenger and seat
	  
	  public void brakeRelationBtwPassengerAndSeat(Ticket ticket) {
		  
		  List<Seat> seats = ticket.getBus().getSeat();
		  
		  for(Seat seat:seats) {
			  
			if(seat.getPassenger() != null) {
				
				if(seat.getPassenger().getPassengerid() == ticket.getPassenger().getPassengerid()) {
					  
					  seat.setPassenger(null);
					  dao.updateSeat(seat, seat.getSeatid());
					  break;
				 }
				
			 }
			  
		  }
		  
	  }
	  
}
