package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.BusScheduleDao;
import com.jspider.app.Bus_Ticket_booking.dto.BusDto;
import com.jspider.app.Bus_Ticket_booking.dto.BusScheduleDto;
import com.jspider.app.Bus_Ticket_booking.dto.SeatDto;
import com.jspider.app.Bus_Ticket_booking.entity.Bus;
import com.jspider.app.Bus_Ticket_booking.entity.BusSchedule;
import com.jspider.app.Bus_Ticket_booking.entity.Seat;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@Service
public class BusScheduleService {
	
	@Autowired
	BusScheduleDao dao;
	
	@Autowired
	BusScheduleDto dto;
	
	@Autowired
	BusService busService;
	
	//save bus schedule
	
	public ResponseEntity<ResponseStructure<BusScheduleDto>> saveBusSchedule(BusSchedule busSchedule){
		
		ResponseStructure<BusScheduleDto> structure = new ResponseStructure<BusScheduleDto>();
		BusSchedule existBusSchedule = dao.saveBusSchedule(busSchedule);
		if(existBusSchedule != null) {
			
			dto = convertBusScheduleToBusScheduleDto(existBusSchedule);
			dto.setBus(convertBusToBusDto(existBusSchedule.getBus()));
			structure.setMessage("bus scheduel saved successfully");
			structure.setData(dto);
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<BusScheduleDto>>(structure,HttpStatus.CREATED);
		}
		else return null;
	}
	
	//get bus schedule
	
	public ResponseEntity<ResponseStructure<BusScheduleDto>> findByBusScheduleId(int bsid){
		
		ResponseStructure<BusScheduleDto> structure = new ResponseStructure<BusScheduleDto>();
		BusSchedule existBusSchedule = dao.findBusScheduleByid(bsid);
		if(existBusSchedule != null) {
			
			dto = convertBusScheduleToBusScheduleDto(existBusSchedule);
			dto.setBus(convertBusToBusDto(existBusSchedule.getBus()));
			structure.setMessage("bus scheduel found successfully");
			structure.setData(dto);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<BusScheduleDto>>(structure,HttpStatus.FOUND);
		}
		else return null;
	}
	
	
	//get bus schedule by departure and destination 
	
	public ResponseEntity<ResponseStructure<BusScheduleDto>> findBusScheduleByFromAndTo(String startplace, String destination){
		
		ResponseStructure<BusScheduleDto> structure = new ResponseStructure<BusScheduleDto>();
		BusSchedule existBusSchedule = dao.findBusScheduleByFromAndTo(startplace, destination);
		if(existBusSchedule != null) {
			
			dto = convertBusScheduleToBusScheduleDto(existBusSchedule);
			dto.setBus(convertBusToBusDto(existBusSchedule.getBus()));
			structure.setMessage("bus scheduel found by dep and arr successfully");
			structure.setData(dto);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<BusScheduleDto>>(structure,HttpStatus.FOUND);
		}
		else return null;
	}
	
	//find bus with from, to and departure date
	
	public ResponseEntity<ResponseStructure<BusScheduleDto>> findBusScheduleByFromToAndDepartureDate(String startplace, String destination, String departureDate){
		
		ResponseStructure<BusScheduleDto> structure = new ResponseStructure<BusScheduleDto>();
		BusSchedule existBusSchedule = dao.findBusScheduleByFromAndTo(startplace, destination);
		if(existBusSchedule != null) {
			
			List<Bus> filteredBus = busService.filterBusByDepartureDate(existBusSchedule.getBus(), departureDate);
			dto = convertBusScheduleToBusScheduleDto(existBusSchedule);
			dto.setBus(convertBusToBusDto(filteredBus));
			structure.setMessage("bus scheduel found by dep and arr successfully");
			structure.setData(dto);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<BusScheduleDto>>(structure,HttpStatus.FOUND);
		}
		else return null;
	}

	
	//update bus schedule
	
	public ResponseEntity<ResponseStructure<BusScheduleDto>> updateBusSchedule(BusSchedule busSchedule, int bsid){
		
		ResponseStructure<BusScheduleDto> structure = new ResponseStructure<BusScheduleDto>();
		BusSchedule existBusSchedule = dao.updateBusSchedule(busSchedule, bsid);
		if(existBusSchedule != null) {
			
			dto = convertBusScheduleToBusScheduleDto(existBusSchedule);
			dto.setBus(convertBusToBusDto(existBusSchedule.getBus()));
			structure.setMessage("bus scheduel updated successfully");
			structure.setData(dto);
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<BusScheduleDto>>(structure,HttpStatus.OK);
		}
		else return null;
	}
	
	//delete bus schedule
	
	public ResponseEntity<ResponseStructure<BusScheduleDto>> deleteBusSchedule(int bsid){
		
		ResponseStructure<BusScheduleDto> structure = new ResponseStructure<BusScheduleDto>();
		BusSchedule existBusSchedule = dao.deleteBusSchedule(bsid);
		if(existBusSchedule != null) {
			
			dto = convertBusScheduleToBusScheduleDto(existBusSchedule);
			dto.setBus(convertBusToBusDto(existBusSchedule.getBus()));
			structure.setMessage("bus scheduel deleted successfully");
			structure.setData(dto);
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<BusScheduleDto>>(structure,HttpStatus.OK);
		}
		else return null;
	}
	
	//get all bus schedule
	
	public ResponseEntity<ResponseStructure<List<BusScheduleDto>>> getAllBusSchedule(){
		
		ResponseStructure<List<BusScheduleDto>> structure = new ResponseStructure<>();
		List<BusSchedule> existBusSchedules = dao.getAllBusSchedule();
		List<BusScheduleDto> newBusScheduleDto = new ArrayList<BusScheduleDto>();
		if(existBusSchedules != null) {
			
			for(BusSchedule bs:existBusSchedules) {
				
				BusScheduleDto scheduleDto = new BusScheduleDto();
				scheduleDto.setFrom(bs.getFrom());
				scheduleDto.setTo(bs.getTo());
				scheduleDto.setVia(bs.getVia());
				scheduleDto.setKm(bs.getKm());
				scheduleDto.setBus(convertBusToBusDto(bs.getBus()));
				newBusScheduleDto.add(scheduleDto);
				
			}
			structure.setMessage("bus scheduels found successfully");
			structure.setData(newBusScheduleDto);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<BusScheduleDto>>>(structure,HttpStatus.FOUND);
			
		}
		else return null;
	}
	
	//bus schedule to bus scheduleDto conversion
	
	public BusScheduleDto convertBusScheduleToBusScheduleDto(BusSchedule busSchedule) {
		
		dto.setFrom(busSchedule.getFrom());
		dto.setTo(busSchedule.getTo());
		dto.setVia(busSchedule.getVia());
		dto.setKm(busSchedule.getKm());
		return dto;
	}

	//bus to busDto conversion
	
	public List<BusDto> convertBusToBusDto(List<Bus> bus) {
		
		List<BusDto> buses = new ArrayList<BusDto>();
		if(bus != null) {
			
			for(Bus b: bus) {
				
				BusDto busDto = new BusDto();
				busDto.setCompany(b.getCompany());
				busDto.setBusno(b.getBusno());
				busDto.setBusCapacity(b.getBusCapacity());
				busDto.setBusType(b.getBusType());
				busDto.setDeparturePlace(b.getDeparturePlace());
				busDto.setArrivalPlace(b.getArrivalPlace());
				busDto.setDepartureDate(b.getDepartureDate());
				busDto.setArrivalDate(b.getArrivalDate());
				busDto.setDepartureTime(b.getDepartureTime());
				busDto.setArrivalTime(b.getArrivalTime());
				busDto.setJourneyDuration(b.getJourneyDuration());
				busDto.setSeat(convertSeatListToSeatDtoList(b.getSeat()));
				buses.add(busDto);
				
			}return buses;
			
		}
		else return null;

	}
	
	//convert seat to seat dto
	
	public List<SeatDto> convertSeatListToSeatDtoList(List<Seat> seats){
		
		List<SeatDto> seatDtoList = new ArrayList<SeatDto>();
		if(seats != null) {
			
			for(Seat seat:seats) {
				
				SeatDto seatDto = new SeatDto();
				seatDto.setSeatno(seat.getSeatno());
				seatDto.setSeatType(seat.getSeatType());
				seatDto.setSeatPosition(seat.getSeatPosition());
				seatDto.setSeatPrice(seat.getSeatPrice());
				seatDto.setBookedDate(seat.getBookedDate());
				seatDtoList.add(seatDto);
			}
			return seatDtoList;
		}
		else return null;
		
	}	
	
}

